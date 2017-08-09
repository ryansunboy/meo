package com.asiainfo.meo.activity.business.bean.impl;

import java.util.List;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.product.profile.app.constant.ProductErrorConstant;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingInfoVO;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class RedeemProductBean implements ActivityBean
{
    ActivityRepository activityRepository;
    
    ActivityRserviceBO activityRserviceBO;
    
    private Long       custId;
   
    private Long       campaignId;
    
    private static final String ACTION_ID = "1003";
    
    public void init(Object... input)
    {
        custId = (Long) input[0];
        campaignId = (Long) input[1];
        activityRepository = ServiceLocatorFactory.getService(ActivityRepository.class);
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
    }
    
    public void validate(Object... input)
    {
        if (ValidateUtil.isNull(custId))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{"custId" });
        }
        if (ValidateUtil.isNull(campaignId))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{"campaignId" });
        }
    }
    
    public Object business(Object... input)
    {
        Long productId = activityRserviceBO.getProductIdByCampaignId(campaignId);
        if (ValidateUtil.isNull(productId))
        {
            throw new MeoException(ProductErrorConstant.PRODUCT_ID_IS_NULL);
        }
        Product product = activityRserviceBO.getProductProfileByProductId(productId);
        if (ValidateUtil.isNull(product))
        {
            throw new MeoException(ProductErrorConstant.PRODUCT_IS_NOT_FOUND);
        }
        CustomerBundleInfo bundleInfo =activityRserviceBO.getBundleInfoByCustId(custId);
        if(ValidateUtil.isEmpty(bundleInfo)){
            throw new MeoException(CustomerErrorConstant.CUST_NOT_BIND_ACCT);
        }
        List<ProdMnoMappingInfoVO> prodMnoMappingInfoVOList = activityRserviceBO.getProductMappingInfoWithMNOByProductId(productId);
        for (final ProdMnoMappingInfoVO prodMnoMappingInfoVO : prodMnoMappingInfoVOList) { 
            if (!prodMnoMappingInfoVO.getMnoId().equals(bundleInfo.getMnoId()))
            {
                throw new MeoException(ProductErrorConstant.MNO_PROD_NOT_FOUND);
            }
        }
        
        //long requireCoins = activityRserviceBO.getProductRequireCoins(productId);
        long requireCoins = activityRserviceBO.getRequireCoinsByCampaignIdAndActionId(ACTION_ID, campaignId);
        long custBalance = activityRserviceBO.getCustBalance(custId);
        if (custBalance< requireCoins)
        {
            throw new MeoException(ActivityErrorCodeDefine.CUST_BALANCE_NOT_ENOUGH);
        }
        Asset asset = new Asset();
        asset.setCustId(custId);
        asset.setAssetType(Asset.ASSET_TYPE_COINS);
        asset.setAmount(0- requireCoins);
        activityRserviceBO.updateAsset(asset);
        return null;
    }
    
}
