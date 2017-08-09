package com.asiainfo.meo.system.user.app.model.vo;

public class UserLoginVO
{
    public static final Integer OBJECT_TYPE_CUSTOMER = 0;
    
    public static final Integer OBJECT_TYPE_PARTNER  = 1;
    
    // public static final Integer OBJECT_TYPE_SALER=2;
    public static final Integer OBJECT_TYPE_SYSTEM   = 2;
    
    private String              loginAcct;
    
    private String              loginPwd;
    
    private Integer             objectType;
    
    private String              curLoginIp;
    
    private String              lastLoginIp;
    
    private Integer             loginType;
    
    private Long                objectId;
    
    private Integer             isFirstLogin;
    
    public Integer getIsFirstLogin()
    {
        return isFirstLogin;
    }
    
    public void setIsFirstLogin(Integer isFirstLogin)
    {
        this.isFirstLogin = isFirstLogin;
    }
    
    public String getLoginAcct()
    {
        return loginAcct;
    }
    
    public void setLoginAcct(String loginAcct)
    {
        this.loginAcct = loginAcct;
    }
    
    public String getLoginPwd()
    {
        return loginPwd;
    }
    
    public void setLoginPwd(String loginPwd)
    {
        this.loginPwd = loginPwd;
    }
    
    public String getCurLoginIp()
    {
        return curLoginIp;
    }
    
    public void setCurLoginIp(String curLoginIp)
    {
        this.curLoginIp = curLoginIp;
    }
    
    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
    
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }
    
    public Integer getLoginType()
    {
        return loginType;
    }
    
    public void setLoginType(Integer loginType)
    {
        this.loginType = loginType;
    }
    
    public Integer getObjectType()
    {
        return objectType;
    }
    
    public void setObjectType(Integer objectType)
    {
        this.objectType = objectType;
    }
    
    public Long getObjectId()
    {
        return objectId;
    }
    
    public void setObjectId(Long objectId)
    {
        this.objectId = objectId;
    }
    
}
