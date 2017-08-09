package com.asiainfo.meo.prm.service.provide.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.prm.contract.app.bo.PartnerContractBO;
import com.asiainfo.meo.prm.contract.app.constant.PartnerContractErrorConstant;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractVO;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.file.app.bo.PartnerFileInfoBO;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.bo.PartnerProfileBO;
import com.asiainfo.meo.prm.profile.app.constant.PartnerProfileErrorConstant;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.profile.app.model.vo.StaffInfoVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmLoginVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmPartnerLoginVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmPasswordVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmTokenVO;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.prm.service.require.PartnerRserviceBO;
import com.asiainfo.meo.system.authority.app.model.entity.StaffInfo;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserVO;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;

public class PartnerPserviceBOImpl implements PartnerPserviceBO
{
    
    private static final Log  LOG = LogFactory.getLog(PartnerPserviceBOImpl.class);
    
    @Resource
    PartnerRserviceBO         prmRserviceBO;
    
    @Resource
    TokenBO                   tokenBo;
    
    @Resource
    PartnerProfileBO          partnerProfileBO;
    
    @Resource
    PartnerContractBO         partnerContractBO;
    
    @Resource
    private PartnerFileInfoBO partnerFileInfoBO;
    
    @Resource
    private SystemPserviceBO systemPserviceBO;
    
    public Contract savePartnerContract(ContractPriceVO contractPriceVO)
    {
        LOG.info("create partner contract and the contract price is new price begin!");
        return partnerContractBO.savePartnerContract(contractPriceVO);
    }
    
    @Override
    public PrmPartnerLoginVO loginForPartner(PrmLoginVO prmLogin)
    {
        UserLoginVO loginVo = prmRserviceBO.getPartnerLoginInfo(prmLogin.getLoginAcct());
        
        if (loginVo== null)
        {
            throw new MeoException(SysErrorCodeDefine.USER_DO_NOT_EXISTED);
        }
        Integer objectType = loginVo.getObjectType();
        Long objectId = loginVo.getObjectId();
        
        if (objectType== SysPortalUser.OBJECT_TYPE_PARTNER)
        {
            Partner prm = partnerProfileBO.getPartner(objectId);
            if (prm.getSts()!= Partner.STS_ACTIVE)
            {
                throw new MeoException(SysErrorCodeDefine.USER_IS_INACTIVE);
            }
        }
        else if (objectType== SysPortalUser.OBJECT_TYPE_SYSTEM)
        {
            SystemUserVO systemUser = systemPserviceBO.getSystemUser(objectId);
            if (systemUser.getSts()!= StaffInfo.STS_VALID)
            {
                throw new MeoException(SysErrorCodeDefine.USER_IS_INACTIVE);
            }
        }
        
        if (!loginVo.getLoginPwd().equals(prmLogin.getPassword()))
        {
            throw new MeoException(SysErrorCodeDefine.USER_PWD_ERROR);
        }
        prmRserviceBO.expiredAccessTokens(objectId);
        PrmTokenVO tokenVo = new PrmTokenVO();
        Token token = tokenBo.createToken(objectId);
        tokenVo.setAccessToken(token.getAccessToken());
        tokenVo.setAccessTokenExpired(token.getAccessTokenExpireTime());
        tokenVo.setRefreshToken(token.getRefreshToken());
        tokenVo.setRefreshTokenExpired(token.getRefreshTokenExpireTime());
        tokenVo.setSecretkey(token.getSecretKey());
        PrmPartnerLoginVO partner = new PrmPartnerLoginVO();
        partner.setToken(tokenVo);
        partner.setIsFirstLogin(loginVo.getIsFirstLogin());
        prmRserviceBO.updatePortalIsFirstLoginFlagToFalse(prmLogin.getLoginAcct());
        return partner;
    }
    
    public List<ContractPriceVO> getPartnerContract(List<Long> partnerIdList, Long validDate, Long expiredDate,
            String contractNo, String contractName, Integer pageSize, Integer pageNo, Long partnerId) throws ParseException
    {
        return partnerContractBO.getPartnerContract(partnerIdList, validDate, expiredDate, contractNo, contractName, pageSize,
                pageNo, partnerId);
    }
    
    public List<Long> getPartnerId(Long userId)
    {
        return partnerContractBO.getPartnerIdsBySaleId(userId);
    }
    
