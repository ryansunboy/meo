package com.asiainfo.meo.web.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.service.entity.vo.CmBundleMobileVO;
import com.asiainfo.meo.customer.service.entity.vo.CmDeviceVO;
import com.asiainfo.meo.customer.service.entity.vo.CmLoginVO;
import com.asiainfo.meo.customer.service.entity.vo.CmOtpVO;
import com.asiainfo.meo.customer.service.entity.vo.CmPasswordVO;
import com.asiainfo.meo.customer.service.entity.vo.CmSignUpVO;
import com.asiainfo.meo.customer.service.entity.vo.CmTokenVO;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.prm.service.entity.vo.PrmLoginVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmPartnerLoginVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmPasswordVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmTokenVO;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.system.authority.app.model.vo.MPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.MenuPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.MenuQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.MenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.OPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperatorQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.PrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserVO;
import com.asiainfo.meo.system.authority.app.model.vo.UPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserMenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserOperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleVO;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;
import com.asiainfo.meo.web.core.intercept.SystemParameters;
import com.asiainfo.meo.web.customer.model.vo.UIBundleMobileVO;
import com.asiainfo.meo.web.passport.login.model.vo.UICustSignUpVO;
import com.asiainfo.meo.web.passport.login.model.vo.UIDeviceVO;
import com.asiainfo.meo.web.passport.login.model.vo.UILoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UIPrmLoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UITokenVO;
import com.asiainfo.meo.web.passport.password.model.vo.UIOtpPwdVO;
import com.asiainfo.meo.web.passport.password.model.vo.UIPasswordVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIMPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIMenuPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIMenuVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIOPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIOperPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIOperaterVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIRPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIRolePrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISysMenuVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISysRoleVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISystemUserQueryConditionVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISystemUserVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUserMenuVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUserOperaterVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUserRoleVO;

public class PassportComponent
{
    @Resource
    CustomerPserviceBO customerPserviceBO;
    
    @Resource
    PartnerPserviceBO  prmPserviceBo;
    
    @Resource
    TokenBO            tokenBo;
    
    @Resource
    SystemPserviceBO   sysPserviceBo;
    
    public UITokenVO customerLogin(UILoginVO custLoginVO, UIDeviceVO custDeviceVO)
    {
        CmLoginVO login = transferUiLoginVoToCmLoginVo(custLoginVO);
        
        CmDeviceVO device = transferUiDeviceVoToCmDeviceVo(custDeviceVO);
        
        CmTokenVO cmToken = customerPserviceBO.loginForCustomer(login, device);
        
        UITokenVO uiToken = transferCmTokenVoToUiTokenVo(cmToken);
        return uiToken;
        
    }
    
    private CmLoginVO transferUiLoginVoToCmLoginVo(UILoginVO uiLogin)
    {
        CmLoginVO login = new CmLoginVO();
        if (ValidateUtil.isNotEmpty(uiLogin.getAcctType()))
        {
            login.setAcctType(uiLogin.getAcctType());
        }
        if (ValidateUtil.isNotEmpty(uiLogin.getLoginAcct()))
        {
            login.setLoginAcct(uiLogin.getLoginAcct());
            
        }
        if (ValidateUtil.isNotEmpty(uiLogin.getPassword()))
        {
            login.setPassword(uiLogin.getPassword());
        }
        if (ValidateUtil.isNotEmpty(uiLogin.getRegionCode()))
        {
            login.setRegionCode(uiLogin.getRegionCode());
        }
        if (ValidateUtil.isNotEmpty(uiLogin.getLoginType()))
        {
            login.setLoginType(uiLogin.getLoginType());
        }
        return login;
    }
    
    private UITokenVO transferCmTokenVoToUiTokenVo(CmTokenVO cmToken)
    {
        if (ValidateUtil.isNotEmpty(cmToken))
        {
            UITokenVO token = new UITokenVO();
            token.setAccessToken(cmToken.getAccessToken());
            token.setAccessTokenExpired(cmToken.getAccessTokenExpired());
            token.setRefreshToken(cmToken.getRefreshToken());
            token.setRefreshTokenExpired(cmToken.getRefreshTokenExpired());
            token.setSecretkey(cmToken.getSecretkey());
            return token;
        }
        return null;
    }
    
