package com.asiainfo.meo.web.product.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Update;

public class UIProductMappingVO
{
    private Long    productMappingId;
    
    @NotNull(groups={Update.class})
    private Integer mnoId;
    
    @NotNull(groups={Update.class})
    private Long mnoProdId;
    
    public Long getProductMappingId()
    {
        return productMappingId;
    }
    
    public void setProductMappingId(Long productMappingId)
    {
        this.productMappingId = productMappingId;
    }
    
    public Integer getMnoId()
    {
        return mnoId;
    }
    
    public void setMnoId(Integer mnoId)
    {
        this.mnoId = mnoId;
    }
    
    public Long getMnoProdId()
    {
        return mnoProdId;
    }
    
    public void setMnoProdId(Long mnoProdId)
    {
        this.mnoProdId = mnoProdId;
    }
    
}
