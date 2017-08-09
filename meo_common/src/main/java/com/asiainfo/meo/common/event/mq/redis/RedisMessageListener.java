/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.event.mq.redis;                                                                                                                                                                                                                                                                       

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
                                                                                                                                                                                                                                                                                              
 /** @Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
 * @Date 2015-4-13                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class RedisMessageListener implements MessageListener
{
    
    /* (Not Javadoc)                                         
     * <p>Title: onMessage</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param message
     * @param pattern                                                                                                                                                                                                                                                                                   
     * @see org.springframework.data.redis.connection.MessageListener#onMessage(org.springframework.data.redis.connection.Message, byte[])                                                                                                                                                                                                                                                                      
     */
    @Override
    public void onMessage(Message message, byte[] pattern)
    {
        System.out.println("Message received: " + message.toString() );                                    
    }
    
}
