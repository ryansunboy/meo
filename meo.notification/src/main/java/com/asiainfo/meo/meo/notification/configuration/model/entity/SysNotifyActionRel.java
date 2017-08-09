package com.asiainfo.meo.meo.notification.configuration.model.entity;

import java.sql.Timestamp;

public class SysNotifyActionRel
{
    private long relId;
    
    private Long notifyId;
    
    private Long actionId;
    
    private Integer   sts;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    private Integer receiverType;
    
    public static final Integer STS_VALID                   = 1;
    
    public static final Integer STS_INVALID                 = 2;
    
    public static final String SEQ_SYS_NOTIFY_ACTION_REL = "SEQ_SYS_NOTIFY_ACTION_REL_ID";

    public long getRelId()
    {
        return relId;
    }

    public void setRelId(long relId)
    {
        this.relId = relId;
    }

    public Long getNotifyId()
    {
        return notifyId;
    }

    public void setNotifyId(Long notifyId)
    {
        this.notifyId = notifyId;
    }

    public Long getActionId()
    {
        return actionId;
    }

    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }

    public Integer getSts()
    {
        return sts;
    }

    public void setSts(Integer sts)
    {
        this.sts = sts;
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

    public Integer getReceiverType()
    {
        return receiverType;
    }

    public void setReceiverType(Integer receiverType)
    {
        this.receiverType = receiverType;
    }
}
