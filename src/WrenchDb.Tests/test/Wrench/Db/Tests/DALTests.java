/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Helpers.HContext;
import java.io.IOException;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d.fontani
 */
public class DALTests {
    
    public DALTests() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    
     @Test
    public void InitHContext()
    {
        //relative path match WtrenchDb.Test folder. Config files are inside test folder
        HContext.Init("test/wdb_db.properties");
        assertTrue( HContext.Current().getProperties().size()>0);
       
    }
     
     
    @Test
    public void OrmInit()
    {
       InitHContext();
        WdbApplication app= new WdbApplication();
        app.setApplicationHostname(22);
        app.setApplicationName("testApp");
        
         HContext.Current().SaveOrUpdate(app);
        
       WdbApplication appedit=  HContext.Current().<WdbApplication>Get(WdbApplication.class,app.getApplicationId());
        
     
    }
    
     @Test
    public void QueryInit()
    {
         InitHContext();
         Session s=HContext.Current().getSessionFactory().openSession();
         WdbApplication app=(WdbApplication)s.createCriteria(WdbApplication.class).uniqueResult();
         s.close();
     
    }
    
    
}