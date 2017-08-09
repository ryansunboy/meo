package com.asiainfo.meo.customer.service.entity.vo;

public class CmBundleMobileVO
{
    private String mobileNo;
    private String otp;
    private Integer mnoId;
    private Integer countryCode;
    public String getMobileNo()
    {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }
    public String getOtp()
    {
        return otp;
    }
    public void setOtp(String otp)
    {
        this.otp = otp;
    }
    public Integer getMnoId()
    {
        return mnoId;
    }
    public void setMnoId(Integer mnoId)
    {
        this.mnoId = mnoId;
    }
    public Integer getCountryCode()
    {
        return countryCode;
    }
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    
}
