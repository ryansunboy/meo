package com.asiainfo.meo.campaign.task.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRuleRel;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRuleRelRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;

public class CampaignTaskRuleRelRepositoryImpl implements CampaignTaskRuleRelRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_CAMPAIGN_TASK_RULE_REL_ID = "SEQ_CAMPAIGN_TASK_RULE_REL_ID";
    
    @Override
    public CampaignTaskRuleRel saveCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel)
    {
        campaignTaskRuleRel.setId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_TASK_RULE_REL_ID));
        hibernateRepository.saveObject(campaignTaskRuleRel);
        return getCampaignTaskRuleRelById(campaignTaskRuleRel.getId());
    }
    
    @Override
    public CampaignTaskRuleRel updateCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel)
    {
        hibernateRepository.updateObject(campaignTaskRuleRel);
        return getCampaignTaskRuleRelById(campaignTaskRuleRel.getId());
    }
    
    @Override
    public CampaignTaskRuleRel getCampaignTaskRuleRelById(Long id)
    {
        return hibernateRepository.get(CampaignTaskRuleRel.class, id);
    }
    
    @Override
    public List<CampaignTaskRuleRel> getCampaignTaskRuleRelListByTaskIds(List<Long> taskIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRuleRel.class);
        criteria.add(Restrictions.in("taskId", taskIds));
        criteria.add(Restrictions.eq("sts", CampaignTaskRuleRel.STS_VALID));
        return (List<CampaignTaskRuleRel>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public void deleteCampaignTaskRuleRelById(Long id)
    {
        CampaignTaskRuleRel campaignTaskRuleRel = getCampaignTaskRuleRelById(id);
        hibernateRepository.deleteObject(campaignTaskRuleRel);
    }
    
}
