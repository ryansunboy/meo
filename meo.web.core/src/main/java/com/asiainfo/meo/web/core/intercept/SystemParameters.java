/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.web.core.intercept;                                                                                                                                                                                                                                                                       

import java.io.Serializable;
                                                                                                                                                                                                                                                                                              
public class SystemParameters implements Serializable
{
    private static final long serialVersionUID = 4399528812606844544L;
    
    private String format = "json";
    
    private String method;
    
    private String accessToken;
    
    private int v;
    
    private long timestamp;
    
    private String signature;

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public int getV()
    {
        return v;
    }

    public void setV(int v)
    {
        this.v = v;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }
    
}
