/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.file.photo.avator.model.vo;

import com.asiainfo.meo.common.file.basic.model.vo.FileBasicVO;

/**
 * @Description: 头像基本信息
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
public class AvatorVO extends FileBasicVO
{
    private String url30;
    
    private String url50;
    
    private String url100;
    
    public String getUrl30()
    {
        return url30;
    }
    
    public void setUrl30(String url30)
    {
        this.url30 = url30;
    }
    
    public String getUrl50()
    {
        return url50;
    }
    
    public void setUrl50(String url50)
    {
        this.url50 = url50;
    }
    
    public String getUrl100()
    {
        return url100;
    }
    
    public void setUrl100(String url100)
    {
        this.url100 = url100;
    }
    
}
