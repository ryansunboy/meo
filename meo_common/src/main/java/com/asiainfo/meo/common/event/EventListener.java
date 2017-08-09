/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event;


/**
 * @Description: 事件监听
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-14
 */
public interface EventListener
{
    void onEvent(Event e);
    
    void onException(Throwable t);
}
