package com.asiainfo.meo.web.core.annotation.version;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.asiainfo.meo.web.core.VersionInfo;


//@Target({ElementType.METHOD, ElementType.TYPE })
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Version
{
    public int minor() default VersionInfo.minorVersion;
    
    public int major() default VersionInfo.majorVersion;
}
