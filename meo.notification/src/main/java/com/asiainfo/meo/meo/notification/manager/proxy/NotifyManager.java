package com.asiainfo.meo.meo.notification.manager.proxy;

import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;

public interface NotifyManager
{
    /**
     * @Description: 发送消息的前置方法
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param commonBean
     */
    public void beforeNotifyHandle(NotifyCommonBean commonBean);
    
    /**
     * @Description: 执行发送消息方法
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param activityClass
     * @param commonBean
     * @return
     */
    public Object executeNotify(Class<? extends NotifyBean> activityClass, NotifyCommonBean commonBean);
    
    /**
     * @Description: 执行发消息后置方法
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param commonBean
     */
    public void afterNotifyHandle(NotifyCommonBean commonBean);
}
