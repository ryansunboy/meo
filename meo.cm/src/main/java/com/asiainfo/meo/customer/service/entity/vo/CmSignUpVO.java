package com.asiainfo.meo.customer.service.entity.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class CmSignUpVO
{
    @Valid
    @NotNull
    private CmLoginVO  loginInfo;
    @Valid
    @NotNull
    private CmBundleMobileVO bundleInfo;
    @Valid
    @NotNull
    private CmDeviceVO deviceInfo;
    public CmLoginVO getLoginInfo()
    {
        return loginInfo;
    }
    public void setLoginInfo(CmLoginVO loginInfo)
    {
        this.loginInfo = loginInfo;
    }
    public CmBundleMobileVO getBundleInfo()
    {
        return bundleInfo;
    }
    public void setBundleInfo(CmBundleMobileVO bundleInfo)
    {
        this.bundleInfo = bundleInfo;
    }
    public CmDeviceVO getDeviceInfo()
    {
        return deviceInfo;
    }
    public void setDeviceInfo(CmDeviceVO deviceInfo)
    {
        this.deviceInfo = deviceInfo;
    }
    
    
}
