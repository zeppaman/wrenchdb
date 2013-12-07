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
import WrenchDb.Data.Enums.JqGridFieldType;
import WrenchDb.Data.Enums.SqlDataTypes;
import WrenchDb.Data.Enums.SqlOrderDirection;
import WrenchDb.Data.Enums.jqGridEditType;
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
             
                //wdb_application
                CrudTableBuilder ctb
                        = new CrudTableBuilder("wdb_application","application_name", SqlOrderDirection.ASC,"Applications","wdb_application");
                
                ctb
               .AllowEdit()
               .AllowDelete()
               .AllowView()
               .AddPK("application_id", SqlDataTypes.Integer)
               .AddColumn("Application Name","application_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
               .AddColumn("Host Name","application_hostname", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
               .EditColumn("application_name",true,true)
                        .AddColumn("Server Username","server_user", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
               .EditColumn("server_user",true,true)
                        
               .AddColumn("Sever application_server_uri","application_server_uri", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
               .EditColumn("application_server_uri",true,true)
                        
                        .AddColumn("Sever Password","server_password", SqlDataTypes.Text,JqGridFieldType.Password)
               .EditColumn("server_password",true,true)
               .EditAsUrl("application_hostname","Host Name",true)
                        .LookupColumn("Server", "servertype_id", SqlDataTypes.Integer,true,true,
                         "wdb_servertype","servertype_id","servertype_name")
                         .EditAsLookup("servertype_id","Server",true)
                        .LookupColumn("Database", "databasetype_id", SqlDataTypes.Integer,true,true,
                         "wdb_databasetype","databasetype_id","databasetype_name")
                         .EditAsLookup("databasetype_id","Server",true)
               .AddColumn("Jdbc","database_jdbc", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
               .EditColumn("database_jdbc",true,true)          
               .AddColumn("Db Username","database_username", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
               .EditColumn("database_username",true,true)          
                .AddColumn("Db Password","database_password", SqlDataTypes.Text,JqGridFieldType.Password)
               .EditColumn("database_password",true,true)
                .AddColumn("Details","link", SqlDataTypes.Text,JqGridFieldType.Expression)
                .Expression("link","'<a class=''btn btn-info'' href=''/WrenchDb.CentralAdmin/DefaultController/ViewActionDetails/'||application_id||''' >details</a>'");
              
                configuration.add(ctb.getCrudTable());
                
     
                ctb= new CrudTableBuilder("wdb_entity","entity_name", SqlOrderDirection.ASC,"Entities","wdb_entity");
                ctb
               .AllowEdit()
               .AllowDelete()
               .AllowView()
                 //Schema definition
                .AddPK("entity_id",SqlDataTypes.Integer)
             
                .AddColumn("Entity", "entity_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                .AddColumn("Desc","entity_desc", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                .AddColumn("Managed","is_managed", SqlDataTypes.Boolean,JqGridFieldType.CheckBox)
                .LookupColumn("Application","application_id",SqlDataTypes.Integer,true,true,"wdb_application","application_id","application_name")
                 //Scaffolding 
                .EditColumn("entity_name",true,true)
                .EditColumn("entity_desc",true,false)
                .EditAsCheckBox("is_managed","Is Managed",true)
                .EditAsLookup("application_id","Application",true);
                
                //Edit basta da solo: deducendo il tipo da  JqGridFieldType apposto sulla colonna
                 configuration.add(ctb.getCrudTable());
                 
                 
                 
                 
                // wdb_property 
                ctb= new CrudTableBuilder("wdb_property","property_name", SqlOrderDirection.ASC,"Entity Properties","wdb_property");
                
                ctb
               .AllowEdit()
               .AllowDelete()
               .AllowView()
                 //Schema definition
                .AddPK("property_id",SqlDataTypes.Integer)
                .LookupColumn("Entity", "entity_id", SqlDataTypes.Integer,true,true,"wdb_entity","entity_id","entity_name")
                         .EditAsLookup("entity_id","Entity",true)
                .LookupColumn("Type", "property_type", SqlDataTypes.Integer,true,true,"wdb_property_type","property_type","property_type_name")
                         .EditAsLookup("property_type","Type",true)
                .AddColumn("Name","property_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                         .EditColumn("property_name",true,true)
                .AddColumn("Label","property_label", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("property_label",true,true)
                .AddColumn("Width","width", SqlDataTypes.Integer,JqGridFieldType.Integer)
                        .EditColumn("width",false,false)
                .AddColumn("Show in New","is_innew", SqlDataTypes.Boolean,JqGridFieldType.CheckBox)
                         .EditAsCheckBox("is_innew","Show in New From",true)
                .AddColumn("Show in Edit","is_inedit", SqlDataTypes.Boolean,JqGridFieldType.CheckBox)
                         .EditAsCheckBox("is_inedit","Show in Edit From",true)
                .AddColumn("Show in View","is_inview", SqlDataTypes.Boolean,JqGridFieldType.CheckBox)
                         .EditAsCheckBox("is_inview","Show in View From",true)
                .AddColumn("Show in List","is_inlist", SqlDataTypes.Boolean,JqGridFieldType.CheckBox)
                         .EditAsCheckBox("is_inlist","Show in List ",true)
                .AddColumn("Config","config_data", SqlDataTypes.Text,JqGridFieldType.MultiLineText)
                        .EditColumn("config_data",false,false); 
                        
                configuration.add(ctb.getCrudTable());
                
                // CREATE TABLE wdb_changescript
                  
               ctb= new CrudTableBuilder("wdb_changescript","changescript_id", SqlOrderDirection.DESC,"Db Changes","wdb_changescript");
               ctb
               .AllowEdit()
               .AllowDelete()
               .AllowView()
                 //Schema definition
                .AddPK("changescript_id",SqlDataTypes.Integer)
                .LookupColumn("Application","application_id",SqlDataTypes.Integer,true,true,"wdb_application","application_id","application_name")
                        .EditAsLookup("application_id","Application",true)
                .AddColumn("Desc","changescript_desc", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("changescript_desc",true,true)
               .AddColumn("Script","changescript_sql", SqlDataTypes.Text,JqGridFieldType.MultiLineText)
                        .EditColumn("changescript_sql",true,true)
               .AddColumn("Deployed","is_deployed", SqlDataTypes.Text,JqGridFieldType.CheckBox)
                        .EditAsCheckBox("is_deployed","Is Deployed",true);
               
               configuration.add(ctb.getCrudTable());
                //CREATE TABLE wdb_settings
        
                ctb= new CrudTableBuilder("wdb_settings","setting_key", SqlOrderDirection.ASC,"Settings","wdb_settings");
                     ctb
               .AllowEdit()
               .AllowDelete()
               .AllowView()
                 //Schema definition
                .AddPK("setting_id",SqlDataTypes.Integer)
                .LookupColumn("Application","application_id",SqlDataTypes.Integer,true,true,"wdb_application","application_id","application_name")
                     .EditAsLookup("application_id","Application",true) 
                
                    .AddColumn("Category","setting_category", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("setting_category",true,true)
                              .AddColumn("Key","setting_key", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("setting_key",true,true)
                              .AddColumn("Value","setting_value", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("setting_value",true,true);
                       
                configuration.add(ctb.getCrudTable());
                
                
                //CREATE TABLE wdb_sitemap
           
                ctb= new CrudTableBuilder("wdb_sitemap","sitemap_name", SqlOrderDirection.ASC,"Sitemap","wdb_sitemap");
                        ctb
               .AllowEdit()
               .AllowDelete()
               .AllowView()
                 //Schema definition
                .AddPK("sitemap_id",SqlDataTypes.Integer)               
                .LookupColumn("Application","application_id",SqlDataTypes.Integer,true,true,"wdb_application","application_id","application_name")
                     .EditAsLookup("application_id","Application",true) 
                         .AddColumn("Name","sitemap_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("sitemap_name",true,true)
                        .AddColumn("Label","sitemap_label", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                        .EditColumn("sitemap_label",true,true)                        
                        .AddColumn("Url","sitemap_url", SqlDataTypes.Text,JqGridFieldType.Url)
                       // .EditAsUrl("sitemap_url","Url",true) 
                        .AddColumn("Config","config_data", SqlDataTypes.Text,JqGridFieldType.MultiLineText)
                        .EditColumn("config_data",false,false)
                        //SELF JOIN
                        .LookupColumn("Parent","parent_sitemap_id",SqlDataTypes.Integer,true,true,"wdb_sitemap","sitemap_id","sitemap_label")
                        .EditAsLookup("parent_sitemap_id","Parent",true) ; 
                configuration.add(ctb.getCrudTable());
                
                
                
                //CREATE TABLE wdb_property_type
                //(
                //  property_type bigserial NOT NULL,
                //  property_type_name character varying,
                //  property_type_desc character varying,
                  ctb= new CrudTableBuilder("wdb_property_type","property_type_name", SqlOrderDirection.ASC,"Property Types","wdb_property_type");
                   ctb
                .AddPK("property_type",SqlDataTypes.Integer)  
                .AddColumn("Name","property_type_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                .AddColumn("Desc","property_type_desc", SqlDataTypes.Text,JqGridFieldType.MultiLineText);
                    configuration.add(ctb.getCrudTable());
                    
                    
                    
                    ctb= new CrudTableBuilder("wdb_databasetype","databasetype_name", SqlOrderDirection.ASC,
                            "Databases","wdb_databasetype");
                   ctb
                .AddPK("databasetype_id",SqlDataTypes.Integer)  
                .AddColumn("Name","databasetype_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                .AddColumn("Deployer","databasetype_deployer", SqlDataTypes.Text,JqGridFieldType.MultiLineText);
                    configuration.add(ctb.getCrudTable());
                    
                    ctb= new CrudTableBuilder("wdb_servertype","servertype_name", SqlOrderDirection.ASC,
                            "Server","wdb_servertype");
                   ctb
                .AddPK("servertype_id",SqlDataTypes.Integer)  
                .AddColumn("Name","servertype_name", SqlDataTypes.Text,JqGridFieldType.SingleLineText)
                .AddColumn("Deployer","servertype_deployername", SqlDataTypes.Text,JqGridFieldType.MultiLineText);
                    configuration.add(ctb.getCrudTable());
            }
       
    }
    
    


