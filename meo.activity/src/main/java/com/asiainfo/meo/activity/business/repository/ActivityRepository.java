package com.asiainfo.meo.activity.business.repository;

import com.asiainfo.meo.activity.business.model.entity.CmActivityRecordHis;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivitySummary;
import com.asiainfo.meo.activity.business.model.entity.CmRewardActivity;

public interface ActivityRepository
{
    
    void updateActivitySummary(CmCampaignActivitySummary summary);
    
    void saveRewardActivity(CmRewardActivity rewardActivity);
    
    Integer getCountParticipantOfCampaignAppDownload(final String campaignNo, final Long custId, final String actionId);
    
    void createActivityRecordHis(CmActivityRecordHis record);
    
    void saveCampaignActivity(CmCampaignActivity campaignActivity);
    
    CmActivityRecordHis getCmActivityRecordHis(Long activityId);

    CmCampaignActivity getCmCampaignActivityByCodition(final String campaignNo, final Long custId, final String actionId);
    
}
