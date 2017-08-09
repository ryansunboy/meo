package com.asiainfo.meo.web.product.model.vo;


public class UIProductBasicVO
{
    // output
    private String  productDescription;
    
    private Integer denomination;
    
    private Integer denominationUnitId;
    
    private String  denominationUnit;
    
    private Integer productStsId;
    
    private String  productSts;
    // all
    private Long    productId;
    
    private String  productName;
    
    private String imgURL;
    
    private Integer productCategoryId;
    
    private String productCategory;
    
    private Long productValidDate;
    
    private Long productExpiredDate;
    
    
    
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
    
    public String getProductCategory()
    {
        return productCategory;
    }

    public void setProductCategory(String productCategory)
    {
        this.productCategory = productCategory;
    }

    public String getImgURL()
    {
        return imgURL;
    }

    public void setImgURL(String imgURL)
    {
        this.imgURL = imgURL;
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

    public Integer getProductCategoryId()
    {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
    
}
