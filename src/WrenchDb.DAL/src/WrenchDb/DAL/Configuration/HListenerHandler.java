/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.DAL.Configuration;

import WrenchDb.Enums.HSaveMode;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import org.hibernate.EmptyInterceptor;
import org.hibernate.HibernateException;
import org.hibernate.engine.EntityEntry;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.LoadEventListener;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;
import org.hibernate.type.Type;

/**
 *
 * @author Daniele Fontani
 */
public class HListenerHandler 
     extends   DefaultSaveOrUpdateEventListener
implements 
//        org.hibernate.event.PostUpdateEventListener,
//        org.hibernate.event.PostInsertEventListener,
        org.hibernate.event.PostDeleteEventListener,
//         org.hibernate.event.PreUpdateEventListener,
//        org.hibernate.event.PreInsertEventListener,
        org.hibernate.event.PreDeleteEventListener
        
{

    public static String EventProvider="WrenchDb.DAL";
   
    public List<EventListener> getListener(Object entity) {
        if(entity!=null )
        {
            Table t=entity.getClass().getAnnotation(Table.class);
            if(t!=null)
            {
                
                //Try to propagate event to listeners
                List<EventListener> list=EventListenerSet.Current().getListenersFromSource(t.name());
            return list;
            }
        }
            
            
        return null;
    }
//
//    @Override
//    public void onPostUpdate(PostUpdateEvent events) {
//        List<EventListener> list= this.getListener(events.getEntity());
//        for(EventListener l :list)
//        {
//            l.AfterUpdate(events.getEntity(),events.getId(),EventProvider);
//        }
//    }
//
//    @Override
//    public void onPostInsert(PostInsertEvent events) {
//      List<EventListener> list= this.getListener(events.getEntity());
//        for(EventListener l :list)
//        {
//            l.AfterInsert(events.getEntity(),events.getId(),EventProvider);
//        }
//    }
//
//
//    @Override
//    public boolean onPreUpdate(PreUpdateEvent events) {
//        List<EventListener> list= this.getListener(events.getEntity());
//        boolean canSave=true;
//        for(EventListener l :list)
//        {
//          canSave= canSave &  l.BeforeUpdate(events.getEntity(),events.getId(),EventProvider);
//        }
//        return canSave;
//    }
//
//    @Override
//    public boolean onPreInsert(PreInsertEvent events) {
//        List<EventListener> list= this.getListener(events.getEntity());
//        boolean canSave=true;
//        for(EventListener l :list)
//        {
//          canSave= canSave &  l.BeforeInsert(events.getEntity(),EventProvider);
//        }
//        return canSave;
//    }
//
//    
    
    @Override
    public void onPostDelete(PostDeleteEvent events) {
      List<EventListener> list= this.getListener(events.getEntity());
        for(EventListener l :list)
        {
            l.AfterInsert(events.getEntity(),events.getId(),EventProvider);
        }
    }
    
    @Override
    public boolean onPreDelete(PreDeleteEvent events) {
            List<EventListener> list= this.getListener(events.getEntity());
         boolean canSave=true;
        for(EventListener l :list)
        {
          canSave= canSave &  l.BeforeDelete(events.getEntity(),events.getId(),EventProvider);
        }
        return canSave;
    }

   

    @Override
    protected Serializable performSaveOrUpdate(SaveOrUpdateEvent event) {
   
        EntityEntry  entity=event.getEntry();
       boolean insert=false;
          List<EventListener> list= this.getListener( event.getEntity());
         boolean cancel=false;
        for(EventListener l :list)
        {
            
          if(entity!=null && entity.getId()!=null)
          {
                cancel= cancel |  l.BeforeUpdate(event.getEntity(),entity.getId(),EventProvider);
          }
          else
          {
              cancel= cancel |  l.BeforeInsert(event.getEntity(),EventProvider);
               insert=true;
          }
        }
        if(!cancel)
        {
        
            Serializable newId= super.performSaveOrUpdate(event);
        
            for(EventListener l :list)
           {

             if(insert)
             {
                   l.AfterUpdate(event.getEntity(),newId,EventProvider);
             }
             else
             {
                   l.AfterInsert(event.getEntity(),newId,EventProvider);
             }
           }
            return newId;
        }
        return null;
    }

   
    
}
