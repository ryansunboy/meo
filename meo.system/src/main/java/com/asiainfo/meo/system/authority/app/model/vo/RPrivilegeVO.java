package com.asiainfo.meo.system.authority.app.model.vo;

import java.util.List;

public class RPrivilegeVO
{
    private UserRoleVO         role;
    
    private List<MPrivilegeVO> menuPriveliges;
    
    public UserRoleVO getRole()
    {
        return role;
    }
    
    public void setRole(UserRoleVO role)
    {
        this.role = role;
    }
    
    public List<MPrivilegeVO> getMenuPriveliges()
    {
        return menuPriveliges;
    }
    
    public void setMenuPriveliges(List<MPrivilegeVO> menuPriveliges)
    {
        this.menuPriveliges = menuPriveliges;
    }
}
