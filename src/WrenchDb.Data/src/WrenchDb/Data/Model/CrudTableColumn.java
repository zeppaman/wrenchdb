/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
      jre.AppendProperty("header", this.Header);
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