    private CmDeviceVO transferUiDeviceVoToCmDeviceVo(UIDeviceVO custDeviceVO)
    {
        if (ValidateUtil.isNotEmpty(custDeviceVO))
        {
            CmDeviceVO device = new CmDeviceVO();
            if (ValidateUtil.isNotEmpty(custDeviceVO.getDeviceToken()))
            {
                device.setDeviceToken(custDeviceVO.getDeviceToken());
            }
            if (ValidateUtil.isNotEmpty(custDeviceVO.getDeviceType()))
            {
                device.setDeviceType(custDeviceVO.getDeviceType());
            }
            if (ValidateUtil.isNotEmpty(custDeviceVO.getOsVersion()))
            {
                device.setOsVersion(custDeviceVO.getOsVersion());
            }
            if (ValidateUtil.isNotEmpty(custDeviceVO.getUdid()))
            {
                device.setUdid(custDeviceVO.getUdid());
            }
            return device;
        }
        return null;
    }
    
    private CmSignUpVO transferUiSignUpVoToCmSiginUpVo(UICustSignUpVO signUp)
    {
        CmSignUpVO cmSignUp = new CmSignUpVO();
        if (ValidateUtil.isNotEmpty(signUp))
        {
            UILoginVO uiLogin = signUp.getLogin();
            CmLoginVO login = new CmLoginVO();
            if (ValidateUtil.isNotEmpty(uiLogin.getAcctType()))
            {
                login.setAcctType(uiLogin.getAcctType());
            }
            if (ValidateUtil.isNotEmpty(uiLogin.getLoginAcct()))
            {
                login.setLoginAcct(uiLogin.getLoginAcct());
                
            }
            if (ValidateUtil.isNotEmpty(uiLogin.getPassword()))
            {
                login.setPassword(uiLogin.getPassword());
            }
            if (ValidateUtil.isNotEmpty(uiLogin.getRegionCode()))
            {
                login.setRegionCode(uiLogin.getRegionCode());
            }
            if (ValidateUtil.isNotEmpty(uiLogin.getLoginType()))
            {
                login.setLoginType(uiLogin.getLoginType());
            }
            
            UIBundleMobileVO bundle = signUp.getBind();
            CmBundleMobileVO cmBundle = new CmBundleMobileVO();
            cmBundle.setCountryCode(bundle.getCountryCode());
            cmBundle.setMnoId(bundle.getMnoId());
            cmBundle.setMobileNo(bundle.getMobileNo());
            cmBundle.setOtp(bundle.getOtp());
            UIDeviceVO device = signUp.getDevice();
            CmDeviceVO cmDevice = new CmDeviceVO();
            cmDevice.setDeviceToken(device.getDeviceToken());
            cmDevice.setDeviceType(device.getDeviceType());
            cmDevice.setOsVersion(device.getOsVersion());
            cmDevice.setUdid(device.getUdid());
            cmDevice.setOsType(device.getOsType());
            cmSignUp.setLoginInfo(login);
            cmSignUp.setBundleInfo(cmBundle);
            cmSignUp.setDeviceInfo(cmDevice);
            return cmSignUp;
        }
        
        return null;
    }
    
    public UITokenVO customerSignUp(UICustSignUpVO signUp)
    {
        CmSignUpVO cmSignUp = transferUiSignUpVoToCmSiginUpVo(signUp);
        CmTokenVO cmToken = customerPserviceBO.signUpForCustomer(cmSignUp);
        UITokenVO token = transferCmTokenVoToUiTokenVo(cmToken);
        return token;
    }
    
    public UIPrmLoginVO partnerLogin(UILoginVO login)
    {
        PrmLoginVO prmLogin = transferUiLoginVoToPrmLoginVo(login);
        PrmPartnerLoginVO partnerLogin = prmPserviceBo.loginForPartner(prmLogin);
        UIPrmLoginVO partner = transferPrmLoginVoToUiLoginVo(partnerLogin);
        return partner;
    }
    
