package com.asiainfo.meo.customer.service.provide.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.cache.Cache;
import com.asiainfo.meo.common.core.cache.distributed.DistributedCache;
import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.bo.AssetBO;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCoinBlanceVO;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCustVO;
import com.asiainfo.meo.customer.cm.app.model.vo.CustomerVO;
import com.asiainfo.meo.customer.define.CmConstantDefine;
import com.asiainfo.meo.customer.device.app.bo.DeviceInfoBO;
import com.asiainfo.meo.customer.device.app.model.entity.DeviceInfo;
import com.asiainfo.meo.customer.device.app.model.vo.DeviceInfoVO;
import com.asiainfo.meo.customer.level.app.bo.LevelBO;
import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.profile.app.bo.CustomerBO;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.resource.app.bo.ResourceBO;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;
import com.asiainfo.meo.customer.service.entity.vo.CmBundleMobileVO;
import com.asiainfo.meo.customer.service.entity.vo.CmDeviceVO;
import com.asiainfo.meo.customer.service.entity.vo.CmLoginVO;
import com.asiainfo.meo.customer.service.entity.vo.CmOtpVO;
import com.asiainfo.meo.customer.service.entity.vo.CmPasswordVO;
import com.asiainfo.meo.customer.service.entity.vo.CmSignUpVO;
import com.asiainfo.meo.customer.service.entity.vo.CmTokenVO;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;
import com.asiainfo.meo.customer.setting.app.bo.CustomerSettingBO;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;

public class CustomerPserviceBOImpl implements CustomerPserviceBO
{
    private static final Log  LOG = LogFactory.getLog(CustomerPserviceBOImpl.class);
    
    @Resource
    private CustomerSettingBO customerSettingBO;
    
    @Resource
    private CustomerBO        customerBO;
    
    @Resource(name = "customerRserviceBO")
    CustomerRserviceBO        custRserviceBO;
    
    @Resource
    DeviceInfoBO              deviceBo;
    
    @Resource
    TokenBO                   tokenBo;
    
    @Resource
    private LevelBO           levelBO;
    
    @Resource
    private AssetBO           assetBO;
    
    @Resource
    SystemPserviceBO          sysPserviceBo;
    
    @Resource
    ResourceBO                 resourceBO;
    
    /**
     * @Description: 获得customer setting
     * @Description: get customer setting
     * @author zhaozx
     * @param custId
     * @param settingType
     * @return
     */
    public List<SettingVO> getSetting(Long custId, Integer settingType)
    {
        return customerSettingBO.getSetting(custId, settingType);
    }
    
    /**
     * @Description: 修改customer setting
     * @Description: update customer setting
     * @author zhaozx
     * @param custId
     * @param settingVOList
     */
    public void updateCustomerSetting(long custId, List<SettingVO> settingVOList)
    {
        Customer customer = customerBO.getCustomer(custId);
        
        if (customer== null)
            throw new MeoException(CustomerErrorConstant.IS_NOT_FOUND, new Object[]{CmConstantDefine.CUSTOMER });
        
        if (customer.getSts()== Customer.STS_INACTIVATE)
            throw new MeoException(CustomerErrorConstant.IS_INVAILD, new Object[]{CmConstantDefine.CUSTOMER_STS });
        
        customerSettingBO.updateCustomerSetting(custId, settingVOList);
    }
    
    /**
     * @Description: 初始化customer setting
     * @Description: init customer setting
     * @author zhaozx
     * @param custId
     */
    public void createCustomerSetting(long custId)
    {
        Customer customer = customerBO.getCustomer(custId);
        
        if (customer== null)
            throw new MeoException(CustomerErrorConstant.IS_NOT_FOUND, new Object[]{CmConstantDefine.CUSTOMER });
        
        if (customer.getSts()== Customer.STS_INACTIVATE)
            throw new MeoException(CustomerErrorConstant.IS_INVAILD, new Object[]{CmConstantDefine.CUSTOMER_STS });
        customerSettingBO.createCustomerSetting(custId);
    }
    
