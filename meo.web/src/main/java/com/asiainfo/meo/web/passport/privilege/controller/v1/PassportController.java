package com.asiainfo.meo.web.passport.privilege.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.system.authority.app.model.vo.MenuQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperatorQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserVO;
import com.asiainfo.meo.system.authority.app.model.vo.UPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserMenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserOperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleVO;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.web.component.PassportComponent;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIRPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIRolePrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISysMenuVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISystemUserQueryConditionVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISystemUserVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUserMenuVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUserOperaterVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIUserRoleVO;

@RestController
@RequestMapping("/passport/authority")
public class PassportController
{
    private static final Log  LOG = LogFactory.getLog(PassportController.class);
    
    @Resource
    private PassportComponent passportComponent;
    
    @Resource
    private SystemPserviceBO  systemPserviceBO;
    
    @RequestMapping(params = "method=meo.role.list")
    public PageInfo<UIUserRoleVO> getRoleList(@RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) String roleName)
    {
        LOG.debug("-------------meo.role.list--------begin-------");
        final UserRoleQueryConditionVO conditionVO = passportComponent.transformRoleCriteriaToRoleQueryConditionVO(pageNo, pageSize, roleId, roleName);
        PageInfo<UserRoleVO> userRoleVOPageInfo = systemPserviceBO.getRoleInfoList(conditionVO);
        PageInfo<UIUserRoleVO> uiUserRoleVOList = passportComponent.transferUserRolePageInfoToUIuserRolePageInfo(userRoleVOPageInfo);
        LOG.debug("-------------meo.role.list--------end-------");
        return uiUserRoleVOList;
    }
    
    /**
     * @Description: 添加角色
     * @Description: add role
     * @modifyReason:
     * @author zhengzy
     * @param userRole
     * @return
     */
    @RequestMapping(params = "method=meo.role.add", method = {RequestMethod.POST })
    public Map<String, Long> addRole(@RequestBody @Validated(value = Insert.class) UIUserRoleVO userRole)
    {
        LOG.debug("-------------meo.role.add--------begin-------");
        Map<String, Long> map = new HashMap<String, Long>();
        UserRoleVO userRoleVO = passportComponent.transferUIUserRoleVOToUserRoleVO(userRole);
        UserRoleVO userRoleR = systemPserviceBO.addRole(userRoleVO);
        if (ValidateUtil.isNull(userRoleR)|| ValidateUtil.isNull(userRoleR.getRoleId()))
        {
            LOG.debug("add role failure");
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{SysConstantDefine.ROLE });
        }
        map.put("roleId", userRoleR.getRoleId());
        LOG.debug("-------------meo.role.add--------end-------");
        return map;
    }
    
    /**
     * @Description: 修改角色
     * @Description: update role
     * @modifyReason:
     * @author zhengzy
     * @param userRole
     * @return
     */
    @RequestMapping(params = "method=meo.role.modify", method = {RequestMethod.POST })
    public Map<String, Long> updateRole(@RequestBody @Validated(value = Update.class) UIUserRoleVO userRole)
    {
        LOG.debug("-------------meo.role.modify--------begin-------");
        Map<String, Long> map = new HashMap<String, Long>();
        UserRoleVO userRoleVO = passportComponent.transferUIUserRoleVOToUserRoleVO(userRole);
        UserRoleVO userRoleR = systemPserviceBO.updateRole(userRoleVO);
        if (ValidateUtil.isNull(userRoleR)|| ValidateUtil.isNull(userRoleR.getRoleId()))
        {
            LOG.debug("update role failure");
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{SysConstantDefine.ROLE });
        }
        map.put("roleId", userRoleR.getRoleId());
        LOG.debug("-------------meo.role.modify--------end-------");
        return map;
    }
    
    /**
     * @Description: 删除角色
     * @Description: delete role by roleid
     * @modifyReason:
     * @author zhengzy
     * @param roleId
     */
    @RequestMapping(params = "method=meo.role.delete", method = {RequestMethod.GET })
    public void deleteRole(@RequestParam Long roleId)
    {
        LOG.debug("-------------meo.role.delete--------begin-------");
        systemPserviceBO.deleteRoleByRoleId(roleId);
        LOG.debug("-------------meo.role.delete--------end-------");
        return;
    }
    
    @RequestMapping(params = "method=meo.menu.get")
    public PageInfo<UISysMenuVO> getMenuList(@RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        LOG.debug("-------------meo.menu.get--------begin-------");
        final MenuQueryConditionVO conditionVO = passportComponent.transferMenuCriteriaToMenuQueryConditionVO(pageNo, pageSize);
        PageInfo<UserMenuVO> userMenuVOPageInfo = systemPserviceBO.getMenuList(conditionVO);
        PageInfo<UISysMenuVO> uiUserMenuVOPageInfo = passportComponent.transformMenuVOPageInfoToUIMenuVOPageInfo(userMenuVOPageInfo);
        LOG.debug("-------------meo.menu.get--------end-------");
        return uiUserMenuVOPageInfo;
    }
    
    @RequestMapping(params = "method=meo.menu.add")
    public Map<String, Long> saveMenu(@RequestBody @Validated(value = Insert.class) UIUserMenuVO uiUserMenuVO)
    {
        LOG.debug("-------------meo.menu.add--------begin-------");
        UserMenuVO userMenuVO = passportComponent.transferUIUserMenuVOToUserMenuVO(uiUserMenuVO);
        Long menuId = systemPserviceBO.saveMenu(userMenuVO);
        Map<String, Long> menuIdMap = new HashMap<String, Long>();
        menuIdMap.put("menuId", menuId);
        LOG.debug("-------------meo.menu.add--------end-------");
        return menuIdMap;
    }
    
    @RequestMapping(params = "method=meo.menu.modify")
    public void modifyMenu(@RequestBody @Validated(value = Update.class) UIUserMenuVO uiUserMenuVO)
    {
        LOG.debug("-------------meo.menu.add--------begin-------");
        UserMenuVO userMenuVO = passportComponent.transferModifyUIUserMenuVOToUserMenuVO(uiUserMenuVO);
        systemPserviceBO.modifyMenu(userMenuVO);
        LOG.debug("-------------meo.menu.add--------end-------");
    }
    
    @RequestMapping(params = "method=meo.menu.delete")
    public void deleteMenu(@RequestParam Long menuId)
    {
        LOG.debug("-------------meo.menu.add--------begin-------");
        systemPserviceBO.deleteMenu(menuId);
        LOG.debug("-------------meo.menu.add--------end-------");
    }
    
    @RequestMapping(params = "method=meo.operation.list")
    public PageInfo<UIUserOperaterVO> getOperationList(@RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long operatorId,
            @RequestParam(required = false) String operatorName)
    {
        
        LOG.debug("-------------meo.operation.list--------begin-------");
        final OperatorQueryConditionVO  conditionsVO = passportComponent
                .transferOperatorCriteriaToOperatorQueryConditionVO(pageNo, pageSize, operatorId, operatorName);

        PageInfo<UserOperaterVO> userOperaterVOList = systemPserviceBO.getOperationList(conditionsVO);
        PageInfo<UIUserOperaterVO> uiUserOperaterVOList = passportComponent
                .transformOperatorVOPageInfoToUIOperatorVOPageInfo(userOperaterVOList);
        LOG.debug("-------------meo.operation.list--------end-------");
        return uiUserOperaterVOList;
    }
    
    @RequestMapping(params = "method=meo.operation.add")
    public Map<String, Long> saveOperation(@RequestParam String operName)
    {
        LOG.debug("-------------meo.operation.add--------begin-------");
        Long operationId = systemPserviceBO.saveOperation(operName);
        Map<String, Long> menuIdMap = new HashMap<String, Long>();
        menuIdMap.put("operationId", operationId);
        LOG.debug("-------------meo.operation.add--------end-------");
        return menuIdMap;
    }
    
    @RequestMapping(params = "method=meo.operation.delete")
    public void deleteOperation(@RequestParam Long operId)
    {
        LOG.debug("-------------meo.operation.delete--------begin-------");
        systemPserviceBO.deleteOperation(operId);
        LOG.debug("-------------meo.operation.delete--------end-------");
    }
    
    @RequestMapping(params = "method=meo.privilege.role.add", method = RequestMethod.POST)
    public void createRolePrivilege(@RequestBody @Validated(value = Insert.class) UIRolePrivilegeVO rolePrivilege)
    {
        LOG.debug("-------------meo.privilege.role.add--------begin-------");
        RolePrivilegeVO rolePrivilegeVO = passportComponent.transferUIRolePrivilegeVOToRolePrivilegeVO(rolePrivilege);
        systemPserviceBO.saveRolePrivilege(rolePrivilegeVO);
        System.out.println(JsonUtil.writeObjectAsString(rolePrivilegeVO));
        LOG.debug("-------------meo.privilege.role.add--------end-------");
    }
    
    @RequestMapping(params = "method=meo.privilege.role.delete", method = RequestMethod.GET)
    public void deleteRolePrivilege(@RequestParam Long roleId)
    {
        LOG.debug("-------------meo.privilege.role.delete--------begin-------");
        systemPserviceBO.deleteRolePrivilege(roleId);
        LOG.debug("-------------meo.privilege.role.delete--------end-------");
    }
    
    /**
     * @Description: 获取systemUser 列表
     * @Description: query systemUser list
     * @modifyReason:
     * @author zhengzy
     * @return
     */
    @RequestMapping(params = "method=meo.user.system.list", method = RequestMethod.POST)
    public PageInfo<UISystemUserVO> getSystemUserList(@RequestBody(required = false) UISystemUserQueryConditionVO systemUser)
    {
        LOG.debug("-------------meo.user.system.list--------begin-------");
        SystemUserQueryConditionVO systemUserVo  = new SystemUserQueryConditionVO();
        systemUserVo = passportComponent.transferUISystemUserQueryCondtionVOToSystemUserQueryCondtionVO(systemUser);
        PageInfo<SystemUserVO> pageInfo = systemPserviceBO.getSystemUserVOPageInfo(systemUserVo);
        PageInfo<UISystemUserVO> uiPageInfo = passportComponent.transferSystemUserVOPageInfoToUISystemUserVOPageInfo(pageInfo);
        LOG.debug("-------------meo.user.system.list--------end-------");
        return uiPageInfo;
    }
    
    /**
     * 
      * @Description: 根据userId查询systemUesr
      * @Description: query systemUser by userId
      * @modifyReason: 
      * @author zhengzy
      * @param userId
      * @return
     */
    @RequestMapping(params = "method=meo.user.system.get", method = RequestMethod.GET)
    public UISystemUserVO getSystemUserByUserId(@RequestParam Long userId)
    {
        LOG.debug("-------------meo.user.system.get--------begin-------");
        SystemUserVO systemUser = systemPserviceBO.getSystemUser(userId);
        UISystemUserVO uiSystemUserVo = passportComponent.transferSystemUserVOToUISystemUserVO(systemUser);
        LOG.debug("-------------meo.user.system.get--------end-------");
        return uiSystemUserVo;
    }
    
    /**
     * @Description: 新增systemUser
     * @Description: add systemUser
     * @modifyReason:
     * @author zhengzy
     * @param systemUser
     * @return
     */
    @RequestMapping(params = "method=meo.user.system.add", method = RequestMethod.POST)
    public Map<String, Long> createSystmeUser(@RequestBody @Validated(Insert.class) UISystemUserVO systemUser)
    {
        LOG.debug("-------------meo.user.system.add--------begin-------");
        SystemUserVO systemUserVO = passportComponent.transferUISystemUserVOToSystemUserVO(systemUser);
        SystemUserVO systemUserVOR = systemPserviceBO.saveSystemUser(systemUserVO);
        if (ValidateUtil.isNull(systemUserVOR)|| ValidateUtil.isNull(systemUserVOR.getUserId()))
        {
            LOG.debug("create systemUser failure");
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{SysConstantDefine.SYSTEM_USER });
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("userId", systemUserVOR.getUserId());
        LOG.debug("-------------meo.user.system.add--------end-------");
        return map;
    }
    
    /**
     * @Description: 修改systemUser
     * @Description: modify systemUser
     * @modifyReason:
     * @author zhengzy
     * @param systemUser
     * @return
     */
    @RequestMapping(params = "method=meo.user.system.modify", method = RequestMethod.POST)
    public Map<String, Long> updateSystmeUser(@RequestBody @Validated(Update.class) UISystemUserVO systemUser)
    {
        LOG.debug("-------------meo.user.system.modify--------begin-------");
        SystemUserVO systemUserVO = passportComponent.transferUISystemUserVOToSystemUserVO(systemUser);
        SystemUserVO systemUserVOR = systemPserviceBO.updateSystemUser(systemUserVO);
        if (ValidateUtil.isNull(systemUserVOR)|| ValidateUtil.isNull(systemUserVOR.getUserId()))
        {
            LOG.debug("update systemUser failure");
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{SysConstantDefine.SYSTEM_USER });
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("userId", systemUserVOR.getUserId());
        LOG.debug("-------------meo.user.system.modify--------end-------");
        return map;
    }
    
    /**
     * @Description: 删除systemUser
     * @Description: delete systemUser by userId
     * @modifyReason:
     * @author zhengzy
     * @param userId
     */
    @RequestMapping(params = "method=meo.user.system.delete", method = RequestMethod.GET)
    public void deleteSystemUser(@RequestParam Long userId)
    {
        LOG.debug("-------------meo.user.system.delete--------begin-------");
        systemPserviceBO.deleteSystemUserByuserId(userId);
        LOG.debug("-------------meo.user.system.delete--------end-------");
        return;
    }
    
    /**
     * @Description: 查询角色对应的权限集合
     * @Description: query role privilege list
     * @modifyReason:
     * @author zhengzy
     * @return
     */
    @RequestMapping(params = "method=meo.privilege.role.get", method = {RequestMethod.GET })
    public PageInfo<UIRPrivilegeVO> getRolePrivilegeList(@RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        //TODO 
        LOG.debug("-------------meo.privilege.role.get--------begin-------");
        final RolePrivilegeQueryConditionVO conditionVO = passportComponent.transformRPrivilegeCriteriaToRPrivilegeQueryConditionVO(pageNo, pageSize);
        PageInfo<RPrivilegeVO> rPrivilegePageInfo = systemPserviceBO.getAllRolePrivilegeList(conditionVO);
        PageInfo<UIRPrivilegeVO> list = passportComponent.transferRPrivilegeVOPageInfoToUIRPrivilegeVOPageInfo(rPrivilegePageInfo);
        LOG.debug("-------------meo.privilege.role.get--------end-------");
        return list;
    }
    
    /**
     * @Description: 查询用户对应的角色集合
     * @Description: query user privilege list
     * @modifyReason:
     * @author zhengzy
     * @return
     */
    @RequestMapping(params = "method=meo.privilege.user.get", method = {RequestMethod.GET })
    public List<UIUPrivilegeVO> getSystemUserPrivilegeListByUserId()
    {
        LOG.debug("-------------meo.privilege.user.get--------begin-------");
        Long userId = BoContext.getBoContext().getUserId();
        List<UPrivilegeVO> uPrivilegeList = systemPserviceBO.getUPrivilegeListByUserId(userId);
        List<UIUPrivilegeVO> list = passportComponent.transferUPrivilegeVOListToUIUPrivilegeVOList(uPrivilegeList);
        LOG.debug("-------------meo.privilege.user.get--------end-------");
        return list;
    }
    
    @RequestMapping(params = "method=meo.password.user.system.reset", method = RequestMethod.GET)
    public void resetSystmeUserPwd(@RequestParam Long userId)
    {
        LOG.debug("-------------meo.password.user.system.reset--------begin-------");
        systemPserviceBO.resetSystmeUserPwd(userId);
        LOG.debug("-------------meo.password.user.system.reset--------end-------");
    }
    
    @RequestMapping(params = "method=meo.privilege.operate.delete", method = RequestMethod.GET)
    public void deletePrivlegeOperate(@RequestParam Long privilegeId, @RequestParam Long operateId)
    {
        LOG.debug("-------------meo.privilege.operate.delete--------begin-------");
        systemPserviceBO.deletePrivlegeOperate(privilegeId, operateId);
        LOG.debug("-------------meo.privilege.operate.delete--------end-------");
    }
    
    @RequestMapping(params = "method=meo.privilege.menu.delete", method = RequestMethod.GET)
    public void deletePrivlegeMenu(@RequestParam Long privilegeId, @RequestParam Long menuId)
    {
        LOG.debug("-------------meo.privilege.menu.delete--------begin-------");
        systemPserviceBO.deletePrivlegeMenu(privilegeId, menuId);
        LOG.debug("-------------meo.privilege.menu.delete--------end-------");
    }
    
    @RequestMapping(params = "method=meo.operation.modify", method = {RequestMethod.POST })
    public Map<String, Long> updateOperation(@RequestBody @Validated(value = Update.class) UIUserOperaterVO uiUserOperatorVO)
    {
        LOG.debug("-------------meo.operation.modify--------begin-------");
        final Map<String, Long> map = new HashMap<String, Long>();
        OperaterVO operatorVO = passportComponent.transferUIUserOperatorToOperatorVO(uiUserOperatorVO);
        systemPserviceBO.updateOperator(operatorVO);
        map.put("operatorId", operatorVO.getOperaterId());
        LOG.debug("-------------meo.operation.modify--------end-------");
        return map;
    }
    
}
