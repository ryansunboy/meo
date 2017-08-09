/**
 * @Description: (用中文描述一下这个文件)
 * @Description: (English description)
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou
 * @author ruanming@asiainfo.com
 * @date 2015-8-29 下午9:39:35
 * @version 1.0
 */
package com.asiainfo.meo.common.file.repository;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description: (这里用一句话描述这个类的作用) 
 * @Description: (English description)                  
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
 * @Date 2015-8-29 下午9:39:35 
 * @version 1.0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
 */
public interface FileRepository
{
   InputStream readFile(String filePath);
   
   String writeFile(byte[] file,String filePath);
}
