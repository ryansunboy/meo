package com.asiainfo.meo.common.core.cache.distributed;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.cache.CacheManager;

import com.asiainfo.meo.common.core.cache.Cache;
import com.asiainfo.meo.common.core.cache.type.TypeContext;

public class DistributedCache implements Cache
{
    
    @Resource
    private CacheManager distributeCacheManager;
    /* (Not Javadoc)                                         
      * <p>Title: put</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param catalog
      * @param key
      * @param value                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.CacheManager1#put(java.lang.String, java.lang.String, java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void put(String catalog, String key, Object value)
    {
        this.distributeCacheManager.getCache(catalog).put(key, value);                                   
         
    }
    
    /* (Not Javadoc)                                         
     * <p>Title: putIfAbsent</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param catalog
     * @param key
     * @param value                                                                                                                                                                                                                                                                                   
     * @see com.asiainfo.meo.common.core.cache.CacheManager1#putIfAbsent(java.lang.String, java.lang.String, java.lang.Object)                                                                                                                                                                                                                                                                      
     */
   @Override
   public void putIfAbsent(String catalog, String key, Object value)
   {
        this.distributeCacheManager.getCache(catalog).putIfAbsent(key, value);
   }
    /* (Not Javadoc)                                         
      * <p>Title: get</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param catalog
      * @param key
      * @param clazz
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.CacheManager1#get(java.lang.String, java.lang.String, java.lang.Class)                                                                                                                                                                                                                                                                      
      */
    @Override
    public <T> T get(String catalog, String key, Class<T> clazz)
    {
        try
        {
           TypeContext.setType(clazz);
            return this.distributeCacheManager.getCache(catalog).get(key, clazz);
        }
        finally
        {
            TypeContext.remove();
        }
    }

    /* (Not Javadoc)                                         
      * <p>Title: evict</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param key                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.cache.CacheManager1#evict(java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void evict(Object key)
    {
//        return this.distributeCacheManager.getCache(catalog).                           
         
    }

    /* (Not Javadoc)                                         
      * <p>Title: clear</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
      * @see com.asiainfo.meo.common.core.cache.CacheManager1#clear()                                                                                                                                                                                                                                                                      
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
      * @see com.asiainfo.meo.common.core.cache.CacheManager1#getCacheNames()                                                                                                                                                                                                                                                                      
      */
    @Override
    public Collection<String> getCacheNames()
    {
          return this.distributeCacheManager.getCacheNames();
    }
}
