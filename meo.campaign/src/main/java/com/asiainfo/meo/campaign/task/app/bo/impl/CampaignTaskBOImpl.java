package com.asiainfo.meo.campaign.task.app.bo.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.campaign.define.CampaignConstantDefine;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.service.require.CampaignRserviceBO;
import com.asiainfo.meo.campaign.task.app.bo.CampaignTaskBO;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRuleRel;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
import com.asiainfo.meo.campaign.task.app.model.vo.TaskParamVO;
import com.asiainfo.meo.campaign.task.app.model.vo.TriggerTaskVO;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskDetailRepository;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRelRepository;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRepository;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRuleRelRepository;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRuleRepository;
import com.asiainfo.meo.campaign.task.app.repository.TaskParamInstanceRepository;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class CampaignTaskBOImpl implements CampaignTaskBO
{
    
    @Resource
    CampaignTaskRelRepository             campaignTaskRelRepository;
    
    @Resource
    CampaignTaskRepository                campaignTaskRepository;
    
    @Resource
    TaskParamInstanceRepository           taskParamInstanceRepository;
    
    @Resource
    private CampaignTaskRuleRepository    campaignTaskRuleRepository;
    
    @Resource
    private CampaignTaskRuleRelRepository campaignTaskRuleRelRepository;
    
    @Resource
    private CampaignRserviceBO            campaignRserviceBO;
    
    @Resource
    private CampaignTaskDetailRepository  campaignTaskDetailRepository;
    
    /**
     * @Description: 通过task id查询task参数
     * @Description: get task parameter by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    @Override
    public List<TaskParamInstance> getTaskParamInstanceByTaskId(Long taskId)
    {
        return taskParamInstanceRepository.getTaskParamInstanceByTaskId(taskId);
    }
    
    /**
     * @Description: 通过action id获得action名字
     * @Description: get action name by action id
     * @author zhaozx
     * @param actionId
     * @return
     */
    private String getActionNameByActionId(long actionId)
    {
        return campaignRserviceBO.getActionNameByActionId(actionId);
    }
    
    /**
     * @Description: 通过parameter id获得parameter名字
     * @Description: parameter name by parameter id
     * @author zhaozx
     * @param paramId
     * @return
     */
    private String getParamNameByParamId(long paramId)
    {
        return campaignRserviceBO.getParamNameByParamId(paramId);
    }
    
    /**
     * @Description: 通过task id获得campaign task
     * @Description: get campaign task by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    private CampaignTask getCampaignTaskByTaskid(long taskId)
    {
        return campaignTaskRepository.getCampaignTaskByTaskId(taskId);
    }
    
    /**
     * @Description: 通过task id获得campaign task参数
     * @Description: get campaign task parameter by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    private List<TaskParamVO> getTaskParamVOByTaskId(long taskId)
    {
        // get task task parameter
        List<TaskParamInstance> taskParamInstanceList = getTaskParamInstanceByTaskId(taskId);
        
        if (ValidateUtil.isEmpty(taskParamInstanceList))
        {
            return null;
        }
        
        List<TaskParamVO> taskParamVOList = new ArrayList<TaskParamVO>();
        
        for (TaskParamInstance taskParamInstance : taskParamInstanceList)
        {
            TaskParamVO taskParamVO = new TaskParamVO();
            long paramId = taskParamInstance.getParamId();
            taskParamVO.setParamInstanceId(taskParamInstance.getParamInstanceId());
            taskParamVO.setParamId(paramId);
            taskParamVO.setParamValue(taskParamInstance.getParamValue());
            taskParamVO.setParamName(getParamNameByParamId(paramId));
            taskParamVOList.add(taskParamVO);
        }
        return taskParamVOList;
    }
    
    /**
     * @Description: 通过campaign编号获得campaign task
     * @Description: get campaign task by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    private List<CampaignTaskVO> getCampaignTaskVOByCampaignId(long campaignId)
    {
        List<CampaignTaskRel> allCampaignTaskRelList = getCampaignTaskRelList(campaignId);
        if (ValidateUtil.isEmpty(allCampaignTaskRelList))
        {
            return null;
        }
        // trigger task relation list
        List<CampaignTaskRel> campaignTaskTriggerRelList = new ArrayList<CampaignTaskRel>();
        // parent task relation list
        List<CampaignTaskRel> campaignTaskParentRelList = new ArrayList<CampaignTaskRel>();
        for (CampaignTaskRel campaignTaskRel : allCampaignTaskRelList)
        {
            if (campaignTaskRel.getRelTypeId()== CampaignTaskRel.REL_TYPE_TRIGGER)
            {
                campaignTaskTriggerRelList.add(campaignTaskRel);
            }
            else
            {
                campaignTaskParentRelList.add(campaignTaskRel);
            }
        }
        
        LinkedHashSet<Long> srcTaskIdSet = new LinkedHashSet<Long>();// resource Task id set
        
        // set resource the parent task id
        if (ValidateUtil.isNotEmpty(campaignTaskParentRelList))
        {
            for (CampaignTaskRel campaignTaskParentRel : campaignTaskParentRelList)
            {
                srcTaskIdSet.add(campaignTaskParentRel.getSrcTaskId());
            }
        }
        
        // set resource the trigger task id
        if (ValidateUtil.isNotEmpty(campaignTaskTriggerRelList))
        {
            for (CampaignTaskRel campaignTaskTriggerRel : campaignTaskTriggerRelList)
            {
                srcTaskIdSet.add(campaignTaskTriggerRel.getSrcTaskId());
            }
        }
        
        // campaign task vo list
        List<CampaignTaskVO> campaignTaskVOList = new ArrayList<CampaignTaskVO>();
        for (Long srcTaskId : srcTaskIdSet)
        {
            // resource campaign task
            CampaignTask srcCampaignTask = getCampaignTaskByTaskid(srcTaskId);
            // campaign task vo
            CampaignTaskVO campaignTaskVO = new CampaignTaskVO();
            // trigger campaign task list
            List<TriggerTaskVO> triggerTaskList = new ArrayList<TriggerTaskVO>();
            Long parentCampaignTaskId = null;
            Long parentActionId = null;
            // get parent task id
            if (ValidateUtil.isNotEmpty(campaignTaskParentRelList))
            {
                // 需要被移除的数据
                List<CampaignTaskRel> campaignParentTaskRelRemoveList = new ArrayList<CampaignTaskRel>();
                for (CampaignTaskRel campaignTaskParentRel : campaignTaskParentRelList)
                {
                    if (srcTaskId== campaignTaskParentRel.getDestTaskId())
                    {
                        parentCampaignTaskId = campaignTaskParentRel.getSrcTaskId();
                        // remove parent task relation that it has trigger task relation
                        // campaignTaskParentRelList.remove(campaignTaskParentRel);
                        campaignParentTaskRelRemoveList.add(campaignTaskParentRel);
                    }
                }
                if (ValidateUtil.isNotEmpty(campaignParentTaskRelRemoveList))
                {
                    // 移除需要被移除的数据
                    campaignTaskParentRelList.removeAll(campaignParentTaskRelRemoveList);
                }
            }
            // get parent action id
            if (ValidateUtil.isNotNull(parentCampaignTaskId))
            {
                CampaignTask parentCampaignTask = getCampaignTaskByTaskid(parentCampaignTaskId);
                parentActionId = parentCampaignTask.getActionId();
            }
            // set resource task id
            campaignTaskVO.setTaskId(srcTaskId);
            // set action id of resource task
            campaignTaskVO.setActionId(srcCampaignTask.getActionId());
            // set action name of resource
            campaignTaskVO.setActionName(getActionNameByActionId(srcCampaignTask.getActionId()));
            
            // set parent id of resource task
            campaignTaskVO.setParentTaskId(parentCampaignTaskId);
            // set resource task parameter
            campaignTaskVO.setTaskParamList(getTaskParamVOByTaskId(srcTaskId));
            campaignTaskVO.setParentActionId(parentActionId);
            
            // 需要被移除的触发关系数据
            List<CampaignTaskRel> campaignTriggerTaskRelRemoveList = new ArrayList<CampaignTaskRel>();
            for (int i = campaignTaskTriggerRelList.size()- 1; i>= 0; i-- )
            {
                CampaignTaskRel campaignTaskTriggerRel = campaignTaskTriggerRelList.get(i);
                if (campaignTaskTriggerRel.getSrcTaskId()== srcTaskId)
                {
                    long triggerTaskId = campaignTaskTriggerRel.getDestTaskId();
                    // trigger task vo
                    TriggerTaskVO triggerTaskVO = new TriggerTaskVO();
                    // trigger campaign task
                    CampaignTask triggerCampaignTask = getCampaignTaskByTaskid(triggerTaskId);
                    triggerTaskVO.setTaskId(triggerTaskId);
                    // set action id of resource task
                    triggerTaskVO.setActionId(triggerCampaignTask.getActionId());
                    // set action name of
                    triggerTaskVO.setActionName(getActionNameByActionId(triggerCampaignTask.getActionId()));
                    // set resource task parameter
                    triggerTaskVO.setTaskParamList(getTaskParamVOByTaskId(triggerTaskId));
                    triggerTaskList.add(triggerTaskVO);
                    // remove task trigger relation list
                    // campaignTaskTriggerRelList.remove(i);
                    campaignTriggerTaskRelRemoveList.add(campaignTaskTriggerRel);
                }
            }
            
            if (ValidateUtil.isNotEmpty(campaignTriggerTaskRelRemoveList))
            {
                // 删除需要被移走的触发关系数据
                campaignTaskTriggerRelList.removeAll(campaignTriggerTaskRelRemoveList);
            }
            campaignTaskVO.setTriggerTaskList(triggerTaskList);
            campaignTaskVOList.add(campaignTaskVO);
        }
        // get only parent task relation
        if (ValidateUtil.isNotEmpty(campaignTaskParentRelList))
        {
            for (CampaignTaskRel campaignTaskParentRel : campaignTaskParentRelList)
            {
                // campaign task vo
                CampaignTaskVO campaignTaskVO = new CampaignTaskVO();
                // son campaign task
                CampaignTask sonCampaignTask = getCampaignTaskByTaskid(campaignTaskParentRel.getDestTaskId());
                Long parentCampaignTaskId = campaignTaskParentRel.getSrcTaskId();
                CampaignTask parentCampaignTask = getCampaignTaskByTaskid(parentCampaignTaskId);
                Long parentActionId = parentCampaignTask.getActionId();
                // set son task id
                campaignTaskVO.setTaskId(sonCampaignTask.getTaskId());
                // set action id of son task
                campaignTaskVO.setActionId(sonCampaignTask.getActionId());
                // set action name of son task
                campaignTaskVO.setActionName(getActionNameByActionId(sonCampaignTask.getActionId()));
                // set parent id of son task
                campaignTaskVO.setParentTaskId(parentCampaignTaskId);
                // set son task parameter
                campaignTaskVO.setTaskParamList(getTaskParamVOByTaskId(sonCampaignTask.getTaskId()));
                campaignTaskVO.setParentActionId(parentActionId);
                campaignTaskVOList.add(campaignTaskVO);
            }
        }
        return campaignTaskVOList;
    }
    
    /**
     * @Description: 通过campaign id获得campaign task
     * @Description: get campaign task by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public List<CampaignTaskVO> getCampaignTaskByCampaignId(Long campaignId)
    {
        if (campaignId== null)
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_ID });
        }
        // get campaign task
        return getCampaignTaskVOByCampaignId(campaignId);
    }
    
    public CampaignTaskVO getCampaignTaskVOById(Long id)
    {
        return null;
    }
    
    public TaskParamInstance saveTaskParamInstance(TaskParamInstance taskParamInstance)
    {
        return taskParamInstanceRepository.saveTaskParamInstance(taskParamInstance);
    }
    
    public CampaignTask saveCampaignTask(CampaignTask campaignTask)
    {
        return campaignTaskRepository.saveCampaignTask(campaignTask);
    }
    
    @Override
    public CampaignTaskRel saveCampaignTaskRel(CampaignTaskRel campaignTaskRel)
    {
        return campaignTaskRelRepository.saveCampaignTaskRel(campaignTaskRel);
    }
    
    @Override
    public CampaignTask updateCampaignTask(CampaignTask campaignTask)
    {
        return campaignTaskRepository.updateCampaignTask(campaignTask);
    }
    
    @Override
    public CampaignTaskRel getCampaignTaskRelByRid(Long rid)
    {
        return campaignTaskRelRepository.getCampaignTaskRelByRid(rid);
    }
    
    @Override
    public CampaignTaskRel deleteCampaignTaskRelByRid(Long rid)
    {
        CampaignTaskRel campaignTaskRel = campaignTaskRelRepository.getCampaignTaskRelByRid(rid);
        campaignTaskRel.setSts(CampaignTaskRel.STS_INVALID);
        campaignTaskRel.setModifyDate(DateTimeUtil.getNow());
        return campaignTaskRelRepository.updateCampaignTaskRel(campaignTaskRel);
    }
    
    @Override
    public TaskParamInstance updateTaskParamInstance(TaskParamInstance taskParamInstance)
    {
        return taskParamInstanceRepository.updateTaskParamInstance(taskParamInstance);
    }
    
    @Override
    public TaskParamInstance getTaskParamInstanceById(Long id)
    {
        return taskParamInstanceRepository.getTaskParamInstanceById(id);
    }
    
    @Override
    public TaskParamInstance deleteTaskParamInstanceById(Long id)
    {
        TaskParamInstance taskParamInstance = taskParamInstanceRepository.getTaskParamInstanceById(id);
        taskParamInstance.setSts(TaskParamInstance.STS_INVALID);
        taskParamInstance.setModifyDate(DateTimeUtil.getNow());
        return taskParamInstanceRepository.updateTaskParamInstance(taskParamInstance);
    }
    
    @Override
    public List<CampaignTaskRel> getCampaignTaskRelList(Long campaignId)
    {
        return campaignTaskRelRepository.getCampaignTaskRelList(campaignId);
    }
    
    /**
     * @Description: 根据relTypeId和relTypeId查询CampaignTaskRel
     * @Description: query CampaignTaskRel by relTypeId and relTypeId
     * @modifyReason:
     * @author zhengzy
     * @param srcTaskid
     * @param relTypeId
     * @param campaignId
     */
    @Override
    public List<CampaignTaskRel> getCampaignTaskRelBySrcTaskIdAndCampaignId(Long srcTaskid, Long campaignId, int relType)
    {
        return campaignTaskRelRepository.getCampaignTaskRelBySrcTaskIdAndCampaignId(srcTaskid, campaignId, relType);
    }
    
    @Override
    public List<CampaignTask> getCampaignTaskList(Long campaignId)
    {
        return campaignTaskRepository.getCampaignTaskList(campaignId);
    }
    
    @Override
    public List<TaskParamInstance> getTaskParamInstanceListByTaskIds(List<Long> taskIds)
    {
        return taskParamInstanceRepository.getTaskParamInstanceListByTaskIds(taskIds);
    }
    
    @Override
    public CampaignTask deleteCampaignTaskByTaskId(Long taskId)
    {
        CampaignTask campaignTask = campaignTaskRepository.getCampaignTaskByTaskId(taskId);
        campaignTask.setSts(CampaignTask.STS_INVALID);
        campaignTask.setModifyDate(DateTimeUtil.getNow());
        return campaignTaskRepository.updateCampaignTask(campaignTask);
    }
    
    @Override
    public CampaignTaskRule saveCampaignTaskRule(CampaignTaskRule campaignTaskRule)
    {
        return campaignTaskRuleRepository.saveCampaignTaskRule(campaignTaskRule);
    }
    
    @Override
    public CampaignTaskRule updateCampaignTaskRule(CampaignTaskRule campaignTaskRule)
    {
        return campaignTaskRuleRepository.updateCampaignTaskRule(campaignTaskRule);
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRule(Long id)
    {
        return campaignTaskRuleRepository.getCampaignTaskRule(id);
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRuleByActionId(Long actionId)
    {
        return campaignTaskRuleRepository.getCampaignTaskRuleByActionId(actionId);
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType)
    {
        return campaignTaskRuleRepository.getCampaignTaskRuleByActionIdAndRuleType(actionId, ruleType);
    }
    
    @Override
    public List<CampaignTaskRule> getCampaignTaskRuleListByActionIds(List<Long> actionIds)
    {
        return campaignTaskRuleRepository.getCampaignTaskRuleListByActionIds(actionIds);
    }
    
    @Override
    public List<CampaignTaskRule> getCampaignTaskRuleListByRuleIds(List<Long> ruleIds)
    {
        return campaignTaskRuleRepository.getCampaignTaskRuleListByRuleIds(ruleIds);
    }
    
    @Override
    public CampaignTaskRuleRel saveCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel)
    {
        return campaignTaskRuleRelRepository.saveCampaignTaskRuleRel(campaignTaskRuleRel);
    }
    
    @Override
    public CampaignTaskRuleRel updateCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel)
    {
        return campaignTaskRuleRelRepository.updateCampaignTaskRuleRel(campaignTaskRuleRel);
    }
    
    @Override
    public CampaignTaskRuleRel getCampaignTaskRuleRelById(Long id)
    {
        return campaignTaskRuleRelRepository.getCampaignTaskRuleRelById(id);
    }
    
    @Override
    public List<CampaignTaskRuleRel> getCampaignTaskRuleRelListByTaskIds(List<Long> taskIds)
    {
        return campaignTaskRuleRelRepository.getCampaignTaskRuleRelListByTaskIds(taskIds);
    }
    
    @Override
    public CampaignTaskRuleRel deleteCampaignTaskRuleRelById(Long id)
    {
        CampaignTaskRuleRel campaignTaskRuleRel = campaignTaskRuleRelRepository.getCampaignTaskRuleRelById(id);
        campaignTaskRuleRel.setSts(CampaignTaskRuleRel.STS_INVALID);
        campaignTaskRuleRel.setModifyDate(DateTimeUtil.getNow());
        return campaignTaskRuleRelRepository.updateCampaignTaskRuleRel(campaignTaskRuleRel);
    }
    
    @Override
    public CampaignTask getCampaignTaskById(Long id)
    {
        return campaignTaskRepository.getCampaignTaskByTaskId(id);
    }
    
    @Override
    public List<CampaignTask> getCampaignTaskByActionIdAndCampaignId(Long actionId, Long campaignId)
    {
        return campaignTaskRepository.getCampaignTaskByActionIdAndCampaignId(actionId, campaignId);
    }
    
    @Override
    public List<CampaignTask> getRewardTask(Long actionId, Long campaignId)
    {
        List<CampaignTask> tasks = campaignTaskRepository.getCampaignTaskByActionIdAndCampaignId(actionId, campaignId);
        if (ValidateUtil.isEmpty(tasks))
        {
            return null;
        }
        List<CampaignTask> campaignTasks = new ArrayList<CampaignTask>();
        for (CampaignTask campaignTask : tasks)
        {
            List<CampaignTaskRel> relList = campaignTaskRelRepository.getCampaignTaskRelByTaskIdAndCampaignId(
                    campaignTask.getTaskId(), campaignTask.getCampaignId());
            if (ValidateUtil.isNotEmpty(relList))
            {
                for (CampaignTaskRel campaignTaskRel : relList)
                {
                    CampaignTask task = campaignTaskRelRepository.getCampaignTaskByTaskId(campaignTaskRel.getDestTaskId());
                    if (task!= null)
                    {
                        campaignTasks.add(task);
                    }
                }
            }
        }
        return campaignTasks;
    }
    
    @Override
    public TaskParamInstance getTaskParamInstanceByTaskIdAndParamId(Long taskId, Long paramId)
    {
        return taskParamInstanceRepository.getTaskParamInstanceByTaskIdAndParamId(taskId, paramId);
    }
    
    @Override
    public Long getProductIdByCampaignId(Long campaignId)
    {
        return taskParamInstanceRepository.getProductIdByCampaignId(campaignId);
    }
    
    @Override
    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId, String campaignNo)
    {
        return campaignTaskDetailRepository.getCampaignTaskDetailByCustIdAndCampaignNo(custId, campaignNo);
    }
    
    @Override
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        campaignTaskDetail = campaignTaskDetailRepository.addCampaignTaskDetail(campaignTaskDetail);
        if (campaignTaskDetail== null)
        {
            throw new MeoException(CampaignErrorConstant.SAVE_CAMPAIGN_TASK_DETAIL_FAILURE);
        }
        return campaignTaskDetail;
    }
    
    @Override
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        return campaignTaskDetailRepository.updateCampaignTaskDetail(campaignTaskDetail);
    }
    
    @Override
    public void deleteCampaignTaskDetailByDetailId(Long detailId)
    {
        campaignTaskDetailRepository.deleteCampaignTaskDetailByDetailId(detailId);
        return;
    }
    
    @Override
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId)
    {
        return campaignTaskDetailRepository.getCampaignTaskDetailByDetailId(detailId);
    }
    
    @Override
    public void updateCampaignTaskDetailByDetailIdNotInDetailIds(Long custId, String campaignNo, List<Long> detailIds)
    {
        List<CampaignTaskDetail> list = getCampaignTaskDetailByCustIdAndCampaignNo(custId, campaignNo);
        if (ValidateUtil.isEmpty(list))
        {
            return;
        }
        for (CampaignTaskDetail campaignTaskDetail : list)
        {
            if (!detailIds.contains(campaignTaskDetail.getDetailId()))
            {
                campaignTaskDetail.setSts(CampaignTaskDetail.STS_INVALID);
                updateCampaignTaskDetail(campaignTaskDetail);
            }
        }
    }
    
}
