package com.asiainfo.meo.meo.notification.notification.profile.repository;

import com.asiainfo.meo.meo.notification.notification.profile.model.NotifySendParam;

public interface NotifySendParamRepository
{
    /**
     * 
      * @Description: 新增NotifySendParam
      * @Description: save NotifySendParam
      * @modifyReason: 
      * @author zhengzy
      * @param notifySendParam
     */
    public void addNotifySendParam(NotifySendParam notifySendParam);
    
    /**
     * 
      * @Description: 修改NotifySendParam
      * @Description: modify NotifySendParam
      * @modifyReason: 
      * @author zhengzy
      * @param notifySendParam
     */
    public void updateNotifySendParam(NotifySendParam notifySendParam);
    
    /**
     * 
      * @Description: 根据id 删除 NotifySendParam
      * @Description: delete NotifySendParam by id
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public void deleteNotifySendParamById(Long id);
    
    /**
     * 
      * @Description: 根据id查询NotifySendParam
      * @Description: query NotifySendParam by id
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public NotifySendParam getNotifySendParamById(Long id);
}
