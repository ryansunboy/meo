/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.test.common;                                                                                                                                                                                                                                                                       

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-3-10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class TestMessageQueue
{
    @Test
    public void testSender()
    {
        JmsTemplate jmsTemplate = ServiceLocatorFactory.getService(JmsTemplate.class);
        final String message = "raymond";
        System.out.println(new Date());
        long s = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++)
        {
            jmsTemplate.send("topic", new MessageCreator()
            {
                
                @Override
                public Message createMessage(Session session) throws JMSException
                {
                    return session.createTextMessage(message);
                }
            });
        }
        long e = System.currentTimeMillis();
        System.out.println(e - s);
        System.out.println(new Date());
    }
    
    @Test
    public void testReciver() throws JMSException
    {
        JmsTemplate jmsTemplate = ServiceLocatorFactory.getService(JmsTemplate.class);
        Message m = jmsTemplate.receive("topic");
        String message = ((TextMessage)m).getText();
        System.out.println(message);
    }
}
