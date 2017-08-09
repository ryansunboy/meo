package com.asiainfo.meo.customer.profile.app.bo;

import java.util.List;

import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfile;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfileVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;

public interface CustomerBO
{
    
    /**
     * @Description: (获取customerInfo list)
     * @Description: (get customerInfo list)
     * @modifyReason:
     * @author lill
     * @param map
     * @return
     */
   // List<CustomerInfo> getCustomerInfo(Map<String, Object> map);
    
    void saveCustomerInfo(CustomerInfo customerInfo);
    
    void saveCustomer(Customer customer);
    
    /**
     * @Description: (用中文描述一下这个方法)
     * @Description: (English description)
     * @modifyReason:
     * @author lill
     * @param customer
     * @param customerInfo
     */
    void saveCustomerProfile(Customer customer, CustomerInfo customerInfo);
    
    // void updateCustomerProfile(Customer customer, CustomerInfo customerInfo);
    /**
     * @Description: (更新customer和customerInfo)
     * @Description: (update customer and customerInfo )
     * @modifyReason:
     * @author lill
     * @param customerProfileVO
     */
//    void updateCustomerProfile(CustomerProfileVO customerProfileVO);
    
    void updateCustomerInfo(CustomerInfo customerInfo);
    
    void updateCustomer(Customer customer);
    
    /**
     * @Description: (根据custId获取customer信息)
     * @Description: (get customer order by custId)
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     */
    Customer getCustomer(long custId);
    
    CustomerBundleInfo getCustomerBundleInfo(Long custId,String bundleAcct);
    
    void saveCustomerBundleInfo(CustomerBundleInfo bundleInfo);
    
    /**
     * @Description: (根据custId获取customer和customerInfo信息)
     * @Description: (get customer and customerInfo order by custId)
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     */
    CustomerProfile getCustomerProfile(long custId);
    
    /**
     * 
      * @Description: (根据customer id 获取customer 基本信息)
      * @Description: (get customer basic information by customer id )
      * @modifyReason: 
      * @author liuyang
      * @param custId
      * @return
     */
    CustomerInfo getCustBasicInfo(Long custId);

    void activateCustomer(Long custId);
    
    public List<ProductBasicInfoVO> getAppProductList(Long customerId, Integer pageNo, Integer pageSize);
    
    public void updateMNO(Long custId, Integer mnoId, String otp);
    
    public void updateMobileNumber(Long custId, String mobileNo, String otp, Integer countryCode);
    
}
