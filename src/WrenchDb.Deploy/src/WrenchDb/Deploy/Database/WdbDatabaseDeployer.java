/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Database;

import WrenchDb.Core.Helpers.ReflectionHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbChangescript;
import WrenchDb.DAL.Entities.WdbDatabasetype;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Entities.WdbServertype;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Deploy.Server.WdbServerDeployer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Daniele Fontani
 */
public abstract class  WdbDatabaseDeployer {
      
    StringBuilder builder= new StringBuilder();
    public boolean logOnly=false;

    private WdbApplication application =null;
    
    
    public  WdbDatabaseDeployer()
    {
        
    }
    
    public static WdbDatabaseDeployer  getDatabaseDeployer(long applicationId) 
    {
        Session s=null;
        try
        {
            s=HContext.Current().getSessionFactory().openSession();
            WdbApplication application =(WdbApplication)s.get(WdbApplication.class, applicationId);
            if(application==null)
            {
                throw new Exception("application NULL : unable to find application for deploy (using application id="+applicationId);
            }
            WdbDatabasetype type=application.getWdbDatabasetype();
            if(type==null)
            {
                throw new Exception("application.getWdbDatabasetype NULL : unable to find database deployer");
            }
            String classname=type.getDatabasetypeDeployer().trim();
            
            Logger.getLogger(WdbServerDeployer.class.getName())
                      .log(Level.SEVERE, "USE DATABASE DEPLOYER classname:"+classname);
            
            WdbDatabaseDeployer app= ReflectionHelper.getNewInstance(classname);
            app.application=application;         
            return app;
            
        }
        catch(Exception err)
        {
            Logger.getLogger(WdbServerDeployer.class.getName())
                      .log(Level.SEVERE, "",err);
        }
        finally
        {
            if(s!=null && s.isOpen())
                s.close();
        }
        return null;

    }

    public void clearLog()
    {
        builder= new StringBuilder();
    }
    public String getScript()
    {
        return builder.toString();
    }
    public boolean execute(String sql)
    {
        if(this.logOnly==false)
        {
            try
            {
                HContext.Current().ExecuteNoResult(sql);
                
            }
            catch(Exception err)
            {
                Logger.getLogger(WdbDatabaseDeployer.class.getName()).log(Level.SEVERE,"execute",err);
                return false;
            }
        }
        return true;
    }
    
    public boolean executeLog()
    {
       try
            {
                HContext.Current().ExecuteNoResult(this.builder.toString());
                
            }
            catch(Exception err)
            {
                Logger.getLogger(WdbDatabaseDeployer.class.getName()).log(Level.SEVERE,"execute",err);
                return false;
            }
       return true;
    }
    public void log(String sql)
    {
        sql= sql.trim();
        this.builder.append("\n");
        this.builder.append(sql);
        if(!sql.endsWith(";"))
        {
            this.builder.append(";");
        }
        this.builder.append("\n");
    }
    public abstract void createTable(String tablename);
    public abstract void addColumn(String tablename,String columnName,String dataType,String defaultValue);
    public abstract void addPk(String tablename,String columnName);
    public abstract void addFk(String tablename,String columnName,String ftableName,String ftableColumn);
    
    public void DeployPendingChanges()
    {
        try
        {
        Session s=HContext.Current().getSessionFactory().openSession();
        List<WdbChangescript> changes=     s
                .createCriteria(WdbChangescript.class)
                .add(
                Restrictions
                    .and(
                        Restrictions .eq("wdbApplication.applicationId",this.application.getApplicationId()),
                        Restrictions .eq("isDeployed",false)
                        )
                )
                .addOrder(Order.asc("changescriptId")).list();
        StringBuilder sb=new StringBuilder();
        
        for(WdbChangescript c : changes)
        {
            sb.append(c.getChangescriptSql());
        }
        s.close();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Deplong database update:"
                + application.getDatabaseJdbc() +" "+ application.getDatabaseUsername(), 
               application.getServerPassword()+"\n\n"+sb.toString());
       HContext externalDbCOntext= HContext
               .createContext("org.hibernate.dialect.PostgreSQLDialect", "org.postgresql.Driver",
               application.getDatabaseJdbc(), application.getDatabaseUsername(), 
               application.getServerPassword());
       
       externalDbCOntext
               .ExecuteNoResult(sb.toString());
        }
        catch(Exception err)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"ERROR DEPLOYNG DATABASE CHANGES",err);
        }
    }
}
