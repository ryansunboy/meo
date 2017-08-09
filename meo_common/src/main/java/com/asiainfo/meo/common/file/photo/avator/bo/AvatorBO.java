/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.photo.avator.bo;

import java.io.InputStream;

import com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorItemVO;
import com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorVO;

/**
 * @Description: 头像操作类BO
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public interface AvatorBO
{
    AvatorVO uploadAvator(AvatorItemVO avator);
    
    InputStream downloadAvator(String path);
}
