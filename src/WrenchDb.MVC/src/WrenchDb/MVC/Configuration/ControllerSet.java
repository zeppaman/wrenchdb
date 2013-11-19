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
