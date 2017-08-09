package com.asiainfo.meo.campaign.task.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRelRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class CampaignTaskRelRepositoryImpl implements CampaignTaskRelRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_CAMPAIGN_TASK_REL_ID = "SEQ_CAMPAIGN_TASK_REL_ID";
    
    /**
     * @Description: 通过campaignNo获得触发的活动关系列表
     * @Description: get campaign task relation list by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CampaignTaskRel> getTriggerCampaignTaskRelList(long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRel.class);
        criteria.add(Restrictions.eq("campaignId", campaignId));
        criteria.add(Restrictions.eq("relTypeId", CampaignTaskRel.REL_TYPE_TRIGGER));
        criteria.add(Restrictions.eq("sts", CampaignTaskRel.STS_VALID));
        return (List<CampaignTaskRel>) hibernateRepository.findByCriteria(criteria);
    }
    
    /**
     * @Description: 获得父活动的task id
     * @Description: get parent campaign task
     * @author zhaozx
     * @param srcTaskId
     * @return
     */
    @SuppressWarnings("unchecked")
    public Long getParentCampaignTaskId(long srcTaskId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRel.class);
        criteria.add(Restrictions.eq("destTaskId", srcTaskId));
        criteria.add(Restrictions.eq("relTypeId", CampaignTaskRel.REL_TYPE_PARENT));
        criteria.add(Restrictions.eq("sts", CampaignTaskRel.STS_VALID));
        List<CampaignTaskRel> campaignTaskRelList = (List<CampaignTaskRel>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isEmpty(campaignTaskRelList))
        {
            return null;
        }
        return campaignTaskRelList.get(0).getSrcTaskId();
    }
    
    @Override
    public CampaignTaskRel saveCampaignTaskRel(CampaignTaskRel campaignTaskRel)
    {
        campaignTaskRel.setRelId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_TASK_REL_ID));
        hibernateRepository.saveObject(campaignTaskRel);
        return getCampaignTaskRelById(campaignTaskRel.getRelId());
    }
    
    public CampaignTaskRel getCampaignTaskRelById(Long id)
    {
        return hibernateRepository.get(CampaignTaskRel.class, id);
    }
    
    @Override
    public CampaignTaskRel getCampaignTaskRelByRid(Long rid)
    {
        return hibernateRepository.get(CampaignTaskRel.class, rid);
    }
    
    @Override
    public void deleteCampaignTaskRelByRid(Long rid)
    {
        CampaignTaskRel campaignTaskRel = getCampaignTaskRelByRid(rid);
        hibernateRepository.deleteObject(campaignTaskRel);
    }
    
    @Override
    public List<CampaignTaskRel> getCampaignTaskRelList(Long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRel.class);
        criteria.add(Restrictions.eq("campaignId", campaignId));
        criteria.add(Restrictions.eq("sts", CampaignTaskRel.STS_VALID));
        return (List<CampaignTaskRel>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public CampaignTaskRel updateCampaignTaskRel(CampaignTaskRel campaignTaskRel)
    {
        hibernateRepository.updateObject(campaignTaskRel);
        return getCampaignTaskRelByRid(campaignTaskRel.getRelId());
    }
    
    @Override
    public List<CampaignTaskRel> getCampaignTaskRelByTaskIdAndCampaignId(long taskId, Long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRel.class);
        criteria.add(Restrictions.eq("campaignId", campaignId));
        criteria.add(Restrictions.eq("sts", CampaignTaskRel.STS_VALID));
        criteria.add(Restrictions.eq("srcTaskId", taskId));
        criteria.add(Restrictions.eq("relTypeId", CampaignTaskRel.REL_TYPE_TRIGGER));
        return (List<CampaignTaskRel>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public CampaignTask getCampaignTaskByTaskId(long taskId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTask.class);
        criteria.add(Restrictions.eq("taskId", taskId));
        criteria.add(Restrictions.eq("sts", CampaignTaskRel.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public List<CampaignTaskRel> getCampaignTaskRelBySrcTaskIdAndCampaignId(Long srcTaskid, Long campaignId,int relType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskRel.class);
        criteria.add(Restrictions.eq("campaignId", campaignId));
        criteria.add(Restrictions.eq("sts", CampaignTaskRel.STS_VALID));
        criteria.add(Restrictions.eq("srcTaskId", srcTaskid));
        criteria.add(Restrictions.eq("relTypeId", relType));
        return (List<CampaignTaskRel>) hibernateRepository.findByCriteria(criteria);
    }
}
