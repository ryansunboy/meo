package com.asiainfo.meo.web.passport.login.model.vo;

import org.hibernate.validator.constraints.NotEmpty;



public class UIPrmLoginVO
{
    @NotEmpty
    private UITokenVO token;
    @NotEmpty
    private Integer isFirstLogin;
    
   
    public UITokenVO getToken()
    {
        return token;
    }
    public void setToken(UITokenVO token)
    {
        this.token = token;
    }
    public Integer getIsFirstLogin()
    {
        return isFirstLogin;
    }
    public void setIsFirstLogin(Integer isFirstLogin)
    {
        this.isFirstLogin = isFirstLogin;
    }
    
}
