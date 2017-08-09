/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.web.common.file.model.vo;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public class ImageInfoVO extends FileInfoVO
{
    private String largeUrl;
    
    private String smallUrl;

    public String getLargeUrl()
    {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl)
    {
        this.largeUrl = largeUrl;
    }

    public String getSmallUrl()
    {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl)
    {
        this.smallUrl = smallUrl;
    }
    
    
}
