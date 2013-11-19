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
        @Test
    public void StringReplace()
    {
      String  literalValue="aaaa\"ss\"ss";
      String escapedValue=literalValue.replace("\"", "\\\"");
      assertEquals("aaaa\\\"ss\\\"ss", escapedValue);
    }
}

