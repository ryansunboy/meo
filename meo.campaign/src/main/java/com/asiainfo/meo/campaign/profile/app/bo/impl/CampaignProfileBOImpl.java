package com.asiainfo.meo.campaign.profile.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.campaign.define.CampaignConstantDefine;
import com.asiainfo.meo.campaign.profile.app.bo.CampaignProfileBO;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.campaign.profile.app.model.entity.Agreement;
import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignActivitySummary;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPriceRel;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignProperty;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPropertyDef;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.entity.RewardActivity;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignActivityHistoryVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignCommonVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignDetailVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPriceVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefQueryConditionVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefineVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.HotCampaignRankVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.NewFeedCampaignVO;
import com.asiainfo.meo.campaign.profile.app.repository.AgreementRepository;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignPriceRelRepository;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignPropertyDefRepository;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignPropertyRepository;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignRepository;
import com.asiainfo.meo.campaign.profile.app.repository.HotCampaignRankRepository;
import com.asiainfo.meo.campaign.profile.app.repository.RewardActivityRepository;
import com.asiainfo.meo.campaign.service.require.CampaignRserviceBO;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskRepository;
import com.asiainfo.meo.campaign.task.app.repository.TaskParamInstanceRepository;
import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.vo.CampaignTypeDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;

public class CampaignProfileBOImpl implements CampaignProfileBO
{
    private static final Log              LOG = LogFactory.getLog(CampaignProfileBOImpl.class);
    
    @Resource
    private CampaignRepository            campaignRepository;
    
    @Resource
    private CampaignPropertyDefRepository campaignPropertyDefRepository;
    
    @Resource
    private CampaignPropertyRepository    campaignPropertyRepository;
    
    @Resource
    private HotCampaignRankRepository     hotCampaignRankRepository;
    
    @Resource
    private RewardActivityRepository      rewardActivityRepository;
    
    @Resource
    private AgreementRepository           agreementRepository;
    
    @Resource
    private CampaignPriceRelRepository    campaignPriceRelRepository;
    
    @Resource
    private CampaignRserviceBO            campaignRserviceBO;
    
    @Resource
    private CampaignTaskRepository        campaignTaskRepository;
    
    @Resource
    private TaskParamInstanceRepository   taskParamInstanceRepository;
    
    @Resource
    private CampaignProfileBO             campaignProfileBO;
    
    /**
     * @Description: 閫氳繃campaign id鑾峰緱campaign
     * @Description: (English description)
     * @author zhaozx
     * @return
     */
    public Campaign getCampaignByCampaignId(long campaignId)
    {
        return campaignRepository.getCampaignByCampaignId(campaignId);
    }
    
