package com.asiainfo.meo.activity.business.bean.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

/**
 * 
  * @Description: For execute action rule for JoinCampaignBean                 
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Jul 31, 2015 4:32:15 PM 
  * @version 1.0
 */
public class JoinCampaignActionRuleBean implements ActivityBean
{
    private static final Log LOG = LogFactory.getLog(JoinCampaignActionRuleBean.class);
    
    private Long             customerId;
    
    private Long             campaignId;
    
    private String           campaignNo;
    
    private String           actionId;
    
    ActivityRepository       activityRepository;
    
    ActivityRserviceBO       activityRserviceBO;
    
    @Override
    public void init(Object... input)
    {
        customerId = (Long) input[0];
        campaignId = (Long) input[1];
        actionId   = (String) input[2];
        activityRepository = ServiceLocatorFactory.getService(ActivityRepository.class);
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        campaignNo = activityRserviceBO.getCampaignNoByCampaignId(campaignId);        
    }
    
    @Override
    public void validate(Object... input)
    {
        if (ValidateUtil.isEmpty(campaignNo))
        {
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_IS_NOT_FOUND, new Object[]{campaignId });
        }
        if (ValidateUtil.isNull(actionId) || ValidateUtil.isEmpty(actionId))
        {
            throw new MeoException(CampaignErrorConstant.ACTIONID_CAN_NOT_NULL, new Object[]{actionId });
        }        
    }
    
    @Override
    public Object business(Object... input)
    {
        Boolean coinsEligible = new Boolean(false);
        /** 1. get count on CM_CAMPAIGN_ACTIVITY */
        final Integer participant = activityRepository.getCountParticipantOfCampaignAppDownload(campaignNo, customerId, actionId);
        /** 2. Compare participant with configuration in CM_TASK_PARAM_INSTANCE */
        Long downloadCountParam = activityRserviceBO.getCampaignTaskParamValue(actionId, campaignId);
        if(ValidateUtil.isEmpty(downloadCountParam)){
            //If there is no configuration downloaded, the default is set to 1
            downloadCountParam=1L;
        }
        if (ValidateUtil.isNotNull(participant) && ValidateUtil.isNotNull(downloadCountParam)
            && participant <= downloadCountParam) {
                LOG.debug("Customer Id :" + customerId + ", campaign Id :" + campaignId 
                        + "  eligible for the coins.");
                coinsEligible = Boolean.TRUE;
        } else {
            LOG.debug("Customer Id :" + customerId + ", campaign Id :" + campaignId 
                    + "  ineligible for the coins.");            
        }

        return coinsEligible;
    }
    
}