    public CmTokenVO loginForCustomer(CmLoginVO login, CmDeviceVO device)
    {
        
        UserLoginVO loginVo = custRserviceBO.getUserLoginInfo(login.getLoginAcct());
        if (ValidateUtil.isEmpty(loginVo))
            throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
        Customer customer = customerBO.getCustomer(loginVo.getObjectId());
        if (ValidateUtil.isEmpty(customer))
            throw new MeoException(CustomerErrorConstant.CUST_DO_NOT_EXIST);
        if (!customer.getSts().equals(Customer.STS_ACTIVATE))
            throw new MeoException(CustomerErrorConstant.IS_INVAILD, new Object[]{CmConstantDefine.CUSTOMER_STS });
        if (!login.getPassword().equals(loginVo.getLoginPwd()))
            throw new MeoException(SysErrorCodeDefine.USER_PWD_ERROR);
        DeviceInfo deviceInfo = deviceBo.getDeviceInfoByUdid(device.getUdid(),customer.getCustId());
        
        if (ValidateUtil.isEmpty(deviceInfo))
        {
            deviceInfo = new DeviceInfo();
            deviceInfo.setCreateDate(DateTimeUtil.getNow());
            deviceInfo.setDeviceToken(device.getDeviceToken());
            deviceInfo.setDeviceType(device.getDeviceType());
            deviceInfo.setOsVersion(device.getOsVersion());
            deviceInfo.setUdid(device.getUdid());
            deviceInfo.setUserId(loginVo.getObjectId());
            deviceBo.saveDeviceInfo(deviceInfo);
        }
        //added by zhengzy 2015-9-11 18:28:16
        Cache devecieCache = ServiceLocatorFactory.getService(DistributedCache.class);
        String deviceCachekey =  SysConstantDefine.CUSTOMER_DEVICE_CACHE_KEY+login.getLoginAcct();
        devecieCache.put(SysConstantDefine.DEVICE_CACHE_NAME, deviceCachekey, deviceInfo);
        
 
        custRserviceBO.expiredAccessTokens(loginVo.getObjectId());
        CmTokenVO tokenVo = new CmTokenVO();
        Token token = tokenBo.createToken(loginVo.getObjectId());
        tokenVo.setAccessToken(token.getAccessToken());
        tokenVo.setAccessTokenExpired(token.getAccessTokenExpireTime());
        tokenVo.setRefreshToken(token.getRefreshToken());
        tokenVo.setRefreshTokenExpired(token.getRefreshTokenExpireTime());
        tokenVo.setSecretkey(token.getSecretKey());
        return tokenVo;
    }
    
   
    public CmTokenVO signUpForCustomer(CmSignUpVO cmSignUp)
    {
        CmLoginVO cmLogin = cmSignUp.getLoginInfo();
        UserLoginVO userLogin = custRserviceBO.getUserLoginInfo(cmLogin.getLoginAcct());
        if (ValidateUtil.isNotEmpty(userLogin))
            throw new MeoException(SysErrorCodeDefine.USER_HAS_EXISTED);
        else
        {
            LOG.debug("begin to create customer ******");
            Customer customer = new Customer();
            customer.setSts(Customer.STS_INACTIVATE);
            customerBO.saveCustomer(customer);
            LOG.debug("begin to create customer login information ******");
            UserLoginVO userLoginVo = new UserLoginVO();
            userLoginVo.setLoginAcct(cmLogin.getLoginAcct());
            userLoginVo.setLoginPwd(cmLogin.getPassword());
            userLoginVo.setObjectId(customer.getCustId());
            userLoginVo.setObjectType(UserLoginVO.OBJECT_TYPE_CUSTOMER);
            custRserviceBO.createSysCustLoginInfo(userLoginVo);
            LOG.debug("begin to register device information ******");
            CmDeviceVO device = cmSignUp.getDeviceInfo();
            DeviceInfo deviceInfo = deviceBo.getDeviceInfoByUdid(device.getUdid(),customer.getCustId());
            if (ValidateUtil.isNull(deviceInfo))
            {
                deviceInfo = new DeviceInfo();
                deviceInfo.setCreateDate(DateTimeUtil.getNow());
                deviceInfo.setDeviceToken(device.getDeviceToken());
                deviceInfo.setDeviceType(device.getDeviceType());
                deviceInfo.setOsVersion(device.getOsVersion());
                deviceInfo.setUdid(device.getUdid());
                deviceInfo.setUserId(customer.getCustId());
                deviceInfo.setOsType(device.getOsType());
                deviceBo.saveDeviceInfo(deviceInfo);
            }
            LOG.debug("begin to init customer asset information ******");
            assetBO.createCustomerAssets(customer.getCustId());
            LOG.debug("begin to bundle mobile numer information ******");
            CmBundleMobileVO bundleMobileInfo = cmSignUp.getBundleInfo();
            Cache otpCache = ServiceLocatorFactory.getService(DistributedCache.class);
            OneTimePasswordVO password = otpCache.get(SysConstantDefine.OTP_CACHE_NAME, bundleMobileInfo.getCountryCode().toString()
                    .concat(bundleMobileInfo.getMobileNo()), OneTimePasswordVO.class);
            Long nowTime = DateTimeUtil.getCurrentTimeMillis();
            if (ValidateUtil.isEmpty(password)|| password.isUsed()|| !bundleMobileInfo.getOtp().equals(password.getOtp()) || nowTime > password.getExpireDate())
                throw new MeoException(SysErrorCodeDefine.OTP_IS_INVALID);
            CmTokenVO cmToken = bundelMobileInfo(bundleMobileInfo, customer.getCustId());
            LOG.debug("begin to set otp to be used ******");
            SysOtpVO sysOtp = new SysOtpVO();
            sysOtp.setAcctType(SysEnumCodeDefine.BUNDLE_ACCT_TYPE_MSISDN);
            sysOtp.setBundleAccount(bundleMobileInfo.getMobileNo());
            sysOtp.setCountryCode(bundleMobileInfo.getCountryCode());
            sysOtp.setMnoId(bundleMobileInfo.getMnoId());
            custRserviceBO.setOTPToUsed(sysOtp);
            return cmToken;
            
        }
    }
    
