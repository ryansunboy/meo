package com.asiainfo.meo.web.campaign.model.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UICampaignCommonVO
{
    private Long                    campaignId;
    
    @NotEmpty
    private String                  campaignName;
    
    @NotNull
    private Integer                 campaignType;
    
    @NotNull
    private Long                    startDate;
    
    @NotNull
    private Long                    endDate;
    
    @NotNull
    private Long                    validDate;
    
    private String                  iconUrl;
    
    private Integer                 categoryId;
    
    private String                  description;
    
    private Integer                 participant;
    
    private String                  campaignUrl;
    
    private List<UICampaignPriceVO> campaignPrices = new ArrayList<UICampaignPriceVO>();
    
    @NotNull
    private Long                    partnerId;
    
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
    
    public List<UICampaignPriceVO> getCampaignPrices()
    {
        return campaignPrices;
    }
    
    public void setCampaignPrices(List<UICampaignPriceVO> campaignPrices)
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
