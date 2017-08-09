/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.cache.distributed;                                                                                                                                                                                                                                                                       

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

import com.asiainfo.meo.common.core.cache.config.CacheConfig;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-4-7                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class DistributedCacheManager implements InitializingBean,CacheManager
{
    private static final float defaultExpirationFactor = 2.0f;
    @Resource
    private RedisCacheManager redisCacheManager;
    /* (Not Javadoc)                                         
      * <p>Title: afterPropertiesSet</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @throws Exception                                                                                                                                                                                                                                                                                   
      * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()                                                                                                                                                                                                                                                                      
      */
    @Override
    public void afterPropertiesSet() throws Exception
    {
//        DistributedCache distributedCache = ;
        Map<String,CacheConfig> cacheConfigs = ServiceLocatorFactory.getServiceOfType(CacheConfig.class);
        if(ValidateUtil.isNotEmpty(cacheConfigs))
        {
            Map<String,Long> expires = new HashMap<String, Long>(cacheConfigs.size());
            for(Map.Entry<String, CacheConfig> entry : cacheConfigs.entrySet())
            {
                CacheConfig config = entry.getValue();
                float factor = config.getExpirationFactor();
                if (factor <= 1 || Float.isNaN(factor))
                {
                    factor = defaultExpirationFactor;
                }
                
                Float f = config.getExpiration() * factor;
                
                expires.put(config.getCacheName(), f.longValue());
            }
            redisCacheManager.setExpires(expires);
        }
    }
    /* (Not Javadoc)                                         
      * <p>Title: getCache</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param name
      * @return                                                                                                                                                                                                                                                                                   
      * @see org.springframework.cache.CacheManager#getCache(java.lang.String)                                                                                                                                                                                                                                                                      
      */
    @Override
    public Cache getCache(String name)
    {
         return redisCacheManager.getCache(name);
    }
    /* (Not Javadoc)                                         
      * <p>Title: getCacheNames</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @return                                                                                                                                                                                                                                                                                   
      * @see org.springframework.cache.CacheManager#getCacheNames()                                                                                                                                                                                                                                                                      
      */
    @Override
    public Collection<String> getCacheNames()
    {
        // TODO Auto-generated method stub                                     
         return redisCacheManager.getCacheNames();
    }
    
}
