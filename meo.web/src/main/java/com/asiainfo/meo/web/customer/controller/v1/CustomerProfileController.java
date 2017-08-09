package com.asiainfo.meo.web.customer.controller.v1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.cm.app.model.vo.CustomerVO;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.prm.contract.app.constant.PartnerContractErrorConstant;
import com.asiainfo.meo.web.component.CustomerComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.customer.model.vo.UIAssetInfoVO;
import com.asiainfo.meo.web.customer.model.vo.UICustProfileResVO;
import com.asiainfo.meo.web.customer.model.vo.UICustomerInfoVO;

/**
 * @author liuyang
 */
@RestController
@RequestMapping("/customer/profile")
public class CustomerProfileController
{
    private static final Log   LOG = LogFactory.getLog(CustomerProfileController.class);
    
    @Resource
    private CustomerPserviceBO customerPserviceBO;
    
    @Resource
    private CustomerComponent  customerComponent;
    
    /**
     * @Description: (获取客户基本信息)
     * @Description: (get profile information of customer info )
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     * @throws MeoException
     */
    @RequestMapping(params = "method=getCustInfo", method = {RequestMethod.GET, RequestMethod.POST })
    public Message<UICustProfileResVO> getCustProfile() throws MeoException
    {
        LOG.info("getCustProfile begin");
        Long custId = BoContext.getBoContext().getUserId();
        if (ValidateUtil.isNull(custId))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new String[]{"custId" });
        CustomerVO customerVO = createCustomerVO(custId);
        if (ValidateUtil.isEmpty(customerVO))
            throw new MeoException(CustomerErrorConstant.CUST_DO_NOT_EXIST);
        CustomerBundleInfo customerBundleInfo = createCustomerBundleInfo(customerVO);
        UICustProfileResVO res = createUICustProfileResVO(custId, customerVO, customerBundleInfo);
        Message<UICustProfileResVO> message = new Message<UICustProfileResVO>();
        message.setBody(res);
        return message;
    }
    
    private CustomerVO createCustomerVO(Long custId)
    {
        CustomerVO customerVO = customerPserviceBO.getCustomerProfile(custId);
        if (ValidateUtil.isNull(customerVO)|| ValidateUtil.isNull(customerVO.getCustomer()))
            throw new MeoException(CustomerErrorConstant.CUST_DO_NOT_EXIST);
        if (ValidateUtil.isEmpty(customerVO.getMnoCode()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new String[]{"mnoCode" });
        return customerVO;
    }
    
    private CustomerBundleInfo createCustomerBundleInfo(CustomerVO customerVO)
    {
        CustomerBundleInfo customerBundleInfo = customerVO.getCustomerBundleInfo();
        if (ValidateUtil.isEmpty(customerBundleInfo))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new String[]{CustomerBundleInfo.class.getSimpleName() });
        if (ValidateUtil.isEmpty(customerBundleInfo.getMnoId()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new String[]{"mnoId" });
        if (ValidateUtil.isEmpty(customerBundleInfo.getBundleAcct()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new String[]{"mobileNO" });
        return customerBundleInfo;
    }
    
    private UICustProfileResVO createUICustProfileResVO(Long custId, CustomerVO customerVO, CustomerBundleInfo customerBundleInfo)
    {
        CustomerInfo customerInfo = customerVO.getCustomerInfo();
        UICustomerInfoVO custInfo = new UICustomerInfoVO();
        custInfo.setCustId(custId);
        custInfo.setMnoId(customerBundleInfo.getMnoId());
        custInfo.setMnoCode(customerVO.getMnoCode());
        custInfo.setMobileNo(customerBundleInfo.getBundleAcct());
        if (ValidateUtil.isNotEmpty(customerInfo))
        {
            custInfo.setFirstName(customerInfo.getFirstName());
            custInfo.setMiddleName(customerInfo.getMiddleName());
            custInfo.setLastName(customerInfo.getLastName());
            custInfo.setAddress(customerInfo.getAddress());
            custInfo.setEmailAddr(customerInfo.getEmailAddr());
            custInfo.setCountryCode(customerInfo.getCountryCode());
            custInfo.setGender(customerInfo.getGender());
            custInfo.setGenderName(customerVO.getGenderName());
            if (ValidateUtil.isNotEmpty(customerInfo.getBirthday()))
            {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                custInfo.setBirthday(df.format(customerInfo.getBirthday()));
            }
            custInfo.setLanguageId(customerInfo.getLanguageCode());
            custInfo.setLanguageCode(customerVO.getLanguageCode());
            custInfo.setProvCode(customerInfo.getProvinceCode());
            custInfo.setCityCode(customerInfo.getCityCode());
            custInfo.setAvatarUrl(customerInfo.getAvatarUrl());
            custInfo.setDisplayName(customerInfo.getDisplayName());
        }
        
        Long coins = customerVO.getCoins();
        UIAssetInfoVO assetInfo = new UIAssetInfoVO();
        if (ValidateUtil.isNotNull(coins))
        {
            assetInfo.setCoins(coins.intValue());
        }
        UICustProfileResVO res = new UICustProfileResVO();
        res.setAssetInfo(assetInfo);
        res.setCustomerInfo(custInfo);
        return res;
    }
    
    /**
     * @Description: (创建客户基本信息)
     * @Description: (create customer basic information)
     * @modifyReason:
     * @author liuyang
     * @param customerInfo
     * @throws MeoException
     */
    
    @RequestMapping(params = "method=createCustBasicInfo", method = RequestMethod.POST)
    public void createCustBasicInfo(@RequestBody @Valid UICustomerInfoVO customerInfo) throws MeoException
    {
        if (ValidateUtil.isEmpty(customerInfo))
            return;
        Long custId = BoContext.getBoContext().getUserId();
        boolean flag = customerComponent.custIsExisted(custId);
        if (!flag)
            throw new MeoException(CustomerErrorConstant.CUST_DO_NOT_EXIST, new Long[]{custId });
        CustomerInfo oldCustInfo = customerPserviceBO.getCustBasicInfo(custId);
        if (ValidateUtil.isNotNull(oldCustInfo))
        {
            LOG.debug("customer info is already exist,custId is "+ custId);
            throw new MeoException(CustomerErrorConstant.CUSTOMER_INFO_IS_ALREADY_EXIST, new Object[]{custId });
        }
        LOG.info("begin to invoke method:[transformCreateCustProfileReq] ****");
        CustomerInfo customer = customerComponent.transformCreateCustProfileReq(customerInfo, custId);
        LOG.info("begin to invoke method :[createCustProfile]******");
        if (ValidateUtil.isEmpty(customer))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new String[]{CustomerInfo.class.getSimpleName() });
        customerComponent.createCustProfile(customer);
    }
    
    /**
     * @Description: (修改客户基本信息)
     * @Description: (modify customer basic information)
     * @modifyReason:
     * @author liuyang
     * @param customerInfo
     * @throws MeoException
     */
    @RequestMapping(params = "method=modifyCustBasicInfo", method = RequestMethod.POST)
    public void modifyCustBasicInfo(@RequestBody @Valid UICustomerInfoVO customerInfo) throws MeoException
    {
        if (ValidateUtil.isEmpty(customerInfo))
            return;
        Long custId = BoContext.getBoContext().getUserId();
        if (ValidateUtil.isEmpty(custId))
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Long[]{custId });
        boolean flag = customerComponent.custIsExisted(custId);
        if (!flag)
            throw new MeoException(CustomerErrorConstant.CUST_DO_NOT_EXIST, new Long[]{custId });
        LOG.info("begin to invoke method[transformModifyCustBasicInfoReq]*****");
        CustomerInfo info = customerComponent.transformModifyCustBasicInfoReq(customerInfo, custId);
        if (ValidateUtil.isEmpty(info))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new String[]{CustomerInfo.class.getSimpleName() });
        LOG.info("begin to invoke method[modifyCustBasicInfo]*****");
        customerComponent.modifyCustBasicInfo(info);
        return;
    }
}
