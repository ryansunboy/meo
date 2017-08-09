/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;

/**
 * @Description: 图片工具类
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public final class FileUtil
{
    public static final String WEB_PREFIX = "";
    
    public static boolean isImage(String suffix)
    {
        return false;
    }
    
//    public static String getAvatorUrl(String fileName)
//    {
//        return WEB_PREFIX + "/meo/rest/public/avatar/uploaded/" + fileName;
//    }
//    
//    public static String getFileUrl(String fileName)
//    {
//        return WEB_PREFIX + "/meo/rest/public/file/uploaded/" + fileName;
//    }
    
    public static String getFileNameSuffix(String fileName)
    {
        return StringUtils.substringAfterLast(fileName, ".");
    }
    
//    public static InputStream getFileContent(String filePath)
//    {
//        File input = new File(filePath);
//        try
//        {
//            return new FileInputStream(input);
//        }
//        catch(Throwable t)
//        {
//            throw new MeoException(CommonErrorConstant.NOT_FOUND, t);
//        }
//    }
    
}
