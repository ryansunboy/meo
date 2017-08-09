package com.asiainfo.meo.activity.manager.proxy;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerBundle;


public interface ActivityManager
{
    /**
     * 
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author liuyang
     * @param activityManagerBundle 
     */
    public void beforeActivityHandle(ActivityManagerBundle activityManagerBundle);
    
    /**
     * 
      * @Description: 执行活动方法
      * @modifyReason: 
      * @author liuyang
      * @param activityClass
      * @param params
      * @return
     */
    
    public Object executeActivity(Class<? extends ActivityBean> activityClass,Object... params);
    
    /**
     * 
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author liuyang
     * @param activityManagerBundle 
     */
    public void afterActivityHandle(ActivityManagerBundle activityManagerBundle);
   
}
