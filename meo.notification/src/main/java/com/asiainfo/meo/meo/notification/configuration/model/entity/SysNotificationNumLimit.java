package com.asiainfo.meo.meo.notification.configuration.model.entity;

import java.sql.Timestamp;

public class SysNotificationNumLimit
{
    private long numLimitId;
    
    private Long actionId;
    
    private Integer regionCode;
    
    private Integer maxNum;
    
    private Integer cycleType;
    
    private Integer cycleUnit;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    private Integer sts;
    
    private Long operatorId;
    
    public static final Integer STS_VALID                   = 1;
    
    public static final Integer STS_INVALID                 = 2;
    
    public static final String SEQ_SYS_NOTIFICATION_NUM_LIMIT = "SEQ_SYS_NOTIFICATION_NUM_LIMIT_ID";

    public long getNumLimitId()
    {
        return numLimitId;
    }

    public void setNumLimitId(long numLimitId)
    {
        this.numLimitId = numLimitId;
    }

    public Long getActionId()
    {
        return actionId;
    }

    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }

    public Integer getRegionCode()
    {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode)
    {
        this.regionCode = regionCode;
    }

    public Integer getMaxNum()
    {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum)
    {
        this.maxNum = maxNum;
    }

    public Integer getCycleType()
    {
        return cycleType;
    }

    public void setCycleType(Integer cycleType)
    {
        this.cycleType = cycleType;
    }

    public Integer getCycleUnit()
    {
        return cycleUnit;
    }

    public void setCycleUnit(Integer cycleUnit)
    {
        this.cycleUnit = cycleUnit;
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
