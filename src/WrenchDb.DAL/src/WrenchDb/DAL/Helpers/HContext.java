/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.DAL.Helpers;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.reflections.Reflections;

/**
 *
 * @author d.fontani
 */
public class HContext {
    
    private static HContext _hContext=null;
    
    private   SessionFactory _sessionFactory;

    private   Configuration _configuration;
    
    
    public static void Init(String path)  {
        try
        {
            _hContext = new HContext();                
            _hContext.getProperties().load(new FileInputStream(path));
            
            //init Hibernate using properties entry;
            
            //set properties
          AnnotationConfiguration cfg=  new AnnotationConfiguration();
          String key="";
          Enumeration<Object> props=_hContext.getProperties().keys();
           while(props.hasMoreElements())
           {
               key=(String)props.nextElement();
               if(key.startsWith("hibernate."))
               {
                   cfg.setProperty(key, _hContext.getProperties().getProperty(key));
               }
           }
           //add entities
           
           cfg.configure();
           if(_hContext.getProperties().containsKey("wdb.entitypackages"))               
           {
            String[] packages= _hContext.getProperties().getProperty("wdb.entitypackages").split(",");
            Reflections reflections;
            for(String pk : packages)
            {
                if(pk!=null && !pk.equals(""))
                {
                       reflections = new Reflections(pk);     
                        Set<Class<?>> entityList = reflections.getTypesAnnotatedWith(Entity.class );
                        for (Class<?> entity : entityList)
                        {
                              Logger.getLogger(HContext.class.getName()).log(Level.INFO,entity.getName());
                            if(cfg.getClassMapping(entity.getName())==null)
                            cfg.addAnnotatedClass(entity);
                        }
                }
            }
            //
           }
          _hContext._configuration=cfg;
          _hContext.setSessionFactory( cfg.buildSessionFactory());
        
         } catch (Exception ex) 
         {
                Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      public static HContext Current()
    {
        if(_hContext==null)
        {
            try {
               Init("wdb_db.properties");
                
                
            } catch (Exception ex) {
                Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _hContext;
    }
      
      
    private HContext()
    {}
    
    public <T> T  Get(Class c,long entityId)   
    {        
       Session s=this._sessionFactory.openSession();
       T result= (T)s.get(c, (Serializable)entityId);
       s.close();
       return result;
      
    }
    public Boolean SaveOrUpdate(Object entity)
    {
       Session s= this._sessionFactory.openSession();
       Transaction t= s.beginTransaction();
        Boolean result=false;
       try
       {
           
          SaveOrUpdate(s,entity); 
          s.flush();
          t.commit();
          return true;
       }
       catch(Exception ex)
       {
           t.rollback();
           Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally
       {
          s.close();
       }
       return result;
    }
    
    public List ExecuteSelectQuery(String sqlSelect)
    {
        Session s= this._sessionFactory.openSession();
        Boolean result=false;
       try
       {
           
         return ExecuteSelectQuery(s,sqlSelect);
         
        
       }
       catch(Exception ex)
       {
          
           Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally
       {
          s.close();
       }
       return null;
    }
    public List ExecuteSelectQuery(Session s,String sqlSelect)
    { 
        try
        {
            SQLQuery q=  s.createSQLQuery(sqlSelect);
            return q.list();
        }
        catch(Exception ex)
        {
              Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Boolean SaveOrUpdate(Session s,Object entity)
    {
        try
        {
             s.saveOrUpdate(entity);
             return true;
        }
        catch(Exception ex)
        {
              Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    private Properties _properties=new Properties();

    /**
     * @return the _properties
     */
    public Properties getProperties() {
        return _properties;
    }

    /**
     * @return the _sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return _sessionFactory;
    }

    /**
     * @param sessionFactory the _sessionFactory to set
     */
    private void setSessionFactory(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    /**
     * @return the _configuration
     */
    public Configuration getConfiguration() {
        return _configuration;
    }

    /**
     * @param configuration the _configuration to set
     */
    private void setConfiguration(Configuration configuration) {
        this._configuration = configuration;
    }
    
    
}
