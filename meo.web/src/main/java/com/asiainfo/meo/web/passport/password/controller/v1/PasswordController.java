package com.asiainfo.meo.web.passport.password.controller.v1;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.web.component.PassportComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.passport.password.model.vo.UIOtpPwdVO;
import com.asiainfo.meo.web.passport.password.model.vo.UIPasswordVO;

@RestController
@RequestMapping("/passport/password")
public class PasswordController
{
    @Resource
    private PassportComponent passportComponent;
    
    @RequestMapping(value = "/public", params = "method=meo.passport.otp.public.get")
    public Message<OneTimePasswordVO> getOtp(@RequestBody @Valid UIOtpPwdVO mobile)
    {
        OneTimePasswordVO password = passportComponent.getOtpForCust(mobile);
        Message<OneTimePasswordVO> message = new Message<OneTimePasswordVO>();
        message.setBody(password);
        return message;
    }
    
    @RequestMapping(params = "method=resetPortalPwd", method = RequestMethod.POST)
    public Message<Void> resetPortalPassword(@RequestBody @Valid UIPasswordVO password)
    {
        passportComponent.resetPortalPassword(password);
        return new Message<Void>();
    }
    
    @RequestMapping(params = "method=resetCustPwd", method = RequestMethod.POST)
    public void resetCustomerPassword(@RequestBody @Valid UIPasswordVO password)
    {
        passportComponent.resetCustPassword(password);
    }
    
    @RequestMapping(params = "method=meo.passport.otp.get", method = RequestMethod.GET)
    public Message<OneTimePasswordVO> getOtp()
    {
        OneTimePasswordVO password = passportComponent.getOtpForCust(null);
        Message<OneTimePasswordVO> message = new Message<OneTimePasswordVO>();
        message.setBody(password);
        return message;
    }
}
