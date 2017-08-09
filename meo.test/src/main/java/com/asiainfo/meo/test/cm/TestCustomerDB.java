package com.asiainfo.meo.test.cm;


import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.profile.app.bo.CustomerBO;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfileVO;

public class TestCustomerDB
{
    Logger log = Logger.getLogger(this.getClass());
    @Test
    public void testSaveCustomer(){
        Customer customer = new Customer();
//        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
//      customer.setCustId(sequence.next("SEQ_CUSTOMER_ID"));
      CustomerBO customerBO = ServiceLocatorFactory.getService(CustomerBO.class);
      customerBO.saveCustomer(customer);
        
    }
    
    
    @Test
    public void testSaveCustomerInfo(){
        CustomerInfo customerInfo = new CustomerInfo();
      customerInfo.setCustId(1001);
      customerInfo.setName("jack10");
      customerInfo.setMsisdn("0822341312");
      customerInfo.setEmailAddr("jack10@gmail.com");
      customerInfo.setCountryCode(1);
      customerInfo.setGender(1);
      customerInfo.setBirthday(DateTimeUtil.getNow());
      customerInfo.setLanguageCode(2);
      customerInfo.setCityCode(3);
      customerInfo.setProvinceCode(1);
      CustomerBO customerBO = ServiceLocatorFactory.getService(CustomerBO.class);
      customerBO.saveCustomerInfo(customerInfo);
    }
    @Test
  public void testSaveCustomerProfileDB()
  {
      Customer customer = new Customer();
//      customer.setMnoId(1);
//      customer.setImgUrl("http://10.10.10.10:8080/image/image1.jpg");
      CustomerInfo customerInfo = new CustomerInfo();
      customerInfo.setName("jack9");
      customerInfo.setMsisdn("0843636424");
      customerInfo.setEmailAddr("jack9@gmail.com");
      customerInfo.setBirthday(DateTimeUtil.getNow());
//      CustomerInfoHis customerInfoHis = new CustomerInfoHis();
      CustomerBO customerBO = ServiceLocatorFactory.getService(CustomerBO.class);
      customerBO.saveCustomerProfile(customer, customerInfo );
  }
  @Test
    public void testUpdateCustomer(){
        Customer customer = new Customer();
        customer.setCustId(100000026000L);
        customer.setLevelId(3);
        CustomerBO customerBO = ServiceLocatorFactory.getService(CustomerBO.class);
        customerBO.updateCustomer(customer);
        
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
//      customer.setLevelId(1);
    CustomerBO customerBO = ServiceLocatorFactory.getService(CustomerBO.class);
    customerBO.updateCustomerProfile(customerProfileVO);
    log.info(JsonUtil.writeObjectAsString(customerProfileVO));
  }
  
}
