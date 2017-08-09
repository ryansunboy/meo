/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.web.core.advice.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.web.core.Message;

/**
 * @Description: 返回结果的切面
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-28
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object>
{
    private static final Object DEFAULT_RETURN = new Object();
   
    /* (Not Javadoc)                                         
      * <p>Title: supports</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param returnType
      * @param converterType
      * @return                                                                                                                                                                                                                                                                                   
      * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)                                                                                                                                                                                                                                                                      
      */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType)
    {
        // TODO Auto-generated method stub                                     
         return true;
    }

    /* (Not Javadoc)                                         
      * <p>Title: beforeBodyWrite</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param body
      * @param returnType
      * @param selectedContentType
      * @param selectedConverterType
      * @param request
      * @param response
      * @return                                                                                                                                                                                                                                                                                   
      * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.MediaType, java.lang.Class, org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse)                                                                                                                                                                                                                                                                      
      */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response)
    {
        BoContext.getBoContext().setResponseParams(body);  
        // 检查对象的返回类型是否为VOID类型
        Class<?> detegateReturnType = returnType.getParameterType();
        if(Void.TYPE.getName().equals(detegateReturnType.getName()))
        {
            return new Message<Void>();
        }
        
        //返回类型不为空，返回结果为空，
        if(body == null)
        {
            body = new Message<Object>(DEFAULT_RETURN); //
        }
        else
        {
            //  检查消息体对象是否为空，//兼容代码中返回对象为 Message<?>的老代码
            if(body instanceof Message)
            {
                Message returnMessage = (Message)(body);
                Object o = returnMessage.getBody();
                if(returnMessage.getError() == null && o == null)
                {
                    ((Message)body).setBody(DEFAULT_RETURN);
                }
            }
        }
        return body instanceof Message ? body : new Message(body);
    }
    
}