    public List<Contract> getContract(DetachedCriteria criteria, Integer firstResults, Integer maxResults)
    {
        return partnerContractBO.getContract(criteria, firstResults, maxResults);
    }
    
    public void resetPortalPassword(PrmPasswordVO password, Integer userType)
    {
        Long userId = BoContext.getBoContext().getUserId();
        
        if(userType.equals(1))
        {
            PartnerInfo partner = partnerProfileBO.getPartnerInfo(userId);
           
            if (ValidateUtil.isEmpty(partner))
            {
                throw new MeoException(PartnerProfileErrorConstant.PARTNER_IS_NOT_FOUND, new Object[]{userId });
            }
        }
        if (ValidateUtil.isNotEmpty(password))
        {
            /**
             * 24/07/2015: Nick : Comment below function because it's no need to check valid bundle acct.
             */
            // prmRserviceBO.validBundleAccount(partner.getContactEmail(), password.getValidPwd());
            SysPasswordVO sysPwd = new SysPasswordVO();
            sysPwd.setLoginAcct(password.getLoginAcct());
            sysPwd.setNewPwd(password.getNewPwd());
            sysPwd.setUserType(SysEnumCodeDefine.USER_TYPE_PORTAL);
            prmRserviceBO.updatePortalPassword(sysPwd);
            prmRserviceBO.expiredToken();
            
        }
        
    }
    
    /**
     * @Description: partner濞夈劌鍞�娣囨繂鐡╬artner profile
     * @Description: sign up partner,save partner profile
     * @author zhaozx
     * @param partnerProfile
     * @param saleId
     * @return
     */
    
