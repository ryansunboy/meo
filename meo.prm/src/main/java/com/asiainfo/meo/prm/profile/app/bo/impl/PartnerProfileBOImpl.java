package com.asiainfo.meo.prm.profile.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.file.app.bo.PartnerFileInfoBO;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.bo.PartnerProfileBO;
import com.asiainfo.meo.prm.profile.app.constant.PartnerProfileErrorConstant;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfoHis;
import com.asiainfo.meo.prm.profile.app.model.entity.Staff;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.profile.app.model.vo.StaffInfoVO;
import com.asiainfo.meo.prm.profile.app.repository.PartnerInfoHisRepository;
import com.asiainfo.meo.prm.profile.app.repository.PartnerInfoRepository;
import com.asiainfo.meo.prm.profile.app.repository.PartnerRepository;
import com.asiainfo.meo.prm.profile.app.repository.StaffPartnerRelRepository;
import com.asiainfo.meo.prm.profile.app.repository.StaffRepository;
import com.asiainfo.meo.prm.service.require.PartnerRserviceBO;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class PartnerProfileBOImpl implements PartnerProfileBO
{
    
    private static final Log          LOG = LogFactory.getLog(PartnerProfileBOImpl.class);
    
    @Resource
    private PartnerInfoRepository     partnerInfoRepository;
    
    @Resource
    private PartnerInfoHisRepository  partnerInfoHisRepository;
    
    @Resource
    private PartnerRepository         partnerRepository;
    
    @Resource
    private StaffPartnerRelRepository staffPartnerRelRepository;
    
    @Resource
    PartnerRserviceBO                 prmRserviceBO;
    
    @Resource
    private StaffRepository           staffRepository;
    
    @Resource
    private PartnerFileInfoBO         partnerFileInfoBO;
    
    /**
     * @Description: 保存partner
     * @Description: save partner
     * @author zhaozx
     * @param partner
     */
    private void savePartner(Partner partner)
    {
        partnerRepository.savePartner(partner);
    }
    
    /**
     * @Description: 保存partner信息
     * @Description: save partnerInfo
     * @author zhaozx
     * @param partnerInfo
     */
    private void savePartnerInfo(PartnerInfo partnerInfo)
    {
        partnerInfoRepository.savePartnerinfo(partnerInfo);
    }
    
    /**
     * @Description: 初始化参数在保存partner profile时
     * @Description: init parameter when save partner profile
     * @author zhaozx
     * @param partner
     */
    private void initSavePartnerProfileParameter(Partner partner, PartnerInfo partnerInfo)
    {
        Timestamp modifyDate = DateTimeUtil.getNow();
        
        partner.setCreateDate(modifyDate);
        partner.setModifyDate(modifyDate);
        partner.setRegTime(modifyDate);
        partner.setSts(Partner.STS_INACTIVE);
        partner.setLevel(Partner.INIT_PARTNER_LEVEL);
        
        partnerInfo.setValidDate(modifyDate);
        
    }
    
    /**
     * @Description: 修改partner
     * @Description: update partner
     * @author zhaozx
     * @param partner
     */
    private void updatePartner(Partner partner)
    {
        partnerRepository.updatePartner(partner);
    }
    
    /**
     * @Description: 修改partner information
     * @Description: update partnerInfo
     * @modifyReason:
     * @author zhaozx
     * @param partnerInfo
     */
    private void updatePartnerInfo(PartnerInfo partnerInfo)
    {
        partnerInfoRepository.updatePartnerInfo(partnerInfo);
    }
    
    /**
     * @Description: 获得partner
     * @Description: get partner
     * @author zhaozx
     * @param partnerId
     * @return
     */
    public Partner getPartner(long partnerId)
    {
        return partnerRepository.getPartner(partnerId);
    }
    
    /**
     * @Description: 获得partner信息
     * @Description: get partnerInfo
     * @author zhaozx
     * @param partnerId
     */
    public PartnerInfo getPartnerInfo(long partnerId)
    {
        return partnerInfoRepository.getPartnerInfo(partnerId);
    }
    
    /**
     * @Description: 获得partner info list
     * @Description: get partner info list
     * @author zhaozx
     * @param selectCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    private List<PartnerInfo> getPartnerInfo(DetachedCriteria selectCondition, int pageSize, int pageNo)
    {
        return partnerInfoRepository.getPartnerInfo(selectCondition, pageSize, pageNo);
    }
    
    /**
     * @Description: 获得partner info list
     * @Description: 获得partner info list
     * @modifyReason:
     * @author zhaozx
     * @param selectCondition
     * @return
     */
    public List<PartnerInfo> getPartnerInfo(DetachedCriteria selectCondition)
    {
        return partnerInfoRepository.getPartnerInfo(selectCondition);
    }
    
    /**
     * @Description: 获得PartnerProfile通过PartnerInfo
     * @Description: get PartnerProfile by PartnerInfo
     * @author zhaozx
     * @param partnerInfoList
     * @return
     */
    private List<PartnerProfileVO> getPartnerProfile(List<PartnerInfo> partnerInfoList)
    {
        if (ValidateUtil.isEmpty(partnerInfoList))
        {
            return null;
        }
        List<PartnerProfileVO> partnerProfileVOList = new ArrayList<PartnerProfileVO>();
        for (PartnerInfo partnerInfo : partnerInfoList)
        {
            PartnerProfileVO partnerProfileVO = new PartnerProfileVO();
            Partner partner = getPartner(partnerInfo.getPartnerId());// get partner
            Contract contract = prmRserviceBO.getContractByPartnerId(partnerInfo.getPartnerId());
            List<PartnerFileInfo> partnerFileInfoList = partnerFileInfoBO.getPartnerFileInfo(partnerInfo.getPartnerId(),
                    PartnerFileInfo.FILE_TYPE_REGISTRATION_FORM);
            for (PartnerFileInfo partnerFileInfo : partnerFileInfoList)
            {
                partnerProfileVO.setRegisterFileUrl(partnerFileInfo.getFileUrl());
                break;
            }
            partnerProfileVO.setPartner(partner);// set partner
            partnerProfileVO.setPartnerInfo(partnerInfo);// set partner info
            partnerProfileVO.setContractId(contract!= null ? contract.getContractId() : null);
            partnerProfileVO.setContractNo(contract!= null ? contract.getContractNo() : null);
            partnerProfileVOList.add(partnerProfileVO);// add partner profile
        }
        return partnerProfileVOList;
    }
    
    /**
     * @Description: 保存partner历史信息
     * @Description: save partner history
     * @author zhaozx
     * @param partnerInfoHis partnerInfoHis瀹炰綋 partnerInfoHis entity
     */
    private void savePartnerInfoHis(PartnerInfoHis partnerInfoHis)
    {
        partnerInfoHisRepository.savePartnerInfoHis(partnerInfoHis);
    }
    
    /**
     * @Description: 复制partnerInfo到partner历史信息
     * @Description: copy partner information to partner history information
     * @author zhaozx
     * @param partnerInfo
     * @param partnerInfoHis
     */
    private PartnerInfoHis copyPartnerInfoToHistory(PartnerInfo partnerInfo, Timestamp modifyDate)
    {
        PartnerInfoHis partnerInfoHis = new PartnerInfoHis();
        partnerInfoHis.setBillAddr(partnerInfo.getBillAddr());
        partnerInfoHis.setBusinessCode(partnerInfo.getBusinessCode());// ??????
        partnerInfoHis.setCityCode(partnerInfo.getCityCode());
        partnerInfoHis.setCompanyName(partnerInfo.getCompanyName());
        partnerInfoHis.setContactEmail(partnerInfo.getContactEmail());
        partnerInfoHis.setContactMsidn(partnerInfo.getContactMsidn());
        partnerInfoHis.setContactName(partnerInfo.getContactName());
        partnerInfoHis.setCountryCode(partnerInfo.getCountryCode());
        partnerInfoHis.setEmailAddr(partnerInfo.getEmailAddr());
        partnerInfoHis.setExpiredDate(modifyDate);
        partnerInfoHis.setMsidn(partnerInfo.getMsisdn());
        partnerInfoHis.setPartnerId(partnerInfo.getPartnerId());
        partnerInfoHis.setPostCode(partnerInfo.getPostCode());
        partnerInfoHis.setProvinceCode(partnerInfo.getProvinceCode());
        partnerInfoHis.setTaxCode(partnerInfo.getTaxCode());// ??????
        partnerInfoHis.setValidDate(partnerInfo.getValidDate());
        return partnerInfoHis;
    }
    
    /**
     * @Description: 获得partner信息从partnerInfoVO里面
     * @Description: get partner from partnerInfoVO
     * @author zhaozx
     * @param partnerInfoVO partnerInfoVO entity
     */
    private void getPartnerFromPartnerInfoVO(PartnerInfoVO partnerInfoVO, Partner partner)
    {
        Timestamp modifyDate = DateTimeUtil.getNow();
        if (ValidateUtil.isNotNull(partnerInfoVO.getPartnerType()))
            partner.setPartnerType(partnerInfoVO.getPartnerType());
        partner.setModifyDate(modifyDate);
    }
    
    /**
     * @Description: 从artnerInfoVO获得partnerInfo
     * @Description: get partnerInfo from partnerInfoVO
     * @author zhaozx
     * @param partnerInfoVO partnerInfoVO entity
     */
    private void getPartnerInfoFromPartnerInfoVO(PartnerInfoVO partnerInfoVO, PartnerInfo partnerInfo)
    {
        Timestamp modifyDate = DateTimeUtil.getNow();
        
        partnerInfo.setValidDate(modifyDate);
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getBillAddr()))
        {
            partnerInfo.setBillAddr(partnerInfoVO.getBillAddr());
        }
        
        if (ValidateUtil.isNotNull(partnerInfoVO.getPostCode()))
        {
            partnerInfo.setPostCode(partnerInfoVO.getPostCode());
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getMsisdn()))
        {
            partnerInfo.setMsisdn(partnerInfoVO.getMsisdn());
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getCompanyName()))
        {
            partnerInfo.setCompanyName(partnerInfoVO.getCompanyName());
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getContactEmail()))
        {
            partnerInfo.setContactEmail(partnerInfoVO.getContactEmail());
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getContactMsidn()))
        {
            partnerInfo.setContactMsidn(partnerInfoVO.getContactMsidn());
        }
        
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getContactName()))
        {
            partnerInfo.setContactName(partnerInfoVO.getContactName());
        }
        if (ValidateUtil.isNotEmpty(partnerInfoVO.getEmailAddr()))
        {
            partnerInfo.setEmailAddr(partnerInfoVO.getEmailAddr());
        }
        if (ValidateUtil.isNotNull(partnerInfoVO.getCountryCode()))
        {
            partnerInfo.setCountryCode(partnerInfoVO.getCountryCode());
        }
        if (ValidateUtil.isNotNull(partnerInfoVO.getProvinceCode()))
        {
            partnerInfo.setProvinceCode(partnerInfoVO.getProvinceCode());
        }
        if (ValidateUtil.isNotNull(partnerInfoVO.getCityCode()))
        {
            partnerInfo.setCityCode(partnerInfoVO.getCityCode());
        }
    }
    
    /**
     * @Description: 检查tax Id and business id是否是唯一的
     * @Description: check tax id and business id is unique
     * @author zhaozx
     * @param taxCode
     * @param businessCode
     */
    private void checkTaxIdAndBusinessId(String taxCode, String businessCode)
    {
        if (ValidateUtil.isEmpty(taxCode))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{PrmConstantDefine.PARTNER_TAX_CODE });
        }
        
        if (ValidateUtil.isEmpty(businessCode))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{PrmConstantDefine.PARTNER_BUSINESS_CODE });
        }
        
        Timestamp modifyDate = DateTimeUtil.getNow();
        
        // select condition
        
        DetachedCriteria selectTaxCode = DetachedCriteria.forClass(PartnerInfo.class).add(Restrictions.eq("taxCode", taxCode))
                .add(Restrictions.gt("expiredDate", modifyDate));
        
        // check tax code unique
        List<PartnerInfo> checkTaxCodeList = partnerInfoRepository.getPartnerInfo(selectTaxCode);
        
        if (ValidateUtil.isNotEmpty(checkTaxCodeList))
        {
            for (PartnerInfo partnerInfo : checkTaxCodeList)
            {
                Partner partner = getPartner(partnerInfo.getPartnerId());
                if (partner.getSts()!= Partner.STS_DEACTIVE)
                {
                    throw new MeoException(PartnerProfileErrorConstant.HAS_DUPLICATED,
                            new Object[]{PrmConstantDefine.PARTNER_TAX_CODE });
                }
            }
        }
        else
        {
            // select partnerInfo condition for checking business code unique
            DetachedCriteria selectBusinessCode = DetachedCriteria.forClass(PartnerInfo.class)
                    .add(Restrictions.eq("businessCode", businessCode)).add(Restrictions.gt("expiredDate", modifyDate));
            
            // check business code unique
            List<PartnerInfo> checkBusinessCodeList = partnerInfoRepository.getPartnerInfo(selectBusinessCode);
            
            if (ValidateUtil.isNotEmpty(checkBusinessCodeList))
            {
                for (PartnerInfo partnerInfo : checkBusinessCodeList)
                {
                    Partner partner = getPartner(partnerInfo.getPartnerId());
                    if (partner.getSts()!= Partner.STS_DEACTIVE)
                    {
                        throw new MeoException(PartnerProfileErrorConstant.HAS_DUPLICATED,
                                new Object[]{PrmConstantDefine.PARTNER_BUSINESS_CODE });
                    }
                }
            }
        }
        
    }
    
    /**
     * @Description: 保存员工和partner的关系
     * @Description: save staff and partner relation
     * @author zhaozx
     * @param saleId
     * @param partnerId
     */
    private void saveStaffPartnerRel(long saleId, long partnerId)
    {
        StaffPartnerRel staffPartnerRel = new StaffPartnerRel();
        Timestamp createDate = DateTimeUtil.getNow();
        staffPartnerRel.setCreateDate(createDate);
        staffPartnerRel.setModifyDate(createDate);
        staffPartnerRel.setPartnerId(partnerId);
        staffPartnerRel.setStaffId(saleId);
        staffPartnerRel.setSts(StaffPartnerRel.STS_VALID);
        staffPartnerRel.setCreator(saleId);
        staffPartnerRel.setOwner(saleId);
        staffPartnerRelRepository.saveStaffPartnerRel(staffPartnerRel);
    }
    
    /**
     * @Description: 保存partner profile
     * @Description: save partner profile
     * @author zhaozx
     * @param partnerProfile
     * @param saleId
     */
    @Override
    public void savePartnerProfile(PartnerProfileVO partnerProfile, Long saleId)
    {
        LOG.info("create partner profile begin!");
        
        Partner partner = partnerProfile.getPartner();
        
        PartnerInfo partnerInfo = partnerProfile.getPartnerInfo();
        
        String taxCode = partnerInfo.getTaxCode();// tax id
        
        String businessCode = partnerInfo.getBusinessCode();// business id
        
        checkTaxIdAndBusinessId(taxCode, businessCode);// check tax id and business id is unique and valid
        
        initSavePartnerProfileParameter(partner, partnerInfo);// init partner and partner information
        
        savePartner(partner);// save partner
        
        partnerInfo.setPartnerId(partner.getPartnerId());// set partner id
        
        savePartnerInfo(partnerInfo);// save partnerInfo
        
        saveStaffPartnerRel(saleId, partner.getPartnerId()); // save staff and partner relation
        
        LOG.info("create partner profile end!");
        
    }
    
    /**
     * @Description: 获得partner profile
     * @Description: get partner profile
     * @author zhaozx
     * @param partnerId
     * @return
     */
    @Override
    public PartnerProfileVO getPartnerProfile(long partnerId)
    {
        LOG.debug("get partner profile begin!");
        PartnerProfileVO partnerProfile = new PartnerProfileVO();
        
        partnerProfile.setPartner(getPartner(partnerId));// get partner
        partnerProfile.setPartnerInfo(getPartnerInfo(partnerId));// get partnerInfo
        
        LOG.debug("get partner profile end!");
        
        return partnerProfile;
    }
    
    /**
     * @Description: partner失效
     * @Description: deactivate partner
     * @author zhaozx
     * @param partnerId
     */
    @Override
    public void deletePartner(long partnerId)
    {
        LOG.info("deactive partner begin!");
        Partner partner = getPartner(partnerId);
        
        if (partner== null)
        {
            LOG.info("partner is null when you deactive partner begin!");
            throw new MeoException(PartnerProfileErrorConstant.PARTNER_IS_NOT_FOUND, new Object[]{partnerId });
        }
        
        if (partner.getSts()== Partner.STS_DEACTIVE)
        {
            LOG.info("partner has expired when you deactive partner begin!");
            throw new MeoException(PartnerProfileErrorConstant.PARTNER_STATUS_HAS_EXPIRED, new Object[]{partnerId });
        }
        
        Timestamp modifyDate = DateTimeUtil.getNow();// get now date
        PartnerInfo partnerInfo = getPartnerInfo(partnerId);// get partner information
        partner.setSts(Partner.STS_DEACTIVE);// set expired
        partner.setModifyDate(modifyDate);// set modify date
        partnerInfo.setExpiredDate(modifyDate);// set partner info expired date
        updatePartner(partner);// update partner, set partner expired
        updatePartnerInfo(partnerInfo);// update partner information , set partnerinfo expired
        
        LOG.info("deactive partner end!");
    }
    
    /**
     * @Description: 淇敼鐘舵�
     * @Description: update status
     * @author zhaozx
     * @param partnerId partner id
     * @param oldSts old status
     * @param newSts new status
     */
    @Override
    public void updatePartnerSts(long partnerId, int oldSts, int newSts)
    {
        LOG.info("update partner sts begin!");
        if (oldSts== newSts)
        {
            LOG.error("update partner sts ,newSts is the same as oldSts");
            throw new MeoException("update partner sts ,newSts is the same as oldSts");
        }
        Timestamp modifyDate = DateTimeUtil.getNow();
        Partner partner = partnerRepository.getPartner(partnerId, oldSts);
        partner.setModifyDate(modifyDate);
        partner.setSts(newSts);
        partnerRepository.updatePartner(partner);
        LOG.info("update partner sts end!");
    }
    
    /**
     * @Description: 修改partner和partner information
     * @Description: update partner and partner information
     * @author zhaozx
     * @param partnerInfoVO partner info
     */
    @Override
    public void updatePartnerProfile(PartnerInfoVO partnerInfoVO)
    {
        LOG.debug("update partner profile begin!");
        
        Timestamp modifyDate = DateTimeUtil.getNow();
        
        Long partnerId = partnerInfoVO.getPartnerId();// get partner id
        
        if (ValidateUtil.isNull(partnerId))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        
        String emailAddress = partnerInfoVO.getEmailAddr();
        
        PartnerProfileVO partnerProfileVO = getPartnerProfile(partnerId);// select partner profile
        
        Partner partner = partnerProfileVO.getPartner();// get old partner entity
        
        PartnerInfo partnerInfo = partnerProfileVO.getPartnerInfo();// get old partner information
        
        if (ValidateUtil.isEmpty(partner)|| ValidateUtil.isEmpty(partnerInfo))
        {
            throw new MeoException(PartnerProfileErrorConstant.PARTNER_IS_NOT_FOUND, new Object[]{partnerInfoVO.getPartnerId() });
        }
        else if (partner.getSts()== Partner.STS_DEACTIVE)
        {
            throw new MeoException(PartnerProfileErrorConstant.PARTNER_STATUS_HAS_EXPIRED,
                    new Object[]{partnerInfoVO.getPartnerId() });
        }
        
        if (ValidateUtil.isNotEmpty(emailAddress)&& !emailAddress.equals(partnerInfo.getEmailAddr()))
        {
            prmRserviceBO.updatePartnerLoginAccount(partnerId, emailAddress);
        }
        
        getPartnerFromPartnerInfoVO(partnerInfoVO, partner);// get new partner
        
        PartnerInfoHis partnerInfoHis = copyPartnerInfoToHistory(partnerInfo, modifyDate);// set old partner information
        
        savePartnerInfoHis(partnerInfoHis);// save partner info history
        
        getPartnerInfoFromPartnerInfoVO(partnerInfoVO, partnerInfo);// set new partner informaton
        
        updatePartner(partner);// update partner
        
        updatePartnerInfo(partnerInfo);// update partner info
        
        LOG.debug("update partner profile end!");
    }
    
    /**
     * @Description: 查询partner信息
     * @Description: select partner info
     * @author zhaozx
     * @param partnerCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public List<PartnerProfileVO> getPartnerInfo(List<PartnerInfoVO> partnerCondition, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria selectCondition = DetachedCriteria.forClass(PartnerInfo.class);
        Disjunction orConnection = Restrictions.disjunction();// list condition use or connection
        for (PartnerInfoVO condition : partnerCondition)
        {
            Conjunction andConnection = Restrictions.conjunction();// parameter use and connection
            
            if (ValidateUtil.isNotNull(condition.getPartnerId()))
            {
                andConnection.add(Restrictions.sqlRestriction("CAST({alias}.PARTNER_ID AS CHAR) like ?",
                        "%"+ condition.getPartnerId()+ "%", StandardBasicTypes.STRING));
                // andConnection.add(Restrictions.like("partnerId", condition.getPartnerId().toString(),MatchMode.ANYWHERE));
            }
            if (ValidateUtil.isNotEmpty(condition.getTaxCode()))
            {
                andConnection.add(Restrictions.like("taxCode", condition.getTaxCode(), MatchMode.ANYWHERE));
            }
            if (ValidateUtil.isNotEmpty(condition.getBusinessCode()))
            {
                andConnection.add(Restrictions.like("businessCode", condition.getBusinessCode(), MatchMode.ANYWHERE));
            }
            orConnection.add(andConnection);
        }
        selectCondition.add(orConnection);// add select conditionss
        selectCondition.addOrder(Order.asc("partnerId"));// add order condition
        
        List<PartnerInfo> partnerInfoList = getPartnerInfo(selectCondition, pageSize, pageNo);
        
        return getPartnerProfile(partnerInfoList);
    }
    
    /**
     * @Description: 查询partner list
     * @Description: select partner list
     * @author zhaozx
     * @param saleId
     * @param partnerId
     * @param companyName
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<PartnerProfileVO> getPartnerList(Long saleId, Long partnerId, String companyName, Integer pageSize, Integer pageNo)
    {
        LOG.debug("get partner list begin!");
        PageInfo<PartnerProfileVO> pageInfo = new PageInfo<PartnerProfileVO>();
        int totalSize = staffPartnerRelRepository.getPartnerIdListTotalSize(saleId, partnerId);
        if(totalSize == 0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<StaffPartnerRel> staffPartnerRelList = staffPartnerRelRepository.getPartnerIdList(saleId, partnerId, pageSize,
                pageNo);
        if(ValidateUtil.isEmpty(staffPartnerRelList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<Long> partnerIds = new ArrayList<Long>();
        for(StaffPartnerRel staffPartnerRel : staffPartnerRelList)
        {
            partnerIds.add(staffPartnerRel.getPartnerId());
        }
        List<PartnerInfo> partnerInfoList = partnerInfoRepository.getpartnerInfo(partnerIds, companyName);
        List<PartnerProfileVO> partnerProfileVOList = getPartnerProfile(partnerInfoList);
        if(ValidateUtil.isEmpty(partnerInfoList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        LOG.debug("get partner list end!");
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, partnerProfileVOList);
    }
    
    /**
     * @Description: 检查partner是否是有效的
     * @Description: check partner valid
     * @author zhaozx
     * @param partnerId
     * @return
     */
    @Override
    public boolean checkPartnerValid(Long partnerId)
    {
        if (ValidateUtil.isNull(partnerId))
            return false;
        
        Partner partner = getPartner(partnerId);
        if (ValidateUtil.isEmpty(partner))
            return false;
        else if (!partner.isValidPartner())
            return false;
        else
            return true;
    }
    
    @Override
    public List<PartnerInfoVO> getAllPartnerInfo(Long partnerId)
    {
        List<PartnerInfo> partnerInfoList = partnerInfoRepository.getAllPartnerInfo(partnerId);
        List<PartnerInfoVO> partnerInfoVOList = new ArrayList<PartnerInfoVO>();
        if (ValidateUtil.isNotEmpty(partnerInfoList))
        {
            for (PartnerInfo partnerInfo : partnerInfoList)
            {
                PartnerInfoVO partnerInfoVO = new PartnerInfoVO();
                partnerInfoVO.setPartnerId(partnerInfo.getPartnerId());
                partnerInfoVO.setCompanyName(partnerInfo.getCompanyName());
                partnerInfoVOList.add(partnerInfoVO);
            }
        }
        return partnerInfoVOList;
    }
    
    @Override
    public void updateStaff(StaffInfoVO staffVO)
    {
        Timestamp modifyDate = DateTimeUtil.getNow();
        Staff staffInfo = staffRepository.getStaffById(staffVO.getStaffId());
        if (ValidateUtil.isEmpty(staffInfo))
        {
            throw new MeoException(SysErrorCodeDefine.SYSTEM_USER_IS_NOT_FOUND, new Object[]{staffVO.getStaffId() });
        }
        else if (staffInfo.getSts()== Staff.STS_DEACTIVE)
        {
            throw new MeoException(SysErrorCodeDefine.STAFF_STATUS_HAS_EXPIRED, new Object[]{staffVO.getStaffId() });
        }
        staffInfo.setModifyDate(modifyDate);
        if (ValidateUtil.isNotEmpty(staffVO.getDepartment()))
        {
            staffInfo.setDepartment(staffVO.getDepartment());
        }
        if (ValidateUtil.isNotEmpty(staffVO.getEmail()))
        {
            staffInfo.setEmail(staffVO.getEmail());
        }
        if (ValidateUtil.isNotEmpty(staffVO.getStaffNo()))
        {
            staffInfo.setStaffNo(staffVO.getStaffNo());
        }
        if (ValidateUtil.isNotEmpty(staffVO.getStaffName()))
        {
            staffInfo.setStaffName(staffVO.getStaffName());
        }
        staffRepository.updateStaff(staffInfo);
    }
    
    @Override
    public StaffPartnerRel getStaffPartnerRel(Long partnerId)
    {
        return staffPartnerRelRepository.getStaffPartnerRel(partnerId);
    }
    
    @Override
    public PageInfo<PartnerProfileVO> getAllPartnerList(Long partnerId, String companyName, Integer pageSize, Integer pageNo)
    {
        PageInfo<PartnerProfileVO> pageInfo = new PageInfo<PartnerProfileVO>();
        int totalSize =  partnerInfoRepository.getPartnerInfoTotalSize(partnerId, companyName);
        if(totalSize == 0)
        {
           return pageInfo.emptyPageInfo(pageSize); 
        }
        List<PartnerInfo> partnerInfoList =  partnerInfoRepository.getPartnerInfo(partnerId, companyName, pageSize, pageNo);
        if(ValidateUtil.isEmpty(partnerInfoList))
        {
           return pageInfo.emptyPageInfo(pageSize); 
        }
        List<PartnerProfileVO> partnerProfileVOList = getPartnerProfile(partnerInfoList);
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, partnerProfileVOList);
    }
    
}
