package com.asiainfo.meo.customer.profile.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfoHis;

 
public interface CustomerInfoRepository {
	
    List<CustomerInfo> getCustomerInfo(DetachedCriteria condition);
	
	Customer getCustomer(long custId);
	
	void saveCustomerInfo(CustomerInfo customerInfo);
	
	void saveCustomer(Customer customer);
	
	void saveCustomerInfoHis(CustomerInfoHis customerInfoHis);
	
	void updateCustomerInfo(CustomerInfo customerInfo);
	
	void updateCustomer(Customer customer);
	
	void updateCustomerInfoHis(CustomerInfoHis customerInfoHis);

    CustomerInfo getCustBasicInfo(Long custId);
	
    CustomerBundleInfo getCustomerBundleInfo(DetachedCriteria condition);

    void updateCustBundleInfo(CustomerBundleInfo customerBundleInfo);

    void saveCustomerBundleInfo(CustomerBundleInfo bundleInfo);
    
//	void updateCutomerLevelByCustId(int levelId, long custId);
//	CustomerProfile getCustomerProfile(long custId);
    
    CustomerBundleInfo getCustomerBundleInfo(Long custId);
    
	
}
 