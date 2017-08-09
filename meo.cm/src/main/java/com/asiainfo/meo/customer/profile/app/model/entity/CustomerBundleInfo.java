package com.asiainfo.meo.customer.profile.app.model.entity;

import java.sql.Timestamp;

public class CustomerBundleInfo
{
    
    public static final Integer STS_INACTIVATE          = 0;
    
    public static final Integer STS_ACTIVATE            = 1;
    
    public static final Integer STS_DEACTIVATE          = 2;
    
    public static final Integer BUNDLE_ACCT_TYPE_MSISDN = 1;
    
    public static final Integer BUNDLE_ACCT_TYPE_EMAIL  = 2;
    
    private long                id;
    
    private Long                custId;
    
    private String              bundleAcct;
    
    private Integer             countryCode;
    
    private Timestamp           createDate;
    
    private Timestamp           modifyDate;
    
    private Integer             sts;
    
    private Integer             bundleAcctSts;
    
    private Integer             mnoId;
    
    private Integer             bundleAcctType;
    
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public Long getCustId()
    {
        return custId;
    }
    
    public void setCustId(Long custId)
    {
        this.custId = custId;
    }
    
    public String getBundleAcct()
    {
        return bundleAcct;
    }
    
    public void setBundleAcct(String bundleAcct)
    {
        this.bundleAcct = bundleAcct;
    }
    
    public Integer getCountryCode()
    {
        return countryCode;
    }
    
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public Timestamp getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Integer getBundleAcctSts()
    {
        return bundleAcctSts;
    }
    
    public void setBundleAcctSts(Integer bundleAcctSts)
    {
        this.bundleAcctSts = bundleAcctSts;
    }
    
    public Integer getMnoId()
    {
        return mnoId;
    }
    
    public void setMnoId(Integer mnoId)
    {
        this.mnoId = mnoId;
    }
    
    public Integer getBundleAcctType()
    {
        return bundleAcctType;
    }
    
    public void setBundleAcctType(Integer bundleAcctType)
    {
        this.bundleAcctType = bundleAcctType;
    }
    
}
