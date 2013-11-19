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
import WrenchDb.Data.Enums.SqlOrderDirection;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.Data.Model.CrudTableColumn;
import WrenchDb.Data.Model.Enums.CrudTableDataType;
import WrenchDb.Data.Model.Enums.CrudTableSortOrder;
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
public class CrudTableModelTests {
   
    @Test
    public void RenderColumnJSON()
    {
        CrudTableColumn ctm = new CrudTableColumn();
        ctm.Name="ColName";
        ctm.Header="Title";
        ctm.Hidden=true;
        ctm.IsPrimaryKey=true;
        ctm.Width=200;
        String columnJSON= ctm.RenderAsJSON();
      assertEquals(columnJSON,
              "{\"name\":\"ColName\",\"index\":\"ColName\",\"hidden\":true,\"key\":true,\"header\":\"Title\",\"width\":200}");
    }
    
    @Test
    public void RenderTableJSON()
    {
        CrudTableColumn ctm = new CrudTableColumn();
        ctm.Name="ColName";
        ctm.Header="Title";
        ctm.Hidden=true;
        ctm.IsPrimaryKey=true;
        ctm.Width=200;
        
        
        CrudTable ct= new CrudTable();
        ct.InstanceId="prova";
        ct.DataType= CrudTableDataType.json;
        ct.DataUrl="prova.it";
        ct.DefaultSortColumnName="provaSortName";
        ct.DefaultSortColumnOrder= SqlOrderDirection.ASC;
        ct.InitialRowCount=20;
        ct.Title="TITLE";
        ct.Columns.add(ctm);
        ct.Columns.add(ctm);
        ct.Columns.add(ctm);
        
        String tableJSON =ct.RenderAsJSON();
    }
     
    @Test
    public void RenderTableHtml()
    {
        
        
        CrudTableColumn ctm = new CrudTableColumn();
        ctm.Name="ColName";
        ctm.Header="Title";
        ctm.Hidden=true;
        ctm.IsPrimaryKey=true;
        ctm.Width=200;
        
        
        CrudTable ct= new CrudTable();
        ct.InstanceId="prova";
        ct.DataType= CrudTableDataType.json;
        ct.DataUrl="prova.it";
        ct.DefaultSortColumnName="provaSortName";
        ct.DefaultSortColumnOrder= SqlOrderDirection.ASC;
        ct.InitialRowCount=20;
        ct.Title="TITLE";
        ct.Columns.add(ctm);
        ct.Columns.add(ctm);
        ct.Columns.add(ctm);
        
         String tableJSON =ct.RenderAsHtml();
    }
     
}
