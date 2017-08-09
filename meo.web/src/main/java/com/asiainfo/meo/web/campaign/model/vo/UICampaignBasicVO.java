package com.asiainfo.meo.web.campaign.model.vo;

public class UICampaignBasicVO
{
    
    private Long    campaignId;
    
    private String  campaignName;
    
    private String  partnerName;
    
    private Long    validDate;
    
    private Long    expiredDate;
    
    private Integer campaignStsId;
    
    private String  campaignSts;
    
    private Long    partnerId;
    
    private Integer campaignType;
    
    private String  campaignNo;
    
    private String  description;
    
    // added by zhengzy 2015-9-10 14:38:46
    // added for meo.campaign.all.list
    
    private String  groupCode;
    
    private String  iconUrl;
    
    private Integer participant;
    
    private Integer remainParticipant;
    
    private Integer totalCoins;
    
    private String  partnerAvatar;
    
    // added by zhengzy 2015-9-16 10:09:51
    private Long    requireCoins;
    
    public String getPartnerAvatar()
    {
        return partnerAvatar;
    }
    
    public void setPartnerAvatar(String partnerAvatar)
    {
        this.partnerAvatar = partnerAvatar;
    }
    
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
    
    public String getPartnerName()
    {
        return partnerName;
    }
    
    public void setPartnerName(String partnerName)
    {
        this.partnerName = partnerName;
    }
    
    public Long getValidDate()
    {
        return validDate;
    }
    
    public void setValidDate(Long validDate)
    {
        this.validDate = validDate;
    }
    
    public Long getExpiredDate()
    {
        return expiredDate;
    }
    
    public void setExpiredDate(Long expiredDate)
    {
        this.expiredDate = expiredDate;
    }
    
    public Integer getCampaignStsId()
    {
        return campaignStsId;
    }
    
    public void setCampaignStsId(Integer campaignStsId)
    {
        this.campaignStsId = campaignStsId;
    }
    
    public String getCampaignSts()
    {
        return campaignSts;
    }
    
    public void setCampaignSts(String campaignSts)
    {
        this.campaignSts = campaignSts;
    }
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public Integer getCampaignType()
    {
        return campaignType;
    }
    
    public void setCampaignType(Integer campaignType)
    {
        this.campaignType = campaignType;
    }
    
    public String getCampaignNo()
    {
        return campaignNo;
    }
    
    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getGroupCode()
    {
        return groupCode;
    }
    
    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }
    
    public String getIconUrl()
    {
        return iconUrl;
    }
    
    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
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
    
    public Integer getTotalCoins()
    {
        return totalCoins;
    }
    
    public void setTotalCoins(Integer totalCoins)
    {
        this.totalCoins = totalCoins;
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
