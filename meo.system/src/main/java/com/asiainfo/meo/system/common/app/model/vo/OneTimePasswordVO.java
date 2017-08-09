package com.asiainfo.meo.system.common.app.model.vo;

public class OneTimePasswordVO
{
    private String contactAccount;
    private Integer pwdPeriod;
    private String otp;
    private long expireDate;
    private long validDate;
    private boolean isUsed=false;
    public String getContactAccount()
    {
        return contactAccount;
    }
    public void setContactAccount(String contactAccount)
    {
        this.contactAccount = contactAccount;
    }
    public Integer getPwdPeriod()
    {
        return pwdPeriod;
    }
    public void setPwdPeriod(Integer pwdPeriod)
    {
        this.pwdPeriod = pwdPeriod;
    }
    public String getOtp()
    {
        return otp;
    }
    public void setOtp(String otp)
    {
        this.otp = otp;
    }
    public long getExpireDate()
    {
        return expireDate;
    }
    public void setExpireDate(long expireDate)
    {
        this.expireDate = expireDate;
    }
    public long getValidDate()
    {
        return validDate;
    }
    public void setValidDate(long validDate)
    {
        this.validDate = validDate;
    }
    public boolean isUsed()
    {
        return isUsed;
    }
    public void setUsed(boolean isUsed)
    {
        this.isUsed = isUsed;
    }
    
    
    
}
