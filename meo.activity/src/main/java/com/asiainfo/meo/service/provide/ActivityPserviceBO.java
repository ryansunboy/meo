package com.asiainfo.meo.service.provide;

import com.asiainfo.meo.activity.app.model.vo.CmActivityRecordHisVO;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.vo.JoinBusinessActionVO;
import com.asiainfo.meo.activity.business.model.vo.JoinCampaignVO;

public interface ActivityPserviceBO
{
    /**
     * @Description: customer join campaign activity
     * @modifyReason:
     * @author liuyang
     * @param join
     * @return Boolean
     */
    public Boolean joinCampaign(JoinCampaignVO join);
    
    /**
     * @Description: customer redeem the product of MEO
     * @modifyReason:
     * @author liuyang
     * @param custId
     * @param meoProduct
     */
    public void redeemMeoProduct(Long custId, Long meoCampaignId);
    
    /**
     * @Description: customer active the resource
     * @modifyReason:
     * @author liuyang
     * @param custId
     * @param resourceId
     */
    public void activateCustomerResource(Long custId, Long resourceId);
    
    /**
     * @Description: system summary campaign activity
     * @modifyReason:
     * @author liuyang
     */
    public void summaryCompaignActivity();
    
    /**
     * 
      * @Description: 氓陇鈥灻э拷鈥犆ヂ⒚λ喡访♀�盲赂拧氓艩隆猫隆艗盲赂潞
      * @Description: customer execute campaign business activity 
      * @modifyReason: 
      * @author liuyang
      * @param join
     */
    public void executeCampaignActivity(JoinBusinessActionVO join);
    
    
    public CmActivityRecordHisVO getCmActivityRecordHis(Long activityId);
    
    /**
     * 
      * @Description: 根据campaignNo、custId、actionId查询campaignActivity
      * @Description: query campaignActivity by campaignNo、custId、actionId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @param custId
      * @param actionId
      * @return
     */
    CmCampaignActivity getCmCampaignActivityByCodition(final String campaignNo, final Long custId, final String actionId);
}
