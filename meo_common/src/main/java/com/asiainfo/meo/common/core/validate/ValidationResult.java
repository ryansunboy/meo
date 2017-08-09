/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.validate;                                                                                                                                                                                                                                                                       

import java.util.Map;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-3-10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class ValidationResult
{
    private boolean hasErrors;
    
    private Map<String,String> errorMsg;

    public boolean isHasErrors()
    {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors)
    {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg)
    {
        this.errorMsg = errorMsg;
    }
    
}
