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
package WrenchDb.Data.Model.Lists;

import WrenchDb.Core.Helpers.JSONRenderer;
import WrenchDb.Core.Interfaces.JSONRenderizzable;
import WrenchDb.Core.Interfaces.NamedItem;
import WrenchDb.Core.Lists.NameIndexedList;
import WrenchDb.Data.Model.CrudTableColumn;
import java.util.Iterator;

/**
 *
 * @author d.fontani
 */
public class CrudColumnList 
    extends NameIndexedList<CrudTableColumn>
   implements JSONRenderizzable{
    
    public void Test()
    {
        
    }

    @Override
    public String RenderAsJSON() {
       JSONRenderer jre= new JSONRenderer();
       RenderAsJSON(jre);
       return jre.GetJSON(); 
    }

    @Override
    public void RenderAsJSON(JSONRenderer sb) {
        sb.StartArrayProperty(null);
        Iterator<CrudTableColumn> ite= this.iterator();
        CrudTableColumn itm;
         while(ite.hasNext()) 
         {             
            itm = (CrudTableColumn)ite.next();
            if(itm!=null)
            {
                
            }
         }
         sb.EndArrayProperty();
    }
}
