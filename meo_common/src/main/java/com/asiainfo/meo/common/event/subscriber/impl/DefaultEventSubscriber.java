/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.subscriber.impl;

import java.util.Collection;

import com.asiainfo.meo.common.event.EventListener;
import com.asiainfo.meo.common.event.subscriber.EventSubscriber;

/**
 * @Description: 事件订阅
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-16
 */
public class DefaultEventSubscriber implements EventSubscriber
{
    private String eventId;
    
    private Collection<EventListener> eventListeners;
    
    
    /*
     * (Not Javadoc) <p>Title: getEventListners</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.event.subscriber.EventSubsriber#getEventListeners()
     */
    @Override
    public Collection<EventListener> getEventListeners()
    {
        // TODO Auto-generated method stub
        return this.eventListeners;
    }
    

    public void setEventListeners(Collection<EventListener> eventListeners)
    {
        this.eventListeners = eventListeners;
    }
    

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }


    /* (Not Javadoc)                                         
      * <p>Title: getEventId</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.event.subscriber.EventSubsriber#getEventId()                                                                                                                                                                                                                                                                      
      */
    @Override
    public String getEventId()
    {
        // TODO Auto-generated method stub                                     
         return this.eventId;
    }
    
}
