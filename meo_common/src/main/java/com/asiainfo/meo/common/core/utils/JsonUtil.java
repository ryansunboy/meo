package com.asiainfo.meo.common.core.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonUtil
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static
    {
        // 忽略为null的属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        // 忽略JSON对象中存在JAVA对象中不存在的属性
        // MAPPER.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        // 设置统一时间格式
    }
    
    private JsonUtil()
    {
    }
    
    public static String writeMapAsString(Map<?, ?> map) throws IOException
    {
        return writeObjectAsString(map);
    }
    
    public static String writeObjectAsString(Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof String)
        {
            return (String) object;
        }
        try
        {
            return MAPPER.writeValueAsString(object);
        }
        catch(JsonProcessingException e)
        {
            throw new RuntimeException("prase object to json failure" + e);
        }
    }
    
    public static <T> T writeStringAsObject(String s, Class<T> type) throws IOException
    {
        if (StringUtil.isBlank(s))
        {
            return null;
        }
        return MAPPER.readValue(s, type);
    }
    
    public static <T> T writeStringAsRawObject(String s, Class<T> type) throws IOException
    {
        if (StringUtil.isBlank(s))
        {
            return null;
        }
        return MAPPER.readValue(s, new TypeReference<T>()
        {
        });
    }
    
    public static ObjectMapper mapper()
    {
        return MAPPER;
    }
}
