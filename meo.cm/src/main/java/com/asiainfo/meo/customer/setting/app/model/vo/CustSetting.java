package com.asiainfo.meo.customer.setting.app.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;

public class CustSetting
{
    private long                  custId;
    private List<CustomerSetting> customerSettingList;
    
    public long getCustId()
    {
        return custId;
    }
    
    public void setCustId(long custId)
    {
        this.custId = custId;
    }
    
    public List<CustomerSetting> getCustomerSettingList()
    {
        if (customerSettingList== null)
            customerSettingList = new ArrayList<CustomerSetting>();
        return customerSettingList;
    }
    
    public void setCustomerSettingList(List<CustomerSetting> customerSettingList)
    {
        this.customerSettingList = customerSettingList;
    }
    
}
