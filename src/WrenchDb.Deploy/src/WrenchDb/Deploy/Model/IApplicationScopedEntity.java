/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Model;

import WrenchDb.DAL.Entities.WdbApplication;

/**
 *
 * @author Daniele Fontani
 */
public abstract class IApplicationScopedEntity {
    private WdbApplication application=null;

    /**
     * @return the application
     */
    public WdbApplication getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(WdbApplication application) {
        this.application = application;
    }
    
}
