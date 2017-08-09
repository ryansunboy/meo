/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.web.common.file.model.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-4
 */
public class FileVO
{
    @NotEmpty
    private String fileName;
    
    @NotEmpty
    private String fileContent;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileContent()
    {
        return fileContent;
    }

    public void setFileContent(String fileContent)
    {
        this.fileContent = fileContent;
    }
    
    
    
}
