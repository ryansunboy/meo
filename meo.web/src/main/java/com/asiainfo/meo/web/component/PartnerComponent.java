package com.asiainfo.meo.web.component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.constant.PartnerContractErrorConstant;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPricesVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractVO;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.profile.app.model.vo.StaffInfoVO;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.web.partner.model.vo.UIContractInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractPriceInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractPriceVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractPricesVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractProfileVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractQueryVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractVO;
import com.asiainfo.meo.web.partner.model.vo.UIPartnerInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIPriceInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIPriceVO;
import com.asiainfo.meo.web.partner.model.vo.UISimpleContractVO;
import com.asiainfo.meo.web.partner.model.vo.UIStaffInfoVO;

public class PartnerComponent
{
    @Resource
    private PartnerPserviceBO partnerPserviceBO;
    
    @Resource
    private SystemPserviceBO  systemPserviceBO;
    
    private static final Log  LOG = LogFactory.getLog(PartnerComponent.class);
    
    /**
     * @Description: PartnerInfo 查询条件 映射到 partnerInfoVO
     * @Description: PartnerInfo condition mapping partnerInfoVO
     * @author zhaozx
     * @param partnerInfoConditionList
     * @return
     */
    public List<PartnerInfoVO> transformPartnerInfoConditionToPartnerInfoVO(List<UIPartnerInfoVO> partnerInfoConditionList)
    {
        LOG.debug("partnerInfo condition   mapping  partnerInfoVO  begin!");
        List<PartnerInfoVO> partnerInfoVOList = new ArrayList<PartnerInfoVO>();
        if (ValidateUtil.isEmpty(partnerInfoConditionList))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"partnerInfoConditionList" });
        }
        for (UIPartnerInfoVO partnerInfoCondition : partnerInfoConditionList)
        {
            PartnerInfoVO partnerInfoVO = new PartnerInfoVO();
            Long partnerId = partnerInfoCondition.getPartnerId();
            String taxId = partnerInfoCondition.getTaxId();
            String businessId = partnerInfoCondition.getBusinessId();
            if (ValidateUtil.isNull(partnerId)&& ValidateUtil.isEmpty(taxId)&& ValidateUtil.isEmpty(businessId))
                throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                        new Object[]{"partnerId && taxId && businessId" });
            if (ValidateUtil.isNotNull(partnerId))
                partnerInfoVO.setPartnerId(partnerId);
            if (ValidateUtil.isNotEmpty(taxId))
                partnerInfoVO.setTaxCode(taxId);
            if (ValidateUtil.isNotEmpty(businessId))
                partnerInfoVO.setBusinessCode(businessId);
            partnerInfoVOList.add(partnerInfoVO);
        }
        LOG.debug("partnerInfo condition   mapping  partnerInfoVO  end!");
        return partnerInfoVOList;
    }
    
    /**
     * @Description: 将partner信息转换为partner list
     * @Description: transform partner profile to partner list
     * @author zhaozx
     * @param partnerProfileList
     * @return
     */
    public PageInfo<UIPartnerInfoVO> transformPartnerProfilePageInfoToPartnerPageInfo(
            PageInfo<PartnerProfileVO> partnerProfilePageInfo)
    {
        LOG.debug("partner profile to partnerList begin!");
        PageInfo<UIPartnerInfoVO> pageInfo = new PageInfo<UIPartnerInfoVO>();
        List<UIPartnerInfoVO> partnerInfoList = new ArrayList<UIPartnerInfoVO>();
        if (ValidateUtil.isNull(partnerProfilePageInfo)|| ValidateUtil.isEmpty(partnerProfilePageInfo.getResult()))
        {
            return pageInfo.emptyPageInfo();
        }
        for (PartnerProfileVO partnerProfile : partnerProfilePageInfo.getResult())
        {
            UIPartnerInfoVO partnerInfoUIVO = new UIPartnerInfoVO();
            Partner partner = partnerProfile.getPartner();// get partner
            if (ValidateUtil.isNotNull(partner))
            {
                partnerInfoUIVO.setPartnerId(partner.getPartnerId());
                partnerInfoUIVO.setCreateDate(partner.getCreateDate().getTime());
                partnerInfoUIVO.setSts(partner.getSts());// set partner status
                partnerInfoUIVO.setDescription(partner.getStsName());
            }
            PartnerInfo partnerInfo = partnerProfile.getPartnerInfo();// get partner information
            partnerInfoUIVO.setCompanyName(partnerInfo.getCompanyName());
            partnerInfoUIVO.setTaxId(partnerInfo.getTaxCode());
            partnerInfoUIVO.setBusinessId(partnerInfo.getBusinessCode());
            partnerInfoUIVO.setContractId(partnerProfile.getContractId());
            partnerInfoUIVO.setContractNo(partnerProfile.getContractNo());
            partnerInfoList.add(partnerInfoUIVO);
        }
        BeanUtils.copyProperties(partnerProfilePageInfo, pageInfo);
        pageInfo.setResult(partnerInfoList);
        LOG.debug("partner profile to partnerList end!");
        return pageInfo;
    }
    
    /**
     * @Description: PartnerInfo 映射 partnerInfoVO
     * @Description: PartnerInfo mapping partnerInfoVO
     * @author zhaozx
     * @param partnerInfoUIVO 前端传过来需要修改的partner信息
     * @return
     */
    public PartnerInfoVO transformPartnerInfoUIVOToPartnerInfoVO(UIPartnerInfoVO partnerInfoUIVO)
    {
        LOG.debug(" modify partnerInfo    mapping  partnerInfoVO  begin!");
        if (ValidateUtil.isEmpty(partnerInfoUIVO))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"UIPartnerInfoVO" });
        PartnerInfoVO partnerInfoVO = new PartnerInfoVO();
        partnerInfoVO.setPartnerId(partnerInfoUIVO.getPartnerId());
        if (ValidateUtil.isNotNull(partnerInfoUIVO.getPartnerType()))
            partnerInfoVO.setPartnerType(partnerInfoUIVO.getPartnerType());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getCompanyName()))
            partnerInfoVO.setCompanyName(partnerInfoUIVO.getCompanyName());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getEmailAddr()))
            partnerInfoVO.setEmailAddr(partnerInfoUIVO.getEmailAddr());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getMobileNo()))
            partnerInfoVO.setMsisdn(partnerInfoUIVO.getMobileNo());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getContactName()))
            partnerInfoVO.setContactName(partnerInfoUIVO.getContactName());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getContactMobileNo()))
            partnerInfoVO.setContactMsidn(partnerInfoUIVO.getContactMobileNo());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getContactEmail()))
            partnerInfoVO.setContactEmail(partnerInfoUIVO.getContactEmail());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getBillAddr()))
            partnerInfoVO.setBillAddr(partnerInfoUIVO.getBillAddr());
        if (ValidateUtil.isNotNull(partnerInfoUIVO.getPostCode()))
        {
            long postCode = partnerInfoUIVO.getPostCode();
            partnerInfoVO.setPostCode(postCode);
        }
        if (ValidateUtil.isNotNull(partnerInfoUIVO.getCountryCode()))
            partnerInfoVO.setCountryCode(partnerInfoUIVO.getCountryCode());
        if (ValidateUtil.isNotNull(partnerInfoUIVO.getCityCode()))
            partnerInfoVO.setCityCode(partnerInfoUIVO.getCityCode());
        if (ValidateUtil.isNotNull(partnerInfoUIVO.getProvCode()))
            partnerInfoVO.setProvinceCode(partnerInfoUIVO.getProvCode());
        if (ValidateUtil.isNotEmpty(partnerInfoUIVO.getRegistrationForm()))
            partnerInfoVO.setRegistrationForm(partnerInfoUIVO.getRegistrationForm());
        LOG.debug("modify partnerInfo    mapping  partnerInfoVO  end!");
        return partnerInfoVO;
    }
    
    /**
     * @Description: PartnerInfoUIVO映射成partnerProfileVO
     * @Description: PartnerInfoUIVO mapping partnerProfileVO
     * @author zhaozx
     * @param partnerInfoReq 前端传过来需要保存的partner信息
     * @return
     * @throws ParseException
     */
    public PartnerProfileVO transformPartnerInfoUIVOToPartnerProfile(UIPartnerInfoVO signUpPartnerInfoReq) throws ParseException
    {
        LOG.info("signUp   partnerInfo mapping  partner profile  begin!");
        
        PartnerProfileVO partnerProfileVO = new PartnerProfileVO();
        Partner partner = new Partner();
        PartnerInfo partnerInfo = new PartnerInfo();
        Timestamp expiredDate = DateTimeUtil.getDateTimeTimestamp("9999-12-31 23:59:59");
        partnerInfo.setExpiredDate(expiredDate);
        partner.setPartnerType(signUpPartnerInfoReq.getPartnerType());
        partnerInfo.setCompanyName(signUpPartnerInfoReq.getCompanyName());
        partnerInfo.setTaxCode(signUpPartnerInfoReq.getTaxId());
        partnerInfo.setBusinessCode(signUpPartnerInfoReq.getBusinessId());
        partnerInfo.setEmailAddr(signUpPartnerInfoReq.getEmailAddr());
        partnerInfo.setMsisdn(signUpPartnerInfoReq.getMobileNo());
        partnerInfo.setContactName(signUpPartnerInfoReq.getContactName());
        partnerInfo.setContactMsidn(signUpPartnerInfoReq.getContactMobileNo());
        partnerInfo.setContactEmail(signUpPartnerInfoReq.getContactEmail());
        if (ValidateUtil.isNotEmpty(signUpPartnerInfoReq.getBillAddr()))
            partnerInfo.setBillAddr(signUpPartnerInfoReq.getBillAddr());
        if (ValidateUtil.isNotNull(signUpPartnerInfoReq.getPostCode()))
            partnerInfo.setPostCode(Long.valueOf(signUpPartnerInfoReq.getPostCode()));
        if (ValidateUtil.isNotNull(signUpPartnerInfoReq.getCountryCode()))
            partnerInfo.setCountryCode(signUpPartnerInfoReq.getCountryCode());
        if (ValidateUtil.isNotNull(signUpPartnerInfoReq.getCityCode()))
            partnerInfo.setCityCode(signUpPartnerInfoReq.getCityCode());
        if (ValidateUtil.isNotNull(signUpPartnerInfoReq.getProvCode()))
            partnerInfo.setProvinceCode(signUpPartnerInfoReq.getProvCode());
        partnerProfileVO.setPartner(partner);
        partnerProfileVO.setPartnerInfo(partnerInfo);
        LOG.info("signUp   partnerInfo  mapping  partner profile  end!");
        return partnerProfileVO;
    }
    
    /**
     * @Description: 转换partnerProfile到partnerInfoUIVO
     * @Description: partnerProfile mapping partnerInfoUIVO
     * @author zhaozx
     * @param partnerProfileList
     * @return
     */
    public List<UIPartnerInfoVO> transformPartnerProfileToPartnerInfoUIVO(List<PartnerProfileVO> partnerProfileList)
    {
        LOG.debug("partnerProfile mapping PartnerInfoUIVO begin!");
        
        List<UIPartnerInfoVO> partnerInfoUIVOList = new ArrayList<UIPartnerInfoVO>();
        if (ValidateUtil.isNotEmpty(partnerProfileList))
        {
            for (PartnerProfileVO partnerProfileVO : partnerProfileList)
            {
                UIPartnerInfoVO partnerInfo = createUIPartnerInfoVo(partnerProfileVO);
                partnerInfoUIVOList.add(partnerInfo);
            }
        }
        LOG.debug("partnerProfile mapping PartnerInfoUIVO end!");
        return partnerInfoUIVOList;
    }
    
    private UIPartnerInfoVO createUIPartnerInfoVo(PartnerProfileVO partnerProfileVO)
    {
        UIPartnerInfoVO getPartnerInfoRes = new UIPartnerInfoVO();
        Partner partner = partnerProfileVO.getPartner();// get partner
        PartnerInfo partnerInfo = partnerProfileVO.getPartnerInfo();// get partner information
        getPartnerInfoRes.setRegisterFileUrl(partnerProfileVO.getRegisterFileUrl());
        getPartnerInfoRes.setPartnerId(partner.getPartnerId());
        getPartnerInfoRes.setPartnerType(partner.getPartnerType());
        getPartnerInfoRes.setCompanyName(partnerInfo.getCompanyName());
        getPartnerInfoRes.setTaxId(partnerInfo.getTaxCode());
        getPartnerInfoRes.setBusinessId(partnerInfo.getBusinessCode());
        getPartnerInfoRes.setEmailAddr(partnerInfo.getEmailAddr());
        getPartnerInfoRes.setMobileNo(partnerInfo.getMsisdn());
        if (ValidateUtil.isNotEmpty(partnerInfo.getContactName()))
            getPartnerInfoRes.setContactName(partnerInfo.getContactName());
        if (ValidateUtil.isNotEmpty(partnerInfo.getContactMsidn()))
            getPartnerInfoRes.setContactMobileNo(partnerInfo.getContactMsidn());
        if (ValidateUtil.isNotEmpty(partnerInfo.getContactEmail()))
            getPartnerInfoRes.setContactEmail(partnerInfo.getContactEmail());
        if (ValidateUtil.isNotEmpty(partnerInfo.getBillAddr()))
            getPartnerInfoRes.setBillAddr(partnerInfo.getBillAddr());
        if (ValidateUtil.isNotNull(partnerInfo.getPostCode()))
        {
            long postCode = partnerInfo.getPostCode();
            getPartnerInfoRes.setPostCode((int) postCode);
        }
        if (ValidateUtil.isNotNull(partnerInfo.getCountryCode()))
            getPartnerInfoRes.setCountryCode(partnerInfo.getCountryCode());
        if (ValidateUtil.isNotNull(partnerInfo.getCityCode()))
            getPartnerInfoRes.setCityCode(partnerInfo.getCityCode());
        if (ValidateUtil.isNotNull(partnerInfo.getProvinceCode()))
            getPartnerInfoRes.setProvCode(partnerInfo.getProvinceCode());
        getPartnerInfoRes.setSts(partner.getSts());
        getPartnerInfoRes.setDescription(partner.getStsName());
        return getPartnerInfoRes;
    }
    
    /**
     * @Description: partnerProfileVO映射成getPartnerInfoRes
     * @Description: partnerProfileVO mapping getPartnerInfoRes
     * @author zhaozx
     * @param partnerPrifile
     * @return
     */
    public UIPartnerInfoVO transformPartnerProfileToPartnerInfoUIVO(PartnerProfileVO partnerProfile)
    {
        LOG.debug("partnerProfile   mapping  PartnerInfo   begin!");
        
        UIPartnerInfoVO partnerInfoRe = new UIPartnerInfoVO();
        Partner partner = partnerProfile.getPartner();// get partner
        PartnerInfo partnerInfo = partnerProfile.getPartnerInfo();// get partner information
        partnerInfoRe.setPartnerType(partner.getPartnerType());
        partnerInfoRe.setCompanyName(partnerInfo.getCompanyName());
        partnerInfoRe.setTaxId(partnerInfo.getTaxCode());
        partnerInfoRe.setBusinessId(partnerInfo.getBusinessCode());
        partnerInfoRe.setEmailAddr(partnerInfo.getEmailAddr());
        partnerInfoRe.setMobileNo(partnerInfo.getMsisdn());
        if (ValidateUtil.isNotEmpty(partnerInfo.getContactName()))
            partnerInfoRe.setContactName(partnerInfo.getContactName());
        if (ValidateUtil.isNotEmpty(partnerInfo.getContactMsidn()))
            partnerInfoRe.setContactMobileNo(partnerInfo.getContactMsidn());
        if (ValidateUtil.isNotEmpty(partnerInfo.getContactEmail()))
            partnerInfoRe.setContactEmail(partnerInfo.getContactEmail());
        if (ValidateUtil.isNotEmpty(partnerInfo.getBillAddr()))
            partnerInfoRe.setBillAddr(partnerInfo.getBillAddr());
        if (ValidateUtil.isNotNull(partnerInfo.getPostCode()))
        {
            long postCode = partnerInfo.getPostCode();
            partnerInfoRe.setPostCode((int) postCode);
        }
        if (ValidateUtil.isNotNull(partnerInfo.getCountryCode()))
            partnerInfoRe.setCountryCode(partnerInfo.getCountryCode());
        if (ValidateUtil.isNotNull(partnerInfo.getCityCode()))
            partnerInfoRe.setCityCode(partnerInfo.getCityCode());
        if (ValidateUtil.isNotNull(partnerInfo.getProvinceCode()))
            partnerInfoRe.setProvCode(partnerInfo.getProvinceCode());
        if (ValidateUtil.isNotEmpty(partnerProfile.getAvatarUrl()))
            partnerInfoRe.setAvatarUrl(partnerProfile.getAvatarUrl());
        if (ValidateUtil.isNotEmpty(partnerProfile.getPartnerTypeDescription()))
            partnerInfoRe.setPartnerTypeName(partnerProfile.getPartnerTypeDescription());
        LOG.debug("partnerProfile entity  mapping  getPartnerInfoRes  entity end!");
        return partnerInfoRe;
    }
    
    /**
     * @Description: 将partner联系人信息 映射 PartnerInfoVO
     * @Description: partner contact mapping partnerInfoVO
     * @author zhaozx
     * @param modifyPartnerInfoReq
     * @return
     */
    public PartnerInfoVO transformPartnerContactInfoToPartnerInfoVO(UIPartnerInfoVO modifyPartnerContactInfoReq)
    {
        PartnerInfoVO partnerInfoVO = new PartnerInfoVO();
        LOG.debug("modify PartnerContactInfo  Mapping PartnerInfoVO  begin!");
        Long partnerId = modifyPartnerContactInfoReq.getPartnerId();
        partnerInfoVO.setPartnerId(partnerId);
        if (ValidateUtil.isNotEmpty(modifyPartnerContactInfoReq.getContactName()))
            partnerInfoVO.setContactName(modifyPartnerContactInfoReq.getContactName());
        if (ValidateUtil.isNotEmpty(modifyPartnerContactInfoReq.getContactMobileNo()))
            partnerInfoVO.setContactMsidn(modifyPartnerContactInfoReq.getContactMobileNo());
        if (ValidateUtil.isNotEmpty(modifyPartnerContactInfoReq.getContactEmail()))
            partnerInfoVO.setContactEmail(modifyPartnerContactInfoReq.getContactEmail());
        if (ValidateUtil.isNotEmpty(modifyPartnerContactInfoReq.getAvatarUrl()))
            partnerInfoVO.setAvatarUrl(modifyPartnerContactInfoReq.getAvatarUrl());
        LOG.debug("modify PartnerContactInfo   Mapping PartnerInfoVO  end!");
        return partnerInfoVO;
    }
    
    public ContractPriceVO transformCreateContractPriceVO(UIContractPriceVO contractPriceUIVO, Long userId)
    {
        ContractPriceVO contractPriceVO = new ContractPriceVO();
        UIContractVO contractUIVO = contractPriceUIVO.getContract();
        if (ValidateUtil.isNull(contractUIVO.getValidDate()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new String[]{"validDate" });
        createContract(userId, contractPriceVO, contractUIVO);
        createPrices(contractPriceUIVO, contractPriceVO, contractUIVO);
        return contractPriceVO;
    }
    
    private void createPrices(UIContractPriceVO contractPriceUIVO, ContractPriceVO contractPriceVO, UIContractVO contractUIVO)
    {
        List<UIPriceVO> priceUIVOList = contractPriceUIVO.getContractPrices();
        List<Price> priceList = contractPriceVO.getPrices();
        Timestamp validDate = DateTimeUtil.getBeginDayTimestamp(contractUIVO.getValidDate());
        Timestamp expiredDate = DateTimeUtil.getEndDayTimestamp(contractUIVO.getExpiredDate());
        for (int i = 0; priceUIVOList!= null&& i< priceUIVOList.size(); i++ )
        {
            Price price = new Price();
            price.setUnitPrice(priceUIVOList.get(i).getUnitPrice());
            price.setUnit(priceUIVOList.get(i).getUnit());
            price.setValidDate(validDate);
            price.setExpiredDate(expiredDate);
            if (priceUIVOList.get(i).getPriceType()== Price.PRICE_TYPE_COIN)
            {
                price.setPriceType(Price.PRICE_TYPE_COIN);
            }
            else if (priceUIVOList.get(i).getPriceType()== Price.PRICE_TYPE_DATA_PACKAGE)
            {
                price.setPriceType(Price.PRICE_TYPE_DATA_PACKAGE);
            }
            priceList.add(price);
        }
        contractPriceVO.setPrices(priceList);
    }
    
    private void createContract(Long userId, ContractPriceVO contractPriceVO, UIContractVO contractUIVO)
    {
        Contract contract = new Contract();
        Timestamp validDate = DateTimeUtil.getBeginDayTimestamp(contractUIVO.getValidDate());
        contract.setValidDate(validDate);
        Long expiredDate = contractUIVO.getExpiredDate();
        if (ValidateUtil.isNull(expiredDate))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new String[]{"expiredDate" });
        contract.setExpiredDate(DateTimeUtil.getEndDayTimestamp(expiredDate));
        contract.setPartnerId(contractUIVO.getPartnerId());
        contract.setContractNo(contractUIVO.getContractNo());
        contract.setContractName(contractUIVO.getContractName());
        contract.setBuyers(contractUIVO.getBuyers());
        contract.setSellers(contractUIVO.getSellers());
        contract.setSignedPlace(contractUIVO.getSignedPlace());
        if (ValidateUtil.isNotNull(contractUIVO.getSignedDate()))
        {
            contract.setSignedDate(new Timestamp(contractUIVO.getSignedDate()));
        }
        else
        {
            contract.setSignedDate(DateTimeUtil.getNow());
        }
        contract.setOperatorId(userId);
        contract.setTempleteId(contractUIVO.getTempleteId());
        contractPriceVO.setContract(contract);
    }
    
    public List<UIContractPriceVO> getContract(UIContractQueryVO uIContractQueryVo) throws ParseException
    {
        Long userId = BoContext.getBoContext().getUserId();
        Integer objectType = systemPserviceBO.getObjectTypeByObjectId(userId);
        // userId = 100000031000L;
        List<Long> partnerIdList = new ArrayList<Long>();
        // userId is partnerId
        if (SysPortalUser.OBJECT_TYPE_PARTNER== objectType)
        {
            partnerIdList.add(userId);
        }
        else if (SysPortalUser.OBJECT_TYPE_SYSTEM== objectType)
        {
            // userId is saleId
            partnerIdList = partnerPserviceBO.getPartnerId(userId);
        }
        List<ContractPriceVO> contractPriceList = partnerPserviceBO.getPartnerContract(partnerIdList,
                uIContractQueryVo.getValidDate(), uIContractQueryVo.getExpiredDate(), uIContractQueryVo.getContractNo(),
                uIContractQueryVo.getContractName(), uIContractQueryVo.getPageSize(), uIContractQueryVo.getPageNo(),
                uIContractQueryVo.getPartnerId());
        List<UIContractPriceVO> contractPriceUIVOList = new ArrayList<UIContractPriceVO>();
        if (ValidateUtil.isNotEmpty(contractPriceList))
        {
            for (ContractPriceVO contractPriceVO : contractPriceList)
            {
                Contract contract = contractPriceVO.getContract();
                if (ValidateUtil.isEmpty(contract))
                    throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                            new Object[]{PrmConstantDefine.CONTRACT });
                if (ValidateUtil.isEmpty(contract.getContractId()))
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                            new Object[]{PrmConstantDefine.CONTRACT_ID });
                if (ValidateUtil.isEmpty(contract.getPartnerId()))
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{contract.getPartnerId() });
                if (ValidateUtil.isEmpty(contract.getContractNo()))
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                            new Object[]{contract.getContractNo() });
                if (ValidateUtil.isEmpty(contract.getOperatorId()))
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                            new Object[]{contract.getOperatorId() });
                if (ValidateUtil.isEmpty(contract.getValidDate()))
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{contract.getValidDate() });
                if (ValidateUtil.isEmpty(contract.getExpiredDate()))
                    throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                            new Object[]{contract.getExpiredDate() });
                UIContractVO contractUIVO = createUIContractVo(contract);
                UIContractPriceVO contractPriceUIVO = new UIContractPriceVO();
                contractPriceUIVO.setContract(contractUIVO);
                List<Price> prices = contractPriceVO.getPrices();
                for (int i = 0; i< prices.size(); i++ )
                {
                    Price price = prices.get(i);
                    UIPriceVO priceUIVO = createPrice(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), price);
                    List<UIPriceVO> priceUIVOList = contractPriceUIVO.getContractPrices();
                    if (ValidateUtil.isEmpty(priceUIVOList))
                        priceUIVOList = new ArrayList<UIPriceVO>();
                    if (ValidateUtil.isNotEmpty(priceUIVO))
                        priceUIVOList.add(priceUIVO);
                    contractPriceUIVO.setContractPrices(priceUIVOList);
                }
                contractPriceUIVOList.add(contractPriceUIVO);
            }
        }
        return contractPriceUIVOList;
        
    }
    
    private UIContractVO createUIContractVo(Contract contract)
    {
        UIContractVO contractUIVO = new UIContractVO();
        contractUIVO.setContractId(contract.getContractId());
        contractUIVO.setContractName(contract.getContractName());
        contractUIVO.setContractNo(contract.getContractNo());
        contractUIVO.setContractUrl(contract.getContractUrl());
        contractUIVO.setPartnerId(contract.getPartnerId());
        contractUIVO.setTempleteId(contract.getTempleteId());
        contractUIVO.setBuyers(contract.getBuyers());
        contractUIVO.setSellers(contract.getSellers());
        if (ValidateUtil.isNotEmpty(contract.getSignedDate()))
            contractUIVO.setSignedDate(contract.getSignedDate().getTime());
        contractUIVO.setSignedPlace(contract.getSignedPlace());
        contractUIVO.setValidDate(contract.getValidDate().getTime());
        contractUIVO.setExpiredDate(contract.getExpiredDate().getTime());
        contractUIVO.setStatus(contract.getSts());
        contractUIVO.setSaleId(contract.getOperatorId());
        contractUIVO.setOperatorId(contract.getOperatorId());
        return contractUIVO;
    }
    
    private UIPriceVO createPrice(DateFormat df, Price price)
    {
        if (ValidateUtil.isEmpty(price))
            return null;
        UIPriceVO priceUIVO = new UIPriceVO();
        priceUIVO.setUnit(price.getUnit());
        priceUIVO.setUnitPrice(price.getUnitPrice());
        priceUIVO.setPriceType(price.getPriceType());
        priceUIVO.setStatus(price.getSts());
        priceUIVO.setValidDate(df.format(price.getValidDate()));
        priceUIVO.setExpiredDate(df.format(price.getExpiredDate()));
        return priceUIVO;
    }
    
    public Contract getContractFromContractId(long contractId)
    {
        return partnerPserviceBO.getContract(contractId);
    }
    
    public Contract getContract(Long contractId)
    {
        return partnerPserviceBO.getContract(contractId);
    }
    
    public List<Price> getContractPrice(Long contractId)
    {
        return partnerPserviceBO.getContractPrice(contractId);
    }
    
    public void updateContractPriceVO(Long contractId, ContractPriceVO contractPriceVO)
    {
        partnerPserviceBO.updateContractPrice(contractId, contractPriceVO);
    }
    
    public ContractPriceVO transferContractInfoUIVOToContractPrice(Long contractId, Contract contract,
            UIContractInfoVO contractInfoUIVO) throws ParseException
    {
        if (ValidateUtil.isEmpty(contractInfoUIVO))
            return null;
        ContractPriceVO contractPriceVO = new ContractPriceVO();
        UIContractProfileVO contractProfileUIVO = contractInfoUIVO.getContractInfo();
        if (ValidateUtil.isEmpty(contractProfileUIVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.UI_CONTRACT_PROFILEVO });
        if (ValidateUtil.isNotEmpty(contractProfileUIVO.getContractName()))
            contract.setContractName(contractProfileUIVO.getContractName());
        if (ValidateUtil.isNotEmpty(contractProfileUIVO.getTempleteId()))
            contract.setTempleteId(contractProfileUIVO.getTempleteId());
        if (ValidateUtil.isNotEmpty(contractProfileUIVO.getValidDate()))
            contract.setValidDate(DateTimeUtil.getBeginDayTimestamp(contractProfileUIVO.getValidDate()));
        if (ValidateUtil.isNotEmpty(contractProfileUIVO.getExpiredDate()))
            contract.setExpiredDate(DateTimeUtil.getEndDayTimestamp(contractProfileUIVO.getExpiredDate()));
        contractPriceVO.setContract(contract);
        List<Price> priceList = getContractPrice(contractId);
        List<UIPriceInfoVO> pricetInfoUIVOList = contractInfoUIVO.getContractPrices();
        if (priceList.size()> 0&& pricetInfoUIVOList.size()> 0)
        {
            List<Price> prices = new ArrayList<Price>();
            for (Price price : priceList)
            {
                for (UIPriceInfoVO priceInfoUIVO : pricetInfoUIVOList)
                {
                    if ((priceInfoUIVO.getPriceType()== 1&& price.getPriceType()== 1)
                            || (priceInfoUIVO.getPriceType()== 2&& price.getPriceType()== 2))
                    {
                        if (ValidateUtil.isNotNull(priceInfoUIVO.getUnitPrice()))
                            price.setUnitPrice(priceInfoUIVO.getUnitPrice());
                        if (ValidateUtil.isNotNull(priceInfoUIVO.getUnit()))
                            price.setUnit(priceInfoUIVO.getUnit());
                        if (ValidateUtil.isNotNull(contractProfileUIVO.getValidDate()))
                            price.setValidDate(DateTimeUtil.getBeginDayTimestamp(contractProfileUIVO.getValidDate()));
                        if (ValidateUtil.isNotNull(contractProfileUIVO.getExpiredDate()))
                            price.setExpiredDate(DateTimeUtil.getEndDayTimestamp(contractProfileUIVO.getExpiredDate()));
                        prices.add(price);
                    }
                }
            }
            contractPriceVO.setPrices(prices);
        }
        return contractPriceVO;
    }
    
    public StaffInfoVO transformStaffInfoConditionToStaffInfoVO(UIStaffInfoVO staffCondition)
    {
        StaffInfoVO staffInfoVO = new StaffInfoVO();
        BeanUtils.copyProperties(staffCondition, staffInfoVO);
        return staffInfoVO;
    }
    
    public PageInfo<UIContractPriceInfoVO> transferContractPriceInfoUIContractPriceInfoVO(PageInfo<ContractPriceInfoVO> pageInfo)
    {
        final PageInfo<UIContractPriceInfoVO> uiPageInfo = new PageInfo<UIContractPriceInfoVO>();
        if (ValidateUtil.isEmpty(pageInfo)|| ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UIContractPriceInfoVO> uiContractPriceInfoVOList = new ArrayList<UIContractPriceInfoVO>();
        for (ContractPriceInfoVO contractPriceInfoVO : pageInfo.getResult())
        {
            UIContractPriceInfoVO uiContractPriceInfoVO = new UIContractPriceInfoVO();
            BeanUtils.copyProperties(contractPriceInfoVO, uiContractPriceInfoVO);
            List<UIContractPricesVO> uiContractPricesVOList = new ArrayList<UIContractPricesVO>();
            for (ContractPricesVO contractPricesVO : contractPriceInfoVO.getContractPrices())
            {
                UIContractPricesVO uiContractPricesVO = new UIContractPricesVO();
                BeanUtils.copyProperties(contractPricesVO, uiContractPricesVO);
                uiContractPricesVOList.add(uiContractPricesVO);
            }
            uiContractPriceInfoVOList.add(uiContractPriceInfoVO);
        }
        uiPageInfo.setResult(uiContractPriceInfoVOList);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        return uiPageInfo;
    }
    
    public ContractPriceInfoQueryConditionVO transformContractCriteriaToQueryConditionVO(final Long partnerId,
            final Integer pageNo, final Integer pageSize)
    {
        final ContractPriceInfoQueryConditionVO conditionVO = new ContractPriceInfoQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        conditionVO.setPartnerId(partnerId);
        return conditionVO;
    }
    
    public List<UISimpleContractVO> transferContractVOToUISimipleContractVO(List<ContractVO> contractList)
    {
        List<UISimpleContractVO> voList = new ArrayList<UISimpleContractVO>();
        if (ValidateUtil.isNotEmpty(contractList))
        {
            for (ContractVO contract : contractList)
            {
                UISimpleContractVO simpleContract = new UISimpleContractVO();
                BeanUtils.copyProperties(contract, simpleContract, new String[]{});
                voList.add(simpleContract);
            }
        }
        return voList;
    }
    
    public UIContractVO transferContractToUIContractVO(Contract contract)
    {
        if (ValidateUtil.isEmpty(contract))
        {
            return null;
        }
        UIContractVO uiContractVO = new UIContractVO();
        BeanUtils.copyProperties(contract, uiContractVO);
        return uiContractVO;
    }
    
    public UIContractPriceInfoVO transferContractPriceInfoToUIContractPriceInfoVO(ContractPriceInfoVO contractPriceInfoVO)
    {
        UIContractPriceInfoVO uiContractPriceInfoVO = new UIContractPriceInfoVO();
        BeanUtils.copyProperties(contractPriceInfoVO, uiContractPriceInfoVO);
        List<UIContractPricesVO> uiContractPricesVOList = new ArrayList<UIContractPricesVO>();
        for (ContractPricesVO contractPricesVO : contractPriceInfoVO.getContractPrices())
        {
            UIContractPricesVO uiContractPricesVO = new UIContractPricesVO();
            BeanUtils.copyProperties(contractPricesVO, uiContractPricesVO);
            uiContractPricesVOList.add(uiContractPricesVO);
        }
        uiContractPriceInfoVO.setContractPrices(uiContractPricesVOList);
        return uiContractPriceInfoVO;
    }
}
