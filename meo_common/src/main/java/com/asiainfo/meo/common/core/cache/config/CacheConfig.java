/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.cache.config;                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-4-7                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public interface CacheConfig
{
    long getExpiration();
    
    String getCacheName();
    
    float getExpirationFactor();
}
