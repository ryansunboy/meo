package com.asiainfo.meo.campaign.task.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;

public interface CampaignTaskRuleRepository
{
    public CampaignTaskRule saveCampaignTaskRule(CampaignTaskRule campaignTaskRule);
    
    public CampaignTaskRule updateCampaignTaskRule(CampaignTaskRule campaignTaskRule);
    
    public CampaignTaskRule getCampaignTaskRule(Long id);
   
    public CampaignTaskRule getCampaignTaskRuleByActionId(Long actionId);
    
    public CampaignTaskRule getCampaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType);
    
    public List<CampaignTaskRule> getCampaignTaskRuleListByActionIds(List<Long> actionIds);
    
    public List<CampaignTaskRule> getCampaignTaskRuleListByRuleIds(List<Long> ruleIds);
    
}
