package com.asiainfo.meo.customer.profile.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.cache.Cache;
import com.asiainfo.meo.common.core.cache.distributed.DistributedCache;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.define.CmConstantDefine;
import com.asiainfo.meo.customer.profile.app.bo.CustomerBO;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfoHis;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfile;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfileVO;
import com.asiainfo.meo.customer.profile.app.repository.CustomerInfoRepository;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;
import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class CustomerBOImpl implements CustomerBO
{
    private static final Log       LOG = LogFactory.getLog(CustomerBOImpl.class);
    
    @Resource
    private CustomerInfoRepository customerInfoRepository;
    
    @Resource 
    private CustomerRserviceBO customerRserviceBO;
    
    private List<CustomerInfo> getCustomerInfo(DetachedCriteria condition)
    {
        LOG.info("getCustomerInfo begin");
        return customerInfoRepository.getCustomerInfo(condition);
    }
    
    public void saveCustomerInfo(CustomerInfo customerInfo)
    {
        customerInfoRepository.saveCustomerInfo(customerInfo);
    }
    
    public void saveCustomer(Customer customer)
    {
        customerInfoRepository.saveCustomer(customer);
    }
    
    public void saveCustomerProfile(Customer customer, CustomerInfo customerInfo)
    {
        checkCustomer(customer);
        customerInfoRepository.saveCustomer(customer);
        customerInfo.setCustId(customer.getCustId());
        checkCustomerInfo(customerInfo);
        customerInfoRepository.saveCustomerInfo(customerInfo);
    }
    
    public void updateCustomerInfo(CustomerInfo customerInfo)
    {
        LOG.info("update customerInfo begin");
        checkCustomerInfo(customerInfo);
        customerInfoRepository.updateCustomerInfo(customerInfo);
    }
    
    public void updateCustomer(Customer customer)
    {
        LOG.info("updateCustomer begin");
        customerInfoRepository.updateCustomer(customer);
    }
    
    public void updateCustomerProfile(CustomerProfileVO customerProfileVO)
    {
        LOG.info("CmBO updateCustomerfile begin");
        Timestamp modifyDate = DateTimeUtil.getNow();
        CustomerProfile customerProfile = getCustomerProfile(customerProfileVO.getCustId());
        Customer customer = customerProfile.getCustomer();// old customer imformation
        CustomerInfo customerInfo = customerProfile.getCustomerInfo();// old customerInfo imformation
        CustomerBundleInfo customerBundleInfo = customerProfile.getCustomerBundleInfo();// old customerBundleInfo
        getNewCustomerFromCustomerProfileVO(customerProfileVO, customer, modifyDate);// set new customer
        updateCustomer(customer);
        getNewCustBundleInfoFromCustBundleInfo(customerProfileVO, customerBundleInfo, modifyDate);
        updateCustBundleInfo(customerBundleInfo);
        CustomerInfoHis customerInfoHis = getCustomerInfoHis(customerInfo, customerBundleInfo);
        saveCustomerInfoHis(customerInfoHis);
        getNewCustomerInfoFromCustomerProfileVO(customerProfileVO, customerInfo, modifyDate);// set new customerInfo
        updateCustomerInfo(customerInfo);
        LOG.info("CmBO updateCustomerfile end");
    }
    
    private void updateCustBundleInfo(CustomerBundleInfo customerBundleInfo)
    {
        customerInfoRepository.updateCustBundleInfo(customerBundleInfo);
    }
    
    private void getNewCustBundleInfoFromCustBundleInfo(CustomerProfileVO customerProfileVO,
            CustomerBundleInfo customerBundleInfo, Timestamp modifyDate)
    {
        customerBundleInfo.setModifyDate(modifyDate);
    }
    
    public CustomerProfile getCustomerProfile(long custId)
    {
        LOG.info("getCustomerProfile is begin");
        Customer customer = getCustomer(custId);
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setCustomer(customer);
        DetachedCriteria condition = DetachedCriteria.forClass(CustomerInfo.class).add(Restrictions.eq("custId", custId));
        
        List<CustomerInfo> customerInfoList = getCustomerInfo(condition);
        
        if (ValidateUtil.isNotEmpty(customerInfoList))
        {
            for (CustomerInfo customerInfo : customerInfoList)
                customerProfile.setCustomerInfo(customerInfo);
        }
        CustomerBundleInfo customerBundleInfo = getCustomerBundleInfo(custId, null);
        customerProfile.setCustomerBundleInfo(customerBundleInfo);
        return customerProfile;
    }
    
    public Customer getCustomer(long custId)
    {
        LOG.info("getCustomer begin");
        return customerInfoRepository.getCustomer(custId);
    }
    
    private void saveCustomerInfoHis(CustomerInfoHis customerInfoHis)
    {
        LOG.info("save customerInfoHis begin");
        customerInfoRepository.saveCustomerInfoHis(customerInfoHis);
    }
    
    private CustomerInfoHis getCustomerInfoHis(CustomerInfo customerInfo, CustomerBundleInfo customerBundleInfo)
    {
        CustomerInfoHis customerInfoHis = new CustomerInfoHis();
        customerInfoHis.setCustId(customerInfo.getCustId());
        customerInfoHis.setFirstName(customerInfo.getFirstName());
        customerInfoHis.setMiddleName(customerInfo.getMiddleName());
        customerInfoHis.setLastName(customerInfo.getLastName());
        customerInfoHis.setAddress(customerInfo.getAddress());
        customerInfoHis.setMsisdn(customerBundleInfo.getBundleAcct());
        customerInfoHis.setEmailAddr(customerInfo.getEmailAddr());
        customerInfoHis.setCountryCode(customerInfo.getCountryCode());
        customerInfoHis.setGender(customerInfo.getGender());
        customerInfoHis.setBirthday(customerInfo.getBirthday());
        customerInfoHis.setLanguageCode(customerInfo.getLanguageCode());
        customerInfoHis.setCityCode(customerInfo.getCityCode());
        customerInfoHis.setProvinceCode(customerInfo.getProvinceCode());
        customerInfoHis.setValidDate(DateTimeUtil.getNow());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "9999-12-30 12:59:59";
        ts = Timestamp.valueOf(tsStr);
        customerInfoHis.setExpireDate(ts);
        return customerInfoHis;
    }
    
    private void getNewCustomerInfoFromCustomerProfileVO(CustomerProfileVO customerProfileVO, CustomerInfo customerInfo,
            Timestamp modifyDate)
    {
        LOG.info("get new customerInfo begin");
        if (ValidateUtil.isEmpty(customerProfileVO)|| ValidateUtil.isEmpty(customerInfo))
            return;
        customerInfo.setFirstName(customerProfileVO.getFirstName());
        customerInfo.setMiddleName(customerProfileVO.getMiddleName());
        customerInfo.setLastName(customerProfileVO.getLastName());
        customerInfo.setAddress(customerProfileVO.getAddress());
        customerInfo.setEmailAddr(customerProfileVO.getEmailAddr());
        customerInfo.setCountryCode(customerProfileVO.getCountryCode());
        customerInfo.setGender(customerProfileVO.getGender());
        customerInfo.setBirthday(customerProfileVO.getBirthday());
        customerInfo.setLanguageCode(customerProfileVO.getLanguageCode());
        customerInfo.setCityCode(customerProfileVO.getCityCode());
        customerInfo.setProvinceCode(customerProfileVO.getProCode());
        customerInfo.setModifyDate(modifyDate);
    }
    
    private void getNewCustomerFromCustomerProfileVO(CustomerProfileVO customerProfileVO, Customer customer, Timestamp modifyDate)
    {
        LOG.info("getNewCustomer begin");
        customer.setModifyDate(modifyDate);
        // customer.setImgUrl(customerProfileVO.getAvatarUrl());
    }
    
    private void checkCustomer(Customer customer)
    {
    }
    
    private void checkCustomerInfo(CustomerInfo customerInfo)
    {
        if (ValidateUtil.isNull(customerInfo.getFirstName()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{CmConstantDefine.CUSTOMER_NAME });
        if (ValidateUtil.isNull(customerInfo.getEmailAddr()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{CmConstantDefine.EMAIL_ADDR });
    }
    
    public CustomerInfo getCustBasicInfo(Long custId)
    {
        if (ValidateUtil.isEmpty(custId))
            return null;
        CustomerInfo info = customerInfoRepository.getCustBasicInfo(custId);
        return info;
    }
    
    public CustomerBundleInfo getCustomerBundleInfo(Long custId, String bundleAcct)
    {
        LOG.info("get customerBundleInfo");
        DetachedCriteria condition = DetachedCriteria.forClass(CustomerBundleInfo.class);
        
        if (ValidateUtil.isNotNull(custId))
            condition.add(Restrictions.eq("custId", custId));
        if (ValidateUtil.isNotNull(bundleAcct))
            condition.add(Restrictions.eq("bundleAcct", bundleAcct));
        condition.add(Restrictions.eq("bundleAcctSts", CustomerBundleInfo.STS_ACTIVATE));
        
        return customerInfoRepository.getCustomerBundleInfo(condition);
    }
    
    public void saveCustomerBundleInfo(CustomerBundleInfo bundleInfo)
    {
        customerInfoRepository.saveCustomerBundleInfo(bundleInfo);
    }
    
    public void activateCustomer(Long custId)
    {
        if (ValidateUtil.isEmpty(custId))
            return;
        
        Customer customer = getCustomer(custId);
        customer.setSts(Customer.STS_ACTIVATE);
        updateCustomer(customer);
    }
    
    @Override
    public List<ProductBasicInfoVO> getAppProductList(Long customerId, Integer pageNo, Integer pageSize)
    {
        CustomerBundleInfo bundleInfo = getCustomerBundleInfo(customerId, null);
        List<Long> productIds = new ArrayList<Long>();
        if (ValidateUtil.isEmpty(bundleInfo) || ValidateUtil.isEmpty(bundleInfo.getMnoId())){
            throw new MeoException(CustomerErrorConstant.CUST_NOT_BIND_ACCT);
        }
        List<ProdMnoMapping> prodMnoMappingList = customerRserviceBO.getProdMnoMappingByMnoId(bundleInfo.getMnoId());
        for (ProdMnoMapping ProdMnoMapping : prodMnoMappingList)
        {
            productIds.add(ProdMnoMapping.getProductId());
        }
        List<Product> productList = customerRserviceBO.getAppProductList(productIds, pageNo, pageSize);
        List<ProductBasicInfoVO> productBasicInfoVOList = new ArrayList<ProductBasicInfoVO>();
        for (Product pdProduct : productList)
        {
            ProductBasicInfoVO ProductBasicInfoVO = getProductInfomation(pdProduct);
            if (ValidateUtil.isNotEmpty(ProductBasicInfoVO))
                productBasicInfoVOList.add(ProductBasicInfoVO);
        }
        return productBasicInfoVOList;
    }
    
    private ProductBasicInfoVO getProductInfomation(Product pdProduct)
    {
        ProductBasicInfoVO ProductBasicInfoVO = new ProductBasicInfoVO();
        ProductBasicInfoVO.setProductId(pdProduct.getProductId());
        ProductBasicInfoVO.setProductName(pdProduct.getProductName());
        ProductBasicInfoVO.setProductDescription(pdProduct.getDescription());
        ProductBasicInfoVO.setDenomination(pdProduct.getDenomination());
        if (ValidateUtil.isNotEmpty(pdProduct.getCategoryId()))
        {
            List<CategoryDefine> campaignTypeList = customerRserviceBO.getCategoryEnum(pdProduct.getCategoryId().longValue(), CategoryDefine.PRODUCT_TYPE);
            for (CategoryDefine campaignType : campaignTypeList)
            {
                ProductBasicInfoVO.setProductCategoryId(pdProduct.getCategoryId());
                ProductBasicInfoVO.setProductCategory(campaignType!= null ? campaignType.getCategoryName() : null);
            }
        }
        if (pdProduct.getDenominationUnit()!= null)
        {
            EnumDefine denominationUnit = customerRserviceBO.getEnumDefByEnumCode(
                    String.valueOf(pdProduct.getDenominationUnit()), Integer.valueOf(EnumDefine.DENOMINATION_UNIT));
            ProductBasicInfoVO.setDenominationUnitId(pdProduct.getDenominationUnit());
            ProductBasicInfoVO.setDenominationUnit(denominationUnit!= null ? denominationUnit.getEnumName() : null);
        }
        if (pdProduct.getProdSts()!= null)
        {
            EnumDefine prodSts = customerRserviceBO.getEnumDefByEnumCode(String.valueOf(pdProduct.getProdSts()),
                    Integer.valueOf(EnumDefine.PRODUCT_STATUS_TYPE));
            ProductBasicInfoVO.setProductStsId(pdProduct.getProdSts());
            ProductBasicInfoVO.setProductSts(prodSts!= null ? prodSts.getEnumName() : "");
        }
        return ProductBasicInfoVO;
    }

    @Override
    public void updateMNO(Long custId, Integer mnoId, String otp)
    {
        CustomerBundleInfo customerBundleInfo = customerInfoRepository.getCustomerBundleInfo(custId);
        String otpCacheKey = null;
        if(ValidateUtil.isNotEmpty(customerBundleInfo.getCountryCode())){
            otpCacheKey=customerBundleInfo.getCountryCode().toString().concat(customerBundleInfo.getBundleAcct());
        }else {
            otpCacheKey=customerBundleInfo.getBundleAcct();
        }
        Cache otpCache = ServiceLocatorFactory.getService(DistributedCache.class);
        OneTimePasswordVO password = otpCache.get(SysConstantDefine.OTP_CACHE_NAME, otpCacheKey, OneTimePasswordVO.class);
        Long nowTime = DateTimeUtil.getCurrentTimeMillis();
        if (ValidateUtil.isEmpty(password)|| password.isUsed()|| !otp.equals(password.getOtp()) || nowTime > password.getExpireDate())
            throw new MeoException(SysErrorCodeDefine.OTP_IS_INVALID);
        CustomerBundleInfo newCustomerBundleInfo = new CustomerBundleInfo();
        
        newCustomerBundleInfo.setBundleAcctSts(CustomerBundleInfo.STS_ACTIVATE);
        newCustomerBundleInfo.setBundleAcct(customerBundleInfo.getBundleAcct());
        newCustomerBundleInfo.setCountryCode(customerBundleInfo.getCountryCode());
        newCustomerBundleInfo.setBundleAcctType(CustomerBundleInfo.BUNDLE_ACCT_TYPE_MSISDN);
        newCustomerBundleInfo.setCustId(customerBundleInfo.getCustId());
        newCustomerBundleInfo.setMnoId(mnoId);
        customerInfoRepository.saveCustomerBundleInfo(newCustomerBundleInfo);
        
        customerBundleInfo.setModifyDate(new Timestamp(nowTime));
        customerBundleInfo.setBundleAcctSts(CustomerBundleInfo.STS_DEACTIVATE);
        customerInfoRepository.updateCustBundleInfo(customerBundleInfo);
        password.setUsed(true);
    }

    @Override
    public void updateMobileNumber(Long custId, String mobileNo, String otp, Integer countryCode)
    {
        CustomerBundleInfo customerBundleInfo = customerInfoRepository.getCustomerBundleInfo(custId);
        Cache otpCache = ServiceLocatorFactory.getService(DistributedCache.class);
        OneTimePasswordVO password = otpCache.get(SysConstantDefine.OTP_CACHE_NAME, countryCode.toString() + 
                mobileNo, OneTimePasswordVO.class);
        Long nowTime = DateTimeUtil.getCurrentTimeMillis();
        if (ValidateUtil.isEmpty(password)|| password.isUsed()|| !otp.equals(password.getOtp()) || nowTime > password.getExpireDate())
            throw new MeoException(SysErrorCodeDefine.OTP_IS_INVALID);
        CustomerBundleInfo newCustomerBundleInfo = new CustomerBundleInfo();
        
        newCustomerBundleInfo.setBundleAcctSts(CustomerBundleInfo.STS_ACTIVATE);
        newCustomerBundleInfo.setBundleAcct(mobileNo);
        newCustomerBundleInfo.setCountryCode(countryCode);
        newCustomerBundleInfo.setMnoId(customerBundleInfo.getMnoId());
        newCustomerBundleInfo.setBundleAcctType(CustomerBundleInfo.BUNDLE_ACCT_TYPE_MSISDN);
        newCustomerBundleInfo.setCustId(customerBundleInfo.getCustId());
        customerInfoRepository.saveCustomerBundleInfo(newCustomerBundleInfo);
        
        customerBundleInfo.setModifyDate(new Timestamp(nowTime));
        customerBundleInfo.setBundleAcctSts(CustomerBundleInfo.STS_DEACTIVATE);
        customerInfoRepository.updateCustBundleInfo(customerBundleInfo);
        password.setUsed(true);
        
    }

    
    
    
}