    public CmTokenVO bundelMobileInfo(CmBundleMobileVO cmBundle, Long userId)
    {
        CustomerBundleInfo bundleInfo = customerBO.getCustomerBundleInfo(null, cmBundle.getMobileNo());
        if (ValidateUtil.isNotEmpty(bundleInfo))
        {
            throw new MeoException(CustomerErrorConstant.CUST_BINDED_MOBILE);
        }
        else
        {
            bundleInfo = new CustomerBundleInfo();
            bundleInfo.setCountryCode(cmBundle.getCountryCode());
            bundleInfo.setCustId(userId);
            bundleInfo.setMnoId(cmBundle.getMnoId());
            bundleInfo.setBundleAcct(cmBundle.getMobileNo());
            bundleInfo.setBundleAcctType(CustomerBundleInfo.BUNDLE_ACCT_TYPE_MSISDN);
            bundleInfo.setBundleAcctSts(CustomerBundleInfo.STS_ACTIVATE);
            customerBO.saveCustomerBundleInfo(bundleInfo);
        }
        
        customerBO.activateCustomer(userId);
        sysPserviceBo.activateLoginAcct(userId, SysEnumCodeDefine.USER_TYPE_CUSTOMER);
        
        customerSettingBO.createCustomerSetting(userId);
        Token token = tokenBo.createToken(userId);
        CmTokenVO cmToken = new CmTokenVO();
        cmToken.setAccessToken(token.getAccessToken());
        cmToken.setAccessTokenExpired(token.getAccessTokenExpireTime());
        cmToken.setRefreshToken(token.getRefreshToken());
        cmToken.setRefreshTokenExpired(token.getRefreshTokenExpireTime());
        cmToken.setSecretkey(token.getSecretKey());
        return cmToken;
        
    }
    
