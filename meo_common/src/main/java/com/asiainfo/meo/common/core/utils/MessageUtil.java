/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.core.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.asiainfo.meo.common.core.resource.PathMatchResourceBundleMessageSource;

/**
 * @Description: 获取属性消息工具类
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-27
 */
public final class MessageUtil
{
    private MessageUtil()
    {}
    
    public static  String getMessage(String code,Object[] args, Locale locale)
    {
        MessageSource messageSource = ServiceLocatorFactory.getService(PathMatchResourceBundleMessageSource.class);
        return messageSource.getMessage(code, args, locale);
    }
    
    public static  String getMessage(String code,Object[] args,String defaultMessage,Locale locale)
    {
        MessageSource messageSource = ServiceLocatorFactory.getService(PathMatchResourceBundleMessageSource.class);
        return messageSource.getMessage(code, args,defaultMessage, locale);
    }
}
