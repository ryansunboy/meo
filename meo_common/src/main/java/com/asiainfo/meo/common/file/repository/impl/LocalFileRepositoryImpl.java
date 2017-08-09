/**
 * @Description: (用中文描述一下这个文件)
 * @Description: (English description)
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou
 * @author ruanming@asiainfo.com
 * @date 2015-8-29 下午9:42:34
 * @version 1.0
 */
package com.asiainfo.meo.common.file.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.file.repository.FileRepository;

/**
 * @Description: (这里用一句话描述这个类的作用) 
 * @Description: (English description)                  
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
 * @Date 2015-8-29 下午9:42:34 
 * @version 1.0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
 */
public class LocalFileRepositoryImpl implements FileRepository
{
    private static final Log LOG = LogFactory.getLog(LocalFileRepositoryImpl.class);
    
    private String rootPath;
    /* (Not Javadoc)                                         
     * <p>Title: readFile</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param filePath
     * @return                                                                                                                                                                                                                                                                                   
     * @see com.asiainfo.meo.common.file.repository.FileRepository#readFile(java.lang.String)                                                                                                                                                                                                                                                                      
     */
    @Override
    public InputStream readFile(String filePath)
    {
        // TODO Auto-generated method stub
        if(ValidateUtil.isEmpty(filePath))
        {
            throw new IllegalArgumentException("file path can not be bull");
        }
        String fullpath = getFullPath(filePath);
        File input = new File(fullpath);
        try
        {
            return new FileInputStream(input);
        }
        catch(Throwable t)
        {
            LOG.error(fullpath + "file not found",t);
            throw new MeoException(CommonErrorConstant.NOT_FOUND, t);
        }
    }
    /* (Not Javadoc)                                         
      * <p>Title: writeFile</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param file                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.file.repository.FileRepository#writeFile(byte[])                                                                                                                                                                                                                                                                      
      */
    @Override
    public String writeFile(byte[] contents,String fileName)
    {
        File f = new File(getFullPath(fileName));
        try
        {
            FileCopyUtils.copy(contents, f);
        }
        catch(IOException e)
        {
            throw new MeoException("4049021001", e);
        }
        return fileName;
    }
    
    private String getFullPath(String filePath)
    {
        return this.rootPath + File.separator + filePath;
    }
    /**                                                                  
     * @return the rootPath                                                                                                                                                                                                                                                             
     */
    public String getRootPath()
    {
        return rootPath;
    }
    /**                                                                  
     * @param rootPath the rootPath to set                                                                                                                                                                                                                                              
     */
    public void setRootPath(String rootPath)
    {
        this.rootPath = rootPath;
    }
    
}
