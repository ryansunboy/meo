package com.asiainfo.meo.activity.business.bean.impl;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivitySummary;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.define.ActivityEnumCodeDefine;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class FinishDownLoadActivityBean implements ActivityBean
{
    ActivityRepository activityRepository;
    
    ActivityRserviceBO activityRserviceBO;
    
    private Long       campaignId;
    
    private Long       customerId;
    
    private String     campaignNo;
    
    public void init(Object... input) throws MeoException
    {
        customerId = (Long) input[0];
        campaignId = (Long) input[1];
        activityRepository = ServiceLocatorFactory.getService(ActivityRepository.class);
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        campaignNo = activityRserviceBO.getCampaignNoByCampaignId(campaignId);
    }
    
    public void validate(Object... input) throws MeoException
    {
        if (ValidateUtil.isEmpty(campaignNo))
        {
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_IS_NOT_FOUND, new Object[]{campaignId });
        }
    }
    
    public Object business(Object... input)
    {
        CmCampaignActivitySummary summary = new CmCampaignActivitySummary();
        summary.setActionId(ActivityEnumCodeDefine.ACTIVITY_FINISH_DOWNLOAD);
        summary.setCampaignNo(campaignNo);
        summary.setSummaryType(CmCampaignActivitySummary.SUMMARY_TYPE_PARTICIPATE);
        summary.setSummaryValue(1l);
        // activityRepository.updateActivitySummary(summary);
        return null;
    }
    
}
