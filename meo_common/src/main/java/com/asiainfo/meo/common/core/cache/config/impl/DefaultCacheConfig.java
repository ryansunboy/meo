/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.core.cache.config.impl;

import com.asiainfo.meo.common.core.cache.config.CacheConfig;

/**
 * @Description: TODO(这里用一句oi话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author AI
 * @Date 2015-4-7
 */
public class DefaultCacheConfig implements CacheConfig
{
    private long expiration;
    
    private String cacheName;
    
    private float expirationFactor;
    
    public long getExpiration()
    {
        return expiration;
    }
    
    public void setExpiration(long expiration)
    {
        this.expiration = expiration;
    }
    
    public String getCacheName()
    {
        return cacheName;
    }
    
    public void setCacheName(String cacheName)
    {
        this.cacheName = cacheName;
    }

    public float getExpirationFactor()
    {
        return expirationFactor;
    }

    public void setExpirationFactor(float expirationFactor)
    {
        this.expirationFactor = expirationFactor;
    }
}
