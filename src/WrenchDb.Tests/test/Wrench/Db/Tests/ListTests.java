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
