/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq;

/**
 * @Description: 消息队列的消息处理监听
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-13
 */
public interface MessageListener
{
    void onMessage(Object message);
}
