package com.asiainfo.meo.service.provide.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.activity.app.bo.ActivityBO;
import com.asiainfo.meo.activity.app.model.vo.CmActivityRecordHisVO;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.vo.JoinBusinessActionVO;
import com.asiainfo.meo.activity.business.model.vo.JoinCampaignVO;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.service.provide.ActivityPserviceBO;

public class ActivityPserviceBOImpl implements ActivityPserviceBO
{
    @Resource
    ActivityBO activityBO;
    
    private static final Log   LOG = LogFactory.getLog(ActivityPserviceBOImpl.class);
    
    public Boolean joinCampaign(JoinCampaignVO join)
    {
        return activityBO.joinCampaign(join.getCustId(),join.getCampaignId(), join.getActionId());
    }
    
    public void redeemMeoProduct(Long custId, Long meoCampaignId)
    {
        
        activityBO.redeemMeoProduct(custId, meoCampaignId);
    }
    
    public void activateCustomerResource(Long custId, Long resourceId)
    {
        activityBO.activateCustomerResource(custId, resourceId);
        
    }
    
    public void summaryCompaignActivity()
    {
        
    }

    public void executeCampaignActivity(JoinBusinessActionVO join)
    {
        activityBO.executeBusinessAction(join);
    }

    @Override
    public CmActivityRecordHisVO getCmActivityRecordHis(Long activityId)
    {
        return activityBO.getCmActivityRecordHis(activityId);
    }

    @Override
    public CmCampaignActivity getCmCampaignActivityByCodition(String campaignNo, Long custId, String actionId)
    {
        if(ValidateUtil.isEmpty(campaignNo))
        {
            LOG.error("query campaignActivity the campaignNo can not empty");
            throw new MeoException(ActivityErrorCodeDefine.QUERY_CAMPAIGNACTIVITY_CAMPAIGNNO_CAN_NOT_EMPTY);
        }
        if(ValidateUtil.isNull(custId))
        {
            LOG.error("query campaignActivity the custId can not null");
            throw new MeoException(ActivityErrorCodeDefine.QUERY_CAMPAIGNACTIVITY_CUSTID_CAN_NOT_NULL);
        }
        if(ValidateUtil.isEmpty(actionId))
        {
            LOG.error("query campaignActivity the actionId can not null");
            throw new MeoException(ActivityErrorCodeDefine.QUERY_CAMPAIGNACTIVITY_ACTIONID_CAN_NOT_NULL);
        }
        return activityBO.getCmCampaignActivityByCodition(campaignNo, custId, actionId);
    }
    
}
