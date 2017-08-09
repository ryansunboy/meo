/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.basic.model.vo;

/**
 * @Description: 文件的基础信息
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public class FileBasicVO
{
    private String url;
    
    private long size;
    
    private long createTime;
    
    private long modifyTime;
    
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public long getSize()
    {
        return size;
    }
    
    public void setSize(long size)
    {
        this.size = size;
    }
    
    public long getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(long createTime)
    {
        this.createTime = createTime;
    }
    
    public long getModifyTime()
    {
        return modifyTime;
    }
    
    public void setModifyTime(long modifyTime)
    {
        this.modifyTime = modifyTime;
    }
    
}
