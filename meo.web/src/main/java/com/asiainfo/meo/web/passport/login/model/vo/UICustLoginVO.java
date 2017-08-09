package com.asiainfo.meo.web.passport.login.model.vo;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
public class UICustLoginVO
{
    @Valid
    @NotNull
    private UILoginVO  login;
    @Valid
    @NotNull
    private UIDeviceVO device;
    public UILoginVO getLogin()
    {
        return login;
    }
    public void setLogin(UILoginVO login)
    {
        this.login = login;
    }
    public UIDeviceVO getDevice()
    {
        return device;
    }
    public void setDevice(UIDeviceVO device)
    {
        this.device = device;
    }
    
   
    
}
