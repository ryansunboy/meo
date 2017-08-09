package com.asiainfo.meo.web.passport.login.controller.v1;



import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.web.component.PassportComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.passport.login.model.vo.UICustLoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UILoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UIPrmLoginVO;
import com.asiainfo.meo.web.passport.login.model.vo.UITokenVO;

@RestController
@RequestMapping("/passport")
public class LoginController
{   
    @Resource
    private PassportComponent passportComponent;
    private static final Log LOG = LogFactory.getLog(LoginController.class);  
    
    @RequestMapping(value="/login", params="method=meo.customer.login",method=RequestMethod.POST)
    public Message<UITokenVO> customerLogin(@RequestBody @Validated(value={Insert.class}) UICustLoginVO login ){
        LOG .info("begin to invoke method:customerLogin******");

        UITokenVO tokenInfo= passportComponent.customerLogin(login.getLogin(),login.getDevice());
        Message<UITokenVO> message = new Message<UITokenVO>();
        message.setBody(tokenInfo);
        return message;
        
    }
    @RequestMapping(value="/login",params="method=portalLogin",method=RequestMethod.POST)
    public Message<UIPrmLoginVO> partnerLogin(@RequestBody @Valid UILoginVO login){
        UIPrmLoginVO prmInfo= passportComponent.partnerLogin(login);
        Message<UIPrmLoginVO> message = new Message<UIPrmLoginVO>();
        message.setBody(prmInfo);
        return message;
    }
    
    @RequestMapping(value="/logout",params="method=meo.passport.logout")
    public void logout(){
        passportComponent.logout();
    }
  
}
