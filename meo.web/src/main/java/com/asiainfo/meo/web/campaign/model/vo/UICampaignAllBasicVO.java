package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

public class UICampaignAllBasicVO
{
    private Long                     campaignId;
    
    private String                   campaignNo;
    
    private Integer                  campaginTypeId;
    
    private String                   campaginType;
    
    private Long                     campaginBeginDate;
    
    private Long                     campaginEndDate;
    
    private Integer                  categoryId;
    
    private String                   category;
    
    private Integer                  participant;
    
    private Integer                  remainParticipant;
    
    private String                   campaignUrl;
    
    private String                   description;
    
    private Long                     currentTaskId;
    
    private List<UICampaignTaskVO>   campaignTasks;
    
    private List<UICampaignDetailVO> campaignPropertys;
    
    private List<UICampaignPriceVO>  campaignPrices;
    
    // Attending the campaign can get a total number of coin
    private Integer                  totalCoins;
    
    // next step taskId
    private Long                     nextTaskId;
    
    // added by zhengzy 2015-9-10 15:45:22
    private String                   campaignName;
    
    private String                   iconUrl;
    
    private Long                     partnerId;
    
    private String                   partnerName;
    
    private String                   partnerAvatar;
    
    // added by zhengzy 2015-9-16 10:09:51
    private Long                     requireCoins;
    
    public Long getCampaignId()
    {
        return campaignId;
    }
    
    public void setCampaignId(Long campaignId)
    {
        this.campaignId = campaignId;
    }
    
    public String getCampaignNo()
    {
        return campaignNo;
    }
    
    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }
    
    public Integer getCampaginTypeId()
    {
        return campaginTypeId;
    }
    
    public void setCampaginTypeId(Integer campaginTypeId)
    {
        this.campaginTypeId = campaginTypeId;
    }
    
    public String getCampaginType()
    {
        return campaginType;
    }
    
    public void setCampaginType(String campaginType)
    {
        this.campaginType = campaginType;
    }
    
    public Long getCampaginBeginDate()
    {
        return campaginBeginDate;
    }
    
    public void setCampaginBeginDate(Long campaginBeginDate)
    {
        this.campaginBeginDate = campaginBeginDate;
    }
    
    public Long getCampaginEndDate()
    {
        return campaginEndDate;
    }
    
    public void setCampaginEndDate(Long campaginEndDate)
    {
        this.campaginEndDate = campaginEndDate;
    }
    
    public Integer getCategoryId()
    {
        return categoryId;
    }
    
    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public Integer getParticipant()
    {
        return participant;
    }
    
    public void setParticipant(Integer participant)
    {
        this.participant = participant;
    }
    
    public Integer getRemainParticipant()
    {
        return remainParticipant;
    }
    
    public void setRemainParticipant(Integer remainParticipant)
    {
        this.remainParticipant = remainParticipant;
    }
    
    public String getCampaignUrl()
    {
        return campaignUrl;
    }
    
    public void setCampaignUrl(String campaignUrl)
    {
        this.campaignUrl = campaignUrl;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public Long getCurrentTaskId()
    {
        return currentTaskId;
    }
    
    public void setCurrentTaskId(Long currentTaskId)
    {
        this.currentTaskId = currentTaskId;
    }
    
    public List<UICampaignTaskVO> getCampaignTasks()
    {
        return campaignTasks;
    }
    
    public void setCampaignTasks(List<UICampaignTaskVO> campaignTasks)
    {
        this.campaignTasks = campaignTasks;
    }
    
    public List<UICampaignDetailVO> getCampaignPropertys()
    {
        return campaignPropertys;
    }
    
    public void setCampaignPropertys(List<UICampaignDetailVO> campaignPropertys)
    {
        this.campaignPropertys = campaignPropertys;
    }
    
    public List<UICampaignPriceVO> getCampaignPrices()
    {
        return campaignPrices;
    }
    
    public void setCampaignPrices(List<UICampaignPriceVO> campaignPrices)
    {
        this.campaignPrices = campaignPrices;
    }
    
    public Integer getTotalCoins()
    {
        return totalCoins;
    }
    
    public void setTotalCoins(Integer totalCoins)
    {
        this.totalCoins = totalCoins;
    }
    
    public Long getNextTaskId()
    {
        return nextTaskId;
    }
    
    public void setNextTaskId(Long nextTaskId)
    {
        this.nextTaskId = nextTaskId;
    }
    
    public String getCampaignName()
    {
        return campaignName;
    }
    
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }
    
    public String getIconUrl()
    {
        return iconUrl;
    }
    
    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public String getPartnerName()
    {
        return partnerName;
    }
    
    public void setPartnerName(String partnerName)
    {
        this.partnerName = partnerName;
    }
    
    public String getPartnerAvatar()
    {
        return partnerAvatar;
    }
    
    public void setPartnerAvatar(String partnerAvatar)
    {
        this.partnerAvatar = partnerAvatar;
    }
    
    public Long getRequireCoins()
    {
        return requireCoins;
    }
    
    public void setRequireCoins(Long requireCoins)
    {
        this.requireCoins = requireCoins;
    }
    
}
