package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

public class UIUserPrivilegeVO
{
    private String                  userName;
    
    private String                  roleName;
    
    private List<UIMenuPrivilegeVO> menuPrivileges;
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
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
