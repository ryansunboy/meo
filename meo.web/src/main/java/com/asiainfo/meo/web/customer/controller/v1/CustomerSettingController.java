package com.asiainfo.meo.web.customer.controller.v1;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;
import com.asiainfo.meo.web.component.CustomerComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.customer.model.vo.UICustSettingInfoVO;
import com.asiainfo.meo.web.customer.model.vo.UISettingVO;

/**
 * @author liuyang
 */
@RestController
@RequestMapping("/customer/setting")
public class CustomerSettingController
{
    private static final Log   LOG = LogFactory.getLog(CustomerSettingController.class);
    
    @Resource
    private CustomerPserviceBO customerPserviceBO;
    
    @Resource
    private CustomerComponent  customerComponent;
    
    /**
     * @Description: (获取Customer Setting信息)
     * @Description: (get information of customer setting )
     * @modifyReason:
     * @author lill
     * @param custId
     * @param settingType
     * @return
     */
    @RequestMapping(params = "method=getCustSetInfo", method = {RequestMethod.GET, RequestMethod.POST })
    public Message<List<UICustSettingInfoVO>> getCustSettingInfo(@RequestParam(required = false) Integer settingType)
    {
        // Long custId = 100000026000L;// access token map custId
        Long custId = BoContext.getBoContext().getUserId();// access token map custId
        LOG.debug("get custSettingInfo begin");
        LOG.debug("requestParam custId="+ custId+ ";settingType="+ settingType);
        
        Message<List<UICustSettingInfoVO>> message = new Message<List<UICustSettingInfoVO>>();
        List<SettingVO> settingVOList = customerPserviceBO.getSetting(custId, settingType);
        List<UICustSettingInfoVO> custSettingInfoList = customerComponent.transformSettingVOToCustSettingInfoVO(settingVOList);
        
        message.setBody(custSettingInfoList);
        LOG.debug("get custSettingInfo end!");
        return message;
    }
    
    /**
     * @Description: 修改customer setting
     * @Description: modify customer setting
     * @author zhaozx
     * @param custId 客户id customer id
     * @param setting setting信息 setting information
     * @return CustSettingInfo
     * @throws IOException
     */
    @RequestMapping(params = "method=modifyCustSet", method = RequestMethod.POST)
    public void setCustSettingInfo(@RequestBody @Validated(value = {Insert.class }) UISettingVO setting)
            throws IOException
    {
        LOG.debug("set custSettingInfo begin!");
        List<UICustSettingInfoVO> settingInfoList = setting.getSettingInfos();
        
        Long custId = BoContext.getBoContext().getUserId();// access token map custId
        
        LOG.debug("requestParam custId="+ custId+ ";settingInfo="+ JsonUtil.writeObjectAsString(settingInfoList));
        
        List<SettingVO> settingVOList = customerComponent.transformCustSettingInfoVOToSettingVO(settingInfoList);
        
        customerPserviceBO.updateCustomerSetting(custId, settingVOList);
        
        LOG.debug("set custSettingInfo end!");
        return;
    }
}
