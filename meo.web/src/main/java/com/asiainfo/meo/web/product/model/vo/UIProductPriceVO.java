package com.asiainfo.meo.web.product.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Update;

public class UIProductPriceVO
{
    private Long    priceId;
    
    @NotNull(groups = {Update.class })
    private Integer price;
    
    @NotNull(groups = {Update.class })
    private Integer priceUnit;
    
    @NotNull(groups = {Update.class })
    private Integer priceType;
    
    public Long getPriceId()
    {
        return priceId;
    }
    
    public void setPriceId(Long priceId)
    {
        this.priceId = priceId;
    }
    
    public Integer getPrice()
    {
        return price;
    }
    
    public void setPrice(Integer price)
    {
        this.price = price;
    }
    
    public Integer getPriceUnit()
    {
        return priceUnit;
    }
    
    public void setPriceUnit(Integer priceUnit)
    {
        this.priceUnit = priceUnit;
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
