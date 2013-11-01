/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
import WrenchDb.Core.Configuration.ItemAppendingConfiguration;
import WrenchDb.MVC.BaseClasses.RoutingConfiguration;
import WrenchDb.MVC.Configuration.RouteMap;
import java.util.ArrayList;

@ItemAppenderConfigurator(Name = "RouteMapSet")
public class DefaultRoutingConfiguration 
    extends  ItemAppendingConfiguration<RouteMap> {

            @Override
            public void Configure(ArrayList<RouteMap> configuration)
            {
                RouteMap map=  new RouteMap();
                map.setName("DefaultController");
                map.setUrl("/WrenchDb.CentralAdmin/{controller}/{action}/{id}");
                map.setDefaultActionName("Index");
                map.setDefaultControllerName("DefaultController");

                configuration.add(map);
                
                
                map=  new RouteMap();
                map.setName("DefaultController");
                map.setUrl("/WrenchDb.CentralAdmin");
                map.setDefaultActionName("Index");
                map.setDefaultControllerName("DefaultController");

                configuration.add(map);
                
            }
       
    }
    
    