    public long savePartnerProfile(PartnerProfileVO partnerProfile, Long saleId)
    {
        
        if (ValidateUtil.isNull(saleId))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.SALE_ID });
        }
        
        checkSignUpAcct(partnerProfile.getPartnerInfo().getEmailAddr());
        
        partnerProfileBO.savePartnerProfile(partnerProfile, saleId);// save partner info
        
        long partnerId = partnerProfile.getPartner().getPartnerId();
        
        UserLoginVO user = new UserLoginVO();
        user.setLoginAcct(partnerProfile.getPartnerInfo().getEmailAddr());
        user.setObjectId(partnerId);
        user.setObjectType(SysPortalUser.OBJECT_TYPE_PARTNER);
        
        prmRserviceBO.createPartnerAccount(user);
        
        return partnerId;
    }
    
    /**
     * @Description: (鏍规嵁partnerId鑾峰彇partner)
     * @Description: (get partner by partnerId)
     * @modifyReason:
     * @author zhengzy
     * @param partnerId
     */
    public Partner getPartner(Long partnerId)
    {
        if (ValidateUtil.isNull(partnerId))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        return partnerProfileBO.getPartner(partnerId);
    }
    
    /**
     * @Description: (婵�椿partner)
     * @Description: (active partner)
     * @modifyReason:
     * @author zhengzy
     * @param partnerId
     * @param oldSts
     * @param newSts
     */
    
    public void updatePartnerActive(long partnerId)
    {
        partnerProfileBO.updatePartnerSts(partnerId, Partner.STS_INACTIVE, Partner.STS_ACTIVE);
    }
    
    /**
     * @Description: (婵�椿contract)
     * @Description: (active contract)
     * @modifyReason:
     * @author zhengzy
     * @param contractId
     */
    
    public void updateContractActive(long contractId)
    {
        partnerContractBO.updateContractActive(contractId);
    }
    
    /**
     * @Description: 鏌ヨpartner淇℃伅
     * @Description: select partner info
     * @author zhaozx
     * @param partnerCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    
    public List<PartnerProfileVO> getPartnerInfo(List<PartnerInfoVO> partnerCondition, Integer pageSize, Integer pageNo)
    {
        if (ValidateUtil.isEmpty(partnerCondition))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{PrmConstantDefine.PARTNER_INFO_CONDITION });
        }
        return partnerProfileBO.getPartnerInfo(partnerCondition, pageSize, pageNo);
    }
    
    /**
     * @Description: 鏌ヨpartner list淇℃伅
     * @Description: select partner list
     * @author zhaozx
     * @param saleId
     * @param partnerId
     * @param companyName
     * @param pageSize
     * @param pageNo
     * @return
     */
    
    public PageInfo<PartnerProfileVO> getPartnerList(Long saleId, Long partnerId, String companyName, Integer pageSize, Integer pageNo)
    {
        if (ValidateUtil.isNull(saleId))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.SALE_ID });
        }
        return partnerProfileBO.getPartnerList(saleId, partnerId, companyName, pageSize, pageNo);
    }
    
    /**
     * @Description: 鑾峰緱partner profile
     * @Description: get partner profile
     * @author zhaozx
     * @param partnerId
     * @return
     */
    
    public PartnerProfileVO getPartnerProfile(Long partnerId)
    {
        if (ValidateUtil.isNull(partnerId))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        PartnerProfileVO partnerProfileVO = partnerProfileBO.getPartnerProfile(partnerId);
        /** 2015/08/07 Nick - added setting partner type description */
        if (ValidateUtil.isNotEmpty(partnerProfileVO) 
                && ValidateUtil.isNotEmpty(partnerProfileVO.getPartner())
                && ValidateUtil.isNotEmpty(partnerProfileVO.getPartner().getPartnerType()))
        {
            final List<EnumDefine> partnerTypeList = systemPserviceBO.getEnumByCode(
                    Integer.toString(partnerProfileVO.getPartner().getPartnerType()),
                    EnumDefine.PARTNER_TYPE);
            if (ValidateUtil.isNotEmpty(partnerTypeList) && 
                    ValidateUtil.isNotEmpty(partnerTypeList.get(0).getEnumName()))
            {
                partnerProfileVO.setPartnerTypeDescription(partnerTypeList.get(0).getEnumName());                
            }
        }
        
        // get partner avatar file info
        List<PartnerFileInfo> partnerFileList = partnerFileInfoBO.getPartnerFileInfo(partnerId, PartnerFileInfo.FILE_TYPE_AVATAR);
        
        if (ValidateUtil.isNotEmpty(partnerFileList))
        {
            partnerProfileVO.setAvatarUrl(partnerFileList.get(0).getFileUrl());
        }
        
        return partnerProfileVO;
    }
    
    /**
     * @Description: 淇敼partner鑱旂郴浜轰俊鎭�
     * @Description: update partner contact information
     * @author zhaozx
     * @param partnerInfoVO
     */
    
    public void updatePartnerContact(PartnerInfoVO partnerInfoVO)
    {
        if (ValidateUtil.isEmpty(partnerInfoVO))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        
        Long partnerId = partnerInfoVO.getPartnerId();
        
        if (ValidateUtil.isNull(partnerId))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        
        String contactName = partnerInfoVO.getCompanyName();
        String contactMobileNo = partnerInfoVO.getContactMsidn();
        String contactEmail = partnerInfoVO.getContactEmail();
        
        if (ValidateUtil.isNotEmpty(contactName)|| ValidateUtil.isNotEmpty(contactMobileNo)
                || ValidateUtil.isNotEmpty(contactEmail))
        {
            // update partner information
            partnerProfileBO.updatePartnerProfile(partnerInfoVO);
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getAvatarUrl()))
        {
            updatePartnerFile(partnerId, partnerInfoVO.getAvatarUrl(), PartnerFileInfo.FILE_TYPE_AVATAR);
        }
    }
    
    /**
     * @Description: 淇敼partner淇℃伅
     * @Description: update partner 淇℃伅
     * @author zhaozx
     * @param partnerInfoVO
     */
    
    public void updatePartnerProfile(PartnerInfoVO partnerInfoVO)
    {
        if (ValidateUtil.isEmpty(partnerInfoVO))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        
        Long partnerId = partnerInfoVO.getPartnerId();
        
        if (ValidateUtil.isNull(partnerId))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        if (ValidateUtil.isNotEmpty(partnerInfoVO))
        {
            partnerProfileBO.updatePartnerProfile(partnerInfoVO);
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getRegistrationForm()))
        {
            updatePartnerFile(partnerId, partnerInfoVO.getRegistrationForm(), PartnerFileInfo.FILE_TYPE_REGISTRATION_FORM);
        }
    }
    
    @Override
    public Contract getContract(long contractId)
    {
        return partnerContractBO.getContract(contractId);
    }
    
    @Override
    public List<Price> getContractPrice(Long contractId)
    {
        return partnerContractBO.getContractPrice(contractId);
    }
    
    @Override
    public void updateContractPrice(Long contractId, ContractPriceVO contractPriceVO)
    {
        partnerContractBO.updateContractPrice(contractId, contractPriceVO);
    }
    
    @Override
    public void updateContractActiveToDeactive(Long contractId)
    {
        partnerContractBO.updateContractActiveToDeactive(contractId);
    }
    
    public List<PartnerInfoVO> getAllPartnerInfo(Long partnerId)
    {
        return partnerProfileBO.getAllPartnerInfo(partnerId);
    }
    
    private void checkSignUpAcct(String loginAcct)
    {
        if (ValidateUtil.isEmpty(loginAcct))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_EMAIL });
        }
        
        UserLoginVO userLoginVO = prmRserviceBO.getPartnerLoginInfo(loginAcct);
        
        if (ValidateUtil.isNotEmpty(userLoginVO))
        {
            throw new MeoException(PartnerContractErrorConstant.HAS_EXISTED, new Object[]{PrmConstantDefine.PARTNER_EMAIL });
        }
    }
    
    /**
     * @Description: 修改partner文件
     * @Description: update partner URL
     * @author zhaozx
     * @param partnerId
     * @param url
     * @param fileType
     */
    private void updatePartnerFile(long partnerId, String url, int fileType)
    {
        if (ValidateUtil.isNotEmpty(url))
        {
            PartnerFileInfo partnerFileInfo = new PartnerFileInfo();
            partnerFileInfo.setFileType(fileType);
            partnerFileInfo.setFileUrl(url);
            partnerFileInfo.setPartnerId(partnerId);
            // update partner avatar
            partnerFileInfoBO.updateParterFileInfo(partnerFileInfo);
        }
    }
    
    @Override
    public Price addPrice(Price price)
    {
        return partnerContractBO.addPrice(price);
    }
    
    @Override
    public Price updatePrice(Price price)
    {
        return partnerContractBO.updatePrice(price);
    }
    
    @Override
    public void deletePrice(Long id)
    {
        partnerContractBO.deletePrice(id);
    }
    
    @Override
    public List<Price> getPricesByIds(Long[] ids)
    {
        return partnerContractBO.getPricesByIds(ids);
    }
    
    @Override
    public Price getPrice(Long priceId)
    {
        return partnerContractBO.getPrice(priceId);
    }
    
    @Override
    public Contract getContractByPartnerId(Long partnerId)
    {
        return partnerContractBO.getContractByPartnerId(partnerId);
    }
    
    @Override
    public void updateStaff(StaffInfoVO staff)
    {
        partnerProfileBO.updateStaff(staff);
    }
    
    @Override
    public PageInfo<ContractPriceInfoVO> getContractListById(final ContractPriceInfoQueryConditionVO conditionVO)
    {
        return partnerContractBO.getContractListById(conditionVO);
    }
    
    @Override
    public List<ContractVO> getContractListByPartnerId(final ContractPriceInfoQueryConditionVO conditionVO)
    {        
        return partnerContractBO.getContractListByPartnerId(conditionVO);
    }
    
    @Override
    public ContractPriceInfoVO getContractInfoByContractId(Long contractId)
    {
        return partnerContractBO.getContractInfoByContractId(contractId);
    }

    @Override
    public PageInfo<PartnerProfileVO> getAllPartnerList(Long partnerId, String companyName, Integer pageSize, Integer pageNo)
    {
        return partnerProfileBO.getAllPartnerList(partnerId, companyName, pageSize, pageNo);
    }

    @Override
    public List<Contract> getContractExpiredList()
    {
        return partnerContractBO.getContractExpiredList();
    }

    @Override
    public Contract updateContract(Contract contract)
    {
        return partnerContractBO.updateContract(contract);
    }

    @Override
    public PartnerInfo getPartnerInfo(Long partnerId)
    {
        return partnerProfileBO.getPartnerInfo(partnerId);
    }

    @Override
    public PartnerFileInfo getPartnerFileInfo(Long partnerId, int fileType)
    {
        List<PartnerFileInfo> partnerFileList = partnerFileInfoBO.getPartnerFileInfo(partnerId, PartnerFileInfo.FILE_TYPE_AVATAR);
        return ValidateUtil.isNotEmpty(partnerFileList)?partnerFileList.get(0):null;
    }
}
