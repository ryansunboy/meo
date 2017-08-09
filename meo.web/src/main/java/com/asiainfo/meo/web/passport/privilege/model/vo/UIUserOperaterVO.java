package com.asiainfo.meo.web.passport.privilege.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIUserOperaterVO
{
    @NotNull(groups={Update.class})
    private Long      operId;
    
    @NotEmpty(groups={Update.class,Insert.class})
    private String    operName;
    
    
    public Long getOperId()
    {
        return this.operId;
    }
    
    public void setOperId(Long operId)
    {
        this.operId = operId;
    }
    
    public String getOperName()
    {
        return this.operName;
    }
    
    public void setOperName(String operName)
    {
        this.operName = operName;
    }
    
    
}
