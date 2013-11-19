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
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.Annotations.*;
import javax.servlet.http.HttpServletRequest;



/**
 *
 * @author d.fontani
 */
public abstract class ControllerBase {
    
    public ControllerBase()
    {}
      public ControllerBase(HttpServletRequest _request)
    {
        this._request=_request;
    }
    /**
     * 
     * @return Name of controller. Used in path routing
     */
    public  String getName()
    {
        Controller anno = this.getClass().getAnnotation(Controller.class);
       
            if (anno != null) {
                try {
                    return anno.ControllerName();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return null;
    }
    
    private HttpServletRequest _request=null;

    /**
     * @return the _request
     */
    public HttpServletRequest getRequest() {
        return _request;
    }
   
    
}
