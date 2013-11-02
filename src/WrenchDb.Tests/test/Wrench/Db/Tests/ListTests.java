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
import WrenchDb.Data.Model.CrudTableColumn;
import WrenchDb.Data.Model.Lists.CrudColumnList;
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
public class ListTests 
{
    @Test
    public void List()
    {
     
        CrudColumnList cr= new CrudColumnList();
        for(int i=0; i<100;i++)
        {
              CrudTableColumn c= new CrudTableColumn();
              c.setName("P"+i);
              cr.add(c);
        }
        CrudTableColumn c=cr.getByName("P20");
        assertNotNull(c);
        assertEquals(c.getName(), "P20");
    }
}
