package com.asiainfo.meo.system.common.app.model.entity;

// Generated 2015-5-11 19:31:42 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysProvDef generated by hbm2java
 */
public class SysProvDef implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private long              provCode;
    
    private long              countryCode;
    
    private String            provName;
    
    private Integer           sts;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private Long              operatorId;
    
    public long getProvCode()
    {
        return this.provCode;
    }
    
    public void setProvCode(long provCode)
    {
        this.provCode = provCode;
    }
    
    public long getCountryCode()
    {
        return this.countryCode;
    }
    
    public void setCountryCode(long countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public String getProvName()
    {
        return this.provName;
    }
    
    public void setProvName(String provName)
    {
        this.provName = provName;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
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
    
    public Long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
}