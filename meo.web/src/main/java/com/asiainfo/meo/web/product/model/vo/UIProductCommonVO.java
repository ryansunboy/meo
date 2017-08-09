package com.asiainfo.meo.web.product.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIProductCommonVO
{
    @Valid
    @NotNull(groups = {Insert.class, Update.class })
    private UIProductVO              product;
    
    @Valid
    @NotNull(groups = {Update.class })
    private UIProductSpecVO          productSpec;
    
    @Valid
    @NotNull(groups = {Update.class })
    private List<UIProductMappingVO> productMappings;
    
     @Valid
     @NotNull(groups={Update.class})
     private List<UIProdPricePlanVO> productPriceDiscounts;

    public UIProductVO getProduct()
    {
        return product;
    }

    public void setProduct(UIProductVO product)
    {
        this.product = product;
    }

    public UIProductSpecVO getProductSpec()
    {
        return productSpec;
    }

    public void setProductSpec(UIProductSpecVO productSpec)
    {
        this.productSpec = productSpec;
    }

    public List<UIProductMappingVO> getProductMappings()
    {
        return productMappings;
    }

    public void setProductMappings(List<UIProductMappingVO> productMappings)
    {
        this.productMappings = productMappings;
    }

    public List<UIProdPricePlanVO> getProductPriceDiscounts()
    {
        return productPriceDiscounts;
    }

    public void setProductPriceDiscounts(List<UIProdPricePlanVO> productPriceDiscounts)
    {
        this.productPriceDiscounts = productPriceDiscounts;
    }
    
}
