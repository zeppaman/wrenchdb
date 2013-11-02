/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Lists;

import WrenchDb.Core.Interfaces.NamedItem;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author d.fontani
 */
public  abstract class NameIndexedList<T extends NamedItem> 
    extends ArrayList<T>
{
    public T getByName(String name)
    {
         Iterator<T> ite= this.iterator();
         NamedItem itm;
         while(ite.hasNext()) 
         {             
            itm = (NamedItem)ite.next();
            if(name.equalsIgnoreCase(itm.getName()))
            return (T)itm;
         }
         
         return null;
    }
}
