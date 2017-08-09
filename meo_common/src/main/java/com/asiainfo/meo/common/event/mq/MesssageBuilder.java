/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.StringUtil;

/**
 * @Description: 消息队列消息构建者
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-17
 */
public class MesssageBuilder<T> 
{
    private String toipc;
    
    private Object payload;
    
    public MesssageBuilder(String topic)
    {
        this.toipc = topic;
    }
    
    public MesssageBuilder<T> withTopicName(String topicName)
    {
        this.toipc = topicName;
        return this;
    }
    
    public MesssageBuilder<T> withPayload(Object payload)
    {
        if(this.payload != null)
        {
            throw new IllegalArgumentException("payload exists");
        }
        this.payload = payload;
        return this;
    } 
    
    public Message build()
    {
         if(StringUtil.isBlank(toipc))
         {
             throw new MeoException("topic blank");
         }
         Message m = new Message();
         m.setTopicName(this.toipc);
         m.setPayload(this.payload);
         m.setTimestamp(DateTimeUtil.getCurrentTimeMillis());
         m.setMessageId(SequenceUtil.getUUIDSequence());
         return m;
    }
    
}
