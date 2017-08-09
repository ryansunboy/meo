package com.asiainfo.meo.meo.notification.service.provide;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationQueryConditionVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationDefVO;

public interface NotifyPserviceBO
{
    public <T>List<String> pushNotificaton(T message,Integer osType);
    
    public Long createSysNotificationDef(NotificationDefVO notification);
    
    public void deleteSysNotificationDef(Long notifId);
    
    public void modifySysNotificationDef(NotificationDefVO notification);
    
    public PageInfo<NotificationDefVO> getSysNotificationDefList(Integer pageNo, Integer pageSize, Long notifId, String notifName);
    
    public NotificationDefVO getSysNotificationDefVOByNotifId(Long notifId);
    
    public void createActionNotification(final ActionNotificationVO actionNotificationVO);
    
    public PageInfo<ActionNotificationVO> getActionNotificationByNotifyAndActionId(ActionNotificationQueryConditionVO conditionVO);
    
    public void deleteActionNotification(Long notifyId, Long actionId);
}
