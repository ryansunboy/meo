package com.asiainfo.meo.campaign.task.app.model.entity;

// Generated 2015-4-20 14:45:47 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmCampaignLimit generated by hbm2java
 */
public class CampaignLimit implements java.io.Serializable
{
    private static final long serialVersionUID = 4653178977135389680L;
    
    private long              id;
    
    private long              campaignId;
    
    private Integer           sts;
    
    private String            description;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private Integer           limitType;
    
    private Long              limitValue;
    
    private Integer           limitUnit;
    
    public long getId()
    {
        return this.id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public long getCampaignId()
    {
        return this.campaignId;
    }
    
    public void setCampaignId(long campaignId)
    {
        this.campaignId = campaignId;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
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
    
    public Integer getLimitType()
    {
        return this.limitType;
    }
    
    public void setLimitType(Integer limitType)
    {
        this.limitType = limitType;
    }
    
    public Long getLimitValue()
    {
        return this.limitValue;
    }
    
    public void setLimitValue(Long limitValue)
    {
        this.limitValue = limitValue;
    }
    
    public Integer getLimitUnit()
    {
        return this.limitUnit;
    }
    
    public void setLimitUnit(Integer limitUnit)
    {
        this.limitUnit = limitUnit;
    }
    
}