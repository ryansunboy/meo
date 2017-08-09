package com.asiainfo.meo.campaign.profile.app.model.vo;

public class CampaignPropertyDefineVO
{
    private String  propertyCode;
    
    private String  propertyName;
    
    private Integer mandatory;
    
    private Integer campaignTypeId;
    
    private Integer propertyValueType;
    
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
    
    public Integer getMandatory()
    {
        return mandatory;
    }
    
    public void setMandatory(Integer mandatory)
    {
        this.mandatory = mandatory;
    }
    
    public Integer getCampaignTypeId()
    {
        return campaignTypeId;
    }
    
    public void setCampaignTypeId(Integer campaignTypeId)
    {
        this.campaignTypeId = campaignTypeId;
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
