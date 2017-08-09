/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq;

/**
 * @Description: 消息队列生产者
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-13
 */
public interface Producer
{
    void send(Message m);
    
    String getTopic();
}
