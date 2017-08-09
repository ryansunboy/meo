package com.asiainfo.meo.customer.setting.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.define.CmConstantDefine;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;
import com.asiainfo.meo.customer.setting.app.bo.CustomerSettingBO;
import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;
import com.asiainfo.meo.customer.setting.app.model.entity.SettingDef;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;
import com.asiainfo.meo.customer.setting.app.repository.CustomerSettingRepository;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;

/**
 * @ClassName: CustomerSettingBOImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lill
 * @date 2015-3-4 下午07:21:21
 */
public class CustomerSettingBOImpl implements CustomerSettingBO
{
    private static final Log          LOG = LogFactory.getLog(CustomerSettingBOImpl.class);
    
    @Resource
    private CustomerSettingRepository customerSettingRepository;
    
    
    @Resource 
    private CustomerRserviceBO customerRserviceBO;
    
    /**
     * @Description: 初始化customer setting
     * @Description: init customer setting
     * @author zhaozx
     * @param custId
     */
    public void createCustomerSetting(long custId)
    {
        /*
         * Map<String, Object> custSettingDefCondition = new HashMap<String, Object>(); custSettingDefCondition.put("sts",
         * SettingDef.STS_USED);
         */
        DetachedCriteria custSettingDefCondition = DetachedCriteria.forClass(SettingDef.class).add(
                Restrictions.eq("sts", SettingDef.STS_USED));
        
        // get setting default by setttingId
        List<SettingDef> settingDefList = getSettingDef(custSettingDefCondition);
        
        Timestamp createDate = DateTimeUtil.getNow();
        for (SettingDef settingDef : settingDefList)
        {
            CustomerSetting customerSetting = new CustomerSetting();
            
            customerSetting.setCreateDate(createDate);
            customerSetting.setModifyDate(createDate);
            customerSetting.setCustId(custId);
            customerSetting.setSettingId(settingDef.getSettingId());
            customerSetting.setSettingValue(settingDef.getDefaultValue());
            customerSetting.setSettingType(settingDef.getSettingType());
            customerSetting.setSts(CustomerSetting.STS_USED);
            
            saveCustomerSetting(customerSetting);// save customer setting
        }
    }
    
