package com.asiainfo.meo.web.passport.password.model.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class UIPasswordVO
{
    @NotEmpty
    private String oldPwd;
    @NotEmpty
    @Length(min=8,max=25,message=SysErrorCodeDefine.LOGIN_PASSWORD_FORMAT_ERROR)
    private String newPwd;
    
    
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
