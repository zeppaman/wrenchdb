/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Context;

import WrenchDb.MVC.BaseClasses.ControllerBase;
import WrenchDb.MVC.BaseClasses.RuleMatchingResult;
import WrenchDb.MVC.Configuration.ControllerSet;
import WrenchDb.MVC.Configuration.RouteMapSet;



/**
 *
 * @author d.fontani
 */
public class RoutingContext {

    /**
     * @return the _current
     */
    public static RoutingContext Current() {
        if(_current==null)
        {
            _current= new RoutingContext();
            _current.Init();
        }
        return _current;
    }
    
    private  RouteMapSet _routingMap= null;
    private  ControllerSet _controllerSet= null;
    
    private static RoutingContext _current= null;

    private void Init() {
         _routingMap= new RouteMapSet();
          _routingMap.LoadItems();
          _controllerSet = new ControllerSet();
          _controllerSet.LoadItems();
         
    }

    /**
     * @return the _routingMap
     */
    public RouteMapSet getRoutingMap() {
        return _routingMap;
    }
    
    public RuleMatchingResult GetRouteResult(String url)
    {
        return _routingMap.GetRouteResult(url);
    }

    /**
     * @return the _controllerSet
     */
    public ControllerSet getControllerSet() {
        return _controllerSet;
    }

  public  ControllerBase getController(String ControllerName) {
       return this._controllerSet.GetControllerByName(ControllerName);
    }
    
    
}
