package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UINotifyActionRelVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -7710450803378342315L;
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long notifyId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long actionId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Integer receiverType;
    
    @Valid
    @NotNull(groups={Update.class})
    private Long relId;
    
    private String notificationName;
    
    private String actionName;
    
    private String receiverTypeName;
    
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
    public Integer getReceiverType()
    {
        return receiverType;
    }
    public void setReceiverType(Integer receiverType)
    {
        this.receiverType = receiverType;
    }
    public Long getRelId()
    {
        return relId;
    }
    public void setRelId(Long relId)
    {
        this.relId = relId;
    }
    public String getNotificationName()
    {
        return notificationName;
    }
    public void setNotificationName(String notificationName)
    {
        this.notificationName = notificationName;
    }
    public String getActionName()
    {
        return actionName;
    }
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    public String getReceiverTypeName()
    {
        return receiverTypeName;
    }
    public void setReceiverTypeName(String receiverTypeName)
    {
        this.receiverTypeName = receiverTypeName;
    }
}
