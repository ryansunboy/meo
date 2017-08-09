package com.asiainfo.meo.system.component;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.RandomUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.common.app.bo.CommonBo;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerPwd;
import com.asiainfo.meo.system.user.app.model.entity.SysPortalLogin;
import com.asiainfo.meo.system.user.app.model.entity.SysPortalPwd;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;
import com.asiainfo.meo.system.user.app.repository.UserRepository;

public class UserComponent
{
    
    @Resource
    private UserRepository userRepository;
    
    @Resource
    private CommonBo       systemBo;
    
    public UserLoginVO getUserLoginInfo(String loginAcct, Integer userType)
    {
        
        if (ValidateUtil.isNotEmpty(loginAcct))
        {
            if (userType== SysEnumCodeDefine.USER_TYPE_CUSTOMER)
            {
                SysCustomerLogin login = userRepository.querySysUserLogin(loginAcct);
                if (ValidateUtil.isNotEmpty(login)&& ValidateUtil.isNotEmpty(login.getLoginId()))
                {
                    SysCustomerPwd pwd = userRepository.querySysUserPwd(login.getLoginId());
                    if (ValidateUtil.isNotEmpty(pwd))
                    {
                        UserLoginVO loginVo = new UserLoginVO();
                        loginVo.setLoginAcct(login.getLoginAcct());
                        loginVo.setLoginPwd(pwd.getPwd());
                        loginVo.setObjectId(login.getCustId());
                        loginVo.setObjectType(SysEnumCodeDefine.USER_TYPE_CUSTOMER);
                        return loginVo;
                    }
                }
            }
            else if (userType== SysEnumCodeDefine.USER_TYPE_PORTAL)
            {
                SysPortalLogin login = userRepository.querySysPartnerLogin(loginAcct);
                if (ValidateUtil.isNotEmpty(login)&& ValidateUtil.isNotEmpty(login.getLoginId()))
                {
                    SysPortalPwd pwd = userRepository.querySysPartnerPwd(login.getLoginId());
                    SysPortalUser user = userRepository.querySysPortalUserByUserId(login.getUserId());
                    if (ValidateUtil.isNotEmpty(pwd)&& ValidateUtil.isNotEmpty(user))
                    {
                        UserLoginVO loginVo = new UserLoginVO();
                        loginVo.setLoginAcct(login.getLoginAcct());
                        loginVo.setLoginPwd(pwd.getPwd());
                        loginVo.setObjectId(user.getObjectId());
                        loginVo.setObjectType(user.getObjectType());
                        loginVo.setIsFirstLogin(login.getIsFirstLogin());
                        return loginVo;
                    }
                }
            }
            
        }
        
        return null;
    }
    
    public UserLoginVO getUserLoginInfoByUserId(final Long userId, final Integer userType)
    {
        if (ValidateUtil.isNotEmpty(userId))
        {
            if (SysEnumCodeDefine.USER_TYPE_CUSTOMER.equals(userType))
            {
                SysCustomerLogin login = userRepository.querySysCustLoginByCustId(userId);
                if (ValidateUtil.isNotEmpty(login)&& ValidateUtil.isNotEmpty(login.getLoginId()))
                {
                    SysCustomerPwd pwd = userRepository.querySysUserPwd(login.getLoginId());
                    if (ValidateUtil.isNotEmpty(pwd))
                    {
                        UserLoginVO loginVo = new UserLoginVO();
                        loginVo.setLoginAcct(login.getLoginAcct());
                        loginVo.setLoginPwd(pwd.getPwd());
                        loginVo.setObjectId(login.getCustId());
                        loginVo.setObjectType(SysEnumCodeDefine.USER_TYPE_CUSTOMER);
                        return loginVo;
                    }
                }
            }
            else if (SysEnumCodeDefine.USER_TYPE_PORTAL.equals(userType))
            {
                SysPortalLogin login = userRepository.querySysPortalLoginByObjectId(userId, userType);
                if (ValidateUtil.isNotEmpty(login)&& ValidateUtil.isNotEmpty(login.getLoginId()))
                {
                    SysPortalPwd pwd = userRepository.querySysPartnerPwd(login.getLoginId());
                    SysPortalUser user = userRepository.querySysPortalUserByUserId(login.getUserId());
                    if (ValidateUtil.isNotEmpty(pwd)&& ValidateUtil.isNotEmpty(user))
                    {
                        UserLoginVO loginVo = new UserLoginVO();
                        loginVo.setLoginAcct(login.getLoginAcct());
                        loginVo.setLoginPwd(pwd.getPwd());
                        loginVo.setObjectId(user.getObjectId());
                        loginVo.setObjectType(user.getObjectType());
                        return loginVo;
                    }
                }
            }
        }
        return null;
    }
    
