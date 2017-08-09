package com.asiainfo.meo.system.authority.app.model.vo;

import java.util.List;
import java.util.Set;

public class UPrivilegeVO
{
    private String             userName;
    
    private Set<String>             roleNames;
    
    private List<MPrivilegeVO> menuPrivileges;
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Set<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

	public List<MPrivilegeVO> getMenuPrivileges()
    {
        return menuPrivileges;
    }
    
    public void setMenuPrivileges(List<MPrivilegeVO> menuPrivileges)
    {
        this.menuPrivileges = menuPrivileges;
    }
    
}
