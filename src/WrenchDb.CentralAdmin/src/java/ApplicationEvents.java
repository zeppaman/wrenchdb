
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Data.Configuration.CrudTableSet;
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
       HContext.Init( sce.getServletContext().getRealPath("WEB-INF/wdb_db.properties"));
        CrudTableSet cs= new CrudTableSet();
         cs.LoadItems();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
