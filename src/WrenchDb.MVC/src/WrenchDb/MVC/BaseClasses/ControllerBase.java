/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
