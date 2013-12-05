/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests.Helpers;

import Wrench.Db.Tests.DALTests;
import WrenchDb.DAL.Helpers.HContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Daniele Fontani
 */
public class Utils {
     public static  void InitHContext()
    {
        try {
            //relative path match WtrenchDb.Test folder. Config files are inside test folder
            HContext.Init("test/wdb_db.properties");
        } catch (IOException ex) {
            Logger.getLogger(DALTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue( HContext.Current().getProperties().size()>0);
       
    }
}
