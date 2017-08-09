package com.asiainfo.meo.customer.setting.app.model.entity;

// default package
// Generated 2015-3-4 13:56:26 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.customer.setting.app.constant.SettingErrorConstant;

public class SettingDef implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long   serialVersionUID     = -1831213471490291161L;
    
    public final static Integer SETTING_TYPE_PRIVACY = 1;
    
    public final static Integer SETTING_TYPE_CHANNEL = 2;
    
    public final static Integer SETTING_TYPE_FEATURE = 3;
    
    public final static String  SETTING_TYPE         = "10";
    
    public final static Integer STS_USED             = 0;
    
    public final static Integer STS_UNUSED           = 1;
    
    public final static Integer DEFAULT_VALUE_PUBLIC = 1;
    
    public final static Integer DEFAULT_VALUE_FRIEND = 2;
    
    public final static Integer DEFAULT_VALUE_ME     = 3;
    
    public final static Integer DEFAULT_VALUE_SMS    = 1;
    
    public final static Integer DEFAULT_VALUE_EMAIL  = 2;
    
    public final static Integer DEFAULT_VALUE_APP    = 3;
    
    public final static Integer DEFAULT_VALUE_ON     = 1;
    
    public final static Integer DEFAULT_VALUE_OFF    = 2;
    
    private int                 settingId;
    
    @NotNull(groups = {Update.class, Insert.class })
    private Integer             settingType;
    
    @NotNull(groups = {Update.class, Insert.class })
    private String              settingName;
    
    @NotNull(groups = {Update.class, Insert.class })
    private String              settingDesc;
    
    @NotNull(groups = {Update.class, Insert.class })
    private int                 sts;
    
    private Timestamp           modifyDate;
    
    private Timestamp           createDate;
    
    private Integer             defaultValue;
    
    private Long                operatorId;
    
    public void setSettingId(int settingId)
    {
        this.settingId = settingId;
    }
    
    public int getSettingId()
    {
        return settingId;
    }
    
    public Integer getSettingType()
    {
        return this.settingType;
    }
    
    public void setSettingType(Integer settingType)
    {
        this.settingType = settingType;
    }
    
    public String getSettingName()
    {
        return this.settingName;
    }
    
    public void setSettingName(String settingName)
    {
        this.settingName = settingName;
    }
    
    public String getSettingDesc()
    {
        return this.settingDesc;
    }
    
    public void setSettingDesc(String settingDesc)
    {
        this.settingDesc = settingDesc;
    }
    
    public int getSts()
    {
        return this.sts;
    }
    
    public void setSts(int sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Integer getDefaultValue()
    {
        return this.defaultValue;
    }
    
    public void setDefaultValue(Integer defaultValue)
    {
        this.defaultValue = defaultValue;
    }
    
    public Long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    public boolean isPrivacy()
    {
        return SETTING_TYPE_PRIVACY== getSettingType();
    }
    
    public boolean isChannel()
    {
        return SETTING_TYPE_CHANNEL== getSettingType();
    }
    
    public boolean isFeature()
    {
        return SETTING_TYPE_FEATURE== getSettingType();
    }
    
    public boolean isSetting_Type()
    {
        return isPrivacy()|| isChannel()|| isFeature();
    }
    
    public boolean isUsed()
    {
        return STS_USED== getSts();
    }
    
    public boolean isUnused()
    {
        return STS_UNUSED== getSts();
    }
    
    public boolean isStatus()
    {
        return isUsed()|| isUnused();
    }
    
    public boolean isPublic()
    {
        return DEFAULT_VALUE_PUBLIC== getDefaultValue();
    }
    
    public boolean isFriend()
    {
        return DEFAULT_VALUE_FRIEND== getDefaultValue();
    }
    
    public boolean isMe()
    {
        return DEFAULT_VALUE_ME== getDefaultValue();
    }
    
    public boolean isSms()
    {
        return DEFAULT_VALUE_SMS== getDefaultValue();
    }
    
    public boolean isEmail()
    {
        return DEFAULT_VALUE_EMAIL== getDefaultValue();
    }
    
    public boolean isApp()
    {
        return DEFAULT_VALUE_APP== getDefaultValue();
    }
    
    public boolean isOn()
    {
        return DEFAULT_VALUE_ON== getDefaultValue();
    }
    
    public boolean isOff()
    {
        return DEFAULT_VALUE_OFF== getDefaultValue();
    }
    
    public boolean isDefault_Value()
    {
        return isPublic()|| isFriend()|| isMe()|| isSms()|| isEmail()|| isApp()|| isOn()|| isOff();
    }
    
}
