package com.asiainfo.meo.system.authority.app.model.vo;

public class UserRoleVO
{
    
    private Long   roleId;
    
    private String roleName;
    
    private Long   createDate;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public Long getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Long createDate)
    {
        this.createDate = createDate;
    }
    
}
