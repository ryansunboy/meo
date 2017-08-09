package com.asiainfo.meo.system.authority.app.model.vo;

import java.util.List;

public class SystemUserVO
{
    private Long         userId;
    
    private List<Long>   roleIds;
    
    private Integer      genderId;
    
    private String       email;
    
    private String       avatarUrl;
    
    private String       userName;
    
    private Integer      department;
    
    private Integer      staffType;
    
    private List<String> roleNames;
    
    private Integer      sts;
    
    public Long getUserId()
    {
        return userId;
    }
    
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    
    public List<Long> getRoleIds()
    {
        return roleIds;
    }
    
    public void setRoleIds(List<Long> roleIds)
    {
        this.roleIds = roleIds;
    }
    
    public Integer getGenderId()
    {
        return genderId;
    }
    
    public void setGenderId(Integer genderId)
    {
        this.genderId = genderId;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
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
    
    public List<String> getRoleNames()
    {
        return roleNames;
    }
    
    public void setRoleNames(List<String> roleNames)
    {
        this.roleNames = roleNames;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
}
