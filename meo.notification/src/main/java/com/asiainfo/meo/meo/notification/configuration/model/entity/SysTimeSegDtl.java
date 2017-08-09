package com.asiainfo.meo.meo.notification.configuration.model.entity;

import java.sql.Timestamp;

public class SysTimeSegDtl
{
    private long dtlId;
    
    private Long segId;
    
    private Long startVal;
    
    private Long endVal;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    private Integer sts;
    
    private Long operatorId;
    
    public static final Integer STS_VALID                   = 1;
    
    public static final Integer STS_INVALID                 = 2;
    
    public static final String SEQ_SYS_TIME_SEG_DTL = "SEQ_SYS_TIME_SEG_DTL_ID";

    public long getDtlId()
    {
        return dtlId;
    }

    public void setDtlId(long dtlId)
    {
        this.dtlId = dtlId;
    }

    public Long getSegId()
    {
        return segId;
    }

    public void setSegId(Long segId)
    {
        this.segId = segId;
    }

    public Long getStartVal()
    {
        return startVal;
    }

    public void setStartVal(Long startVal)
    {
        this.startVal = startVal;
    }

    public Long getEndVal()
    {
        return endVal;
    }

    public void setEndVal(Long endVal)
    {
        this.endVal = endVal;
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
    
}
