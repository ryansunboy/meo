package com.asiainfo.meo.system.common.app.model.vo;

public class MeasureDefine {
	
    
    public static final Long CURRENCY_UNIT_TYPE  = (long) 0;
    public static final Long TIME_UNIT_TYPE  = (long) 1;
	private Long measureId;
	
	private Long measureTypeId;
	
	private String name;

    public Long getMeasureId()
    {
        return measureId;
    }

    public void setMeasureId(Long measureId)
    {
        this.measureId = measureId;
    }

    public Long getMeasureTypeId()
    {
        return measureTypeId;
    }

    public void setMeasureTypeId(Long measureTypeId)
    {
        this.measureTypeId = measureTypeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
