package com.asiainfo.meo.product.profile.app.model.entity;

import java.sql.Timestamp;

public class ProdMnoMapping implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private long              id;
    
    private Long              productId;
    
    private Long              mnoProdId;
    
    private Integer           mnoId;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private Integer           sts;
    
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    public long getId()
    {
        return this.id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public Long getProductId()
    {
        return this.productId;
    }
    
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    
    public Long getMnoProdId()
    {
        return this.mnoProdId;
    }
    
    public void setMnoProdId(Long mnoProdId)
    {
        this.mnoProdId = mnoProdId;
    }
    
    public Integer getMnoId()
    {
        return this.mnoId;
    }
    
    public void setMnoId(Integer mnoId)
    {
        this.mnoId = mnoId;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
}