/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.bus.impl;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.CollectionUtils;

import com.asiainfo.meo.common.event.bus.EventBus;
import com.asiainfo.meo.common.event.subscriber.EventSubscriber;

/**
 * @Description: 加载事件总线配置
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-16
 */
public class EventBusBeanFactoryPostProcessor implements BeanFactoryPostProcessor
{
    
    /*
     * (Not Javadoc) <p>Title: postProcessBeanFactory</p> <p>Description: </p>
     * @param beanFactory
     * @throws BeansException
     * @see
     * org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory
     * .config.ConfigurableListableBeanFactory)
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        EventBus eventBus = beanFactory.getBean(EventBus.class);
        Map<String,EventSubscriber> eventSubscribers =   beanFactory.getBeansOfType(EventSubscriber.class);
        if(!CollectionUtils.isEmpty(eventSubscribers))
        {
            for(Map.Entry<String,EventSubscriber> entry : eventSubscribers.entrySet())
            {
                EventSubscriber eventSubscriber = entry.getValue();
                eventBus.registerSubscriptions(eventSubscriber.getEventId(), eventSubscriber.getEventListeners());
            }
        } 
    }
    
}