    private EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        return custRserviceBO.getEnumDefByEnumCode(enumCode, enumType);
    }
    
    public CustomerVO getCustomerProfile(Long custId)
    {
        
        LOG.debug("getCustomer begin...");
        
        if(ValidateUtil.isNull(custId))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{CmConstantDefine.CUSTOMER_ID});
        }
        
        CustomerVO customerVO = new CustomerVO();
        
        Customer customer = getCustomer(custId);
        
        customerVO.setCustomer(customer);
        
        CustomerBundleInfo customerBundleInfo = customerBO.getCustomerBundleInfo(custId, null);
        
        customerVO.setCustomerBundleInfo(customerBundleInfo);
        if (ValidateUtil.isEmpty(customerBundleInfo))
        {
            LOG.debug("method get getCustomerProfile,customerBundleInfo is null,the custId is "+ custId);
            throw new MeoException(CustomerErrorConstant.ENTITY_IS_EMPTY, new Object[]{CmConstantDefine.CUST_BUNDLE_INFO });
        }
        EnumDefine mno = getEnumDefByEnumCode(String.valueOf(customerBundleInfo.getMnoId()), CustomerVO.MNO_ENUM_TYPE);
      
        if (ValidateUtil.isNotEmpty(mno)&& ValidateUtil.isNotEmpty(mno.getEnumName()))
            customerVO.setMnoCode(mno.getEnumName());
        
        CustomerInfo customerInfo = customerBO.getCustBasicInfo(custId);
        
        if (ValidateUtil.isNotEmpty(customerInfo))
        {
                customerVO.setCustomerInfo(customerInfo);
                
                if (ValidateUtil.isNotNull(customerInfo.getLanguageCode()))
                {
                    EnumDefine language = getEnumDefByEnumCode(customerInfo.getLanguageCode().toString(),
                            CustomerVO.LANGUAGE__ENUM_TYPE);
                    if (ValidateUtil.isNotEmpty(language)&& ValidateUtil.isNotEmpty(language.getEnumName()))
                        customerVO.setLanguageCode(language.getEnumName());
                }
                else if (ValidateUtil.isNotNull(customerInfo.getGender()))
                {
                    EnumDefine gender = getEnumDefByEnumCode(customerInfo.getGender().toString(), CustomerVO.GENDER_ENUM_TYPE);
                    if (ValidateUtil.isNotEmpty(gender)&& ValidateUtil.isNotEmpty(gender.getEnumName()))
                        customerVO.setGenderName(gender.getEnumName());
                }
        }
        List<LevelDef> levelDefList = levelBO.getLevelDef(customer.getLevelId(), LevelDef.CUSTOMER_LEVEL);
        if (levelDefList.size()> 0)
        {
            for (LevelDef levelDef : levelDefList)
            {
                customerVO.setLevelDef(levelDef);
            }
        }
        List<Asset> assetList = getAsset(custId, Asset.ASSET_TYPE_COINS);
        
        if (assetList.size()> 0)
        {
            for (Asset asset : assetList)
                customerVO.setCoins(asset.getAmount());
        }
        List<Asset> assetlist = getAsset(custId, Asset.ASSET_TYPE_EXPERIENCE);
        
        if (ValidateUtil.isNotEmpty(assetlist))
        {
            for (Asset asset : assetlist)
                customerVO.setExperience(asset.getAmount());
        }
        customerVO.setSocialType(CustomerVO.MEO_SOCIAL_TYPE);
        return customerVO;
    }
    
    public Customer getCustomer(long custId)
    {
        return customerBO.getCustomer(custId);
    }
    
    public void saveCustomerInfo(CustomerInfo customerInfo)
    {
        customerBO.saveCustomerInfo(customerInfo);
    }
    
    public CustomerInfo getCustBasicInfo(Long custId)
    {
        return customerBO.getCustBasicInfo(custId);
    }
    
    public void updateCustomerInfo(CustomerInfo customerInfo)
    {
        customerBO.updateCustomerInfo(customerInfo);
    }
    
    public void resetCustPassword(CmPasswordVO password)
    {
        Long custId = BoContext.getBoContext().getUserId();
        CustomerBundleInfo bundleInfo = customerBO.getCustomerBundleInfo(custId, null);
        if (ValidateUtil.isEmpty(bundleInfo))
            throw new MeoException(CustomerErrorConstant.CUST_NOT_BIND_ACCT, new Object[]{CmConstantDefine.CUST_NOT_BIND_ACCT });
        
        if (ValidateUtil.isNotEmpty(password))
        {
            /**
             * Since 24/07/2015, OTP for reset password is no longer used
             */
            /*Cache otpCache = ServiceLocatorFactory.getService(DistributedCache.class);
            OneTimePasswordVO otp = otpCache.get(SysConstantDefine.OTP_CACHE_NAME, bundleInfo.getCountryCode()+bundleInfo.getBundleAcct(),
                    OneTimePasswordVO.class);
            if (ValidateUtil.isEmpty(otp)|| !password.getValidPwd().equals(otp.getOtp()))
                throw new MeoException(SysErrorCodeDefine.OTP_IS_INVALID);*/
            // custRserviceBO.validBundleAccount(bundleInfo.getBundleAcct() , password.getValidPwd());
            SysPasswordVO sysPwd = new SysPasswordVO();
            sysPwd.setLoginAcct(password.getLoginAcct());
            sysPwd.setNewPwd(password.getNewPwd());
            sysPwd.setUserType(SysEnumCodeDefine.USER_TYPE_CUSTOMER);
            custRserviceBO.modifyCustPassword(sysPwd);
            custRserviceBO.expiredToken();
        }
        
    }
    
    public OneTimePasswordVO getOtpForCust(CmOtpVO cmOtp)
    {
        SysOtpVO sysOtp = new SysOtpVO();
        if (ValidateUtil.isEmpty(cmOtp))
        {
            CustomerBundleInfo bundleInfo = customerBO.getCustomerBundleInfo(BoContext.getBoContext().getUserId(), null);
            if (ValidateUtil.isEmpty(bundleInfo))
            {
                throw new MeoException(CustomerErrorConstant.CUST_NOT_BIND_ACCT);
            }
            sysOtp.setAcctType(bundleInfo.getBundleAcctType());
            sysOtp.setBundleAccount(bundleInfo.getBundleAcct());
            sysOtp.setCountryCode(bundleInfo.getCountryCode());
            sysOtp.setMnoId(bundleInfo.getMnoId());
        }
        else
        {
            sysOtp.setAcctType(cmOtp.getAcctType());
            sysOtp.setBundleAccount(cmOtp.getBundleAccount());
            sysOtp.setCountryCode(cmOtp.getCountryCode());
            sysOtp.setMnoId(cmOtp.getMnoId());
        }
        
        OneTimePasswordVO otp = sysPserviceBo.genarateOtp(sysOtp);
        // will invoke send notification interface
        return otp;
        
    }
    
    public void updateCustomer(Customer customer)
    {
        if (ValidateUtil.isEmpty(customer))
        {
            throw new MeoException(CustomerErrorConstant.ENTITY_IS_EMPTY, new Object[]{CmConstantDefine.CUSTOMER });
        }
        
        if (ValidateUtil.isNull(customer.getCustId()))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{CmConstantDefine.CUSTOMER_ID });
        }
        customerBO.updateCustomer(customer);
    }
    
