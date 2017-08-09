package com.asiainfo.meo.product.profile.app.model.vo;

public class DiscountRuleVO
{
    
    private Long    discountId;
    
    private String  discountName;
    
    private Long    minCount;
    
    private Long    maxCount;
    
    private Long    discountPrice;
    
    private Integer priceUnit;
    
    private String  unit;
    
    private Integer discountTypeId;
    
    private String  discountType;
    
    public Long getDiscountId()
    {
        return discountId;
    }
    
    public void setDiscountId(Long discountId)
    {
        this.discountId = discountId;
    }
    
    public String getDiscountName()
    {
        return discountName;
    }
    
    public void setDiscountName(String discountName)
    {
        this.discountName = discountName;
    }
    
    public Long getMinCount()
    {
        return minCount;
    }
    
    public void setMinCount(Long minCount)
    {
        this.minCount = minCount;
    }
    
    public Long getMaxCount()
    {
        return maxCount;
    }
    
    public void setMaxCount(Long maxCount)
    {
        this.maxCount = maxCount;
    }
    
    public Long getDiscountPrice()
    {
        return discountPrice;
    }
    
    public void setDiscountPrice(Long discountPrice)
    {
        this.discountPrice = discountPrice;
    }
    
    public Integer getPriceUnit()
    {
        return priceUnit;
    }
    
    public void setPriceUnit(Integer priceUnit)
    {
        this.priceUnit = priceUnit;
    }
    
    public String getUnit()
    {
        return unit;
    }
    
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    
    public Integer getDiscountTypeId()
    {
        return discountTypeId;
    }
    
    public void setDiscountTypeId(Integer discountTypeId)
    {
        this.discountTypeId = discountTypeId;
    }
    
    public String getDiscountType()
    {
        return discountType;
    }
    
    public void setDiscountType(String discountType)
    {
        this.discountType = discountType;
    }
    
}
