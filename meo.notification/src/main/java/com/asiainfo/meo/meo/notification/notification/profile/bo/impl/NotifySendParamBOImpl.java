package com.asiainfo.meo.meo.notification.notification.profile.bo.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.meo.notification.notification.profile.bo.NotifySendParamBO;
import com.asiainfo.meo.meo.notification.notification.profile.model.NotifySendParam;
import com.asiainfo.meo.meo.notification.notification.profile.repository.NotifySendParamRepository;

public class NotifySendParamBOImpl implements NotifySendParamBO
{

    @Resource
    private NotifySendParamRepository notifySendParamRepository;
    @Override
    public void addNotifySendParam(NotifySendParam notifySendParam)
    {
        notifySendParamRepository.addNotifySendParam(notifySendParam);
        return;
    }

    @Override
    public void updateNotifySendParam(NotifySendParam notifySendParam)
    {
        notifySendParamRepository.updateNotifySendParam(notifySendParam);
        return;
    }

    @Override
    public void deleteNotifySendParamById(Long id)
    {
        notifySendParamRepository.deleteNotifySendParamById(id);
        return;
    }

    @Override
    public NotifySendParam getNotifySendParamById(Long id)
    {
        return notifySendParamRepository.getNotifySendParamById(id);
    }
    
}
