package com.asiainfo.meo.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCustVO;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.define.ActivityEnumCodeDefine;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdPriceVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.product.service.provide.ProductPserviceBO;
import com.asiainfo.meo.service.require.ActivityRserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;

public class ActivityRserviceBOImpl implements ActivityRserviceBO
{
    @Resource
    CampaignPserviceBO campaignPserviceBO;
    
    @Resource
    CustomerPserviceBO customerPserviceBO;
    
    @Resource
    ProductPserviceBO  productPserviceBO;
    
    @Resource
    SystemPserviceBO   sysPserviceBo;
    
    public String getCampaignNoByCampaignId(Long campaignId)
    {
        Campaign campaign = campaignPserviceBO.getCampaignById(campaignId);
        if (ValidateUtil.isNotEmpty(campaign))
        {
            return campaign.getCampaignNo();
        }
        return null;
    }
    
    public Campaign getCampaignByCampaignId(Long campaignId)
    {
        
        return campaignPserviceBO.getCampaignById(campaignId);
    }
    
    public Long getRewardCoins(Long taskId)
    {
        TaskParamInstance param = campaignPserviceBO.getTaskParamInstanceByTaskIdAndParamId(taskId, 1000l);
        if (ValidateUtil.isNotEmpty(param))
        {
            return param.getParamValue();
        }
        return 0L;
    }
    
    public Long getGrantProduct(Long taskId)
    {
        TaskParamInstance param = campaignPserviceBO.getTaskParamInstanceByTaskIdAndParamId(taskId, 1001l);
        if (ValidateUtil.isNotEmpty(param))
        {
            return param.getParamValue();
        }
        return null;
    }
    
    public void rewardAsset(Asset asset)
    {
        customerPserviceBO.updateAsset(asset);
        
    }
    
    public void getCampaignTask(Long actionId, Long campaignId)
    {
        // List<CampaignTaskVO> ssss = campaignPserviceBO.getCampaignActivity(campaignId);
        
    }
    
    public Customer getCustomerByCustId(Long custId)
    {
        return customerPserviceBO.getCustomer(custId);
    }
    
    public CustomerBundleInfo getBundleInfoByCustId(Long custId)
    {
        
        return customerPserviceBO.getBundleInfoByCustId(custId);
    }
    
    public ProdMnoMappingVO getMnoProductMappingByResourceIdAndMnoId(Long resourceId, Integer mnoId)
    {
        CmResource resource = customerPserviceBO.getCmResourceById(resourceId);
        if (ValidateUtil.isEmpty(resource))
        {
            return null;
        }
        
        return productPserviceBO.getProductMappingInfoByProdIdAndMnoId(resource.getProductId(), mnoId);
        
    }
    
    public void orderMnoProduct(String bundleAcct, Integer mnoId, Long mnoProdId)
    {
        
    }
    
    public Product getProductProfileByProductId(Long productId)
    {
        
        return productPserviceBO.getProductById(productId);
    }
    
    public void redeemProduct(CmResource resource)
    {
        customerPserviceBO.addCmResource(resource);
    }
    
    public ProductSpecInfoVO getProductSpecByProductId(Long productId)
    {
        
        return productPserviceBO.getProductSpecificationInfo(productId);
    }
    
    public CampaignTaskRule getCapmpaignTaskRuleByActionId(Long actionId)
    {
        // return campaignPserviceBO.getCampaignTaskRuleByActionId(actionId);
        return campaignPserviceBO.getCampaignTaskRuleByActionIdAndRuleType(actionId, ActivityEnumCodeDefine.BUSINESS_RULE_TYPE);
    }
    
