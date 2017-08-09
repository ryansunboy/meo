package com.asiainfo.meo.customer.service.provide;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCoinBlanceVO;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCustVO;
import com.asiainfo.meo.customer.cm.app.model.vo.CustomerVO;
import com.asiainfo.meo.customer.device.app.model.vo.DeviceInfoVO;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;
import com.asiainfo.meo.customer.service.entity.vo.CmDeviceVO;
import com.asiainfo.meo.customer.service.entity.vo.CmLoginVO;
import com.asiainfo.meo.customer.service.entity.vo.CmOtpVO;
import com.asiainfo.meo.customer.service.entity.vo.CmPasswordVO;
import com.asiainfo.meo.customer.service.entity.vo.CmSignUpVO;
import com.asiainfo.meo.customer.service.entity.vo.CmTokenVO;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;

public interface CustomerPserviceBO
{
    /**
     * @Description: 鑾峰緱customer setting
     * @Description: get customer setting
     * @author zhaozx
     * @param custId
     * @param settingType
     * @return
     */
    public List<SettingVO> getSetting(Long custId, Integer settingType);
    
    
    /**
     * @Description: 淇敼customer setting
     * @Description: update customer setting
     * @author zhaozx
     * @param custId
     * @param settingVOList
     */
    public void updateCustomerSetting(long custId, List<SettingVO> settingVOList);
    /**
     * @Description: 鍒濆鍖朿ustomer setting
     * @Description: init customer setting
     * @author zhaozx
     * @param custId
     */
    public void createCustomerSetting(long custId);
    /**
     * 
      * @Description: (瀹㈡埛鐧诲綍)
      * @Description: (customer login)
      * @modifyReason: 
      * @author liuyang
      * @param login
      * @param device
      * @return
     */
    public CmTokenVO loginForCustomer(CmLoginVO login,CmDeviceVO device);

    /**
     * 
      * @Description: (瀹㈡埛娉ㄥ唽)
      * @Description: (customer sign up)
      * @modifyReason: 
      * @author liuyang
      * @param cmSignUp
      * @return
     */
    public CmTokenVO signUpForCustomer(CmSignUpVO cmSignUp);

    /**
      * @Description: (鏍规嵁custId鏌ヨcustomer淇℃伅)
      * @Description: (get customer profile information from custId )
      * @modifyReason: 
      * @author lill
      * @param custId
      * @return    
      */
    CustomerVO getCustomerProfile(Long custId);
 
    /**
     * @Description: (鏍规嵁custId鑾峰彇customer淇℃伅)
     * @Description: (get customer order by custId)
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     */
    Customer getCustomer(long custId);
    
    /**
      * @Description: (鍒涘缓customer鍩烘湰淇℃伅)
      * @Description: (create customer profile information)
      * @modifyReason: 
      * @author lill
      * @param customerInfo    
      */
    void saveCustomerInfo(CustomerInfo customerInfo);
    
    /**
     * 
      * @Description: (鏍规嵁customer id 鑾峰彇customer 鍩烘湰淇℃伅)
      * @Description: (get customer basic information by customer id )
      * @modifyReason: 
      * @author lill
      * @param custId
      * @return
     */
    CustomerInfo getCustBasicInfo(Long custId);
    
    /**
      * @Description: (鏇存柊customer鍩烘湰淇℃伅)
      * @Description: (update customer information)
      * @modifyReason: 
      * @author lill
      * @param customerInfo    
      */
    void updateCustomerInfo(CustomerInfo customerInfo);


    public void resetCustPassword(CmPasswordVO password);

    /**
     * 
      * @Description: 涓哄鎴风敓鎴恛tp骞朵笖鍙戦�鐭俊
      * @Description: generate otp for customer and send sms to customer
      * @modifyReason: 
      * @author liuyang
      * @param cmOtp
     * @return 
     */
    public OneTimePasswordVO getOtpForCust(CmOtpVO cmOtp);
    
    void   updateCustomer(Customer customer);
    
//     List<Product> getProduct(Long productId);
     
     /**
      * 
       * @Description: 更新客户资产信息
       * @Description: update the asset information of customer
       * @modifyReason: 
       * @author liuyang
       * @param asset
      */
     void updateAsset(Asset asset);
     
     public List<PResource> getResourceListbycustid(long custId, Integer pageSize, Integer pageNo);
     
     /**
      * 
       * @Description: 根据 custId 、restType、resSts、pageNo、pageSize分页查询resource 信息
       * @Description: query resource pageInfo by custId 、restType、resSts、pageNo、pageSize
       * @modifyReason: 
       * @author zhengzy
       * @param resType
       * @param resSts
       * @param pageSize
       * @param pageNo
       * @return
      */
     public PageInfo<PResource> getResourcePageInfoBycustId(Long custId,Integer resType,Integer resSts, Integer pageSize, Integer pageNo);
     
     public AssetCoinBlanceVO checkCoinBalance(Long custId);
     
     public CmResource addCmResource(CmResource cmResource);
     
     public CmResource getCmResourceById(Long id);
     
     public CmResource updateCmResource(CmResource cmResource);

     /**
      * 
       * @Description: 根据customer id 获得客户的绑定信息
       * @Description: get the customer bind info by customer id 
       * @modifyReason: 
       * @author liuyang
       * @param custId
       * @return
      */
    public CustomerBundleInfo getBundleInfoByCustId(Long custId);
    
    List<Asset> createCustomerAssets(Long custId);


    public void updateResourceStatus(Long resourceId, Integer status);
    
    public AssetCustVO getAssetInfoByCustIdAndAssetType(Long custId, Integer assetType);
    
    public List<ProductBasicInfoVO> getAppProductList(Long customerId, Integer pageNo, Integer pageSize);
    
    UserLoginVO getUserLoginInfo(String loginAcct,Integer userType);
    
    public void updateMNO(Long custId, Integer mnoId, String otp);
    
    public void updateBoundMobile(Long custId, String mobileNo, String otp, Integer countryCode);
    
    public List<DeviceInfoVO> getDeviceInfoByUserIdandOSType(Integer osType, Long userId);
    
    
}
