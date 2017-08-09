package com.asiainfo.meo.customer.service.entity.vo;

public class CmDeviceVO
{
    private Integer deviceType;
    private String  deviceToken;
    private String  osVersion;
    private String  udid;
    private Integer osType;
    
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
