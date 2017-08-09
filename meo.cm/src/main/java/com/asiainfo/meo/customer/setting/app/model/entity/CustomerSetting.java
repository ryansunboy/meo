package com.asiainfo.meo.customer.setting.app.model.entity;

// Generated 2015-3-4 13:53:08 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class CustomerSetting implements java.io.Serializable
{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long   serialVersionUID = 1L;
    
    public final static Integer STS_USED         = 0;
    
    public final static Integer STS_UNUSED       = 1;
    
    private long                id;
    
    @NotNull(groups = {Update.class, Insert.class })
    private long                custId;
    
    private Timestamp           modifyDate;
    
    private Timestamp           createDate;
    
    private Integer             sts;
    
    private int                 settingId;
    
    private Integer             settingValue;
    private Integer             settingType;
    
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Integer getSettingType()
    {
        return settingType;
    }

    public void setSettingType(Integer settingType)
    {
        this.settingType = settingType;
    }

    public long getCustId()
    {
        return this.custId;
    }
    
    public void setCustId(long custId)
    {
        this.custId = custId;
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
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Integer getSettingValue()
    {
        return this.settingValue;
    }
    
    public void setSettingValue(Integer settingValue)
    {
        this.settingValue = settingValue;
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
    
    public void setSettingId(int settingId)
    {
        this.settingId = settingId;
    }
    
    public int getSettingId()
    {
        return settingId;
    }
}
