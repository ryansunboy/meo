/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq;

import java.io.Serializable;

/**
 * @Description: 消息队列的消息格式
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-13
 */
public class Message implements Serializable
{
    
    private static final long serialVersionUID = 1L;

    private String messageId;
    
    private Object payload;
    
    private long timestamp;
    
    private String topicName;
    
    
    public String getMessageId()
    {
        return messageId;
    }
    
    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }
    
    public Object getPayload()
    {
        return payload;
    }
    
    public void setPayload(Object payload)
    {
        this.payload = payload;
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }
    
    public String getTopicName()
    {
        return topicName;
    }
    
    public void setTopicName(String topicName)
    {
        this.topicName = topicName;
    }
    
}
