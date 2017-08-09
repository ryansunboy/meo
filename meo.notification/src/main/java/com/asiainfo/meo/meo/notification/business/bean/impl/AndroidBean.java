package com.asiainfo.meo.meo.notification.business.bean.impl;

import org.apache.log4j.Logger;

import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;

public class AndroidBean implements NotifyBean
{
    private static Logger LOG = Logger.getLogger(AndroidBean.class);
    
    @Override
    public void init(NotifyCommonBean commonBean)
    {
        LOG.info("AndroidBean======method:[init]========");
        return;
    }
    
    @Override
    public void validate(NotifyCommonBean commonBean)
    {
        LOG.info("AndroidBean======method:[validate]========");
        return;
    }
    
    @Override
    public Object business(NotifyCommonBean commonBean)
    {
        LOG.info("AndroidBean======method:[business]========");
        return null;
    }
}
