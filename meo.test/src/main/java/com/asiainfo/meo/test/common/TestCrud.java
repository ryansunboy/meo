/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.test.common;

import java.util.Random;

import org.junit.Test;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.user.app.model.entity.User;
import com.asiainfo.meo.user.app.model.entity.User1;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author AI
 * @Date 2015-3-6
 */
public class TestCrud
{
    @Test
    public void testCreate()
    {
        HibernateRepository repository = ServiceLocatorFactory.getService(HibernateRepository.class);
        User u = createUser();
        repository.saveObject(u);
        System.out.println(u.getUserId());
//        repository.get(User.class, 4l);
    }
    
    @Test
    public void testUpdate()
    {
        HibernateRepository repository = ServiceLocatorFactory.getService(HibernateRepository.class);
        User u = new User();
        u.setUserId(1487);
        u.setUserName("15");
        repository.updateObject(u);
    }
    
    private User createUser()
    {
        User u = new User();
        u.setUserId(new Random().nextInt(2000));
//        u.setUserName(u.getUserId() + "ruanming");
        return u;
    }
    
    @Test
    public void testNameSql()
    {
        HibernateRepository repository = ServiceLocatorFactory.getService(HibernateRepository.class);
        User u = repository.findUniqueObjectByNamedQuery("meo.user.getUser", 1487l);
        System.out.println(u.getUserName());
    }
}
