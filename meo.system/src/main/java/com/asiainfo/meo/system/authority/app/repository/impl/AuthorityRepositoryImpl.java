package com.asiainfo.meo.system.authority.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.authority.app.model.entity.SysMenu;
import com.asiainfo.meo.system.authority.app.model.entity.SysOperater;
import com.asiainfo.meo.system.authority.app.model.entity.SysPrivilege;
import com.asiainfo.meo.system.authority.app.model.entity.SysPrivilegeMenu;
import com.asiainfo.meo.system.authority.app.model.entity.SysPrivilegeOperater;
import com.asiainfo.meo.system.authority.app.model.entity.SysRole;
import com.asiainfo.meo.system.authority.app.model.entity.SysRolePrivilege;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.MenuQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperatorQueryConditionVO;
import com.asiainfo.meo.system.authority.app.repository.AuthorityRepository;

public class AuthorityRepositoryImpl implements AuthorityRepository
{
    private static final String SEQ_SYS_MENU_ID           = "SEQ_SYS_MENU_ID";
    
    private static final String SEQ_SYS_OPERATER_ID       = "SEQ_SYS_OPERATER_ID";
    
    private static final String SEQ_SYS_PRIVILEGE_ID      = "SEQ_SYS_PRIVILEGE_ID";
    
    private static final String SEQ_SYS_ROLE_PRIVILEGE_ID = "SEQ_SYS_ROLE_PRIVILEGE_ID";
    
    private static final String SEQ_SYS_MENU_PRIVILEGE_ID = "SEQ_SYS_MENU_PRIVILEGE_ID";
    
    private static final String SEQ_SYS_OPER_PRIVILEGE_ID = "SEQ_SYS_OPER_PRIVILEGE_ID";
    
    private static final String SEQ_SYS_ROLE_ID = "SEQ_SYS_ROLE_ID";
    
    private static final String SEQ_SYS_ROLE_USER_ID = "SEQ_SYS_ROLE_USER_ID";
    
    @Resource
    private HibernateRepository repository;
    
    @Override
    public List<SysMenu> getMenuList(final MenuQueryConditionVO conditionVO, final Integer pageSize, Integer pageNo)
    {
        final DetachedCriteria criteria = getMenuListDetachedCriteria(conditionVO);
        criteria.add(Restrictions.eq("sts", SysMenu.STS_VALID));
        return (List<SysMenu>) repository.findByCriteria(criteria, (pageNo- 1)* pageSize, pageSize);
    }
    
    @Override
    public Integer getMenuListTotalSize(MenuQueryConditionVO conditionVO)
    {
        Integer totalSize = null;
        final DetachedCriteria criteria = getMenuListDetachedCriteria(conditionVO);
        final List<SysMenu> list = (List<SysMenu>) repository.findByCriteria(criteria);
        if (ValidateUtil.isNotNull(list)) 
        {
            totalSize = new Integer(list.size());
        }
        return totalSize;
    }
    
