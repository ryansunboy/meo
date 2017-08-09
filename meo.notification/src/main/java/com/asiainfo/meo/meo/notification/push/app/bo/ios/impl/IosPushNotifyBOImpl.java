package com.asiainfo.meo.meo.notification.push.app.bo.ios.impl;

import java.util.List;


import javax.annotation.Resource;

import com.asiainfo.meo.meo.notification.component.NotificationComponent;
import com.asiainfo.meo.meo.notification.push.app.bo.ios.IosPushNotifyBO;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyIosMessageVO;

public class IosPushNotifyBOImpl implements IosPushNotifyBO
{
    @Resource
    NotificationComponent notificationComponent;
    public List<String> syncPushNotificaton(NotifyIosMessageVO message)
    {
        return notificationComponent.syncPushIosNotification(message);
        
    }
    public List<String> asyncPushNotificaton(NotifyIosMessageVO message)
    {
        return notificationComponent.asyncPushIosNotification(message);

    }
    
}
