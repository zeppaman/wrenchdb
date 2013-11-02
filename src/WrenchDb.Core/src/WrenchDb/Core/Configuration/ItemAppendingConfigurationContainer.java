/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Configuration;

import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
import WrenchDb.Core.Helpers.ReflectionHelper;
import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.Core.Interfaces.NamedItem;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author d.fontani
 */
public abstract class ItemAppendingConfigurationContainer<ObjectType> {
    
    private boolean _inited=false;
    private  ArrayList<ObjectType> items= new ArrayList<ObjectType>();
   /**
     * @return the items
     */
    public ArrayList<ObjectType> getItems() {
        if(!_inited)
        {
            LoadItems();
        }
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<ObjectType> items) {
        this.items = items;
    }
    
    
    public ObjectType GetByName(String Name)
    {
        if(WdbStringHelper.isBlank(Name)) return  null;
        for(ObjectType o : this.items)
        {
            if(o instanceof NamedItem)
            {
                if(Name.equalsIgnoreCase(((NamedItem)o).getName()))
                {
                    return o;
                }
            }
        }
        return null;
    }
    
      public abstract String getName();
    
    public abstract Class GetType();
    
    public void LoadItems()
    {
       
        setItems(new ArrayList<ObjectType>());
      
      
       
        
        //Get all ItemAppendigConfiguration Classes
        Set<Class<? extends ItemAppendingConfiguration>> configs 
                = ReflectionHelper.getSubTypesOf(ItemAppendingConfiguration.class);
        ItemAppenderConfigurator ann=null;
        
        for (Class<? extends ItemAppendingConfiguration> item : configs)
        {
            ann=item.getAnnotation(ItemAppenderConfigurator.class);
           //Same configurator type &&  same name
           if(ann!=null && ann.Name().equals(getName()) )
           {
               Constructor<?> ctor;
               try {
                   ctor = item.getConstructor(null);

                Object object = ctor.newInstance(); 
                ItemAppendingConfiguration refl=(ItemAppendingConfiguration)object;
                
               
                    Class[] cArg = new Class[1];
                    cArg[0]=getItems().getClass();

                    item.getMethod("Configure",cArg)
                        .invoke(object, getItems());
                

                } catch (Exception ex) {
                   Logger.getLogger(ItemAppendingConfiguration.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               
           }           
               
           
        }
    }
    
}
