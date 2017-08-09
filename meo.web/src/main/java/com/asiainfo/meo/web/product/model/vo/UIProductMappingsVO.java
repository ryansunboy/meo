package com.asiainfo.meo.web.product.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UIProductMappingsVO
{
    @NotNull
    private Long productId;
    
    @Valid
    @NotNull
    private List<UIProductMappingVO> productMappings;

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public List<UIProductMappingVO> getProductMappings()
    {
        return productMappings;
    }

    public void setProductMappings(List<UIProductMappingVO> productMappings)
    {
        this.productMappings = productMappings;
    }
    
    
}
