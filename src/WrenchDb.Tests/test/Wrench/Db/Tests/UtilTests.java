/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Helpers.HContext;
import java.io.IOException;
import java.util.Date;
import javax.xml.ws.spi.http.HttpContext;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author d.fontani
 */
public class UtilTests {
      @Test
    public void TestSplit()
    {
        String test="/a/b/c/d/e/f";
       // String[] splitted=test.split("/",-1);        
        // assertSame(6, splitted.length);
       String[]  splitted=WdbStringHelper.split(test, "/");
         assertSame(6, splitted.length);
        
    }
}

