/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.basic.bo;

import java.io.InputStream;

import com.asiainfo.meo.common.file.basic.model.vo.FileBasicVO;
import com.asiainfo.meo.common.file.basic.model.vo.FileItemVO;

/**
 * @Description: 文件服务
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public interface FileBO
{
    FileBasicVO uploadFile(FileItemVO fileItemVO);
    
    InputStream downloadFile(String path);
}
