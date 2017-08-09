package com.asiainfo.meo.common.core.utils;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class SignUtil
{
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
    private SignUtil()
    {
        
    }
    
    public static String md5Signature(TreeMap<String, String> params, String secretKey, String body)
    {
        String result = null;
        StringBuilder signParams = getSignParams(params, secretKey, body);
        if (signParams == null)
        {
            return null;
        }
        signParams.append(secretKey);
        
        try
        {
            System.out.println(signParams.toString());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            result = byte2hex(md5.digest(signParams.toString().getBytes("utf-8")));
        }
        catch(Exception e)
        {
            throw new RuntimeException("sign error");
        }
        return result;
        
    }
    
    private static String byte2hex(byte[] b)
    {
        return String.valueOf(encodeHex(b));
    }
    
    private static StringBuilder getSignParams(TreeMap<String, String> params, String secretKey, String body)
    {
        if (params == null)
        {
            return null;
        }
        StringBuilder secret = new StringBuilder(secretKey);
        Map<String, String> signParams = new TreeMap<String, String>(params);
        Set<Entry<String, String>> entries = signParams.entrySet();
        for (Entry<String, String> entry : entries)
        {
            secret.append(entry.getKey()).append(entry.getValue());
        }
        if (!isBlank(body))
        {
            secret.append(body);
        }
        return secret;
    }
    
    private static char[] encodeHex(byte[] bytes)
    {
        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i += 2)
        {
            byte b = bytes[(i / 2)];
            chars[i] = HEX_CHARS[(b >>> 4 & 0xF)];
            chars[(i + 1)] = HEX_CHARS[(b & 0xF)];
        }
        return chars;
    }
    
    public static boolean isBlank(CharSequence cs)
    {
        int strLen;
        if ((cs == null) || ((strLen = cs.length()) == 0))
        {
            return true;
        }
        for (int i = 0; i < strLen; i++ )
        {
            if (!Character.isWhitespace(cs.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    
}