    /**
     * @Description: 閫氳繃campaignId鑾峰緱campaign灞炴�淇℃伅
     * @Description: get campaign property by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    private List<CampaignProperty> getCampaignPropertyByCampaignId(long campaignId)
    {
        return campaignPropertyRepository.getCampaignPropertyByCampaignId(campaignId);
    }
    
    /**
     * @Description: 閫氳繃campaign id鑾峰緱campaign鐨勫睘鎬�
     * @Description: get campaign detail by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public List<CampaignDetailVO> getCampaignDetail(Long campaignId)
    {
        if (campaignId== null)
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_ID });
        }
        
        List<CampaignDetailVO> campaignDetailList = new ArrayList<CampaignDetailVO>();
        List<CampaignProperty> campaignPropertylist = getCampaignPropertyByCampaignId(campaignId);// get campaign property
        if (ValidateUtil.isEmpty(campaignPropertylist))
        {
            return campaignDetailList;
        }
        
        for (CampaignProperty campaignProperty : campaignPropertylist)
        {
            CampaignPropertyDef campaignPropertyDef = getCampaignPropertyDef(campaignProperty.getPropertyKey());
            if(null == campaignPropertyDef)
            {
                continue;
            }
            CampaignDetailVO campaignDetail = new CampaignDetailVO();
            campaignDetail.setPropertyId(campaignProperty.getPropertyId());
            campaignDetail.setPropertyValue(campaignProperty.getPropertyValue());
            campaignDetail.setPropertyCode(campaignPropertyDef.getPropertyCode());
            // added by zhengzy 2015-9-10 16:00:26
            // added these three properties just for the "meo.campaign.detail.get" show in the app
            campaignDetail.setMandatory(campaignPropertyDef.getMandatory());  
            campaignDetail.setPropertyName(campaignPropertyDef.getPropertyName());
            campaignDetail.setPropertyValueType(campaignPropertyDef.getPropertyValueType());
            campaignDetailList.add(campaignDetail);
        }
        return campaignDetailList;
    }
    
    /**
     * @Description: 鏍规嵁鏉′欢鏌ヨcampaign 闆嗗悎
     * @Description: query campaign list by conditions
     * @modifyReason:
     * @author zhengzy
     * @param criteria
     * @param firstResults
     * @param maxResults
     * @return
     */
    public PageInfo<CampaignVO> getCampaignList(List<Integer> campaignTypes, Integer categoryId, Integer pageSize, Integer pageNo)
    {
        PageInfo<CampaignVO> pageInfo = new PageInfo<CampaignVO>();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        int totalSize = campaignRepository.getCampaignListTotalSize(currentTime, campaignTypes, categoryId, null);
        if (totalSize== 0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<Campaign> list = campaignRepository.getCampaignList(currentTime, campaignTypes, categoryId, null, pageSize, pageNo);
        if (ValidateUtil.isEmpty(list))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<CampaignVO> campaignVoList = new ArrayList<CampaignVO>();
        for (Campaign campaign : list)
        {
            CampaignVO vo = new CampaignVO();
            BeanUtils.copyProperties(campaign, vo);
            Integer totalCoins = getCampaignTotalCoins(campaign.getCampaignId());
            vo.setTotalCoins(totalCoins);
            
            //added by zhengzy 2015-9-10 18:00:48
            Integer remainParticipant = countRemainParticipantByCampaignNo(campaign.getCampaignNo(),campaign.getParticipant());
            vo.setRemainParticipant(remainParticipant);
            campaignVoList.add(vo);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, campaignVoList);
    }
    
    /**
     * @Description: 鏍规嵁绫诲埆闄嶅簭鏌ヨ HotCampaignRank
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param categoryId
     * @param pageSize
     * @param pageNo
     * @return
     */
    public List<HotCampaignRank> getHotCampaignRankDescOrder(Integer categoryId, Integer pageSize, Integer pageNo)
    {
        return hotCampaignRankRepository.getHotCampaignRankDescOrder(categoryId, pageSize, pageNo);
    }
    
    public List<HotCampaignRank> getHotCampaignRankDescOrder(Integer categoryId)
    {
        return hotCampaignRankRepository.getHotCampaignRankDescOrder(categoryId);
    }
    
    /**
     * @Description: 鏌ヨ鍙楁杩庣殑campaign
     * @Description: query hot campaign
     * @modifyReason:
     * @author zhengzy
     * @param hotCampaignRankList
     * @param categoryId
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageInfo<CampaignVO> getHotCampaignList(List<Integer> campaignTypes, List<HotCampaignRank> hotCampaignRankList,
            Integer categoryId, Integer pageSize, Integer pageNo)
    {
        PageInfo<CampaignVO> pageInfo = new PageInfo<CampaignVO>();
        if (ValidateUtil.isEmpty(hotCampaignRankList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Long[] campaignIds = createCampaignIds(hotCampaignRankList);
        int totalSize = campaignRepository.getCampaignListTotalSize(currentTime, campaignTypes, categoryId, campaignIds);
        if (totalSize== 0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<Campaign> list = campaignRepository.getCampaignList(currentTime, campaignTypes, categoryId, campaignIds, pageSize,
                pageNo);
        if (ValidateUtil.isEmpty(list))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<CampaignVO> campaignList = copyPropertiesForCampaign(hotCampaignRankList, list);
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, campaignList);
    }
    
    /**
     * @Description: 鎷疯礉灞炴�
     * @Description: copy property
     * @modifyReason:
     * @author zhengzy
     * @param hotCampaignRankList
     * @param list
     * @return
     */
    private List<CampaignVO> copyPropertiesForCampaign(List<HotCampaignRank> hotCampaignRankList, List<Campaign> list)
    {
        List<CampaignVO> campaignVoList = new ArrayList<CampaignVO>();
        for (Campaign campaign : list)
        {
            CampaignVO vo = new CampaignVO();
            BeanUtils.copyProperties(campaign, vo);
            Integer totalCoins = getCampaignTotalCoins(campaign.getCampaignId());
            vo.setTotalCoins(totalCoins);
            //added by zhengzy 2015-9-10 18:00:48
            Integer remainParticipant = countRemainParticipantByCampaignNo(campaign.getCampaignNo(),campaign.getParticipant());
            vo.setRemainParticipant(remainParticipant);
            campaignVoList.add(vo);
        }
        
        // 鎺掑簭
        List<CampaignVO> copyCampaignVoList = new ArrayList<CampaignVO>();
        for (HotCampaignRank hotCampaignRank : hotCampaignRankList)
        {
            for (CampaignVO campaignVo : campaignVoList)
            {
                if (hotCampaignRank.getCampaignId()!= campaignVo.getCampaignId())
                {
                    continue;
                }
                copyCampaignVoList.add(campaignVo);
            }
        }
        return copyCampaignVoList;
    }
    
    /**
     * @Description: 鎻愬彇鍙楁杩庣殑campaign 鐨刢ampaignId
     * @Description:
     * @modifyReason:
     * @author zhengzy
     * @param hotCampaignRankList
     * @return
     */
    private Long[] createCampaignIds(List<HotCampaignRank> hotCampaignRankList)
    {
        if (ValidateUtil.isEmpty(hotCampaignRankList))
        {
            return new Long[]{};
        }
        
        Long[] campaignIds = new Long[hotCampaignRankList.size()];
        for (int i = 0; i< hotCampaignRankList.size(); i++ )
        {
            HotCampaignRank hotCampaignRank = hotCampaignRankList.get(i);
            campaignIds[i] = hotCampaignRank.getCampaignId();
        }
        return campaignIds;
    }
    
    public PageInfo<CampaignActivityHistoryVO> getCampaignActivityHistoryList(Long custId, Integer pageSize, Integer pageNo)
    {
        PageInfo<CampaignActivityHistoryVO> page = new PageInfo<CampaignActivityHistoryVO>();
        List<RewardActivity> rewardActivityList = rewardActivityRepository.getRewardActivityList(custId, pageSize, pageNo);
        Integer totalSize = rewardActivityRepository.getRewardActivityListTotalSize(custId);
        if (ValidateUtil.isEmpty(rewardActivityList))
        {
            return null;
        }
        List<CampaignActivityHistoryVO> list = campaignRewardActivityList(rewardActivityList);
        return page.createPageInfo(pageNo, pageSize, totalSize, list);
    }
    
    private List<CampaignActivityHistoryVO> campaignRewardActivityList(List<RewardActivity> rewardActivityList)
    {
        List<CampaignActivityHistoryVO> historyList = new ArrayList<CampaignActivityHistoryVO>();
        for (RewardActivity rewardActivity : rewardActivityList)
        {
            CampaignActivityHistoryVO historyVO = new CampaignActivityHistoryVO();
            historyVO.setRewardId(rewardActivity.getRewardId());
            historyVO.setActionId(rewardActivity.getActionId());
            historyVO.setDoneTime(rewardActivity.getDoneTime().getTime());
            historyVO.setRewardType(rewardActivity.getRewardType());
            historyVO.setRewardValue(rewardActivity.getRewardValue());
            historyVO.setRewardUnitId(rewardActivity.getRewardUnit());
            historyVO.setCampaignNo(rewardActivity.getCampaignNo());
            historyVO.setActivityId(rewardActivity.getActivityId());
            historyList.add(historyVO);
        }
        return historyList;
    }
    
    public Campaign getActiveCampaignByCampaignNo(String campaignNo)
    {
        return campaignRepository.getActiveCampaignByCampaignNo(campaignNo);
    }
    
    public List<Agreement> getAgreementInfo(Long agreementId)
    {
        return agreementRepository.getAgreementInfo(agreementId);
    }
    
    public List<Campaign> getCampaignBasicInfo(CampaignBasicVO capaignCondition, Integer pageSize, Integer pageNo)
    {
        return campaignRepository.getCampaignBasicInfo(capaignCondition, pageSize, pageNo);
    }
    
    public int getCampaignBasicInfoTotalSize(CampaignBasicVO capaignCondition)
    {
        return campaignRepository.getCampaignBasicInfoTotalSize(capaignCondition);
    }
    
    private CampaignPropertyDef getCampaignPropertyDef(long propertyKey)
    {
        return campaignPropertyDefRepository.getCampaignPropertyDef(propertyKey);
    }
    
    public CampaignPriceRel saveCampaignPriceRel(CampaignPriceRel campaignPriceRel)
    {
        return campaignPriceRelRepository.saveCampaignPriceRel(campaignPriceRel);
    }
    
    @Override
    public List<CampaignPriceRel> getCampaignPriceRelsByCampaignId(Long campaignId)
    {
        return campaignPriceRelRepository.getCampaignPriceRelsByCampaignId(campaignId);
    }
    
    @Override
    public void deleteCampaignPriceRel(Long id)
    {
        campaignPriceRelRepository.deleteCampaignPriceRel(id);
    }
    
    @Override
    public CampaignProperty getCampaignPropertyById(Long id)
    {
        CampaignProperty campaignProperty = campaignPropertyRepository.getCampaignPropertyById(id);
        return campaignProperty!= null&& campaignProperty.getSts()== CampaignProperty.STS_VALID ? campaignProperty : null;
    }
    
    @Override
    public CampaignProperty saveCampaignProperty(CampaignDetailVO campaignDetailVO, int campaignType, Long campaignId)
    {
        CampaignPropertyDef campaignPropertyDef = campaignPropertyDefRepository.getCampaignPropertyDefByCode(
                campaignDetailVO.getPropertyCode(), campaignType);
        if (campaignPropertyDef== null)
        {
            // if not found the data should throw exception
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_PROPERTY_IS_NOT_FOUND_BY_PROPERTYCODE_AND_CAMAPIGNTYPE,
                    new Object[]{campaignDetailVO.getPropertyCode(), campaignType });
        }
        CampaignProperty campaignProperty = new CampaignProperty();
        campaignProperty.setCampaignId(campaignId);
        campaignProperty.setPropertyKey(campaignPropertyDef.getPropertyKey());
        campaignProperty.setPropertyValue(campaignDetailVO.getPropertyValue());
        campaignProperty.setCreateDate(DateTimeUtil.getNow());
        campaignProperty.setSts(CampaignProperty.STS_VALID);
        return campaignPropertyRepository.saveCampaignProperty(campaignProperty);
    }
    
