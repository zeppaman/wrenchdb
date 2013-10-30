/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.Annotations.*;
import java.util.HashMap;

/**
 *
 * @author d.fontani
 */
@Model(ModelName = "Model Base")
public class ModelBase {
    
    
     public  String getModelName()
    {
        Model anno = this.getClass().getAnnotation(Model.class);
       
            if (anno != null) {
                try {
                    return anno.ModelName();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return null;
    }
     
     public HashMap Properties= new HashMap();

    /**
     * @return the Properties
     */
    public HashMap getProperties() {
        return Properties;
    }

    
     public String  get(String key) {
        return (String)Properties.get(key);
    }
   
}
