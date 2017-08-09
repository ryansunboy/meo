package com.asiainfo.meo.web.passport.token;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.web.component.PassportComponent;

@RestController
@RequestMapping("/passport/token")
public class TokenController
{
    @Resource
    private PassportComponent passportComponent;

    @RequestMapping(params="method=meo.passport.token.refresh")
    public Token refreshToken(@RequestParam String refreshToken){
        return passportComponent.refreshToken(refreshToken);
    }
    
    
}
