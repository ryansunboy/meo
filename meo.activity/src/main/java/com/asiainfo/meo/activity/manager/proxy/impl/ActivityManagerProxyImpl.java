package com.asiainfo.meo.activity.manager.proxy.impl;

import org.apache.log4j.Logger;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerConduit;
import com.asiainfo.meo.activity.manager.proxy.ActivityManager;
import com.asiainfo.meo.activity.manager.proxy.ActivityManagerProxy;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;

public class ActivityManagerProxyImpl implements ActivityManagerProxy
{
    
    private ActivityManager     activityManager;
    
    private static final Logger LOG = Logger.getLogger(ActivityManagerProxyImpl.class);
    
    public ActivityManagerProxyImpl(ActivityManager activityManager)
    {
        this.activityManager = activityManager;
    }
    
    public Object startActivity(ActivityManagerConduit conduit)
    {
        
        LOG.info("*********begin to invoke [beforeActivityHandle] method*************");
        long start = DateTimeUtil.getCurrentTimeMillis();
        if (conduit.isActivity())
        {
            LOG.info(conduit.getBundle());
            activityManager.beforeActivityHandle(conduit.getBundle());
            
        }
        long end = DateTimeUtil.getCurrentTimeMillis();
        LOG.info("*********finish to invoke [beforeActivityHandle] method---handle Time:["+ (end- start)+ "]*************");
        
        LOG.info("*********begin to invoke [executeActivity] method*************");
        start = DateTimeUtil.getCurrentTimeMillis();
        Object result = activityManager.executeActivity(conduit.getClazz(), conduit.getBeanParams().toArray());
        end = DateTimeUtil.getCurrentTimeMillis();
        LOG.info("*********finish to invoke [executeActivity] method---handle Time:["+ (end- start)+ "]*************");
        
        LOG.info("*********begin to invoke [afterActivityHandle] method*************");
        start = DateTimeUtil.getCurrentTimeMillis();
        if (conduit.isActivity())
        {
            activityManager.afterActivityHandle(conduit.getBundle());
        }
        
        end = DateTimeUtil.getCurrentTimeMillis();
        LOG.info("*********finish to invoke [afterActivityHandle] method---handle Time:["+ (end- start)+ "]*************");
        return result;
    }
    
}
