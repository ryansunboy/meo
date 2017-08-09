package com.asiainfo.meo.activity.manager.factory;

import org.apache.log4j.Logger;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.manager.proxy.ActivityManager;
import com.asiainfo.meo.activity.manager.proxy.ActivityManagerProxy;
import com.asiainfo.meo.activity.manager.proxy.impl.ActivityManagerProxyImpl;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class ActivityManagerFactory
{
    private Logger logger = Logger.getLogger(ActivityManagerFactory.class);
    
    public static ActivityManagerProxy getActivityManagerProxy()
    {
        ActivityManager activityManager = ServiceLocatorFactory.getService(ActivityManager.class);
        return (ActivityManagerProxy) new ActivityManagerProxyImpl(activityManager);
        
    }
    
    public static ActivityBean getActivityBean(Long actionId)
    {
        
        ActivityRserviceBO activityPserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        CampaignTaskRule rule = activityPserviceBO.getCapmpaignTaskRuleByActionId(actionId);
        
        if (ValidateUtil.isNotNull(rule))
        {
            String actionClass = rule.getRuleContent();
            Object object;
            try
            {
                object = Class.forName(actionClass).newInstance();
            }
            catch(Exception e)
            {
                throw new MeoException(ActivityErrorCodeDefine.GET_ACTIVITY_BEAN_ERROR);
            }
            
            if (object instanceof ActivityBean)
            {
                return (ActivityBean) object;
            }
            else
            {
                throw new MeoException(ActivityErrorCodeDefine.GET_ACTIVITY_BEAN_ERROR);
                
            }
        }
        return null;
    }
    
    public static ActivityBean getActivityBean(Long actionId, Integer ruleType)
    {
        
        ActivityRserviceBO activityPserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        CampaignTaskRule rule = activityPserviceBO.getCapmpaignTaskRuleByActionIdAndRuleType(actionId, ruleType);
        
        if (ValidateUtil.isNotNull(rule))
        {
            String actionClass = rule.getRuleContent();
            Object object;
            try
            {
                object = Class.forName(actionClass).newInstance();
            }
            catch(Exception e)
            {
                throw new MeoException(ActivityErrorCodeDefine.GET_ACTIVITY_BEAN_ERROR);
            }
            
            if (object instanceof ActivityBean)
            {
                return (ActivityBean) object;
            }
            else
            {
                throw new MeoException(ActivityErrorCodeDefine.GET_ACTIVITY_BEAN_ERROR);
                
            }
        }
        return null;
    }
    
    public static Long getCurrentActivityId()
    {
        return null;
        
    }
}
