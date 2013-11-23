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
package WrenchDb.Data.Model;

import WrenchDb.Core.Helpers.JSONRenderer;
import WrenchDb.Core.Interfaces.JSONRenderizzable;

/**
 *
 * @author Daniele Fontani
 */
public class CrudTableEditRules  
implements JSONRenderizzable{
    
public boolean edithidden;
public boolean required;
public boolean number	;
boolean integer;
public Integer minValue;
public Integer maxValue;
public boolean email;
public boolean url;
public boolean date;
public boolean time;
public boolean custom;

/**
 * The function (input value,colModel) should return array with the following parameters: first parameter - true or false. The value of true mean that the checking is successful false otherwise; the second parameter have sense only if the first value is false and represent the error message which will be displayed to the user. Typically this can look like this [false,”Please enter valid value”]
 */
public String custom_func;
     
@Override
    public String RenderAsJSON() {
       JSONRenderer jre= new JSONRenderer();
       RenderAsJSON(jre);
       return jre.GetJSON();
    }

    @Override
    public void RenderAsJSON(JSONRenderer jre) {
         jre.AppendPropertyIfValued("edithidden", this.edithidden);
         jre.AppendPropertyIfValued("required", this.required);
         jre.AppendPropertyIfValued("number", this.number);
         jre.AppendPropertyIfValued("integer", this.integer);
         jre.AppendPropertyIfValued("minValue", this.minValue);
         jre.AppendPropertyIfValued("maxValue", this.maxValue);
         jre.AppendPropertyIfValued("email", this.email);
         jre.AppendPropertyIfValued("url", this.url);         
         jre.AppendPropertyIfValued("date", this.date);         
         jre.AppendPropertyIfValued("time", this.time);
         jre.AppendPropertyIfValued("custom", this.custom);
         jre.AppendPropertyIfValued("custom_func", this.custom_func);
    }
}
