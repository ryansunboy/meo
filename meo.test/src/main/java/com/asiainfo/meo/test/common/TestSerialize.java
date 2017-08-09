package com.asiainfo.meo.test.common;

import java.io.IOException;

import org.junit.Test;

import com.asiainfo.meo.common.core.serialize.Serialize;
import com.asiainfo.meo.common.core.serialize.impl.json.JacksonSerialize;
import com.asiainfo.meo.common.core.serialize.impl.jvm.JvmSerialize;
import com.asiainfo.meo.common.core.serialize.impl.protostuff.ProtoStuffSerialize;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.user.app.model.entity.Address;
import com.asiainfo.meo.user.app.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

public class TestSerialize
{
    @Test
    public void testProtoStuff()
    {
        Serialize s = new ProtoStuffSerialize();
        testSer(s);
    }
    
    @Test
    public void testJvm()
    {
        Serialize s = new JvmSerialize();
        testSer(s);
    }
    
    @Test
    public void testJson()
    {
        Serialize s = new JacksonSerialize();
        testSer(s);
    }
    
    @Test
    public void testJsonEmpty() throws IOException
    {
        User u = createUser();
        System.out.println(JsonUtil.writeObjectAsString(u));
    }
    
    @Test
    public void testJsonSchema()  throws IOException
    {
        ObjectMapper m = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        m.acceptJsonFormatVisitor(m.constructType(User.class), visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        System.out.println(jsonSchema);
    }
    
    
    private void testSer(Serialize s )
    {
        User u = createUser();
        byte[] b = s.deserialize(u);
        User u2 = s.serialize(b,User.class);
        
        System.out.println(u2.getUserId());
        System.out.println(b.length);
        long start = System.currentTimeMillis();
//        System.out.println(new String(b));
        for(int i = 0; i < 1000000; i++)
        {
            s.deserialize(u);
            s.serialize(b,User.class);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    
    private User createUser()
    {
        User u = new User();
        u.setUserId(1001);
        u.setUserName("raymond");
        Address a = new Address();
        a.setAddessId(111);
//        u.getAddress().add(a);
        return u;
    }
}
