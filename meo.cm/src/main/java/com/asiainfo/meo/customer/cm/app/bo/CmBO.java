package com.asiainfo.meo.customer.cm.app.bo;

import com.asiainfo.meo.customer.cm.app.model.vo.CustomerVO;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfileVO;

public interface CmBO
{
 /*   *//**
     * @Description: (保存客户信息)
     * @Description: (save customer information)
     * @modifyReason:
     * @author lill
     * @param customer
     * @param customerInfo
     *//*
    void saveCustomer(Customer customer, CustomerInfo customerInfo);
    
    *//**
     * @Description: (更新客户信息)
     * @Description: (update customer information)
     * @modifyReason:
     * @author lill
     * @param customer
     * @param customerInfo
     * @param customerInfoHis
     *//*
    void updateCustomerProfile(CustomerProfileVO customerProfileVO);
    
    *//**
     * @Description: (获取客户信息)
     * @Description: (get customer information )
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     *//*
    CustomerVO getCustomer(long custId);
    */
}
