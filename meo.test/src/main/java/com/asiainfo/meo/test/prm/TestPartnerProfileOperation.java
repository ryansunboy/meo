package com.asiainfo.meo.test.prm;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.prm.profile.app.bo.PartnerProfileBO;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;

public class TestPartnerProfileOperation
{
    Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * @Description: 创建partner信息
     * @Description: (English description)
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testCreateProfile() throws IOException
    {
        Timestamp timeExpired = Timestamp.valueOf("9999-12-31 23:59:59");
        
        Partner partner = new Partner();
        partner.setPartnerType(1);
        
        PartnerInfo partnerInfo = new PartnerInfo();
        partnerInfo.setBillAddr("123456");
        partnerInfo.setBusinessCode("12109092");
        partnerInfo.setCountryCode(1);
        partnerInfo.setProvinceCode(2);
        partnerInfo.setCityCode(3);
        partnerInfo.setPostCode(123L);
        partnerInfo.setExpiredDate(timeExpired);
        partnerInfo.setMsisdn("110");
        partnerInfo.setTaxCode("1212090");
        partnerInfo.setCompanyName("AIS");
        partnerInfo.setContactEmail("123456@qq.com");
        partnerInfo.setContactMsidn("111");
        partnerInfo.setContactName("Tom");
        partnerInfo.setEmailAddr("654321@qq.com");
        
        PartnerProfileVO partnerProfile = new PartnerProfileVO();
        partnerProfile.setPartner(partner);
        partnerProfile.setPartnerInfo(partnerInfo);
        
        long saleId=11111111111L;
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(partnerProfile));
        
        PartnerPserviceBO partnerPserviceBO = ServiceLocatorFactory.getService(PartnerPserviceBO.class);
       
        partnerPserviceBO.savePartnerProfile(partnerProfile,saleId );//save partner 
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(partnerProfile));
    }
    
    /**
      * @Description: 修改partner联系人信息
      * @Description: update partner contact information
      * @author zhaozx
      * @throws IOException
      */
    @Test
    public void testUpdateContact() throws IOException
    {
        
        PartnerPserviceBO partnerPserviceBO = ServiceLocatorFactory.getService(PartnerPserviceBO.class);
        
        PartnerInfoVO partnerContactInfo=new PartnerInfoVO();
        
        partnerContactInfo.setPartnerId(100000031000L);
        partnerContactInfo.setAvatarUrl("D:/work/workdata.txt");
        partnerContactInfo.setContactMsidn("112121212");
        partnerContactInfo.setContactName("Jane1");
        partnerContactInfo.setConfirmEmail("1212121@qq.com");
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(partnerContactInfo));
        
        partnerPserviceBO.updatePartnerContact(partnerContactInfo);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(partnerContactInfo));
    }
    
    /**
     * @Description: 修改partner信息
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testUpdateProfile() throws IOException
    {
        PartnerInfoVO info = new PartnerInfoVO();
        info.setPartnerId(100000021000L);
        info.setPartnerType(0);
        info.setBillAddr("123");
        info.setBusinessCode("111");
        info.setCountryCode(1);
        info.setProvinceCode(2);
        info.setCityCode(3);
        info.setPostCode(123L);
        info.setMsisdn("2222");
        info.setTaxCode("111");
        info.setCompanyName("AIS1");
        info.setConfirmEmail("123@qq.com");
        info.setContactMsidn("111");
        info.setContactName("Tom1");
        info.setEmailAddr("654@qq.com");
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(info));
        
        PartnerProfileBO cmPartnerInfoBO = ServiceLocatorFactory.getService(PartnerProfileBO.class);
        cmPartnerInfoBO.updatePartnerProfile(info);
        
    }
    
    /**
     * @Description: 获得partner信息
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testGetPartnerProfile() throws IOException
    {
        long partnerId = 100000021000L;
        
        logger.info("in:"+ "partnerId="+ partnerId);
        
        PartnerProfileBO cmPartnerInfoBO = ServiceLocatorFactory.getService(PartnerProfileBO.class);
        PartnerProfileVO partnerProfile = cmPartnerInfoBO.getPartnerProfile(partnerId);
        if (partnerProfile.getPartnerInfo()!= null)
        {
            logger.info("out:"+ JsonUtil.writeObjectAsString(partnerProfile));
        }
    }
    
    /**
     * @Description: 将partner变失效
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     */
    @Test
    public void testDeactivePartner()
    {
        long partnerId = 100000021000L;
        
        logger.info("in:"+ "partnerId="+ partnerId);
        
        PartnerProfileBO cmPartnerInfoBO = ServiceLocatorFactory.getService(PartnerProfileBO.class);
        cmPartnerInfoBO.deletePartner(partnerId);
    }
    
    /**
     * @Description: 修改partner状态
     * @Description: update partner status
     * @author zhaozx
     */
    @Test
    public void testUpdatePartnerSts()
    {
        long partnerId = 100000021000L;
        int oldSts = 0;
        int newSts = 1;
        logger.info("in:"+ "partnerId="+ partnerId+ "\t"+ "oldSts="+ oldSts+ "\t"+ "newSts="+ newSts);
        PartnerProfileBO cmPartnerInfoBO = ServiceLocatorFactory.getService(PartnerProfileBO.class);
        cmPartnerInfoBO.updatePartnerSts(partnerId, oldSts, newSts);
    }
    
    /**
     * @Description: 通过 销售员id获得partner profile list
     * @Description: get partner profile list by sale id
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testGetPartnerProfileList() throws IOException
    {
        long saleId = 111;
        logger.info("in:saleId="+ saleId);
        PartnerProfileBO cmPartnerInfoBO = ServiceLocatorFactory.getService(PartnerProfileBO.class);
        // List<PartnerProfileVO> partnerProfileVOList= cmPartnerInfoBO.getPartnerProfileList(saleId);
        // logger.info(JsonUtil.writeObjectAsString(partnerProfileVOList));
    }
}
