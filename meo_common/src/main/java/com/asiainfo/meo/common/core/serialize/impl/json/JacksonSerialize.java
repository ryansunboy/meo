package com.asiainfo.meo.common.core.serialize.impl.json;

import java.io.IOException;

import com.asiainfo.meo.common.core.serialize.Serialize;
import com.asiainfo.meo.common.core.utils.JsonUtil;

public class JacksonSerialize implements Serialize
{
    
    public <T> T serialize(byte[] bytes, Class<T> clazz)
    {
        try
        {
            if(bytes == null || bytes.length == 0)
            {
                return null;
            }
            return JsonUtil.mapper().readValue(bytes, clazz);
        }
        catch(IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
    
    public byte[] deserialize(Object o)
    {
        try
        {
            if(o == null)
            {
                return null;
            }
            return JsonUtil.mapper().writeValueAsBytes(o);
        }
        catch(IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
    
}
