/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import WrenchDb.MVC.Annotations.RouteMapConfiguration;
import WrenchDb.MVC.BaseClasses.RoutingConfiguration;
import WrenchDb.MVC.Configuration.RouteMap;
import java.util.ArrayList;

@RouteMapConfiguration
public class DefaultRoutingConfiguration 
    extends  RoutingConfiguration {

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
    
    

