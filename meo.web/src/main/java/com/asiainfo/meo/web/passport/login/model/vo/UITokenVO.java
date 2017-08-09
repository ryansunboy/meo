package com.asiainfo.meo.web.passport.login.model.vo;

public class UITokenVO
{
    private String accessToken;
    private Long accessTokenExpired;
    private String refreshToken;
    private Long refreshTokenExpired;
    private String secretkey;
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
    public String getSecretkey()
    {
        return secretkey;
    }
    public void setSecretkey(String secretkey)
    {
        this.secretkey = secretkey;
    }
    public Long getAccessTokenExpired()
    {
        return accessTokenExpired;
    }
    public void setAccessTokenExpired(Long accessTokenExpired)
    {
        this.accessTokenExpired = accessTokenExpired;
    }
    public Long getRefreshTokenExpired()
    {
        return refreshTokenExpired;
    }
    public void setRefreshTokenExpired(Long refreshTokenExpired)
    {
        this.refreshTokenExpired = refreshTokenExpired;
    }
    
}
