package com.asiainfo.meo.service.require;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;

public interface ActivityRserviceBO
{
    String getCampaignNoByCampaignId(Long campaignId);

    Long getRewardCoins(Long taskId);
    
    Long getGrantProduct(Long taskId);

    void rewardAsset(Asset asset);

    void getCampaignTask(Long actionId, Long campaignId);

    Customer getCustomerByCustId(Long custId);

    CustomerBundleInfo getBundleInfoByCustId(Long custId);

    void orderMnoProduct(String bundleAcct, Integer mnoId, Long mnoProductId);

    Product getProductProfileByProductId(Long productId);

    void redeemProduct(CmResource resource);

    ProductSpecInfoVO getProductSpecByProductId(Long productId);

    ProdMnoMappingVO getMnoProductMappingByResourceIdAndMnoId(Long resourceId, Integer mnoId);

    CampaignTaskRule getCapmpaignTaskRuleByActionId(Long actionId);
    
    CampaignTaskRule getCapmpaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType);    

    void updateResourceStatus(Long resourceId, Integer status);

    long getProductRequireCoins(Long productId);

    long getCustBalance(Long custId);

    void updateAsset(Asset asset);

    int getCampaignPaticipantBy(Long campaignId);

    CmResource getResourceByResourceId(Long resourceId);

    void updateResource(CmResource resource);

    CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo);

    int addEntityParticipantCount(String campaignNo,int participant);
    
    public List<ProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(Long productId);
    
    Long getProductIdByCampaignId(final Long campaignId);
    
    //campaignTaskDetail
    
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
    
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
    
    public void deleteCampaignTaskDetailByDetailId(Long detailId);
    
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId);

    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId,String campaignNo);
    
    public List<CampaignTaskRel> getCampaignTaskRelBySrcTaskIdAndCampaignId(Long srcTaskid, Long campaignId,int relType);
    
    public CampaignTask getCampaignTaskByActionIdAndCampaignId(Long actionId, Long campaignId);
    
    public void  updateCampaignTaskDetailByDetailIdNotInDetailIds(Long custId,String campaignNo,List<Long> detailIds);

    String generateActivityMessageByActionId(Long customerId,Long activityId, Long actionId, Long entityId, Integer entityType, Long taskId);
    
    public Long getCampaignTaskParamValue(String actionId, Long campaignId);
    
    long getRequireCoinsByCampaignIdAndActionId(String actionId, Long campaignId);
    
}
