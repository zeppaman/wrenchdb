/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.Annotations.*;
import WrenchDb.MVC.Configuration.RouteMap;

/**
 *
 * @author d.fontani
 */
public abstract class RoutingConfiguration extends ConfiguratorBase<RouteMap> {
    @Override 
    public Class getUnderlingType()
    {
        return RoutingConfiguration.class;
    }
    
   
    
}
