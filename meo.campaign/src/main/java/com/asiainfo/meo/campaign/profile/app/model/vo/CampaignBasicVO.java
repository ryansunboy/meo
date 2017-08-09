package com.asiainfo.meo.campaign.profile.app.model.vo;

import java.sql.Timestamp;

public class CampaignBasicVO
{
    private Long      campaignId;
    
    private String    campaignNo;
    
    private String    campaignName;
    
    private String    partnerName;
    
    private Integer   campaignType;
    
    private Timestamp campaignBeginDate;
    
    private Timestamp campaignEndDate;
    
    private Long      agreementId;
    
    private Integer   sts;
    
    private String    iconUrl;
    
    private Timestamp validDate;
    
    private Timestamp expiredDate;
    
    private Integer   categoryId;
    
    private String    description;
    
    private Long      partnerId;
    
    private Integer   participant;
    
    private Long      operatorId;
    
    private String    campaignSts;
    
    private Integer   campaignStsId;
    
    private String    categoryDisName;
    
    private String    campaignUrl;
    
    // added by zhengzy 2015-9-10 14:38:46
    // added for meo.campaign.all.list
    
    private String    groupCode;
    
    private Integer   remainParticipant;
    
    private Integer   totalCoins;
    
    private String    partnerAvatar;
    
    public String getPartnerAvatar()
    {
        return partnerAvatar;
    }
    
    public void setPartnerAvatar(String partnerAvatar)
    {
        this.partnerAvatar = partnerAvatar;
    }
    
    public Integer getCampaignStsId()
    {
        return campaignStsId;
    }
    
    public void setCampaignStsId(Integer campaignStsId)
    {
        this.campaignStsId = campaignStsId;
    }
    
    public String getCampaignUrl()
    {
        return campaignUrl;
    }
    
    public void setCampaignUrl(String campaignUrl)
    {
        this.campaignUrl = campaignUrl;
    }
    
    public String getCategoryDisName()
    {
        return categoryDisName;
    }
    
    public void setCategoryDisName(String categoryDisName)
    {
        this.categoryDisName = categoryDisName;
    }
    
    public String getPartnerName()
    {
        return partnerName;
    }
    
    public void setPartnerName(String partnerName)
    {
        this.partnerName = partnerName;
    }
    
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
    
    public Timestamp getCampaignBeginDate()
    {
        return campaignBeginDate;
    }
    
    public void setCampaignBeginDate(Timestamp campaignBeginDate)
    {
        this.campaignBeginDate = campaignBeginDate;
    }
    
    public Timestamp getCampaignEndDate()
    {
        return campaignEndDate;
    }
    
    public void setCampaignEndDate(Timestamp campaignEndDate)
    {
        this.campaignEndDate = campaignEndDate;
    }
    
    public Long getAgreementId()
    {
        return agreementId;
    }
    
    public void setAgreementId(Long agreementId)
    {
        this.agreementId = agreementId;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public String getIconUrl()
    {
        return iconUrl;
    }
    
    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }
    
    public Timestamp getValidDate()
    {
        return validDate;
    }
    
    public void setValidDate(Timestamp validDate)
    {
        this.validDate = validDate;
    }
    
    public Timestamp getExpiredDate()
    {
        return expiredDate;
    }
    
    public void setExpiredDate(Timestamp expiredDate)
    {
        this.expiredDate = expiredDate;
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
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public Integer getParticipant()
    {
        return participant;
    }
    
    public void setParticipant(Integer participant)
    {
        this.participant = participant;
    }
    
    public Long getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    public String getCampaignSts()
    {
        return campaignSts;
    }
    
    public void setCampaignSts(String campaignSts)
    {
        this.campaignSts = campaignSts;
    }
    
    public String getGroupCode()
    {
        return groupCode;
    }
    
    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
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
}
