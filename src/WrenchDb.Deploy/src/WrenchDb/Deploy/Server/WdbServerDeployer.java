/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Server;

import WrenchDb.Core.Helpers.ReflectionHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Entities.WdbServertype;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import WrenchDb.Deploy.Model.IFileDeployItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author Daniele Fontani
 */
public abstract class WdbServerDeployer {
    
    private WdbApplication application =null;
    
    
    public  WdbServerDeployer()
    {
    }
    
    public static WdbServerDeployer  getWebServerDeployer(long applicationId) 
    {
        Session s=null;
        try
        {
             s=HContext.Current().getSessionFactory().openSession();
            WdbApplication application =(WdbApplication)s.get(WdbApplication.class, applicationId);
            WdbServertype type=application.getWdbServertype();
            String classname=type.getServertypeDeployername();
            WdbServerDeployer app= ReflectionHelper.getNewInstance(classname);
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
    
    public boolean Start() throws Exception
    {
        throw new Exception(this.getClass().getName()+" Start not implemented");
    }
    
    
    public boolean Stop() throws Exception
    {
        throw new Exception(this.getClass().getName()+" Stop not implemented");
    }
    
    public boolean Install() throws Exception
    {
          throw new Exception(this.getClass().getName()+" Install not implemented");
    }
    
    public boolean Deploy(IFileDeployItem deployItem) throws Exception
    {
        throw new Exception(this.getClass().getName()+" Deploy not implemented");
    }

    public boolean UnDeploy(IFileDeployItem deployItem) throws Exception
    {
        throw new Exception(this.getClass().getName()+" Deploy not implemented");
    }
    
    public boolean Uninstall() throws Exception
    {
        throw new Exception(this.getClass().getName()+" Uninstall not implemented");
    }

    /**
     * @return the application
     */
    public WdbApplication getApplication() {
        return application;
    }
}
