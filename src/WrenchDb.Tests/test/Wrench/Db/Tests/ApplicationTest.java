/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import java.io.IOException;
import java.util.Properties;
import org.junit.Test;

/**
 *
 * @author Daniele Fontani
 */
public class ApplicationTest {
    
    
 
    
    @Test
    public void LoadTest() throws IOException
    {
        
         HContext.Init("test/wdb_db.properties");
       
        Properties p= new Properties();
        p.setProperty("application_id","23");
        WdbApplicationSettings.Init(p);
    }
}
