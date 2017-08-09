package com.asiainfo.meo.meo.notification.push.app.bo.ios;

import java.util.List;


import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyIosMessageVO;

public interface IosPushNotifyBO
{
    public List<String> syncPushNotificaton(NotifyIosMessageVO message);
    public List<String> asyncPushNotificaton(NotifyIosMessageVO message);
}
