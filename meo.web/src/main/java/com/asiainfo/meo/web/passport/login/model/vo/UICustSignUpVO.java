package com.asiainfo.meo.web.passport.login.model.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.web.customer.model.vo.UIBundleMobileVO;

public class UICustSignUpVO
{
    @Valid
    @NotNull
    private UILoginVO  login;
    @Valid
    @NotNull
    private UIBundleMobileVO bind;
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
  
    public UIBundleMobileVO getBind()
    {
        return bind;
    }
    public void setBind(UIBundleMobileVO bind)
    {
        this.bind = bind;
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
