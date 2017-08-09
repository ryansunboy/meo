/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.test.common;

import java.util.List;
import java.util.Set;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.user.app.model.entity.User;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author AI
 * @Date 2015-3-6
 */
public class TestValidate
{
    @Test
    public void testJsr303()
    {
        User u = new User();
        u.setUserId(1000);
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
         Validator validator = ServiceLocatorFactory.getService(Validator.class);
        // Validator validator =
        // Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
         Set<ConstraintViolation<User>> violations = validator.validate(u,Insert.class);
        System.setProperty("javax.persistence.validation.group.pre-persist","javax.persistence.validation.group.pre-persist");
//        Set<ConstraintViolation<User>> violations = validator.validateProperty(u, "userId");
        for (ConstraintViolation<User> violation : violations)
        {
            System.out.println(violation.getInvalidValue());
            System.out.println(violation.getMessage());
        }
    }
    
    @Test
    public void testJavaScript()
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory f : factories)
        {
            System.out.println("egine name:" + f.getEngineName() + ",engine version:" + f.getEngineVersion() + ",language name:"
                    + f.getLanguageName() + ",language version:" + f.getLanguageVersion() + ",names:" + f.getNames() + ",mime:"
                    + f.getMimeTypes() + ",extension:" + f.getExtensions());
        }
    }
    
    @Test
    public void testJavaScript1()
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String script = "print ('hello script')";
        Compilable compilingEngine = (Compilable)engine;
        try
        {
            engine.eval(script);
        }
        catch(ScriptException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGroup()
    {
        User u = new User();
        Validator validator = ServiceLocatorFactory.getService(Validator.class);
        Set<ConstraintViolation<User>> violations = validator.validate(u);
        for (ConstraintViolation<User> violation : violations)
        {
            System.out.println(violation.getInvalidValue());
            System.out.println(violation.getMessage());
        }
    }
    
    @Test
    public void testLong()
    {
        Long l = 3l;
        System.out.println(String.valueOf(l));
        
    }
}
