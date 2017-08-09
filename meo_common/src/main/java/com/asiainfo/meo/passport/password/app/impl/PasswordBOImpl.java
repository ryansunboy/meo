/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.passport.password.app.impl;                                                                                                                                                                                                                                                                       

import com.asiainfo.meo.common.core.utils.RandomUtil;
import com.asiainfo.meo.passport.password.app.PasswordBO;
                                                                                                                                                                                                                                                                                              
public class PasswordBOImpl implements PasswordBO
{

    /* (Not Javadoc)                                         
      * <p>Title: getOtp</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
      * @see com.asiainfo.meo.passport.password.app.PasswordBO#getOtp()                                                                                                                                                                                                                                                                      
      */
    @Override
    public void getOtp(String msisdn)
    {
        // TODO Auto-generated method stub                                     
        String otp = RandomUtil.randomLetter(4); 
        
    }

    /* (Not Javadoc)                                         
      * <p>Title: resetPassword</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
      * @see com.asiainfo.meo.passport.password.app.PasswordBO#resetPassword()                                                                                                                                                                                                                                                                      
      */
    @Override
    public void resetPassword()
    {
        // TODO Auto-generated method stub                                     
         
    }
    
}
