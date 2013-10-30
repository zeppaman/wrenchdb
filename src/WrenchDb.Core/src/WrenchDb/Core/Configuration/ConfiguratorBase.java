/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Configuration;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

/**
 *
 * @author d.fontani
 */
public abstract  class ConfiguratorBase<T> {
    
    public abstract  Class  getUnderlingType();
    
    public abstract void Configure(ArrayList<T> configuration);
}
