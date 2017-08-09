package com.asiainfo.meo.meo.notification.business.bean;

import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;

public interface NotifyBean
{
   /**
    * 
     * @Description: 初始化数据
     * @Description: init the commonBean
     * @modifyReason: 
     * @author zhengzy
     * @param commonBean
    */
    public void init(NotifyCommonBean commonBean) ;
   
    /**
     * 
      * @Description: 验证业务数据
      * @Description: validate the business data
      * @modifyReason: 
      * @author zhengzy
      * @param commonBean
     */
    public void validate(NotifyCommonBean commonBean);
    
    /**
     * 
      * @Description: 处理业务数据
      * @Description: do the business data
      * @modifyReason: 
      * @author zhengzy
      * @param commonBean
      * @return
     */
    public Object business(NotifyCommonBean commonBean);
}
