/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo;                                                                                                                                                                                                                                                                       

import org.springframework.web.bind.MissingServletRequestParameterException;
                                                                                                                                                                                                                                                                                              
 /** @Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
 * @Date 2015-4-24                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class MeoExceptionReslover
{
    
    public void test(Throwable t)
    {
        if(t instanceof MissingServletRequestParameterException)
        {
            MissingServletRequestParameterException me = (MissingServletRequestParameterException)t;
            String parameterName = me.getParameterName();
        }
    }
}
