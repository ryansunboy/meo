package com.asiainfo.meo.product.profile.app.model.vo;

public class ProdPriceVO
{
    private Long    productPriceId;
    
    private Long    productId;
    
    private Long price;
    
    private Integer priceUnitId;
    
    private String  priceUnit;
    
    private Integer priceTypeId;
    
    private String  priceType;
    
    public Long getProductPriceId()
    {
        return productPriceId;
    }
    
    public void setProductPriceId(Long productPriceId)
    {
        this.productPriceId = productPriceId;
    }
    
    public Long getProductId()
    {
        return productId;
    }
    
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    
    public Long getPrice()
    {
        return price;
    }

    public void setPrice(Long price)
    {
        this.price = price;
    }

    public Integer getPriceUnitId()
    {
        return priceUnitId;
    }
    
    public void setPriceUnitId(Integer priceUnitId)
    {
        this.priceUnitId = priceUnitId;
    }
    
    public String getPriceUnit()
    {
        return priceUnit;
    }
    
    public void setPriceUnit(String priceUnit)
    {
        this.priceUnit = priceUnit;
    }
    
    public Integer getPriceTypeId()
    {
        return priceTypeId;
    }
    
    public void setPriceTypeId(Integer priceTypeId)
    {
        this.priceTypeId = priceTypeId;
    }
    
    public String getPriceType()
    {
        return priceType;
    }
    
    public void setPriceType(String priceType)
    {
        this.priceType = priceType;
    }
    
}
