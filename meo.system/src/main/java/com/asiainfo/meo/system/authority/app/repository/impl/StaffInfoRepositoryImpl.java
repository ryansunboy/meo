package com.asiainfo.meo.system.authority.app.repository.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.authority.app.model.entity.StaffInfo;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;
import com.asiainfo.meo.system.authority.app.repository.StaffInfoRepository;

public class StaffInfoRepositoryImpl implements StaffInfoRepository
{
    private static final String SEQ_STAFF_INFO_ID = "SEQ_STAFF_INFO_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    @Override
    public StaffInfo getStaffInfo(Long staffId)
    {
        return hibernateRepository.get(StaffInfo.class, staffId);
    }
    
    @Override
    public StaffInfo saveStaffInfo(StaffInfo staffInfo)
    {
        Long staffId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_STAFF_INFO_ID);
        staffInfo.setStaffId(staffId);
        staffInfo.setStaffNo(staffId);
        staffInfo.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(staffInfo);
        return staffInfo;
    }
    
    @Override
    public StaffInfo updateStaffInfo(StaffInfo staff)
    {
        staff.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(staff);
        return getStaffInfo(staff.getStaffId());
    }
    
    @Override
    public void deleteStaffInfoByStaffId(Long staffId)
    {
        StaffInfo staff = getStaffInfo(staffId);
        hibernateRepository.deleteObject(staff);
    }
    
    @Override
    public List<StaffInfo> getStaffInfoList(SystemUserQueryConditionVO systemUserQueryCodition,Set<Long> queryUserIds,int pageSize,int pageNo)
    {   
        DetachedCriteria criteria =  createStaffInfoListDetachedCriteria(systemUserQueryCodition,queryUserIds);
        return (List<StaffInfo>)hibernateRepository.findByCriteria(criteria, (pageNo- 1)* pageSize, pageSize);
    }
    
    private DetachedCriteria createStaffInfoListDetachedCriteria(SystemUserQueryConditionVO systemUserQueryCodition,Set<Long> queryUserIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(StaffInfo.class);
        criteria.add(Restrictions.eq("sts", StaffInfo.STS_VALID));
        if (ValidateUtil.isNull(systemUserQueryCodition))
        {
            return criteria;
        }
        if (ValidateUtil.isNotEmpty(systemUserQueryCodition.getUserName()))
        {
            criteria.add(Restrictions.like("staffName", "%"+ systemUserQueryCodition.getUserName()+ "%"));
        }
        if (ValidateUtil.isNotNull(systemUserQueryCodition.getUserId()))
        {
            criteria.add(Restrictions.eq("staffId", systemUserQueryCodition.getUserId()));
        }
        if (ValidateUtil.isNotEmpty(systemUserQueryCodition.getEmail()))
        {
            criteria.add(Restrictions.like("email", "%"+ systemUserQueryCodition.getEmail()+ "%"));
        }
        if(ValidateUtil.isNotEmpty(queryUserIds))
        {
            criteria.add(Restrictions.in("staffId", queryUserIds));
        }
        return criteria;
    }
    
    @Override
    public int getStaffInfoListTotalSize(SystemUserQueryConditionVO systemUserQueryCodition,Set<Long> queryUserIds)
    {
        DetachedCriteria criteria = createStaffInfoListDetachedCriteria(systemUserQueryCodition,queryUserIds);
        List<StaffInfo> list = (List<StaffInfo>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    @Override
    public List<SysRoleUser> getSysRoleUserByUserId(Long userId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRoleUser.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("sts", SysRoleUser.STS_VALID));
        return (List<SysRoleUser>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public List<SysRoleUser> getSysRoleUserByRoleId(Long roleId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRoleUser.class);
        criteria.add(Restrictions.eq("roleId", roleId));
        criteria.add(Restrictions.eq("sts", SysRoleUser.STS_VALID));
        return (List<SysRoleUser>) hibernateRepository.findByCriteria(criteria);
    }
}
