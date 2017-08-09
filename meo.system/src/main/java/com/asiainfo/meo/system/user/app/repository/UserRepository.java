package com.asiainfo.meo.system.user.app.repository;

import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerPwd;
import com.asiainfo.meo.system.user.app.model.entity.SysPortalLogin;
import com.asiainfo.meo.system.user.app.model.entity.SysPortalPwd;

public interface UserRepository
{
    public SysCustomerLogin querySysUserLogin(String loginAcct) ;
    
    public SysCustomerPwd querySysUserPwd(Long loginId);

    public void saveSysUserLogin(SysCustomerLogin login);

    public void saveSysUserPwd(SysCustomerPwd pwd);

    public SysPortalLogin querySysPartnerLogin(String loginAcct);

    public SysPortalPwd querySysPartnerPwd(long loginId);

    public void updateCustomerPassword(SysCustomerPwd pwd);

    public void updatePortalPassword(SysPortalPwd pwd);

    public SysPortalUser querySysPortalUserByUserId(Long userId);
    
    public void saveSysPortalPwd(SysPortalPwd pwd);
    
    public void saveSysPortalLogin(SysPortalLogin login);
    
    public void saveSysPortalUser(SysPortalUser portalUser);

    public SysCustomerLogin querySysCustLoginByCustId(Long objectId);

    public void updateCustomerLogin(SysCustomerLogin login);

    public SysPortalLogin querySysPortalLoginByObjectId(Long objectId, Integer objectType);

    public void updatePortalLogin(SysPortalLogin login);
    
    public SysPortalLogin querySysPortalLoginByUserId(Long userId);

}
