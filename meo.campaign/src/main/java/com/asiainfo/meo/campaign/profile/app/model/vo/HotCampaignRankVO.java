package com.asiainfo.meo.campaign.profile.app.model.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * CmHotCampaignRank entity
 */

public class HotCampaignRankVO
{
    
    private Long      campaignId;
    
    private Timestamp refreshDate;
    
    private String    campaignName;
    
    private String    campaignNo;
    
    private Integer   participateCount;
    
    private String    iconUrl;
    
    private Integer   categoryId;
    
    private Long      partnerId;
    
    private Integer   inventory;
    
    private Integer   campaignType;

    public Long getCampaignId()
    {
        return campaignId;
    }

    public void setCampaignId(Long campaignId)
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
        return campaignName;
    }

    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }

    public String getCampaignNo()
    {
        return campaignNo;
    }

    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }

    public Integer getParticipateCount()
    {
        return participateCount;
    }

    public void setParticipateCount(Integer participateCount)
    {
        this.participateCount = participateCount;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }

    public Integer getInventory()
    {
        return inventory;
    }

    public void setInventory(Integer inventory)
    {
        this.inventory = inventory;
    }

    public Integer getCampaignType()
    {
        return campaignType;
    }

    public void setCampaignType(Integer campaignType)
    {
        this.campaignType = campaignType;
    }
    
}