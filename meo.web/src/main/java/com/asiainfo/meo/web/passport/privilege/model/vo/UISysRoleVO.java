package com.asiainfo.meo.web.passport.privilege.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;

public class UISysRoleVO
{
    @NotNull(groups={Insert.class})
    private Long   roleId;
    

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

}
