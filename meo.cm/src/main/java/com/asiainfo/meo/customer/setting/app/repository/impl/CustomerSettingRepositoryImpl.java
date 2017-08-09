package com.asiainfo.meo.customer.setting.app.repository.impl;

import java.util.List;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;
import com.asiainfo.meo.customer.setting.app.model.entity.SettingDef;
import com.asiainfo.meo.customer.setting.app.repository.CustomerSettingRepository;

public class CustomerSettingRepositoryImpl implements CustomerSettingRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    
    public void updateSettingDef(SettingDef settingDef)
    {
        
    }
    
    public void saveCustomerSetting(CustomerSetting customerSetting)
    {
        customerSetting.setId(getCustSettingNextSequence());
        if (customerSetting.getSts()== null)
        {
            customerSetting.setSts(CustomerSetting.STS_USED);
        }
        customerSetting.setCreateDate(DateTimeUtil.getNow());
        customerSetting.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(customerSetting);
    }
    
    public void updateCustomerSetting(CustomerSetting customerSetting)
    {
        customerSetting.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(customerSetting);
    }
    
    /**
     * @Description: 保存或修改customer setting list
     * @Description: save or update customer setting list
     * @author zhaozx
     * @param customerSettingList customer setting list
     */
    public void saveOrUpdateCustSettingList(List<CustomerSetting> customerSettingList)
    {
        hibernateRepository.saveOrUpdataAll(customerSettingList);
    }
    
    /**
     * @Description: 获得下一个序列id
     * @Description: get next sequence
     * @author zhaozx
     * @return
     */
    public long getCustSettingNextSequence()
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        return sequence.next("SEQ_CUSTOMER_SETTING_ID");
    }
    
    @SuppressWarnings("unchecked")
    public List<CustomerSetting> getCustomerSetting(DetachedCriteria criteria)
    {
        return  (List<CustomerSetting>) hibernateRepository.findByCriteria(criteria);
    }

    @SuppressWarnings("unchecked")
    public List<SettingDef> getSettingDef(DetachedCriteria criteria)
    {
        return (List<SettingDef>) hibernateRepository.findByCriteria(criteria);
    }
    
}
