package com.asiainfo.meo.web.passport.privilege.model.vo;

public class UISystemUserQueryConditionVO
{
    private Integer pageNo;
    
    private Integer pageSize;
    
    private Long    userId;
    
    private String  userName;
    
    private Long    roleId;
    
    private String  email;
    
    public Integer getPageNo()
    {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public Integer getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public Long getUserId()
    {
        return userId;
    }
    
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Long getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
}
