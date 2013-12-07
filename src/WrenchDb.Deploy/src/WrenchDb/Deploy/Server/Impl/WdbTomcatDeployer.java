/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Server.Impl;

import WrenchDb.Deploy.Model.IFileDeployItem;
import WrenchDb.Deploy.Server.WdbDeployRepository;
import WrenchDb.Deploy.Server.WdbServerDeployer;
import org.codehaus.cargo.container.ContainerType;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.deployable.Deployable;
import org.codehaus.cargo.container.deployable.DeployableType;
import org.codehaus.cargo.container.deployer.Deployer;
import org.codehaus.cargo.container.tomcat.TomcatCopyingInstalledLocalDeployer;
import org.codehaus.cargo.container.tomcat.TomcatExistingLocalConfiguration;
import org.codehaus.cargo.container.tomcat.TomcatWAR;
import org.codehaus.cargo.generic.DefaultContainerFactory;
import org.codehaus.cargo.generic.configuration.ConfigurationFactory;
import org.codehaus.cargo.generic.configuration.DefaultConfigurationFactory;
import org.codehaus.cargo.generic.deployable.DefaultDeployableFactory;
import org.codehaus.cargo.generic.deployable.DeployableFactory;

/**
 *
 * @author Daniele Fontani
 */
//WrenchDb.Deploy.Server.Impl.WdbTomcatDeployer
public class WdbTomcatDeployer
extends WdbServerDeployer
{
    
    
    public boolean Deploy(IFileDeployItem deployItem) 
    {
      
        String serverUri=this.getApplication().getApplicationServerUri();
       //Setup server
        ConfigurationFactory configurationFactory =
                new DefaultConfigurationFactory();
        LocalConfiguration configuration = new TomcatExistingLocalConfiguration(serverUri);

        InstalledLocalContainer container =
                (InstalledLocalContainer) new DefaultContainerFactory().createContainer(
                "tomcat7x", ContainerType.INSTALLED, configuration);
        container.setHome(serverUri);
        
        // Set up war assuming it is really a jar file        
        Deployable war = new TomcatWAR(deployItem.getFilePath());
        DeployableFactory factory = new DefaultDeployableFactory();
         war = factory.createDeployable(container.getId(),deployItem.getFilePath(),
            DeployableType.WAR);
        
            
       Deployer deployer = new TomcatCopyingInstalledLocalDeployer(container);
       
       deployer.deploy(war);
        return true;
    }
}
