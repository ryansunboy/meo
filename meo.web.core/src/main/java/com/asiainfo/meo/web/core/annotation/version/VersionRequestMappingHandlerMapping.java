package com.asiainfo.meo.web.core.annotation.version;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping
{
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType)
    {
        Version typeAnnotation = AnnotationUtils.findAnnotation(handlerType, Version.class);
        return createVersionRequestCondition(typeAnnotation);
    }
    
    protected RequestCondition<?> getCustomMethodCondition(Method method)
    {
        Version methodAnnotation = AnnotationUtils.findAnnotation(method, Version.class);
        return createVersionRequestCondition(methodAnnotation);
    }
    
    private VersionRequestCondition createVersionRequestCondition(Version versionAnnotation)
    {
        if(versionAnnotation != null)
        {
            return new VersionRequestCondition(versionAnnotation.minor(),versionAnnotation.major());
        }
        return null;
    }
    
}
