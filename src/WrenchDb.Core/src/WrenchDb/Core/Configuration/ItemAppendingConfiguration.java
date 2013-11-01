/*
 * To change this template, choose Tools | Templates
 * and op
 * en the template in the editor.
 */
package WrenchDb.Core.Configuration;

import WrenchDb.Core.Annotations.ItemAppenderConfigurator;
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
public abstract class ItemAppendingConfiguration<ObjectType>   {
    

    public abstract void Configure(ArrayList<ObjectType> configuration);
    
    
}
