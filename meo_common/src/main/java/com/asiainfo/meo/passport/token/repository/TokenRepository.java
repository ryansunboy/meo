package com.asiainfo.meo.passport.token.repository;

import java.util.List;

import com.asiainfo.meo.passport.token.model.entity.Token;

public interface TokenRepository
{
    void saveToken(Token t);
    
    Token getToken(String accessToken);
    
    List<Token> getTokens(long entityId);
    
    List<Token> getUnExpiredAccessTokens(long entityId);
    
    Token getRefreshToken(String refreshToken);
    
    void updateToken(Token t);
}
