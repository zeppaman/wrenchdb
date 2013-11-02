package WrenchDb.Resources.Configuration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
import WrenchDb.Core.Configuration.ItemAppendingConfiguration;
import WrenchDb.MVC.BaseClasses.JSReference;
import WrenchDb.MVC.BaseClasses.RoutingConfiguration;
import WrenchDb.MVC.BaseClasses.ScriptReference;
import WrenchDb.MVC.Configuration.RouteMap;
import java.util.ArrayList;

@ItemAppenderConfigurator(Name = "ScriptSet")
public class BootstrapConfiguration 
    extends  ItemAppendingConfiguration<ScriptReference> {

            @Override
            public void Configure(ArrayList<ScriptReference> configuration)
            {
                //TODO: REMOVE
               
                
            }
       
    }
    
    

