package com.asiainfo.meo.activity.manager.listener;

import java.util.List;
import java.util.Map;

import com.asiainfo.meo.activity.app.bo.ActivityBO;
import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.manager.factory.ActivityManagerFactory;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerBundle;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerConduit;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.event.Event;
import com.asiainfo.meo.common.event.EventListener;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;

public class ActivityEventListener implements EventListener
{
    
    public void onEvent(Event e)
    {
        ActivityBO activityBO = ServiceLocatorFactory.getService(ActivityBO.class);
        Map<String, Object> message = (Map<String, Object>) e.getPayload();
        
        Object object = message.get("rewardTasks");
        List<Map<String, Object>> maps = (List<Map<String, Object>>) message.get("rewardTasks");
        
        for (Map<String, Object> map : maps)
        {
            Long actionId = Long.valueOf(map.get("actionId").toString());
            if (ValidateUtil.isEmpty(actionId))
            {
                continue;
            }
            ActivityBean bean = ActivityManagerFactory.getActivityBean(actionId);
            ActivityManagerBundle bundle = new ActivityManagerBundle();
            bundle.setActionId(actionId);
            bundle.setEntityId(Long.valueOf(message.get("entityId").toString()));
            bundle.setCustId(Long.valueOf(message.get("custId").toString()));
            bundle.setActivityId(Long.valueOf(message.get("activityId").toString()));
            bundle.setTaskId(Long.valueOf(map.get("taskId").toString()));
            
            ActivityManagerConduit conduit = new ActivityManagerConduit(bean.getClass());
            conduit.addParams(Long.valueOf(message.get("custId").toString()));
            conduit.addParams(Long.valueOf(message.get("entityId").toString()));
            conduit.addParams(actionId);
            conduit.addParams(Long.valueOf(message.get("activityId").toString()));
            conduit.addParams(Long.valueOf(map.get("taskId").toString()));
            conduit.setBundle(bundle);
            activityBO.executeActivity(conduit);
            
        }
        
    }
    
    public void onException(Throwable t)
    {
        throw new MeoException(ActivityErrorCodeDefine.ACTIVITY_MANAGER_ERROR, t);
        
    }
    
}
