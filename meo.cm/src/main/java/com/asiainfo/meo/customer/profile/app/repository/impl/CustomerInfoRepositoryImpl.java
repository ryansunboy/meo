package com.asiainfo.meo.customer.profile.app.repository.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.profile.app.constant.ConstantDefine;
import com.asiainfo.meo.customer.profile.app.constant.CustomerSqlConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfoHis;
import com.asiainfo.meo.customer.profile.app.repository.CustomerInfoRepository;

public class CustomerInfoRepositoryImpl implements CustomerInfoRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public void saveCustomerInfo(CustomerInfo customerInfo)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        customerInfo.setId(sequence.next("SEQ_CUSTOMER_INFO_ID"));
        customerInfo.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(customerInfo);
        
    }
    
    public void saveCustomer(Customer customer)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        customer.setCustId(sequence.next("SEQ_CUSTOMER_ID"));
        customer.setLevelId(Customer.LEVEL_ID);
        customer.setRegTime(DateTimeUtil.getNow());
        customer.setCreateDate(DateTimeUtil.getNow());
        customer.setLevelValidDate(DateTimeUtil.getNow());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "9999-12-30 12:59:59";
        ts = Timestamp.valueOf(tsStr);
        customer.setLevelExpiredDate(ts);
        hibernateRepository.saveObject(customer);
        
    }
    
    @SuppressWarnings("unchecked")
    public List<CustomerInfo> getCustomerInfo(DetachedCriteria condition)
    {
        return (List<CustomerInfo>) hibernateRepository.findByCriteria(condition);
    }
    
    public Customer getCustomer(long custId)
    {
        
        return hibernateRepository.get(Customer.class, custId);
    }
    
    public void updateCustomerInfo(CustomerInfo customerInfo)
    {
        customerInfo.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.saveOrUpdate(customerInfo);// .updateObject(customerInfo);
    }
    
    public void updateCustomer(Customer customer)
    {
        customer.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.saveOrUpdate(customer);// .saveOrUpdate(customer);
        
    }
    
    public void saveCustomerInfoHis(CustomerInfoHis customerInfoHis)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        customerInfoHis.setId(sequence.next("SEQ_CUSTOMER_INFO_HIS_ID"));
        hibernateRepository.saveObject(customerInfoHis);
        
    }
    
    public void updateCustomerInfoHis(CustomerInfoHis customerInfoHis)
    {
        hibernateRepository.updateObject(customerInfoHis);
        
    }
    
    public void updateCutomerLevelByCustId(int levelId, long custId)
    {
        hibernateRepository.executeByNativeSql(CustomerSqlConstant.UPDATE_LEVELID_BY_CUSTID, levelId, custId);
        
    }
    
    @SuppressWarnings("unchecked")
    public CustomerInfo getCustBasicInfo(Long custId)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(CustomerInfo.class).add(Restrictions.eq("custId", custId))
                .add(Restrictions.eq("sts", ConstantDefine.STS_ACTIVE));
        
        List<CustomerInfo> info = (List<CustomerInfo>) hibernateRepository.findByCriteria(condition);
        
        if (ValidateUtil.isNotNull(info)&& info.size()> 0)
        {
            return info.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public CustomerBundleInfo getCustomerBundleInfo(DetachedCriteria condition)
    {
        
        List<CustomerBundleInfo> customerBundleInfoList = (List<CustomerBundleInfo>) hibernateRepository
                .findByCriteria(condition);
        if (ValidateUtil.isNotNull(customerBundleInfoList)&& customerBundleInfoList.size()> 0)
            return customerBundleInfoList.get(0);
        return null;
    }
    
    public void updateCustBundleInfo(CustomerBundleInfo customerBundleInfo)
    {
        hibernateRepository.updateObject(customerBundleInfo);
        
    }
    
    public void saveCustomerBundleInfo(CustomerBundleInfo bundleInfo)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        bundleInfo.setId(sequence.next("SEQ_CUSTOMER_BUNDLE_INFO_ID"));
        bundleInfo.setSts(CustomerBundleInfo.STS_ACTIVATE);
        bundleInfo.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(bundleInfo);
    }

    @Override
    public CustomerBundleInfo getCustomerBundleInfo(Long custId)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(CustomerBundleInfo.class)
                .add(Restrictions.eq("custId", custId))
                .add(Restrictions.eq("bundleAcctType", CustomerBundleInfo.BUNDLE_ACCT_TYPE_MSISDN))
                .add(Restrictions.eq("bundleAcctSts", CustomerBundleInfo.STS_ACTIVATE));
        return (CustomerBundleInfo) hibernateRepository.findUniqueObjectByCriteria(condition);
    }
}