    /**
     * @Description: 更新用户Setting信息
     * @Description: update setting imformation of person
     * @author zhaozx
     * @param custId
     * @param settingId
     * @param settingValue
     */
    public void updateCustomerSetting(long custId, List<SettingVO> settingVOList)
    {
        LOG.debug("update customerSetting on customerSettingBO begin");
        
        if (settingVOList!= null&& settingVOList.size()> 0)
        {
            // List<CustomerSetting> saveOrUpdatecustSettingList = new ArrayList<CustomerSetting>();
            
            for (SettingVO settingVO : settingVOList)
            {
                if (settingVO.getSettingId()== null)
                {
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                            new Object[]{CmConstantDefine.SETTING });
                }
                if (settingVO.getSettingValue()== null)
                {
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                            new Object[]{CmConstantDefine.SETTING });
                }
                
                int settingId = settingVO.getSettingId();
                
                int settingValue = settingVO.getSettingValue();
                /*
                 * Map<String, Object> custSettingCondition = new HashMap<String, Object>(); custSettingCondition.put("custId",
                 * custId); custSettingCondition.put("settingId", settingId); custSettingCondition.put("sts",
                 * CustomerSetting.STS_USED);
                 */
                DetachedCriteria custSettingCondition = DetachedCriteria.forClass(CustomerSetting.class)
                        .add(Restrictions.eq("custId", custId)).add(Restrictions.eq("settingId", settingId))
                        .add(Restrictions.eq("sts", CustomerSetting.STS_USED));
                
                // get customer setting by custId and settingId
                List<CustomerSetting> customerSettingList = getCustomerSetting(custSettingCondition);
                
                if (customerSettingList== null|| customerSettingList.size()== 0)
                {
                    // save new customer setting
                    CustomerSetting customerSetting = initCustomerSetting(custId, settingId, settingValue);
                    
                    saveCustomerSetting(customerSetting);
                    // saveOrUpdatecustSettingList.add(customerSetting);
                }
                else
                {
                    CustomerSetting customerSetting = customerSettingList.get(0);
                    
                    customerSetting.setSettingValue(settingValue);
                    customerSetting.setModifyDate(DateTimeUtil.getNow());
                    
                    customerSettingRepository.updateCustomerSetting(customerSetting);
                    // saveOrUpdatecustSettingList.add(customerSetting);
                }
            }
            // saveOrUpdateSettingList(saveOrUpdatecustSettingList);
        }
        
        LOG.debug("update customerSetting on customerSettingBO end");
    }
    
    public void updateSettingDef(SettingDef settingDef)
    {
        customerSettingRepository.updateSettingDef(settingDef);
    }
    
   
    
    /**
     * @Description: 查询customer setting
     * @Description: get customer setting list
     * @author zhaozx
     * @param custId
     * @param settingType
     * @return List<SettingVO>
     */
    public List<SettingVO> getSetting(Long custId, Integer settingType)
    {
        LOG.info("getSetting on customerSettingBO begin");
        List<SettingVO> settingVOlist = new ArrayList<SettingVO>();
        DetachedCriteria custSettingCondition = DetachedCriteria.forClass(CustomerSetting.class)
                .add(Restrictions.eq("custId", custId)).add(Restrictions.eq("sts", CustomerSetting.STS_USED));
     
        if (ValidateUtil.isNotNull(settingType))
        {
            custSettingCondition.add(Restrictions.eq("settingType", settingType));
        }
        // get customer setting by custId and settingType
        List<CustomerSetting> customerSettingList = getCustomerSetting(custSettingCondition);
        
        if (customerSettingList!= null&& customerSettingList.size()> 0)
        {
            for (CustomerSetting customerSetting : customerSettingList)
            {
                SettingVO settingVO = customerSettingMapSettingVO(customerSetting);// get setting value from customerSetting
                
                int settingId = customerSetting.getSettingId();
                DetachedCriteria custSettingDefCondition=DetachedCriteria.forClass(SettingDef.class)
                        .add(Restrictions.eq("settingId", settingId)).add(Restrictions.eq("sts", SettingDef.STS_USED));
                
                // get setting default by setttingId
                List<SettingDef> settingDefList = getSettingDef(custSettingDefCondition);
                
                settingVO.setSettingName(settingDefList.get(0).getSettingName());
                List<EnumDefine> enumDefinelist = customerRserviceBO.getEnumByCode(customerSetting.getSettingType() + "", SettingDef.SETTING_TYPE);
                if (enumDefinelist == null || enumDefinelist.size() <= 0)
                {
                    throw new MeoException("SettingType is not exist");
                }
                List<EnumDefine> enumDefinelist1 = customerRserviceBO.getEnumByCode(customerSetting.getSettingValue() + "", enumDefinelist.get(0).getEnumCode());
                if (enumDefinelist1 == null || enumDefinelist1.size() <= 0)
                {
                    throw new MeoException("SettingType is not exist");
                }
                settingVO.setSettingValueName(enumDefinelist1.get(0).getEnumName());
                settingVOlist.add(settingVO);
            }
        }
        else
        {
            DetachedCriteria custSettingDefCondition=DetachedCriteria.forClass(SettingDef.class)
                    .add(Restrictions.eq("sts", SettingDef.STS_USED));
            
            if(ValidateUtil.isNotNull(settingType))
            {
                custSettingDefCondition.add(Restrictions.eq("settingType", settingType));
            }
            List<SettingDef> settingDefList = getSettingDef(custSettingDefCondition);
            
            for (SettingDef settingDef : settingDefList)
            {
                SettingVO settingVO = settingDefMapingSettingVO(settingDef);
                
                settingVOlist.add(settingVO);
            }
            
        }
        LOG.info("getSetting on customerSettingBO end");
        return settingVOlist;
    }
    
    public void saveCustomerSetting(CustomerSetting customerSetting)
    {
        
        customerSettingRepository.saveCustomerSetting(customerSetting);
    }
    
    private List<SettingDef> getSettingDef(DetachedCriteria condition)
    {
        return customerSettingRepository.getSettingDef(condition);
    }
    
    private List<CustomerSetting> getCustomerSetting(DetachedCriteria condition)
    {
        return customerSettingRepository.getCustomerSetting(condition);
    }
    
    private void saveOrUpdateSettingList(List<CustomerSetting> custSettingList)
    {
        customerSettingRepository.saveOrUpdateCustSettingList(custSettingList);
    }
    
    private long getNextSequence()
    {
        return customerSettingRepository.getCustSettingNextSequence();
    }
    
    /**
     * @Description: customerSetting 映射settingVO
     * @Description: customerSetting entity mapping settingVO entity
     * @author zhaozx
     * @param customerSetting
     * @return SettingVO
     */
    private SettingVO customerSettingMapSettingVO(CustomerSetting customerSetting)
    {
        SettingVO settingVO = new SettingVO();
        settingVO.setSettingId(customerSetting.getSettingId());
        settingVO.setSettingValue(customerSetting.getSettingValue());
        settingVO.setSettingType(customerSetting.getSettingType());
        return settingVO;
    }
    
    /**
     * @Description: settingDef 映射 settingVO
     * @Description: settingDef map settingVO
     * @author zhaozx
     * @param settingDef
     * @return SettingVO
     */
    private SettingVO settingDefMapingSettingVO(SettingDef settingDef)
    {
        SettingVO settingVO = new SettingVO();
        
        settingVO.setSettingId(settingDef.getSettingId());
        settingVO.setSettingType(settingDef.getSettingType());
        settingVO.setSettingName(settingDef.getSettingName());
        settingVO.setSettingValue(settingDef.getDefaultValue());
        
        return settingVO;
    }
    
    /**
     * @Description: 初始化customer Setting
     * @Description: init customer Setting
     * @author zhaozx
     * @param custId
     * @param settingId
     * @param settingValue
     * @return
     */
    private CustomerSetting initCustomerSetting(long custId, int settingId, int settingValue)
    {
        
        CustomerSetting customerSetting = new CustomerSetting();
        
        /*
         * Map<String, Object> custSettingDefCondition = new HashMap<String, Object>(); custSettingDefCondition.put("settingId",
         * settingId);
         */
        
        DetachedCriteria custSettingDefCondition = DetachedCriteria.forClass(SettingDef.class).add(
                Restrictions.eq("settingId", settingId));
        
        // get setting default by setttingId
        List<SettingDef> settingDefList = getSettingDef(custSettingDefCondition);
        if (ValidateUtil.isEmpty(settingDefList))
        {
            throw new MeoException(CustomerErrorConstant.IS_ERROR, new Object[]{CmConstantDefine.SETTING });
        }
        Timestamp createDate = DateTimeUtil.getNow();
        // customerSetting.setDefId(getNextSequence());//set sequence
        customerSetting.setCreateDate(createDate);
        customerSetting.setModifyDate(createDate);
        customerSetting.setCustId(custId);
        customerSetting.setSettingId(settingId);
        customerSetting.setSettingValue(settingValue);
       
        if(settingDefList.size()>0)
        {
            customerSetting.setSettingType(settingDefList.get(0).getSettingType());
        }
        
        customerSetting.setSts(CustomerSetting.STS_USED);
        
        return customerSetting;
    }
}
