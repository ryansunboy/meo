package com.asiainfo.meo.meo.notification.push.app.bo.android;

import java.util.List;


import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyAndroidMessageVO;

public interface AndroidPushNotifyBO
{
    public List<String> asyncPushNotificaton(NotifyAndroidMessageVO message);
    public List<String> syncPushNotificaton(NotifyAndroidMessageVO message);
    
}
