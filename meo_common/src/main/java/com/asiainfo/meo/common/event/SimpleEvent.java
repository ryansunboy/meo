/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description: 简单事件实现
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-16
 */
public class SimpleEvent implements Event
{
    private static final long serialVersionUID = -7179629829341779927L;
    
    private static final Log LOG = LogFactory.getLog(SimpleEvent.class);
    
    private String eventId;
    
    private Object payload;
    
    private boolean asysEvent = true;
    
    public SimpleEvent()
    {
        
    }
    
    public SimpleEvent(String eventId)
    {
        this.eventId = eventId;
    }
    
    public SimpleEvent(String eventId, Object payload)
    {
        this(eventId);
        this.payload = payload;
    }
    
    /*
     * (Not Javadoc) <p>Title: getEventId</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.event.Event#getEventId()
     */
    @Override
    public String getEventId()
    {
        // TODO Auto-generated method stub
        return this.eventId;
    }
    
    /*
     * (Not Javadoc) <p>Title: getPayload</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.event.Event#getPayload()
     */
    @Override
    public Object getPayload()
    {
        // TODO Auto-generated method stub
        return this.payload;
    }
    
    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }
    
    public void setPayload(Object payload)
    {
        this.payload = payload;
    }
    
    public boolean isAsysEvent()
    {
        return asysEvent;
    }
    
    public void setAsysEvent(boolean asysEvent)
    {
        this.asysEvent = asysEvent;
    }
    
    /*
     * (Not Javadoc) <p>Title: getPayload</p> <p>Description: </p>
     * @param type
     * @return
     * @see com.asiainfo.meo.common.event.Event#getPayload(java.lang.Class)
     */
    @Override
    public <T> T getPayload(Class<T> type)
    {
        try
        {
            T t = type.getConstructor().newInstance();
            BeanUtils.copyProperties(t, this.payload);
            return t;
        }
        catch(Throwable e)
        {
           LOG.error("get payload exception", e);
           return null;
        }
    }
    
}
