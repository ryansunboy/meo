package com.asiainfo.meo.web.campaign.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UICampaignPropertyDefineVO
{
    @NotNull(groups = {Update.class })
    private Long    propertyKey;
    
    @NotEmpty(groups = {Insert.class })
    private String  propertyCode;
    
    @NotEmpty(groups = {Update.class, Insert.class })
    private String  propertyName;
    
    private Integer mandatory;
    
    @NotNull(groups = {Insert.class })
    private Integer campaignTypeId;
    
    @NotNull(groups = {Insert.class })
    private Integer propertyValueType;
    
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