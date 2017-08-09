package com.asiainfo.meo.system.service.entity.vo;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class SysOtpVO
{
    @NotEmpty
    private String bundleAccount;
    private Integer countryCode;
    private Integer mnoId;
    @NotNull
    private Integer acctType;
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
    public Integer getAcctType()
    {
        return acctType;
    }
    public void setAcctType(Integer acctType)
    {
        this.acctType = acctType;
    }
    
    
   
}
