/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.event.message.impl;

import com.asiainfo.meo.common.event.SimpleEvent;
import com.asiainfo.meo.common.event.message.MessageEvent;


/**
 * @Description: 简单消息事件实现
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-15
 */
public class SimpleMessageEvent extends SimpleEvent implements MessageEvent
{
    
    private static final long serialVersionUID = -7421693996188848710L;

    private String topic;
    
    public SimpleMessageEvent()
    {
        
    }
    
    /**                                            
     * <p>Title: </p>                                                                                                                                                                                                                                                                
     * <p>Description:</p>                                                                                                                                                                                                                                                           
     * @param eventId                                                                                                                                                                                                                                                                                   
     */
   public SimpleMessageEvent(String eventId,String topic)
   {
       super(eventId);       
       this.topic = topic;
       setAsysEvent(false);
   }
    
    /* (Not Javadoc)                                         
      * <p>Title: getTopic</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.event.MessageEvent#getTopic()                                                                                                                                                                                                                                                                      
      */
    @Override
    public String getTopic()
    {
        // TODO Auto-generated method stub                                     
         return this.topic;
    }


    public void setTopic(String topic)
    {
        this.topic = topic;
    }
}
