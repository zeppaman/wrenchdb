package WrenchDb.Web.Defaults.Resources;

/* 
 * Copyright (C) 2013 Daniele Fontani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import WrenchDb.Core.Helpers.DateHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import WrenchDb.Deploy.Database.WdbDatabaseDeployer;
import WrenchDb.Deploy.Model.IFileDeployItem;
import WrenchDb.Deploy.Model.JarPackage;
import WrenchDb.Deploy.Model.WarPackage;
import WrenchDb.Deploy.Server.WdbDeployRepository;
import WrenchDb.Deploy.Server.WdbServerDeployer;
import WrenchDb.MVC.Annotations.Action;
import WrenchDb.MVC.Annotations.Controller;
import WrenchDb.MVC.BaseClasses.ActionResult;
import WrenchDb.MVC.BaseClasses.ControllerBase;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import WrenchDb.MVC.BaseClasses.Model.RequestModel;
import WrenchDb.MVC.Enums.RequestMethod;
import WrenchDb.MVC.Helpers.FileUploaderHelper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
@Controller(
        ControllerName = "ReleaseController")
public class ReleaseController extends ControllerBase {

  @Action
    public ActionResult addRelease(ModelBase model) {
    ActionResult r = new ActionResult(
                "addrelease",
                "OneColumn", 
                new ModelBase());
    long application_id=Long.parseLong(model.get("id"));
     WdbApplication application= HContext.Current().Get(WdbApplication.class, application_id);
            
             r.Model.getProperties().put("application", application);
    return r;
  }
    @Action
    public ActionResult Release(ModelBase model) {
        
        RequestModel rm=((RequestModel)model);
        
        
         ActionResult r = new ActionResult(
                "viewrelease",
                "OneColumn", 
                new ModelBase());
         
        if(rm.Method== RequestMethod.POST)
        {
             SaveNewRelease(rm);
        }
        else
        {
             long releaseId=Long.parseLong(rm.get("id"));
             WdbRelease rel= HContext.Current().Get(WdbRelease.class, releaseId);
            
             r.Model.getProperties().put("release", rel);
             
        }
       


        return r;
    }
    
  @Action
  public ActionResult Deploy(ModelBase model) {
 
         ActionResult r = new ActionResult(
                "deploy",
                "OneColumn", 
                new ModelBase());
         StringBuilder sb= new StringBuilder();
         long releaseId=Long.parseLong(model.get("id"));
         WdbRelease rel= HContext.Current().Get(WdbRelease.class, releaseId);
         Deploy(sb,rel);
         r.Model.getProperties().put("log", sb.toString());
         r.Model.getProperties().put("release", rel);
         return r;
         
 }
    public void SaveNewRelease(RequestModel rm)  {
        WdbDeployRepository repo = new WdbDeployRepository(WdbApplicationSettings.Current().getProperty("repoRoot"));
        String tempFileName= repo.getTempRandomFilePath();
           
      
       FileUploaderHelper.UploadFile(rm.OriginalRequest,"file",tempFileName);
       //Load JAR File
       JarPackage jp= new JarPackage();
       jp.LoadFromFile((tempFileName));
      long application_id=Long.parseLong(rm.get("id"));
       WdbDatabaseDeployer db= WdbDatabaseDeployer.getDatabaseDeployer(application_id);
       db.DeployPendingChanges();
       // Save release and jar
       Session s= HContext.Current().getSessionFactory().openSession();
       Transaction t=null;
       try
       {
       t=s.beginTransaction();
      
       
       
       
       WdbApplication parentApp=(WdbApplication)s.get(WdbApplication.class,application_id);
       
       long release=0;
       List<WdbRelease> prevReleases= s.createCriteria(WdbRelease.class)
               .add(Restrictions.eq("wdbApplication.applicationId",application_id))
               .addOrder(Order.desc("wdbReleaseId"))
               .setFetchSize(1)
               .list();
       
       if(prevReleases==null || prevReleases.size()>0)
       {
           release=prevReleases.get(0).getReleaseVersion()+1;
       }
       
               
               
       WdbRelease newRelease= new WdbRelease();
       newRelease.setDatabaseVersion(1);//TODO: GET CURRENT VALUE FROM CHANGESCRIPT
       newRelease.setReleaseVersion(release);
       newRelease.setReleaseFullversion("1.0.0"+release);
       newRelease.setReleaseMemocode(parentApp.getApplicationName()+"_"+release);
       newRelease.setReleasedOn(DateHelper.getNow());
       newRelease.setWdbApplication(parentApp);
       s.save(newRelease);
       
       //Do upload & redirect
   
       repo.setReleaseCacheItem(newRelease);
        
       repo.storeJarCustomization(newRelease.getWdbReleaseId(),jp);
      
       
       t.commit();
     
       }
       catch(Exception err)
       {
           Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE,"SaveNewRelease",err);
           t.rollback();
          throw  err;
       }
       finally
       {
           if(s!=null && s.isOpen())
           {
                  s.flush();
                  s.close();
           }
       }
    }

    private void Deploy(StringBuilder sb, WdbRelease rel) {
       WdbDeployRepository repo = new WdbDeployRepository(WdbApplicationSettings.Current().getProperty("repoRoot"));
      appendLine(sb,"Created Deploy Repository");       
      appendLine(sb,"Start deploy for release"+rel.getWdbReleaseId());
       repo.setReleaseCacheItem(rel);
       sb.append("release context set");sb.append("\n");
       try
       {
            repo.createReleaseWar(rel.getWdbReleaseId());
            appendLine(sb,"release war created");
            String repoWar= repo.getReleaseWarPath(rel.getWdbReleaseId());
            appendLine(sb, repoWar);
            WarPackage pkg= new WarPackage();
            pkg.LoadFromFile(repoWar);
            appendLine(sb, "Package loaded");            
            WdbServerDeployer deplo = WdbServerDeployer.getWebServerDeployer(rel.getWdbApplication().getApplicationId());
            // TODO: Ensure rigth deploy sequence based on derver type (stop-deploy-start?)
           // deplo.Install();
            appendLine(sb, "Installed war");
            deplo.Deploy(pkg);
           
       }
       catch(Exception err)
       {
           appendLine(sb,"[ERROR]"+ err.getMessage());
       }
    }

    public void appendLine(StringBuilder sb,String message) {
        sb.append(message);sb.append("\n");
    }
    
    
    
    
}
