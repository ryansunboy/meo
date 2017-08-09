package com.asiainfo.meo.web.product.model.vo;

public class UIProdMnoMappingInfoVO
{
    private Long    productMappingId;
    
    private Long    productId;
    
    private Long    mnoProdId;
    
    private Integer mnoId;
    
    private String  mnoName;
    
    public Long getProductId()
    {
        return productId;
    }
    
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    
    public String getMnoName()
    {
        return mnoName;
    }
    
    public void setMnoName(String mnoName)
    {
        this.mnoName = mnoName;
    }
    
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
