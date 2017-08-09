package com.asiainfo.meo.web.campaign.model.vo;

import javax.validation.constraints.NotNull;

public class UITaskParamVO
{
    private Long    paramInstanceId;
    
    @NotNull
    private Long    paramId;
    
    private String  paramName;
    
    @NotNull
    private Long paramValue;
    
    public Long getParamInstanceId()
    {
        return paramInstanceId;
    }
    
    public void setParamInstanceId(Long paramInstanceId)
    {
        this.paramInstanceId = paramInstanceId;
    }
    
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
    
    public Long getParamValue()
    {
        return paramValue;
    }
    
    public void setParamValue(Long paramValue)
    {
        this.paramValue = paramValue;
    }
}
