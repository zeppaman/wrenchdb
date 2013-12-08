package WrenchDb.Web.Defaults.Resources;

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
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import WrenchDb.MVC.BaseClasses.CssReference;
import WrenchDb.MVC.BaseClasses.JSReference;
import WrenchDb.MVC.BaseClasses.LinkReference;
import WrenchDb.MVC.BaseClasses.RoutingConfiguration;
import WrenchDb.MVC.BaseClasses.ScriptReference;
import WrenchDb.MVC.Configuration.RouteMap;
import WrenchDb.MVC.Context.WdbAppContext;
import java.util.ArrayList;

@ItemAppenderConfigurator(Name = "LinkSet")
public class LinkConfiguration 
    extends  ItemAppendingConfiguration<LinkReference> {

            @Override
            public void Configure(ArrayList<LinkReference> configuration)
            {
                CssReference css= new CssReference();
                css.href= WdbApplicationSettings.Current().getProperty("serverletContextPath") +"/wro/WdbCa.css";
                configuration.add(css);
              
            }
       
    }
    
    

