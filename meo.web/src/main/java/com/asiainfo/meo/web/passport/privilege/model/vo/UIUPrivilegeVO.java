package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;
import java.util.Set;

public class UIUPrivilegeVO
{
    private String               userName;
    
    private Set<String>               roleNames;
    
    private List<UIMPrivilegeVO> menuPrivileges;
    
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

	public List<UIMPrivilegeVO> getMenuPrivileges()
    {
        return menuPrivileges;
    }
    
    public void setMenuPrivileges(List<UIMPrivilegeVO> menuPrivileges)
    {
        this.menuPrivileges = menuPrivileges;
    }
}
