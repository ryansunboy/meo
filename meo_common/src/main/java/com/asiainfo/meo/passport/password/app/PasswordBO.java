package com.asiainfo.meo.passport.password.app;                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                              
public interface PasswordBO
{
    void getOtp(String msisdn);
    
    void resetPassword();
}
