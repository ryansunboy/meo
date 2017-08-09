/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.mq.activemq.consumer;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.CollectionUtils;

import com.asiainfo.meo.common.event.mq.Consumer;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-15
 */
public class ActiveMqConsumerBeanFactoryPostProcessor implements BeanFactoryPostProcessor,PriorityOrdered
{
    private int order = Ordered.LOWEST_PRECEDENCE;
    
    private String defaultListenerMethod = "onMessage";
    
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
        Map<String,Consumer> consumers =   beanFactory.getBeansOfType(Consumer.class);
        if(!CollectionUtils.isEmpty(consumers))
        {
            for(Map.Entry<String,Consumer> entry : consumers.entrySet())
            {
                initConsumer(entry.getValue(),beanFactory);
            }
        }
    }
    
    private void initConsumer(Consumer consumer,ConfigurableListableBeanFactory beanFactory)
    {
        String topicName = consumer.getTopic();
        DefaultMessageListenerContainer mlc = beanFactory.getBean(DefaultMessageListenerContainer.class);
        MessageListenerAdapter mla = new MessageListenerAdapter(consumer);
        mla.setDefaultListenerMethod(defaultListenerMethod);
        
//        MessageConverter jsonMessageConverter = beanFactory.getBean(MappingJackson2MessageConverter.class);
//        mla.setMessageConverter(jsonMessageConverter);
        
        mlc.setDestinationName(topicName);
        mlc.setPubSubDomain(true);
        mlc.setMessageListener(mla);
        
        mlc.start();
    }
    
    /* (Not Javadoc)                                         
      * <p>Title: getOrder</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @return                                                                                                                                                                                                                                                                                   
      * @see org.springframework.core.Ordered#getOrder()                                                                                                                                                                                                                                                                      
      */
    @Override
    public int getOrder()
    {
         return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public String getDefaultListenerMethod()
    {
        return defaultListenerMethod;
    }

    public void setDefaultListenerMethod(String defaultListenerMethod)
    {
        this.defaultListenerMethod = defaultListenerMethod;
    }
    
    
}
