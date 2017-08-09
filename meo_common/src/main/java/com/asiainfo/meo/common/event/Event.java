/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event;

import java.io.Serializable;

/**
 * @Description: 事件接口
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-13
 */
public interface Event extends Serializable
{
    String getEventId();
    
    Object getPayload();
    
    <T> T getPayload(Class<T> type);
    
    boolean isAsysEvent();
}
