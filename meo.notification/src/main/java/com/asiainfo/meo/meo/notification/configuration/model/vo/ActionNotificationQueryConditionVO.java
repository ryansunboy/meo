package com.asiainfo.meo.meo.notification.configuration.model.vo;
/**
 * 
  * @Description: The class contains query conditions of ActionNotification         
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 11, 2015 1:02:46 PM 
  * @version 1.0
 */
public class ActionNotificationQueryConditionVO
{
    private Integer pageNo;
    private Integer pageSize;
    private Long notifyId;
    private Long actionId;
    
    public Integer getPageNo()
    {
        return pageNo;
    }
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    public Integer getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
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
}
