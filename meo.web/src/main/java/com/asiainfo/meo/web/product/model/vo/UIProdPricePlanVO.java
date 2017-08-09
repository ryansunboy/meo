package com.asiainfo.meo.web.product.model.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIProdPricePlanVO
{
//    @Valid
//    @NotNull(groups={Update.class})
    private Long    pricePlanId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long    priceId;
    
    private Long    discountId;
    
    public Long getPricePlanId()
    {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId)
    {
        this.pricePlanId = pricePlanId;
    }

    public Long getPriceId()
    {
        return priceId;
    }

    public void setPriceId(Long priceId)
    {
        this.priceId = priceId;
    }

    public Long getDiscountId()
    {
        return discountId;
    }

    public void setDiscountId(Long discountId)
    {
        this.discountId = discountId;
    }
    
    
    
}
