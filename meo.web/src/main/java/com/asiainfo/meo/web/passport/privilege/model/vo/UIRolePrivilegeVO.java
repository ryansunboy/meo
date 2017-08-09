package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UIRolePrivilegeVO
{
    @Valid
    @NotNull
    private UIUserRoleVO role;
    
    @Valid
    @NotNull
    private List<UIMenuPrivilegeVO> menuPrivileges;

    public UIUserRoleVO getRole()
    {
        return role;
    }

    public void setRole(UIUserRoleVO role)
    {
        this.role = role;
    }

    public List<UIMenuPrivilegeVO> getMenuPrivileges()
    {
        return menuPrivileges;
    }

    public void setMenuPrivileges(List<UIMenuPrivilegeVO> menuPrivileges)
    {
        this.menuPrivileges = menuPrivileges;
    }

    
    
}
