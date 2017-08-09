package com.asiainfo.meo.campaign.profile.app.model.vo;

public class CampaignDetailVO
{
    private Long    propertyId;
    
    private String  propertyCode;
    
    private String  propertyValue;
    
    // added by zhengzy 2015-9-10 16:00:26
    // added these three properties just for the "meo.campaign.detail.get" show in the app
    private Integer propertyValueType;
    
    private String  propertyName;
    
    private Integer mandatory;
    
    public Long getPropertyId()
    {
        return propertyId;
    }
    
    public void setPropertyId(Long propertyId)
    {
        this.propertyId = propertyId;
    }
    
    public String getPropertyCode()
    {
        return propertyCode;
    }
    
    public void setPropertyCode(String propertyCode)
    {
        this.propertyCode = propertyCode;
    }
    
    public String getPropertyValue()
    {
        return propertyValue;
    }
    
    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }
    
    public Integer getPropertyValueType()
    {
        return propertyValueType;
    }
    
    public void setPropertyValueType(Integer propertyValueType)
    {
        this.propertyValueType = propertyValueType;
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
}
