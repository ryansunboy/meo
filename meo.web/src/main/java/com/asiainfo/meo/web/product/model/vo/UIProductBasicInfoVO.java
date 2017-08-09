package com.asiainfo.meo.web.product.model.vo;


public class UIProductBasicInfoVO
{
    private Integer productCategoryId;
    
    private Long    productId;
    
    private String  productName;
    
    private Integer productValidity;
    
    private Integer activationValidity;
    
    private Long    validDate;
    
    private Long    expiredDate;

    public Integer getProductCategoryId()
    {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
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
