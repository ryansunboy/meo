package com.asiainfo.meo.system.authority.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.authority.app.bo.AuthorityBO;
import com.asiainfo.meo.system.authority.app.model.entity.StaffInfo;
import com.asiainfo.meo.system.authority.app.model.entity.SysMenu;
import com.asiainfo.meo.system.authority.app.model.entity.SysOperater;
import com.asiainfo.meo.system.authority.app.model.entity.SysPrivilege;
import com.asiainfo.meo.system.authority.app.model.entity.SysPrivilegeMenu;
import com.asiainfo.meo.system.authority.app.model.entity.SysPrivilegeOperater;
import com.asiainfo.meo.system.authority.app.model.entity.SysRole;
import com.asiainfo.meo.system.authority.app.model.entity.SysRolePrivilege;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.MPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.MenuPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.MenuQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.MenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.OPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperatorQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.PrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserMenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserOperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleVO;
import com.asiainfo.meo.system.authority.app.repository.AuthorityRepository;
import com.asiainfo.meo.system.authority.app.repository.StaffInfoRepository;
import com.asiainfo.meo.system.authority.app.repository.SysRoleRepository;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;

public class AuthorityBOImpl implements AuthorityBO
{
    private static final Log    LOG = LogFactory.getLog(AuthorityBOImpl.class);
    
    @Resource
    private AuthorityRepository authorityRepository;
    
    @Resource
    private SysRoleRepository   sysRoleRepository;
    
    @Resource
    private StaffInfoRepository staffInfoRepository;
    
    @Resource
    private SystemPserviceBO sysPserviceBo;
    
