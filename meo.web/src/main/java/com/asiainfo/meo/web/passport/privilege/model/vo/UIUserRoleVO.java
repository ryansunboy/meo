package com.asiainfo.meo.web.passport.privilege.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIUserRoleVO
{
    @NotNull(groups={Update.class})
    private Long   roleId;
    
    @NotEmpty(groups={Update.class,Insert.class})
    private String roleName;
    
    @NotNull(groups={Update.class,Insert.class})
    private Long   effectDate;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public Long getEffectDate()
    {
        return effectDate;
    }

    public void setEffectDate(Long effectDate)
    {
        this.effectDate = effectDate;
    }
}