    public void createUserLoginInfo(UserLoginVO userLoginVo)
    {
        if (ValidateUtil.isNull(userLoginVo))
        {
            return;
        }
        
        if (userLoginVo.getObjectType()== SysEnumCodeDefine.USER_TYPE_CUSTOMER)
        {
            SysCustomerLogin login = new SysCustomerLogin();
            if (ValidateUtil.isNotEmpty(userLoginVo.getLoginAcct()))
            {
                login.setLoginAcct(userLoginVo.getLoginAcct());
            }
            if (ValidateUtil.isNotEmpty(userLoginVo.getObjectId()))
            {
                login.setCustId(userLoginVo.getObjectId());
            }
            login.setIsFirstLogin(SysCustomerLogin.IS_FIRST_LOGIN);
            userRepository.saveSysUserLogin(login);
            SysCustomerPwd pwd = new SysCustomerPwd();
            if (ValidateUtil.isNotEmpty(userLoginVo.getLoginPwd()))
            {
                pwd.setPwd(userLoginVo.getLoginPwd());
            }
            pwd.setLoginId(login.getLoginId());
            userRepository.saveSysUserPwd(pwd);
        }
        else
        {
            SysPortalLogin login = new SysPortalLogin();
            if (ValidateUtil.isNotEmpty(userLoginVo.getLoginAcct()))
            {
                /** checking a duplicate of login account */
                if (ValidateUtil.isNotNull(userRepository.querySysPartnerLogin(userLoginVo.getLoginAcct())))
                {
                    throw new MeoException(SysErrorCodeDefine.USER_HAS_EXISTED);
                }
                login.setLoginAcct(userLoginVo.getLoginAcct());
            }
            
            login.setIsFirstLogin(SysPortalLogin.IS_FIRST_LOGIN);
            SysPortalUser portalUser = new SysPortalUser();
            portalUser.setObjectId(userLoginVo.getObjectId());
            portalUser.setObjectType(userLoginVo.getObjectType());
            portalUser.setOperatorId(BoContext.getBoContext().getUserId());
            
            
            userRepository.saveSysPortalUser(portalUser);
            login.setUserId(portalUser.getUserId());
            userRepository.saveSysPortalLogin(login);
            SysPortalPwd pwd = new SysPortalPwd();
            if (ValidateUtil.isEmpty(userLoginVo.getLoginPwd()))
            {
                SysConfigurationDef def = systemBo.getConfigDefByConfigValueCode(SysConstantDefine.PASSWORD_LENTH_COFIG_CODE);
                if (ValidateUtil.isEmpty(def))
                {
                    throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
                }
                pwd.setPwd(RandomUtil.randomNum(def.getConfigValue()));
            }
            pwd.setLoginId(login.getLoginId());
            userRepository.saveSysPortalPwd(pwd);
        }
    }
    
    public UserLoginVO getPartnerLoginInfo(String loginAcct)
    {
        return null;
    }
    
