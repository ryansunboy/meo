package com.asiainfo.meo.test.cm;

// import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.cm.app.bo.CmBO;
import com.asiainfo.meo.customer.cm.app.model.vo.CustomerVO;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfileVO;

public class TestCmBO
{
    Logger log = Logger.getLogger(this.getClass());
    
    @Test
    public void testCmBO() throws Exception
    {
        
        CmBO cmBO = ServiceLocatorFactory.getService(CmBO.class);
        CustomerVO customerVO = cmBO.getCustomer(100000012000L);
        log.info(JsonUtil.writeObjectAsString(customerVO));
    }
    
    @Test
    public void testUpdateCustomerProfile() throws Exception{
      CustomerProfileVO customerProfileVO = new CustomerProfileVO();
      customerProfileVO.setCustId(100000012000L);
      customerProfileVO.setCustName("Lee");
      customerProfileVO.setMobileNo("0854345264");
      customerProfileVO.setMnoCode(1);
      customerProfileVO.setEmailAddr("Lee@gmail.com");
      customerProfileVO.setCountryCode(1);
      customerProfileVO.setGender(1);
      customerProfileVO.setBirthday(DateTimeUtil.getDate("1990-01-11"));
      customerProfileVO.setLanguageCode(1);
      customerProfileVO.setProCode(1);
      customerProfileVO.setCityCode(1);
      customerProfileVO.setSocialType(1);
      customerProfileVO.setAvatarUrl("http://10.10.10.10/img1.jpg");
//        customer.setLevelId(1);
      CmBO cmBO = ServiceLocatorFactory.getService(CmBO.class);
      cmBO.updateCustomerProfile(customerProfileVO);
      //.updateCustomerProfile(customerProfileVO);
      log.info(JsonUtil.writeObjectAsString(customerProfileVO));
    }
}



