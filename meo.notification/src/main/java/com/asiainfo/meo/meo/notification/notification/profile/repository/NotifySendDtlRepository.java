package com.asiainfo.meo.meo.notification.notification.profile.repository;

import com.asiainfo.meo.meo.notification.notification.profile.model.NotifySendDtl;

public interface NotifySendDtlRepository
{
    /**
     * 
      * @Description: 新增NotifySendDtl
      * @Description: add NotifySendDtl
      * @modifyReason: 
      * @author zhengzy
      * @param notifySendDtl
     */
    public void addNotifySendDtl(NotifySendDtl notifySendDtl);
    
    /**
     * 
      * @Description: 修改NotifySendDtl
      * @Description: modify NotifySendDtl
      * @modifyReason: 
      * @author zhengzy
      * @param notifySendDtl
     */
    public void updateNotifySendDtl(NotifySendDtl notifySendDtl);
    
    /**
     * 
      * @Description: 根据sendId 查询NotifySendDtl
      * @Description: query NotifySendDtl by sendId
      * @modifyReason: 
      * @author zhengzy
      * @param sendId
      * @return
     */
    public NotifySendDtl getNotifySendDtlBySendId(Long sendId);
    
    /**
     * 
      * @Description: 根据sendId删除NotifySendDtl
      * @Description: delete NotifySendDtl by sendId
      * @modifyReason: 
      * @author zhengzy
      * @param sendId
     */
    public void deleteNotifySendDtlBySendId(Long sendId);
}
