package com.asiainfo.meo.meo.notification.configuration.model.vo;

import java.io.Serializable;
/**
 * 
  * @Description: The class contains all values of SysNotifyActionRel entity                  
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 8, 2015 11:26:55 AM 
  * @version 1.0
 */
public class NotifyActionRelVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 3741349278945268426L;
    
    private Long relId;
    private Long notifyId;
    private Long actionId;
    private Integer receiverType;
    private String notificationName;
    private String actionName;
    private String receiverTypeName;
    private Long templateId;
    
    public Long getRelId()
    {
        return relId;
    }
    public void setRelId(Long relId)
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
    public Integer getReceiverType()
    {
        return receiverType;
    }
    public void setReceiverType(Integer receiverType)
    {
        this.receiverType = receiverType;
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
    public Long getTemplateId()
    {
        return templateId;
    }
    public void setTemplateId(Long templateId)
    {
        this.templateId = templateId;
    }

}
