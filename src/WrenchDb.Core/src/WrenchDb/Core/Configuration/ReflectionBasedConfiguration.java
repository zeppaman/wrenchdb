/*
 * To change this template, choose Tools | Templates
 * and op
 * en the template in the editor.
 */
package WrenchDb.Core.Configuration;

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
public abstract class ReflectionBasedConfiguration<ObjectType>   {
    

    private  ArrayList<ObjectType> items= new ArrayList<ObjectType>();
   
    
    public abstract Class GetType();
    
    public void LoadItems()
    {
       
        setItems(new ArrayList<ObjectType>());
      
      
        

       
      
        Reflections reflections = new Reflections("");
        
        Set<Class<? extends ReflectionBasedConfiguration>> configs 
                = reflections.getSubTypesOf(ReflectionBasedConfiguration.class);
        for (Class<? extends ReflectionBasedConfiguration> item : configs)
        {
       
                Constructor<?> ctor;
               try {
                   ctor = item.getConstructor(null);

                Object object = ctor.newInstance(); 
                ReflectionBasedConfiguration refl=(ReflectionBasedConfiguration)object;
                
                if(object!=null &&  (refl.GetType().isAssignableFrom(  GetType())))
                {
                    Class[] cArg = new Class[1];
                    cArg[0]=getItems().getClass();

                    item.getMethod("Configure",cArg)
                        .invoke(object, getItems());
                }
                else
                {
                    items.add((ObjectType)object);
                }

                } catch (Exception ex) {
                   Logger.getLogger(ReflectionBasedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
               }
           
        }
    }

    /**
     * @return the items
     */
    public ArrayList<ObjectType> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<ObjectType> items) {
        this.items = items;
    }
    
    
}
