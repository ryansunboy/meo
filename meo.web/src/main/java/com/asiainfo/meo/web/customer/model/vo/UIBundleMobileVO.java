package com.asiainfo.meo.web.customer.model.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.system.define.SysErrorCodeDefine;


public class UIBundleMobileVO
{
    @NotEmpty
    @Pattern(regexp="[0-9]{7,20}",message=SysErrorCodeDefine.MOBILE_NO_FORMAT)
    private String mobileNo;
    @NotEmpty
    private String otp;
    @NotNull
    private Integer mnoId;
    @NotNull
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
