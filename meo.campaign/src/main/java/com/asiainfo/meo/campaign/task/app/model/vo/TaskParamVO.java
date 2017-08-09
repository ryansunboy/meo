package com.asiainfo.meo.campaign.task.app.model.vo;

public class TaskParamVO
{
    private Long    paramInstanceId;
    
    private Long    paramId;
    
    private String  paramName;
    
    private Long paramValue;
    
    private Integer paramValueUnit;
    
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
    
    public Integer getParamValueUnit()
    {
        return paramValueUnit;
    }
    
    public void setParamValueUnit(Integer paramValueUnit)
    {
        this.paramValueUnit = paramValueUnit;
    }
}
