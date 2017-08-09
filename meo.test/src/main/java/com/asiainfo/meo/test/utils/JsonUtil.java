package com.asiainfo.meo.test.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil
{
private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static
    {
        // 忽略为null的属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略JSON对象中存在JAVA对象中不存在的属性
//        MAPPER.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        // 设置统一时间格式
    }
    
    /**  
     * 对象转换成JSON字符串  
     *   
     * @param obj  
     *            需要转换的对象  
     * @return 对象的string字符  
     * @throws JsonProcessingException 
     */  
    public static String toJson(Object obj) throws JsonProcessingException {   
        return MAPPER.writeValueAsString(obj);
       
    }   
  
    /**  
     * JSON字符串转换成对象  
     *   
     * @param jsonString  
     *            需要转换的字符串  
     * @param type  
     *            需要转换的对象类型  
     * @return 对象  
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */  
   
    public static <T> T fromJson(String jsonString, Class<T> type) throws JsonParseException, JsonMappingException, IOException {   
        
        
        return MAPPER.readValue(jsonString, type);
    }   
  

}
