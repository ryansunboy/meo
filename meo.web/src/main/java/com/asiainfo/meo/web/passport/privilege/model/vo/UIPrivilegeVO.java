package com.asiainfo.meo.web.passport.privilege.model.vo;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;

public class UIPrivilegeVO
{   
    @NotEmpty(groups={Insert.class})
    private String privilegeCode;
    
    @NotEmpty(groups={Insert.class})
    private String privilegeName;

    public String getPrivilegeCode()
    {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode)
    {
        this.privilegeCode = privilegeCode;
    }

    public String getPrivilegeName()
    {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName)
    {
        this.privilegeName = privilegeName;
    }

}
