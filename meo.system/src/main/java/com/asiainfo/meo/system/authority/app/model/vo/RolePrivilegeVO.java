package com.asiainfo.meo.system.authority.app.model.vo;

import java.util.List;

public class RolePrivilegeVO
{
    private UserRoleVO role;
 
    private List<MenuPrivilegeVO> menuPrivileges;

    public UserRoleVO getRole()
    {
        return role;
    }

    public void setRole(UserRoleVO role)
    {
        this.role = role;
    }

    public List<MenuPrivilegeVO> getMenuPrivileges()
    {
        return menuPrivileges;
    }

    public void setMenuPrivileges(List<MenuPrivilegeVO> menuPrivileges)
    {
        this.menuPrivileges = menuPrivileges;
    }

    
}