    private UIPrmLoginVO transferPrmLoginVoToUiLoginVo(PrmPartnerLoginVO partnerLogin)
    {
        if (ValidateUtil.isNotEmpty(partnerLogin))
        {
            if (ValidateUtil.isNotEmpty(partnerLogin.getToken()))
            {
                UIPrmLoginVO prmLogin = new UIPrmLoginVO();
                PrmTokenVO prmToken = partnerLogin.getToken();
                UITokenVO token = new UITokenVO();
                token.setAccessToken(prmToken.getAccessToken());
                token.setAccessTokenExpired(prmToken.getAccessTokenExpired());
                token.setRefreshToken(prmToken.getRefreshToken());
                token.setRefreshTokenExpired(prmToken.getRefreshTokenExpired());
                token.setSecretkey(prmToken.getSecretkey());
                prmLogin.setIsFirstLogin(partnerLogin.getIsFirstLogin());
                prmLogin.setToken(token);
                return prmLogin;
            }
        }
        return null;
    }
    
    private PrmLoginVO transferUiLoginVoToPrmLoginVo(UILoginVO login)
    {
        if (ValidateUtil.isNotEmpty(login))
        {
            PrmLoginVO prmLogin = new PrmLoginVO();
            prmLogin.setAcctType(login.getAcctType());
            prmLogin.setLoginAcct(login.getLoginAcct());
            prmLogin.setPassword(login.getPassword());
            prmLogin.setRegionCode(login.getRegionCode());
            prmLogin.setUserType(login.getRegionCode());
            return prmLogin;
        }
        return null;
    }
    
    public void resetPortalPassword(UIPasswordVO password)
    {
        if (ValidateUtil.isNotEmpty(password))
        {
            UserLoginVO userVO = sysPserviceBo.getUserLoginInfoByUserId(BoContext.getBoContext().getUserId(),
                    SysEnumCodeDefine.USER_TYPE_PORTAL);
            PrmPasswordVO prmPassword = new PrmPasswordVO();
            prmPassword.setLoginAcct(userVO.getLoginAcct());
            prmPassword.setNewPwd(password.getNewPwd());
            prmPassword.setOldPwd(password.getOldPwd());
            if (!prmPassword.getOldPwd().equals(userVO.getLoginPwd()))
            {
                throw new MeoException(SysErrorCodeDefine.USER_PWD_ERROR);
            }
            prmPserviceBo.resetPortalPassword(prmPassword, userVO.getObjectType());
            Token token = new Token();
            token.setAccessToken(BoContext.getBoContext().getContent(SystemParameters.class).getAccessToken());
            BoContext.getBoContext().setContent(Token.class, token);
        }
    }
    
    public void resetCustPassword(UIPasswordVO password)
    {
        if (ValidateUtil.isNotEmpty(password))
        {
            UserLoginVO userVO = sysPserviceBo.getUserLoginInfoByUserId(BoContext.getBoContext().getUserId(),
                    SysEnumCodeDefine.USER_TYPE_CUSTOMER);
            CmPasswordVO cmPassword = new CmPasswordVO();
            cmPassword.setLoginAcct(userVO.getLoginAcct());
            cmPassword.setNewPwd(password.getNewPwd());
            cmPassword.setOldPwd(password.getOldPwd());
            if (!cmPassword.getOldPwd().equals(userVO.getLoginPwd()))
            {
                throw new MeoException(SysErrorCodeDefine.USER_PWD_ERROR);
            }
            customerPserviceBO.resetCustPassword(cmPassword);
            Token token = new Token();
            token.setAccessToken(BoContext.getBoContext().getContent(SystemParameters.class).getAccessToken());
            BoContext.getBoContext().setContent(Token.class, token);
        }
    }
    
    public OneTimePasswordVO getOtpForCustByExisedAccount()
    {
        return customerPserviceBO.getOtpForCust(null);
        
    }
    
    public OneTimePasswordVO getOtpForCust(UIOtpPwdVO otp)
    {
        CmOtpVO cmOtp = transferUIOtpPwdVOToCmOtpVO(otp);
        return customerPserviceBO.getOtpForCust(cmOtp);
        
    }
    
