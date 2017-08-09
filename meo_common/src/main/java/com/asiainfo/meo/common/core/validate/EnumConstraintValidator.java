/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.validate;                                                                                                                                                                                                                                                                       

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.asiainfo.meo.common.core.validate.annotation.Enum;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-3-9                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class EnumConstraintValidator implements ConstraintValidator<Enum, Serializable>
{
    private List<String> values;
    @Override
    public void initialize(Enum constraintAnnotation)
    {
        values = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(Serializable value, ConstraintValidatorContext context)
    {
        if(value == null)
        {
            return false;
        }
        if(!(value instanceof String))
        {
            value = String.valueOf(value);
            if(values.contains(value))
            {
                return true;
            }
            return false;
        }
        return false;
    }
    
}
