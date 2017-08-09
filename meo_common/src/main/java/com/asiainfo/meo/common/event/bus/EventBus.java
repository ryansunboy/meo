/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.bus;

import java.util.Collection;
import java.util.Map;

import com.asiainfo.meo.common.event.Event;
import com.asiainfo.meo.common.event.EventListener;

/**
 * @Description: 事件管理器接口
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-16
 */
public interface EventBus
{
    /**
     * @Description: 注册事件的订阅者
     * @param eventId 事件ID
     * @param eventListner 事件订阅者
     */
    void registerSubscription(String eventId, EventListener eventListner);
    
    /**
     * @Description: 注册事件的订阅者
     * @param eventId 事件ID
     * @param eventListners 事件订阅者
     */
    void registerSubscriptions(String eventId, Collection<EventListener> eventListners);
    
    /**
     * @Description: 取消事件的订阅
     * @param eventId 事件ID
     */
    void unRegisterSubscription(String eventId);
    
    /**
     * @Description: 取消所有事件的订阅
     */
    void unRegisterAllSubscriptions();
    
    /**
     * @Description: 获取事件的订阅者
     * @param eventId 事件ID
     * @return 事件订阅者
     */
    Collection<EventListener> getSubscription(String eventId);
    
    /**
     * @Description: 获取所有的订阅事件明细
     * @return 订阅事件明细
     */
    Map<String, Collection<EventListener>> getAllSubscriptions();
    
    /**
     * @Description: 发布事件
     * @param e 事件
     */
    void publish(Event e);
}
