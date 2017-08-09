package com.asiainfo.meo.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.asiainfo.meo.activity.business.model.entity.CmActivityRecordHis;
import com.asiainfo.meo.activity.business.model.vo.RewardTask;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerBundle;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class ActivityComponent
{
    

    @Resource
    CampaignPserviceBO          campaignPserviceBO;
    
    @Resource
    ActivityRepository          activityRepository;
    
    @Resource
    ActivityRserviceBO          activityRserviceBO;
    
    private static final Logger LOG = Logger.getLogger(ActivityComponent.class);
    
    public List<RewardTask> getRewardTask(Long actionId, Long campaignId)
    {
        List<RewardTask> rewardTasks = new ArrayList<RewardTask>();
        List<CampaignTask> taskList = campaignPserviceBO.getRewardCampaignTask(actionId, campaignId);
        if (ValidateUtil.isNotEmpty(taskList))
        {
            for (CampaignTask campaignTask : taskList)
            {
                RewardTask rewardTask = new RewardTask();
                rewardTask.setActionId(campaignTask.getActionId());
                rewardTask.setTaskId(campaignTask.getTaskId());
                rewardTasks.add(rewardTask);
            }
        }
        return rewardTasks;
    }
    
    /**
     * modify the old campaignTaskDetail datas ,than add a new campaignTaskDetail data
     * 
     * @param entityId
     * @param taskId
     * @param actionId
     * @param custId
     */
    public void modifyCampaignTaskDetail(ActivityManagerBundle activityManagerBundle, boolean result)
    {
        if (ValidateUtil.isNull(activityManagerBundle)|| ValidateUtil.isNull(activityManagerBundle.getEntityId())
                || ValidateUtil.isNull(activityManagerBundle.getActionId())
                || ValidateUtil.isNull(activityManagerBundle.getCustId()))
        {
            return;
        }
        
        Long entityId = activityManagerBundle.getEntityId();
        Long taskId = activityManagerBundle.getTaskId();
        Long actionId = activityManagerBundle.getActionId();
        Long custId = activityManagerBundle.getCustId();
        // =================================================================
        String campaignNo = entityId.toString();
        // cm_campaign_task_detail ,cm_campaign_task_rel 获取nextstepId
        Long nextTaskId = null;
        Long currentTaskId = null;
        if (ValidateUtil.isNotNull(taskId))
        {
            List<CampaignTaskRel> parentTypeList = activityRserviceBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(taskId,
                    entityId, CampaignTaskRel.REL_TYPE_PARENT);
            nextTaskId = ValidateUtil.isNotEmpty(parentTypeList) ? parentTypeList.get(0).getDestTaskId() : null;
            currentTaskId = taskId;
        }
        else
        {
            CampaignTask campaignTask = activityRserviceBO.getCampaignTaskByActionIdAndCampaignId(actionId, entityId);
            if (ValidateUtil.isNotNull(campaignTask))
            {
                List<CampaignTaskRel> parentTypeList = activityRserviceBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(
                        campaignTask.getTaskId(), entityId, CampaignTaskRel.REL_TYPE_PARENT);
                // 通过父子关系，查询nextStepId
                nextTaskId = ValidateUtil.isNotEmpty(parentTypeList) ? parentTypeList.get(0).getDestTaskId() : null;
                List<CampaignTaskRel> triggerTypeList = activityRserviceBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(
                        campaignTask.getTaskId(), entityId, CampaignTaskRel.REL_TYPE_TRIGGER);
                // 通过触发关系查询current_task_id
                currentTaskId = ValidateUtil.isNotEmpty(triggerTypeList) ? campaignTask.getTaskId() : campaignTask.getTaskId();
            }
        }
        
        //如果result为true，说明改步骤没有对应的触发关系;并且没有查询到下一步的taskId则直接返回
        if(result && ValidateUtil.isNull(nextTaskId))
        {
            return;
        }
        CampaignTaskDetail campaignTaskDetail = new CampaignTaskDetail();
        campaignTaskDetail.setCampaignNo(campaignNo);
        campaignTaskDetail.setCurrentTaskId(currentTaskId);
        campaignTaskDetail.setCustId(custId);
        campaignTaskDetail.setNextTaskId(nextTaskId);
        campaignTaskDetail = activityRserviceBO.addCampaignTaskDetail(campaignTaskDetail);
        if (ValidateUtil.isNull(campaignTaskDetail))
        {
            LOG.debug("========================the method afterActivityHandle,add campaignTaskDetail record failed=====================");
            throw new MeoException(CampaignErrorConstant.SAVE_CAMPAIGN_TASK_DETAIL_FAILURE);
        }
        List<Long> detailIds = new ArrayList<Long>();
        detailIds.add(campaignTaskDetail.getDetailId());
        activityRserviceBO.updateCampaignTaskDetailByDetailIdNotInDetailIds(custId, campaignNo, detailIds);
    }
    public void createCustomerActivityRecordHis(Long customerId,Long activityId, Long actionId, Long entityId, Integer entityType, Long taskId)
    {
        
        String content = activityRserviceBO.generateActivityMessageByActionId(customerId,activityId,actionId,entityId,entityType,taskId);
        CmActivityRecordHis record = new CmActivityRecordHis();
        record.setContent(content);
        record.setActivityId(activityId);
        activityRepository.createActivityRecordHis(record);
    }
}
