package com.asiainfo.meo.activity.manager.repository.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.activity.manager.model.entity.CmJoinActivity;
import com.asiainfo.meo.activity.manager.repository.ManagerRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;

public class ManagerRepositoryImpl implements ManagerRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public void createActivityRecord(CmJoinActivity activity)
    {
        activity.setDoneTime(DateTimeUtil.getNow());
        activity.setActivityId(SequenceUtil.getSequence(CmJoinActivity.SEQ_CM_JOIN_ACTIVITY));
        hibernateRepository.saveObject(activity);
    }
    
}
