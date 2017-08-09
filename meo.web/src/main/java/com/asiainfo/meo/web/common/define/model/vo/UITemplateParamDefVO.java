package com.asiainfo.meo.web.common.define.model.vo;

// Generated 2015-7-31 18:27:27 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

/**
 * SysTemplateParamDef generated by hbm2java
 */
public class UITemplateParamDefVO implements java.io.Serializable
{
    @Valid
    @NotNull(groups={Update.class})
    private Long    paramId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private String  paramName;
    
    private Integer paramType;
    
    private String  paramDesc;
    
    private Long    operatorId;
    
    public Long getParamId()
    {
        return paramId;
    }
    
    public void setParamId(Long paramId)
    {
        this.paramId = paramId;
    }
    
    public String getParamName()
    {
        return paramName;
    }
    
    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }
    
    public Integer getParamType()
    {
        return paramType;
    }
    
    public void setParamType(Integer paramType)
    {
        this.paramType = paramType;
    }
    
    public String getParamDesc()
    {
        return paramDesc;
    }
    
    public void setParamDesc(String paramDesc)
    {
        this.paramDesc = paramDesc;
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