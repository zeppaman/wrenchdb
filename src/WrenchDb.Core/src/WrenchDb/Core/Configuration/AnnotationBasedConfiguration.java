/*
 * To change this template, choose Tools | Templates
 * and op
 * en the template in the editor.
 */
package WrenchDb.Core.Configuration;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
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
public abstract class AnnotationBasedConfiguration<ObjectType>   {
    

    private  ArrayList<ObjectType> items= new ArrayList<ObjectType>();
   
    
    public abstract  Class<? extends Annotation> GetAnnotationMarker();
    
    
    public void LoadItems()
    {
       
        setItems(new ArrayList<ObjectType>());
      
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
       
      
        Reflections reflections = new Reflections( new ConfigurationBuilder()
        .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0]))));
       
        Set<Class<?>> configs = reflections.getTypesAnnotatedWith( GetAnnotationMarker());
        for (Class<?> item : configs)
        {
       
                Constructor<?> ctor;
               try {
                   ctor = item.getConstructor(null);

                Object object = ctor.newInstance(); 
                
                items.add((ObjectType)object);
                

                } catch (Exception ex) {
                   Logger.getLogger(AnnotationBasedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
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
