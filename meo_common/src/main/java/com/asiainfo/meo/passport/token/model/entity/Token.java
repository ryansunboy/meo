package com.asiainfo.meo.passport.token.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;

public class Token implements Serializable
{
    private static final long serialVersionUID = 300651969584227480L;
    
    private long tokenId;
    
    private String accessToken;
    
    private String refreshToken;
    
    private long accessTokenExpireTime;
    
    private long refreshTokenExpireTime;
    
    private String secretKey;
    
    private long userId;
    
    private Timestamp createTime;
    
    public boolean accessTokenExpired()
    {
        return DateTimeUtil.getCurrentTimeMillis() - accessTokenExpireTime >= 0;
    }
    
    public boolean refreshTokenExpired()
    {
        return DateTimeUtil.getCurrentTimeMillis() - refreshTokenExpireTime >= 0;
    }
    
    public String getAccessToken()
    {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
    public String getRefreshToken()
    {
        return refreshToken;
    }
    
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }
    
    public long getAccessTokenExpireTime()
    {
        return accessTokenExpireTime;
    }
    
    public void setAccessTokenExpireTime(long accessTokenExpireTime)
    {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }
    
    public long getRefreshTokenExpireTime()
    {
        return refreshTokenExpireTime;
    }
    
    public void setRefreshTokenExpireTime(long refreshTokenExpireTime)
    {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }
    
    public String getSecretKey()
    {
        return secretKey;
    }
    
    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }
    
    public long getTokenId()
    {
        return tokenId;
    }
    
    public void setTokenId(long tokenId)
    {
        this.tokenId = tokenId;
    }
    
    public long getUserId()
    {
        return userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public Timestamp getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }
}
