package com.asiainfo.meo.meo.notification.notification.profile.model;

import java.sql.Timestamp;

public class NotifySendDtl implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private Long              sendId;
    
    private String            sender;
    
    private String            receiver;
    
    private Integer           sts;
    
    private Timestamp         doneTime;
    
    private Integer           receiverType;
    
    private Long              actionId;
    
    private Long              notifyChannelId;
    
    private Integer           priority;
    
    // ***************************************//
    public static final int   STS_INIT         = 0;
    
    public static final int   STS_SUCCESS      = 1;
    
    public static final int   STS_FAIL         = 2;
    
    public static final int   STS_PROCESSING   = 3;
    
    // ***************************************//
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    // ***************************************//
    public Long getSendId()
    {
        return sendId;
    }
    
    public void setSendId(Long sendId)
    {
        this.sendId = sendId;
    }
    
    public String getSender()
    {
        return sender;
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
    }
    
    public String getReceiver()
    {
        return receiver;
    }
    
    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getDoneTime()
    {
        return doneTime;
    }
    
    public void setDoneTime(Timestamp doneTime)
    {
        this.doneTime = doneTime;
    }
    
    public Integer getReceiverType()
    {
        return receiverType;
    }
    
    public void setReceiverType(Integer receiverType)
    {
        this.receiverType = receiverType;
    }
    
    public Long getActionId()
    {
        return actionId;
    }
    
    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }
    
    public Long getNotifyChannelId()
    {
        return notifyChannelId;
    }
    
    public void setNotifyChannelId(Long notifyChannelId)
    {
        this.notifyChannelId = notifyChannelId;
    }
    
    public Integer getPriority()
    {
        return priority;
    }
    
    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }
}