/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.bus.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.StringUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.event.Event;
import com.asiainfo.meo.common.event.EventListener;
import com.asiainfo.meo.common.event.bus.EventBus;
import com.asiainfo.meo.common.event.message.MessageEvent;
import com.asiainfo.meo.common.event.message.listener.DefaultMessageEventListener;

/**
 * @Description: 事件管理
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-14
 */
public class EventBusImpl implements EventBus
{
    private static final Log LOG = LogFactory.getLog(EventBusImpl.class);
    
    private final ConcurrentMap<String, Collection<EventListener>> eventStore = new ConcurrentHashMap<String, Collection<EventListener>>();
    
    private volatile ExecutorService executor;
    
    private final EventListener messageEventListener = new DefaultMessageEventListener();
    
    private long timeOut = 3000;
    
    public void publish(Event e) 
    {
        final Event dispatchEvent = e;
        if(StringUtil.isBlank(e.getEventId()))
        {
            throw new IllegalArgumentException("blank event id");
        }
        
        Future<Void> f = getExecutor().submit(new Callable<Void>()
        {
            
            @Override
            public Void call() throws Exception
            {
                EventBusImpl.this.dispatchEvent(dispatchEvent);
                return null;
            }
        });
        if(!e.isAsysEvent())
        {
            try
            {
                f.get(getTimeOut(),TimeUnit.MILLISECONDS);
            }
            catch(Throwable t)
            {
                throw new MeoException("timeout", t);
            }
        }
    }
    
    public ExecutorService getExecutor()
    {
        if(executor == null)
        {
            executor = Executors.newFixedThreadPool(10);
        }
        return executor;
    }
    
    public void setExecutor(ExecutorService executor)
    {
        this.executor = executor;
    }
    
    private void dispatchEvent(Event e)
    {
        if(e instanceof MessageEvent)
        {
            dispatchMessageEvent(e);
        }
        else
        {
            dispatchLocalEvent(e);
        }
    }
    
    private void dispatchLocalEvent(Event e)
    {
        Collection<EventListener> eventListeners = eventStore.get(e.getEventId());
        if (ValidateUtil.isNotEmpty(eventListeners))
        {
            for (EventListener eventListener : eventListeners)
            {
                handleEvent(e,eventListener);
            }
        }
        else
        {
            LOG.warn("event do not have handler..." + e);
        }
    }
    
    private void dispatchMessageEvent(Event e)
    {
        handleEvent(e,messageEventListener);
    }
    
    private void handleEvent(Event e,EventListener eventListener)
    {
        try
        {
            eventListener.onEvent(e);
        }
        catch(Throwable t)
        {
            LOG.error("dispatch event exception" + eventListener.getClass(), t);
            try
            {
                eventListener.onException(t);   
            }
            catch(Throwable ignore)
            {
                LOG.error("hanle event exception throwable" + eventListener.getClass(), ignore);
            }
        }
    }
    
    public long getTimeOut()
    {
        return timeOut;
    }
    
    public void setTimeOut(long timeOut)
    {
        this.timeOut = timeOut;
    }

    /* (Not Javadoc)                                         
      * <p>Title: registerSubscription</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param eventId
      * @param eventListner                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.mq.EventManager#registerSubscription(java.lang.String, com.asiainfo.meo.common.core.mq.EventListener)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void registerSubscription(String eventId, EventListener eventListner)
    {
        Set<EventListener> listners = new HashSet<EventListener>(1);
        listners.add(eventListner);
        registerSubscriptions(eventId, listners);                                     
    }
    
    /* (Not Javadoc)                                         
     * <p>Title: registerSubscription</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param eventId
     * @param eventListners                                                                                                                                                                                                                                                                                   
     * @see com.asiainfo.meo.common.core.mq.EventManager#registerSubscriptions(java.lang.String, java.util.Collection)                                                                                                                                                                                                                                                                      
     */
   @Override
   public void registerSubscriptions(String eventId, Collection<EventListener> eventListners)
   {
       Collection<EventListener> eventListeners = eventStore.get(eventId);
       if (eventListeners == null)
       {
           eventListeners = new CopyOnWriteArraySet<EventListener>();
           Collection<EventListener>  oldEventListeners = eventStore.putIfAbsent(eventId, eventListeners);
           if(oldEventListeners != null)
           {
               eventListeners = oldEventListeners;
           }
       }
       eventListeners.addAll(eventListners);                                     
        
   }
   
    /* (Not Javadoc)                                         
      * <p>Title: unRegisterSubscription</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param eventId                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.mq.EventManager#unRegisterSubscription(java.lang.String)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void unRegisterSubscription(String eventId)
    {
        eventStore.remove(eventId);
    }

    /* (Not Javadoc)                                         
      * <p>Title: unRegisterAllSubscriptions</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
      * @see com.asiainfo.meo.common.core.mq.EventManager#unRegisterAllSubscriptions()                                                                                                                                                                                                                                                                      
      */
    @Override
    public void unRegisterAllSubscriptions()
    {
        eventStore.clear();
    }

    /* (Not Javadoc)                                         
      * <p>Title: getSubscription</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param eventId
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.mq.EventManager#getSubscription(java.lang.String)                                                                                                                                                                                                                                                                      
      */
    @Override
    public Collection<EventListener> getSubscription(String eventId)
    {
         return eventStore.get(eventId);
    }
    
    public void destory()
    {
        this.executor.shutdown();
    }

    /* (Not Javadoc)                                         
      * <p>Title: getAllSubscriptions</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.event.bus.EventBus#getAllSubscriptions()                                                                                                                                                                                                                                                                      
      */
    @Override
    public Map<String, Collection<EventListener>> getAllSubscriptions()
    {
         return this.eventStore;
    }
}
