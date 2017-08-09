package com.asiainfo.meo.system.user.app.model.entity;

// Generated 2015-4-3 16:25:17 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysUserLogin generated by hbm2java
 */
public class SysPortalLogin implements java.io.Serializable
{

    private static final long   serialVersionUID    = -6144000910688619115L;
    public static final Integer STS_PORTAL_INACTIVATE = 0;
    public static final Integer STS_PORTAL_ACTIVATE   = 1;
    public static final Integer STS_PORTAL_DEACTIVATE = 2;
    public static final Integer IS_FIRST_LOGIN      = 0;
    public static final Integer IS_NOT_FIRST_LOGIN  = 1;
    public static final String SYS_PORTAL_LOGIN_SEQ = "SEQ_SYS_PORTAL_LOGIN_ID";
    
    private long                loginId;
    private long                userId;
    private String              loginAcct;
    private Timestamp           createTime;
    private Timestamp           modifyTime;
    private Integer             isFirstLogin;
    private Integer             sts;
    
    public long getLoginId()
    {
        return this.loginId;
    }
    
    public void setLoginId(long loginId)
    {
        this.loginId = loginId;
    }
    
    public long getUserId()
    {
        return this.userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public String getLoginAcct()
    {
        return this.loginAcct;
    }
    
    public void setLoginAcct(String loginAcct)
    {
        this.loginAcct = loginAcct;
    }
    
    public Timestamp getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }
    
    public Timestamp getModifyTime()
    {
        return this.modifyTime;
    }
    
    public void setModifyTime(Timestamp modifyTime)
    {
        this.modifyTime = modifyTime;
    }
    
    public Integer getIsFirstLogin()
    {
        return this.isFirstLogin;
    }
    
    public void setIsFirstLogin(Integer isFirstLogin)
    {
        this.isFirstLogin = isFirstLogin;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
}