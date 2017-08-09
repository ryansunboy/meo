package com.asiainfo.meo.meo.notification.notification.profile.bo.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.meo.notification.notification.profile.bo.NotifySendDtlBO;
import com.asiainfo.meo.meo.notification.notification.profile.model.NotifySendDtl;
import com.asiainfo.meo.meo.notification.notification.profile.repository.NotifySendDtlRepository;

public class NotifySendDtlBOImpl implements NotifySendDtlBO
{
    
    @Resource
    private NotifySendDtlRepository notifySendDtlRepository;
    
    @Override
    public void addNotifySendDtl(NotifySendDtl notifySendDtl)
    {
        notifySendDtlRepository.addNotifySendDtl(notifySendDtl);
        return;
    }

    @Override
    public void updateNotifySendDtl(NotifySendDtl notifySendDtl)
    {
        notifySendDtlRepository.updateNotifySendDtl(notifySendDtl);
        return;
    }

    @Override
    public NotifySendDtl getNotifySendDtlBySendId(Long sendId)
    {
        return notifySendDtlRepository.getNotifySendDtlBySendId(sendId);
    }

    @Override
    public void deleteNotifySendDtlBySendId(Long sendId)
    {
        notifySendDtlRepository.deleteNotifySendDtlBySendId(sendId);
        return;
    }
    
}
