package com.asiainfo.meo.product.profile.app.model.entity;

import java.sql.Timestamp;

public class ProdPricePlan implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private long              pricePlanId;
    
    private Long              priceId;
    
    private Integer           sts;
    
    private Timestamp         modifyDate;
    
    private Timestamp         createDate;
    
    private Long              operaterId;
    
    private Long              productId;
    
    private Long              discountId;
    
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    public Long getDiscountId()
    {
        return discountId;
    }

    public void setDiscountId(Long discountId)
    {
        this.discountId = discountId;
    }

    public long getPricePlanId()
    {
        return this.pricePlanId;
    }
    
    public void setPricePlanId(long pricePlanId)
    {
        this.pricePlanId = pricePlanId;
    }
    
    public Long getPriceId()
    {
        return this.priceId;
    }
    
    public void setPriceId(Long priceId)
    {
        this.priceId = priceId;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Long getOperaterId()
    {
        return this.operaterId;
    }
    
    public void setOperaterId(Long operaterId)
    {
        this.operaterId = operaterId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    
}