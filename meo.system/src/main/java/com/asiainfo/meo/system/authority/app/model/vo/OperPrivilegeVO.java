package com.asiainfo.meo.system.authority.app.model.vo;

import javax.validation.Valid;

public class OperPrivilegeVO
{
    @Valid
    private PrivilegeVO privilege;
    
    @Valid
    private OperaterVO operater;

    public PrivilegeVO getPrivilege()
    {
        return privilege;
    }

    public void setPrivilege(PrivilegeVO privilege)
    {
        this.privilege = privilege;
    }

    public OperaterVO getOperater()
    {
        return operater;
    }

    public void setOperater(OperaterVO operater)
    {
        this.operater = operater;
    }

    
    
    
}
