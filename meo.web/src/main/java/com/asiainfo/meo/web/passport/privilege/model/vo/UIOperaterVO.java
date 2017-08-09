package com.asiainfo.meo.web.passport.privilege.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;


public class UIOperaterVO
{
    @NotNull(groups={Insert.class})
    private Long operaterId;

    public Long getOperaterId()
    {
        return operaterId;
    }

    public void setOperaterId(Long operaterId)
    {
        this.operaterId = operaterId;
    }

}
