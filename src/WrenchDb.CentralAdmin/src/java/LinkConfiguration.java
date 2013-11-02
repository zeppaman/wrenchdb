/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
import WrenchDb.Core.Configuration.ItemAppendingConfiguration;
import WrenchDb.MVC.BaseClasses.CssReference;
import WrenchDb.MVC.BaseClasses.JSReference;
import WrenchDb.MVC.BaseClasses.LinkReference;
import WrenchDb.MVC.BaseClasses.RoutingConfiguration;
import WrenchDb.MVC.BaseClasses.ScriptReference;
import WrenchDb.MVC.Configuration.RouteMap;
import java.util.ArrayList;

@ItemAppenderConfigurator(Name = "LinkSet")
public class LinkConfiguration 
    extends  ItemAppendingConfiguration<LinkReference> {

            @Override
            public void Configure(ArrayList<LinkReference> configuration)
            {
                CssReference css= new CssReference();
                css.href="/WrenchDb.CentralAdmin/wro/WdbCa.css";
                configuration.add(css);
                
            }
       
    }
    
    

