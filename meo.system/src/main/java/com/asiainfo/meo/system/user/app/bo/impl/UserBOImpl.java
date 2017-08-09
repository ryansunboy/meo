package com.asiainfo.meo.system.user.app.bo.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.component.UserComponent;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.user.app.bo.UserBO;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;


public class UserBOImpl implements UserBO
{
    private static final Log LOG = LogFactory.getLog(UserBOImpl.class);
    
    @Resource
    private UserComponent    userComponent;
    
    public UserLoginVO getUserLoginInfo(String loginAcct, Integer userType)
    {
        LOG.debug("begin to invoke getUserLoginInfo method****");
        UserLoginVO userLogin = userComponent.getUserLoginInfo(loginAcct, userType);
        LOG.debug("finish to invoke getUserLoginInfo method****");
        return userLogin;
    }
    
    public void createUserLoginInfo(UserLoginVO userLoginVo)
    {
        if (ValidateUtil.isEmpty(userLoginVo))
            return;
        userComponent.createUserLoginInfo(userLoginVo);
    }
    
    public UserLoginVO getPartnerLoginInfo(String loginAcct)
    {
        UserLoginVO login = userComponent.getPartnerLoginInfo(loginAcct);
        return login;
    }
    
    public void updatePassword(SysPasswordVO resetPassword)
    {
        userComponent.updatePassword(resetPassword);
    }
    
    public void validateBundleAccount(String validPwd, String bundleAcct)
    {
        // TODO Auto-generated method stub
        
    }
    
    public void activateLoginAcct(Long objectId, Integer objectType)
    {
        userComponent.activateLoginAcct(objectId, objectType);
    }
    
    public void updatePartnerLoginAcct(Long partnerId, String newLoginAcct)
    {
        userComponent.modifyPartnerLoginAcct(partnerId, newLoginAcct);
    }

    @Override
    public void resetSystmeUserPwd(Long userId)
    {
        userComponent.resetSystmeUserPwd(userId);
    }

    @Override
    public UserLoginVO getUserLoginInfoByUserId(Long userId, Integer userType)
    {
        LOG.debug("begin to invoke getUserLoginInfoByUserId ****");
        UserLoginVO userLogin = userComponent.getUserLoginInfoByUserId(userId, userType);
        LOG.debug("end to invoke getUserLoginInfoByUserId ****");
        return userLogin;
    }

    @Override
    public void updateUserIsFirstLoginFlagToFalse(String loginAcct, Integer userTypePortal)
    {
        userComponent.updateUserIsFirstLoginFlagToFalse(loginAcct,userTypePortal);
        
    }

    @Override
    public SysCustomerLogin querySysCustLoginByCustId(Long objectId)
    {
        return userComponent.querySysCustLoginByCustId(objectId);
    }

    
}
