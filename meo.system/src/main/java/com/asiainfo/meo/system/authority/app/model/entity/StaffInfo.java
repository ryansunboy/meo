package com.asiainfo.meo.system.authority.app.model.entity;


import java.sql.Timestamp;

public class StaffInfo implements java.io.Serializable
{
    private static final long serialVersionUID = -9061991366339436182L;
    
    public static final int   STS_VALID        = 0;
    
    public static final int   STS_INVALID      = 1;
    
    private Long staffId;
    private String avatarUrl;
    private Long staffNo;
    private String staffName;
    private String email;
    private Integer sts;
    private Timestamp modifyDate;
    private Timestamp createDate;
    private Integer department;
    private Integer staffType;
    private Integer gender;
    public Long getStaffId()
    {
        return staffId;
    }
    public void setStaffId(Long staffId)
    {
        this.staffId = staffId;
    }
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
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
    public Integer getGender()
    {
        return gender;
    }
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }
}
