package com.asiainfo.meo.meo.notification.notification.profile.repository.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.meo.notification.notification.profile.model.NotifySendDtl;
import com.asiainfo.meo.meo.notification.notification.profile.repository.NotifySendDtlRepository;

public class NotifySendDtlRepositoryImpl implements NotifySendDtlRepository
{
    private final static String SYS_NOTIF_SEND_DTL_ID = "SYS_NOTIF_SEND_DTL_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;

    @Override
    public void addNotifySendDtl(NotifySendDtl notifySendDtl)
    {
        Long sendId = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SYS_NOTIF_SEND_DTL_ID);
        notifySendDtl.setSendId(sendId);
        notifySendDtl.setDoneTime(new Timestamp(System.currentTimeMillis()));
        hibernateRepository.saveObject(notifySendDtl);
    }

    @Override
    public void updateNotifySendDtl(NotifySendDtl notifySendDtl)
    {
        notifySendDtl.setDoneTime(new Timestamp(System.currentTimeMillis()));
        hibernateRepository.updateObject(notifySendDtl);
    }

    @Override
    public NotifySendDtl getNotifySendDtlBySendId(Long sendId)
    {
        return hibernateRepository.get(NotifySendDtl.class, sendId);
    }

    @Override
    public void deleteNotifySendDtlBySendId(Long sendId)
    {
        NotifySendDtl entity =  getNotifySendDtlBySendId(sendId);
        if(null == entity)
        {
            return;
        }
        hibernateRepository.deleteObject(entity);
    }
}
