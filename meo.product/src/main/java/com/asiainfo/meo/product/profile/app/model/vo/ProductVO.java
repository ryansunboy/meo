package com.asiainfo.meo.product.profile.app.model.vo;



public class ProductVO
{
    private Long    productId;
    
    private String  productName;
    
    private String  description;
    
    private String  imgUrl;
    
    private Integer categoryId;
    
    private Integer denomination;
    
    private Integer denominationUnit;
    
    private Long    validDate;
    
    private Long    expiredDate;

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public Integer getDenomination()
    {
        return denomination;
    }

    public void setDenomination(Integer denomination)
    {
        this.denomination = denomination;
    }

    public Integer getDenominationUnit()
    {
        return denominationUnit;
    }

    public void setDenominationUnit(Integer denominationUnit)
    {
        this.denominationUnit = denominationUnit;
    }

    public Long getValidDate()
    {
        return validDate;
    }

    public void setValidDate(Long validDate)
    {
        this.validDate = validDate;
    }

    public Long getExpiredDate()
    {
        return expiredDate;
    }

    public void setExpiredDate(Long expiredDate)
    {
        this.expiredDate = expiredDate;
    }
    
}
