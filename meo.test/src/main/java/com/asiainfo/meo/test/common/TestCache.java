package com.asiainfo.meo.test.common;

import org.junit.Test;

import com.asiainfo.meo.common.core.cache.local.LocalCacheManager;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;

public class TestCache
{
    @Test
    public void testLocalCache()
    {
        LocalCacheManager localCacheManager = ServiceLocatorFactory.getService(LocalCacheManager.class);
        localCacheManager.getCache("cache").put("1", "2");
        System.out.println(localCacheManager.getCache("cache").get("1",Object.class));
    }
}
