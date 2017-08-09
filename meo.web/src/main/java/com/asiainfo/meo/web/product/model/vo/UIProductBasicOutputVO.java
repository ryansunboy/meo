package com.asiainfo.meo.web.product.model.vo;


public class UIProductBasicOutputVO
{
    private Long    productId;
    
    private String  productName;
    
    private String  productDescription;
    
    private Integer denomination;
    
    private Integer denominationUnitId;
    
    private String  denominationUnit;
    
    private Integer productCyclePeriod;    // prod_cycle_type
                                            
    private String  productCyclePeriodUnit;
    
    private Integer productStsId;
    
    private String  productSts;
    
    private Long    productValidDate;
    
    private Long    productExpiredDate;
    
    public Long getProductId()
    {
        return productId;
    }
    
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    public String getProductDescription()
    {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }
    
    public Integer getDenomination()
    {
        return denomination;
    }
    
    public void setDenomination(Integer denomination)
    {
        this.denomination = denomination;
    }
    
    public Integer getDenominationUnitId()
    {
        return denominationUnitId;
    }
    
    public void setDenominationUnitId(Integer denominationUnitId)
    {
        this.denominationUnitId = denominationUnitId;
    }
    
    public String getDenominationUnit()
    {
        return denominationUnit;
    }
    
    public void setDenominationUnit(String denominationUnit)
    {
        this.denominationUnit = denominationUnit;
    }
    
    public Integer getProductCyclePeriod()
    {
        return productCyclePeriod;
    }
    
    public void setProductCyclePeriod(Integer productCyclePeriod)
    {
        this.productCyclePeriod = productCyclePeriod;
    }
    
    public String getProductCyclePeriodUnit()
    {
        return productCyclePeriodUnit;
    }
    
    public void setProductCyclePeriodUnit(String productCyclePeriodUnit)
    {
        this.productCyclePeriodUnit = productCyclePeriodUnit;
    }
    
    public Integer getProductStsId()
    {
        return productStsId;
    }
    
    public void setProductStsId(Integer productStsId)
    {
        this.productStsId = productStsId;
    }
    
    public String getProductSts()
    {
        return productSts;
    }
    
    public void setProductSts(String productSts)
    {
        this.productSts = productSts;
    }
    
    public Long getProductValidDate()
    {
        return productValidDate;
    }
    
    public void setProductValidDate(Long productValidDate)
    {
        this.productValidDate = productValidDate;
    }
    
    public Long getProductExpiredDate()
    {
        return productExpiredDate;
    }
    
    public void setProductExpiredDate(Long productExpiredDate)
    {
        this.productExpiredDate = productExpiredDate;
    }
    
}
