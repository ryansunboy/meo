package com.asiainfo.meo.customer.setting.app.model.vo;

public class SettingVO
{
    private Integer settingId;
    private Integer settingType;
    private String  settingName;
    private Integer settingValue;
    private String  settingValueName;
    
    public String getSettingValueName()
    {
        return settingValueName;
    }

    public void setSettingValueName(String settingValueName)
    {
        this.settingValueName = settingValueName;
    }

    public Integer getSettingId()
    {
        return settingId;
    }
    
    public void setSettingId(Integer settingId)
    {
        this.settingId = settingId;
    }
    
    public Integer getSettingType()
    {
        return settingType;
    }
    
    public void setSettingType(Integer settingType)
    {
        this.settingType = settingType;
    }
    
    public String getSettingName()
    {
        return settingName;
    }
    
    public void setSettingName(String settingName)
    {
        this.settingName = settingName;
    }
    
    public Integer getSettingValue()
    {
        return settingValue;
    }
    
    public void setSettingValue(Integer settingValue)
    {
        this.settingValue = settingValue;
    }
}
