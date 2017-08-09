package com.asiainfo.meo.web.common.define.model.vo;

public class UITimeUnitInfoVO
{
    private Long timeUnitId;
    
    private Long measureTypeId;
    
    private String timeUnitName;
   
    public Long getMeasureTypeId()
    {
        return measureTypeId;
    }

    public void setMeasureTypeId(Long measureTypeId)
    {
        this.measureTypeId = measureTypeId;
    }

    public Long getTimeUnitId()
    {
        return timeUnitId;
    }

    public void setTimeUnitId(Long timeUnitId)
    {
        this.timeUnitId = timeUnitId;
    }

    public String getTimeUnitName()
    {
        return timeUnitName;
    }

    public void setTimeUnitName(String timeUnitName)
    {
        this.timeUnitName = timeUnitName;
    }

}
