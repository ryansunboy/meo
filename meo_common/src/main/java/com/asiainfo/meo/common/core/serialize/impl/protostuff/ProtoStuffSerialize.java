package com.asiainfo.meo.common.core.serialize.impl.protostuff;

import com.asiainfo.meo.common.core.serialize.Serialize;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtoStuffSerialize implements Serialize
{
    
    private static final Schema<ProtoMessageWrapper> schema = RuntimeSchema.createFrom(ProtoMessageWrapper.class);
    
    @SuppressWarnings("unchecked")
    public <T> T serialize(byte[] bytes, Class<T> clazz)
    {
        if(bytes == null || bytes.length == 0)
        {
            return null;
        }
        ProtoMessageWrapper wrapper = new ProtoMessageWrapper();
        ProtostuffIOUtil.mergeFrom(bytes, wrapper, schema);
        return (T) wrapper.getWrapper();
    }
    
    public byte[] deserialize(Object o)
    {
        if(o == null)
        {
            return null;
        }
        ProtoMessageWrapper wrapper = new ProtoMessageWrapper();
        wrapper.setWrapper(o);
        return ProtostuffIOUtil.toByteArray(wrapper, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }
    
    private static class ProtoMessageWrapper
    {
        private Object wrapper;
        
        public Object getWrapper()
        {
            return wrapper;
        }
        
        public void setWrapper(Object wrapper)
        {
            this.wrapper = wrapper;
        }
        
    }
    
}