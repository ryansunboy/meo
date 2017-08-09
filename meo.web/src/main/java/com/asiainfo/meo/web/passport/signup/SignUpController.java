package com.asiainfo.meo.web.passport.signup;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;
import com.asiainfo.meo.web.component.PassportComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.passport.login.model.vo.UICustSignUpVO;
import com.asiainfo.meo.web.passport.login.model.vo.UITokenVO;
@RestController
@RequestMapping("/passport/signup")
public class SignUpController   
{
    @Resource
    private PassportComponent passportComponent;
    
    @Resource
    CustomerPserviceBO        customerPserviceBO;
    
    private static final Log LOG = LogFactory.getLog(SignUpController.class);  
    
    @RequestMapping(params="method=meo.customer.signup",method=RequestMethod.POST)
    public Message<UITokenVO> custSignUp(@RequestBody @Validated(value=Insert.class) UICustSignUpVO signUp){
        LOG.info("begin to invoke method:customerSignUp");
        UITokenVO token = passportComponent.customerSignUp(signUp);
        Message<UITokenVO> message = new Message<UITokenVO>();
        message.setBody(token);
        return message;
    }
    
    
    /**
     * @Description: 检查login Account是否重复
     * @Description: check login account whether to repeat
     * @modifyReason:
     * @author zhengzy
     * @param loginAcct
     * @return
     */
    @RequestMapping(params = "method=meo.customer.loginAcct.check", method = {RequestMethod.GET})
    public Map<String,Integer> checkCustomerLoginAcctRepeat(@RequestParam String loginAcct)
    {
        LOG.info("==================meo.customer.loginAcct.check===================start");
        UserLoginVO userLogin = customerPserviceBO.getUserLoginInfo(loginAcct,SysEnumCodeDefine.USER_TYPE_CUSTOMER);
        //if (ValidateUtil.isNotNull(userLogin))
        //{
        //    LOG.debug("the loginAccount "+ loginAcct+ " has existed");
        //    throw new MeoException(CustomerErrorConstant.LOGINACCOUNT_HAS_EXISTED, new Object[]{loginAcct });
        //}
        LOG.info("==================meo.customer.loginAcct.check===================end");
        Integer result = ValidateUtil.isNotNull(userLogin)?0:1;
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("isRepeat", result);
        return map;
    }
    
    /**
     * @Description: 检查login Account是否重复
     * @Description: check login account whether to repeat
     * @modifyReason:
     * @author zhengzy
     * @param loginAcct
     * @return
     */
    @RequestMapping(params = "method=meo.partner.loginAcct.check", method = {RequestMethod.GET })
    public Map<String,Integer> checkPartnerLoginAcctRepeat(@RequestParam String loginAcct)
    {
        LOG.info("==================meo.partner.loginAcct.check===================start");
        UserLoginVO userLogin = customerPserviceBO.getUserLoginInfo(loginAcct,SysEnumCodeDefine.USER_TYPE_PORTAL);
        //if (ValidateUtil.isNotNull(userLogin))
        //{
        //    LOG.debug("the loginAccount "+ loginAcct+ " has existed");
        //    throw new MeoException(CustomerErrorConstant.LOGINACCOUNT_HAS_EXISTED, new Object[]{loginAcct });
        //}
        LOG.info("==================meo.partner.loginAcct.check===================start");
        Integer result = ValidateUtil.isNotNull(userLogin)?0:1;
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("isRepeat", result);
        return map;
    }
}
