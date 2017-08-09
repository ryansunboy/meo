package com.asiainfo.meo.customer.service.entity.vo;



public class CmOtpVO
{
    private String bundleAccount;
    private Integer countryCode;
    private Integer mnoId;
    private Integer acctType;
    
    public Integer getAcctType()
    {
        return acctType;
    }
    public void setAcctType(Integer acctType)
    {
        this.acctType = acctType;
    }
    public String getBundleAccount()
    {
        return bundleAccount;
    }
    public void setBundleAccount(String bundleAccount)
    {
        this.bundleAccount = bundleAccount;
    }
    public Integer getCountryCode()
    {
        return countryCode;
    }
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    public Integer getMnoId()
    {
        return mnoId;
    }
    public void setMnoId(Integer mnoId)
    {
        this.mnoId = mnoId;
    }
    
}
