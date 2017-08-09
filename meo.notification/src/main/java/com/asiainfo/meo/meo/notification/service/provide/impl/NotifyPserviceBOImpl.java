package com.asiainfo.meo.meo.notification.service.provide.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.meo.notification.configuration.bo.ConfigurationBO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationQueryConditionVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationDefVO;
import com.asiainfo.meo.meo.notification.define.NotifyEnumDefine;
import com.asiainfo.meo.meo.notification.push.app.bo.android.AndroidPushNotifyBO;
import com.asiainfo.meo.meo.notification.push.app.bo.ios.IosPushNotifyBO;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyIosMessageVO;
import com.asiainfo.meo.meo.notification.service.provide.NotifyPserviceBO;

public class NotifyPserviceBOImpl implements NotifyPserviceBO
{
    @Resource
    private IosPushNotifyBO     iosPushBO;
    
    @Resource
    private AndroidPushNotifyBO androidPushBO;
    
    @Resource
    private ConfigurationBO     configurationBO;
    
    public List<String> pushNotificatonIos(Object message, Integer osType)
    {
        
        return iosPushBO.syncPushNotificaton((NotifyIosMessageVO) message);
    }
    
    public <T> List<String> pushNotificaton(T message, Integer osType)
    {
        if (osType.equals(NotifyEnumDefine.OS_TYPE_ANDROID))
        {
            
        }
        else if (osType.equals(NotifyEnumDefine.OS_TYPE_ANDROID))
        {
            // iosPushBO.pushNotificaton((NotifyIosMessageVO)message);
        }
        return null;
    }
    
    public static void main(String[] args)
    {
        IosPushNotifyBO bo = ServiceLocatorFactory.getService(IosPushNotifyBO.class);
        NotifyIosMessageVO message = new NotifyIosMessageVO();
        message.setTest(false);
        message.setBadge(1);
        message.setCertificatePath("D:/Project/eclipse luna/meo.notification/APNS-MeO-Pilot-Production.p12");
        message.setContent("hello world!!!");
        List<String> deviceTokens = new ArrayList<String>();
        deviceTokens.add("3485531f8e37fed3a8113a667d3d3c387931da0a76bc6e68174b2c69b42a2ca8");
        message.setDeviceTokens(deviceTokens);
        bo.syncPushNotificaton(message);
    }
    
    @Override
    public Long createSysNotificationDef(NotificationDefVO notification)
    {
        return configurationBO.createSysNotificationDef(notification);
        
    }
    
    @Override
    public void deleteSysNotificationDef(Long notifId)
    {
        configurationBO.deleteSysNotificationDef(notifId);
    }
    
    @Override
    public void modifySysNotificationDef(NotificationDefVO notification)
    {
        configurationBO.modifySysNotificationDef(notification);
    }
    
    @Override
    public PageInfo<NotificationDefVO> getSysNotificationDefList(Integer pageNo, Integer pageSize, Long notifId, String notifName)
    {
        return configurationBO.getSysNotificationDefList(pageNo, pageSize, notifId, notifName);
    }
    
    @Override
    public NotificationDefVO getSysNotificationDefVOByNotifId(Long notifId)
    {
        return configurationBO.getSysNotificationDefVOByNotifId(notifId);
    }

    @Override
    public void createActionNotification(ActionNotificationVO actionNotificationVO)
    {
        configurationBO.createActionNotification(actionNotificationVO);        
    }

    @Override
    public PageInfo<ActionNotificationVO> getActionNotificationByNotifyAndActionId(ActionNotificationQueryConditionVO conditionVO)
    {        
        return configurationBO.getActionNotificationByNotifyAndActionId(conditionVO);
    }

    @Override
    public void deleteActionNotification(Long notifyId, Long actionId)
    {
        configurationBO.deleteActionNotification(notifyId, actionId);        
    }

    
}
