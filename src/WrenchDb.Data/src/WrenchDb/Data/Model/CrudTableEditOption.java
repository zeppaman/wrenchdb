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
public class CrudTableEditOption  
implements JSONRenderizzable{
   public String value;

   public String dataUrl;
   public String buildSelect;
   public String dataInit;
   public String defaultValue;
   public boolean NullIfEmpty=false;	
   public String custom_element	;
   public String custom_value	;
   public String other_options;

    @Override
    public String RenderAsJSON() {
       JSONRenderer jre= new JSONRenderer();
       RenderAsJSON(jre);
       return jre.GetJSON();
    }

    @Override
    public void RenderAsJSON(JSONRenderer jre) {
         jre.AppendPropertyIfValued("value", this.value);
         jre.AppendPropertyIfValued("dataUrl", this.dataUrl);
         jre.AppendPropertyIfValued("buildSelect", this.buildSelect);
         jre.AppendPropertyIfValued("dataInit", this.dataInit);
         jre.AppendPropertyIfValued("defaultValue", this.defaultValue);
         jre.AppendPropertyIfValued("NullIfEmpty", this.NullIfEmpty);
         jre.AppendPropertyIfValued("custom_element", this.custom_element);
         jre.AppendPropertyIfValued("custom_value", this.custom_value);
         jre.AppendPropertyIfValued("other_options", this.other_options);
    }
}

