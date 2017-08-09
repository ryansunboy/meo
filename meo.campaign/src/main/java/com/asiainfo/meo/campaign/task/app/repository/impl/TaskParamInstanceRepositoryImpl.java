package com.asiainfo.meo.campaign.task.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.define.CampaignTypeDefine;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.campaign.task.app.repository.TaskParamInstanceRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class TaskParamInstanceRepositoryImpl implements TaskParamInstanceRepository
{
    
    private static final String SEQ_TASK_PARAM_INSTANCE_ID = "SEQ_TASK_PARAM_INSTANCE_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 通过task id查询task参数实例
     * @Description: get task parameter instance by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<TaskParamInstance> getTaskParamInstanceByTaskId(long taskId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(TaskParamInstance.class);
        criteria.add(Restrictions.eq("taskId", taskId));
        criteria.add(Restrictions.eq("sts", TaskParamInstance.STS_VALID));
        return (List<TaskParamInstance>) hibernateRepository.findByCriteria(criteria);
    }
    
    public TaskParamInstance saveTaskParamInstance(TaskParamInstance taskParamInstance)
    {
        taskParamInstance.setParamInstanceId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_TASK_PARAM_INSTANCE_ID));
        hibernateRepository.saveObject(taskParamInstance);
        return getTaskParamInstanceById(taskParamInstance.getParamInstanceId());
    }
    
    public TaskParamInstance getTaskParamInstanceById(Long id)
    {
        return hibernateRepository.get(TaskParamInstance.class, id);
    }
    
    @Override
    public TaskParamInstance updateTaskParamInstance(TaskParamInstance taskParamInstance)
    {
        hibernateRepository.updateObject(taskParamInstance);
        return getTaskParamInstanceById(taskParamInstance.getParamInstanceId());
    }
    
    @Override
    public void deleteTaskParamInstanceById(Long id)
    {
        TaskParamInstance taskParamInstance = getTaskParamInstanceById(id);
        hibernateRepository.deleteObject(taskParamInstance);
    }
    
    @Override
    public List<TaskParamInstance> getTaskParamInstanceListByTaskIds(List<Long> taskIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(TaskParamInstance.class);
        criteria.add(Restrictions.in("taskId", taskIds));
        criteria.add(Restrictions.eq("sts", TaskParamInstance.STS_VALID));
        return (List<TaskParamInstance>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public TaskParamInstance getTaskParamInstanceByTaskIdAndParamId(Long taskId, Long paramId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(TaskParamInstance.class);
        criteria.add(Restrictions.eq("taskId", taskId));
        criteria.add(Restrictions.eq("paramId", paramId));
        criteria.add(Restrictions.eq("sts", TaskParamInstance.STS_VALID));
        List<TaskParamInstance> list = (List<TaskParamInstance>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public Long getProductIdByCampaignId(final Long campaignId)
    {
        List<Object> vs = (List<Object>) hibernateRepository.findByNamedQuery("meo.campaign.getProductIdByCampaignId",
                campaignId, CampaignTypeDefine.DATA_PACKAGE_REDEMPTION);
        if (ValidateUtil.isNotEmpty(vs))
        {
            Long productId = null;
            if (ValidateUtil.isNotNull(vs.get(0))|| ValidateUtil.isNotEmpty(vs.get(0)))
            {
                productId = new Long(vs.get(0).toString());
            }
            return productId;
        }
        else
        {
            return null;
        }
        
    }
    
}
