package com.asiainfo.meo.meo.notification.manager.factory;

import org.apache.log4j.Logger;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;
import com.asiainfo.meo.meo.notification.business.bean.impl.AndroidBean;
import com.asiainfo.meo.meo.notification.business.bean.impl.EmailBean;
import com.asiainfo.meo.meo.notification.business.bean.impl.IOSBean;
import com.asiainfo.meo.meo.notification.business.bean.impl.SmsBean;
import com.asiainfo.meo.meo.notification.define.NotifyConstantDefine;
import com.asiainfo.meo.meo.notification.manager.proxy.NotifyManager;
import com.asiainfo.meo.meo.notification.manager.proxy.NotifyManagerProxy;
import com.asiainfo.meo.meo.notification.manager.proxy.impl.NotifyManagerProxyImpl;

public class NotifyManagerFactory
{
    private static Logger LOG = Logger.getLogger(NotifyManagerFactory.class);
    
    public static NotifyManagerProxy getNotifyManagerProxy()
    {
        NotifyManager notifyManager = ServiceLocatorFactory.getService(NotifyManager.class);
        return (NotifyManagerProxy) new NotifyManagerProxyImpl(notifyManager);
    }
    
    public static NotifyBean getNotifyBean(Integer notifyType)
    {
        if (notifyType== null)
        {
            LOG.info("the parameter notifyType is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{NotifyConstantDefine.NOTIFY_TYPE });
        }
        
        if (notifyType== NotifyCommonBean.STS_IOS)
        {
            LOG.info("==============the notifyType is IOS====================");
            return new IOSBean();
        }
        else if (notifyType== NotifyCommonBean.STS_ANDROID)
        {
            LOG.info("==============the notifyType is ANDROID==============");
            return new AndroidBean();
        }
        else if (notifyType== NotifyCommonBean.STS_EMAIL)
        {
            LOG.info("==============the notifyType is EMAIL==============");
            return new EmailBean();
        }
        else if (notifyType== NotifyCommonBean.STS_SMS)
        {
            LOG.info("==============the notifyType is SMS==============");
            return new SmsBean();
        }
        else
        {
            LOG.debug("the notifyType is incorrect");
            throw new MeoException("the notifyType is incorrect");
        }
    }
    
}
