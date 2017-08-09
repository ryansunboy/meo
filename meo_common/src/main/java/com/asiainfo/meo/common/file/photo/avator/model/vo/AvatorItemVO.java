/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.photo.avator.model.vo;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Description: 头像上传VO
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public class AvatorItemVO
{
    @Valid
    @NotEmpty
    private String fileName;
    
    @Valid
    @NotEmpty
    private String content;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
    
    
}
