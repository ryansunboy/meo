/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.file.photo.constant;                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                              
 /** @Description: 图片的后缀                  
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author ruanming@asiainfo.com                                                                                                                                                                                                                                                                           
 * @Date 2015-5-11                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public enum ImageType
{
    
    JPG("jpg"),
    
    PNG("png"),
    
    GIF("gif"),
    
    JPEG ("jpeg");
    
    private final String type;
    
    private ImageType(String type)
    {
        this.type = type;
    }
    
    public String value()
    {
        return this.type;
    }
}
