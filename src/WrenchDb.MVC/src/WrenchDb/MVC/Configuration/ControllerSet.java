/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Configuration;


import WrenchDb.Core.Configuration.AnnotationBasedConfiguration;
import WrenchDb.MVC.Annotations.Controller;
import WrenchDb.MVC.BaseClasses.*;
import java.lang.annotation.Annotation;


public class ControllerSet extends AnnotationBasedConfiguration<ControllerBase> 
{
   
     public  Class GetType()
     {
         return ControllerBase.class;
     }
     
    public ControllerBase GetControllerByName(String name)
    {
        for(ControllerBase  r: this.getItems() )
        {
            if(r.getName().equalsIgnoreCase(name))
            {
              return r;
            }
        }
        return null;
    }

    @Override
    public Class<? extends Annotation> GetAnnotationMarker() {
      return Controller.class;
    }

    
    
}
