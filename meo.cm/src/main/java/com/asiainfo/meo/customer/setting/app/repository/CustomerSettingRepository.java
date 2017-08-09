package com.asiainfo.meo.customer.setting.app.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;
import com.asiainfo.meo.customer.setting.app.model.entity.SettingDef;


 
public interface CustomerSettingRepository
{
    
//    void saveSettingDef(SettingDef settingDef);
    
    void updateSettingDef(SettingDef settingDef);
  
    void saveCustomerSetting(CustomerSetting customerSetting);
    
    void updateCustomerSetting(CustomerSetting customerSetting);
    
    /**
      * @Description: 保存或修改customer setting list
      * @Description: save or update customer setting list
      * @author zhaozx
      * @param customerSettingList customer setting list
      */
    void saveOrUpdateCustSettingList(List<CustomerSetting> customerSettingList);
    /**
      * @Description: 获得下一个序列id
      * @Description: get next sequence
      * @author zhaozx
      * @return
      */
    long getCustSettingNextSequence();
    
    public List<CustomerSetting> getCustomerSetting(DetachedCriteria criteria);
    
    public List<SettingDef> getSettingDef(DetachedCriteria criteria);
}
