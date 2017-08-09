package com.asiainfo.meo.meo.notification.configuration.model.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
  * @Description: (English description)                  
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 8, 2015 2:50:01 PM 
  * @version 1.0
 */
public class ActionNotificationVO implements Serializable
{
    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -2713228232026386295L;
    private NotifyActionRelVO notifyActionRelVO;
    private List<NotificationTimeLimitVO> notifyTimeLimitList;
    private List<NotificationNumLimitVO> notifyNumLimitList;
    
    public NotifyActionRelVO getNotifyActionRelVO()
    {
        return notifyActionRelVO;
    }
    public void setNotifyActionRelVO(NotifyActionRelVO notifyActionRelVO)
    {
        this.notifyActionRelVO = notifyActionRelVO;
    }
    public List<NotificationTimeLimitVO> getNotifyTimeLimitList()
    {
        return notifyTimeLimitList;
    }
    public void setNotifyTimeLimitList(List<NotificationTimeLimitVO> notifyTimeLimitList)
    {
        this.notifyTimeLimitList = notifyTimeLimitList;
    }
    public List<NotificationNumLimitVO> getNotifyNumLimitList()
    {
        return notifyNumLimitList;
    }
    public void setNotifyNumLimitList(List<NotificationNumLimitVO> notifyNumLimitList)
    {
        this.notifyNumLimitList = notifyNumLimitList;
    }
}
