package com.asiainfo.meo.system.service.entity.vo;

public class SysPasswordVO
{
    private String loginAcct;
    private Integer userType;
    private String newPwd;
    
    
    
    
    public String getLoginAcct()
    {
        return loginAcct;
    }
    public void setLoginAcct(String loginAcct)
    {
        this.loginAcct = loginAcct;
    }
  
   
    public Integer getUserType()
    {
        return userType;
    }
    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }
   
    public String getNewPwd()
    {
        return newPwd;
    }
    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }
    
}
