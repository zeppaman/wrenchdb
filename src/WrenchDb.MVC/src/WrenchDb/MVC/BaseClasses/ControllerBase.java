/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.Annotations.*;



/**
 *
 * @author d.fontani
 */
public abstract class ControllerBase {
    
    
     
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
   
    
}
