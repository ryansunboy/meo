package com.asiainfo.meo.prm.profile.app.repository.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.prm.profile.app.model.entity.Staff;
import com.asiainfo.meo.prm.profile.app.repository.StaffRepository;

public class StaffRepositoryImpl implements StaffRepository
{
    
    private static final String SEQ_STAFF_ID = "SEQ_STAFF_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;

    @Override
    public void updateStaff(Staff staff)
    {
        hibernateRepository.updateObject(staff);
    }

    @Override
    public Staff getStaffById(Long staffId)
    {
        return hibernateRepository.get(Staff.class, staffId);
    }
    
}
