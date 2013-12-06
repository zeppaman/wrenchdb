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
import WrenchDb.Data.Enums.JqGridFieldType;
import static WrenchDb.Data.Enums.JqGridFieldType.Url;
import WrenchDb.Data.Enums.SqlDataTypes;
import WrenchDb.Data.Enums.jqGridEditType;

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
     /*Edit settings*/
       public boolean IsEditable=false;
       public JqGridFieldType FieldType= JqGridFieldType.SingleLineText;
       public CrudTableEditOption EditOptions= new CrudTableEditOption();
       public CrudTableEditRules EditRules= new CrudTableEditRules();
       public CrudTableColumnFormOptions FormOptions= new CrudTableColumnFormOptions();
       public SqlDataTypes DbType= SqlDataTypes.Text;
    public String LookupTable= null;
    public String LookupId= null;
    public String LookupDesc= null;
    public String ColumnName=null;
    public String Expression=null;
    
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
          /*Edit settings*/
       jre.AppendProperty("editable",this.IsEditable);
       jre.AppendProperty("edittype",GetEditTypeFromFieldType(this.FieldType));
       if(this.IsEditable)
       {
            jre.StartObjectProperty("editoptions");
                    this.EditOptions.RenderAsJSON(jre);
            jre.EndObjectProperty();
            
            
            jre.StartObjectProperty("editrules");
                    this.EditRules.RenderAsJSON(jre);
            jre.EndObjectProperty();
            
            jre.StartObjectProperty("formoptions");
                    this.FormOptions.RenderAsJSON(jre);
            jre.EndObjectProperty();
       }
    }
    
    public String Name="";
    @Override
    public String getName() {
        return Name;
    }
    public void setName(String value) {
         Name=value;
    }

    private jqGridEditType GetEditTypeFromFieldType(JqGridFieldType FieldType) {
       switch(FieldType)
       {
           case CheckBox: return jqGridEditType.checkbox;
           case DatePicker: return jqGridEditType.text;
           case File:return jqGridEditType.file;
           case HtmlText: return jqGridEditType.textarea;
           case Image: return jqGridEditType.image;
           case Integer: return jqGridEditType.text;
           case Lookup: return jqGridEditType.select;
           case MultiLineText: return jqGridEditType.textarea;
           case Number:return jqGridEditType.text;
           case Password:return jqGridEditType.password;
           case RadioChoice:return jqGridEditType.custom;
           case SelectChoice: return jqGridEditType.select;
           case SingleLineText:return jqGridEditType.text;
           case Url:return jqGridEditType.text;
           case Email:return jqGridEditType.text;
           default:
               return jqGridEditType.custom;
       }
    }
    
}
