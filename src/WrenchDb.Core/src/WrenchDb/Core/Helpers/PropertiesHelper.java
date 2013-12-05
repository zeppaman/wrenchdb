/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author Daniele Fontani
 */
public class PropertiesHelper {
    
    /**
     * Add propsToAdd into props. value will be overwritten if key is yet into props.
     * @param props
     * @param propsToAdd 
     */
    public static void  MergeProperties(Properties props,Properties propsToAdd)
    {
         Object s;
            Enumeration<Object> list =propsToAdd.keys();
            while(list.hasMoreElements())
            {
                
                s=list.nextElement();
                if(s!=null)
                {
                    props.setProperty(s.toString(), propsToAdd.getProperty(s.toString()));
                }
            }
    }
}
