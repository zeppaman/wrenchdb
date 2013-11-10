/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
import WrenchDb.Core.Configuration.ItemAppendingConfiguration;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.Data.Model.CrudTableColumn;
import WrenchDb.Data.Model.Enums.CrudTableDataType;
import WrenchDb.Data.Model.Enums.CrudTableSortOrder;
import WrenchDb.MVC.BaseClasses.CssReference;
import WrenchDb.MVC.BaseClasses.JSReference;
import WrenchDb.MVC.BaseClasses.LinkReference;
import WrenchDb.MVC.BaseClasses.RoutingConfiguration;
import WrenchDb.MVC.BaseClasses.ScriptReference;
import WrenchDb.MVC.Configuration.RouteMap;
import java.util.ArrayList;

@ItemAppenderConfigurator(Name = "CrudTableSet")
public class CrudTableConfiguration 
    extends  ItemAppendingConfiguration<CrudTable> {

            @Override
            public void Configure(ArrayList<CrudTable> configuration)
            {
             


                CrudTable ct= new CrudTable();
                ct.InstanceId="prova";
                ct.DataType= CrudTableDataType.json;
                ct.DataUrl="prova.it";
                ct.DefaultSortColumnName="provaSortName";
                ct.DefaultSortColumnOrder= CrudTableSortOrder.asc;
                ct.InitialRowCount=20;
                ct.Title="TITLE";
                for(int i=0;i<10;i++)
                {
                   CrudTableColumn ctm = new CrudTableColumn();
                     ctm.Name="ColName"+i;
                    ctm.Header="Title"+i;
                    ctm.Hidden=false;
                    ctm.IsPrimaryKey=false;
                    ctm.Width=200;
                    ct.Columns.add(ctm);
                }

                 configuration.add(ct);
                
            }
       
    }
    
    

