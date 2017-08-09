package com.asiainfo.meo.campaign.profile.app.model.vo;

import java.util.ArrayList;
import java.util.List;

public class CampaignCommonVO
{
    private Long                  campaignId;
    
    private String                campaignName;
    
    private Integer               campaignType;
    
    private Long                  startDate;
    
    private Long                  endDate;
    
    private Long                  validDate;
    
    private String                iconUrl;
    
    private Integer               categoryId;
    
    private String                description;
    
    private Integer               participant;
    
    private String                campaignUrl;
    
    private List<CampaignPriceVO> campaignPrices = new ArrayList<CampaignPriceVO>();
    
    private Long                  partnerId;
    
    public Long getCampaignId()
    {
        return campaignId;
    }
    
    public void setCampaignId(Long campaignId)
    {
        this.campaignId = campaignId;
    }
    
    public String getCampaignName()
    {
        return campaignName;
    }
    
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }
    
    public Integer getCampaignType()
    {
        return campaignType;
    }
    
    public void setCampaignType(Integer campaignType)
    {
        this.campaignType = campaignType;
    }
    
    public Long getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(Long startDate)
    {
        this.startDate = startDate;
    }
    
    public Long getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(Long endDate)
    {
        this.endDate = endDate;
    }
    
    public Long getValidDate()
    {
        return validDate;
    }
    
    public void setValidDate(Long validDate)
    {
        this.validDate = validDate;
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
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public Integer getParticipant()
    {
        return participant;
    }
    
    public void setParticipant(Integer participant)
    {
        this.participant = participant;
    }
    
    public String getCampaignUrl()
    {
        return campaignUrl;
    }
    
    public void setCampaignUrl(String campaignUrl)
    {
        this.campaignUrl = campaignUrl;
    }
    
    public List<CampaignPriceVO> getCampaignPrices()
    {
        return campaignPrices;
    }
    
    public void setCampaignPrices(List<CampaignPriceVO> campaignPrices)
    {
        this.campaignPrices = campaignPrices;
    }
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
}
