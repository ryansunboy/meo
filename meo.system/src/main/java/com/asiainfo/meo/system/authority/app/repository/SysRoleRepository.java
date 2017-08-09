package com.asiainfo.meo.system.authority.app.repository;

import java.util.List;

import com.asiainfo.meo.system.authority.app.model.entity.SysRole;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;

public interface SysRoleRepository
{
    public Integer getRoleInfoTotalSize(UserRoleQueryConditionVO conditionVO);
    
    public List<SysRole> getRoleInfoList(UserRoleQueryConditionVO conditionVO, Integer pageSize, Integer pageNo);
    
    public SysRole saveRole(SysRole sysRole);
    
    public SysRole modifyRole(SysRole sysRole);
    
    public SysRole getSysRoleByRoleId(Long roleId);
    
    /**
     * 
     * @Description: 根据角色Id删除角色
      * @Description: delete role by roleId
      * @modifyReason: 
      * @author zhengzy
      * @param roleId
     */
    public void deleteRoleByRoleId(Long roleId);
    
    public List<SysRole> getRoles(RolePrivilegeQueryConditionVO conditionVO);
    
}
