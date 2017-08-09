package com.asiainfo.meo.meo.notification.business.bean.impl;

import org.apache.log4j.Logger;

import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;

public class IOSBean implements NotifyBean
{
    private static Logger LOG = Logger.getLogger(IOSBean.class);
    
    @Override
    public void init(NotifyCommonBean commonBean)
    {
        LOG.info("IOSBean======method:[init]========");
        return;
    }
    
    @Override
    public void validate(NotifyCommonBean commonBean)
    {
        LOG.info("IOSBean======method:[validate]========");
        return;
    }
    
    @Override
    public Object business(NotifyCommonBean commonBean)
    {
        LOG.info("IOSBean======method:[business]========");
        return null;
    }
    
}
