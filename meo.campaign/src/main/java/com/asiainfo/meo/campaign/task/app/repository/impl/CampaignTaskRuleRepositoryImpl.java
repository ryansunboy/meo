package com.asiainfo.meo.campaign.task.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRuleRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class CampaignTaskRuleRepositoryImpl implements CampaignTaskRuleRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_CAMPAIGN_TASK_RULE_ID = "SEQ_CAMPAIGN_TASK_RULE_ID";
    
    @Override
    public CampaignTaskRule saveCampaignTaskRule(CampaignTaskRule campaignTaskRule)
    {
        campaignTaskRule.setRuleId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_TASK_RULE_ID));
        hibernateRepository.saveObject(campaignTaskRule);
        return getCampaignTaskRule(campaignTaskRule.getRuleId());
    }
    
    @Override
    public CampaignTaskRule updateCampaignTaskRule(CampaignTaskRule campaignTaskRule)
    {
        hibernateRepository.updateObject(campaignTaskRule);
        return getCampaignTaskRule(campaignTaskRule.getRuleId());
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRule(Long id)
    {
        return hibernateRepository.get(CampaignTaskRule.class, id);
    }
    
    @Override
    public List<CampaignTaskRule> getCampaignTaskRuleListByActionIds(List<Long> actionIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRule.class);
        criteria.add(Restrictions.in("actionId", actionIds));
        criteria.add(Restrictions.eq("sts", CampaignTaskRule.STS_VALID));
        return (List<CampaignTaskRule>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public List<CampaignTaskRule> getCampaignTaskRuleListByRuleIds(List<Long> ruleIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRule.class);
        criteria.add(Restrictions.in("ruleId", ruleIds));
        criteria.add(Restrictions.eq("sts", CampaignTaskRule.STS_VALID));
        return (List<CampaignTaskRule>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public CampaignTaskRule getCampaignTaskRuleByActionId(Long actionId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRule.class);
        criteria.add(Restrictions.eq("actionId", actionId));
        criteria.add(Restrictions.eq("sts", CampaignTaskRule.STS_VALID));
        List<CampaignTaskRule> list = (List<CampaignTaskRule>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list)?null:list.get(0);
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRule.class).add(Restrictions.eq("actionId", actionId))
                .add(Restrictions.eq("sts", CampaignTaskRule.STS_VALID))
                .add(Restrictions.eq("ruleType", ruleType));
        List<CampaignTaskRule> list = (List<CampaignTaskRule>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list)?null:list.get(0);
    }
    
}