    public PageInfo<UserRoleVO> getRoleInfoList(final UserRoleQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
            
        }
        final PageInfo<UserRoleVO> pageInfo = new PageInfo<UserRoleVO>();
        final List<UserRoleVO> userRoleVOList = new ArrayList<UserRoleVO>();
        Integer totalSize = sysRoleRepository.getRoleInfoTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<SysRole> sysRoleList = sysRoleRepository.getRoleInfoList(conditionVO, pageSize, pageNo);        
        for (SysRole sysRole : sysRoleList)
        {
            UserRoleVO userRoleVO = new UserRoleVO();
            BeanUtils.copyProperties(sysRole, userRoleVO);
            if (ValidateUtil.isNotNull(sysRole.getCreateDate()))
            {
                userRoleVO.setCreateDate(sysRole.getCreateDate().getTime());
            }
            userRoleVOList.add(userRoleVO);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, userRoleVOList);
    }
    
    
    public PageInfo<UserMenuVO> getMenuList(final MenuQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
            
        }
        final PageInfo<UserMenuVO> pageInfo = new PageInfo<UserMenuVO>();
        Integer totalSize = authorityRepository.getMenuListTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        
        final List<UserMenuVO> userMenuVOList = new ArrayList<UserMenuVO>();
        List<SysMenu> sysMenuList = authorityRepository.getMenuList(conditionVO, pageSize, pageNo);
        for (SysMenu sysMenu : sysMenuList)
        {
            final UserMenuVO userMenuVO = new UserMenuVO();
            userMenuVO.setMenuId(sysMenu.getMenuId());
            userMenuVO.setMenuName(sysMenu.getName());
            userMenuVO.setParentMenuId(sysMenu.getParentId());
            List<EnumDefine> enumDefineList = sysPserviceBo.getEnumByCode(sysMenu.getSystemId() + "", EnumDefine.SYSTEM_TYPE);
            for (EnumDefine EnumDefine : enumDefineList)
            {
                userMenuVO.setSystemId(sysMenu.getSystemId());
                userMenuVO.setSystemValue(EnumDefine.getEnumName());
            }
            List<EnumDefine> enumDefineList1 = sysPserviceBo.getEnumByCode(sysMenu.getModuleId() + "", EnumDefine.MODULE_TYPE);
            for (EnumDefine EnumDefine : enumDefineList1)
            {
                userMenuVO.setModuleId(sysMenu.getModuleId());
                userMenuVO.setModuleValue(EnumDefine.getEnumName());
            }
            userMenuVO.setUrl(sysMenu.getUrl());
            userMenuVOList.add(userMenuVO);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, userMenuVOList);
    }
    
    @Override
    public Long saveMenu(UserMenuVO userMenuVO)
    {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setIconUrl(userMenuVO.getImagUrl());
        sysMenu.setName(userMenuVO.getMenuName());
        sysMenu.setModuleId(userMenuVO.getModuleId());
        sysMenu.setParentId(userMenuVO.getParentMenuId());
        sysMenu.setSystemId(userMenuVO.getSystemId());
        sysMenu.setMenuType(userMenuVO.getMenuType());
        sysMenu.setUrl(userMenuVO.getUrl());
        sysMenu.setCreateDate(DateTimeUtil.getNow());
        sysMenu.setSts(SysMenu.STS_VALID);
        Long menuId = authorityRepository.saveMenu(sysMenu);
        return menuId;
    }
    
    @Override
    public void modifyMenu(UserMenuVO userMenuVO)
    {
        SysMenu sysMenu = authorityRepository.getSysMenuById(userMenuVO.getMenuId());
        if (ValidateUtil.isEmpty(sysMenu))
        {
            throw new MeoException(SysErrorCodeDefine.MENU_DOES_NOT_EXIST);
        }
        sysMenu.setName(userMenuVO.getMenuName());
        sysMenu.setModuleId(userMenuVO.getModuleId());
        sysMenu.setParentId(userMenuVO.getParentMenuId());
        sysMenu.setSystemId(userMenuVO.getSystemId());
        sysMenu.setUrl(userMenuVO.getUrl());
        sysMenu.setModifyDate(DateTimeUtil.getNow());
        authorityRepository.modifyMenu(sysMenu);
    }
    
    @Override
    public void deleteMenu(Long menuId)
    {
        SysMenu sysMenu = authorityRepository.getSysMenuById(menuId);
        if (ValidateUtil.isEmpty(sysMenu))
        {
            throw new MeoException(SysErrorCodeDefine.MENU_DOES_NOT_EXIST);
        }
        sysMenu.setSts(SysMenu.STS_INVALID);
        sysMenu.setModifyDate(DateTimeUtil.getNow());
        authorityRepository.modifyMenu(sysMenu);
    }
    
    @Override
    public PageInfo<UserOperaterVO> getOperationList(final OperatorQueryConditionVO conditionsVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        
        if (ValidateUtil.isNotNull(conditionsVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionsVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionsVO.getPageNo());
        }
        final PageInfo<UserOperaterVO> pageInfo = new PageInfo<UserOperaterVO>();
        final Integer totalSize = authorityRepository.getOperationListTotalSize(conditionsVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        
        final List<UserOperaterVO> userOperaterVOList = new ArrayList<UserOperaterVO>();
        final List<SysOperater> sysOperaterList = authorityRepository.getOperationList(pageNo, pageSize, conditionsVO);
        for (SysOperater sysOperater : sysOperaterList)
        {
            final UserOperaterVO userOperaterVO = new UserOperaterVO();
            userOperaterVO.setOperId(sysOperater.getOperId());
            userOperaterVO.setOperName(sysOperater.getOperName());
            userOperaterVOList.add(userOperaterVO);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, userOperaterVOList);
    }
    
    @Override
    public Long saveOperation(String operName)
    {
        SysOperater sysOperater = new SysOperater();
        sysOperater.setOperName(operName);
        sysOperater.setCreateDate(DateTimeUtil.getNow());
        sysOperater.setSts(SysMenu.STS_VALID);
        Long operaterId = authorityRepository.saveOperation(sysOperater);
        return operaterId;
    }
    
    @Override
    public void deleteOperation(Long operId)
    {
        SysOperater sysOperater = authorityRepository.getOperation(operId);
        sysOperater.setModifyDate(DateTimeUtil.getNow());
        sysOperater.setSts(SysMenu.STS_INVALID);
        authorityRepository.modifyOperation(sysOperater);
    }
    
    @Override
    public void saveRolePrivilege(RolePrivilegeVO rolePrivilegeVO)
    {
        
        Long menuId = null;
        Long privId = null;
        Long operId = null;
        // get role id
        UserRoleVO userRoleVO = rolePrivilegeVO.getRole();
        SysRole sysRole = new SysRole();
        sysRole.setCreateDate(new Timestamp(userRoleVO.getCreateDate()));
        sysRole.setRoleName(userRoleVO.getRoleName());
        Long roleId = authorityRepository.saveSysRole(sysRole);
        List<MenuPrivilegeVO> menuPrivilegeVOList = rolePrivilegeVO.getMenuPrivileges();
        for (MenuPrivilegeVO menuPrivilegeVO : menuPrivilegeVOList)
        {
            
            MenuVO menuVO = menuPrivilegeVO.getMenu();
            menuId = menuVO.getMenuId();
            SysMenu sysMenu = authorityRepository.getSysMenuById(menuId);
            if (ValidateUtil.isEmpty(sysMenu))
            {
                throw new MeoException(SysErrorCodeDefine.MENU_DOES_NOT_EXIST);
            }
            List<OperPrivilegeVO> operPrivilegeVOList = menuPrivilegeVO.getOperPrivileges();
            for (OperPrivilegeVO operPrivilegeVO : operPrivilegeVOList)
            {
                PrivilegeVO privilegeVO = operPrivilegeVO.getPrivilege();
                
                SysPrivilege sysPrivilege = new SysPrivilege();
                sysPrivilege.setSts(SysPrivilege.STS_VALID);
                sysPrivilege.setCreateDate(DateTimeUtil.getNow());
                sysPrivilege.setPrivName(privilegeVO.getPrivilegeName());
                sysPrivilege.setPrivCode(privilegeVO.getPrivilegeCode());
                privId = authorityRepository.savePrivilege(sysPrivilege);
                
                OperaterVO operaterVO = operPrivilegeVO.getOperater();
                operId = operaterVO.getOperaterId();
                SysOperater sysOperater = authorityRepository.getOperation(operId);
                if (ValidateUtil.isEmpty(sysOperater))
                {
                    throw new MeoException(SysErrorCodeDefine.OPERATER_DOES_NOT_EXIST, new Object[]{operId });
                }
                
                SysRolePrivilege sysRolePrivilege = new SysRolePrivilege();
                sysRolePrivilege.setCreateDate(DateTimeUtil.getNow());
                sysRolePrivilege.setSts(SysRolePrivilege.STS_VALID);
                sysRolePrivilege.setRoleId(roleId);
                sysRolePrivilege.setPrivId(privId);
                authorityRepository.saveRolePrivilege(sysRolePrivilege);
                
                SysPrivilegeMenu sysPrivilegeMenu = new SysPrivilegeMenu();
                sysPrivilegeMenu.setCreateDate(DateTimeUtil.getNow());
                sysPrivilegeMenu.setSts(SysPrivilegeMenu.STS_VALID);
                sysPrivilegeMenu.setMenuId(menuId);
                sysPrivilegeMenu.setPrivId(privId);
                authorityRepository.savePrivilegeMenu(sysPrivilegeMenu);
                
                SysPrivilegeOperater sysPrivilegeOperater = new SysPrivilegeOperater();
                sysPrivilegeOperater.setSts(SysPrivilegeOperater.STS_VALID);
                sysPrivilegeOperater.setCreateDate(DateTimeUtil.getNow());
                sysPrivilegeOperater.setPrivId(privId);
                sysPrivilegeOperater.setOperId(operId);
                authorityRepository.savePrivilegeOperater(sysPrivilegeOperater);
                
            }
        }
    }
    
    @Override
    public void deleteRolePrivilege(Long roleId)
    {
        List<SysRolePrivilege> sysRolePrivilegeList = authorityRepository.getRolePrivilegeByRoleId(roleId);
        if (sysRolePrivilegeList.size()<= 0)
        {
            throw new MeoException(SysErrorCodeDefine.ROLE_DOES_NOT_EXIST, new Object[]{roleId });
        }
        for (SysRolePrivilege sysRolePrivilege : sysRolePrivilegeList)
        {
            sysRolePrivilege.setModifyDate(DateTimeUtil.getNow());
            sysRolePrivilege.setSts(SysRolePrivilege.STS_INVALID);
            authorityRepository.modifySysRolePrivilege(sysRolePrivilege);
        }
    }
    
    @Override
    public List<UPrivilegeVO> getUPrivilegeListByUserId(Long userId)
    {
        List<UPrivilegeVO> uPrivilegeList = new ArrayList<UPrivilegeVO>();
        StaffInfo staffInfo = staffInfoRepository.getStaffInfo(userId);
        if (ValidateUtil.isNull(staffInfo))
        {
            LOG.debug("the method getUPrivilegeListByUserId ,not found systemUser by userId ");
            throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_IS_NOT_FOUND, new Object[]{userId });
        }
        if (staffInfo.getSts().equals(StaffInfo.STS_INVALID))
        {
            LOG.debug("the systemUser's sts is inValid,the userId is "+ userId);
            throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_STS_INVALID, new Object[]{userId });
        }
        List<SysRoleUser> roleUserList = staffInfoRepository.getSysRoleUserByUserId(userId);
        if (ValidateUtil.isEmpty(roleUserList))
        {
            return uPrivilegeList;
        }
        List<Long> roleIds = new ArrayList<Long>();
        for (SysRoleUser roleUser : roleUserList)
        {
            roleIds.add(roleUser.getRoleId());
        }
        List<SysRolePrivilege> sysRolePrivilegeList = authorityRepository.getRolePrivilegeByRoleIds(roleIds);
        if (ValidateUtil.isEmpty(sysRolePrivilegeList))
        {
            return uPrivilegeList;
        }
        List<SysRole> roles = authorityRepository.getSysRoleByIds(roleIds);
        Set<String> roleNames = new HashSet<String>();
        if (ValidateUtil.isEmpty(roles))
        {
            return uPrivilegeList;
        }
        for(SysRole role:roles)
        {
        	roleNames.add(role.getRoleName());
        }
        List<Long> privIds = new ArrayList<Long>();
        for (SysRolePrivilege rolePrivilege : sysRolePrivilegeList)
        {
            privIds.add(rolePrivilege.getPrivId());
        }
        List<SysPrivilegeMenu> sysPrivilegeMenuList = authorityRepository.getSysPrivilegeMenuListByPrivIds(privIds);
        if (ValidateUtil.isEmpty(sysPrivilegeMenuList))
        {
            return uPrivilegeList;
        }
        
        Set<Long> menuIds = new HashSet<Long>();
        for (SysPrivilegeMenu privilegeMenu : sysPrivilegeMenuList)
        {
            menuIds.add(privilegeMenu.getMenuId());
        }
        if (ValidateUtil.isEmpty(menuIds))
        {
            return uPrivilegeList;
        }
        List<MPrivilegeVO> menuPrivileges = new ArrayList<MPrivilegeVO>();
        
        for (Long menuId : menuIds)
        {
            MPrivilegeVO mPrivilege = new MPrivilegeVO();
            MenuVO menuVO = createMenuVOByMenuId(menuId);
            mPrivilege.setMenu(menuVO);
            menuPrivileges.add(mPrivilege);
        }
        
        for (MPrivilegeVO mp : menuPrivileges)
        {
            List<OPrivilegeVO> oPrivilegeList = new ArrayList<OPrivilegeVO>();
            List<SysPrivilegeOperater> privilegeOperaterList = new ArrayList<SysPrivilegeOperater>();
            for (SysPrivilegeMenu privilegeMenu : sysPrivilegeMenuList)
            {
                if (privilegeMenu.getMenuId().equals(mp.getMenu().getMenuId()))
                {
                    List<SysPrivilegeOperater> privilegeOperaterListTemp = authorityRepository
                            .getSysPrivilegeOperaterListByPrivId(privilegeMenu.getPrivId());
                    if (ValidateUtil.isNotEmpty(privilegeOperaterListTemp))
                    {
                        privilegeOperaterList.addAll(privilegeOperaterListTemp);
                    }
                }
            }
            
            if (ValidateUtil.isNotEmpty(privilegeOperaterList))
            {
                for (SysPrivilegeOperater privilegeOperater : privilegeOperaterList)
                {
                    OPrivilegeVO oPrivilege = new OPrivilegeVO();
                    oPrivilege.setPrivilegeId(privilegeOperater.getPrivId());
                    oPrivilege.setOperId(privilegeOperater.getOperId());
                    SysOperater operater = authorityRepository.getOperation(privilegeOperater.getOperId());
                    if (ValidateUtil.isNotNull(operater))
                    {
                        oPrivilege.setOperName(operater.getOperName());
                    }
                    oPrivilegeList.add(oPrivilege);
                }
            }
            mp.setOperPrivileges(oPrivilegeList);
        }
        UPrivilegeVO uPrivilege = new UPrivilegeVO();
        uPrivilege.setRoleNames(roleNames);
        uPrivilege.setUserName(staffInfo.getStaffName());
        uPrivilege.setMenuPrivileges(menuPrivileges);
        uPrivilegeList.add(uPrivilege);
        return uPrivilegeList;
    }
    
    private MenuVO createMenuVOByMenuId(Long menuId)
    {
        SysMenu sysMenu = authorityRepository.getSysMenuById(menuId);
        MenuVO menuVO = new MenuVO();
        if (ValidateUtil.isNotNull(sysMenu))
        {
            menuVO.setMenuId(menuId);
            menuVO.setMenuName(sysMenu.getName());
            menuVO.setModuleId(sysMenu.getModuleId());
            menuVO.setParentMenuId(sysMenu.getParentId());
            menuVO.setSystemId(sysMenu.getSystemId());
        }
        return menuVO;
    }
    
    @Override
    public UserRoleVO addRole(UserRoleVO userRole)
    {
        SysRole sysRole = transformUserRoleVOToUserRole(userRole);
        sysRole = sysRoleRepository.saveRole(sysRole);
        if (ValidateUtil.isNull(sysRole)|| ValidateUtil.isNull(sysRole.getRoleId()))
        {
            LOG.debug("add role failure");
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{SysConstantDefine.ROLE });
        }
        UserRoleVO userRoleVo = new UserRoleVO();
        transformUserRoleToUserRoleVO(sysRole, userRoleVo);
        return userRoleVo;
    }
    
    @Override
    public UserRoleVO getRole(Long roleId)
    {
        SysRole role = sysRoleRepository.getSysRoleByRoleId(roleId);
        UserRoleVO userRoleVo = new UserRoleVO();
        if (ValidateUtil.isNull(role))
        {
            return null;
        }
        transformUserRoleToUserRoleVO(role, userRoleVo);
        return userRoleVo;
    }
    
    private void transformUserRoleToUserRoleVO(SysRole role, UserRoleVO userRoleVo)
    {
        userRoleVo.setCreateDate(role.getCreateDate().getTime());
        userRoleVo.setRoleId(role.getRoleId());
        userRoleVo.setRoleName(role.getRoleName());
    }
    
    private SysRole transformUserRoleVOToUserRole(UserRoleVO userRole)
    {
        SysRole sysRole = new SysRole();
        if (ValidateUtil.isNotNull(userRole.getRoleId()))
        {
            sysRole = sysRoleRepository.getSysRoleByRoleId(userRole.getRoleId());
            if (ValidateUtil.isNull(sysRole))
            {
                LOG.debug("can not found the role,roleId is "+ userRole.getRoleId());
                throw new MeoException(SysErrorCodeDefine.ROLE_DOES_NOT_EXIST, new Object[]{userRole.getRoleId() });
            }
            if (sysRole.getSts().equals(SysRole.STS_INVALID))
            {
                LOG.debug("the sysRole's sts is inValid,roleId is "+ userRole.getRoleId());
                throw new MeoException(SysErrorCodeDefine.ROLE_STS_INVALID, new Object[]{userRole.getRoleId() });
            }
        }
        sysRole.setCreateDate(new Timestamp(userRole.getCreateDate()));
        sysRole.setRoleName(userRole.getRoleName());
        return sysRole;
    }
    
    private SysOperater transformOpereatorVOToSysOperator(final OperaterVO operator)
    {
        SysOperater sysOperator = new SysOperater();
        if (ValidateUtil.isNotNull(operator.getOperaterId()))
        {
            sysOperator = authorityRepository.getOperation(operator.getOperaterId());
            if (ValidateUtil.isNull(sysOperator))
            {
                LOG.debug("can not found the operator,operatorId is "+ operator.getOperaterId());
                throw new MeoException(SysErrorCodeDefine.OPERATER_DOES_NOT_EXIST, new Object[]{operator.getOperaterId() });
            }
            if (SysRole.STS_INVALID.equals(sysOperator.getSts()))
            {
                LOG.debug("the sysOperator's sts is inValid,operatorId is "+ operator.getOperaterId());
                throw new MeoException(SysErrorCodeDefine.OPERATOR_STS_INVALID, new Object[]{operator.getOperaterId() });
            }
        }
        sysOperator.setOperName(operator.getOperatorName());
        sysOperator.setModifyDate(new Timestamp(DateTimeUtil.getCurrentTimeMillis()));
        return sysOperator;
    }
    
    @Override
    public UserRoleVO updateRole(UserRoleVO userRole)
    {
        SysRole sysRole = transformUserRoleVOToUserRole(userRole);
        sysRole = sysRoleRepository.modifyRole(sysRole);
        if (ValidateUtil.isNull(sysRole))
        {
            LOG.debug("update role failure,the roleId is "+ userRole.getRoleId());
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{SysConstantDefine.ROLE });
        }
        UserRoleVO userRoleVo = new UserRoleVO();
        transformUserRoleToUserRoleVO(sysRole, userRoleVo);
        return userRoleVo;
    }
    
    @Override
    public OperaterVO updateOperator(OperaterVO operator)
    {
       SysOperater sysOperator = transformOpereatorVOToSysOperator(operator);
       authorityRepository.modifyOperation(sysOperator);
       return operator;
    }
    
    @Override
    public void deleteRoleByRoleId(Long roleId)
    {
        SysRole sysRole = sysRoleRepository.getSysRoleByRoleId(roleId);
        if (sysRole.getSts().equals(SysRole.STS_INVALID))
        {
            LOG.debug("the sysRole's sts is inValid,roleId is "+ roleId);
            throw new MeoException(SysErrorCodeDefine.ROLE_STS_INVALID, new Object[]{roleId });
        }
        sysRole.setSts(SysRole.STS_INVALID);
        sysRoleRepository.modifyRole(sysRole);
    }
    
    @Override
    public void deletePrivlegeMenu(Long privilegeId, Long menuId)
    {
        SysPrivilegeMenu sysPrivilegeMenu = authorityRepository.getSysPrivilegeMenuById(menuId, privilegeId);
        if (ValidateUtil.isEmpty(sysPrivilegeMenu))
        {
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{"PrivilegeMenu" });
        }
        sysPrivilegeMenu.setSts(SysPrivilegeMenu.STS_INVALID);
        sysPrivilegeMenu.setModifyDate(DateTimeUtil.getNow());
        authorityRepository.modifyPrivilegeMenu(sysPrivilegeMenu);
    }
    
    @Override
    public void deletePrivlegeOperate(Long privilegeId, Long operateId)
    {
        SysPrivilegeOperater sysPrivilegeOperater = authorityRepository.getSysPrivilegeOperaterById(operateId, privilegeId);
        if (ValidateUtil.isEmpty(sysPrivilegeOperater))
        {
            throw new MeoException(SysErrorCodeDefine.ENTITY_IS_EMPTY, new Object[]{"PrivilegeOperater" });
        }
        sysPrivilegeOperater.setSts(SysPrivilegeMenu.STS_INVALID);
        sysPrivilegeOperater.setModifyDate(DateTimeUtil.getNow());
        authorityRepository.modifyPrivilegeOperater(sysPrivilegeOperater);
    }
    
    /**
     * @Description: 查询所有角色的权限
     * @Description: get all role privilege
     * @author zhengzy
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<RPrivilegeVO> getAllRolePrivilegeList(final RolePrivilegeQueryConditionVO conditionVO)
    {        
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
        }
        final PageInfo<RPrivilegeVO> pageInfo = new PageInfo<RPrivilegeVO>();
        List<SysRole> sysRoleList = sysRoleRepository.getRoles(conditionVO);
        if (ValidateUtil.isEmpty(sysRoleList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
                
        List<RPrivilegeVO> rolePrivilegeList = new ArrayList<RPrivilegeVO>();
        
        // 查询出菜单和权限之间的关系
        List<SysPrivilegeMenu> sysPrivilegeMenuList = authorityRepository.getSysPrivilegeMenuList();
        
        for (SysRole sysRole : sysRoleList)
        {
            RPrivilegeVO rolePrivilegeVO = new RPrivilegeVO();
            UserRoleVO userRoleVO = new UserRoleVO();
            BeanUtils.copyProperties(sysRole, userRoleVO);
            if (ValidateUtil.isNotNull(sysRole.getCreateDate()))
            {
                userRoleVO.setCreateDate(sysRole.getCreateDate().getTime());
            }
            rolePrivilegeVO.setRole(userRoleVO);
            
            // 根据角色ID查询到角色和权限之间的关系数据
            List<SysRolePrivilege> sysRolePrivilegeList = authorityRepository.getRolePrivilegeByRoleId(sysRole.getRoleId());
            if (ValidateUtil.isEmpty(sysRolePrivilegeList))
            {
                continue;
            }
            
            Set<Long> menuIdList = new HashSet<Long>();
            
            for (SysPrivilegeMenu sysPrivilegeMenu : sysPrivilegeMenuList)
            {
                Long privId = sysPrivilegeMenu.getPrivId();
                for (SysRolePrivilege sysRolePrivilege : sysRolePrivilegeList)
                {
                    if (privId.equals(sysRolePrivilege.getPrivId()))
                    {
                        menuIdList.add(sysPrivilegeMenu.getMenuId());
                    }
                }
            }
            
            List<MPrivilegeVO> menuPriveligeList = new ArrayList<MPrivilegeVO>();
            
            if (ValidateUtil.isEmpty(menuIdList))
            {
                continue;
            }
            
            for (Long menuId : menuIdList)
            {
                MPrivilegeVO menuPrivilegeVO = new MPrivilegeVO();
                MenuVO menuVO = createMenuVOByMenuId(menuId);
                menuPrivilegeVO.setMenu(menuVO);
                
                List<OPrivilegeVO> oPrivilegeList = new ArrayList<OPrivilegeVO>();
                
                List<SysPrivilegeOperater> privilegeOperaterList = new ArrayList<SysPrivilegeOperater>();
                for (SysPrivilegeMenu privilegeMenu : sysPrivilegeMenuList)
                {
                    if (privilegeMenu.getMenuId().equals(menuId))
                    {
                        List<SysPrivilegeOperater> privilegeOperaterListTemp = authorityRepository
                                .getSysPrivilegeOperaterListByPrivId(privilegeMenu.getPrivId());
                        if (ValidateUtil.isNotEmpty(privilegeOperaterListTemp))
                        {
                            privilegeOperaterList.addAll(privilegeOperaterListTemp);
                        }
                    }
                }
                
                if (ValidateUtil.isNotEmpty(privilegeOperaterList))
                {
                    for (SysPrivilegeOperater privilegeOperater : privilegeOperaterList)
                    {
                        OPrivilegeVO oPrivilege = new OPrivilegeVO();
                        oPrivilege.setPrivilegeId(privilegeOperater.getPrivId());
                        oPrivilege.setOperId(privilegeOperater.getOperId());
                        SysOperater operater = authorityRepository.getOperation(privilegeOperater.getOperId());
                        if (ValidateUtil.isNotNull(operater))
                        {
                            oPrivilege.setOperName(operater.getOperName());
                        }
                        oPrivilegeList.add(oPrivilege);
                    }
                }
                
                menuPrivilegeVO.setOperPrivileges(oPrivilegeList);
                menuPriveligeList.add(menuPrivilegeVO);
            }
            rolePrivilegeVO.setMenuPriveliges(menuPriveligeList);
            rolePrivilegeList.add(rolePrivilegeVO);
        }
        if (rolePrivilegeList.size() == 0)
        {
            pageInfo.emptyPageInfo(pageSize);
        }

        final List<RPrivilegeVO> paginateList = rolePrivilegeList.subList((pageNo - 1) * pageSize,
                (pageSize > rolePrivilegeList.size()) ? rolePrivilegeList.size() : pageSize);
        return pageInfo.createPageInfo(pageNo, pageSize, rolePrivilegeList.size(), paginateList);
    }
    
    @Override
    public List<SysRolePrivilege> getRolePrivilegeByRoleId(Long roleId)
    {
        return authorityRepository.getRolePrivilegeByRoleId(roleId);
    } 
    
}
