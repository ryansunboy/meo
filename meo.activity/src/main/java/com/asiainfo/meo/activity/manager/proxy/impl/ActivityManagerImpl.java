package com.asiainfo.meo.activity.manager.proxy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.vo.RewardTask;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.activity.manager.model.entity.CmJoinActivity;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerBundle;
import com.asiainfo.meo.activity.manager.proxy.ActivityManager;
import com.asiainfo.meo.activity.manager.repository.ManagerRepository;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.event.bus.EventBus;
import com.asiainfo.meo.common.event.message.impl.SimpleMessageEvent;
import com.asiainfo.meo.component.ActivityComponent;
import com.asiainfo.meo.define.ActivityConstantDefine;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class ActivityManagerImpl implements ActivityManager
{
    private static final Logger LOG = Logger.getLogger(ActivityManagerImpl.class);
    
    @Resource
    ManagerRepository           managerRepository;
    
    @Resource
    ActivityComponent           activityComp;
    
    @Resource
    ActivityRserviceBO          activityRserviceBO;
    
    @Resource
    ActivityRepository          activityRepository;
    
    public Object executeActivity(Class<? extends ActivityBean> activityClass, Object... params)
    {
        ActivityBean activity = null;
        try
        {
            activity = activityClass.newInstance();
            
            LOG.info("*********begin to init *************");
            activity.init(params);
            LOG.info("*********finish to init *************");
            
            LOG.info("*********begin to validate*************");
            activity.validate(params);
            LOG.info("*********finish to validate*************");
            
            LOG.info("*********begin to business *************");
            Object object = activity.business(params);
            LOG.info("*********finish to business *************");
            
            return object;
        }
        catch(InstantiationException | IllegalAccessException e)
        {
            LOG.error("executeActivity fail", e);
            throw new MeoException(ActivityErrorCodeDefine.ACTIVITY_MANAGER_ERROR, e);
        }
        
    }
    
    public void beforeActivityHandle(ActivityManagerBundle activityManagerBundle)
    {
        LOG.info("begin to pre handle ");
        CmJoinActivity activity = new CmJoinActivity();
        activity.setActionId(activityManagerBundle.getActionId());
        activity.setCustId(activityManagerBundle.getCustId());
        managerRepository.createActivityRecord(activity);
        activityManagerBundle.setActivityId(activity.getActivityId());
    }
    
    public void afterActivityHandle(ActivityManagerBundle activityManagerBundle)
    {
        List<RewardTask> tasks = activityComp.getRewardTask(activityManagerBundle.getActionId(),
                activityManagerBundle.getEntityId());
        Map<String, Object> message = new HashMap<String, Object>();
        boolean result = true;
        if (ValidateUtil.isNotEmpty(tasks))
        {
            result = false;
            message.put("rewardTasks", tasks);
            message.put("custId", activityManagerBundle.getCustId());
            message.put("entityId", activityManagerBundle.getEntityId());
            message.put("actionId", activityManagerBundle.getActionId());
            message.put("taskId", activityManagerBundle.getTaskId());
            message.put("activityId", activityManagerBundle.getActivityId());
            
            //put the activity message into the activityMQ
            setMsgObjectIntoMQ(message,ActivityConstantDefine.ACTIVITY_EVENT_ID,
                    ActivityConstantDefine.ACTIVITY_EVENT_TOPIC);
            
            //put the notification message into the notificationMQ
            //NotifyCommonBean notifyCommonBean = new NotifyCommonBean(activityManagerBundle.getCustId(),null,activityManagerBundle.getActionId()); 
            //setMsgObjectIntoMQ(notifyCommonBean, NotifyConstantDefine.NOTIFY_EVENT_ID, NotifyConstantDefine.NOTIFY_EVENT_TOPIC);
            
            activityComp.createCustomerActivityRecordHis(activityManagerBundle.getCustId(),
                                                         activityManagerBundle.getActivityId(),
                                                         activityManagerBundle.getActionId(),
                                                         activityManagerBundle.getEntityId(),
                                                         activityManagerBundle.getEntityType(),
                                                         tasks.get(0).getTaskId());
        }
        //update the old campaignTaskDetail datas and add a new campaignTaskDetial data
        activityComp.modifyCampaignTaskDetail(activityManagerBundle,result);
        /**
         * find out current task ID
         */
        Long currentTaskId = null;
        if (ValidateUtil.isNotNull(activityManagerBundle.getTaskId()))
        {            
            currentTaskId = activityManagerBundle.getTaskId();
        }
        else
        {
            CampaignTask campaignTask = activityRserviceBO.getCampaignTaskByActionIdAndCampaignId(
                    activityManagerBundle.getActionId(), activityManagerBundle.getEntityId());
            if (ValidateUtil.isNotNull(campaignTask))
            {
                List<CampaignTaskRel> triggerTypeList = activityRserviceBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(
                        campaignTask.getTaskId(), activityManagerBundle.getEntityId(), CampaignTaskRel.REL_TYPE_TRIGGER);                
                currentTaskId = ValidateUtil.isNotEmpty(triggerTypeList) ? campaignTask.getTaskId() : campaignTask.getTaskId();
            }
        }
        /**
         * Record in CM_CAMPAIGN_ACTIVITY
         */
        /** 1. Checking that the action is predecessor action or not ? */
        
        List<CampaignTaskRel> typeList = activityRserviceBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(
                currentTaskId, activityManagerBundle.getEntityId(), CampaignTaskRel.REL_TYPE_TRIGGER);
        /** 2. If it's predecessor action, record to CM_CAMPAIGN_ACTIVITY */
        if (ValidateUtil.isNotEmpty(typeList))
        {
            final CmCampaignActivity campaignActivity = new CmCampaignActivity();
            campaignActivity.setActionId(activityManagerBundle.getActionId().toString());
            campaignActivity.setCampaignNo(activityManagerBundle.getEntityId().toString());
            campaignActivity.setActivityId(activityManagerBundle.getActivityId());
            campaignActivity.setCustId(activityManagerBundle.getCustId());
            activityRepository.saveCampaignActivity(campaignActivity);
        }        
    }

    private void setMsgObjectIntoMQ(Object obj,String eventId, String topic)
    {
        EventBus eventBus = ServiceLocatorFactory.getService(EventBus.class);
        SimpleMessageEvent event = new SimpleMessageEvent(eventId,topic);
        event.setPayload(obj);
        eventBus.publish(event);
    }
    
}
