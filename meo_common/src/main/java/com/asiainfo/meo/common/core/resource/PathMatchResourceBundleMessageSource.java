/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.core.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

/**
 * @Description: 读取错误类的配置文件
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-27
 */
public class PathMatchResourceBundleMessageSource extends ReloadableResourceBundleMessageSource
{
    private static Log LOG = LogFactory.getLog(PathMatchResourceBundleMessageSource.class);
    
    // wildcards
    private PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
    
    private static final String PROPERTIES_SUFFIX = ".properties";
    
    private static final String CLASSES_PREFIX = "/classes";
    
    private static final String JAR_PREFIX = ".jar!/";
    
    private static final String MESSAGE_SUFFIX = "message";
    
    @Override
    public void setBasenames(String... basenames)
    {
        List<String> baseNames = new ArrayList<String>();
        if (basenames != null)
        {
            for (int i = 0; i < basenames.length; i++ )
            {
                String basename = basenames[i];
                Assert.hasText(basename, "Basename must not be empty");
                try
                {
                    Resource[] resources = patternResolver.getResources(basename.trim());
                    for (Resource resource : resources)
                    {
                        String uri = resource.getURI().toString();
                        String baseName = null;
                        if (resource instanceof FileSystemResource)
                        {
                            baseName = ResourceUtils.CLASSPATH_URL_PREFIX
                                    + StringUtils.substringBetween(uri, CLASSES_PREFIX, PROPERTIES_SUFFIX);
                        }
                        else if (resource instanceof ClassPathResource)
                        {
                            baseName = StringUtils.substringBefore(uri, PROPERTIES_SUFFIX);
                        }
                        else if (resource instanceof UrlResource)
                        {
                            baseName = ResourceUtils.CLASSPATH_URL_PREFIX
                                    + StringUtils.substringBetween(uri, JAR_PREFIX, PROPERTIES_SUFFIX);
                        }
                        if (baseName != null)
                        {
                            String resloveName = resolveBaseName(baseName);
                            if(resloveName != null)
                            {
                                baseNames.add(resloveName);
                            }
                        }
                        
                        if (LOG.isInfoEnabled())
                        {
                            LOG.info("Message source files found for basename " + basename + ".");
                        }
                    }
                }
                catch(IOException e)
                {
                    LOG.info("No message source files found for basename " + basename + ".");
                }
                
            }
        }
        super.setBasenames(baseNames.toArray(new String[baseNames.size()]));
    }
    
    private String resolveBaseName(String name)
    {
        if(name == null)
        {
            return null;
        }
        String withOutMessageSuffix = StringUtils.substringBeforeLast(name, MESSAGE_SUFFIX);
        return withOutMessageSuffix + MESSAGE_SUFFIX;
    }
}
