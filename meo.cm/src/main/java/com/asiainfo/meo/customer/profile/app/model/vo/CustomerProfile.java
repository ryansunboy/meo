package com.asiainfo.meo.customer.profile.app.model.vo;

import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;

public class CustomerProfile
{
    private Customer customer;
    private CustomerInfo customerInfo;
    private CustomerBundleInfo customerBundleInfo;
    public Customer getCustomer()
    {
        return customer;
    }
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    public CustomerInfo getCustomerInfo()
    {
        return customerInfo;
    }
    public void setCustomerInfo(CustomerInfo customerInfo)
    {
        this.customerInfo = customerInfo;
    }
    public CustomerBundleInfo getCustomerBundleInfo()
    {
        return customerBundleInfo;
    }
    public void setCustomerBundleInfo(CustomerBundleInfo customerBundleInfo)
    {
        this.customerBundleInfo = customerBundleInfo;
    }
    
    
}
