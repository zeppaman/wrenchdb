/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import org.codehaus.cargo.container.ContainerType;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.configuration.*;
import org.codehaus.cargo.container.deployable.*;
import org.codehaus.cargo.container.deployer.Deployer;
import org.codehaus.cargo.container.tomcat.TomcatCopyingInstalledLocalDeployer;
import org.codehaus.cargo.container.tomcat.TomcatExistingLocalConfiguration;
import org.codehaus.cargo.container.tomcat.TomcatWAR;
import org.codehaus.cargo.generic.DefaultContainerFactory;
import org.codehaus.cargo.generic.configuration.*;
import org.codehaus.cargo.generic.deployable.DefaultDeployableFactory;
import org.codehaus.cargo.generic.deployable.DeployableFactory;
import org.junit.Test;

/**
 *
 * @author Daniele Fontani
 */
public class DeployTest {
    
    
    @Test
    public void Test()
    {
         Deployable war = new TomcatWAR("C:\\temp\\sample.war");

        ConfigurationFactory configurationFactory =
                new DefaultConfigurationFactory();
        LocalConfiguration configuration = new TomcatExistingLocalConfiguration("D:/devserver/Apache Tomcat 7.0.34");
        configuration.addDeployable(war);

        InstalledLocalContainer container =
                (InstalledLocalContainer) new DefaultContainerFactory().createContainer(
                "tomcat7x", ContainerType.INSTALLED, configuration);
        container.setHome("D:/devserver/Apache Tomcat 7.0.34");
                
        DeployableFactory factory = new DefaultDeployableFactory();
         war = factory.createDeployable(container.getId(), "C:\\temp\\sample.war",
            DeployableType.WAR);
        
            
       Deployer deployer = new TomcatCopyingInstalledLocalDeployer(container);
       
       deployer.deploy(war);
       container.start();
       container.stop();
    }
}
