package com.asiainfo.meo.activity.app.bo;

import com.asiainfo.meo.activity.app.model.vo.CmActivityRecordHisVO;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.vo.JoinBusinessActionVO;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerConduit;

public interface ActivityBO
{
    
    
    /**
     * @Description: customer join campaign activity
     * @modifyReason:
     * @author liuyang
     * @param join
     * @return Boolean
     */
    public Boolean joinCampaign(Long custId,Long campaignId, String actionId);
    
    /**
     * 
      * @Description: Execute join campaign action rule
      * @modifyReason: 
      * @author Thanapol
      * @param custId
      * @param campaignId
      * @param actionId
      * @return Boolean
     */
    public Boolean executeJoinCampaignActionRule(Long custId, Long campaignId, String actionId);
    
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
      * @Description: execute activity manager 
      * @modifyReason: 
      * @author liuyang
     */
    public void executeActivity(ActivityManagerConduit conduit);
    
    /**
     * 
      * @Description: execute customer actions 
      * @modifyReason: 
      * @author liuyang
     */
    public void executeBusinessAction(JoinBusinessActionVO joinAction);
    
    
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