    private CmOtpVO transferUIOtpPwdVOToCmOtpVO(UIOtpPwdVO otp)
    {
        if (ValidateUtil.isEmpty(otp))
        {
            return null;
        }
        CmOtpVO cmVo = new CmOtpVO();
        cmVo.setAcctType(CustomerBundleInfo.BUNDLE_ACCT_TYPE_MSISDN);
        cmVo.setBundleAccount(otp.getMobileNo());
        cmVo.setCountryCode(otp.getCountryCode());
        cmVo.setMnoId(otp.getMnoId());
        return cmVo;
    }
    
    public void logout()
    {
        tokenBo.expiredToken(BoContext.getBoContext().getContent(SystemParameters.class).getAccessToken());
        
    }
    
    public Token refreshToken(String refreshToken)
    {
        return tokenBo.refreshToken(refreshToken);
        
    }
    
    public UserRoleQueryConditionVO transformRoleCriteriaToRoleQueryConditionVO(final Integer pageNo,
            final Integer pageSize,
            final Long roleId,
            final String roleName)
    {
        final UserRoleQueryConditionVO conditionVO = new UserRoleQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        conditionVO.setRoleId(roleId);
        conditionVO.setRoleName(roleName);
        return conditionVO;
    }
    
    
    public PageInfo<UIUserRoleVO> transferUserRolePageInfoToUIuserRolePageInfo(PageInfo<UserRoleVO> pageInfo)
    {
        //TODO transform to PageInfo
        final PageInfo<UIUserRoleVO> uiPageInfo = new PageInfo<UIUserRoleVO>();
        final List<UIUserRoleVO> uiUserRoleVOList = new ArrayList<UIUserRoleVO>();
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        for (UserRoleVO userRoleVO : pageInfo.getResult())
        {
            final UIUserRoleVO uiUserRoleVO = new UIUserRoleVO();
            BeanUtils.copyProperties(userRoleVO, uiUserRoleVO);
            uiUserRoleVO.setEffectDate(userRoleVO.getCreateDate());
            uiUserRoleVOList.add(uiUserRoleVO);
        }
        uiPageInfo.setResult(uiUserRoleVOList);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        return uiPageInfo;
    }
    
    public List<UISysMenuVO> transferUserMenuVOToUIUserMenuVO(List<UserMenuVO> userMenuVOList)
    {
        List<UISysMenuVO> uiUserMenuVOList = new ArrayList<UISysMenuVO>();
        for (UserMenuVO userMenuVO : userMenuVOList)
        {
            UISysMenuVO uiUserRoleVO = new UISysMenuVO();
            uiUserRoleVO.setMenuId(userMenuVO.getMenuId());
            uiUserRoleVO.setParentMenuId(userMenuVO.getParentMenuId());
            uiUserRoleVO.setModuleId(userMenuVO.getModuleId());
            uiUserRoleVO.setModule(userMenuVO.getModuleValue());
            uiUserRoleVO.setSystemId(userMenuVO.getSystemId());
            uiUserRoleVO.setSystem(userMenuVO.getSystemValue());
            uiUserRoleVO.setMenuName(userMenuVO.getMenuName());
            uiUserRoleVO.setUrl(userMenuVO.getUrl());
            uiUserMenuVOList.add(uiUserRoleVO);
        }
        uiUserMenuVOList = createMenuTree(uiUserMenuVOList);
        return uiUserMenuVOList;
    }
    
    private List<UISysMenuVO> createMenuTree(List<UISysMenuVO> menuList)
    {
        List<UISysMenuVO> nodeList = new ArrayList<UISysMenuVO>();
        for (UISysMenuVO menu1 : menuList)
        {
            boolean mark = false;
            for (UISysMenuVO menu2 : menuList)
            {
                if (menu1.getParentMenuId()!= null&& menu1.getParentMenuId().longValue()== menu2.getMenuId().longValue())
                {
                    mark = true;
                    if (menu2.getChildren()== null)
                    {
                        menu2.setChildren(new ArrayList<UISysMenuVO>());
                    }
                    menu2.getChildren().add(menu1);
                    break;
                }
            }
            if (!mark)
            {
                nodeList.add(menu1);
            }
        }
        return nodeList;
    }
    
