package com.asiainfo.meo.common.core.cache;

import java.util.Collection;

public interface Cache
{
    /**
      * @Description: 键值对存放到缓存中，其中 catalog是缓存目录，KEY为当前缓存的KEY，VALUE是当前缓存的值
      * @param catalog 缓存目录
      * @param key 
      * @param value
     */
    void put(String catalog,String key,Object value);
    
    void putIfAbsent(String catalog,String key,Object value);
    
    <T> T get(String catalog,String key,Class<T> clazz);

    void evict(Object key);
    
    void clear();
    
    Collection<String> getCacheNames();
}
