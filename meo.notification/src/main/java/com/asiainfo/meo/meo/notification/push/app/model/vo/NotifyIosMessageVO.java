package com.asiainfo.meo.meo.notification.push.app.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NotifyIosMessageVO
{
    @Valid
    @NotNull
    private List<String> deviceTokens;
    @NotNull
    private String       content;
    @NotNull
    private int          badge;
    private String       sound;
    @NotNull
    private String       certificatePath;
    @NotNull
    private String       certificatePwd;
    
    private boolean      isTest=false;
    
    private boolean      isGroup=true;
    
    
    public boolean isGroup()
    {
        return isGroup;
    }

    public void setGroup(boolean isGroup)
    {
        this.isGroup = isGroup;
    }

    public boolean isTest()
    {
        return isTest;
    }

    public void setTest(boolean isTest)
    {
        this.isTest = isTest;
    }

    public List<String> getDeviceTokens()
    {
        return deviceTokens;
    }
    
    public void setDeviceTokens(List<String> deviceTokens)
    {
        this.deviceTokens = deviceTokens;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public int getBadge()
    {
        return badge;
    }
    
    public void setBadge(int badge)
    {
        this.badge = badge;
    }
    
    public String getSound()
    {
        return sound;
    }
    
    public void setSound(String sound)
    {
        this.sound = sound;
    }
    
    public String getCertificatePath()
    {
        return certificatePath;
    }
    
    public void setCertificatePath(String certificatePath)
    {
        this.certificatePath = certificatePath;
    }
    
    public String getCertificatePwd()
    {
        return certificatePwd;
    }
    
    public void setCertificatePwd(String certificatePwd)
    {
        this.certificatePwd = certificatePwd;
    }
    
}
