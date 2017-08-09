 package com.asiainfo.meo.passport.token.app.impl;                                                                                                                                                                                                                                                                       

import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.Destroyable;

import org.springframework.beans.factory.InitializingBean;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.RandomUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.StringUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.constant.TokenErrorCodeConstant;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.passport.token.repository.TokenRepository;
                                                                                                                                                                                                                                                                                              
public class TokenBOImpl implements TokenBO
{
    private static final String CACHE_NAME = TokenBOImpl.class.getCanonicalName() + ".token";
    
    private int accessExpired = 1;
    
    private int refreshExpired = 7;
    
    @Resource
    private TokenRepository tokenRepository;
    
    public Token createToken(long entityId)
    {
       Token t = new Token();
       t.setAccessToken(SequenceUtil.getUUIDSequence());
       t.setRefreshToken(SequenceUtil.getUUIDSequence());
       
       long accessTokenExpireTime = DateTimeUtil.getCurrentTimeMillis() + DateTimeUtil.DAY * getAccessExpired();
       t.setAccessTokenExpireTime(accessTokenExpireTime);
       
       long refreshTokenExpireTime = DateTimeUtil.getCurrentTimeMillis() + DateTimeUtil.DAY * getRefreshExpired(); 
       t.setRefreshTokenExpireTime(refreshTokenExpireTime);
       
       t.setSecretKey(RandomUtil.randomDigitLetter(8));
       t.setUserId(entityId);
       t.setCreateTime(DateTimeUtil.getNow());
       
       getTokenRepository().saveToken(t);
       
       return t;
    }
    
    public Token getToken(String accessToken)
    {
        if(StringUtil.isBlank(accessToken))
        {
            return null;
        }
        return getTokenRepository().getToken(accessToken);
    }

    public TokenRepository getTokenRepository()
    {
        return tokenRepository;
    }

    public void setTokenRepository(TokenRepository tokenRepository)
    {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token refreshToken(String refreshToken)
    {
        if(StringUtil.isBlank(refreshToken))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object[]{"refreshToken"});
        }
        Token t = getTokenRepository().getRefreshToken(refreshToken);
        if(t == null)
        {
            throw new MeoException(TokenErrorCodeConstant.TOKEN_INVALID);
        }
        if(t.refreshTokenExpired())
        {
            throw new MeoException(TokenErrorCodeConstant.REFRESH_TOKEN_EXPIRED);
        }
       return createToken(t.getUserId());
    }

    @Override
    public Token getValidToken(String accessToken)
    {
        Token t = getToken(accessToken);
        if(t == null)
        {
            throw new MeoException(TokenErrorCodeConstant.TOKEN_INVALID);
        }
        if(t.accessTokenExpired())
        {
            throw new MeoException(TokenErrorCodeConstant.TOKEN_EXPIRED);
        }
         return t;
    }
    
    public void expiredToken(String accessToken)
    {
        Token t = getToken(accessToken);
        expiredToken(t);
    }
    
    private void expiredToken(Token t)
    {
        if(t != null)
        {
            t.setRefreshTokenExpireTime(DateTimeUtil.getCurrentTimeMillis());
            t.setAccessTokenExpireTime(DateTimeUtil.getCurrentTimeMillis());
            this.tokenRepository.updateToken(t);
        }
    }
    
    public int getAccessExpired()
    {
        return accessExpired;
    }

    public void setAccessExpired(int accessExpired)
    {
        this.accessExpired = accessExpired;
    }

    public int getRefreshExpired()
    {
        return refreshExpired;
    }

    public void setRefreshExpired(int refreshExpired)
    {
        this.refreshExpired = refreshExpired;
    }

    /* (Not Javadoc)                                         
      * <p>Title: getVailiTokens</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param entityId
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.passport.token.app.TokenBO#getVailiTokens(long)                                                                                                                                                                                                                                                                      
      */
    @Override
    public List<Token> getUnExpiredAccessTokens(long entityId)
    {
        return tokenRepository.getUnExpiredAccessTokens(entityId);
    }

    /* (Not Javadoc)                                         
      * <p>Title: expiredToken</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param entityId                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.passport.token.app.TokenBO#expiredToken(long)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void expiredAccessTokens(long entityId)
    {
        List<Token> unExpiredTokens = getUnExpiredAccessTokens(entityId);
        if(ValidateUtil.isNotEmpty(unExpiredTokens))
        {
            for(Token t : unExpiredTokens)
            {
                expiredToken(t);
            }
        }
    }
    
}
