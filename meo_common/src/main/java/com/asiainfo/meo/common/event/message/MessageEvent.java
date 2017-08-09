/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.message;

import com.asiainfo.meo.common.event.Event;


/**
 * @Description: 消息事件接口
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-14
 */
public interface MessageEvent extends Event
{
    String getTopic();
}
