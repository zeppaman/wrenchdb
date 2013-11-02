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
        ct.DefaultSortColumnOrder= CrudTableSortOrder.asc;
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
        ct.DefaultSortColumnOrder= CrudTableSortOrder.asc;
        ct.InitialRowCount=20;
        ct.Title="TITLE";
        ct.Columns.add(ctm);
        ct.Columns.add(ctm);
        ct.Columns.add(ctm);
        
         String tableJSON =ct.RenderAsHtml();
    }
     
}
