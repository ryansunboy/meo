package com.asiainfo.meo.meo.notification.configuration.model.entity;

import java.sql.Timestamp;

public class SysNotificationTimeLimit
{
    private long timeLimitId;
    
    private Long actionId;
    
    private Long segId;
    
    private Integer regionCode;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    private Integer sts;
    
    private Long operatorId;
    
    public static final Integer STS_VALID                   = 1;
    
    public static final Integer STS_INVALID                 = 2;
    
    public static final String SEQ_SYS_NOTIFICATION_TIME_LIMIT = "SEQ_SYS_NOTIFICATION_TIME_LIMIT_ID";

    public long getTimeLimitId()
    {
        return timeLimitId;
    }

    public void setTimeLimitId(long timeLimitId)
    {
        this.timeLimitId = timeLimitId;
    }

    public Long getActionId()
    {
        return actionId;
    }

    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }

    public Long getSegId()
    {
        return segId;
    }

    public void setSegId(Long segId)
    {
        this.segId = segId;
    }

    public Integer getRegionCode()
    {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode)
    {
        this.regionCode = regionCode;
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
