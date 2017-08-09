package com.asiainfo.meo.system.common.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.repository.SysEnumGroupRelRepository;

public class SysEnumGroupRelRepositoryImpl implements SysEnumGroupRelRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_ENUM_GROUP_REL_ID = "SEQ_ENUM_GROUP_REL_ID";
    
    @Override
    public void addSysEnumGroupRel(SysEnumGroupRel sysEnumGroupRel)
    {
        Long relId = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_ENUM_GROUP_REL_ID);
        sysEnumGroupRel.setRelId(relId);
        sysEnumGroupRel.setCreateDate(DateTimeUtil.getNow());
        sysEnumGroupRel.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.saveObject(sysEnumGroupRel);
    }
    
    @Override
    public void updateSysEnumGroupRel(SysEnumGroupRel sysEnumGroupRel)
    {
        sysEnumGroupRel.setModifyDate(DateTimeUtil.getNow());
        sysEnumGroupRel.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.updateObject(sysEnumGroupRel);
    }
    
    @Override
    public SysEnumGroupRel getSysEnumGroupRel(Long relId)
    {
        return hibernateRepository.get(SysEnumGroupRel.class, relId);
    }
    
    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumIdAndGroupId(Long enumId, Long groupId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupRel.class);
        if(enumId!=null)
        {
            criteria.add(Restrictions.eq("enumId", enumId));
        }
        if(groupId!=null)
        {
            criteria.add(Restrictions.eq("groupId", groupId));
        }
        criteria.add(Restrictions.eq("sts", SysEnumGroupRel.STS_VALID));
        return (List<SysEnumGroupRel>)hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupRel.class);
        criteria.add(Restrictions.in("groupId", groupIds));
        criteria.add(Restrictions.eq("sts", SysEnumGroupRel.STS_VALID));
        return (List<SysEnumGroupRel>)hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumId(Long enumId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupRel.class);
        criteria.add(Restrictions.eq("enumId", enumId));
        criteria.add(Restrictions.eq("sts", SysEnumGroupRel.STS_VALID));
        return (List<SysEnumGroupRel>)hibernateRepository.findByCriteria(criteria);
    }
    
}
