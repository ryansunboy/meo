package com.asiainfo.meo.common.core.encrypt.impl;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.asiainfo.meo.common.core.encrypt.Encrypt;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.Base64;

public class DefaultEncryptImpl implements Encrypt
{
    private String algorithm = "AES";
    
    private String seed = "e7d3ee32f0760abc";// AES算法SEED需要16长度的倍数
    
    @Override
    public String encrypt(String ori) 
    {
        Key key = generateKey();
        try
        {
            Cipher c = Cipher.getInstance(getAlgorithm());
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptValue = c.doFinal(ori.getBytes());
            
            return new String(Base64.getEncoder().encode(encryptValue));
        }
        catch (Exception e)
        {
            throw new MeoException("", e);// TODO
        }
    }
    
    @Override
    public String decrypt(String encrypt)
    {
        Key key = generateKey();
        try
        {
            Cipher c = Cipher.getInstance(getAlgorithm());
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] base64Value = Base64.getDecoder().decode(encrypt);  
            byte[] decryptValue = c.doFinal(base64Value);
            return new String(decryptValue);
        }
        catch (Exception e)
        {
            throw new MeoException("", e);// TODO
        }
    }
    private Key generateKey() 
    {  
        Key key = new SecretKeySpec(getSeed().getBytes(), getAlgorithm());  
        return key;  
    }
    
    public String getAlgorithm()
    {
        return algorithm;
    }

    public void setAlgorithm(String algorithm)
    {
        this.algorithm = algorithm;
    }

    public String getSeed()
    {
        return seed;
    }

    public void setSeed(String seed)
    {
        this.seed = seed;
    }
    
}
