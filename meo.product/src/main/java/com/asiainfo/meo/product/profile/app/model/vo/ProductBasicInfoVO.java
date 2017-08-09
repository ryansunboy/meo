package com.asiainfo.meo.product.profile.app.model.vo;

import java.sql.Timestamp;

public class  ProductBasicInfoVO
{
    // input
    private Integer   productCategoryId;
    
    private Integer   productValidity;
    
    private Integer   activationValidity;
    
    private String    productCategory;
    // output
    private String    productDescription;
    
    private Integer   denomination;
    
    private Integer   denominationUnitId;
    
    private String    denominationUnit;
    
    private Integer   productCyclePeriod;    // prod_cycle_type
                                              
    private String    productCyclePeriodUnit;
    
    private Integer   productStsId;
    
    private String    productSts;
    // all
    private Long      productId;
    
    private String    productName;
    
    private Timestamp productValidDate;
    
    private Timestamp productExpiredDate;
    
    private Integer   productTypeId;
    
    private String    productType;
    
    private String    imgURL;

    public String getProductCategory()
    {
        return productCategory;
    }

    public void setProductCategory(String productCategory)
    {
        this.productCategory = productCategory;
    }

    public Integer getProductCategoryId()
    {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductValidity()
    {
        return productValidity;
    }

    public void setProductValidity(Integer productValidity)
    {
        this.productValidity = productValidity;
    }

    public Integer getActivationValidity()
    {
        return activationValidity;
    }

    public void setActivationValidity(Integer activationValidity)
    {
        this.activationValidity = activationValidity;
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

    public Timestamp getProductValidDate()
    {
        return productValidDate;
    }

    public void setProductValidDate(Timestamp productValidDate)
    {
        this.productValidDate = productValidDate;
    }

    public Timestamp getProductExpiredDate()
    {
        return productExpiredDate;
    }

    public void setProductExpiredDate(Timestamp productExpiredDate)
    {
        this.productExpiredDate = productExpiredDate;
    }

    public Integer getProductTypeId()
    {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId)
    {
        this.productTypeId = productTypeId;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getImgURL()
    {
        return imgURL;
    }

    public void setImgURL(String imgURL)
    {
        this.imgURL = imgURL;
    }

}
