/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import WrenchDb.Core.Configuration.ItemAppendingConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
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
public class ReflectionHelper {

    private static  Reflections _reflections ;
    
    
    public static Reflections GetCurrentContext()
    {
        if(_reflections==null)
        {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
       
      
        _reflections= new Reflections( new ConfigurationBuilder()
        .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0]))));
        }
        return _reflections;
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> aClass) {
        return GetCurrentContext().getSubTypesOf(aClass);
    }
    
    
    public static Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> Annotation) {
       
       return GetCurrentContext().getTypesAnnotatedWith(Annotation);
    }
    
    
    public static   <T> T  getNewInstance(String classname)
    {
        //TODO: Prendere in input anche lista di oggetti, trovare l'overload corretto del costruttore e quindi utilizzarlo passando
        //i parametri corretti
        try
        {
            Class cItem=Class.forName(classname);
            Constructor c= cItem.getConstructor();
            return (T)c.newInstance();
        }
        catch(Exception err)
        {
              Logger.getLogger(ReflectionHelper.class.getName())
                      .log(Level.SEVERE, "",err);
        }
        return null;
    }
}
