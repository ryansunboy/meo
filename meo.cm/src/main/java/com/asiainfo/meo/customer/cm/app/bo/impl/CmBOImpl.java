package com.asiainfo.meo.customer.cm.app.bo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.customer.cm.app.bo.CmBO;

public class CmBOImpl implements CmBO
{
    private static final Log LOG = LogFactory.getLog(CmBOImpl.class);
  /*  
    @Resource
    private CustomerBO       customerBO;
    
    @Resource
    private LevelBO          levelBO;
    
    @Resource
    private AssetBO          assetBO;
    

    
    public void saveCustomer(Customer customer, CustomerInfo customerInfo)
    {
        customerBO.saveCustomerProfile(customer, customerInfo);
    }
    
    public CustomerVO getCustomer(long custId)
    {
        LOG.debug("cmBO getCustomer begin...");
        CustomerVO customerVO = new CustomerVO();
        Customer customer = customerBO.getCustomer(custId);
        customerVO.setCustomer(customer);
        CustomerBundleInfo customerBundleInfo = customerBO.getCustomerBundleInfo(custId, null);
        customerVO.setCustomerBundleInfo(customerBundleInfo);
        String mnoCode = systemBo.getEnumDefByEnumCode(String.valueOf(customerBundleInfo.getMnoId()), CustomerVO.MNO_ENUM_TYPE)
                .getEnumName();
        customerVO.setMnoCode(mnoCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custId", custId);
        List<CustomerInfo> customerInfolist = customerBO.getCustomerInfo(map);
        if (ValidateUtil.isNotEmpty(customerInfolist))
        {
            for (CustomerInfo customerInfo : customerInfolist)
            {
                customerVO.setCustomerInfo(customerInfo);
                if (ValidateUtil.isNotNull(customerInfo.getLanguageCode()))
                {
                    String languageCode = systemBo.getEnumDefByEnumCode(customerInfo.getLanguageCode().toString(),
                            CustomerVO.LANGUAGE__ENUM_TYPE).getEnumName();
                    customerVO.setLanguageCode(languageCode);
                }
                if (ValidateUtil.isNotNull(customerInfo.getGender()))
                {
                    String genderName = systemBo.getEnumDefByEnumCode(customerInfo.getGender().toString(),
                            CustomerVO.GENDER_ENUM_TYPE).getEnumName();
                    customerVO.setGenderName(genderName);
                }
            }
        }
        List<LevelDef> levelDefList = levelBO.getLevelDef(customer.getLevelId(), LevelDef.CUSTOMER_LEVEL);
        if (ValidateUtil.isNotEmpty(levelDefList))
        {
            for (LevelDef levelDef : levelDefList)
                customerVO.setLevelDef(levelDef);
        }
        map.put("assetType", Asset.ASSET_TYPE_TOTAL_COINS);
        List<Asset> assetList = assetBO.getAsset(map);
        if (ValidateUtil.isNotEmpty(assetList))
        {
            for (Asset asset : assetList)
            {
                long coins = asset.getAmount();
                customerVO.setCoins(coins);
            }
        }
        
        map.put("custId", custId);
        map.put("assetType", Asset.ASSET_TYPE_EXPERIENCE);
        List<Asset> assetlist = assetBO.getAsset(map);
        if (ValidateUtil.isNotEmpty(assetList))
        {
            for (Asset asset : assetlist)
            {
                long experience = asset.getAmount();
                customerVO.setExperience(experience);
            }
        }
        customerVO.setSocialType(CustomerVO.MEO_SOCIAL_TYPE);
        return customerVO;
    }
    
    public void updateCustomerProfile(CustomerProfileVO customerProfileVO)
    {
        customerBO.updateCustomerProfile(customerProfileVO);
    }
    */
}
