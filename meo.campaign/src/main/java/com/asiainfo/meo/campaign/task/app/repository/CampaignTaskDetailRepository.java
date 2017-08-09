package com.asiainfo.meo.campaign.task.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;

public interface CampaignTaskDetailRepository
{
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
    
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
    
    public void deleteCampaignTaskDetailByDetailId(Long detailId);
    
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId);

    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId,String campaignNo);
    
}
