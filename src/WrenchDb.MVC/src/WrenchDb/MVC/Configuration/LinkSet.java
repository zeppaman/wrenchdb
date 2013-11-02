/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Configuration;

import WrenchDb.Core.Configuration.ItemAppendingConfigurationContainer;
import WrenchDb.MVC.BaseClasses.LinkReference;
import WrenchDb.MVC.BaseClasses.ScriptReference;

/**
 *
 * @author d.fontani
 */
public class LinkSet 
    extends ItemAppendingConfigurationContainer<LinkReference> {

    @Override
    public String getName() {
        return "LinkSet";
    }

    @Override
    public Class GetType() {
        return LinkReference.class;
    }
    
}
