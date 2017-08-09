package com.asiainfo.meo.prm.service.entity.vo;

public class PrmPartnerLoginVO
{
    private PrmTokenVO token;
    private Integer isFirstLogin;
    public PrmTokenVO getToken()
    {
        return token;
    }
    public void setToken(PrmTokenVO token)
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
