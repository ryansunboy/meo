package com.asiainfo.meo.activity.business.bean;

import com.asiainfo.meo.common.core.exception.MeoException;


public interface ActivityBean
{
    /**
     * 
      * @Description: 初始化处理
      * @modifyReason: 
      * @author liuyang
      * @param input
      * @throws MeoException
     */
    public void init(Object... input) ;
    /**
     *    
      * @Description: 业务校验
      * @modifyReason: 
      * @author liuyang
      * @param input
      * @throws MeoException
     */
    public void validate(Object... input);
    
    /**
     * 
      * @Description: 活动处理方法
      * @modifyReason: 
      * @author liuyang
      * @param input
      * @return
      * @throws MeoException
     */
    public Object business(Object... input);
    
    
}
