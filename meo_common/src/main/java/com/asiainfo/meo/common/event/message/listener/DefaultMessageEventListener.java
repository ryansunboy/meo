/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.message.listener;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.event.Event;
import com.asiainfo.meo.common.event.EventListener;
import com.asiainfo.meo.common.event.message.MessageEvent;
import com.asiainfo.meo.common.event.mq.Message;
import com.asiainfo.meo.common.event.mq.MesssageBuilder;
import com.asiainfo.meo.common.event.mq.Producer;

/**
 * 默认的消息监听
 * 
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-16
 */
public class DefaultMessageEventListener implements EventListener
{
    private static final Log LOG = LogFactory.getLog(DefaultMessageEventListener.class);
            
    /*
     * (Not Javadoc) <p>Title: onEvent</p> <p>Description: </p>
     * @param e
     * @see com.asiainfo.meo.common.core.mq.EventListener#onEvent(com.asiainfo.meo.common.core.event.Event)
     */
    @Override
    public void onEvent(Event e)
    {
        if (e == null || !(e instanceof MessageEvent))
        {
            LOG.info("empty event or not message event,ignore " + e.getClass());
            return;
        }
        MessageEvent me = (MessageEvent) e;
        Producer producer = ServiceLocatorFactory.getService(Producer.class);
        MesssageBuilder<Message> mb = new MesssageBuilder<Message>(me.getTopic());
        mb.withPayload(me);
        producer.send(mb.build()); 
    }
    
    /*
     * (Not Javadoc) <p>Title: onException</p> <p>Description: </p>
     * @param t
     * @see com.asiainfo.meo.common.core.mq.EventListener#onException(java.lang.Throwable)
     */
    @Override
    public void onException(Throwable t)
    {
        // TODO Auto-generated method stub
        
    }
    
}
