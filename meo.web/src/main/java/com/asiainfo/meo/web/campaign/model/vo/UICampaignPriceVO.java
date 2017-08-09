package com.asiainfo.meo.web.campaign.model.vo;

import javax.validation.constraints.NotNull;

public class UICampaignPriceVO
{
    private Long    priceId;
    
    @NotNull
    private Long    unitPrice;
    
    @NotNull
    private Integer unit;
    
    @NotNull
    private Integer chargeUnit;
    
    @NotNull
    private Integer priceType;
    
    public Long getPriceId()
    {
        return priceId;
    }
    
    public void setPriceId(Long priceId)
    {
        this.priceId = priceId;
    }
    
    public Long getUnitPrice()
    {
        return unitPrice;
    }
    
    public void setUnitPrice(Long unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    
    public Integer getUnit()
    {
        return unit;
    }
    
    public void setUnit(Integer unit)
    {
        this.unit = unit;
    }
    
    public Integer getChargeUnit()
    {
        return chargeUnit;
    }
    
    public void setChargeUnit(Integer chargeUnit)
    {
        this.chargeUnit = chargeUnit;
    }
    
    public Integer getPriceType()
    {
        return priceType;
    }
    
    public void setPriceType(Integer priceType)
    {
        this.priceType = priceType;
    }
}
