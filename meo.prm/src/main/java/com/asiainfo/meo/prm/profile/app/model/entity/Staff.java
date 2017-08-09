package com.asiainfo.meo.prm.profile.app.model.entity;

// Generated 2015-5-11 19:31:42 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmStaffInfo generated by hbm2java
 */
public class Staff implements java.io.Serializable
{
    
    /** sts constant */
    public static final int   STS_INACTIVE        = 0;
    /** sts constant */
    public static final int   STS_ACTIVE          = 1;
    /** sts constant */
    public static final int   STS_DEACTIVE        = 2;
    
    private Long      staffId;
    
    private Long      staffNo;
    
    private String    staffName;
    
    private String    email;
    
    private Integer   sts;
    
    private Timestamp modifyDate;
    
    private Timestamp createDate;
    
    private Integer   department;
    
    private Integer   staffType;
    
    public Long getStaffId()
    {
        return staffId;
    }
    
    public void setStaffId(Long staffId)
    {
        this.staffId = staffId;
    }
    
    public Long getStaffNo()
    {
        return staffNo;
    }
    
    public void setStaffNo(Long staffNo)
    {
        this.staffNo = staffNo;
    }
    
    public String getStaffName()
    {
        return staffName;
    }
    
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getModifyDate()
    {
        return modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Timestamp getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Integer getDepartment()
    {
        return department;
    }
    
    public void setDepartment(Integer department)
    {
        this.department = department;
    }
    
    public Integer getStaffType()
    {
        return staffType;
    }
    
    public void setStaffType(Integer staffType)
    {
        this.staffType = staffType;
    }
    
}