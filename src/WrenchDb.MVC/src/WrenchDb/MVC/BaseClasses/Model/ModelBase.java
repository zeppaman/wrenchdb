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
package WrenchDb.MVC.BaseClasses.Model;

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
