package com.asiainfo.meo.common.core.cache.local;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.asiainfo.meo.common.core.cache.Cache;

public class LocalCache implements Cache
{
    @Resource
    private ConcurrentMapCacheManager concurrentCacheManager;
    /* (Not Javadoc)                                         
      * <p>Title: put</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param catalog
      * @param key
      * @param value                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.Cache#put(java.lang.String, java.lang.String, java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void put(String catalog, String key, Object value)
    {
        this.concurrentCacheManager.getCache(catalog).put(key, value);                                
         
    }

    /* (Not Javadoc)                                         
      * <p>Title: putIfAbsent</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param catalog
      * @param key
      * @param value                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.Cache#putIfAbsent(java.lang.String, java.lang.String, java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void putIfAbsent(String catalog, String key, Object value)
    {
        this.concurrentCacheManager.getCache(catalog).putIfAbsent(key, value);                                     
    }

    /* (Not Javadoc)                                         
      * <p>Title: get</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param catalog
      * @param key
      * @param clazz
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.Cache#get(java.lang.String, java.lang.String, java.lang.Class)                                                                                                                                                                                                                                                                      
      */
    @Override
    public <T> T get(String catalog, String key, Class<T> clazz)
    {
         return  this.concurrentCacheManager.getCache(catalog).get(key, clazz);
    }

    /* (Not Javadoc)                                         
      * <p>Title: evict</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param key                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.Cache#evict(java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void evict(Object key)
    {
        // TODO Auto-generated method stub                                     
         
    }

    /* (Not Javadoc)                                         
      * <p>Title: clear</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
      * @see com.asiainfo.meo.common.core.cache.Cache#clear()                                                                                                                                                                                                                                                                      
      */
    @Override
    public void clear()
    {
        // TODO Auto-generated method stub                                     
         
    }

    /* (Not Javadoc)                                         
      * <p>Title: getCacheNames</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.Cache#getCacheNames()                                                                                                                                                                                                                                                                      
      */
    @Override
    public Collection<String> getCacheNames()
    {
        // TODO Auto-generated method stub                                     
         return null;
    }
}
