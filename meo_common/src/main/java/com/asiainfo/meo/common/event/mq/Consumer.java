/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq;

/**
 * @Description: 消息队列消费者
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-13
 */
public interface Consumer extends MessageListener
{
    String getGroup();
    
    String getConsumerId();
    
    String getTopic();
    
}
