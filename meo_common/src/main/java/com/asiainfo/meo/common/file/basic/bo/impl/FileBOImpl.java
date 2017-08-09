/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.basic.bo.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.FileCopyUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.Base64;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.file.basic.bo.FileBO;
import com.asiainfo.meo.common.file.basic.model.vo.FileBasicVO;
import com.asiainfo.meo.common.file.basic.model.vo.FileItemVO;
import com.asiainfo.meo.common.file.repository.FileRepository;
import com.asiainfo.meo.common.file.util.FileUtil;

/**
 * @Description: 文件上传下载操作类
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public class FileBOImpl implements FileBO
{
    private FileRepository fileRepository;
    
    private String urlPrefix;
    
    /*
     * (Not Javadoc) <p>Title: uploadFile</p> <p>Description: </p>
     * @param fileItemVO
     * @return
     * @see com.asiainfo.meo.common.file.basic.bo.FileBO#uploadFile(com.asiainfo.meo.common.file.basic.model.vo.FileItemVO)
     */
    @Override
    public FileBasicVO uploadFile(FileItemVO fileItem)
    {
        FileBasicVO fileBasic = new FileBasicVO();
        String path = SequenceUtil.getUUIDSequence();
        String suffix = FileUtil.getFileNameSuffix(fileItem.getFileName());
        // 检查是否为文件 TODO
        byte[] contents = Base64.getDecoder().decode(fileItem.getContent());
        
        String fileName = path + "." + suffix;
//        File f = new File(fileName);
//        try
//        {
//            FileCopyUtils.copy(contents, f);
//            fileBasic.setUrl(FileUtil.getFileUrl(fileName));
//        }
//        catch(IOException e)
//        {
//            throw new MeoException("4049021001", e);
//        }
        String filePath = fileRepository.writeFile(contents, fileName);
        fileBasic.setUrl(urlPrefix + filePath);
        fileBasic.setCreateTime(DateTimeUtil.getCurrentTimeMillis());
        fileBasic.setModifyTime(DateTimeUtil.getCurrentTimeMillis());
        fileBasic.setSize(contents.length);
       
        return fileBasic;
        
    }
    
    /*
     * (Not Javadoc) <p>Title: downloadFile</p> <p>Description: </p>
     * @param path
     * @see com.asiainfo.meo.common.file.basic.bo.FileBO#downloadFile(java.lang.String)
     */
    @Override
    public InputStream downloadFile(String path)
    {
        // 
        return fileRepository.readFile(path);
    }

    /**                                                                  
     * @return the fileRepository                                                                                                                                                                                                                                                             
     */
    public FileRepository getFileRepository()
    {
        return fileRepository;
    }

    /**                                                                  
     * @param fileRepository the fileRepository to set                                                                                                                                                                                                                                              
     */
    public void setFileRepository(FileRepository fileRepository)
    {
        this.fileRepository = fileRepository;
    }

    /**                                                                  
     * @return the urlPrefix                                                                                                                                                                                                                                                             
     */
    public String getUrlPrefix()
    {
        return urlPrefix;
    }

    /**                                                                  
     * @param urlPrefix the urlPrefix to set                                                                                                                                                                                                                                              
     */
    public void setUrlPrefix(String urlPrefix)
    {
        this.urlPrefix = urlPrefix;
    }
    
    
}
