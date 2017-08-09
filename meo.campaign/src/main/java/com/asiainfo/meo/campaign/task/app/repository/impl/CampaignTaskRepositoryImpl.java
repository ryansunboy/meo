package com.asiainfo.meo.campaign.task.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;

public class CampaignTaskRepositoryImpl implements CampaignTaskRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_CAMPAIGN_TASK_ID = "SEQ_CAMPAIGN_TASK_ID";
    
    /**
     * @Description: 通过task id查询campaign task
     * @Description: get campaign task by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    public CampaignTask getCampaignTaskByTaskId(long taskId)
    {
        return hibernateRepository.get(CampaignTask.class, taskId);
    }
    
    /**
     * @Description: 通过campaign编号去查询campaign task
     * @Description: get campaign task by campaign number
     * @author zhaozx
     * @param campaignNo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CampaignTask> getCampaignTaskByCampaignNo(String campaignNo)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTask.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("sts", CampaignTask.STS_VALID));
        return (List<CampaignTask>) hibernateRepository.findByCriteria(criteria);
    }
    
    public CampaignTask saveCampaignTask(CampaignTask campaignTask)
    {
        campaignTask.setTaskId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_TASK_ID));
        hibernateRepository.saveObject(campaignTask);
        return getCampaignTaskByTaskId(campaignTask.getTaskId());
    }
    
    @Override
    public CampaignTask updateCampaignTask(CampaignTask campaignTask)
    {
        hibernateRepository.updateObject(campaignTask);
        return getCampaignTaskByTaskId(campaignTask.getTaskId());
    }
    
    @Override
    public List<CampaignTask> getCampaignTaskList(Long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTask.class);
        criteria.add(Restrictions.eq("campaignId", campaignId));
        criteria.add(Restrictions.eq("sts", CampaignTask.STS_VALID));
        return (List<CampaignTask>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public void deleteCampaignTaskByTaskId(Long taskId)
    {
        CampaignTask campaignTask = getCampaignTaskByTaskId(taskId);
        hibernateRepository.deleteObject(campaignTask);
    }
    
    @Override
    public List<CampaignTask> getCampaignTaskByActionIdAndCampaignId(Long actionId, Long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTask.class);
        criteria.add(Restrictions.eq("actionId", actionId));
        criteria.add(Restrictions.eq("campaignId", campaignId));
        criteria.add(Restrictions.eq("sts", CampaignTask.STS_VALID));
        return (List<CampaignTask>) hibernateRepository.findByCriteria(criteria);
    }
    
}
