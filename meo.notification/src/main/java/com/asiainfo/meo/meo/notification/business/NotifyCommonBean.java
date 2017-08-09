package com.asiainfo.meo.meo.notification.business;

/**
 * this bean is notify common bean
 */

public class NotifyCommonBean
{
    // ************the notify type**************************//
    public final static int STS_IOS      = 1;
    
    public final static int STS_ANDROID  = 2;
    
    public final static int STS_EMAIL    = 3;
    
    public final static int STS_SMS      = 4;
    
    // ***************the priority level**********************//
    
    public final static int HIGH_LEVEL   = 0;
    
    public final static int MIDDLE_LEVEL = 1;
    
    public final static int LOW_LEVEL    = 2;
    
    // **************************************//
    
    public NotifyCommonBean()
    {
        
    }
    
    public NotifyCommonBean(Long userId,Long userType,Long actionId)
    {
        this.userId = userId;
        this.userType = userType;
        this.actionId = actionId;
    }
    
    public NotifyCommonBean(Long userId, Long userType, Long actionId,Integer notifyType)
    {
        this.userId = userId;
        this.userType = userType;
        this.actionId = actionId;
        this.notifyType = notifyType;
    }
    
    private Integer notifyType;
    
    private Long    userId;
    
    private Long    userType;
    
    private Long    actionId;
    
    private Long    templateId;
    
    private String  deviceId;
    
    private String  deviceToken;
    
    private Long    channelType;
    
    private Long    channelId;
    
    private Integer priority;

    public Integer getNotifyType()
    {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType)
    {
        this.notifyType = notifyType;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserType()
    {
        return userType;
    }

    public void setUserType(Long userType)
    {
        this.userType = userType;
    }

    public Long getActionId()
    {
        return actionId;
    }

    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }

    public Long getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId(Long templateId)
    {
        this.templateId = templateId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceToken()
    {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }

    public Long getChannelType()
    {
        return channelType;
    }

    public void setChannelType(Long channelType)
    {
        this.channelType = channelType;
    }

    public Long getChannelId()
    {
        return channelId;
    }

    public void setChannelId(Long channelId)
    {
        this.channelId = channelId;
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
