package com.asiainfo.meo.customer.device.app.model.vo;

public class DeviceInfoVO implements java.io.Serializable
{
    
    private long               deviceId;
    
    private String             deviceToken;
    
    private int                deviceType;
    
    private String             osVersion;
    
    private long               userId;
    
    private String             udid;
    
    private Integer            osType;

    public long getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(long deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceToken()
    {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }

    public int getDeviceType()
    {
        return deviceType;
    }

    public void setDeviceType(int deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getOsVersion()
    {
        return osVersion;
    }

    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getUdid()
    {
        return udid;
    }

    public void setUdid(String udid)
    {
        this.udid = udid;
    }

    public Integer getOsType()
    {
        return osType;
    }

    public void setOsType(Integer osType)
    {
        this.osType = osType;
    }
}
