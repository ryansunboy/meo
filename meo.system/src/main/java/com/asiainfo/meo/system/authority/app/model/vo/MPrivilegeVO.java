package com.asiainfo.meo.system.authority.app.model.vo;

import java.util.List;

public class MPrivilegeVO
{
    private MenuVO menu;
    
    private List<OPrivilegeVO> operPrivileges;

    public MenuVO getMenu()
    {
        return menu;
    }

    public void setMenu(MenuVO menu)
    {
        this.menu = menu;
    }

    public List<OPrivilegeVO> getOperPrivileges()
    {
        return operPrivileges;
    }

    public void setOperPrivileges(List<OPrivilegeVO> operPrivileges)
    {
        this.operPrivileges = operPrivileges;
    }
}
