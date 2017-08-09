package com.asiainfo.meo.web.core.advice.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.core.ResponseError;
import com.asiainfo.meo.web.core.constant.WebErrorCodeConstant;

@ControllerAdvice
public class ExceptionControllerAdvice
{
    private static final Log LOG = LogFactory.getLog(ExceptionControllerAdvice.class);
    
    private static final String NOT_EMPTY = "NotEmpty";
    
    public static final String NOT_NULL = "NotNull";
    
    @ExceptionHandler(Throwable.class)
    public @ResponseBody
    ResponseEntity<Message<?>> handleAllException(Throwable t)
    {
        try
        {
            MeoException mex = null;
            if (t instanceof MeoException)
            {
                mex = (MeoException) t;
            }
            else
            {
                mex = transferException(t);
            }
            return handleMeoException(mex);
        }
        catch(Throwable ingore)
        {
            LOG.error("transfer exception error", ingore);
            MeoException serverException = new MeoException(CommonErrorConstant.SYSTEM_ERROR, ingore);
            return handleMeoException(serverException);
        }
        
    }
    
    private ResponseEntity<Message<?>> handleMeoException(MeoException ex)
    {
        LOG.error(BoContext.getBoContext(), ex);
        
        Message<?> m = new Message();
        ResponseError error = new ResponseError();
        error.setErrCode(ex.getErrorCode());
        error.setErrMsg(ex.getErrorMessage());
        m.setError(error);
        
        String httpStatusCode = ex.getHttpCode();
        ResponseEntity<Message<?>> body = new ResponseEntity<Message<?>>(m, HttpStatus.valueOf(Integer.valueOf(httpStatusCode)));
        return body;
        
    }
    
    private MeoException transferException(Throwable t)
    {
        if (t instanceof MethodArgumentNotValidException)
        {
            return handleMethodArgumentNotValidException((MethodArgumentNotValidException) t);
        }
        else if (t instanceof UnsatisfiedServletRequestParameterException)
        {
            return handleUnsatisfiedServletRequestParameterException((UnsatisfiedServletRequestParameterException) t);
        }
        else if (t instanceof HttpMessageNotReadableException)
        {
            return handleHttpMessageNotReadableException((HttpMessageNotReadableException) t);
        }
        else if (t instanceof MissingServletRequestParameterException)
        {
            return handleMissingServletRequestParameterException((MissingServletRequestParameterException) t);
        }
        else if (t instanceof HttpRequestMethodNotSupportedException)
        {
            return handleHttpRequestMethodNotSupportedException((HttpRequestMethodNotSupportedException) t);
        }
        else if (t instanceof TypeMismatchException)
        {
            return handleTypeMismatchException((TypeMismatchException) t);
        }
        else
        {
            MeoException mex = new MeoException(CommonErrorConstant.SYSTEM_ERROR, t);
            return mex;
        }
    }
    
    private MeoException handleMissingServletRequestParameterException(
            MissingServletRequestParameterException missingServletRequestParameterException)
    {
        String propertyName = missingServletRequestParameterException.getParameterName();
        MeoException mex = new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{propertyName },
                missingServletRequestParameterException);
        return mex;
    }
    
    private MeoException handleHttpMessageNotReadableException(HttpMessageNotReadableException t)
    {
        MeoException mex = new MeoException(WebErrorCodeConstant.MESSAGE_NOT_READABLE, t);
        return mex;
    }
    
    private MeoException handleUnsatisfiedServletRequestParameterException(
            UnsatisfiedServletRequestParameterException unsatisfiedServletRequestParameterException)
    {
        MeoException mex = new MeoException(WebErrorCodeConstant.UNSATISFIED_REQUEST, unsatisfiedServletRequestParameterException);
        return mex;
    }
    
    private MeoException handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException httpMethodNotSupportedException)
    {
        MeoException mex = new MeoException(WebErrorCodeConstant.NOT_SUPPORT_GET_METHOD, httpMethodNotSupportedException);
        return mex;
    }
    
    private MeoException handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        
        FieldError fieldError = bindingResult.getFieldError();
        String annotaionCode = fieldError.getCode();
        String propertyName = fieldError.getField();
        
        MeoException mex = null;
        if(NOT_EMPTY.equals(annotaionCode) || NOT_NULL.equals(annotaionCode))
        {
            mex = new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{propertyName },
                    methodArgumentNotValidException);
        }
        else
        {
             mex = new MeoException(fieldError.getDefaultMessage(), new Object[]{propertyName },
            methodArgumentNotValidException);
        }
        return mex;
    }
    
    private MeoException handleTypeMismatchException(TypeMismatchException typeMismatchException)
    {
        String propertyName = typeMismatchException.getPropertyName();
        MeoException mex = new MeoException(CommonErrorConstant.TYPE_MISMATCH, new Object[]{propertyName }, typeMismatchException);
        return mex;
    }
}
