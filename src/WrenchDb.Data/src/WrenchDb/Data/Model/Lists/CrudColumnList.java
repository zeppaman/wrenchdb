/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