    public UserMenuVO transferUIUserMenuVOToUserMenuVO(UIUserMenuVO uiUserMenuVO)
    {
        UserMenuVO userMenuVO = new UserMenuVO();
        userMenuVO.setImagUrl(uiUserMenuVO.getImageUrl());
        userMenuVO.setMenuName(uiUserMenuVO.getMenuName());
        userMenuVO.setModuleId(uiUserMenuVO.getModuleId());
        userMenuVO.setParentMenuId(uiUserMenuVO.getParentMenuId());
        userMenuVO.setSystemId(uiUserMenuVO.getSystemId());
        userMenuVO.setMenuType(uiUserMenuVO.getMenuType());
        userMenuVO.setUrl(uiUserMenuVO.getUrl());
        return userMenuVO;
    }
    
    public UserMenuVO transferModifyUIUserMenuVOToUserMenuVO(UIUserMenuVO uiUserMenuVO)
    {
        UserMenuVO userMenuVO = new UserMenuVO();
        userMenuVO.setMenuId(uiUserMenuVO.getMenuId());
        userMenuVO.setMenuName(uiUserMenuVO.getMenuName());
        userMenuVO.setModuleId(uiUserMenuVO.getModuleId());
        userMenuVO.setParentMenuId(uiUserMenuVO.getParentMenuId());
        userMenuVO.setSystemId(uiUserMenuVO.getSystemId());
        userMenuVO.setUrl(uiUserMenuVO.getUrl());
        return userMenuVO;
    }
    
    public OperatorQueryConditionVO transferOperatorCriteriaToOperatorQueryConditionVO(final Integer pageNo,
            final Integer pageSize,
            final Long operatorId,
            final String operatorName)
    {
        final OperatorQueryConditionVO conditionsVO = new OperatorQueryConditionVO();
        conditionsVO.setPageNo(pageNo);
        conditionsVO.setPageSize(pageSize);
        conditionsVO.setOperatorId(operatorId);
        conditionsVO.setOperatorName(operatorName);
        return conditionsVO;
    }
    
    public RolePrivilegeVO transferUIRolePrivilegeVOToRolePrivilegeVO(UIRolePrivilegeVO rolePrivilege)
    {
        RolePrivilegeVO rolePrivilegeVO = new RolePrivilegeVO();
        // UserRole
        UIUserRoleVO uiUserRoleVO = rolePrivilege.getRole();
        UserRoleVO role = new UserRoleVO();
        role.setRoleName(uiUserRoleVO.getRoleName());
        role.setCreateDate(uiUserRoleVO.getEffectDate());
        rolePrivilegeVO.setRole(role);
        
        List<MenuPrivilegeVO> menuPrivilegeVOList = new ArrayList<MenuPrivilegeVO>();
        
        List<UIMenuPrivilegeVO> uiMenuPrivilegeVOList = rolePrivilege.getMenuPrivileges();
        for (UIMenuPrivilegeVO uiMenuPrivilegeVO : uiMenuPrivilegeVOList)
        {
            MenuPrivilegeVO menuPrivilegeVO = new MenuPrivilegeVO();
            // Menu
            UIMenuVO uiMenuVO = uiMenuPrivilegeVO.getMenu();
            MenuVO menuVO = new MenuVO();
            menuVO.setMenuId(uiMenuVO.getMenuId());
            List<OperPrivilegeVO> operPrivilegeVOList = new ArrayList<OperPrivilegeVO>();
            
            List<UIOperPrivilegeVO> uiOperPrivilegeVOList = uiMenuPrivilegeVO.getOperPrivileges();
            for (UIOperPrivilegeVO uiOperPrivilegeVO : uiOperPrivilegeVOList)
            {
                OperPrivilegeVO operPrivilegeVO = new OperPrivilegeVO();
                
                UIOperaterVO uiOperaterVO = uiOperPrivilegeVO.getOperater();
                OperaterVO operaterVO = new OperaterVO();
                BeanUtils.copyProperties(uiOperaterVO, operaterVO);
                operPrivilegeVO.setOperater(operaterVO);
                
                UIPrivilegeVO uiPrivilegeVO = uiOperPrivilegeVO.getPrivilege();
                PrivilegeVO privilege = new PrivilegeVO();
                BeanUtils.copyProperties(uiPrivilegeVO, privilege);
                operPrivilegeVO.setPrivilege(privilege);
                
                operPrivilegeVOList.add(operPrivilegeVO);
            }
            menuPrivilegeVO.setMenu(menuVO);
            menuPrivilegeVO.setOperPrivileges(operPrivilegeVOList);
            menuPrivilegeVOList.add(menuPrivilegeVO);
        }
        rolePrivilegeVO.setMenuPrivileges(menuPrivilegeVOList);
        
        return rolePrivilegeVO;
    }
    
