/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Configuration;

import WrenchDb.Core.Configuration.ItemAppendingConfigurationContainer;
import WrenchDb.MVC.BaseClasses.ScriptReference;

/**
 *
 * @author d.fontani
 */
public class ScriptSet 
    extends ItemAppendingConfigurationContainer<ScriptReference> {

    @Override
    public String getName() {
        return "ScriptSet";
    }

    @Override
    public Class GetType() {
        return ScriptReference.class;
    }
    
}
