package com.asiainfo.meo.meo.notification.notification.profile.repository.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.meo.notification.notification.profile.model.NotifySendParam;
import com.asiainfo.meo.meo.notification.notification.profile.repository.NotifySendParamRepository;

public class NotifySendParamRepositoryImpl implements NotifySendParamRepository
{
    private final static String SYS_NOTIF_SEND_PARAM_ID = "SYS_NOTIF_SEND_PARAM_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;

    @Override
    public void addNotifySendParam(NotifySendParam notifySendParam)
    {
        Long id = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SYS_NOTIF_SEND_PARAM_ID);
        notifySendParam.setId(id);
        notifySendParam.setDoneTime(new Timestamp(System.currentTimeMillis()));
        hibernateRepository.saveObject(notifySendParam);
        
    }

    @Override
    public void updateNotifySendParam(NotifySendParam notifySendParam)
    {
        notifySendParam.setDoneTime(new Timestamp(System.currentTimeMillis()));
        hibernateRepository.updateObject(notifySendParam);
    }

    @Override
    public void deleteNotifySendParamById(Long id)
    {
        NotifySendParam entity = getNotifySendParamById(id);
        if(null == entity)
        {
            return;
        }
        hibernateRepository.deleteObject(entity);
    }

    @Override
    public NotifySendParam getNotifySendParamById(Long id)
    {
        return hibernateRepository.get(NotifySendParam.class, id);
    }
}