    public void updatePassword(SysPasswordVO resetPassword)
    {
        if (ValidateUtil.isNull(resetPassword))
        {
            return;
        }
        if (resetPassword.getUserType()== SysEnumCodeDefine.USER_TYPE_CUSTOMER)
        {
            SysCustomerLogin login = userRepository.querySysUserLogin(resetPassword.getLoginAcct());
            if (ValidateUtil.isEmpty(login)|| ValidateUtil.isEmpty(login.getLoginId()))
            {
                throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
            }
            else
            {
                SysCustomerPwd pwd = userRepository.querySysUserPwd(login.getLoginId());
                if (ValidateUtil.isNotEmpty(pwd))
                {
                    pwd.setPwd(resetPassword.getNewPwd());
                    userRepository.updateCustomerPassword(pwd);
                }
            }
        }
        else
        {
            SysPortalLogin login = userRepository.querySysPartnerLogin(resetPassword.getLoginAcct());
            if (ValidateUtil.isEmpty(login)|| ValidateUtil.isEmpty(login.getLoginId()))
            {
                throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
            }
            else
            {
                SysPortalPwd pwd = userRepository.querySysPartnerPwd(login.getLoginId());
                if (ValidateUtil.isNotEmpty(pwd))
                {
                    pwd.setPwd(resetPassword.getNewPwd());
                    userRepository.updatePortalPassword(pwd);
                }
            }
        }
    }
    
    public void activateLoginAcct(Long objectId, Integer objectType)
    {
        if (objectType.equals(SysEnumCodeDefine.USER_TYPE_CUSTOMER))
        {
            SysCustomerLogin login = userRepository.querySysCustLoginByCustId(objectId);
            if (ValidateUtil.isEmpty(login))
            {
                throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
            }
            login.setSts(SysCustomerLogin.STS_USER_ACTIVATE);
            login.setModifyTime(DateTimeUtil.getNow());
            userRepository.updateCustomerLogin(login);
        }
        else
        {
            SysPortalLogin login = userRepository.querySysPortalLoginByObjectId(objectId, objectType);
            login.setSts(SysPortalLogin.STS_PORTAL_ACTIVATE);
            userRepository.updatePortalLogin(login);
        }
    }
    
    public void modifyPartnerLoginAcct(Long partnerId, String newLoginAcct)
    {
        SysPortalLogin login = userRepository.querySysPortalLoginByObjectId(partnerId, SysPortalUser.OBJECT_TYPE_PARTNER);
        if (ValidateUtil.isEmpty(login))
        {
            throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
        }
        login.setLoginAcct(newLoginAcct);
        userRepository.updatePortalLogin(login);
    }
    
    public void resetSystmeUserPwd(Long staffId)
    {   
        SysPortalLogin sysPortalLogin =userRepository.querySysPortalLoginByObjectId(staffId, SysEnumCodeDefine.USER_TYPE_SYSTEM);
//        SysPortalLogin sysPortalLogin = userRepository.querySysPortalLoginByUserId(userId);
        if (ValidateUtil.isEmpty(sysPortalLogin))
        {
            throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
        }
        SysPortalPwd sysPortalPwd = userRepository.querySysPartnerPwd(sysPortalLogin.getLoginId());
        if (ValidateUtil.isEmpty(sysPortalPwd))
        {
            throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
        }
        SysConfigurationDef def = systemBo.getConfigDefByConfigValueCode(SysConstantDefine.PWD_DEFAULT);
        if (ValidateUtil.isEmpty(def))
        {
            throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
        }
        sysPortalPwd.setPwd(""+ def.getConfigValue());
        userRepository.updatePortalPassword(sysPortalPwd);
    }
    
    public void updateUserIsFirstLoginFlagToFalse(String loginAcct, Integer userTypePortal)
    {
        if (userTypePortal.equals(SysEnumCodeDefine.USER_TYPE_PORTAL))
        {
            SysPortalLogin login = userRepository.querySysPartnerLogin(loginAcct);
            login.setIsFirstLogin(SysPortalLogin.IS_NOT_FIRST_LOGIN);
            userRepository.updatePortalLogin(login);
        }
    }
    
    public SysCustomerLogin querySysCustLoginByCustId(Long objectId)
    {
        return userRepository.querySysCustLoginByCustId(objectId);
    }
    
}