    public UserRoleVO transferUIUserRoleVOToUserRoleVO(UIUserRoleVO uiUserRole)
    {
        UserRoleVO userRole = new UserRoleVO();
        if (ValidateUtil.isNotNull(uiUserRole))
        {
            userRole.setCreateDate(uiUserRole.getEffectDate());
            userRole.setRoleId(uiUserRole.getRoleId());
            userRole.setRoleName(uiUserRole.getRoleName());
        }
        return userRole;
    }
    
    public UIUserRoleVO transferUserRoleVOToUserRoleVO(UserRoleVO userRole)
    {
        UIUserRoleVO uiUserRole = new UIUserRoleVO();
        if (ValidateUtil.isNotNull(userRole))
        {
            uiUserRole.setEffectDate(userRole.getCreateDate());
            uiUserRole.setRoleId(userRole.getRoleId());
            uiUserRole.setRoleName(userRole.getRoleName());
        }
        return uiUserRole;
    }
    
    public SystemUserQueryConditionVO transferUISystemUserQueryCondtionVOToSystemUserQueryCondtionVO(UISystemUserQueryConditionVO systemUserQueryConditionVO)
    {
        SystemUserQueryConditionVO queryCondition = new SystemUserQueryConditionVO(); 
        if(ValidateUtil.isNotNull(systemUserQueryConditionVO))
        {
            BeanUtils.copyProperties(systemUserQueryConditionVO, queryCondition);
        }
        return queryCondition;
    }
    
    public PageInfo<UISystemUserVO> transferSystemUserVOPageInfoToUISystemUserVOPageInfo(PageInfo<SystemUserVO> pageInfo)
    {
        PageInfo<UISystemUserVO> uiPageInfo = new PageInfo<UISystemUserVO>();
        List<UISystemUserVO> systemUserVOList = new ArrayList<UISystemUserVO>();
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        // search the gender enum list by gender type
        List<EnumDefine> genderEnumList = sysPserviceBo.getEnumByCode(null, EnumDefine.GENDER_TYPE);
        for (SystemUserVO systemUserVO : pageInfo.getResult())
        {
            UISystemUserVO vo = new UISystemUserVO();
            BeanUtils.copyProperties(systemUserVO, vo);
            if (ValidateUtil.isNotEmpty(systemUserVO.getRoleIds()))
            {
                vo.setRoleIds(systemUserVO.getRoleIds());
            }
            if (ValidateUtil.isNotEmpty(systemUserVO.getRoleNames()))
            {
                vo.setRoleNames(systemUserVO.getRoleNames());
            }
            Integer genderId = systemUserVO.getGenderId();
            if (ValidateUtil.isNotNull(genderId)&& ValidateUtil.isNotEmpty(genderEnumList))
            {
                for (EnumDefine enumDefine : genderEnumList)
                {
                    if (enumDefine.getEnumCode().equals(genderId.toString()))
                    {
                        vo.setGender(enumDefine.getEnumName());
                    }
                }
            }
            systemUserVOList.add(vo);
        }
        uiPageInfo.setResult(systemUserVOList);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        return uiPageInfo;
    }
    
    public PageInfo<UIUserOperaterVO> transformOperatorVOPageInfoToUIOperatorVOPageInfo(final PageInfo<UserOperaterVO> pageInfo)
    {
        final PageInfo<UIUserOperaterVO> uiPageInfo = new PageInfo<UIUserOperaterVO>();
        //TODO 
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        final List<UIUserOperaterVO> uiOperatorList = new ArrayList<UIUserOperaterVO>();
        for (final UserOperaterVO vo : pageInfo.getResult())
        {
            final UIUserOperaterVO uiVO = new UIUserOperaterVO();
            uiVO.setOperId(vo.getOperId());
            uiVO.setOperName(vo.getOperName());
            uiOperatorList.add(uiVO);
        }
        uiPageInfo.setResult(uiOperatorList);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        
        return uiPageInfo;
    }
    
