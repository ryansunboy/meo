package com.asiainfo.meo.meo.notification.email.app.bo;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.meo.notification.email.app.model.vo.NotifyEmailMessageVO;

public interface EmailNotificationBO
{
    public Boolean sendEmailNotification(NotifyEmailMessageVO message) throws MeoException;
}
