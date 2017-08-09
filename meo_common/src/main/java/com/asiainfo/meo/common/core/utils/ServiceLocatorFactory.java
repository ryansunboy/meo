package com.asiainfo.meo.common.core.utils;

import java.util.Map;

import com.asiainfo.meo.common.core.container.RegistryServiceBean;

public class ServiceLocatorFactory
{
    public static <T> T getService(String id)
    {
        return RegistryServiceBean.getInstance().getService(id);
    }
    
    public static <T> T getService(String id, Class<T> clazz)
    {
        return RegistryServiceBean.getInstance().getService(id, clazz);
    }
    
    public static <T> T getService(Class<T> clazz)
    {
        return RegistryServiceBean.getInstance().getService(clazz);
    }
    
    public static <T> Map<String,T> getServiceOfType(Class<T> clazz)
    {
        return RegistryServiceBean.getInstance().getServiceOfType(clazz);
    }
    
}
