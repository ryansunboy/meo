 package com.asiainfo.meo.web.core.intercept;                                                                                                                                                                                                                                                                       

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
                                                                                                                                                                                                                                                                                              
public class MeoWebRequestInterceptor implements WebRequestInterceptor
{
    
    /* (Not Javadoc)                                         
     * <p>Title: preHandle</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param request
     * @throws Exception                                                                                                                                                                                                                                                                                   
     * @see org.springframework.web.context.request.WebRequestInterceptor#preHandle(org.springframework.web.context.request.WebRequest)                                                                                                                                                                                                                                                                      
     */
    @Override
    public void preHandle(WebRequest request) throws Exception
    {
        request.getLocale();
        // TODO Auto-generated method stub                                     
        System.out.println(request);
    }
    
    /* (Not Javadoc)                                         
     * <p>Title: postHandle</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param request
     * @param model
     * @throws Exception                                                                                                                                                                                                                                                                                   
     * @see org.springframework.web.context.request.WebRequestInterceptor#postHandle(org.springframework.web.context.request.WebRequest, org.springframework.ui.ModelMap)                                                                                                                                                                                                                                                                      
     */
    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception
    {
        // TODO Auto-generated method stub                                     
        System.out.println(request);
        System.out.println(model);
    }
    
    /* (Not Javadoc)                                         
     * <p>Title: afterCompletion</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param request
     * @param ex
     * @throws Exception                                                                                                                                                                                                                                                                                   
     * @see org.springframework.web.context.request.WebRequestInterceptor#afterCompletion(org.springframework.web.context.request.WebRequest, java.lang.Exception)                                                                                                                                                                                                                                                                      
     */
    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception
    {
        // TODO Auto-generated method stub                                     
        System.out.println(request);
        System.out.println(ex);
    }
    
}
