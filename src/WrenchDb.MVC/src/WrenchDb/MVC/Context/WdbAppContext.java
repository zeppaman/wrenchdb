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
package WrenchDb.MVC.Context;

import WrenchDb.MVC.BaseClasses.ControllerBase;
import WrenchDb.MVC.BaseClasses.RuleMatchingResult;
import WrenchDb.MVC.Configuration.ControllerSet;
import WrenchDb.MVC.Configuration.LinkSet;
import WrenchDb.MVC.Configuration.RouteMapSet;
import WrenchDb.MVC.Configuration.ScriptSet;



/**
 *
 * @author d.fontani
 */
public class WdbAppContext {

    /**
     * @return the _current
     */
    public static WdbAppContext Current() {
        if(_current==null)
        {
            _current= new WdbAppContext();
            _current.Init();
        }
        return _current;
    }
    
    private  RouteMapSet _routingMap= null;
    private  ControllerSet _controllerSet= null;    
    private  LinkSet _linkSet= null;
    private  ScriptSet _scriptSet= null;
    
    private static WdbAppContext _current= null;

    private void Init() {
          _routingMap= new RouteMapSet();
          _routingMap.LoadItems();
          
          _controllerSet = new ControllerSet();
          _controllerSet.LoadItems();
          
          _linkSet= new LinkSet();
          _linkSet.LoadItems();
          
          _scriptSet= new ScriptSet();
          _scriptSet.LoadItems();
         
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

    /**
     * @return the _linkSet
     */
    public LinkSet getLinkSet() {
        return _linkSet;
    }

    /**
     * @return the _scriptSet
     */
    public ScriptSet getScriptSet() {
        return _scriptSet;
    }
    
    
}
