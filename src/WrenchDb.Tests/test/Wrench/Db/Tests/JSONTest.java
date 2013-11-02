/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.Core.Helpers.JSONHelper;
import WrenchDb.Core.Helpers.JSONRenderer;
import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Helpers.HContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.ws.spi.http.HttpContext;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author d.fontani
 */
public class JSONTest {
    
    public List<Object> items()
    {
         List<Object> objs= new ArrayList<Object>();
            objs.add(  "PROVA\"?^^^''''");//stirng
            objs.add(new Double(23.52));
            objs.add(new Float(345.4f));
            objs.add( 3344.6f);
            objs.add( new Integer(234));
            objs.add(22222);
            objs.add(21312323l);
            objs.add(new Long(2222));
            objs.add(new Date());
            return objs;
    }
        
    @Test
    public void ValueSerialization()
    { 
            String s;
             List<Object> objs=items();
             for(Object o : objs)
             {
                 s=JSONHelper.ConvertBaseType(o);
             }
             
    }
    
    @Test
    public void PropertyListSerialization()
    { 
            String s;
             List<Object> objs=items();
             JSONRenderer jr= new JSONRenderer();
             int i=1;
             for(Object o : objs)
             {
                 jr.AppendProperty("p"+i, o);
                 i++;
             }
           String result= jr.GetJSON();
    }
}
