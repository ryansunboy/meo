package com.asiainfo.meo.campaign.profile.app.model.vo;



public class CampaignPropertyDefVO
{
    
    private Long   propertyKey;
    
    private String propertyCode;
    
    private String propertyName;
    
    private String campaignType;
    
    private Integer propertyValueType;
    
    private Integer mandatory;

    public Integer getMandatory()
    {
        return mandatory;
    }

    public void setMandatory(Integer mandatory)
    {
        this.mandatory = mandatory;
    }

    public Long getPropertyKey()
    {
        return propertyKey;
    }

    public void setPropertyKey(Long propertyKey)
    {
        this.propertyKey = propertyKey;
    }

    public String getPropertyCode()
    {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode)
    {
        this.propertyCode = propertyCode;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public void setPropertyName(String propertyName)
    {
        this.propertyName = propertyName;
    }

    public String getCampaignType()
    {
        return campaignType;
    }

    public void setCampaignType(String campaignType)
    {
        this.campaignType = campaignType;
    }

    public Integer getPropertyValueType()
    {
        return propertyValueType;
    }

    public void setPropertyValueType(Integer propertyValueType)
    {
        this.propertyValueType = propertyValueType;
    }
    
    
}