    public UISystemUserVO transferSystemUserVOToUISystemUserVO(SystemUserVO systemUser)
    {
        UISystemUserVO vo = new UISystemUserVO();
        if (ValidateUtil.isNotNull(systemUser))
        {
            BeanUtils.copyProperties(systemUser, vo);
        }
        if (ValidateUtil.isNotEmpty(systemUser.getRoleIds()))
        {
            vo.setRoleIds(systemUser.getRoleIds());
        }
        if (ValidateUtil.isNotEmpty(systemUser.getRoleNames()))
        {
            vo.setRoleNames(systemUser.getRoleNames());
        }
        return vo;
    }
    
    public SystemUserVO transferUISystemUserVOToSystemUserVO(UISystemUserVO systemUser)
    {
        SystemUserVO vo = new SystemUserVO();
        if (ValidateUtil.isNotNull(systemUser))
        {
            BeanUtils.copyProperties(systemUser, vo);
            vo.setGenderId(systemUser.getGenderId());
        }
        if (ValidateUtil.isNotEmpty(systemUser.getRoleIds()))
        {
            vo.setRoleIds(systemUser.getRoleIds());
        }
        if (ValidateUtil.isNotEmpty(systemUser.getRoleNames()))
        {
            vo.setRoleNames(systemUser.getRoleNames());
        }
        return vo;
    }
    
    public RolePrivilegeQueryConditionVO transformRPrivilegeCriteriaToRPrivilegeQueryConditionVO(final Integer pageNo,
            final Integer pageSize)
    {
        final RolePrivilegeQueryConditionVO conditionVO = new RolePrivilegeQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        return conditionVO;
    }
    
    public PageInfo<UIRPrivilegeVO> transferRPrivilegeVOPageInfoToUIRPrivilegeVOPageInfo(PageInfo<RPrivilegeVO> pageInfo)
    {
        //TODO transform to pageInfo
        final PageInfo<UIRPrivilegeVO> uiPageInfo = new PageInfo<UIRPrivilegeVO>();
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UIRPrivilegeVO> listVO = new ArrayList<UIRPrivilegeVO>();

        for (RPrivilegeVO rp : pageInfo.getResult())
        {
            UIRPrivilegeVO rpVO = new UIRPrivilegeVO();
                
            UserRoleVO uRole = rp.getRole();
            UIUserRoleVO uRoleVO = new UIUserRoleVO();
            BeanUtils.copyProperties(uRole, uRoleVO);
            rpVO.setRole(uRoleVO);
                
            List<UIMPrivilegeVO> menuPriveligesVO = new ArrayList<UIMPrivilegeVO>();
            
            List<MPrivilegeVO> menuPriveliges = rp.getMenuPriveliges();
            if (ValidateUtil.isNotEmpty(menuPriveliges))
            {
                for (MPrivilegeVO mp : menuPriveliges)
                {
                    UIMPrivilegeVO mpVO = new UIMPrivilegeVO();
                    
                    UIMenuVO menuVO = new UIMenuVO();
                    MenuVO menu = mp.getMenu();
                    BeanUtils.copyProperties(menu, menuVO);
                    mpVO.setMenu(menuVO);
                    
                    List<UIOPrivilegeVO> operPrivilegesVO = new ArrayList<UIOPrivilegeVO>();
                    
                    List<OPrivilegeVO> operPrivileges = mp.getOperPrivileges();
                    if (ValidateUtil.isNotEmpty(operPrivileges))
                    {
                        for (OPrivilegeVO op : operPrivileges)
                        {
                            UIOPrivilegeVO opVO = new UIOPrivilegeVO();
                            BeanUtils.copyProperties(op, opVO);
                            operPrivilegesVO.add(opVO);
                        }
                    }
                    
                    mpVO.setOperPrivileges(operPrivilegesVO);
                    menuPriveligesVO.add(mpVO);
                }
            }
                
            rpVO.setMenuPriveliges(menuPriveligesVO);
            listVO.add(rpVO);
        }
        uiPageInfo.setResult(listVO);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        return uiPageInfo;
    }
    
