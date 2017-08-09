/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.test.passport;                                                                                                                                                                                                                                                                       

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.passport.token.app.TokenBO;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-3-31                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class TestToken
{
    @Test
    public void testCreateToken()
    {
        TokenBO tokenBO = ServiceLocatorFactory.getService(TokenBO.class);
        tokenBO.createToken(100000104001L);
    }
    
    @Test
    public void testGetToken()
    {
        String token = "1fd66e64a8eb489ba6ef246dda5414f0";
        TokenBO tokenBO = ServiceLocatorFactory.getService(TokenBO.class);
        System.out.println(tokenBO.getToken(token).getUserId());
    }
}
