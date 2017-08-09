/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.validate.annotation;                                                                                                                                                                                                                                                                       

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.asiainfo.meo.common.core.validate.EnumConstraintValidator;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-3-9                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
 @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
 @Retention(RUNTIME)
 @Documented
 @Constraint(validatedBy = {EnumConstraintValidator.class})
public @interface Enum
{
     String message() default "The value should be in {value} ";

     Class<?>[] groups() default { };

     Class<? extends Payload>[] payload() default { };

     String[] value();
     
     @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
     @Retention(RUNTIME)
     @Documented
     public @interface List {
         Enum[] value();
     }
}
