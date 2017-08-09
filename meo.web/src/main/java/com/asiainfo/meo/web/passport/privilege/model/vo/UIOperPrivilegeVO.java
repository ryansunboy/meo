package com.asiainfo.meo.web.passport.privilege.model.vo;

import javax.validation.Valid;


public class UIOperPrivilegeVO
{

    @Valid
    private UIPrivilegeVO privilege;
    
    @Valid
    private UIOperaterVO operater;

    public UIPrivilegeVO getPrivilege()
    {
        return privilege;
    }

    public void setPrivilege(UIPrivilegeVO privilege)
    {
        this.privilege = privilege;
    }

    public UIOperaterVO getOperater()
    {
        return operater;
    }

    public void setOperater(UIOperaterVO operater)
    {
        this.operater = operater;
    }
    
}
