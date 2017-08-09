package com.asiainfo.meo.web.core.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.asiainfo.meo.web.core.annotation.version.VersionRequestMappingHandlerMapping;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class WebConfig extends DelegatingWebMvcConfiguration
{
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping()
    {
        RequestMappingHandlerMapping handlerMapping = new VersionRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());
        
        PathMatchConfigurer configurer = getPathMatchConfigurer();
        if (configurer.isUseSuffixPatternMatch() != null)
        {
            handlerMapping.setUseSuffixPatternMatch(configurer.isUseSuffixPatternMatch());
        }
        if (configurer.isUseRegisteredSuffixPatternMatch() != null)
        {
            handlerMapping.setUseRegisteredSuffixPatternMatch(configurer.isUseRegisteredSuffixPatternMatch());
        }
        if (configurer.isUseTrailingSlashMatch() != null)
        {
            handlerMapping.setUseTrailingSlashMatch(configurer.isUseTrailingSlashMatch());
        }
        if (configurer.getPathMatcher() != null)
        {
            handlerMapping.setPathMatcher(configurer.getPathMatcher());
        }
        if (configurer.getUrlPathHelper() != null)
        {
            handlerMapping.setUrlPathHelper(configurer.getUrlPathHelper());
        }
        
        return handlerMapping;
    }
    
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) 
    {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        // 忽略为null的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略JSON对象中存在JAVA对象中不存在的属性
        objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, false);
        // 忽略空对象
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
