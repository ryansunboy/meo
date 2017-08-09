package com.asiainfo.meo.system.authority.app.bo.impl;

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
import com.asiainfo.meo.system.authority.app.bo.StaffInfoBO;
import com.asiainfo.meo.system.authority.app.model.entity.StaffInfo;
import com.asiainfo.meo.system.authority.app.model.entity.SysRole;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserVO;
import com.asiainfo.meo.system.authority.app.repository.AuthorityRepository;
import com.asiainfo.meo.system.authority.app.repository.StaffInfoRepository;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class StaffInfoBOImpl implements StaffInfoBO
{
    private static final Log    LOG = LogFactory.getLog(StaffInfoBOImpl.class);
    
    @Resource
    StaffInfoRepository         staffInfoRepository;
    
    @Resource
    private AuthorityRepository authorityRepository;
    
    public SystemUserVO saveSystemUser(SystemUserVO systemUser)
    {
        StaffInfo staff = transferSystemUserToStaffInfo(systemUser);
        // 把systemUserVO的值拷贝到中staffInfo
        staff = staffInfoRepository.saveStaffInfo(staff);
        if (ValidateUtil.isNull(staff)|| ValidateUtil.isNull(staff.getStaffId()))
        {
            LOG.debug("save systemUser failure");
            throw new MeoException(SysErrorCodeDefine.CREATE_SYSTEMUSER_FAILURE);
        }
        // 创建角色和用户之间的关系
        createSysRoleUser(systemUser, staff);
        SystemUserVO systemUserVO = transferStaffInfoToSystemUser(staff);
        return systemUserVO;
    }
    
    // 创建角色和用户之间的关系
    private void createSysRoleUser(SystemUserVO systemUser, StaffInfo staffInfo)
    {
        List<Long> roleIds = systemUser.getRoleIds();
        for (Long roleId : roleIds)
        {
            SysRole role = authorityRepository.getSysRoleById(roleId);
            if (ValidateUtil.isNull(role))
            {
                LOG.debug("can not found the role,roleId is "+ roleId);
                throw new MeoException(SysErrorCodeDefine.ROLE_DOES_NOT_EXIST, new Object[]{roleId });
            }
            if (role.getSts().equals(SysRole.STS_INVALID))
            {
                LOG.debug("the sysRole's sts is inValid,roleId is "+ roleId);
                throw new MeoException(SysErrorCodeDefine.ROLE_STS_INVALID, new Object[]{roleId });
            }
        }
        for (Long roleId : roleIds)
        {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setCreateDate(DateTimeUtil.getNow());
            sysRoleUser.setUserId(staffInfo.getStaffId());
            sysRoleUser.setSts(SysRoleUser.STS_VALID);
            authorityRepository.addSysRoleUser(sysRoleUser);
        }
    }
    
    private StaffInfo transferSystemUserToStaffInfo(SystemUserVO systemUser)
    {
        StaffInfo staff = new StaffInfo();
        if (ValidateUtil.isNotNull(systemUser.getUserId()))
        {
            staff = staffInfoRepository.getStaffInfo(systemUser.getUserId());
            if (ValidateUtil.isNull(staff))
            {
                LOG.debug("not found systemUser,the userId is "+ systemUser.getUserId());
                throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_IS_NOT_FOUND, new Object[]{systemUser.getUserId() });
            }
            if (staff.getSts().equals(StaffInfo.STS_INVALID))
            {
                LOG.debug("the systemUser's sts is inValid,the userId is "+ systemUser.getUserId());
                throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_STS_INVALID, new Object[]{systemUser.getUserId() });
            }
        }
        staff.setAvatarUrl(systemUser.getAvatarUrl());
        staff.setDepartment(systemUser.getDepartment());
        staff.setEmail(systemUser.getEmail());
        staff.setGender(systemUser.getGenderId());
        staff.setStaffName(systemUser.getUserName());
        staff.setStaffType(systemUser.getStaffType());
        staff.setSts(StaffInfo.STS_VALID);
        return staff;
    }
    
    public SystemUserVO getSystemUser(Long userId)
    {
        StaffInfo staffInfo = staffInfoRepository.getStaffInfo(userId);
        if (ValidateUtil.isNull(staffInfo)|| ValidateUtil.isNull(staffInfo.getStaffId()))
        {
            LOG.debug("not found systemUser,the userId is "+ userId);
            throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_IS_NOT_FOUND, new Object[]{userId });
        }
        SystemUserVO systemUserVO = transferStaffInfoToSystemUser(staffInfo);
        // 把staffInfo的值拷贝到systemUserVO中
        return systemUserVO;
    }
    
    @Override
    public SystemUserVO updateSystemUser(SystemUserVO systemUser)
    {
        StaffInfo staff = transferSystemUserToStaffInfo(systemUser);
        // 把systemUserVO的值拷贝到中staffInfo
        staff = staffInfoRepository.updateStaffInfo(staff);
        // 修改角色和用户之间的关系
        updateSysRoleUserByUserId(systemUser);
        SystemUserVO systemUserVO = transferStaffInfoToSystemUser(staff);
        // 把staffInfo的值拷贝到systemUserVO中
        return systemUserVO;
    }
    
    // 修改角色和用户之间的关系
    private void updateSysRoleUserByUserId(SystemUserVO systemUser)
    {
        List<SysRoleUser> sysRoleUsers = authorityRepository.getSysRoleUserByUserId(systemUser.getUserId());
        List<Long> roleIds = systemUser.getRoleIds();
        if (ValidateUtil.isEmpty(sysRoleUsers))
        {
            LOG.debug("not found sysRoleUser ,the userId is "+ systemUser.getUserId());
            throw new MeoException(SysErrorCodeDefine.SYSROLEUSER_NOT_FOUND, new Object[]{systemUser.getUserId() });
        }
        for (Long roleId : roleIds)
        {
            SysRole role = authorityRepository.getSysRoleById(roleId);
            if (ValidateUtil.isNull(role))
            {
                LOG.debug("can not found the role,roleId is "+ roleId);
                throw new MeoException(SysErrorCodeDefine.ROLE_DOES_NOT_EXIST, new Object[]{roleId });
            }
            if (role.getSts().equals(SysRole.STS_INVALID))
            {
                LOG.debug("the sysRole's sts is inValid,roleId is "+ roleId);
                throw new MeoException(SysErrorCodeDefine.ROLE_STS_INVALID, new Object[]{roleId });
            }
        }
        
        List<Long> oldRoleIds = new ArrayList<Long>();
        for (SysRoleUser roleUser : sysRoleUsers)
        {
            oldRoleIds.add(roleUser.getRoleId());
            if (roleIds.contains(roleUser.getRoleId()))
            {
                // 修改
                roleUser.setModifyDate(DateTimeUtil.getNow());
                roleUser.setSts(SysRoleUser.STS_VALID);
            }
            else
            {
                // 设置为失效
                roleUser.setSts(SysRoleUser.STS_INVALID);
            }
            authorityRepository.updateSysRoleUser(roleUser);
        }
        
        for (Long roleId : roleIds)
        {
            // 新增
            if (!oldRoleIds.contains(roleId))
            {
                SysRoleUser roleUser = new SysRoleUser();
                roleUser.setCreateDate(DateTimeUtil.getNow());
                roleUser.setRoleId(roleId);
                roleUser.setSts(SysRoleUser.STS_VALID);
                roleUser.setUserId(systemUser.getUserId());
                authorityRepository.addSysRoleUser(roleUser);
            }
        }
    }
    
    @Override
    public void deleteSystemUserByuserId(Long userId)
    {
        StaffInfo staffInfo = staffInfoRepository.getStaffInfo(userId);
        if (ValidateUtil.isNull(staffInfo)|| ValidateUtil.isNull(staffInfo.getStaffId()))
        {
            LOG.debug("not found systemUser,the userId is "+ userId);
            throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_IS_NOT_FOUND, new Object[]{userId });
        }
        staffInfo.setSts(StaffInfo.STS_INVALID);
        
        // 把用户和角色关系设置为无效
        List<SysRoleUser> sysRoleUsers = authorityRepository.getSysRoleUserByUserId(userId);
        if (ValidateUtil.isNotEmpty(sysRoleUsers))
        {
            for (SysRoleUser roleUser : sysRoleUsers)
            {
                if (roleUser.getSts().equals(SysRoleUser.STS_VALID))
                {
                    roleUser.setSts(SysRoleUser.STS_INVALID);
                    authorityRepository.updateSysRoleUser(roleUser);
                }
            }
        }
        staffInfoRepository.updateStaffInfo(staffInfo);
    }
    
    @Override
    public PageInfo<SystemUserVO> getSystemUserVOPageInfo(SystemUserQueryConditionVO systemUserQueryCodition)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        Set<Long> queryUserIds = new HashSet<Long>();
        if (ValidateUtil.isNotNull(systemUserQueryCodition))
        {
            pageSize = ValidateUtil.checkPageSize(systemUserQueryCodition.getPageSize());
            pageNo = ValidateUtil.checkPageNo(systemUserQueryCodition.getPageNo());
            if (ValidateUtil.isNotNull(systemUserQueryCodition.getRoleId()))
            {
                List<SysRoleUser> roleUserList = authorityRepository.getSysRoleUserByRoleId(systemUserQueryCodition.getRoleId());
                if (ValidateUtil.isNotEmpty(roleUserList))
                {
                    for (int i = 0; i< roleUserList.size(); i++ )
                    {
                        SysRoleUser roleUser = roleUserList.get(i);
                        if (SysRoleUser.STS_VALID.equals(roleUser.getSts()))
                        {
                            queryUserIds.add(roleUser.getUserId());
                        }
                    }
                }
            }
        }
        
        
        
        
        PageInfo<SystemUserVO> pageInfo = new PageInfo<SystemUserVO>();
        Integer totalRowSize = staffInfoRepository.getStaffInfoListTotalSize(systemUserQueryCodition, queryUserIds);
        if (ValidateUtil.isNull(totalRowSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<StaffInfo> staffInfoList = staffInfoRepository.getStaffInfoList(systemUserQueryCodition, queryUserIds, pageSize,
                pageNo);
        List<SystemUserVO> systemUserList = new ArrayList<SystemUserVO>();
        if (ValidateUtil.isEmpty(staffInfoList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        for (StaffInfo staffInfo : staffInfoList)
        {
            SystemUserVO systemUserVO = transferStaffInfoToSystemUser(staffInfo);
            // 把staffInfo的值拷贝到systemUser中
            systemUserList.add(systemUserVO);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalRowSize, systemUserList);
    }
    
    private SystemUserVO transferStaffInfoToSystemUser(StaffInfo staffInfo)
    {
        SystemUserVO systemUserVO = new SystemUserVO();
        BeanUtils.copyProperties(staffInfo, systemUserVO);
        systemUserVO.setUserName(staffInfo.getStaffName());
        systemUserVO.setUserId(staffInfo.getStaffId());
        systemUserVO.setGenderId(staffInfo.getGender());
        systemUserVO.setSts(staffInfo.getSts());
        List<SysRoleUser> roleUsers = authorityRepository.getSysRoleUserByUserId(staffInfo.getStaffId());
        List<Long> roleIds = new ArrayList<Long>();
        List<String> roleNames = new ArrayList<String>();
        if (ValidateUtil.isNotEmpty(roleUsers))
        {
            for (SysRoleUser roleUser : roleUsers)
            {
                if (roleUser.getSts().equals(SysRoleUser.STS_VALID))
                    roleIds.add(roleUser.getRoleId());
            }
        }
        if (ValidateUtil.isNotEmpty(roleIds))
        {
            systemUserVO.setRoleIds(roleIds);
            List<SysRole> roles = authorityRepository.getSysRoleByIds(roleIds);
            if (ValidateUtil.isNotEmpty(roles))
            {
                for (SysRole role : roles)
                {
                    roleNames.add(role.getRoleName());
                }
                systemUserVO.setRoleNames(roleNames);
            }
        }
        return systemUserVO;
    }
    
    @Override
    public List<SysRoleUser> getSysRoleUserByRoleId(Long roleId)
    {
        return staffInfoRepository.getSysRoleUserByRoleId(roleId);
    }
}
