package com.asiainfo.meo.activity.business.bean.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.activity.app.bo.ActivityBO;
import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.service.require.ActivityRserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysActionDef;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;

public class JoinCampaignBean implements ActivityBean
{
    private static final Log LOG = LogFactory.getLog(JoinCampaignBean.class);
    
    private Long             customerId;
    
    private Long             campaignId;
    
    private String           campaignNo;
    
    private String           actionId;
    
    ActivityRepository       activityRepository;
    
    ActivityRserviceBO       activityRserviceBO;
    
    ActivityBO               activityServiceBO;
    
    SystemPserviceBO         systemPserviceBO;
    
    public void init(Object... input)
    {
        customerId = (Long) input[0];
        campaignId = (Long) input[1];
        actionId   = (String) input[2];
        activityRepository = ServiceLocatorFactory.getService(ActivityRepository.class);
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        activityServiceBO  = ServiceLocatorFactory.getService(ActivityBO.class);
        systemPserviceBO   = ServiceLocatorFactory.getService(SystemPserviceBO.class);
        campaignNo = activityRserviceBO.getCampaignNoByCampaignId(campaignId);
        
    }
    
    public void validate(Object... input)
    {
        if (ValidateUtil.isEmpty(campaignNo))
        {
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_IS_NOT_FOUND, new Object[]{campaignId });
        }
        if (ValidateUtil.isNull(actionId) || ValidateUtil.isEmpty(actionId) )
        {
            throw new MeoException(CampaignErrorConstant.ACTIONID_CAN_NOT_NULL, new Object[]{actionId });
        }
        final List<ActionDefine> actionDefineList = systemPserviceBO.getActionDefine(Long.valueOf(actionId), SysActionDef.PREDECESSOR_ACTION);
        if (ValidateUtil.isNull(actionDefineList) || ValidateUtil.isEmpty(actionDefineList))
        {
            throw new MeoException(CampaignErrorConstant.ACTIONDEFINE_IS_NOT_FOUND, new Object[]{actionId });
        }
    }
    
    public Object business(Object... input)
    {
        Boolean coinsEligible = new Boolean(false);
        int paticipant = activityRserviceBO.getCampaignPaticipantBy(campaignId);
        int count = activityRserviceBO.addEntityParticipantCount(campaignNo, paticipant);
        if (count == 0)
        {
            LOG.debug(ActivityErrorCodeDefine.REACH_MAX_PATICIPANT);
            throw new MeoException(ActivityErrorCodeDefine.REACH_MAX_PATICIPANT);
        }
        /**
         * execute join campaign action rule
         */
        coinsEligible = activityServiceBO.executeJoinCampaignActionRule(customerId, campaignId, actionId);
        
        return coinsEligible;
    }
    
}
