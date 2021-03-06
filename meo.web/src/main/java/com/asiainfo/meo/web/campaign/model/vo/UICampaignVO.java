package com.asiainfo.meo.web.campaign.model.vo;

public class UICampaignVO
{
    private Long    campaignId;
    
    private String  campaignName;
    
    private String  iconUrl;
    
    private Integer categoryId;
    
    private String  categoryName;
    
    private Integer totalCoins;
    
    // added by zhengzy 2015-9-10 17:33:56
    private Integer participant;
    
    private Integer remainParticipant;
    
    // added by zhengzy 2015-9-16 10:09:51
    private Long    requireCoins;
    
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
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
    
    public Integer getTotalCoins()
    {
        return totalCoins;
    }
    
    public void setTotalCoins(Integer totalCoins)
    {
        this.totalCoins = totalCoins;
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
    
    public Long getRequireCoins()
    {
        return requireCoins;
    }
    
    public void setRequireCoins(Long requireCoins)
    {
        this.requireCoins = requireCoins;
    }
}
