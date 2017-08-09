package com.asiainfo.meo.test.cm;


import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.define.SysEnumCodeDefine;
import com.asiainfo.meo.test.utils.HttpClientUtil;
import com.asiainfo.meo.test.utils.JsonUtil;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.passport.login.model.vo.UiCustLoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UiDeviceVO;
import com.asiainfo.meo.web.passport.login.model.vo.UiLoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UiTokenVO;

public class TestBusinessFlow
{
    private static final String signUrl       = "https://localhost:8443/meo/rest/passport/signup";
    private static final String loginurl       = "https://localhost:8443/meo/rest/passport/login";
    private static final String getOtpurl       = "http://localhost:8080/meo/rest/passport/password";
    private static final String validOtpurl       = "http://localhost:8080/meo/rest/customers";
    @Test
    public void testCustSignUp() throws Exception{
       custSignUp();
        
    }
    @Test
    public void testCustLogin()throws Exception{
        custLogin();
    }
    
    private Message custLogin() throws Exception
    {
        String queryString =loginurl+ "?"+ "method=custLogin&timestamp="+DateTimeUtil.getCurrentTimeMillis()+"&format=json&v=1";
        UiCustLoginVO login = custLoginBean();
        String reponse = HttpClientUtil.post(queryString, login);
        System.out.println(reponse);
        Message message = JsonUtil.fromJson(reponse, Message.class);
      return message;
        
    }
    public Message custSignUp() throws Exception{
        String queryString = signUrl+ "?"+"method=custSignUp&timestamp="+DateTimeUtil.getCurrentTimeMillis()+"&format=json&v=1";
        UiLoginVO sign = createSignUp();
        String reponse = HttpClientUtil.post(queryString, sign);
        System.out.println(reponse);
        Message message = JsonUtil.fromJson(reponse, Message.class);
      return message;
       
        
       
        
        
    }
    private UiLoginVO createSignUp()
    {
        UiLoginVO vo = new UiLoginVO();
        vo.setLoginAcct(SequenceUtil.getGlobalSequence());
        vo.setPassword("12345678");
        return vo;
    }
    private UiCustLoginVO custLoginBean()
    {
        UiCustLoginVO custVo=new UiCustLoginVO();
        UiLoginVO vo = new UiLoginVO();
        vo.setLoginAcct("meo-2015041518113000000000011");
        vo.setPassword("12345678");
        vo.setUserType(SysEnumCodeDefine.USER_TYPE_CUSTOMER);
        vo.setRegionCode(1);
        custVo.setLoginInfo(vo);
        UiDeviceVO deviceVo = new UiDeviceVO();
        deviceVo.setDeviceToken("121212121212");
        deviceVo.setDeviceType(1);
        deviceVo.setOsVersion("1");
        deviceVo.setUdid("3784738748347");
        custVo.setDeviceInfo(deviceVo);
        return custVo;
       
    }
}
