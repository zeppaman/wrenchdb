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
import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
import WrenchDb.Core.Configuration.ItemAppendingConfiguration;
import WrenchDb.Data.Enums.SqlOrderDirection;
import WrenchDb.Data.Helpers.CrudTableBuilder;
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
             
                
              CrudTableBuilder ct= new CrudTableBuilder("wdb_application","application_name", SqlOrderDirection.ASC,"Applications","wdb_application");
              
              ct.AddColumn("ApplicationID",true,true,"application_id",100);
              ct.AddColumn("Application Name","application_name");
              ct.AddColumn("Host Name","application_hostname");
              
              configuration.add(ct.getCrudTable());
                
            }
       
    }
    
    


