package com.asiainfo.meo.customer.setting.app.bo;

import java.util.List;
import java.util.Map;

import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;
import com.asiainfo.meo.customer.setting.app.model.entity.SettingDef;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;

public interface CustomerSettingBO
{
    
    // void saveSettingDef(SettingDef settingDef);
    
    /**
     * @Description: ()
     * @Description: (English description)
     * @modifyReason:
     * @author lill
     * @param customerSetting
     */
    void saveCustomerSetting(CustomerSetting customerSetting);
    
    /**
      * @Description: 更新用户Setting信息
      * @Description: update setting imformation of person
      * @author zhaozx
      * @param custId
      * @param settingVOList
      */
    void updateCustomerSetting(long custId, List<SettingVO> settingVOList);
    
    
    /**
     * @Description: (根据条件查询个人Setting信息,先查个人Setting信息，没有去SettingDef取默认值)
     * @Description: (get Setting imformation of person by param )
     * @modifyReason:
     * @author lill
     * @param map
     * @return
     */
    List<SettingVO> getSetting(Long custId, Integer settingType);
    
    /**
     * @Description: 初始化customer setting
     * @Description: init customer setting
     * @author zhaozx
     * @param custId
     */
    void createCustomerSetting(long custId);
    /**
     * @Description: (插入个人setting信息)
     * @Description: (insert imformation of person )
     * @modifyReason:
     * @author lill
     * @param list
     */
    // void saveCustIdSetting(List<CustSetting> list);
    
    /**
     * @Description: (更新setting信息)
     * @Description: (update imformation of person)
     * @modifyReason:
     * @author lill
     * @param list
     */
    // void updateCustIdSetting(List<CustSetting> list);
    
    // void updateSetting1(CustomerSetting customerSetting);
    
    // void updateSettingDef(SettingDef settingDef);
}
