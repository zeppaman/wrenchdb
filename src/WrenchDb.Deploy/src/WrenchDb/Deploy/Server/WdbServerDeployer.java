/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Server;

import WrenchDb.Core.Helpers.ReflectionHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.DAL.Helpers.WdbApplicationSettings;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try
        {
            WdbApplication application = HContext.Current().Get(WdbApplication.class, applicationId);
            WdbServerDeployer app= ReflectionHelper.getNewInstance(application.getWdbServertype().getServertypeName());
            app.application=application;
            return app;
            
        }
        catch(Exception err)
        {
            Logger.getLogger(WdbServerDeployer.class.getName())
                      .log(Level.SEVERE, "",err);
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
        //WdbRelease rel=HContext.Current().
      return   Deploy();
    }
    
    public boolean Deploy() throws Exception
    {
        throw new Exception(this.getClass().getName()+" Deploy not implemented");
    }

    public boolean UnDeploy() throws Exception
    {
        throw new Exception(this.getClass().getName()+" Deploy not implemented");
    }
    
    public boolean Uninstall() throws Exception
    {
        throw new Exception(this.getClass().getName()+" Uninstall not implemented");
    }
}
