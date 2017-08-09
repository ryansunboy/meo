package com.asiainfo.meo.web.common.define.model.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;


public class UIEnumGroupDefVO 
{
    @Valid
    @NotNull(groups={Insert.class})
    private String    groupCode;
    
    @Valid
    @NotEmpty(groups={Insert.class})
    private String    groupName;
    
    @Valid
    @NotNull(groups={Insert.class})
    private Integer   groupType;
    
    @Valid
    @NotNull(groups={Insert.class})
    private Long      enumId;

    private Long      operatorId;

    public String getGroupCode()
    {
        return groupCode;
    }

    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public Integer getGroupType()
    {
        return groupType;
    }

    public void setGroupType(Integer groupType)
    {
        this.groupType = groupType;
    }

    public Long getEnumId()
    {
        return enumId;
    }

    public void setEnumId(Long enumId)
    {
        this.enumId = enumId;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
   
   
    
}