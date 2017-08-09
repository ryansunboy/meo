package com.asiainfo.meo.passport.token.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.passport.token.repository.TokenRepository;

public class TokenRepositoryImpl implements TokenRepository
{
    private static final String SEQ_TOKEN_ID = "SEQ_TOKEN_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    @Override
    public void saveToken(Token t)
    {
        long tokenId = SequenceUtil.getSequence(SEQ_TOKEN_ID);
        t.setTokenId(tokenId);
        getHibernateRepository().saveObject(t);
    }
    
    @Override
    public Token getToken(String accessToken)
    {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("accessToken", accessToken);
        List<Token> tokens = getHibernateRepository().findByParams(Token.class, params); //TODO
        if(ValidateUtil.isNotEmpty(tokens))
        {
            return tokens.get(0);
        }
        return null;
    }
    
    public HibernateRepository getHibernateRepository()
    {
        return hibernateRepository;
    }
    
    public void setHibernateRepository(HibernateRepository hibernateRepository)
    {
        this.hibernateRepository = hibernateRepository;
    }

    /* (Not Javadoc)                                         
      * <p>Title: updateToken</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param t                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.passport.token.repository.TokenRepository#updateToken(com.asiainfo.meo.passport.token.model.entity.Token)                                                                                                                                                                                                                                                                      
      */
    @Override
    public void updateToken(Token t)
    {
        this.hibernateRepository.updateObject(t);
    }

    /* (Not Javadoc)                                         
      * <p>Title: getRefreshToken</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param refreshToken
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.passport.token.repository.TokenRepository#getRefreshToken(java.lang.String)                                                                                                                                                                                                                                                                      
      */
    @Override
    public Token getRefreshToken(String refreshToken)
    {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("refreshToken", refreshToken);
        List<Token> tokens = getHibernateRepository().findByParams(Token.class, params); //TODO
        if(ValidateUtil.isNotEmpty(tokens))
        {
            return tokens.get(0);
        }
        return null;
    }

    /* (Not Javadoc)                                         
      * <p>Title: getTokens</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param entityId
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.passport.token.repository.TokenRepository#getTokens(long)                                                                                                                                                                                                                                                                      
      */
    @Override
    public List<Token> getTokens(long entityId)
    {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("userId", entityId);
        List<Token> tokens = getHibernateRepository().findByParams(Token.class, params); //TODO
        return tokens;
    }

    /* (Not Javadoc)                                         
      * <p>Title: getValidTokens</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param entityId
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.passport.token.repository.TokenRepository#getValidTokens(long)                                                                                                                                                                                                                                                                      
      */
    @Override
    public List<Token> getUnExpiredAccessTokens(long entityId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Token.class);
        criteria.add(Restrictions.eq("userId", entityId));
        //TOKEN的失效时间 >= 系统时间，说明当前TOKEN未失效
        criteria.add(Restrictions.ge("accessTokenExpireTime", DateTimeUtil.getCurrentTimeMillis()));
        return (List<Token>) getHibernateRepository().findByCriteria(criteria);
    }

}
