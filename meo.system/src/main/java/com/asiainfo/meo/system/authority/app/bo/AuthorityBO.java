package com.asiainfo.meo.system.authority.app.bo;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.system.authority.app.model.entity.SysRolePrivilege;
import com.asiainfo.meo.system.authority.app.model.vo.MenuQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperatorQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserMenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserOperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleVO;

public interface AuthorityBO
{
    public PageInfo<UserRoleVO> getRoleInfoList(final UserRoleQueryConditionVO conditionVO);

    /**
     * 
      * @Description: 新增角色
      * @Description: add role
      * @modifyReason: 
      * @author zhengzy
      * @param userRole
      * @return
     */
    public UserRoleVO addRole(UserRoleVO userRole);
    
    /**
     * 
      * @Description: 修改角色
      * @Description: update role
      * @modifyReason: 
      * @author zhengzy
      * @param userRole
      * @return
     */
    public UserRoleVO updateRole(UserRoleVO userRole);
    
    /**
     * 
      * @Description: update operator
      * @modifyReason: 
      * @author Thanapol
      * @param operator
      * @return
     */
    public OperaterVO updateOperator(OperaterVO operator);
    
    public UserRoleVO getRole(Long roleId);
    
    /**
     * 
     * @Description: 根据角色Id删除角色
      * @Description: delete role by roleId
      * @modifyReason: 
      * @author zhengzy
      * @param roleId
     */
    public void deleteRoleByRoleId(Long roleId);
    
    public PageInfo<UserMenuVO> getMenuList(MenuQueryConditionVO conditionVO);
    
    public Long saveMenu(UserMenuVO sysMenu);
    
    public void modifyMenu(UserMenuVO userMenuVO);
    
    public void deleteMenu(Long menuId);
    
    public PageInfo<UserOperaterVO> getOperationList(OperatorQueryConditionVO conditionsVO);
    
    public Long saveOperation(String operName);
    
    public void deleteOperation(Long operId);
    
    public void saveRolePrivilege(RolePrivilegeVO rolePrivilegeVO);
    
    public void deleteRolePrivilege(Long roleId);
    /**
     * @Description: 查询所有角色的权限
     * @Description: get all role privilege
     * @author zhengzy
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<RPrivilegeVO> getAllRolePrivilegeList(RolePrivilegeQueryConditionVO conditionVO);
    
    /**
     * 
      * @Description: 根据用户Id查询用户权限
      * @Description: query user privilege list by userId
      * @modifyReason: 
      * @author zhengzy
      * @param userId
      * @return
     */
    public List<UPrivilegeVO> getUPrivilegeListByUserId(Long userId);
    
    public void deletePrivlegeMenu(Long privilegeId, Long menuId);
    
    public void deletePrivlegeOperate(Long privilegeId, Long operateId);
    
    public List<SysRolePrivilege> getRolePrivilegeByRoleId(Long roleId);
}
