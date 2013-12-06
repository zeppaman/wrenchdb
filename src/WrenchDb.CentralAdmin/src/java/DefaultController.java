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

import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Data.Configuration.CrudTableSet;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.MVC.Annotations.Action;
import WrenchDb.MVC.Annotations.Controller;
import WrenchDb.MVC.BaseClasses.ActionResult;
import WrenchDb.MVC.BaseClasses.ControllerBase;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import WrenchDb.MVC.BaseClasses.Model.RequestModel;
import java.util.List;
import org.codehaus.cargo.container.ContainerType;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.configuration.ConfigurationType;
import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.deployable.Deployable;
import org.codehaus.cargo.container.deployable.DeployableType;
import org.codehaus.cargo.container.deployer.Deployer;
import org.codehaus.cargo.container.tomcat.Tomcat4xRemoteDeployer;
import org.codehaus.cargo.container.tomcat.TomcatCopyingInstalledLocalDeployer;
import org.codehaus.cargo.container.tomcat.TomcatExistingLocalConfiguration;
import org.codehaus.cargo.container.tomcat.TomcatManager7x8xInstalledLocalDeployer;
import org.codehaus.cargo.container.tomcat.TomcatWAR;
import org.codehaus.cargo.generic.DefaultContainerFactory;
import org.codehaus.cargo.generic.configuration.ConfigurationFactory;
import org.codehaus.cargo.generic.configuration.DefaultConfigurationFactory;
import org.codehaus.cargo.generic.deployable.DefaultDeployableFactory;
import org.codehaus.cargo.generic.deployable.DeployableFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
@Controller(
        ControllerName = "DefaultController")
public class DefaultController extends ControllerBase {

//    @Override
//    public   String getName()
//    {
//        return "DeafultController";
//    }
    @Action
    public ActionResult Index(ModelBase model) {
        ActionResult r = new ActionResult("default", "OneColumn", new ModelBase());

        r.Model = new ModelBase();
        r.Model.Properties.put("id", "MODIFICATO");
        r.Model.Properties.put("link", "/TestNotation/default/Prova/123");



//       
//  Deployable war = new TomcatWAR("C:\\temp\\sample.war");
//
//        ConfigurationFactory configurationFactory =
//                new DefaultConfigurationFactory();
//        
//        LocalConfiguration configuration = new TomcatExistingLocalConfiguration("C:\\Users\\DanieleFontani\\AppData\\Roaming\\NetBeans\\7.3.1\\apache-tomcat-7.0.34.0_base");
//        configuration.addDeployable(war);
//        
//
//        InstalledLocalContainer container =
//                (InstalledLocalContainer) new DefaultContainerFactory().createContainer(
//                "tomcat7x", ContainerType.INSTALLED, configuration);
//        container.setHome("C:\\Users\\DanieleFontani\\AppData\\Roaming\\NetBeans\\7.3.1\\apache-tomcat-7.0.34.0_base");
//         
//      
//        DeployableFactory factory = new DefaultDeployableFactory();
//         war = factory.createDeployable(container.getId(), "C:\\temp\\sample.war",
//            DeployableType.WAR);
//        
//            
//       Deployer deployer = new TomcatCopyingInstalledLocalDeployer(container);
//       
//       deployer.deploy(war);
//       container.start();
//       container.stop();
//
        return r;

    }

    @Action
    public ActionResult Prova(ModelBase model) {
        ActionResult r = new ActionResult("ok", "OneColumn", new ModelBase());

        r.Model = new ModelBase();
        r.Model.Properties.put("id", "MODIFICATO");
        return r;
    }
    
      @Action
    public ActionResult ViewActionDetails(ModelBase model) {
        ActionResult r = new ActionResult("applicationdetail", "OneColumn", new ModelBase());
      long applicationId=Long.parseLong(model.Properties.get("id").toString());
        WdbApplication app= HContext.Current().Get(WdbApplication.class,applicationId );
        List<WdbRelease> rels=HContext.Current().getSessionFactory()
                .openSession()
                .createCriteria(WdbRelease.class)
                .add(Restrictions
                .eq("wdbApplication.applicationId",applicationId))
                .addOrder(Order.desc("wdbReleaseId")).list();
        
        r.Model = new ModelBase();
        r.Model.Properties.put("application", app);
         r.Model.Properties.put("releases", rels);
        
        return r;
    }
    
    
}
