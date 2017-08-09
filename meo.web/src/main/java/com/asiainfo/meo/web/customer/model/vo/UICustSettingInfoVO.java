package com.asiainfo.meo.web.customer.model.vo;

import javax.validation.constraints.NotNull;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UICustSettingInfoVO
{
    @NotNull(groups={Insert.class,Update.class})
    private Integer settingId;
    @NotNull(groups={Insert.class,Update.class})
    private Integer settingValue;
    private Integer settingType;
    private String  settingName;
    private String  settingValueName;
    
    public String getSettingValueName()
    {
        return settingValueName;
    }

    public void setSettingValueName(String settingValueName)
    {
        this.settingValueName = settingValueName;
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

    public Integer getSettingId()
    {
        return settingId;
    }
    
    public void setSettingId(Integer settingId)
    {
        this.settingId = settingId;
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
