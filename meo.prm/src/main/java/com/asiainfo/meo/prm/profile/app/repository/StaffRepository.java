package com.asiainfo.meo.prm.profile.app.repository;

import com.asiainfo.meo.prm.profile.app.model.entity.Staff;

public interface StaffRepository
{
    public void updateStaff(Staff staff);
    
    public Staff getStaffById(Long staffId);
}
