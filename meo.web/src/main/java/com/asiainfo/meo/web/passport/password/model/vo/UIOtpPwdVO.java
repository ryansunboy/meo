package com.asiainfo.meo.web.passport.password.model.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class UIOtpPwdVO
{
    @NotEmpty
    @Pattern(regexp="[0-9]{8,20}",message=SysErrorCodeDefine.MOBILE_NO_FORMAT)
    private String mobileNo;
    @NotNull
    private Integer countryCode;
    @NotNull
    private Integer mnoId;
   
    public String getMobileNo()
    {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
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
