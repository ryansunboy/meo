package com.asiainfo.meo.customer.service.entity.vo;

public class CmLoginVO
{
    private Integer loginType;
    private String  loginAcct;
    private Integer acctType;
    private String  password;
    private Integer userType;
    private Integer regionCode;
    public Integer getLoginType()
    {
        return loginType;
    }
    public void setLoginType(Integer loginType)
    {
        this.loginType = loginType;
    }
    public String getLoginAcct()
    {
        return loginAcct;
    }
    public void setLoginAcct(String loginAcct)
    {
        this.loginAcct = loginAcct;
    }
    public Integer getAcctType()
    {
        return acctType;
    }
    public void setAcctType(Integer acctType)
    {
        this.acctType = acctType;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public Integer getUserType()
    {
        return userType;
    }
    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }
    public Integer getRegionCode()
    {
        return regionCode;
    }
    public void setRegionCode(Integer regionCode)
    {
        this.regionCode = regionCode;
    }
    
}
