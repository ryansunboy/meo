/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.subscriber;

import java.util.Collection;

import com.asiainfo.meo.common.event.EventListener;

/**l
 * @Description: 事件订阅者
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-14
 */
public interface EventSubscriber 
{
    String getEventId();
    
    Collection<EventListener> getEventListeners();
}
