package com.asiainfo.meo.campaign.profile.app.model.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * CmHotCampaignRank entity
 */

public class HotCampaignRank implements java.io.Serializable
{
    
    private static final long serialVersionUID = 7345095863586863514L;
    
    private long              campaignId;
    
    private Timestamp         refreshDate;
    
    private String            campaignName;
    
    private String            campaignNo;
    
    private Integer           participateCount;
    
    private String            iconUrl;
    
    private Integer           categoryId;
    
    private long              partnerId;
    
    private Integer           inventory;
    
    private Integer           campaignType;
    
    public long getCampaignId()
    {
        return this.campaignId;
    }
    
    public void setCampaignId(long campaignId)
    {
        this.campaignId = campaignId;
    }
    
    public Timestamp getRefreshDate()
    {
        return refreshDate;
    }

    public void setRefreshDate(Timestamp refreshDate)
    {
        this.refreshDate = refreshDate;
    }

    public String getCampaignName()
    {
        return this.campaignName;
    }
    
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }
    
    public String getCampaignNo()
    {
        return this.campaignNo;
    }
    
    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }
    
    public Integer getParticipateCount()
    {
        return this.participateCount;
    }
    
    public void setParticipateCount(Integer participateCount)
    {
        this.participateCount = participateCount;
    }
    
    public String getIconUrl()
    {
        return this.iconUrl;
    }
    
    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }
    
    public Integer getCategoryId()
    {
        return this.categoryId;
    }
    
    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }
    
    public long getPartnerId()
    {
        return this.partnerId;
    }
    
    public void setPartnerId(long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public Integer getInventory()
    {
        return this.inventory;
    }
    
    public void setInventory(Integer inventory)
    {
        this.inventory = inventory;
    }
    
    public Integer getCampaignType()
    {
        return this.campaignType;
    }
    
    public void setCampaignType(Integer campaignType)
    {
        this.campaignType = campaignType;
    }
    
}