package com.asiainfo.meo.customer.setting.app.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;
import com.asiainfo.meo.customer.setting.app.model.entity.SettingDef;

public class SettingAndSettingDef
{
    private List<CustomerSetting> customerSettingList;
    
    private List<SettingDef> settingDefList;

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

    public List<SettingDef> getSettingDefList()
    {
        if(settingDefList == null)
            settingDefList = new ArrayList<SettingDef>();
        return settingDefList;
    }

    public void setSettingDefList(List<SettingDef> settingDefList)
    {
        this.settingDefList = settingDefList;
    }
    
    
}
