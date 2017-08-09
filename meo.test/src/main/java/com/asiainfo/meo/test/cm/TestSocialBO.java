package com.asiainfo.meo.test.cm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.social.app.bo.SocialBO;
import com.asiainfo.meo.customer.social.app.model.entity.SocialInfo;

public class TestSocialBO
{
    Logger log = Logger.getLogger(TestSocialBO.class);
    @Test
    public void testSaveSocial() throws Exception{
        
        SocialInfo socialInfo = new SocialInfo();
        
        socialInfo.setSocialNumber("1");
        socialInfo.setChannel(1);
        socialInfo.setMsisdn("0823442222");
        socialInfo.setEmailAddr("thailand");
        socialInfo.setCountryCode(1);
        socialInfo.setGender(1);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date =  format1.parse("1990-01-01");
        socialInfo.setBirthday(date);
        socialInfo.setLanguageCode(1);
        socialInfo.setCityCode(1);
        socialInfo.setProvinceCode(1);
        socialInfo.setCustId(100000012000L);
        
        SocialBO socialBO = ServiceLocatorFactory.getService(SocialBO.class);
        socialBO.saveSocial(socialInfo);
        log.info(JsonUtil.writeObjectAsString(socialInfo));
    }
}
