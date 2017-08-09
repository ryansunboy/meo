package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

public class UIMPrivilegeVO
{
    private UIMenuVO             menu;
    
    private List<UIOPrivilegeVO> operPrivileges;
    
    public UIMenuVO getMenu()
    {
        return menu;
    }
    
    public void setMenu(UIMenuVO menu)
    {
        this.menu = menu;
    }
    
    public List<UIOPrivilegeVO> getOperPrivileges()
    {
        return operPrivileges;
    }
    
    public void setOperPrivileges(List<UIOPrivilegeVO> operPrivileges)
    {
        this.operPrivileges = operPrivileges;
    }
}
