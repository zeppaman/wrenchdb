/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Web.CentralAdmin.Listeners;

import WrenchDb.DAL.Annotations.EventListenerAnnotation;
import WrenchDb.DAL.Configuration.EventListener;
import WrenchDb.DAL.Entities.WdbChangescript;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Entities.WdbChangescript;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Helpers.HContext;

/**
 *
 * @author Daniele Fontani
 */
@EventListenerAnnotation()
public class WdbApplicationListener extends EventListener {

    @Override
    public String getName() {
        return "WdbApplicationAudit";
    }

    @Override
    public String getEventSource() {
        return "wdb_application";
    }


    @Override
    public void AfterInsert(Object entityToSave, Object objectId, String EventProvider) {
        long id=-1;
        id= Long.parseLong(objectId.toString());
        
        WdbApplication entitySaved=HContext.Current().Get(WdbApplication.class,id);
        
        WdbChangescript cs= new WdbChangescript();
        cs.setChangescriptDesc("APPLICATION CORE ENTITIES");
        cs.setChangescriptDesc("SELECT 'TEST QUERY'");
        cs.setIsDeployed(false);
        cs.setScriptSource(0);
        cs.setWdbApplication(entitySaved);
        
        HContext.Current().SaveOrUpdate(cs);
        
    }

    
    
}
