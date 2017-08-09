package com.asiainfo.meo.common.core.serialize.impl.jvm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.asiainfo.meo.common.core.serialize.Serialize;

public class JvmSerialize implements Serialize
{
    public <T> T serialize(byte[] bytes, Class<T> clazz)
    {
        if(bytes == null || bytes.length == 0)
        {
            return null;
        }
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (T) ois.readObject();
        }
        catch(IOException ex)
        {
            throw new IllegalArgumentException("Failed to deserialize object", ex);
        }
        catch(ClassNotFoundException ex)
        {
            throw new IllegalStateException("Failed to deserialize object type", ex);
        }
    }
    
    public byte[] deserialize(Object o)
    {
        if (o == null)
        {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
        }
        catch(IOException ex)
        {
            throw new IllegalArgumentException("Failed to serialize object of type: " + o.getClass(), ex);
        }
        return baos.toByteArray();
    }
    
}
