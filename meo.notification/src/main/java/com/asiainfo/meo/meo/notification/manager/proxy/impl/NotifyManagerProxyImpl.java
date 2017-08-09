package com.asiainfo.meo.meo.notification.manager.proxy.impl;

import org.apache.log4j.Logger;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;
import com.asiainfo.meo.meo.notification.manager.model.vo.NotifyManagerConduit;
import com.asiainfo.meo.meo.notification.manager.proxy.NotifyManager;
import com.asiainfo.meo.meo.notification.manager.proxy.NotifyManagerProxy;

public class NotifyManagerProxyImpl implements NotifyManagerProxy
{
    private static Logger LOG = Logger.getLogger(NotifyManagerProxyImpl.class);
    
    private NotifyManager notifyManager;
    
    public NotifyManagerProxyImpl(NotifyManager notifyManager)
    {
        this.notifyManager = notifyManager;
    }
    
    @Override
    public Object startNotify(NotifyManagerConduit conduit)
    {
        LOG.info("=======invoke==========method:[startNotify]=================");
        if (null== conduit)
        {
            LOG.info("the parameter conduit is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object [] {"conduit"});
        }
        NotifyCommonBean commonBean = conduit.getCoomonBean();
        if (null== commonBean)
        {
            LOG.info("the conduit's property commonBean is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object [] {"commonBean"});
        }
        Class<? extends NotifyBean> clazz = conduit.getClazz();
        if (null== clazz)
        {
            LOG.info("the conduit's property clazz is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object [] {"clazz"});
        }
        Object obj = null;
        LOG.info("=====================[beforeNotifyHandle]==========================begin");
        Long start = System.currentTimeMillis();
        if (conduit.isTrigger())
        {
            LOG.info(JsonUtil.writeObjectAsString(commonBean));
            notifyManager.beforeNotifyHandle(commonBean);
        }
        Long end = System.currentTimeMillis();
        LOG.info("=====================[beforeNotifyHandle]==========================end=======spend time:["+ (end- start)+ "]");
        
        LOG.info("=====================[executeNotify]===============================begin");
        start = System.currentTimeMillis();
        obj = notifyManager.executeNotify(clazz, commonBean);
        end = System.currentTimeMillis();
        LOG.info("=====================[executeNotify]===============================end=======spend time:["+ (end- start)+ "]");
        
        LOG.info("=====================[afterNotifyHandle]===============================begin");
        start = System.currentTimeMillis();
        if (conduit.isTrigger())
        {
            notifyManager.afterNotifyHandle(commonBean);
        }
        end = System.currentTimeMillis();
        LOG.info("=====================[afterNotifyHandle]===============================end=======spend time:["+ (end- start)
                + "]");
        return obj;
    }
    
}
