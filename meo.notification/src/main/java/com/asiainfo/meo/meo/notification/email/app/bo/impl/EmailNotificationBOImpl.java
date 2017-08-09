package com.asiainfo.meo.meo.notification.email.app.bo.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.meo.notification.component.NotificationComponent;
import com.asiainfo.meo.meo.notification.email.app.bo.EmailNotificationBO;
import com.asiainfo.meo.meo.notification.email.app.model.vo.NotifyEmailMessageVO;

public class EmailNotificationBOImpl implements EmailNotificationBO
{
    @Resource
    NotificationComponent notificationComponent;
    
    @Override
    public Boolean sendEmailNotification(final NotifyEmailMessageVO message) throws MeoException
    {
        return notificationComponent.sendEmailNotification(message);
    }
    
}
