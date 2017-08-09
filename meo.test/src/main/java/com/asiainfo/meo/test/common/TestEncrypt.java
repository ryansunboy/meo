package com.asiainfo.meo.test.common;

import java.io.File;

import org.junit.Test;

import com.asiainfo.meo.common.core.encrypt.Encrypt;
import com.asiainfo.meo.common.core.encrypt.impl.DefaultEncryptImpl;

public class TestEncrypt
{
    @Test
    public void testAes()
    {
        String ori = "ruanming";
        Encrypt encrypt = new DefaultEncryptImpl();
        String encryptValue = encrypt.encrypt(ori);
        System.out.println(encryptValue);
       String deValue = encrypt.decrypt(encryptValue);
       System.out.println("deValue : " + deValue);
    }
    
    @Test
    public void testFile()
    {
        File f = new File("1.txt");
        System.out.println(f.getAbsolutePath());
    }
}
