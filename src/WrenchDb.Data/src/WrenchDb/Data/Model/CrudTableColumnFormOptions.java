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
public class CrudTableColumnFormOptions
implements JSONRenderizzable {
    
public String elmprefix;
public String elmsuffix	;
public String label;
public Integer rowpos;
public Integer colpos;
        
         
@Override
    public String RenderAsJSON() {
       JSONRenderer jre= new JSONRenderer();
       RenderAsJSON(jre);
       return jre.GetJSON();
    }

    @Override
    public void RenderAsJSON(JSONRenderer jre) {
        jre.AppendPropertyIfValued("elmprefix", this.elmprefix);
         jre.AppendPropertyIfValued("elmsuffix", this.elmsuffix);
         jre.AppendPropertyIfValued("label", this.label);
         jre.AppendPropertyIfValued("rowpos", this.rowpos);
         jre.AppendPropertyIfValued("colpos", this.colpos);       
    }
}
