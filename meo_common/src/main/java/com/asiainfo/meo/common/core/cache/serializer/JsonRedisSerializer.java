/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.cache.serializer;                                                                                                                                                                                                                                                                       

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.asiainfo.meo.common.core.cache.type.TypeContext;
import com.asiainfo.meo.common.core.serialize.Serialize;
import com.asiainfo.meo.common.core.serialize.impl.json.JacksonSerialize;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-4-8                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class JsonRedisSerializer<T> implements RedisSerializer<T>
{
    private Serialize jsonSeralize = new JacksonSerialize();
    /* (Not Javadoc)                                         
      * <p>Title: serialize</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param t
      * @return
      * @throws SerializationException                                                                                                                                                                                                                                                                                   
      * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public byte[] serialize(T t) throws SerializationException
    {
        // TODO Auto-generated method stub                                     
         return jsonSeralize.deserialize(t);
    }

    /* (Not Javadoc)                                         
      * <p>Title: deserialize</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param bytes
      * @return
      * @throws SerializationException                                                                                                                                                                                                                                                                                   
      * @see org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])                                                                                                                                                                                                                                                                      
      */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException
    {
        // TODO Auto-generated method stub                                     
         return jsonSeralize.serialize(bytes, ((Class<T>)TypeContext.get()));
    }
    
}
