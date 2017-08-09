package com.asiainfo.meo.meo.notification.configuration.model.entity;

import java.sql.Timestamp;

public class SysTimeSegDef
{
    private long segId;
    
    private Integer timeMode;
    
    private String segName;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    private Integer sts;
    
    private Long operatorId;
    
    private String description;
    
    public static final Integer STS_VALID                   = 1;
    
    public static final Integer STS_INVALID                 = 2;
    
    public static final String SEQ_SYS_TIME_SEG_DEF = "SEQ_SYS_TIME_SEG_ID";

    public long getSegId()
    {
        return segId;
    }

    public void setSegId(long segId)
    {
        this.segId = segId;
    }

    public Integer getTimeMode()
    {
        return timeMode;
    }

    public void setTimeMode(Integer timeMode)
    {
        this.timeMode = timeMode;
    }

    public String getSegName()
    {
        return segName;
    }

    public void setSegName(String segName)
    {
        this.segName = segName;
    }

    public Timestamp getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }

    public Timestamp getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public Integer getSts()
    {
        return sts;
    }

    public void setSts(Integer sts)
    {
        this.sts = sts;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
