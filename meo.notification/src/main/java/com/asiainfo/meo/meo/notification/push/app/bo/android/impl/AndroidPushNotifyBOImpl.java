package com.asiainfo.meo.meo.notification.push.app.bo.android.impl;

import java.util.List;

import javax.annotation.Resource;



import com.asiainfo.meo.meo.notification.component.NotificationComponent;
import com.asiainfo.meo.meo.notification.push.app.bo.android.AndroidPushNotifyBO;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyAndroidMessageVO;

public class AndroidPushNotifyBOImpl implements AndroidPushNotifyBO
{
    @Resource
    NotificationComponent notificationComponent;
    public List<String> asyncPushNotificaton(NotifyAndroidMessageVO message)
    {
        return notificationComponent.asyncPushAndroidNotification(message);
    }
    public List<String> syncPushNotificaton(NotifyAndroidMessageVO message)
    {
        return notificationComponent.syncPushAndroidNotificaiton(message);
    }
    
}