    @Override
    public CampaignTaskRule getCapmpaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType)
    {
        return campaignPserviceBO.getCampaignTaskRuleByActionIdAndRuleType(actionId, ruleType);
    }
    
    public void updateResourceStatus(Long resourceId, Integer status)
    {
        customerPserviceBO.updateResourceStatus(resourceId, status);
        
    }
    
    public long getProductRequireCoins(Long productId)
    {
        List<ProdPriceVO> prices = productPserviceBO.getProdPriceByProdId(productId);
        for (ProdPriceVO price : prices)
        {
            if (price.getPriceTypeId().equals(Price.PRICE_TYPE_COIN))
            {
                return price.getPrice();
            }
        }
        return 0;
    }
    
    public long getCustBalance(Long custId)
    {
        AssetCustVO asset = customerPserviceBO.getAssetInfoByCustIdAndAssetType(custId, Asset.ASSET_TYPE_COINS);
        if (ValidateUtil.isNotEmpty(asset))
        {
            return asset.getAssetBalance();
        }
        return 0;
    }
    
    public void updateAsset(Asset asset)
    {
        customerPserviceBO.updateAsset(asset);
        
    }
    
    public int getCampaignPaticipantBy(Long campaignId)
    {
        Campaign campaign = campaignPserviceBO.getCampaignById(campaignId);
        if (ValidateUtil.isNotEmpty(campaign))
        {
            return campaign.getParticipant();
        }
        return 0;
    }
    
    public CmResource getResourceByResourceId(Long resourceId)
    {
        return customerPserviceBO.getCmResourceById(resourceId);
    }
    
    public void updateResource(CmResource resource)
    {
        customerPserviceBO.updateCmResource(resource);
        
    }
    
    public CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo)
    {
        return sysPserviceBo.getEntityParticipantByCamapginNo(campaignNo);
    }
    
    public int addEntityParticipantCount(String campaignNo, int paticipant)
    {
        return sysPserviceBo.addEntityParticipantCount(campaignNo, paticipant);
    }
    
    @Override
    public List<ProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(Long productId)
    {
        return productPserviceBO.getProductMappingInfoWithMNOByProductId(productId);
    }
    
    @Override
    public Long getProductIdByCampaignId(final Long campaignId)
    {
        return campaignPserviceBO.getProductIdByCampaignId(campaignId);
    }
    
    @Override
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        return campaignPserviceBO.addCampaignTaskDetail(campaignTaskDetail);
    }
    
    @Override
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        return campaignPserviceBO.updateCampaignTaskDetail(campaignTaskDetail);
    }
    
    @Override
    public void deleteCampaignTaskDetailByDetailId(Long detailId)
    {
        campaignPserviceBO.deleteCampaignTaskDetailByDetailId(detailId);
        return;
    }
    
    @Override
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId)
    {
        return campaignPserviceBO.getCampaignTaskDetailByDetailId(detailId);
    }
    
    @Override
    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId, String campaignNo)
    {
        return campaignPserviceBO.getCampaignTaskDetailByCustIdAndCampaignNo(custId, campaignNo);
    }

    @Override
    public List<CampaignTaskRel> getCampaignTaskRelBySrcTaskIdAndCampaignId(Long srcTaskid, Long campaignId,int relType)
    {
        return campaignPserviceBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(srcTaskid, campaignId,relType);
    }

    @Override
    public CampaignTask getCampaignTaskByActionIdAndCampaignId(Long actionId, Long campaignId)
    {
        return campaignPserviceBO.getCampaignTaskByActionIdAndCampaignId(actionId, campaignId);
    }

    @Override
    public void  updateCampaignTaskDetailByDetailIdNotInDetailIds(Long custId,String campaignNo,List<Long> detailIds)
    {
        campaignPserviceBO.updateCampaignTaskDetailByDetailIdNotInDetailIds(custId, campaignNo, detailIds);       
    }

    @Override
    public String generateActivityMessageByActionId(Long customerId,Long activityId, Long actionId, Long entityId, Integer entityType, Long taskId)
    {
        Long coins = this.getRewardCoins(taskId);
        List<ActionDefine> actionList = sysPserviceBo.getActionDefine(actionId, null);
        Campaign campaign = campaignPserviceBO.getCampaignById(entityId);
        String actionName ="";
        String campaignName="";
        if(ValidateUtil.isNotEmpty(actionList)){
            actionName = actionList.get(0).getActionName();
        }
        if(ValidateUtil.isNotEmpty(campaign)){
            campaignName=campaign.getCampaignName();
        }       
        return sysPserviceBo.generateActivityMessageByActionId(actionId,actionName,campaignName,coins.toString());
       
        
    }

    @Override
    public Long getCampaignTaskParamValue(String actionId, Long campaignId)
    {
        return sysPserviceBo.getCampaignTaskParamValue(actionId, campaignId);
    }
    
    @Override
    public long getRequireCoinsByCampaignIdAndActionId(String actionId, Long campaignId)
    {
        Long requireCoins = getCampaignTaskParamValue(actionId, campaignId);
        return requireCoins == null?0L:requireCoins;
    }
    
}
