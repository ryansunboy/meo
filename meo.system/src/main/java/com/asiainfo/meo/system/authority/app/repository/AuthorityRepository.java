package com.asiainfo.meo.system.authority.app.repository;

import java.util.List;

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

public interface AuthorityRepository
{
    public List<SysMenu> getMenuList(MenuQueryConditionVO conditionVO, Integer pageSize, Integer pageNo);
    
    public Integer getMenuListTotalSize(MenuQueryConditionVO conditionVO);
    
    public Long saveMenu(SysMenu sysMenu);
    
    public void modifyMenu(SysMenu sysMenu);
    
    public SysMenu getSysMenuById(Long menuId);
    
    public List<SysOperater> getOperationList(Integer pageNo, Integer pageSize, OperatorQueryConditionVO conditionsVO);
    
    Integer getOperationListTotalSize(OperatorQueryConditionVO conditionsVO);
    
    public Long saveOperation(SysOperater sysOperater);
    
    public SysOperater getOperation(Long operId);
    
    public void modifyOperation(SysOperater sysOperater);
    
    public Long savePrivilege(SysPrivilege sysPrivilege);
    
    public void saveRolePrivilege(SysRolePrivilege sysRolePrivilege);
    
    public void savePrivilegeMenu(SysPrivilegeMenu sysPrivilegeMenu);
    
    public void savePrivilegeOperater(SysPrivilegeOperater sysPrivilegeOperater);
    
    public SysPrivilege getSysPrivilegeById(Long rivilegeId);
    
    public SysRole getSysRoleById(Long roleId);
    
    public List<SysRole> getSysRoleByIds(List<Long> roleIds);
    
    public void modifySysPrivilege(SysPrivilege sysPrivilege);
    
    public List<SysRolePrivilege> getRolePrivilegeByRoleId(Long roleId);
    
    public List<SysRolePrivilege> getRolePrivilegeByRoleIds(List<Long> roleIds);
    
    public void modifySysRolePrivilege(SysRolePrivilege sysRolePrivilege);
    
    /**
      * @Description: 获得所有的sysPrivilegeMenu信息
      * @Description: get all sysPrivilegeMenu
      * @author zhaozx
      * @return
      */
    public List<SysPrivilegeMenu> getSysPrivilegeMenuList();
    
    public List<SysPrivilegeMenu> getSysPrivilegeMenuListByPrivIds(List<Long> privIds);
    
    public List<SysPrivilegeOperater> getSysPrivilegeOperaterListByPrivId(Long privId);
    
    public SysPrivilegeMenu getSysPrivilegeMenuById(Long menuId, Long privId);
    
    public SysPrivilegeOperater getSysPrivilegeOperaterById(Long operId, Long privId);
    
    public void modifyPrivilegeMenu(SysPrivilegeMenu sysPrivilegeMenu);
    
    public void modifyPrivilegeOperater(SysPrivilegeOperater sysPrivilegeOperater);
    
    public void updateSysRoleUser(SysRoleUser sysRoleUser);
    
    public void addSysRoleUser(SysRoleUser sysRoleUser);
    
    public List<SysRoleUser> getSysRoleUserByUserId(Long userId);
    
    public List<SysRoleUser> getSysRoleUserByRoleId(Long roleId);
    
    public void deleteSysRoleUserByUserId(Long userId);
    
    public Long saveSysRole(SysRole sysRole);
    
}
