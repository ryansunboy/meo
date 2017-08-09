package com.asiainfo.meo.meo.notification.push.app.model.vo;

import java.util.List;

public class NotifyAndroidMessageVO
{
    private List<String> devices;
    
    private String       apiKey;
    
    private String       content;
    
    public List<String> getDevices()
    {
        return devices;
    }
    
    public void setDevices(List<String> devices)
    {
        this.devices = devices;
    }
    
    public String getApiKey()
    {
        return apiKey;
    }
    
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
}
