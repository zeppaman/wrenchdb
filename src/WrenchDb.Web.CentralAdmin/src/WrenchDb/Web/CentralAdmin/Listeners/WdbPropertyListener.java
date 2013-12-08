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
import WrenchDb.DAL.Entities.WdbEntity;
import WrenchDb.DAL.Entities.WdbProperty;
import WrenchDb.DAL.Entities.WdbRelease;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Data.Enums.JqGridFieldType;
import WrenchDb.Deploy.Database.WdbDatabaseDeployer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Daniele Fontani
 */
@EventListenerAnnotation()
public class WdbPropertyListener extends EventListener {

    @Override
    public String getName() {
        return "WdbPropertyListener";
    }

    @Override
    public String getEventSource() {
        return "wdb_property";
    }


    @Override
    public void AfterInsert(Object entityToSave, Object objectId, String EventProvider) {
        long id=-1;
        id= Long.parseLong(objectId.toString());
        Session s = null;
        try
        {
            s=HContext.Current().getSessionFactory().getCurrentSession();
        
        }
        catch(Exception err)
        {
            
        }
        if(s==null) s= HContext.Current().getSessionFactory().openSession();
        Transaction t= s.beginTransaction();
        try
        {
        WdbProperty property=HContext.Current().Get(s,WdbProperty.class,id);
        WdbEntity entity=property.getWdbEntity();
        WdbApplication application=entity.getWdbApplication();
        
        //Create Table
        WdbDatabaseDeployer db= WdbDatabaseDeployer.getDatabaseDeployer(application.getApplicationId());
        db.logOnly=true;

        
        
        String pkColumnName=property.getPropertyName();
        //Add pkcolumn
        db.addColumn(entity.getEntityName(),pkColumnName,property.getWdbPropertyType().getPropertyTypeSql(),null);
        if(property.getWdbPropertyType().getPropertyType()==15)
        {
            db.addPk(entity.getEntityName(),pkColumnName);
        }
        
         
       //Get last chacge script
       long version=0;
       List<WdbChangescript> prevVersion= s.createCriteria(WdbChangescript.class)
               .add(Restrictions.eq("wdbApplication.applicationId",application.getApplicationId()))
               .addOrder(Order.desc("changescriptId"))
               .setFetchSize(1)
               .list();
       
       if(prevVersion==null || prevVersion.size()>0)
       {
           version=prevVersion.get(0).getScriptSource()+1;
       }
       
        //Add change
        WdbChangescript cs= new WdbChangescript();
        cs.setChangescriptDesc("ADDED PROPERTY "+entity.getEntityName());
        cs.setChangescriptSql(db.getScript());
        cs.setIsDeployed(false);
        cs.setScriptSource(0);
        cs.setWdbApplication(application);
        s.saveOrUpdate(cs);
        

                
        s.flush();
        t.commit();
        }
        catch(Exception err)
        {
            Logger.getLogger("").log(Level.SEVERE,"ADDING ENTITY",err);
            t.rollback();
        }
        finally
        {
            if(s!=null && s.isOpen())
                s.close();
        }
        
    }

    
    
}