    public List<UIUPrivilegeVO> transferUPrivilegeVOListToUIUPrivilegeVOList(List<UPrivilegeVO> uPrivilegeList)
    {
        List<UIUPrivilegeVO> listVO = new ArrayList<UIUPrivilegeVO>();
        if (ValidateUtil.isNotEmpty(uPrivilegeList))
        {
            for (UPrivilegeVO up : uPrivilegeList)
            {
                UIUPrivilegeVO upVO = new UIUPrivilegeVO();
                BeanUtils.copyProperties(up, upVO);
                
                if (ValidateUtil.isNotEmpty(up.getRoleNames()))
                {
                    upVO.setRoleNames(up.getRoleNames());
                }
                
                List<UIMPrivilegeVO> menuPrivilegesVO = new ArrayList<UIMPrivilegeVO>();
                
                List<MPrivilegeVO> menuPrivileges = up.getMenuPrivileges();
                if (ValidateUtil.isNotEmpty(menuPrivileges))
                {
                    for (MPrivilegeVO mp : menuPrivileges)
                    {
                        UIMPrivilegeVO mpVO = new UIMPrivilegeVO();
                        
                        MenuVO menu = mp.getMenu();
                        UIMenuVO menuVO = new UIMenuVO();
                        BeanUtils.copyProperties(menu, menuVO);
                        mpVO.setMenu(menuVO);
                        
                        List<UIOPrivilegeVO> operPrivilegesVO = new ArrayList<UIOPrivilegeVO>();
                        List<OPrivilegeVO> operPrivileges = mp.getOperPrivileges();
                        if (ValidateUtil.isNotEmpty(operPrivileges))
                        {
                            for (OPrivilegeVO op : operPrivileges)
                            {
                                UIOPrivilegeVO opVO = new UIOPrivilegeVO();
                                BeanUtils.copyProperties(op, opVO);
                                operPrivilegesVO.add(opVO);
                            }
                        }
                        
                        mpVO.setOperPrivileges(operPrivilegesVO);
                        menuPrivilegesVO.add(mpVO);
                    }
                }
                upVO.setMenuPrivileges(menuPrivilegesVO);
                listVO.add(upVO);
            }
        }
        return listVO;
    }
    
    public OperaterVO transferUIUserOperatorToOperatorVO(final UIUserOperaterVO uiUserOperaterVO)
    {
        final OperaterVO vo = new OperaterVO();
        if (ValidateUtil.isNotNull(uiUserOperaterVO))
        {
            vo.setOperaterId(uiUserOperaterVO.getOperId());
            vo.setOperatorName(uiUserOperaterVO.getOperName());
        }
        return vo;
    }
    
    public MenuQueryConditionVO transferMenuCriteriaToMenuQueryConditionVO(final Integer pageNo,
            final Integer pageSize)
    {
        final MenuQueryConditionVO conditionVO = new MenuQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        return conditionVO;
    }
    
    public PageInfo<UISysMenuVO> transformMenuVOPageInfoToUIMenuVOPageInfo(final PageInfo<UserMenuVO> pageInfo)
    {
        final PageInfo<UISysMenuVO> uiPageInfo = new PageInfo<UISysMenuVO>();
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        final List<UISysMenuVO> uiMenuList = new ArrayList<UISysMenuVO>();
        for (final UserMenuVO vo : pageInfo.getResult())
        {
            final UISysMenuVO uiVO = new UISysMenuVO();
            uiVO.setMenuId(vo.getMenuId());
            uiVO.setParentMenuId(vo.getParentMenuId());
            uiVO.setModuleId(vo.getModuleId());
            uiVO.setModule(vo.getModuleValue());
            uiVO.setSystemId(vo.getSystemId());
            uiVO.setSystem(vo.getSystemValue());
            uiVO.setMenuName(vo.getMenuName());
            uiVO.setUrl(vo.getUrl());
            uiMenuList.add(uiVO);
        }
        uiPageInfo.setResult(uiMenuList);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        
        return uiPageInfo;
    }
}
