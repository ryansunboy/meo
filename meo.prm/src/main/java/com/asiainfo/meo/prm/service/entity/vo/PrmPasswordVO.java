package com.asiainfo.meo.prm.service.entity.vo;

public class PrmPasswordVO
{
    private String loginAcct;
    private String oldPwd;
    private String newPwd;
    public String getLoginAcct()
    {
        return loginAcct;
    }
    public void setLoginAcct(String loginAcct)
    {
        this.loginAcct = loginAcct;
    }    
    public String getNewPwd()
    {
        return newPwd;
    }
    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }
    public String getOldPwd()
    {
        return oldPwd;
    }
    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }
    
}
