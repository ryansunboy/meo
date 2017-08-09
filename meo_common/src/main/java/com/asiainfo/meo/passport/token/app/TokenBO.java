 package com.asiainfo.meo.passport.token.app;                                                                                                                                                                                                                                                                       

import java.util.List;

import com.asiainfo.meo.passport.token.model.entity.Token;
                                                                                                                                                                                                                                                                                              
public interface TokenBO
{
    Token createToken(long entityId);
    
    Token getToken(String accessToken);
    
    Token getValidToken(String accessToken);
    
    Token refreshToken(String refreshToken);
    
    void expiredToken(String accessToken);
    
    List<Token> getUnExpiredAccessTokens(long entityId);
    
    void expiredAccessTokens(long entityId);
}
