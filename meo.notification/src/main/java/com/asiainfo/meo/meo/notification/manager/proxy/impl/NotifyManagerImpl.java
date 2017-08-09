package com.asiainfo.meo.meo.notification.manager.proxy.impl;

import org.apache.log4j.Logger;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;
import com.asiainfo.meo.meo.notification.manager.proxy.NotifyManager;

public class NotifyManagerImpl implements NotifyManager
{
    private static Logger LOG = Logger.getLogger(NotifyManagerImpl.class);
    
    @Override
    public void beforeNotifyHandle(NotifyCommonBean commonBean)
    {
        LOG.info("====================={beforeNotifyHandle}===============================end=======");
        Long start = System.currentTimeMillis();
        Long end = System.currentTimeMillis();
        LOG.info("====================={beforeNotifyHandle}===============================end=======spend time:["+ (end- start)
                + "]");
        return;
    }
    
    @Override
    public Object executeNotify(Class<? extends NotifyBean> activityClass, NotifyCommonBean commonBean)
    {
        LOG.info("====================={executeNotify}===============================end=======");
        Long start = System.currentTimeMillis();
        NotifyBean notify = null;
        Object obj = null;
        try
        {
            notify = activityClass.newInstance();
            
            LOG.info("====================begin to call init===============================");
            notify.init(commonBean);
            LOG.info("====================finish to call init===============================");
            
            
            LOG.info("====================begin to call validate============================");
            notify.validate(commonBean);
            LOG.info("====================finish to call validate===========================");
            
            
            LOG.info("====================begin to call business============================");
            obj = notify.business(commonBean);
            LOG.info("====================begin to call business============================");
        }
        catch(InstantiationException| IllegalAccessException e)
        {
            LOG.error("executeNotify fail", e);
            throw new MeoException("executeNotify fail", e);
        }
        
        Long end = System.currentTimeMillis();
        LOG.info("====================={executeNotify}===============================end=======spend time:["+ (end- start)+ "]");
        return obj;
    }
    
    @Override
    public void afterNotifyHandle(NotifyCommonBean commonBean)
    {
        LOG.info("====================={afterNotifyHandle}===============================end=======");
        Long start = System.currentTimeMillis();
        
        //EventBus eventBus = ServiceLocatorFactory.getService(EventBus.class);
        //SimpleMessageEvent event = new SimpleMessageEvent(NotifyConstantDefine.NOTIFY_EVENT_ID,NotifyConstantDefine.NOTIFY_EVENT_TOPIC);
        //event.setPayload(commonBean);
        //eventBus.publish(event);
        
        
        
        
        Long end = System.currentTimeMillis();
        LOG.info("====================={afterNotifyHandle}===============================end=======spend time:["+ (end- start)+ "]");
        return;
    }
}
