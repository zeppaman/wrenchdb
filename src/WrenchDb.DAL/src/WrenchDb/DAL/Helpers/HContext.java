/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.DAL.Helpers;

import WrenchDb.Core.Helpers.PropertiesHelper;
import WrenchDb.DAL.Configuration.HListenerHandler;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.hibernate.dialect.Dialect;
import org.reflections.Reflections;

/**
 *
 * @author d.fontani
 */
public class HContext {
    
    private static HContext _hContext=null;

    
    public static HContext createContext(String dialect,String DriverClass,String connectionUrl,String username,String password)
    {
        Properties p= new Properties();
        p.put("hibernate.dialect",dialect.toString());
        p.put("hibernate.connection.driver_class",DriverClass);
        p.put("hibernate.connection.url",connectionUrl);
        p.put("hibernate.connection.username",username);
        p.put("hibernate.connection.password",password);
        return createContext(p);
    }
    public static HContext createContext(Properties props) {
        try
        {
          HContext   newcontext = new HContext();                
           PropertiesHelper.MergeProperties( newcontext.getProperties(),props);
            
            //init Hibernate using properties entry;
            
            //set properties
          AnnotationConfiguration cfg=  new AnnotationConfiguration();
          String key="";
          Enumeration<Object> propsKeys=newcontext.getProperties().keys();
           while(propsKeys.hasMoreElements())
           {
               key=(String)propsKeys.nextElement();
               if(key.startsWith("hibernate."))
               {
                   cfg.setProperty(key, newcontext.getProperties().getProperty(key));
               }
           }
           
           
      
           if(newcontext.getProperties().containsKey("wdb.entitypackages"))               
           {
            String[] packages= newcontext.getProperties().getProperty("wdb.entitypackages").split(",");
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
          newcontext._configuration=cfg;
        
          
     
//            cfg.setListener("pre-insert", new HListenerHandler());
//            cfg.setListener("pre-update",  new HListenerHandler());
            cfg.setListener("pre-delete",  new HListenerHandler());
            
//            cfg.setListener("post-insert",  new HListenerHandler());
//            cfg.setListener("post-update",  new HListenerHandler());
            cfg.setListener("post-delete",  new HListenerHandler());
            
            cfg.setListener("save-update",  new HListenerHandler());
            
          newcontext.setSessionFactory( cfg.buildSessionFactory());
          
          return newcontext;
        
         } catch (Exception ex) 
         {
                Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    
    private   SessionFactory _sessionFactory;

    private   Configuration _configuration;
    
     public static void Init(String path) throws IOException  {

            Properties props= new Properties();
            props.load(new FileInputStream(path));
            Init(props);
        }
     
    public static void Init(Properties  props)  {
        HContext   newcontext =null;
       _hContext= createContext(props); 
        
        
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
       T result=(T)Get(s,c,entityId);
       s.close();
       return result;
      
    }
    
    public <T> T  Get(Session s,Class c,long entityId)   
    {        
       
       T result= (T)s.get(c, (Serializable)entityId);
   
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
    
    
    public int  ExecuteNoResult(String sql) {
       Session s= this._sessionFactory.openSession();
        Boolean result=false;
       try
       {
          
           
         return ExecuteNoResult(s,sql);
       }
       catch(Exception ex)
       {
          
           Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally
       {
          s.close();
       }
       return -1;
    }
    public int ExecuteNoResult(Session s,String sql)
    { 
         Transaction t=null;
        try
        {
            
            SQLQuery q=  s.createSQLQuery(sql);
             t=s.beginTransaction();
            int results= q.executeUpdate();
            
            if(results>0)
            {
                t.commit();
            }
            else
            {
                t.rollback();
            }
        }
        catch(Exception ex)
        {
            if(t!=null) t.rollback();
              Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    
    
    public Object  ExecuteScalar(String sql) {
       Session s= this._sessionFactory.openSession();
        Boolean result=false;
       try
       {
          
           
         return ExecuteScalar(s,sql);
       }
       catch(Exception ex)
       {
          
           Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally
       {
          s.close();
       }
       return -1;
    }
    public Object ExecuteScalar(Session s,String sql)
    { 
         Transaction t=null;
        try
        {
            
            SQLQuery q=  s.createSQLQuery(sql);
            t=s.beginTransaction();
            Object results= q.uniqueResult();
              t.commit();
            return results;
            
          
       
        }
        catch(Exception ex)
        {
            if(t!=null) t.rollback();
              Logger.getLogger(HContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
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
