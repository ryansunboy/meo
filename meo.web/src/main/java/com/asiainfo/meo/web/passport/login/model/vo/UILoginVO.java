package com.asiainfo.meo.web.passport.login.model.vo;



import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class UILoginVO
{
    private Integer loginType;
    @Valid
    @NotEmpty(groups={Insert.class})
    @Pattern(regexp="^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", groups={Insert.class}, message=SysErrorCodeDefine.LOGIN_ACCOUNT_REGEXP_FORMAT_ERROR)
    private String  loginAcct;
    private Integer acctType;
    
    @Valid
    @NotEmpty(groups={Insert.class})
    @Length(min=8,max=25,message=SysErrorCodeDefine.LOGIN_PASSWORD_FORMAT_ERROR, groups={Insert.class})
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
