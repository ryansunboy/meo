package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

public class UIRPrivilegeVO
{
    private UIUserRoleVO         role;
    
    private List<UIMPrivilegeVO> menuPriveliges;
    
    public UIUserRoleVO getRole()
    {
        return role;
    }
    
    public void setRole(UIUserRoleVO role)
    {
        this.role = role;
    }
    
    public List<UIMPrivilegeVO> getMenuPriveliges()
    {
        return menuPriveliges;
    }
    
    public void setMenuPriveliges(List<UIMPrivilegeVO> menuPriveliges)
    {
        this.menuPriveliges = menuPriveliges;
    }
}
