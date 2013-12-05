/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.DAL.Helpers;


import WrenchDb.Core.Annotations.MappedProperty;
import WrenchDb.Core.Helpers.PropertiesHelper;
import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbSettings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author Daniele Fontani
 */
public class WdbApplicationSettings extends Properties {
    private static  WdbApplicationSettings _current=null;
  
    
    public static WdbApplicationSettings Init(Properties props)
    {
            WdbApplicationSettings settings= new WdbApplicationSettings();
           PropertiesHelper.MergeProperties(settings, props);
           
            //Merge with db values
            if(!WdbStringHelper.isBlank(settings.getProperty("application_id")))
            {
                Session sess= HContext.Current().getSessionFactory()
                        .openSession();
                
                    List<WdbSettings>  _items=  sess.createCriteria(WdbSettings.class)
                        .add(Restrictions.eq("wdbApplication.applicationId",
                            Long.parseLong(settings.getProperty("application_id") )))
                        .list();
                    
                    for(WdbSettings item:_items)
                    {
                        
                       settings.setProperty(item.getSettingKey(), item.getSettingValue());
                    }
                sess.close();
            }
            
            
            Ensurekeys(settings);
            
            return settings;
    }
    
    /**
     * NEED INIT
     * @return 
     */
    public static WdbApplicationSettings Current()
    {
       
        return _current;
    }

    private static void Ensurekeys(WdbApplicationSettings settings) {
       
         Session sess=null;
         Transaction trans=null;
        try
        {
         
         Long applicationId=Long.parseLong(settings.getProperty("application_id") );
         List<WdbSettings> _items;
          sess= HContext.Current().getSessionFactory()
                        .openSession();
         
         WdbApplication currentApp= (WdbApplication)sess.get(WdbApplication.class, applicationId);
         
          trans=sess.beginTransaction();
         String propname="";
        for(Method field : WdbApplicationSettings.class.getMethods()){
            if(!field.isAnnotationPresent(MappedProperty.class)) continue;
            propname= field.getAnnotation(MappedProperty.class).Name();
            if("".equals( propname))
            {
                propname=field.getName().replaceFirst("get", "");
            }
            if(field.isAnnotationPresent(MappedProperty.class))
            {
                 _items=  sess.createCriteria(WdbSettings.class)
                        .add(
                         Restrictions.and(
                         Restrictions.eq("wdbApplication.applicationId",
                            applicationId),
                         Restrictions.eq("settingKey",
                           propname ))
                         )
                        .list();
                 if(_items==null || _items.size()==0)
                 {
                     WdbSettings s= new WdbSettings();
                     s.setSettingKey(propname);
                     s.setSettingValue("");
                     s.setSettingCategory("");
                     s.setWdbApplication(currentApp);
                     sess.saveOrUpdate(s);
                 }
            }
        }
        trans.commit();
      
        }
        catch(Exception er)
        {
            if(trans!=null)
            trans.rollback();
            Logger.getLogger(WdbApplicationSettings.class.getName()).log(Level.SEVERE,"",er);
        }
        finally
        {
            if(sess!=null && sess.isOpen())
            {
                    sess.flush();
                    sess.close();
            }
        }
    }

/**** SOME System key mapped
 * 
 */
   @MappedProperty(Name="application_id")
   public long getApplicationId()
   {
      return  Long.parseLong(this.getProperty("application_id"));
   }
   
   @MappedProperty()
   public String getWebServerName()
   {
      return this.getProperty("WebServerName");
   }
   
   @MappedProperty()
   public String getWebServerPort()
   {
      return this.getProperty("WebServerPort");
   }
   
  

}
