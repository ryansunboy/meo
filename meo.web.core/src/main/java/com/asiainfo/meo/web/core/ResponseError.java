/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.web.core;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-4-9
 */
public class ResponseError
{
    
    private String errCode;
    
    private String errMsg;
    
    public String getErrCode()
    {
        return errCode;
    }
    
    public void setErrCode(String errCode)
    {
        this.errCode = errCode;
    }
    
    public String getErrMsg()
    {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }
    
}