//    @Override
//    public List<Product> getProduct(Long productId)
//    {
//        return productBO.getProduct(productId);
//    }
    
    private  List<Asset> getAsset(Long custId,Integer assetType)
    {
        if(ValidateUtil.isNull(custId))
        {
            return null;
        }
        
       return   assetBO.getAsset(custId, assetType);
    }

    @Override
    public void updateAsset(Asset asset)
    {
       assetBO.updateAsset(asset);
    }

    @Override
    public List<PResource> getResourceListbycustid(long custId, Integer pageSize, Integer pageNo)
    {
        return resourceBO.getResourceListbycustid(custId, pageSize, pageNo);
    }

    @Override
    public AssetCoinBlanceVO checkCoinBalance(Long custId)
    {
        return assetBO.checkCoinBalance(custId);
    }

    @Override
    public CmResource addCmResource(CmResource cmResource)
    {
        return resourceBO.addCmResource(cmResource);
    }

    @Override
    public CmResource getCmResourceById(Long id)
    {
        return resourceBO.getCmResourceById(id);
    }

    @Override
    public CmResource updateCmResource(CmResource cmResource)
    {
        return resourceBO.updateCmResource(cmResource);
    }

    @Override
    public CustomerBundleInfo getBundleInfoByCustId(Long custId)
    {
       
        return  customerBO.getCustomerBundleInfo(custId, null);
    }

    @Override
    public List<Asset> createCustomerAssets(Long custId)
    {
        if(ValidateUtil.isNull(custId))
        {
            LOG.debug("create customer assets ,custId can not null");
            throw new MeoException(CustomerErrorConstant.CREATE_CUSTOMER_ASSETS_CUSTID_CAN_NOT_NULL);
        }
        return assetBO.createCustomerAssets(custId);
    }

    @Override
    public void updateResourceStatus(Long resourceId, Integer status)
    {
       CmResource cmResource = resourceBO.getCmResourceById(resourceId);
       if(ValidateUtil.isEmpty(cmResource)){
           throw new MeoException(CustomerErrorConstant.CUST_RESOURCE_NOT_FOUND);
       }
       cmResource.setResSts(status);
        resourceBO.updateCmResource(cmResource);
    }

    @Override
    public AssetCustVO getAssetInfoByCustIdAndAssetType(Long custId, Integer assetType)
    {
        return assetBO.getAssetInfoByCustIdAndAssetType(custId, assetType);
    }
    
    @Override
    public List<ProductBasicInfoVO> getAppProductList(Long customerId, Integer pageNo, Integer pageSize)
    {
        return customerBO.getAppProductList(customerId, pageNo, pageSize);  
    }
    
    @Override
    public UserLoginVO getUserLoginInfo(String loginAcct,Integer userType)
    {
        if(ValidateUtil.isEmpty(loginAcct))
        {
            LOG.debug("the method getuserLoginInfo input parameter loginAcct is empty");
            throw new MeoException(CustomerErrorConstant.LOGINACCT_IS_EMPTY);
        }
        if(ValidateUtil.isNull(userType))
        {
            LOG.debug("the method getuserLoginInfo input parameter userType is null");
            throw new MeoException(CustomerErrorConstant.USERTYPE_IS_NULL);
        }
        if(!(SysEnumCodeDefine.USER_TYPE_CUSTOMER.equals(userType) || SysEnumCodeDefine.USER_TYPE_PORTAL.equals(userType)))
        {
            LOG.debug("the method getuserLoginInfo input parameter userType type is error");
            throw new MeoException(CustomerErrorConstant.USERTYPE_IS_ERROR);
        }
        return sysPserviceBo.getUserLoginInfo(loginAcct, userType);
    }

    @Override
    public PageInfo<PResource> getResourcePageInfoBycustId(Long custId, Integer resType, Integer resSts, Integer pageSize,
            Integer pageNo)
    {
        return resourceBO.getResourcePageInfoBycustId(custId, resType, resSts, pageSize, pageNo);
    }

    @Override
    public void updateMNO(Long custId, Integer mnoId, String otp)
    {
        customerBO.updateMNO(custId, mnoId, otp);
    }

    @Override
    public void updateBoundMobile(Long custId, String mobileNo, String otp, Integer countryCode)
    {
        customerBO.updateMobileNumber(custId, mobileNo, otp, countryCode);
    }

    @Override
    public List<DeviceInfoVO> getDeviceInfoByUserIdandOSType(Integer osType, Long userId)
    {
        return deviceBo.getDeviceInfoByUserIdandOSType(osType, userId);
    }
    
}
