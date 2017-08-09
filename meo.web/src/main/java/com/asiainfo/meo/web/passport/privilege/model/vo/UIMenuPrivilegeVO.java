package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UIMenuPrivilegeVO
{
    @Valid
    @NotNull
    private UIMenuVO menu;
    
    @Valid
    @NotNull
    private List<UIOperPrivilegeVO> operPrivileges;

    public UIMenuVO getMenu()
    {
        return menu;
    }

    public void setMenu(UIMenuVO menu)
    {
        this.menu = menu;
    }

    public List<UIOperPrivilegeVO> getOperPrivileges()
    {
        return operPrivileges;
    }

    public void setOperPrivileges(List<UIOperPrivilegeVO> operPrivileges)
    {
        this.operPrivileges = operPrivileges;
    }
    
    
}
