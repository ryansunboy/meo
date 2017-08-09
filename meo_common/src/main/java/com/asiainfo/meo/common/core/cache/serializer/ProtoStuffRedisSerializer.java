/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.cache.serializer;                                                                                                                                                                                                                                                                       

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.asiainfo.meo.common.core.serialize.Serialize;
import com.asiainfo.meo.common.core.serialize.impl.protostuff.ProtoStuffSerialize;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-4-8                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class ProtoStuffRedisSerializer<T> implements RedisSerializer<T>
{
    private Serialize protoStuffSerializer = new ProtoStuffSerialize();
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
         return protoStuffSerializer.deserialize(t);
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
         return (T)protoStuffSerializer.serialize(bytes, Object.class);
    }
    
}
