package com.asiainfo.meo.product.profile.app.model.vo;

public class ProdPricePlanVO
{
    private Long    pricePlanId;
    
    private Long    priceId;
    
    private Long    discountId;

    private Long    productId;
    
    public Long getPricePlanId()
    {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId)
    {
        this.pricePlanId = pricePlanId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
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
