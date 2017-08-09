package com.asiainfo.meo.common.core.exception;

import org.springframework.validation.ObjectError;

import com.asiainfo.meo.common.core.utils.MessageUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class MeoException extends RuntimeException
{
    private static final long serialVersionUID = 2810898889385405121L;
    
    private String errorCode;
    
    private Object[] params = new Object[0];
    
    private static final int HTTP_PREFIX_INDEX = 3;
    
    private String errorMessage;
    
    public MeoException(String errorCode)
    {
        this(errorCode, null, null);
    }
    
    public MeoException(String errorCode, Object[] params)
    {
        this(errorCode, params, null);
    }
    
    public MeoException(String errorCode, Throwable t)
    {
        this(errorCode, null, t);
    }
    
    public MeoException(String errorCode, Object[] params, Throwable t)
    {
        super(t);
        this.errorCode = errorCode;
        
        if (ValidateUtil.isNotEmpty(params))
        {
            this.params = params;
        }
        
    }
    
    public String getHttpCode()
    {
        return this.errorCode.substring(0, HTTP_PREFIX_INDEX);
    }
    
    public String getErrorCode()
    {
        return this.errorCode.substring(HTTP_PREFIX_INDEX);
    }
    
    public String getOriErrorCode()
    {
        return this.errorCode;
    }
    
    public String getErrorMessage()
    {
        if (this.errorMessage == null)
        {
            this.errorMessage = MessageUtil.getMessage(this.errorCode, this.params,this.errorCode, null);
        }
        return this.errorMessage;
    }
    
    public String getErrorMessage(String defaultMessage)
    {
        return MessageUtil.getMessage(this.errorCode, this.params, defaultMessage, null);
    }
    
    @Override
    public String getMessage()
    {
        StringBuilder sb = new StringBuilder("error code [").append(this.errorCode).append("],params [");
        for (Object o : params)
        {
            sb.append(o).append(",");
        }
        sb.append("],error message [").append(getErrorMessage()).append("]");
        return sb.toString();
    }
}
