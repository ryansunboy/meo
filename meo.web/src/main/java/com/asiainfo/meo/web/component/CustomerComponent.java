package com.asiainfo.meo.web.component;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;
import com.asiainfo.meo.customer.profile.app.model.vo.CustomerProfileVO;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.web.customer.model.vo.UICustSettingInfoVO;
import com.asiainfo.meo.web.customer.model.vo.UICustomerInfoVO;
import com.asiainfo.meo.web.customer.model.vo.UIPResourceVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicListVO;

public class CustomerComponent
{
    @Resource
    CustomerPserviceBO          custPserviceBo;
    
    private static final Log    LOG              = LogFactory.getLog(CustomerComponent.class);
    
    private static final String BIRTHDATE_FORMAT = "yyyy-MM-dd";
    
    /*public CustomerProfileVO custInfoToCustomerProfileVO(UICustomerInfoVO custInfo) throws IOException
    {
        CustomerProfileVO customreProfileVO = new CustomerProfileVO();
        
        if (ValidateUtil.isNotEmpty(custInfo.getCustId()))
            customreProfileVO.setCustId(custInfo.getCustId());
        else
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"" });
        
        if (ValidateUtil.isNotEmpty(custInfo.getCustName()))
            customreProfileVO.setCustName(custInfo.getCustName());
        else
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"" });
        
        if (ValidateUtil.isNotEmpty(custInfo.getMobileNo()))
            customreProfileVO.setMobileNo(custInfo.getMobileNo());
        else
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"Mobile No" });
        
        if (ValidateUtil.isNotEmpty(custInfo.getMnoCode()))
            customreProfileVO.setMnoCode(custInfo.getMnoId());
        else
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"mno Id" });
        
        if (emailFormat(custInfo.getEmailAddr()))
            customreProfileVO.setEmailAddr(custInfo.getEmailAddr());
        else
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"email address" });
        
        if (ValidateUtil.isNotEmpty(custInfo.getCountryCode()))
            customreProfileVO.setCountryCode(custInfo.getCountryCode());
        
        if (ValidateUtil.isNotEmpty(custInfo.getGender()))
            customreProfileVO.setGender(custInfo.getGender());
        if (ValidateUtil.isNotEmpty(custInfo.getBirthday()))
            customreProfileVO.setBirthday(Timestamp.valueOf(custInfo.getBirthday()));
        if (ValidateUtil.isNotEmpty(custInfo.getLanguageCode()))
            customreProfileVO.setLanguageCode(custInfo.getLanguageId());
        
        if (ValidateUtil.isNotEmpty(custInfo.getProvCode()))
            customreProfileVO.setProCode(custInfo.getProvCode());
        
        if (ValidateUtil.isNotEmpty(custInfo.getCityCode()))
            customreProfileVO.setCityCode(custInfo.getCityCode());
        
        if (ValidateUtil.isNotEmpty(custInfo.getAvatarUrl()))
            customreProfileVO.setAvatarUrl(custInfo.getAvatarUrl());
        
        return customreProfileVO;
    }*/
    
