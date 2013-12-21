package WrenchDb.Web.Defaults.Application;


import WrenchDb.Core.Helpers.ReflectionHelper;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import WrenchDb.Data.Configuration.CrudTableSet;
import WrenchDb.MVC.Annotations.MainApplication;
import WrenchDb.MVC.BaseClasses.Model.ApplicationInfo;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniele Fontani
 */
public class ApplicationEvents implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
         //TODO: FIX IN GLOBAL CONFIGURATION
        Properties prop =new Properties();
        String webBase="";
        try {
            prop.load(new FileInputStream(sce.getServletContext().getRealPath("WEB-INF/wdb_db.properties")));
            webBase=sce.getServletContext().getRealPath("WEB-INF");
        } catch (IOException ex) {
            Logger.getLogger(ApplicationEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HContext.Init(prop);
        
        WdbApplicationSettings.Init(prop);
        
        WdbApplicationSettings.Current().put("serverletContextPath", sce.getServletContext().getContextPath());
        WdbApplicationSettings.Current().put("webBase", webBase);
        //Make repoRoot absolute path
        String repoRoot=WdbApplicationSettings.Current().getProperty("repoRoot");
        if(repoRoot!=null && !repoRoot.startsWith("/"))
        {
            WdbApplicationSettings.Current()
                    .setProperty("repoRoot", 
                    sce.getServletContext().getRealPath(repoRoot));
        }
        
        
        CrudTableSet cs= new CrudTableSet();
        cs.LoadItems();
        
        Set<Class<?>> infos = ReflectionHelper.getTypesAnnotatedWith( MainApplication.class);
        if(infos==null || infos.size()!=1)
        {
            sce.getServletContext().log("UNABLE TO START APPLICATION CONTEXT =>> ONE OR MORE APPLICATION DEFINED");
            return;
        }
        
        Class<?> infotemplate=(Class)infos.toArray()[0];
         ApplicationInfo info=null;
        try {
            
            Constructor<?> ctor;
            ctor = infotemplate.getConstructor(null);
                   
            info = (ApplicationInfo) ctor.newInstance();
       
        } catch (Exception ex) {
            Logger.getLogger(ApplicationEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(info==null) return;
         WdbApplicationSettings.Current().put("applicationInfo",info);
         info.CopyToWeb(webBase);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
