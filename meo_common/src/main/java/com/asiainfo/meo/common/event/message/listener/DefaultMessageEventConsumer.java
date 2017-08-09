/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.message.listener;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.event.Event;
import com.asiainfo.meo.common.event.SimpleEvent;
import com.asiainfo.meo.common.event.bus.EventBus;
import com.asiainfo.meo.common.event.mq.Consumer;
import com.asiainfo.meo.common.event.mq.Message;

/**
 * @Description: 消息队列消费者实现
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-15
 */
public class DefaultMessageEventConsumer implements Consumer
{
    private static final Log LOG = LogFactory.getLog(DefaultMessageEventConsumer.class);
    
    private String topic;
    
    public void onMessage(Object message)
    {
        if(message == null || !(message instanceof String))
        {
            LOG.warn("message should be String or can not be null,ingore this message" + message);
            return;
        }
        if((message instanceof String))
        {
            try
            {
                Message m  = JsonUtil.writeStringAsObject((String)message, Message.class);
                Map<String,Object> me = (Map<String, Object>) m.getPayload();
                
                EventBus eventBus = ServiceLocatorFactory.getService(EventBus.class);
                
                Event event = new SimpleEvent((String) me.get("eventId"),me.get("payload"));
                eventBus.publish(event);
            }
            catch(Throwable t)
            {
                LOG.error("consumer event exception : " ,t);
            }
        }
    }
    
    /*
     * (Not Javadoc) <p>Title: getGroup</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.mq.Consumer#getGroup()
     */
    @Override
    public String getGroup()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (Not Javadoc) <p>Title: getConsumerId</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.mq.Consumer#getConsumerId()
     */
    @Override
    public String getConsumerId()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (Not Javadoc) <p>Title: getTopic</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.mq.Consumer#getTopic()
     */
    @Override
    public String getTopic()
    {
        // TODO Auto-generated method stub
        return this.topic;
    }
    
    public void setTopic(String topic)
    {
        this.topic = topic;
    }
    
}
