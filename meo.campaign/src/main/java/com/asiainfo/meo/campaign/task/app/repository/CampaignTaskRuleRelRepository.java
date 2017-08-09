package com.asiainfo.meo.campaign.task.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRuleRel;

public interface CampaignTaskRuleRelRepository
{
    public CampaignTaskRuleRel saveCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel);
    
    public CampaignTaskRuleRel updateCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel);
    
    public CampaignTaskRuleRel getCampaignTaskRuleRelById(Long id);
    
    public List<CampaignTaskRuleRel> getCampaignTaskRuleRelListByTaskIds(List<Long> taskIds);
    
    public void deleteCampaignTaskRuleRelById(Long id);
}
