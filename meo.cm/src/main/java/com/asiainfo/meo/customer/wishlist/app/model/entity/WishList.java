package com.asiainfo.meo.customer.wishlist.app.model.entity;

// Generated 2015-3-20 16:06:44 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmWishList generated by hbm2java
 */
public class WishList implements java.io.Serializable
{
    private static final long serialVersionUID        = 6933579571405946457L;
    
    public static final int   WISHLIST_STATUS_VALID   = 0;
    
    public static final int   WISHLIST_STATUS_INVALID = 1;
    
    private long              id;
    private Long              custId;
    private Integer           productId;
    private Integer           sts;
    private Timestamp         modifyDate;
    private Timestamp         createDate;
    
    public long getId()
    {
        return this.id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public Long getCustId()
    {
        return this.custId;
    }
    
    public void setCustId(Long custId)
    {
        this.custId = custId;
    }
    
    public Integer getProductId()
    {
        return productId;
    }

    public void setProductId(Integer productId)
    {
        this.productId = productId;
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
    
}
