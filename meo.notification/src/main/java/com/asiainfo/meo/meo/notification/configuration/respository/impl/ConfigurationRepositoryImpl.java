package com.asiainfo.meo.meo.notification.configuration.respository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationDef;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationNumLimit;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationTimeLimit;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotifyActionRel;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysTimeSegDef;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysTimeSegDtl;
import com.asiainfo.meo.meo.notification.configuration.respository.ConfigurationRepository;

public class ConfigurationRepositoryImpl implements ConfigurationRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public void createSysNotificationDef(SysNotificationDef notification)
    {
        notification.setCreateDate(DateTimeUtil.getNow());
        notification.setSts(SysNotificationDef.STS_VALID);
        notification.setNotifyId(SequenceUtil.getSequence(SysNotificationDef.SEQ_SYS_NOTIFICATION_DEF));
        hibernateRepository.saveObject(notification);
        
    }
    
    public void modifySysNotificationDef(SysNotificationDef notification)
    {
        notification.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(notification);
        
    }
    
    public SysNotificationDef getSysNotificationDefByNotifId(Long notifId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysNotificationDef.class)
                .add(Restrictions.eq("notifyId", notifId))
                .add(Restrictions.eq("sts", SysNotificationDef.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public List<SysNotificationDef> getSysNotificationDefList(Integer pageNo, Integer pageSize, Long notifId, String notifName)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysNotificationDef.class)
                .add(Restrictions.eq("sts", SysNotificationDef.STS_VALID));
        if (ValidateUtil.isNotEmpty(notifId))
        {
            criteria.add(Restrictions.sqlRestriction("CAST({alias}.NOTIFY_ID AS CHAR) like ?", "%"+ notifId+ "%",
                    StandardBasicTypes.STRING));
        }
        if (ValidateUtil.isNotEmpty(notifName))
        {
            criteria.add(Restrictions.like("notifName", "%"+notifName+"%"));
        }
        return (List<SysNotificationDef>)hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }
    
    @Override
    public List<SysNotificationDef> getSysNotificationDefByNotifyType(Integer notifyType)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysNotificationDef.class);
        criteria.add(Restrictions.eq("sts", SysNotificationDef.STS_VALID));
        if (ValidateUtil.isNotNull(notifyType)) {
            criteria.add(Restrictions.eq("notifType", notifyType));
        }
        return (List<SysNotificationDef>)hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public Integer getSysNotificationDefTotalSize()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysNotificationDef.class)
                .add(Restrictions.eq("sts", SysNotificationDef.STS_VALID));
        List<SysNotificationDef> list =  (List<SysNotificationDef>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public Long createSysNotifyActionRel(final SysNotifyActionRel notifyActionRel)
    {
        notifyActionRel.setCreateDate(DateTimeUtil.getNow());
        notifyActionRel.setRelId(SequenceUtil.getSequence(SysNotifyActionRel.SEQ_SYS_NOTIFY_ACTION_REL));
        notifyActionRel.setSts(SysNotifyActionRel.STS_VALID);
        hibernateRepository.saveObject(notifyActionRel);
        return notifyActionRel.getRelId();
    }

    @Override
    public Long createSysNotificationTimeLimit(final SysNotificationTimeLimit notificationTimeLimit)
    {
        notificationTimeLimit.setCreateDate(DateTimeUtil.getNow());
        notificationTimeLimit.setTimeLimitId(SequenceUtil.getSequence(SysNotificationTimeLimit.SEQ_SYS_NOTIFICATION_TIME_LIMIT));
        notificationTimeLimit.setSts(SysNotificationTimeLimit.STS_VALID);
        hibernateRepository.saveObject(notificationTimeLimit);
        return notificationTimeLimit.getTimeLimitId();
    }

    @Override
    public Long createSysTimeSegDef(final SysTimeSegDef timeSegDef)
    {
        timeSegDef.setCreateDate(DateTimeUtil.getNow());
        timeSegDef.setSegId(SequenceUtil.getSequence(SysTimeSegDef.SEQ_SYS_TIME_SEG_DEF));
        timeSegDef.setSts(SysTimeSegDef.STS_VALID);
        hibernateRepository.saveObject(timeSegDef);
        return timeSegDef.getSegId();
    }

    @Override
    public Long createSysTimeSegDtl(final SysTimeSegDtl timeSegDtl)
    {
        timeSegDtl.setCreateDate(DateTimeUtil.getNow());
        timeSegDtl.setDtlId(SequenceUtil.getSequence(SysTimeSegDtl.SEQ_SYS_TIME_SEG_DTL));
        timeSegDtl.setSts(SysTimeSegDtl.STS_VALID);
        hibernateRepository.saveObject(timeSegDtl);
        return timeSegDtl.getDtlId();
    }

    @Override
    public Long createSysNotificationNumLimit(final SysNotificationNumLimit notificationNumLimit)
    {
        notificationNumLimit.setCreateDate(DateTimeUtil.getNow());        
        notificationNumLimit.setNumLimitId(SequenceUtil.getSequence(SysNotificationNumLimit.SEQ_SYS_NOTIFICATION_NUM_LIMIT));
        notificationNumLimit.setSts(SysNotificationNumLimit.STS_VALID);
        hibernateRepository.saveObject(notificationNumLimit);
        return notificationNumLimit.getNumLimitId();
    }

    @Override
    public List<SysNotifyActionRel> getSysNotifyActionRelByNotifyIdAndActionId(Long notifyId, Long actionId)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysNotifyActionRel.class);
        criteria.add(Restrictions.eq("sts", SysNotifyActionRel.STS_VALID));
        if (ValidateUtil.isNotNull(notifyId)) {
            criteria.add(Restrictions.eq("notifyId", notifyId));
        }
        if (ValidateUtil.isNotNull(actionId)) {
            criteria.add(Restrictions.eq("actionId", actionId));
        }
        return (List<SysNotifyActionRel>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysNotificationTimeLimit> getSysNotificationTimeLimitByActionId(Long actionId)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysNotificationTimeLimit.class);
        criteria.add(Restrictions.eq("sts", SysNotificationTimeLimit.STS_VALID));
        if (ValidateUtil.isNotNull(actionId)) {
            criteria.add(Restrictions.eq("actionId", actionId));
        }
        
        return (List<SysNotificationTimeLimit>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysTimeSegDef> getSysTimeSegDefBySegId(Long segId)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysTimeSegDef.class);
        criteria.add(Restrictions.eq("sts", SysTimeSegDef.STS_VALID));
        if (ValidateUtil.isNotNull(segId)) {
            criteria.add(Restrictions.eq("segId", segId));
        }
        return (List<SysTimeSegDef>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysTimeSegDtl> getSysTimeSegDtlBySegId(Long segId)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysTimeSegDtl.class);
        criteria.add(Restrictions.eq("sts", SysTimeSegDtl.STS_VALID));
        if (ValidateUtil.isNotNull(segId)) {
            criteria.add(Restrictions.eq("segId", segId));
        }
        return (List<SysTimeSegDtl>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysNotificationNumLimit> getSysNotificationNumLimit(Long actionId)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysNotificationNumLimit.class);
        criteria.add(Restrictions.eq("sts", SysNotificationNumLimit.STS_VALID));
        if (ValidateUtil.isNotNull(actionId)) {
            criteria.add(Restrictions.eq("actionId", actionId));
        }
        return (List<SysNotificationNumLimit>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public void deleteSysNotifyActionRel(final SysNotifyActionRel notifyActionRel)
    {
        notifyActionRel.setModifyDate(DateTimeUtil.getNow());
        notifyActionRel.setSts(SysNotifyActionRel.STS_INVALID);
        hibernateRepository.updateObject(notifyActionRel);
    }

    @Override
    public void deleteSysNotificationNumLimit(final SysNotificationNumLimit notificationNumLimit)
    {
        notificationNumLimit.setModifyDate(DateTimeUtil.getNow());
        notificationNumLimit.setOperatorId(BoContext.getBoContext().getUserId());        
        notificationNumLimit.setSts(SysNotificationNumLimit.STS_INVALID);
        hibernateRepository.updateObject(notificationNumLimit);
    }

    @Override
    public void deleteSysNotificationTimeLimit(final SysNotificationTimeLimit notificationTimeLimit)
    {
        notificationTimeLimit.setModifyDate(DateTimeUtil.getNow());
        notificationTimeLimit.setOperatorId(BoContext.getBoContext().getUserId());
        notificationTimeLimit.setSts(SysNotificationTimeLimit.STS_INVALID);
        hibernateRepository.updateObject(notificationTimeLimit);
    }

    @Override
    public void deleteSysTimeSegDef(final SysTimeSegDef timeSegDef)
    {
        timeSegDef.setModifyDate(DateTimeUtil.getNow());
        timeSegDef.setOperatorId(BoContext.getBoContext().getUserId());
        timeSegDef.setSts(SysTimeSegDef.STS_INVALID);
        hibernateRepository.updateObject(timeSegDef);
    }

    @Override
    public void deleteSysTimeSegDtl(final SysTimeSegDtl timeSegDtl)
    {
        timeSegDtl.setModifyDate(DateTimeUtil.getNow());
        timeSegDtl.setOperatorId(BoContext.getBoContext().getUserId());
        timeSegDtl.setSts(SysTimeSegDtl.STS_INVALID);
        hibernateRepository.updateObject(timeSegDtl);
    }

    @Override
    public void modifySysNotifyActionRel(final SysNotifyActionRel notifyActionRel)
    {
        notifyActionRel.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(notifyActionRel);
    }

    @Override
    public void modifySysNotificationNumLimit(final SysNotificationNumLimit notificationNumLimit)
    {
        notificationNumLimit.setModifyDate(DateTimeUtil.getNow());
        notificationNumLimit.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.updateObject(notificationNumLimit);
    }

    @Override
    public void modifySysNotificationTimeLimit(SysNotificationTimeLimit notificationTimeLimit)
    {
        notificationTimeLimit.setModifyDate(DateTimeUtil.getNow());
        notificationTimeLimit.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.updateObject(notificationTimeLimit);
    }

    @Override
    public void modifySysTimeSegDef(final SysTimeSegDef timeSegDef)
    {
        timeSegDef.setModifyDate(DateTimeUtil.getNow());
        timeSegDef.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.updateObject(timeSegDef);
    }

    @Override
    public void modifySysTimeSegDtl(SysTimeSegDtl timeSegDtl)
    {
        timeSegDtl.setModifyDate(DateTimeUtil.getNow());
        timeSegDtl.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.updateObject(timeSegDtl);
    }
    
}
