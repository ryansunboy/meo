package com.asiainfo.meo.system.user.app.bo;

import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;



public interface UserBO {
    
    public UserLoginVO getUserLoginInfo(String loginAcct,Integer userType);
    
    public UserLoginVO getUserLoginInfoByUserId(final Long userId, final Integer userType);

    public void createUserLoginInfo( UserLoginVO userLoginVo);


    public void updatePassword(SysPasswordVO resetPassword);

    public void validateBundleAccount(String validPwd, String bundleAcct);

    public void activateLoginAcct(Long objectId, Integer objectType);

    public void updatePartnerLoginAcct(Long partnerId, String newLoginAcct);
    
    public void resetSystmeUserPwd(Long userId);

    public void updateUserIsFirstLoginFlagToFalse(String loginAcct, Integer userTypePortal);
    
    public SysCustomerLogin querySysCustLoginByCustId(Long objectId);
    
    
}
