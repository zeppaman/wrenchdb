/* 
 * Copyright (C) 2013 d.fontani
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
package WrenchDb.DAL.Configuration;

import WrenchDb.Core.Configuration.AnnotationBasedConfiguration;
import WrenchDb.Core.Configuration.ItemAppendingConfigurationContainer;
import WrenchDb.DAL.Annotations.EventListenerAnnotation;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author d.fontani
 */
public class EventListenerSet  
extends AnnotationBasedConfiguration<EventListener> {

    
    private static EventListenerSet _current=null;
    
    public static  EventListenerSet Current()
    {
        if(_current==null)
        {
            _current= new EventListenerSet();
            _current.LoadItems();
        }
        return _current;
    }
    
    public  List<EventListener> getListenersFromSource(String sourceName)
    {
        List<EventListener> result= new ArrayList<EventListener>();
        
        if(this.getItems()!=null && this.getItems().size()>0 && sourceName!=null)
        {
            for(EventListener l : this.getItems())
            {
                if(sourceName.equals(l.getEventSource()))
                {
                    result.add(l);
                }
            }
        }
        
        return result;
    }

    @Override
    public Class<? extends Annotation> GetAnnotationMarker() {
       
        return  EventListenerAnnotation.class;
    }
    
    
}