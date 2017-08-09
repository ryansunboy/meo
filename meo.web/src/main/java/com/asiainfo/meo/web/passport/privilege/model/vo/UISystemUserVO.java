package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UISystemUserVO
{
    @NotNull(groups = {Update.class })
    private Long         userId;
    
    @NotNull(groups = {Insert.class, Update.class })
    private List<Long>   roleIds;
    
    @NotNull(groups = {Insert.class, Update.class })
    private Integer      genderId;
    
    private String       gender;
    
    @NotNull(groups = {Insert.class, Update.class })
    private String       userName;
    
    private Integer      department;
    
    private Integer      staffType;
    
    @NotNull(groups = {Insert.class, Update.class })
    private String       email;
    
    private String       avatarUrl;
    
    private List<String> roleNames;
    
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
    
    public String getGender()
    {
        return gender;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
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
    
    public List<String> getRoleNames()
    {
        return roleNames;
    }
    
    public void setRoleNames(List<String> roleNames)
    {
        this.roleNames = roleNames;
    }
}
