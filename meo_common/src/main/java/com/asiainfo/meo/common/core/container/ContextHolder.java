package com.asiainfo.meo.common.core.container;

import org.springframework.context.ApplicationContext;


public final class ContextHolder
{
    private static ContextHolder contextHolder = new ContextHolder();
    
    private ApplicationContext context;
    
    public static ContextHolder getInstance()
    {
        return contextHolder;
    }
    
    public   ApplicationContext getContext()
    {
        return context;
    }

    public void registerContext(ApplicationContext context)
    {
        this.context = context;
    }
    
}
