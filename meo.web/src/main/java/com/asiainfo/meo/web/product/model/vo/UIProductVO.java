package com.asiainfo.meo.web.product.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIProductVO
{
    private Long    productId;
    
    @NotNull(groups = {Update.class })
    private String  productName;
    
    @NotNull(groups = {Update.class })
    private String  description;
    
    @NotNull(groups = {Insert.class, Update.class })
    private String  imgUrl;
    
    @NotNull(groups = {Insert.class, Update.class })
    private Integer categoryId;
    
    @NotNull(groups = {Update.class })
    private Integer denomination;
    
    @NotNull(groups = {Update.class })
    private Integer denominationUnit;
    
    @NotNull(groups = {Insert.class, Update.class })
    private Long    validDate;
    
    @NotNull(groups = {Insert.class, Update.class })
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