    private DetachedCriteria getMenuListDetachedCriteria(final MenuQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysMenu.class);
        criteria.add(Restrictions.eq("sts", SysOperater.STS_VALID));
        if (ValidateUtil.isNotNull(conditionVO))
        {
            //TODO filter more criteria here
        }
        return criteria;
    }
    
    @Override
    public Long saveMenu(SysMenu sysMenu)
    {
        Long menuId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_MENU_ID);
        sysMenu.setMenuId(menuId);
        repository.saveObject(sysMenu);
        return menuId;
    }
    
    @Override
    public void modifyMenu(SysMenu sysMenu)
    {
        repository.updateObject(sysMenu);
    }
    
    @Override
    public SysMenu getSysMenuById(Long menuId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMenu.class);
        criteria.add(Restrictions.eq("menuId", menuId));
        return (SysMenu) repository.findUniqueObjectByCriteria(criteria);
    }
    
    private DetachedCriteria getOperationListDetachedCriteria(final OperatorQueryConditionVO conditionsVO) 
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysOperater.class);
        criteria.add(Restrictions.eq("sts", SysOperater.STS_VALID));
        if (ValidateUtil.isNotNull(conditionsVO))
        {
            if (ValidateUtil.isNotEmpty(conditionsVO.getOperatorId()))
            {
                criteria.add(Restrictions.eq("operId", conditionsVO.getOperatorId()));
            }
            if (ValidateUtil.isNotEmpty(conditionsVO.getOperatorName()))
            {
                criteria.add(Restrictions.ilike("operName", conditionsVO.getOperatorName().trim(), MatchMode.ANYWHERE));
            }
        }
        return criteria;
    }
    
    @Override
    public List<SysOperater> getOperationList(Integer pageNo, Integer pageSize, final OperatorQueryConditionVO conditionsVO)
    {
        final DetachedCriteria criteria = getOperationListDetachedCriteria(conditionsVO);        
        return (List<SysOperater>) repository.findByCriteria(criteria, (pageNo- 1)* pageSize, pageSize);
    }
    
    @Override
    public Integer getOperationListTotalSize(OperatorQueryConditionVO conditionsVO)
    {
        Integer totalSize = null;
        final DetachedCriteria criteria = getOperationListDetachedCriteria(conditionsVO);
        final List<SysOperater> list = (List<SysOperater>) repository.findByCriteria(criteria);
        if (ValidateUtil.isNotNull(list)) 
        {
            totalSize = new Integer(list.size());
        }
        return totalSize;
    }
    
    @Override
    public Long saveOperation(SysOperater sysOperater)
    {
        Long operaterId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_OPERATER_ID);
        sysOperater.setOperId(operaterId);
        repository.saveObject(sysOperater);
        return operaterId;
    }
    
    @Override
    public SysOperater getOperation(Long operId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysOperater.class);
        criteria.add(Restrictions.eq("operId", operId));
        criteria.add(Restrictions.eq("sts", SysOperater.STS_VALID));
        return (SysOperater) repository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public void modifyOperation(SysOperater sysOperater)
    {
        repository.updateObject(sysOperater);
    }
    
    @Override
    public Long savePrivilege(SysPrivilege sysPrivilege)
    {
        Long privId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_PRIVILEGE_ID);
        sysPrivilege.setPrivId(privId);
        repository.saveObject(sysPrivilege);
        return privId;
    }
    
    @Override
    public void saveRolePrivilege(SysRolePrivilege sysRolePrivilege)
    {
        Long privRoleId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_ROLE_PRIVILEGE_ID);
        sysRolePrivilege.setId(privRoleId);
        repository.saveObject(sysRolePrivilege);
    }
    
    @Override
    public void savePrivilegeMenu(SysPrivilegeMenu sysPrivilegeMenu)
    {
        Long id = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_MENU_PRIVILEGE_ID);
        sysPrivilegeMenu.setId(id);
        repository.saveObject(sysPrivilegeMenu);
    }
    
    @Override
    public void savePrivilegeOperater(SysPrivilegeOperater sysPrivilegeOperater)
    {
        Long id = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_OPER_PRIVILEGE_ID);
        sysPrivilegeOperater.setId(id);
        repository.saveObject(sysPrivilegeOperater);
    }
    
    @Override
    public SysPrivilege getSysPrivilegeById(Long rivilegeId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPrivilege.class);
        criteria.add(Restrictions.eq("privId", rivilegeId));
        return (SysPrivilege) repository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public SysRole getSysRoleById(Long roleId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRole.class);
        criteria.add(Restrictions.eq("roleId", roleId));
        return (SysRole) repository.findUniqueObjectByCriteria(criteria);
    }
    
    public List<SysRole> getSysRoleByIds(List<Long> roleIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRole.class);
        criteria.add(Restrictions.in("roleId", roleIds));
        criteria.add(Restrictions.eq("sts", SysRole.STS_VALID));
        return (List<SysRole>) repository.findByCriteria(criteria);
    }
    
    @Override
    public void modifySysPrivilege(SysPrivilege sysPrivilege)
    {
        repository.updateObject(sysPrivilege);
    }
    
    public List<SysRolePrivilege> getRolePrivilegeByRoleId(Long roleId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRolePrivilege.class);
        criteria.add(Restrictions.eq("roleId", roleId));
        criteria.add(Restrictions.eq("sts", SysRolePrivilege.STS_VALID));
        return (List<SysRolePrivilege>) repository.findByCriteria(criteria);
    }
    
    public List<SysRolePrivilege> getRolePrivilegeByRoleIds(List<Long> roleIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRolePrivilege.class);
        criteria.add(Restrictions.in("roleId", roleIds));
        criteria.add(Restrictions.eq("sts", SysRolePrivilege.STS_VALID));
        return (List<SysRolePrivilege>) repository.findByCriteria(criteria);
    }
    
    @Override
    public void modifySysRolePrivilege(SysRolePrivilege sysRolePrivilege)
    {
        repository.updateObject(sysRolePrivilege);
    }
    
    /**
     * @Description: 获得所有的sysPrivilegeMenu信息
     * @Description: get all sysPrivilegeMenu
     * @author zhaozx
     * @return
     */
    @Override
    public List<SysPrivilegeMenu> getSysPrivilegeMenuList()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPrivilegeMenu.class);
        criteria.add(Restrictions.eq("sts", SysPrivilegeMenu.STS_VALID));
        return (List<SysPrivilegeMenu>) repository.findByCriteria(criteria);
    }
    
    @Override
    public List<SysPrivilegeMenu> getSysPrivilegeMenuListByPrivIds(List<Long> privIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPrivilegeMenu.class);
        criteria.add(Restrictions.eq("sts", SysPrivilegeMenu.STS_VALID));
        criteria.add(Restrictions.in("privId", privIds));
        return (List<SysPrivilegeMenu>) repository.findByCriteria(criteria);
    }
    
    @Override
    public List<SysPrivilegeOperater> getSysPrivilegeOperaterListByPrivId(Long privId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPrivilegeOperater.class);
        criteria.add(Restrictions.eq("sts", SysPrivilegeOperater.STS_VALID));
        criteria.add(Restrictions.eq("privId", privId));
        return (List<SysPrivilegeOperater>) repository.findByCriteria(criteria);
    }
    
    @Override
    public SysPrivilegeMenu getSysPrivilegeMenuById(Long menuId, Long privId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPrivilegeMenu.class);
        criteria.add(Restrictions.eq("menuId", menuId));
        criteria.add(Restrictions.eq("privId", privId));
        return (SysPrivilegeMenu)repository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public SysPrivilegeOperater getSysPrivilegeOperaterById(Long operId, Long privId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPrivilegeOperater.class);
        criteria.add(Restrictions.eq("privId", privId));
        criteria.add(Restrictions.eq("operId", operId));
        return (SysPrivilegeOperater)repository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public void modifyPrivilegeMenu(SysPrivilegeMenu sysPrivilegeMenu)
    {
        repository.updateObject(sysPrivilegeMenu);
    }

    @Override
    public void modifyPrivilegeOperater(SysPrivilegeOperater sysPrivilegeOperater)
    {
        repository.updateObject(sysPrivilegeOperater);
    }

    @Override
    public void updateSysRoleUser(SysRoleUser sysRoleUser)
    {
        sysRoleUser.setModifyDate(sysRoleUser.getModifyDate());
        repository.updateObject(sysRoleUser);
    }

    @Override
    public void addSysRoleUser(SysRoleUser sysRoleUser)
    {
        Long id = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_ROLE_USER_ID);
        sysRoleUser.setId(id);
        repository.saveObject(sysRoleUser);
    }
    
    public Long saveSysRole(SysRole sysRole)
    {
        Long roleId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_SYS_ROLE_ID);
        sysRole.setRoleId(roleId);
        sysRole.setSts(SysRole.STS_VALID);
        repository.saveObject(sysRole);
        return roleId;
    }

    @Override
    public List<SysRoleUser> getSysRoleUserByUserId(Long userId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRoleUser.class);
        criteria.add(Restrictions.eq("userId", userId));
        return (List<SysRoleUser>)repository.findByCriteria(criteria);
    }

    @Override
    public List<SysRoleUser> getSysRoleUserByRoleId(Long roleId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRoleUser.class);
        criteria.add(Restrictions.eq("roleId", roleId));
        return (List<SysRoleUser>)repository.findByCriteria(criteria);
    }
    
    @Override
    public void deleteSysRoleUserByUserId(Long userId)
    {
        List<SysRoleUser> roleUsers =  getSysRoleUserByUserId(userId);
        repository.deleteAll(roleUsers);
    }        

}