    @Override
    public CampaignProperty updateCampaignProperty(CampaignDetailVO campaignDetailVO, int campaignType, Long campaignId)
    {
        CampaignPropertyDef campaignPropertyDef = campaignPropertyDefRepository.getCampaignPropertyDefByCode(
                campaignDetailVO.getPropertyCode(), campaignType);
        if (campaignPropertyDef== null)
        {
            // if not found the data should throw exception
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_PROPERTY_IS_NOT_FOUND_BY_PROPERTYCODE_AND_CAMAPIGNTYPE,
                    new Object[]{campaignDetailVO.getPropertyCode(), campaignType });
        }
        CampaignProperty campaignProperty = campaignPropertyRepository.getCampaignPropertyById(campaignDetailVO.getPropertyId());
        campaignProperty.setPropertyKey(campaignPropertyDef.getPropertyKey());
        campaignProperty.setPropertyValue(campaignDetailVO.getPropertyValue());
        campaignProperty.setCampaignId(campaignId);
        campaignProperty.setModifyDate(DateTimeUtil.getNow());
        campaignProperty.setSts(CampaignProperty.STS_VALID);
        return campaignPropertyRepository.updateCampaignProperty(campaignProperty);
    }
    
    @Override
    public CampaignProperty deleteCampaignProperty(Long id)
    {
        CampaignProperty campaignProperty = campaignPropertyRepository.getCampaignPropertyById(id);
        campaignProperty.setSts(CampaignProperty.STS_INVALID);
        return campaignPropertyRepository.updateCampaignProperty(campaignProperty);
    }
    
    @Override
    public List<CampaignProperty> getCampaignPropertyListByCampaignId(Long campaignId)
    {
        return campaignPropertyRepository.getCampaignPropertyListByCampaignId(campaignId);
    }
    
    @Override
    public Campaign addCampaign(CampaignCommonVO campaignCommonVO)
    {
        Campaign campaign = new Campaign();
        campaign.setCampaignName(campaignCommonVO.getCampaignName());
        campaign.setCampaignType(campaignCommonVO.getCampaignType());
        Long startDate = campaignCommonVO.getStartDate();
        Timestamp campaignBeginDate = DateTimeUtil.getBeginDayTimestamp(startDate);
        campaign.setCampaignBeginDate(campaignBeginDate);
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setIconUrl(campaignCommonVO.getIconUrl());
        campaign.setCategoryId(campaignCommonVO.getCategoryId());
        campaign.setDescription(campaignCommonVO.getDescription());
        campaign.setCampaignUrl(campaignCommonVO.getCampaignUrl());
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_DRAF);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        campaign.setPartnerId(campaignCommonVO.getPartnerId());
        campaign.setOperatorId(BoContext.getBoContext().getUserId());
        return campaignRepository.addCampaign(campaign);
    }
    
    @Override
    public Campaign updateCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO)
    {
        campaign.setCampaignName(campaignCommonVO.getCampaignName());
        campaign.setCampaignType(campaignCommonVO.getCampaignType());
        Long startDate = campaignCommonVO.getStartDate();
        Timestamp campaignStartDate = DateTimeUtil.getBeginDayTimestamp(startDate);
        campaign.setCampaignBeginDate(campaignStartDate);
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setIconUrl(campaignCommonVO.getIconUrl());
        campaign.setCategoryId(campaignCommonVO.getCategoryId());
        campaign.setDescription(campaignCommonVO.getDescription());
        campaign.setCampaignUrl(campaignCommonVO.getCampaignUrl());
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_DRAF);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        campaign.setPartnerId(campaignCommonVO.getPartnerId());
        return campaignRepository.updateCampaign(campaign);
    }
    
    @Override
    public Campaign addPublishCampaign(CampaignCommonVO campaignCommonVO)
    {
        Campaign campaign = new Campaign();
        campaign.setCampaignName(campaignCommonVO.getCampaignName());
        campaign.setCampaignType(campaignCommonVO.getCampaignType());
        Long startDate = campaignCommonVO.getStartDate();
        Timestamp campaignStartDate = DateTimeUtil.getBeginDayTimestamp(startDate);
        campaign.setCampaignBeginDate(campaignStartDate);
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setIconUrl(campaignCommonVO.getIconUrl());
        campaign.setCategoryId(campaignCommonVO.getCategoryId());
        campaign.setDescription(campaignCommonVO.getDescription());
        campaign.setParticipant(campaignCommonVO.getParticipant());
        campaign.setCampaignUrl(campaignCommonVO.getCampaignUrl());
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_ACTIVE);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setPartnerId(campaignCommonVO.getPartnerId());
        return campaignRepository.addCampaign(campaign);
    }
    
    @Override
    public Campaign updateRenewCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO)
    {
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_RENEW);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        campaign.setExpiredDate(campaignEndDate);
        campaign.setOperatorId(BoContext.getBoContext().getUserId());
        return campaignRepository.updateCampaign(campaign);
    }
    
    public void updateActiveCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO)
    {
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_ACTIVE);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        campaign.setExpiredDate(campaignEndDate);
        campaign.setOperatorId(BoContext.getBoContext().getUserId());
        campaignRepository.updateCampaign(campaign);
        campaignRepository.updateCampaignEndDate(campaign.getCampaignNo(), campaignEndDate);
    }
    
    @Override
    public Campaign addRenewCampaign(Campaign oldCampaign, CampaignCommonVO campaignCommonVO)
    {
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(oldCampaign, campaign, new String[]{"campaignId", "sts", "campaignSts", "campaignEndDate",
                "validDate", "expiredDate" });
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_RENEW);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setOperatorId(BoContext.getBoContext().getUserId());
        oldCampaign.setIsEdit(Campaign.STS_UNEDIT);
        campaignRepository.updateCampaign(oldCampaign);
        return campaignRepository.addCampaign(campaign);
    }
    
    @Override
    public Campaign updatePublishCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO)
    {
        campaign.setCampaignName(campaignCommonVO.getCampaignName());
        campaign.setCampaignType(campaignCommonVO.getCampaignType());
        Long startDate = campaignCommonVO.getStartDate();
        Timestamp campaignStartDate = DateTimeUtil.getBeginDayTimestamp(startDate);
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignBeginDate(campaignStartDate);
        campaign.setCampaignEndDate(campaignEndDate);
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setIconUrl(campaignCommonVO.getIconUrl());
        campaign.setCategoryId(campaignCommonVO.getCategoryId());
        campaign.setDescription(campaignCommonVO.getDescription());
        campaign.setParticipant(campaignCommonVO.getParticipant());
        campaign.setCampaignUrl(campaignCommonVO.getCampaignUrl());
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_ACTIVE);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setPartnerId(campaignCommonVO.getPartnerId());
        return campaignRepository.updateCampaign(campaign);
    }
    
    @Override
    public Campaign updatePublishRenewCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO)
    {
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        Long validDate = campaignCommonVO.getValidDate();
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_ACTIVE);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        Campaign oldCampaign = campaignRepository.getActiveCampaignByCampaignNo(campaign.getCampaignNo());
        oldCampaign.setExpiredDate(DateTimeUtil.getYesterdayEndDateTime(validDate));
        campaignRepository.updateCampaign(oldCampaign);
        campaignRepository.updateCampaign(campaign);
        campaignRepository.updateCampaignEndDate(campaign.getCampaignNo(), campaignEndDate);
        return campaignRepository.getCampaignByCampaignId(campaign.getCampaignId());
    }
    
    @Override
    public Campaign addPublishRenewCampaign(Campaign oldCampaign, CampaignCommonVO campaignCommonVO)
    {
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(oldCampaign, campaign, new String[]{"campaignId", "sts", "isEdit", "campaignSts",
                "campaignEndDate", "validDate", "expiredDate" });
        Long validDate = campaignCommonVO.getValidDate();
        Long endDate = campaignCommonVO.getEndDate();
        Timestamp campaignEndDate = DateTimeUtil.getEndDayTimestamp(endDate);
        campaign.setCampaignEndDate(campaignEndDate);
        if (validDate!= null)
        {
            campaign.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        }
        else
        {
            campaign.setValidDate(DateTimeUtil.getNextDayStartTime());
        }
        campaign.setExpiredDate(campaignEndDate);
        campaign.setParticipant(campaignCommonVO.getParticipant());
        campaign.setSts(Campaign.STS_VALID);
        campaign.setCampaignSts(Campaign.STS_ACTIVE);
        campaign.setIsEdit(Campaign.STS_ISEDIT);
        campaign.setOperatorId(BoContext.getBoContext().getUserId());
        oldCampaign.setIsEdit(Campaign.STS_UNEDIT);
        oldCampaign.setExpiredDate(DateTimeUtil.getYesterdayEndDateTime(validDate));
        campaignRepository.updateCampaign(oldCampaign);
        Campaign newCampaign = campaignRepository.addCampaign(campaign);
        campaignRepository.updateCampaignEndDate(campaign.getCampaignNo(), campaignEndDate);
        return campaignRepository.getCampaignByCampaignId(newCampaign.getCampaignId());
    }
    
    @Override
    public Campaign getCampaignByCampaignNoAndCampaignSts(String campaignNo, int campaignSts)
    {
        return campaignRepository.getCampaignByCampaignNoAndCampaignSts(campaignNo, campaignSts);
    }
    
    @Override
    public CampaignPriceRel updateCampaignPriceStsRelInvalid(CampaignPriceRel campaignPriceRel)
    {
        campaignPriceRel.setSts(CampaignPriceRel.STS_INVALID);
        return campaignPriceRelRepository.updateCampaignPriceRel(campaignPriceRel);
    }
    
    @Override
    public CampaignPriceRel getCampaignPriceRelByPriceIdAndCampaignId(Long priceId, Long campaignId)
    {
        return campaignPriceRelRepository.getCampaignPriceRelByPriceIdAndCampaignId(priceId, campaignId);
    }
    
    @Override
    public List<Campaign> getCampaignListByCampaignNo(String campaignNo)
    {
        return campaignRepository.getCampaignListByCampaignNo(campaignNo);
    }
    
    @Override
    public CampaignProperty saveCampaignProperty(CampaignProperty campaignProperty)
    {
        return campaignPropertyRepository.saveCampaignProperty(campaignProperty);
    }
    
    @Override
    public List<String> getMandatoryPropertyCampaignPropertyDefByCampaignType(int campaignType)
    {
        List<CampaignPropertyDef> list = campaignPropertyDefRepository
                .getMandatoryPropertyCampaignPropertyDefByCampaignType(campaignType);
        if (ValidateUtil.isEmpty(list))
        {
            return null;
        }
        List<String> mandatoryProperties = new ArrayList<String>();
        for (CampaignPropertyDef campaignPropertyDef : list)
        {
            mandatoryProperties.add(campaignPropertyDef.getPropertyCode());
        }
        return mandatoryProperties;
    }
    
    @Override
    public CampaignPriceRel updateCampaignPriceRel(CampaignPriceRel campaignPriceRel)
    {
        return campaignPriceRelRepository.updateCampaignPriceRel(campaignPriceRel);
    }
    
    @Override
    public List<Campaign> getActiveVersionCampaignByCampaignNo(String campaignNo)
    {
        return campaignRepository.getActiveVersionCampaignByCampaignNo(campaignNo);
    }
    
    @Override
    public String getNextVersion(String campaignNo)
    {
        return campaignRepository.getNextVersion(campaignNo);
    }
    
    @Override
    public List<CampaignPriceVO> getCampaignPricesByCampaignId(Long campaignId)
    {
        List<CampaignPriceVO> campaignPriceVOList = new ArrayList<CampaignPriceVO>();
        List<CampaignPriceRel> campaignPriceRelList = campaignPriceRelRepository.getCampaignPriceRelsByCampaignId(campaignId);
        for (CampaignPriceRel campaignPriceRel : campaignPriceRelList)
        {
            CampaignPriceVO campaignPriceVO = new CampaignPriceVO();
            Price price = campaignRserviceBO.getPrice(campaignPriceRel.getPriceId());
            if (price== null)
            {
                continue;
            }
            BeanUtils.copyProperties(price, campaignPriceVO);
            campaignPriceVOList.add(campaignPriceVO);
        }
        return campaignPriceVOList;
    }
    
    @Override
    public CampaignActivitySummary getCampaignSummaryByCampaignNoAndParticipantType(String campaignNo)
    {
        return campaignRepository.getCampaignSummaryByCampaignNoAndParticipantType(campaignNo);
    }
    
    @Override
    public Campaign getPreviousVersionCampaign(String campaignNo, String version)
    {
        return campaignRepository.getPreviousVersionCampaign(campaignNo, version);
    }
    
    @Override
    public HotCampaignRankVO get(Long campaignId)
    {
        HotCampaignRank hotCampaignRank = hotCampaignRankRepository.get(campaignId);
        HotCampaignRankVO hotCampaignRankVO = new HotCampaignRankVO();
        if (hotCampaignRank== null)
        {
            return hotCampaignRankVO;
        }
        BeanUtils.copyProperties(hotCampaignRank, hotCampaignRankVO);
        return hotCampaignRankVO;
    }
    
    @Override
    public void saveHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        hotCampaignRankRepository.saveHotCampaignRank(hotCampaignRank);
    }
    
    @Override
    public void updateHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        hotCampaignRankRepository.updateHotCampaignRank(hotCampaignRank);
    }
    
    @Override
    public PageInfo<CampaignPropertyDefVO> getCampaignPropertyDefByCampaignType(CampaignPropertyDefQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        
        if (conditionVO!= null)
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
        }
        final PageInfo<CampaignPropertyDefVO> pageInfo = new PageInfo<CampaignPropertyDefVO>();
        Integer totalSize = campaignPropertyDefRepository.getCampaignPropertyDefByCampaignTypeTotalSize(conditionVO);
        if (totalSize== null)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        
        List<CampaignPropertyDef> campaignPropertyDefList = campaignPropertyDefRepository.getCampaignPropertyDefByCampaignType(
                conditionVO, pageSize, pageNo);
        List<CampaignPropertyDefVO> campaignPropertyDefVOList = new ArrayList<CampaignPropertyDefVO>();
        for (CampaignPropertyDef campaignPropertyDef : campaignPropertyDefList)
        {
            CampaignPropertyDefVO campaignPropertyDefVO = new CampaignPropertyDefVO();
            BeanUtils.copyProperties(campaignPropertyDef, campaignPropertyDefVO);
            EnumDefine enumDefine = campaignRserviceBO.getEnumDefByEnumCode(
                    String.valueOf(campaignPropertyDef.getCampaignType()), Integer.valueOf(EnumDefine.CAMPAGIN_TYPE));
            campaignPropertyDefVO.setCampaignType(enumDefine!= null ? enumDefine.getEnumName() : null);
            campaignPropertyDefVOList.add(campaignPropertyDefVO);
        }
        
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, campaignPropertyDefVOList);
    }
    
    @Override
    public void addCampaignPropertyDef(List<CampaignPropertyDefineVO> campaignPropertyDefineVOList)
    {
        for (CampaignPropertyDefineVO campaignPropertyDefineVO : campaignPropertyDefineVOList)
        {
            List<CampaignTypeDefine> campaignTypeDefineList = campaignRserviceBO.getCampaginType(campaignPropertyDefineVO
                    .getCampaignTypeId().toString());
            if (ValidateUtil.isEmpty(campaignTypeDefineList))
            {
                LOG.debug("the valid CampaignTypeDefine is not found ");
                throw new MeoException(CampaignErrorConstant.CAN_NOT_FOUND_VALID_CAMPAIGN_TYPE,
                        new Object[]{campaignPropertyDefineVO.getCampaignTypeId() });
            }
            
            if (campaignTypeDefineList.size()> 1)
            {
                LOG.debug("the CampaignTypeDefine size more than one");
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_TYPE_MORE_THAN_ONE,
                        new Object[]{campaignPropertyDefineVO.getCampaignTypeId() });
            }
            CampaignPropertyDef tempCampaignPropertyDef = campaignPropertyDefRepository.getCampaignPropertyDefByCode(
                    campaignPropertyDefineVO.getPropertyCode(), campaignPropertyDefineVO.getCampaignTypeId());
            
            if (ValidateUtil.isNotNull(tempCampaignPropertyDef))
            {
                LOG.debug("the propertyCode "+ campaignPropertyDefineVO.getPropertyCode()+ "exist ,the campaignTypeId is "
                        + campaignPropertyDefineVO.getCampaignTypeId());
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_PROPERTYCODE_EXIST,
                        new Object[]{campaignPropertyDefineVO.getPropertyCode() });
            }
            CampaignPropertyDef campaignPropertyDef = new CampaignPropertyDef();
            
            Integer mandatory = campaignPropertyDefineVO.getMandatory();
            if (mandatory!= null)
            {
                if (mandatory!= CampaignPropertyDef.IS_MANDATORY&& mandatory!= CampaignPropertyDef.UN_MANDATORY)
                {
                    LOG.debug("add the campaignPropertyDef the property mandatory wasn't correct");
                    throw new MeoException(CampaignErrorConstant.CAMPAIGNPROPERTYDEF_MANDATORY_INCORRECT);
                }
            }
            campaignPropertyDef.setMandatory(mandatory== null ? CampaignPropertyDef.UN_MANDATORY : mandatory);
            campaignPropertyDef.setPropertyCode(campaignPropertyDefineVO.getPropertyCode());
            campaignPropertyDef.setPropertyName(campaignPropertyDefineVO.getPropertyName());
            campaignPropertyDef.setSts(CampaignPropertyDef.STS_VALID);
            campaignPropertyDef.setCampaignType(Integer.valueOf(campaignTypeDefineList.get(0).getCampaignTypeId()));
            campaignPropertyDef.setPropertyValueType(campaignPropertyDefineVO.getPropertyValueType());
            campaignPropertyDefRepository.addCampaignPropertyDef(campaignPropertyDef);
        }
    }
    
    @Override
    public List<CampaignVO> getCampaignListByCampaignTypesAndCategoryIds(List<Integer> campaignTypes, List<Integer> categoryIds)
    {
        List<Campaign> list = campaignRepository.getCampaignListByCampaignTypesAndCategoryIds(campaignTypes, categoryIds);
        if (ValidateUtil.isEmpty(list))
        {
            return null;
        }
        List<CampaignVO> campaignVoList = new ArrayList<CampaignVO>();
        for (Campaign campaign : list)
        {
            CampaignVO vo = new CampaignVO();
            BeanUtils.copyProperties(campaign, vo);
            Integer totalCoins = getCampaignTotalCoins(campaign.getCampaignId());
            vo.setTotalCoins(totalCoins);
            campaignVoList.add(vo);
        }
        return campaignVoList;
    }
    
    @Override
    public void modifyCampaignPropertyDef(CampaignPropertyDefVO campaignPropertyDefVO)
    {
        if (campaignPropertyDefVO== null)
        {
            throw new MeoException(CampaignErrorConstant.ENTITY_IS_EMPTY, new Object[]{"CampaignPropertyDefVO" });
        }
        Long propertyKey = campaignPropertyDefVO.getPropertyKey();
        CampaignPropertyDef campaignPropertyDef = campaignPropertyDefRepository.getCampaignPropertyDef(propertyKey);
        if (campaignPropertyDef== null)
        {
            throw new MeoException(CampaignErrorConstant.NOT_EXIST, new Object[]{"PropertyKey" });
        }
        if (ValidateUtil.isNotEmpty(campaignPropertyDefVO.getPropertyCode()))
        {
            campaignPropertyDef.setPropertyCode(campaignPropertyDefVO.getPropertyCode());
        }
        Integer propertyValueType = campaignPropertyDefVO.getPropertyValueType();
        if (propertyValueType!= null)
        {
            campaignPropertyDef.setPropertyValueType(propertyValueType);
        }
        Integer mandatory = campaignPropertyDefVO.getMandatory();
        if (mandatory!= null)
        {
            if (mandatory!= CampaignPropertyDef.IS_MANDATORY&& mandatory!= CampaignPropertyDef.UN_MANDATORY)
            {
                LOG.debug("modify the campaignPropertyDef the property mandatory wasn't correct,the propertyKey was "
                        + propertyKey);
                throw new MeoException(CampaignErrorConstant.CAMPAIGNPROPERTYDEF_MANDATORY_INCORRECT);
            }
            campaignPropertyDef.setMandatory(mandatory);
        }
        campaignPropertyDef.setPropertyName(campaignPropertyDefVO.getPropertyName());
        campaignPropertyDefRepository.modifyCampaignPropertyDef(campaignPropertyDef);
    }
    
    @Override
    public Integer getCampaignTotalCoins(Long campaignId)
    {
        List<CampaignTask> campaingTaskList = campaignTaskRepository.getCampaignTaskList(campaignId);
        Long totalCoins = 0l;
        if (ValidateUtil.isNotEmpty(campaingTaskList))
        {
            List<Long> taskIds = new ArrayList<Long>();
            for (CampaignTask ct : campaingTaskList)
            {
                taskIds.add(ct.getTaskId());
            }
            List<TaskParamInstance> list = taskParamInstanceRepository.getTaskParamInstanceListByTaskIds(taskIds);
            for (TaskParamInstance tpi : list)
            {
                totalCoins += tpi.getParamValue();
            }
        }
        if (totalCoins.longValue()== 0)
        {
            return null;
        }
        return totalCoins.intValue();
    }
    
    @Override
    public List<Campaign> getCampaignList(Long currentTime)
    {
        return campaignRepository.getCampaignList(currentTime);
    }
    
    @Override
    public Campaign updateCampaign(Campaign campaign)
    {
        return campaignRepository.updateCampaign(campaign);
    }
    
    @Override
    public PageInfo<String> getAppDownLoad(Long custId, Integer pageNo, Integer pageSize)
    {
        pageSize = ValidateUtil.checkPageSize(pageSize);
        pageNo = ValidateUtil.checkPageNo(pageNo);
        PageInfo<String> page = new PageInfo<String>();
        Integer totalSize = campaignRepository.getAppDownLoadTotalSize(custId);
        if (totalSize== null|| totalSize== 0)
        {
            return page.emptyPageInfo(pageSize);
        }
        List<Object> list = campaignRepository.getAppDownLoad(custId, pageNo, pageSize);
        if (ValidateUtil.isEmpty(list))
        {
            return page.emptyPageInfo(pageSize);
        }
        List<String> applist = new ArrayList<String>();
        for (Object obj : list)
        {
            applist.add(obj.toString());
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, applist);
    }
    
    @Override
    public PageInfo<NewFeedCampaignVO> getNewsFeedCampaignPageInfo(Integer pageNo, Integer pageSize)
    {
        pageSize = ValidateUtil.checkPageSize(pageSize);
        pageNo = ValidateUtil.checkPageNo(pageNo);
        PageInfo<NewFeedCampaignVO> pageInfo = new PageInfo<NewFeedCampaignVO>();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        int totalSize = campaignRepository.getCampaignListTotalSize(currentTime, null, null, null);
        if (totalSize== 0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<Campaign> list = campaignRepository.getCampaignList(currentTime, null, null, null, pageSize, pageNo);
        if (ValidateUtil.isEmpty(list))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<NewFeedCampaignVO> newFeedCampaignList = new ArrayList<NewFeedCampaignVO>();
        for (Campaign campaign : list)
        {
            NewFeedCampaignVO newFeedCampaign = new NewFeedCampaignVO();
            
            CampaignBasicVO campaignBasic = copyProperties(campaign);
            newFeedCampaign.setCampaignBasic(campaignBasic);
            List<CampaignDetailVO> campaignDetails = campaignProfileBO.getCampaignDetail(campaign.getCampaignId());
            if (ValidateUtil.isNotEmpty(campaignDetails))
            {
                newFeedCampaign.setCampaignDetails(campaignDetails);
            }
            newFeedCampaignList.add(newFeedCampaign);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, newFeedCampaignList);
    }
    
    private CampaignBasicVO copyProperties(Campaign campaign)
    {
        CampaignBasicVO campaignBasic = new CampaignBasicVO();
        Long partnerId = campaign.getPartnerId();
        Long campaignId = campaign.getCampaignId();
        campaignBasic.setCampaignName(campaign.getCampaignName());
        campaignBasic.setCampaignNo(campaign.getCampaignNo());
        campaignBasic.setValidDate(campaign.getValidDate());
        campaignBasic.setExpiredDate(campaign.getExpiredDate());
        campaignBasic.setDescription(campaign.getDescription());
        campaignBasic.setCampaignId(campaignId);
        
        // added by zhengzy 2015-9-10 14:38:46
        // added for meo.campaign.all.list
        campaignBasic.setParticipant(campaign.getParticipant());
        campaignBasic.setPartnerId(partnerId);
        campaignBasic.setIconUrl(campaign.getIconUrl());
        Integer totalCoins = getCampaignTotalCoins(campaignId);
        campaignBasic.setTotalCoins(totalCoins);
        EnumDefine enumDefine = campaignRserviceBO.getEnumDefByEnumCode(campaign.getCampaignType()+ "",
                Integer.valueOf(EnumDefine.CAMPAGIN_TYPE));
        if (null!= enumDefine)
        {
            List<SysEnumGroupDef> enumGroupDefList = campaignRserviceBO.getSysEnumGroupRelListByEnumId(enumDefine.getEnumId());
            if(ValidateUtil.isNotEmpty(enumGroupDefList))
            {
                String groupCode = enumGroupDefList.get(0).getGroupCode();
                campaignBasic.setGroupCode(groupCode);
            }
        }
        Integer remainParticipant = countRemainParticipantByCampaignNo(campaign.getCampaignNo(),campaign.getParticipant());
        campaignBasic.setRemainParticipant(remainParticipant);
        
        
        if (partnerId!= null)
        {
            PartnerInfo partner = campaignRserviceBO.getPartnerInfo(partnerId);
            // company is the partner ,so the companyName is the partnerName
            if (partner!= null&& ValidateUtil.isNotEmpty(partner.getCompanyName()))
            {
                campaignBasic.setPartnerName(partner.getCompanyName());
            }
            PartnerFileInfo partnerFileInfo = campaignRserviceBO.getPartnerFileInfo(partnerId,
                    PartnerFileInfo.FILE_TYPE_AVATAR);
            if (partnerFileInfo!= null&& ValidateUtil.isNotEmpty(partnerFileInfo.getFileUrl()))
            {
                campaignBasic.setPartnerAvatar(partnerFileInfo.getFileUrl());
            }
        }
        return campaignBasic;
    }
    
    @Override
    public int countRemainParticipantByCampaignNo(String campaignNo,int participant)
    {
        CmEntityParticipant entityParticipant = campaignRserviceBO.getEntityParticipantByCamapginNo(campaignNo);
        int currentParticipant = null == entityParticipant?0:entityParticipant.getParticipant();
        if(participant>currentParticipant)
        {
            return participant - currentParticipant;
        }
        else
        {
            return 0;
        }
    }
}
