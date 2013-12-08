/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests.Helpers;

import WrenchDb.DAL.Annotations.EventListenerAnnotation;
import WrenchDb.DAL.Configuration.EventListener;

/**
 *
 * @author Daniele Fontani
 */
@EventListenerAnnotation()
public class WdbTestAudit extends EventListener {

    @Override
    public String getName() {
        return "WdbTestAudit";
    }

    @Override
    public String getEventSource() {
        return "wdb_application";
    }

    @Override
    public boolean BeforeInsert(Object entityToSave, String EventProvider) {
        return super.BeforeInsert(entityToSave, EventProvider); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AfterInsert(Object entityToSave, Object objectId, String EventProvider) {
        super.AfterInsert(entityToSave, objectId, EventProvider); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean BeforeUpdate(Object entityToSave, Object objectId, String EventProvider) {
        return super.BeforeUpdate(entityToSave, objectId, EventProvider); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AfterUpdate(Object entityToSave, Object objectId, String EventProvider) {
        super.AfterUpdate(entityToSave, objectId, EventProvider); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean BeforeDelete(Object entityToSave, Object objectId, String EventProvider) {
        return super.BeforeDelete(entityToSave, objectId, EventProvider); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AfterDelete(Object entityToSave, Object objectId, String EventProvider) {
        super.AfterDelete(entityToSave, objectId, EventProvider); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
