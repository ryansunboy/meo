package com.asiainfo.meo.common.core.container;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;


public final class RegistryServiceBean
{
    private static final String BEANS_REFS_XML_NAME = "classpath:config/bean-ref-factory.xml";
    private static final String MEO_SERVICE = "com.asiainfo.meo.service";
    
    ApplicationContext beanFactory = ContextHolder.getInstance().getContext(); 
    
    private static class ServiceLocatorHolder
    {
        public static final RegistryServiceBean instance = new RegistryServiceBean();
    }
    
    private RegistryServiceBean()
    {
        initBeanFactory();
    }
    
    private void initBeanFactory()
    {
        if(beanFactory == null)
        {
            try
            {
                beanFactory = (ApplicationContext)ContextSingletonBeanFactoryLocator.getInstance().useBeanFactory(MEO_SERVICE).getFactory();
            }
            catch(Exception e)
            {
                beanFactory = (ApplicationContext)ContextSingletonBeanFactoryLocator.getInstance(BEANS_REFS_XML_NAME).useBeanFactory(MEO_SERVICE).getFactory();
            }
        }
    }
    
    public static RegistryServiceBean getInstance()
    {
        return ServiceLocatorHolder.instance;
    }

    public <T> T getService(String id)
    {
        return (T)beanFactory.getBean(id);
    }
    public <T> T getService(String id,Class<T> clazz)
    {
        return (T)beanFactory.getBean(id,clazz);
    }
    public <T> T getService(Class<T> clazz)
    {
        return (T)beanFactory.getBean(clazz);
    }
    public <T> Map<String,T> getServiceOfType(Class<T> type)
    {
        return beanFactory.getBeansOfType(type);
    }
}
