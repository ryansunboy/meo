package com.asiainfo.meo.web.passport.login.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class UIDeviceVO
{
    @NotNull
    private Integer deviceType;
    @NotEmpty
    private String  deviceToken;
    @NotEmpty
    private String  osVersion;
    @NotNull
    private Integer osType;
    @NotEmpty
    private String  udid;
    
    
    public Integer getOsType()
    {
        return osType;
    }

    public void setOsType(Integer osType)
    {
        this.osType = osType;
    }

    public Integer getDeviceType()
    {
        return deviceType;
    }
    
    public void setDeviceType(Integer deviceType)
    {
        this.deviceType = deviceType;
    }
    
    public String getDeviceToken()
    {
        return deviceToken;
    }
    
    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }
    
    public String getOsVersion()
    {
        return osVersion;
    }
    
    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }
    
    public String getUdid()
    {
        return udid;
    }
    
    public void setUdid(String udid)
    {
        this.udid = udid;
    }
    
}
