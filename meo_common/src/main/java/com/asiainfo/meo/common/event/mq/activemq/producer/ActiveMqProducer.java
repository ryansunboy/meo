/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq.activemq.producer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.StringUtil;
import com.asiainfo.meo.common.event.mq.Message;
import com.asiainfo.meo.common.event.mq.Producer;

/**
 * @Description: 消息队列生成者
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-15
 */
public class ActiveMqProducer implements Producer
{
    @Resource
    private JmsTemplate jmsTemplate;
    
    private String topic  = ActiveMqProducer.class.getName();
    
    private ConcurrentMap<String, Topic> topics = new ConcurrentHashMap<String, Topic>();
    
    /*
     * (Not Javadoc) <p>Title: send</p> <p>Description: </p>
     * @param m
     * @see com.asiainfo.meo.common.core.mq.Producer#send(com.asiainfo.meo.common.core.mq.Message)
     */
    @Override
    public void send(final Message m)
    {
        String topicName = m.getTopicName();
        if(StringUtil.isBlank(topicName))
        {
            topicName = getTopic();
        }
        Topic t = getTopic(topicName);
        // TODO Auto-generated method stub
        jmsTemplate.send(t,new MessageCreator()
        {
            
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException
            {
                javax.jms.Message messageToSend = session.createTextMessage(JsonUtil.writeObjectAsString(m)) ;
                return messageToSend;
            }
        });
    }
    
    private Topic getTopic(String topicName)
    {
        Topic t = this.topics.get(topicName);
        if(t == null)
        {
            t = new ActiveMQTopic(topicName);
            Topic oldTopic = this.topics.putIfAbsent(topicName, t);
            if(oldTopic != null)
            {
                t = oldTopic;
            }
        }
        return t;
    }
    
    /*
     * (Not Javadoc) <p>Title: getTopic</p> <p>Description: </p>
     * @return
     * @see com.asiainfo.meo.common.core.mq.Producer#getTopic()
     */
    @Override
    public String getTopic()
    {
        return this.topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

}