    public void checkCustSettingInfo(Long custId, Integer settingId, Integer settingValue)
    {
        LOG.info("check custSettingInfo begin");
        
        if (ValidateUtil.isNull(custId))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"custId" });
        if (ValidateUtil.isNull(settingId))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"settingId" });
        if (ValidateUtil.isNull(settingValue))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"settingValue" });
        LOG.info("check custSettingInfo end");
    }
    
    public static boolean emailFormat(String email)
    {
        boolean tag = true;
        final String pattern1 = "^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find())
        {
            tag = false;
        }
        return tag;
    }
    
    public boolean custIsExisted(Long custId)
    {
        Customer cust = custPserviceBo.getCustomer(custId);
        return !ValidateUtil.isEmpty(cust);
    }
    
    public void createCustProfile(CustomerInfo customerInfo)
    {
        if (ValidateUtil.isEmpty(customerInfo))
            return;
        custPserviceBo.saveCustomerInfo(customerInfo);
    }
    
    public CustomerInfo transformCreateCustProfileReq(UICustomerInfoVO req, Long custId)
    {
        if (ValidateUtil.isEmpty(req))
            return null;
        
        CustomerInfo info = new CustomerInfo();
        if (ValidateUtil.isNotEmpty(custId))
            info.setCustId(custId);
        if (ValidateUtil.isNotEmpty(req.getBirthday()))
            try
            {
                info.setBirthday(DateTimeUtil.getDate(req.getBirthday()));
            }
            catch(ParseException e)
            {
                throw new MeoException(CustomerErrorConstant.CUSTOMER_BIRTHDAY_IS_INCORRECT);
            }
        if (ValidateUtil.isNotEmpty(req.getCityCode()))
            info.setCityCode(req.getCityCode());
        if (ValidateUtil.isNotEmpty(req.getCountryCode()))
            info.setCountryCode(req.getCountryCode());
        if (ValidateUtil.isNotEmpty(req.getFirstName()))
            info.setFirstName(req.getFirstName());
        if (ValidateUtil.isNotEmpty(req.getMiddleName()))
            info.setMiddleName(req.getMiddleName());
        if (ValidateUtil.isNotEmpty(req.getLastName()))
            info.setLastName(req.getLastName());
        if (ValidateUtil.isNotEmpty(req.getAddress()))
            info.setAddress(req.getAddress());
        if (ValidateUtil.isNotEmpty(req.getEmailAddr()))
            info.setEmailAddr(req.getEmailAddr());
        if (ValidateUtil.isNotEmpty(req.getGender()))
            info.setGender(req.getGender());
        if (ValidateUtil.isNotEmpty(req.getLanguageCode()))
            info.setLanguageCode(Integer.valueOf(req.getLanguageCode()));
        if (ValidateUtil.isNotEmpty(req.getProvCode()))
            info.setProvinceCode(req.getProvCode());
        if (ValidateUtil.isNotEmpty(req.getAvatarUrl()))
            info.setAvatarUrl(req.getAvatarUrl());
        if (ValidateUtil.isNotEmpty(req.getDisplayName()))
            info.setDisplayName(req.getDisplayName());
        info.setSts(Customer.STS_INACTIVATE);
        return info;
    }
    
    public CustomerInfo transformModifyCustBasicInfoReq(UICustomerInfoVO req, Long custId)
    {
        CustomerInfo custInfo = custPserviceBo.getCustBasicInfo(custId);
        
        if (ValidateUtil.isEmpty(custInfo)|| ValidateUtil.isEmpty(req))
            return custInfo;
        
        if (ValidateUtil.isNotEmpty(req.getBirthday()))
            try
            {
                custInfo.setBirthday(DateTimeUtil.getDate2(req.getBirthday(), BIRTHDATE_FORMAT));
            }
            catch(ParseException e)
            {
                throw new MeoException(CustomerErrorConstant.CUSTOMER_BIRTHDAY_IS_INCORRECT);
            }
        if (ValidateUtil.isNotEmpty(req.getCityCode()))
            custInfo.setCityCode(req.getCityCode());
        if (ValidateUtil.isNotEmpty(req.getCountryCode()))
            custInfo.setCountryCode(req.getCountryCode());
        if (ValidateUtil.isNotEmpty(req.getFirstName()))
            custInfo.setFirstName(req.getFirstName());
        if (ValidateUtil.isNotEmpty(req.getMiddleName()))
            custInfo.setMiddleName(req.getMiddleName());
        if (ValidateUtil.isNotEmpty(req.getLastName()))
            custInfo.setLastName(req.getLastName());
        if (ValidateUtil.isNotEmpty(req.getAddress()))
            custInfo.setAddress(req.getAddress());
        if (ValidateUtil.isNotEmpty(req.getEmailAddr()))
            custInfo.setEmailAddr(req.getEmailAddr());
        if (ValidateUtil.isNotEmpty(req.getGender()))
            custInfo.setGender(req.getGender());
        if (ValidateUtil.isNotEmpty(req.getLanguageCode()))
            custInfo.setLanguageCode(Integer.valueOf(req.getLanguageCode()));
        if (ValidateUtil.isNotEmpty(req.getProvCode()))
            custInfo.setProvinceCode(req.getProvCode());
        if (ValidateUtil.isNotEmpty(req.getAvatarUrl()))
            custInfo.setAvatarUrl(req.getAvatarUrl());
        if (ValidateUtil.isNotEmpty(req.getDisplayName()))
            custInfo.setDisplayName(req.getDisplayName());
        custInfo.setModifyDate(DateTimeUtil.getNow());
        return custInfo;
    }
    
    public void modifyCustBasicInfo(CustomerInfo info)
    {
        if (ValidateUtil.isEmpty(info))
            return;
        custPserviceBo.updateCustomerInfo(info);
    }
    
    /**
     * @Description: 转换CustSettingInfoVO到SettingVO
     * @Description: transform CustSettingInfoVO to SettingVO
     * @author zhaozx
     * @param settingInfoList
     * @return
     */
    public List<SettingVO> transformCustSettingInfoVOToSettingVO(List<UICustSettingInfoVO> settingInfoList)
    {
        if (ValidateUtil.isEmpty(settingInfoList))
            return null;
        List<SettingVO> settingVOlist = new ArrayList<SettingVO>();
        for (UICustSettingInfoVO settingInfo : settingInfoList)
        {
            SettingVO settingVO = new SettingVO();
            if (settingInfo.getSettingId()!= null)
                settingVO.setSettingId(settingInfo.getSettingId());
            if (settingInfo.getSettingValue()!= null)
                settingVO.setSettingValue(settingInfo.getSettingValue());
            settingVOlist.add(settingVO);
        }
        return settingVOlist;
    }
    
    /**
     * @Description: 转换SettingVO到CustSettingInfoVO
     * @Description: transform SettingVO to CustSettingInfoVO
     * @modifyReason:
     * @author zhaozx
     * @param settingList
     * @return
     */
    public List<UICustSettingInfoVO> transformSettingVOToCustSettingInfoVO(List<SettingVO> settingVOList)
    {
        List<UICustSettingInfoVO> custSettingInfoList = new ArrayList<UICustSettingInfoVO>();
        if (ValidateUtil.isNotEmpty(settingVOList))
        {
            for (SettingVO settingVO : settingVOList)
            {
                UICustSettingInfoVO settingInfo = new UICustSettingInfoVO();
                settingInfo.setSettingId(settingVO.getSettingId());
                settingInfo.setSettingName(settingVO.getSettingName());
                settingInfo.setSettingType(settingVO.getSettingType());
                settingInfo.setSettingValue(settingVO.getSettingValue());
                settingInfo.setSettingValueName(settingVO.getSettingValueName());
                custSettingInfoList.add(settingInfo);
            }
        }
        return custSettingInfoList;
    }
    
    public List<UIPResourceVO> transformPResourceListToUIPResourceVOList(List<PResource> pResourceList)
    {
        List<UIPResourceVO> uiPResourceVOList = new ArrayList<UIPResourceVO>();
        for (PResource pResource : pResourceList)
        {
            UIPResourceVO uiPResourceVO = new UIPResourceVO();
            BeanUtils.copyProperties(pResource, uiPResourceVO);
            uiPResourceVO
                    .setCycleBeginDate(pResource.getCycleBeginDate()!= null ? pResource.getCycleBeginDate().getTime() : null);
            uiPResourceVO.setCycleEndDate(pResource.getCycleEndDate()!= null ? pResource.getCycleBeginDate().getTime() : null);
            uiPResourceVOList.add(uiPResourceVO);
        }
        return uiPResourceVOList;
    }
    
    public PageInfo<UIPResourceVO> transformPResourcePageInfoToUIPResourceVOPageInfo(PageInfo<PResource> pageInfo)
    {
        PageInfo<UIPResourceVO> uiPageInfo = new PageInfo<UIPResourceVO>();
        if (pageInfo== null|| ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        BeanUtils.copyProperties(pageInfo, uiPageInfo);
        List<UIPResourceVO> uiPResourceVOList = new ArrayList<UIPResourceVO>();
        for (PResource pResource : pageInfo.getResult())
        {
            UIPResourceVO uiPResourceVO = new UIPResourceVO();
            BeanUtils.copyProperties(pResource, uiPResourceVO);
            if (pResource.getCycleBeginDate()!= null)
            {
                uiPResourceVO.setCycleBeginDate(pResource.getCycleBeginDate().getTime());
            }
            if (pResource.getCycleEndDate()!= null)
            {
                uiPResourceVO.setCycleEndDate(pResource.getCycleBeginDate().getTime());
            }
            uiPResourceVOList.add(uiPResourceVO);
        }
        uiPageInfo.setResult(uiPResourceVOList);
        return uiPageInfo;
    }
    
    public List<UIProductBasicListVO> transformProductVOListToUIProductBasicListVOList(
            List<ProductBasicInfoVO> productBasicInfoVOList)
    {
        List<UIProductBasicListVO> uiProductBasicListVOlist = new ArrayList<UIProductBasicListVO>();
        for (ProductBasicInfoVO productBasicInfoVO : productBasicInfoVOList)
        {
            UIProductBasicListVO uiProductBasicListVO = new UIProductBasicListVO();
            BeanUtils.copyProperties(productBasicInfoVO, uiProductBasicListVO);
            uiProductBasicListVOlist.add(uiProductBasicListVO);
        }
        return uiProductBasicListVOlist;
    }
}
