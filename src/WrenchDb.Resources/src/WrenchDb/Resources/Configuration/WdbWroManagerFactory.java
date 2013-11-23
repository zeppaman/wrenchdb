/* 
 * Copyright (C) 2013 Daniele Fontani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.*;
import ro.isdc.wro.model.resource.processor.impl.css.CssDataUriPreProcessor;

/**
 *
 * @author d.fontani
 */
//WrenchDb.Resources.Configuration.WdbWroManagerFactory
public class WdbWroManagerFactory 
  extends ConfigurableWroManagerFactory {

    @Override
    protected ProcessorsFactory newProcessorsFactory() {
      final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
      factory.addPreProcessor(new CssDataUriPreProcessor());
      return factory;
    }

    @Override
    public ConfigurableWroManagerFactory setConfigProperties(Properties configProperties) {
        configProperties.setProperty("preProcessors", "cssUrlRewriting,cssImport,semicolonAppender");
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