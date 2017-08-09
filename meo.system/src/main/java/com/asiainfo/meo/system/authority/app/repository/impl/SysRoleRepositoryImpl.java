package com.asiainfo.meo.system.authority.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.authority.app.model.entity.SysRole;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;
import com.asiainfo.meo.system.authority.app.repository.SysRoleRepository;

public class SysRoleRepositoryImpl implements SysRoleRepository
{
    private static final String SEQ_SYS_ROLE_ID = "SEQ_SYS_ROLE_ID";
    
    @Resource
    private HibernateRepository repository;
    
    private DetachedCriteria getRoleInfoDetachedCriteria(final UserRoleQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysRole.class);
        criteria.add(Restrictions.eq("sts", SysRole.STS_VALID));
        if (ValidateUtil.isNotNull(conditionVO))
        {
            if (ValidateUtil.isNotEmpty(conditionVO.getRoleId()))
            {
                criteria.add(Restrictions.eq("roleId", conditionVO.getRoleId()));
            }
            if (ValidateUtil.isNotEmpty(conditionVO.getRoleName()))
            {
                criteria.add(Restrictions.ilike("roleName", conditionVO.getRoleName(), MatchMode.ANYWHERE));
            }
        }
        return criteria;
    }
    
    @Override
    public List<SysRole> getRoleInfoList(final UserRoleQueryConditionVO conditionVO, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria criteria = getRoleInfoDetachedCriteria(conditionVO);                
        return (List<SysRole>) repository.findByCriteria(criteria, (pageNo - 1)* pageSize, pageSize);
    }
    
    @Override
    public Integer getRoleInfoTotalSize(final UserRoleQueryConditionVO conditionVO)
    {
        Integer totalSize = null;
        DetachedCriteria criteria = getRoleInfoDetachedCriteria(conditionVO);
        final List<SysRole> list = (List<SysRole>) repository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            totalSize = list.size();
        }
        return totalSize;
    }
    
    @Override
    public SysRole saveRole(SysRole sysRole)
    {
        Long roleId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_ROLE_ID);
        sysRole.setRoleId(roleId);
        sysRole.setSts(SysRole.STS_VALID);
        repository.saveObject(sysRole);
        return getSysRoleByRoleId(roleId);
    }

    @Override
    public SysRole modifyRole(SysRole sysRole)
    {
        sysRole.setModifyDate(DateTimeUtil.getNow());
        repository.updateObject(sysRole);
        return getSysRoleByRoleId(sysRole.getRoleId());
    }

    @Override
    public SysRole getSysRoleByRoleId(Long roleId)
    {
        return repository.get(SysRole.class, roleId);
    }

    @Override
    public void deleteRoleByRoleId(Long roleId)
    {
        SysRole role = getSysRoleByRoleId(roleId);
        repository.deleteObject(role);
    }
    
    private DetachedCriteria getRolesDetachedCriteria(final RolePrivilegeQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysRole.class);
        criteria.add(Restrictions.eq("sts", SysRole.STS_VALID));
        if (ValidateUtil.isNotNull(conditionVO))
        {
            //TODO filter more criteria for role privilege here
        }
        
        return criteria;
    }
    
    @Override
    public List<SysRole> getRoles(final RolePrivilegeQueryConditionVO conditionVO)
    {
        DetachedCriteria criteria = getRolesDetachedCriteria(conditionVO);
        criteria.add(Restrictions.eq("sts", SysRole.STS_VALID));
        return (List<SysRole>) repository.findByCriteria(criteria);
    }   
    
}
