/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.photo.avator.bo.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.Base64;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.file.photo.avator.bo.AvatorBO;
import com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorItemVO;
import com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorVO;
import com.asiainfo.meo.common.file.repository.FileRepository;
import com.asiainfo.meo.common.file.util.FileUtil;

/**
 * @Description:上传头像，并返回30，50，100的缩略图
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public class AvatorBOImpl implements AvatorBO
{
    private static final Log LOG = LogFactory.getLog(AvatorBOImpl.class);
    
    private static final int SIZE30 = 30;
    
    private static final String SIZE30_30 = "_30_30";
    
    private static final int SIZE50 = 50;
    
    private static final String SIZE50_50 = "_50_50";
    
    private static final int SIZE100 = 100;
    
    private static final String SIZE100_100 = "_100_100";
    
    private Map<Integer, String> thumbnails = new HashMap<Integer, String>(3);
    
    private static final String DOT = ".";
    
    private FileRepository fileRepository;
    
    private String avatorUrlPrefix;
    
    public AvatorBOImpl()
    {
        thumbnails.put(SIZE30, SIZE30_30);
        thumbnails.put(SIZE50, SIZE50_50);
        thumbnails.put(SIZE100, SIZE100_100);
    }
    
    /*
     * (Not Javadoc) <p>Title: uploadAvator</p> <p>Description: </p>
     * @param avator
     * @return
     * @see
     * com.asiainfo.meo.common.file.photo.avator.bo.AvatorBO#uploadAvator(com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorVO
     * )
     */
    @Override
    public AvatorVO uploadAvator(AvatorItemVO avatorItem)
    {
        AvatorVO avator = new AvatorVO();
        String path = SequenceUtil.getUUIDSequence();
        String suffix = FileUtil.getFileNameSuffix(avatorItem.getFileName());
        // 检查是否为图片 TODO
        byte[] contents = Base64.getDecoder().decode(avatorItem.getContent());
        try
        {
            
            String fileName = path + "." + suffix;
            fileRepository.writeFile(contents, fileName);
            avator.setUrl(getAvatorUrl(fileName));
            
            String file30 = getThumbNailFileName(SIZE30, path, suffix);
            generateThumbNailAndSaveFile(contents,SIZE30, file30);
            avator.setUrl30(getAvatorUrl(file30));
            
            String file50 = getThumbNailFileName(SIZE50, path, suffix);
            generateThumbNailAndSaveFile(contents,SIZE50, file50);
            avator.setUrl50(getAvatorUrl(file50));
            
            String file100 = getThumbNailFileName(SIZE100, path, suffix);
            generateThumbNailAndSaveFile(contents,SIZE100, file100);
            avator.setUrl100(getAvatorUrl(file100));
            
            return avator;
        }
        catch(Throwable t)
        {
            throw new MeoException("4049021001", t);
        }
    }
    
    /*
     * (Not Javadoc) <p>Title: downloadAvator</p> <p>Description: </p>
     * @param path
     * @return
     * @see com.asiainfo.meo.common.file.photo.avator.bo.AvatorBO#downloadAvator(java.lang.String)
     */
    @Override
    public InputStream downloadAvator(String path)
    {
        // TODO Auto-generated method stub
        return fileRepository.readFile(path);
    }
    
    private void generateThumbNailAndSaveFile(byte[] contents, int size, String thumbnailFileName)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        generateThumbnail(size, size, thumbnailFileName, new ByteArrayInputStream(contents), baos);
        fileRepository.writeFile(baos.toByteArray(), thumbnailFileName);
    }
    
    private String getThumbNailFileName(int size, String path, String suffix)
    {
        return path + thumbnails.get(size) + DOT + suffix;
    }
    
    private String getAvatorUrl(String fileName)
    {
        return this.avatorUrlPrefix + fileName;
    }
    
    private void generateThumbnail(int height, int width, String fileName, InputStream is, OutputStream os)
    {
        try
        {
            Thumbnails.of(is).size(height, width).toOutputStream(os);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
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
     * @return the avatorUrlPrefix
     */
    public String getAvatorUrlPrefix()
    {
        return avatorUrlPrefix;
    }
    
    /**
     * @param avatorUrlPrefix the avatorUrlPrefix to set
     */
    public void setAvatorUrlPrefix(String avatorUrlPrefix)
    {
        this.avatorUrlPrefix = avatorUrlPrefix;
    }
    
}
