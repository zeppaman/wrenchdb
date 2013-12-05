
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import WrenchDb.Data.Configuration.CrudTableSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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
        try {
            prop.load(new FileInputStream(sce.getServletContext().getRealPath("WEB-INF/wdb_db.properties")));
        } catch (IOException ex) {
            Logger.getLogger(ApplicationEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HContext.Init(prop);
        
        WdbApplicationSettings.Init(prop);
        
        CrudTableSet cs= new CrudTableSet();
        cs.LoadItems();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
