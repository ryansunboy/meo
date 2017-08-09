/**
 * @Description: (用中文描述一下这个文件)
 * @Description: (English description)
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou
 * @author ruanming@asiainfo.com
 * @date 2015-8-29 下午10:31:04
 * @version 1.0
 */
package com.asiainfo.meo.common.file.repository.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.util.FileCopyUtils;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.file.repository.FileRepository;

/**
 * @Description: (这里用一句话描述这个类的作用) 
 * @Description: (English description)                  
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
 * @Date 2015-8-29 下午10:31:04 
 * @version 1.0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
 */
public class HDFSRepositoryImpl implements FileRepository
{
    private static final Log LOG = LogFactory.getLog(HDFSRepositoryImpl.class);
    
    private Configuration  conf = new Configuration();
    
    private String hdfsPrefix;
    
    private boolean overwrite = true;
    
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
        String dst = createHdfsPath(filePath);
        FileSystem fs = null;
        try
        {
            fs = FileSystem.get(URI.create(dst),conf);
            FSDataInputStream hdfsInStream = fs.open(new Path(dst));
            return new ByteArrayInputStream(FileCopyUtils.copyToByteArray(hdfsInStream));
        }
        catch(Throwable t)
        {
            LOG.error("read file exception",t);
            throw new MeoException(CommonErrorConstant.NOT_FOUND);
        }
        finally
        {
           try
           {
                fs.close();
           }
           catch(IOException e)
           {
           } 
        }
    }
    
    /* (Not Javadoc)                                         
     * <p>Title: writeFile</p>                                                                                                                                                                                                                                             
     * <p>Description: </p>                                                                                                                                                                                                                                                          
     * @param file
     * @param filePath
     * @return                                                                                                                                                                                                                                                                                   
     * @see com.asiainfo.meo.common.file.repository.FileRepository#writeFile(byte[], java.lang.String)                                                                                                                                                                                                                                                                      
     */
    @Override
    public String writeFile(byte[] file, String filePath)
    {
        String dst = createHdfsPath(filePath);
        FileSystem fs = null;
        try
        {
            fs = FileSystem.get(URI.create(dst),conf);
            OutputStream os = fs.create(new Path(dst), overwrite);
            FileCopyUtils.copy(file, os);
            
            return filePath;
        }
        catch(Throwable t)
        {
            LOG.error("write file exception",t);
            throw new MeoException(CommonErrorConstant.SYSTEM_ERROR);
        }
        finally
        {
           try
           {
                fs.close();
           }
           catch(IOException e)
           {
           } 
        }
    }
    
    private String createHdfsPath(String filePath)
    {
        return  hdfsPrefix + filePath;
    }

    /**                                                                  
     * @return the hdfsPrefix                                                                                                                                                                                                                                                             
     */
    public String getHdfsPrefix()
    {
        return hdfsPrefix;
    }

    /**                                                                  
     * @param hdfsPrefix the hdfsPrefix to set                                                                                                                                                                                                                                              
     */
    public void setHdfsPrefix(String hdfsPrefix)
    {
        this.hdfsPrefix = hdfsPrefix;
    }
    
    
}
