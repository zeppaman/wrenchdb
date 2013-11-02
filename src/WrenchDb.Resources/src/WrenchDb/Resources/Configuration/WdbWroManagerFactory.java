/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Resources.Configuration;

import java.util.Map;
import java.util.Properties;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.group.GroupExtractor;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;

/**
 *
 * @author d.fontani
 */
//WrenchDb.Resources.Configuration.WdbWroManagerFactory
public class WdbWroManagerFactory 
  extends ConfigurableWroManagerFactory {

    @Override
    public ConfigurableWroManagerFactory setConfigProperties(Properties configProperties) {
        return super.setConfigProperties(configProperties); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected GroupExtractor newGroupExtractor() {
        return super.newGroupExtractor(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseWroManagerFactory setGroupExtractor(GroupExtractor groupExtractor) {
        return super.setGroupExtractor(groupExtractor); //To change body of generated methods, choose Tools | Templates.
    }
  
 
  @Override
  protected void contributePreProcessors(Map<String, ResourcePreProcessor> map) {
  
  }
  
  @Override
  protected void contributePostProcessors(Map<String, ResourcePostProcessor> map) {
    //map.put("myPostProcessor", new MyPostProcessor());
  }
  @Override
  protected void contributeLocators(Map<String, UriLocator> map) {
 //   map.put("myLocator", new MyLocator());
  }
}