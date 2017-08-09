package com.asiainfo.meo.common.core.container;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DelegateContextLoaderListener implements ServletContextListener
{
    private ContextLoaderListener contextLoaderListener = new ContextLoaderListener();
    
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        this.contextLoaderListener.contextInitialized(event);
        //获取ApplicationContext到Application应用中    
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ContextHolder.getInstance().registerContext(applicationContext);

        // TODO 注册中心？ 
        
    }
    
    /**
     * Close the root web application context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        this.contextLoaderListener.contextDestroyed(event);
    }
    
}
