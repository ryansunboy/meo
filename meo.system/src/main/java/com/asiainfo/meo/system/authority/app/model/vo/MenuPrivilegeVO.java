package com.asiainfo.meo.system.authority.app.model.vo;

import java.util.List;

public class MenuPrivilegeVO
{
    private MenuVO menu;
    
    private List<OperPrivilegeVO> operPrivileges;

    public MenuVO getMenu()
    {
        return menu;
    }

    public void setMenu(MenuVO menu)
    {
        this.menu = menu;
    }

    public List<OperPrivilegeVO> getOperPrivileges()
    {
        return operPrivileges;
    }

    public void setOperPrivileges(List<OperPrivilegeVO> operPrivileges)
    {
        this.operPrivileges = operPrivileges;
    }
    
    
}
