package com.asiainfo.meo.meo.notification.configuration.respository;

import java.util.List;

import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationDef;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationNumLimit;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationTimeLimit;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotifyActionRel;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysTimeSegDef;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysTimeSegDtl;

public interface ConfigurationRepository
{

    public void createSysNotificationDef(SysNotificationDef notification);
    
    public void modifySysNotificationDef(SysNotificationDef notification);
    
    public SysNotificationDef getSysNotificationDefByNotifId(Long notifId);
    
    public List<SysNotificationDef> getSysNotificationDefList(Integer pageNo, Integer pageSize, Long notifId, String notifName);
    
    public List<SysNotificationDef> getSysNotificationDefByNotifyType(Integer notifyType);
    
    public Integer getSysNotificationDefTotalSize();
    
    public Long createSysNotifyActionRel(SysNotifyActionRel notifyActionRel);
    
    public Long createSysNotificationTimeLimit(SysNotificationTimeLimit notificationTimeLimit);
    
    public Long createSysTimeSegDef(SysTimeSegDef timeSegDef);
    
    public Long createSysTimeSegDtl(SysTimeSegDtl timeSegDtl);
    
    public Long createSysNotificationNumLimit(SysNotificationNumLimit notificationNumLimit);
    
    public List<SysNotifyActionRel> getSysNotifyActionRelByNotifyIdAndActionId(Long notifyId, Long actionId);
    
    public List<SysNotificationTimeLimit> getSysNotificationTimeLimitByActionId(Long actionId);
    
    public List<SysTimeSegDef> getSysTimeSegDefBySegId(Long segId);
    
    public List<SysTimeSegDtl> getSysTimeSegDtlBySegId(Long segId);
    
    public List<SysNotificationNumLimit> getSysNotificationNumLimit(Long actionId);
    
    public void deleteSysNotifyActionRel(SysNotifyActionRel notifyActionRel);
    
    public void deleteSysNotificationNumLimit(SysNotificationNumLimit notificationNumLimit);
    
    public void deleteSysNotificationTimeLimit(SysNotificationTimeLimit notificationTimeLimit);
    
    public void deleteSysTimeSegDef(SysTimeSegDef timeSegDef);
    
    public void deleteSysTimeSegDtl(SysTimeSegDtl timeSegDtl);
    
    public void modifySysNotifyActionRel(SysNotifyActionRel notifyActionRel);
    
    public void modifySysNotificationNumLimit(SysNotificationNumLimit notificationNumLimit);
    
    public void modifySysNotificationTimeLimit(SysNotificationTimeLimit notificationTimeLimit);
    
    public void modifySysTimeSegDef(SysTimeSegDef timeSegDef);
    
    public void modifySysTimeSegDtl(SysTimeSegDtl timeSegDtl);
    
}
