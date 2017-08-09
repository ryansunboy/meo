package com.asiainfo.meo.meo.notification.business.bean.impl;

import org.apache.log4j.Logger;

import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;

public class EmailBean implements NotifyBean
{
    private static Logger LOG = Logger.getLogger(EmailBean.class);
    
    @Override
    public void init(NotifyCommonBean commonBean)
    {
        LOG.info("EmailBean======method:[init]========");
        return;
    }
    
    @Override
    public void validate(NotifyCommonBean commonBean)
    {
        LOG.info("EmailBean======method:[validate]========");
        return;
    }
    
    @Override
    public Object business(NotifyCommonBean commonBean)
    {
        LOG.info("EmailBean======method:[business]========");
        return null;
    }
}
