package com.asiainfo.meo.common.event.mq.redis;
///**                                                                  
//  *                                                                                                                                                                                                                                                                                           
//  */                                                                              
// package com.asiainfo.meo.common.core.mq.redis;                                                                                                                                                                                                                                                                       
//
//import java.io.IOException;
//
//import javax.annotation.Resource;
//
//import org.springframework.data.redis.core.RedisTemplate;
//
//import com.asiainfo.meo.common.core.mq.Message;
//import com.asiainfo.meo.common.core.mq.Producer;
//import com.asiainfo.meo.common.core.utils.JsonUtil;
//                                                                                                                                                                                                                                                                                              
// /** @Description: TODO(这里用一句话描述这个类的作用)                   
// * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
// * @Author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
// * @Date 2015-4-13                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
// */
//public class RedisProducer implements Producer
//{
//    @Resource
//    private RedisTemplate redisTemplate;
//    
//    private String topic;
//    
//    /* (Not Javadoc)                                         
//     * <p>Title: send</p>                                                                                                                                                                                                                                             
//     * <p>Description: </p>                                                                                                                                                                                                                                                          
//     * @param m                                                                                                                                                                                                                                                                                   
//     * @see com.asiainfo.meo.common.core.mq.Producer#send(com.asiainfo.meo.common.core.mq.Message)                                                                                                                                                                                                                                                                      
//     */
//    @Override
//    public void send(Message m)
//    {
//        // TODO Auto-generated method stub          
//        Object o = m.getPayload();
//        try
//        {
//            redisTemplate.convertAndSend(getTopic(), JsonUtil.writeObjectAsString(o));
//        }
//        catch(IOException e)
//        {
//             e.printStackTrace();
//        }
//    }
//
//    /* (Not Javadoc)                                         
//      * <p>Title: getTopic</p>                                                                                                                                                                                                                                             
//      * <p>Description: </p>                                                                                                                                                                                                                                                          
//      * @return                                                                                                                                                                                                                                                                                   
//      * @see com.asiainfo.meo.common.core.mq.Producer#getTopic()                                                                                                                                                                                                                                                                      
//      */
//    @Override
//    public String getTopic()
//    {
//         return this.topic;
//    }
//
//    public void setTopic(String topic)
//    {
//        this.topic = topic;
//    }
//    
//}
