package com.asiainfo.meo.customer.level.app.model.entity;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

/**
 * CmLevelDef generated by hbm2java
 */
public class LevelDef implements java.io.Serializable
{
    
    private static final long serialVersionUID = 1728228710046382753L;
    
    public static final int   SPARK            = 1;
    
    public static final int   NOVA             = 2;
    
    public static final int   AKIRA            = 3;
    
    public static final int   CUSTOMER_LEVEL   = 1;
    
    public static final int   PARTNER_LEVEL    = 2;
    
    public static final int   OTHER_LEVEL      = 3;
    
    public static final int   STS_VALID        = 0;
    
    public static final int   STS_INVALID      = 1;
    
    private int               levelId;
    @NotNull(groups = {Update.class, Insert.class })
    private String            levelName;
    @NotNull(groups = {Update.class, Insert.class })
    private long              minValue;
    @NotNull(groups = {Update.class, Insert.class })
    private Long              maxValue;
    @NotNull(groups = {Update.class, Insert.class })
    private long              operatorId;
    private String            icon;
    private Integer           sts;
    private Timestamp         createDate;
    @NotNull(groups = {Update.class })
    private Timestamp         modifyDate;
    private Integer           levelType;
    private Integer           period;
    private Integer           periodUnit;
    
    public boolean isCustomerLevel()
    {
        return CUSTOMER_LEVEL== getLevelType();
    }
    
    public boolean isPartnerLevel()
    {
        return PARTNER_LEVEL== getLevelType();
    }
    
    public boolean isOtherLevel()
    {
        return OTHER_LEVEL== getLevelType();
    }
    
    public boolean isLevel_Type()
    {
        return isCustomerLevel()|| isPartnerLevel()|| isOtherLevel();
    }
    
    public int getLevelId()
    {
        return this.levelId;
    }
    
    public void setLevelId(int levelId)
    {
        this.levelId = levelId;
    }
    
    public String getLevelName()
    {
        return this.levelName;
    }
    
    public void setLevelName(String levelName)
    {
        this.levelName = levelName;
    }
    
    public long getMinValue()
    {
        return this.minValue;
    }
    
    public void setMinValue(long minValue)
    {
        this.minValue = minValue;
    }
    
    public Long getMaxValue()
    {
        return this.maxValue;
    }
    
    public void setMaxValue(Long maxValue)
    {
        this.maxValue = maxValue;
    }
    
    public long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    public String getIcon()
    {
        return this.icon;
    }
    
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Integer getLevelType()
    {
        return this.levelType;
    }
    
    public void setLevelType(Integer levelType)
    {
        this.levelType = levelType;
    }
    
    public Integer getPeriod()
    {
        return period;
    }
    
    public void setPeriod(Integer period)
    {
        this.period = period;
    }
    
    public Integer getPeriodUnit()
    {
        return periodUnit;
    }
    
    public void setPeriodUnit(Integer periodUnit)
    {
        this.periodUnit = periodUnit;
    }
    
}
