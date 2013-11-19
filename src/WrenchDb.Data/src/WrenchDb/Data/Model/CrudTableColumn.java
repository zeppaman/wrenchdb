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
import WrenchDb.Core.Interfaces.NamedItem;

/**
 *
 * @author d.fontani
 */
public class CrudTableColumn 
implements JSONRenderizzable,NamedItem {

    public boolean Hidden=false;
    public String Header="";
    public boolean IsPrimaryKey=false;
    public Integer Width=new Integer(100);
    
    @Override
    public String RenderAsJSON() {
       JSONRenderer jre= new JSONRenderer();
       RenderAsJSON(jre);
       return jre.GetJSON();
    }

    @Override
    public void RenderAsJSON(JSONRenderer jre) {
      jre.AppendProperty("name", this.Name);
      jre.AppendProperty("index", this.Name);
      jre.AppendProperty("hidden", this.Hidden);
      jre.AppendProperty("key", this.IsPrimaryKey);
      jre.AppendProperty("label", this.Header);
      jre.AppendProperty("width", this.Width);
    }
    
    public String Name="";
    @Override
    public String getName() {
        return Name;
    }
    public void setName(String value) {
         Name=value;
    }
    
}
