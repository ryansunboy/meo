package com.asiainfo.meo.web.campaign.model.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class UICampaignDetailVO
{
    private Long    propertyId;
    
    @NotEmpty
    private String  propertyCode;
    
    @NotEmpty
    private String  propertyValue;
    
    // added by zhengzy 2015-9-10 16:00:26
    // added these three properties just for the "meo.campaign.detail.get" show in the app
    private Integer propertyValueType;
    
    private String propertyName;
    
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
