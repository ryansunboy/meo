/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.asiainfo.meo.common.core.exception.MeoException;

/**
 * @Description: 实体BEAN校验类
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author AI
 * @Date 2015-3-9
 */
public final class BeanValidatorUtil
{
    private BeanValidatorUtil()
    {
    }
    
    public static <T> void validateObject(T t, Class<?>... group)
    {
        Validator validator = ServiceLocatorFactory.getService(Validator.class);
        
        Set<ConstraintViolation<T>> violations = validator.validate(t, group);
        if (ValidateUtil.isNotEmpty(violations))
        {
            Map<String, String> errors = new HashMap<String, String>(violations.size());
            for (ConstraintViolation<T> violation : violations)
            {
                String messageId = violation.getMessage();
                String propertyName = violation.getPropertyPath().toString();
                errors.put(messageId, propertyName);
                
                throw new MeoException(messageId, new Object[]{propertyName});
            }
        }
    }
    
    public static <T> void validateProperty(T object, String propertyName, Class<?>... groups)
    {
        Validator validator = ServiceLocatorFactory.getService(Validator.class);
        Set<ConstraintViolation<T>> violations = validator.validateProperty(object, propertyName, groups);
        if (ValidateUtil.isNotEmpty(violations))
        {
            Map<String, String> errors = new HashMap<String, String>(violations.size());
            for (ConstraintViolation<T> violation : violations)
            {
                String messageId = violation.getMessage();
                errors.put(messageId, propertyName);
                
                throw new MeoException(messageId,new Object[]{propertyName});
            }
        }
    }
    
}
