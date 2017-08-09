package com.asiainfo.meo.campaign.service.provide.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.campaign.define.CampaignConstantDefine;
import com.asiainfo.meo.campaign.profile.app.bo.CampaignProfileBO;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPriceRel;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignProperty;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignActivityHistoryVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignAllBasicVO;
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
import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.campaign.service.require.CampaignRserviceBO;
import com.asiainfo.meo.campaign.task.app.bo.CampaignTaskBO;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRuleRel;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
import com.asiainfo.meo.campaign.task.app.model.vo.TaskParamVO;
import com.asiainfo.meo.campaign.task.app.model.vo.TriggerTaskVO;
import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureDef;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefineVO;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;

public class CampaignPserviceBOImpl implements CampaignPserviceBO
{
    private static final Log   LOG = LogFactory.getLog(CampaignPserviceBOImpl.class);
    
    @Resource
    private CampaignProfileBO  campaignProfileBO;
    
    @Resource
    private CampaignTaskBO     campaignTaskBO;
    
    @Resource
    private CampaignRserviceBO campaignRserviceBO;
    
    /**
     * @Description: 查询最新发布的campaign 集合
     * @Description: query the new campaign profile
     * @modifyReason:
     * @author zhengzy
     * @param groupCode
     * @param categoryId
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<CampaignVO> getNewCampaignList(String groupCode, Integer categoryId, Integer pageSize, Integer pageNo)
    {
        List<Integer> campaignTypes = getSysEnumCodeByGroupcode(groupCode);
        PageInfo<CampaignVO> pageInfo = campaignProfileBO.getCampaignList(campaignTypes, categoryId, pageSize, pageNo);
        setCategoryNameForCampaignVo(categoryId, pageInfo);
        return pageInfo;
    }
    
    @Override
    public PageInfo<CampaignVO> getHotCampaignList(String groupCode, Integer categoryId, Integer pageSize, Integer pageNo)
    {
        List<Integer> campaignTypes = getSysEnumCodeByGroupcode(groupCode);
        List<HotCampaignRank> hotCampaignRankList = campaignProfileBO.getHotCampaignRankDescOrder(categoryId);
        PageInfo<CampaignVO> pageInfo = campaignProfileBO.getHotCampaignList(campaignTypes, hotCampaignRankList, categoryId,
                pageSize, pageNo);
        setCategoryNameForCampaignVo(categoryId, pageInfo);
        return pageInfo;
    }
    
    /**
     * @description 根据groupCode查询sysEnumDef集合
     * @description query sysEnumDef list by groupCode
     * @param groupCode
     * @author zhengzy
     * @return
     */
    private List<Integer> getSysEnumCodeByGroupcode(String groupCode)
    {
        if (ValidateUtil.isEmpty(groupCode))
        {
            LOG.debug("query sysEnumGroupDef list by groupCode，groupCode is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.GROUPCODE });
        }
        List<SysEnumGroupDef> enumGropuDefs = campaignRserviceBO.getCampaignEnumGroup(groupCode);
        if (ValidateUtil.isEmpty(enumGropuDefs))
        {
            LOG.debug("not found sysEnumGroupDef list by groupCode ,the groupCode is "+ groupCode);
            throw new MeoException(SysErrorCodeDefine.SYSENUMGROUPDEF_NOT_FOUND, new Object[]{groupCode });
        }
        
        List<Long> groupIds = new ArrayList<Long>();
        for (SysEnumGroupDef enumGroup : enumGropuDefs)
        {
            groupIds.add(enumGroup.getGroupId());
        }
        List<SysEnumGroupRel> enumGroupRelList = campaignRserviceBO.getSysEnumGroupRelListByGroupIds(groupIds);
        
        if (ValidateUtil.isEmpty(enumGroupRelList))
        {
            LOG.info("not found the sysEnumGroupRel data,the groupIds were ["+ groupIds+ "]");
            throw new MeoException(CampaignErrorConstant.NOT_FOUND_SYSENUMGROUPREL, new Object[]{groupIds });
        }
        List<Long> enumIds = new ArrayList<Long>();
        for (SysEnumGroupRel enumGroupRel : enumGroupRelList)
        {
            enumIds.add(enumGroupRel.getEnumId());
        }
        List<SysEnumDef> enumDefList = campaignRserviceBO.getSysEnumDefListByIds(enumIds);
        
        if (ValidateUtil.isEmpty(enumDefList))
        {
            LOG.debug("not found sysEnumDef list by enumIds ,the enumIds is "+ enumIds);
            throw new MeoException(SysErrorCodeDefine.SYSENUMDEF_NOT_FOUND, new Object[]{enumIds });
        }
        List<Integer> campaignTypes = new ArrayList<Integer>();
        for (SysEnumDef sysEnumDef : enumDefList)
        {
            Integer type = Integer.valueOf(sysEnumDef.getEnumCode());
            campaignTypes.add(type);
        }
        return campaignTypes;
    }
    
    /**
     * @Description: 通过campaign id获得campaign的属性
     * @Description: get campaign detail by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public List<CampaignDetailVO> getCampaignDetail(Long campaignId)
    {
        if (null==campaignId)
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_ID });
        }
        return campaignProfileBO.getCampaignDetail(campaignId);
    }
    
    /**
     * @Description: 通过campaign id获得campaign 活动
     * @Description: get campaign activity by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public List<CampaignTaskVO> getCampaignActivity(Long campaignId)
    {
        if (campaignId==null)
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_ID });
        }
        return campaignTaskBO.getCampaignTaskByCampaignId(campaignId);
    }
    
    /**
     * @Description: 获取Campaign基本信息
     * @Description: get Campaign Basic Info
     * @modifyReason:
     * @author zhoujj
     * @param capaignCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageInfo<CampaignBasicVO> getCampaignBasicInfo(CampaignBasicVO capaignCondition, Integer pageSize, Integer pageNo)
    {
        PageInfo<CampaignBasicVO> pageInfo = new PageInfo<CampaignBasicVO>();
        List<PartnerInfoVO> partnerInfoVOList = campaignRserviceBO.getAllPartnerInfo(capaignCondition.getPartnerId());
        if (ValidateUtil.isEmpty(partnerInfoVOList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        int totalSize = campaignProfileBO.getCampaignBasicInfoTotalSize(capaignCondition);
        if (totalSize== 0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<Campaign> campaignList = campaignProfileBO.getCampaignBasicInfo(capaignCondition, pageSize, pageNo);
        if (ValidateUtil.isEmpty(campaignList))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<CampaignBasicVO> list = new ArrayList<CampaignBasicVO>();
        for (PartnerInfoVO partnerInfoVO : partnerInfoVOList)
        {
            for (Campaign campaign : campaignList)
            {
                if (campaign.getPartnerId()== null|| partnerInfoVO.getPartnerId()== null)
                {
                    continue;
                }
                if (campaign.getPartnerId().longValue()== partnerInfoVO.getPartnerId().longValue())
                {
                    CampaignBasicVO campaignBasicVO = copyProperties(campaign);
                    campaignBasicVO.setPartnerName(partnerInfoVO.getCompanyName());
                    list.add(campaignBasicVO);
                }
            }
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, list);
    }
    
    private CampaignBasicVO copyProperties(Campaign campaign)
    {
        CampaignBasicVO campaignBasic = new CampaignBasicVO();
        campaignBasic.setCampaignName(campaign.getCampaignName());
        campaignBasic.setCampaignNo(campaign.getCampaignNo());
        campaignBasic.setValidDate(campaign.getValidDate());
        campaignBasic.setExpiredDate(campaign.getExpiredDate());
        campaignBasic.setSts(campaign.getCampaignSts());
        EnumDefine campaginType = campaignRserviceBO.getEnumDefByEnumCode(String.valueOf(campaign.getCampaignSts()),
                Integer.valueOf(EnumDefine.CAMPAGIN_STATUS_TYPE));
        campaignBasic.setCampaignSts(campaginType!= null ? campaginType.getEnumName() : null);
        campaignBasic.setCampaignId(campaign.getCampaignId());
        return campaignBasic;
    }
    
    public Campaign getCampaignById(Long campaignId)
    {
        return campaignProfileBO.getCampaignByCampaignId(campaignId);
    }
    
    /**
     * @Description: 根据CampaignId获取Campaign信息
     * @Description: get Campaign Info By CampaignId
     * @modifyReason:
     * @author zhoujj
     * @param campaignId
     * @return
     */
    public CampaignBasicVO getCampaignInfoById(Long campaignId)
    {
        Campaign campaign = getCampaignById(campaignId);
        CampaignBasicVO campaignBasicVO = new CampaignBasicVO();
        if (ValidateUtil.isEmpty(campaign))
        {
            return campaignBasicVO;
        }
        List<CategoryDefine> categoryDefineList = campaignRserviceBO.getCategoryEnum(campaign.getCategoryId().longValue(),
                CategoryDefine.CAMPAGIN_TYPE);
        for (CategoryDefine categoryDefine : categoryDefineList)
        {
            campaignBasicVO.setCategoryDisName(categoryDefine.getCategoryName());
        }
        BeanUtils.copyProperties(campaign, campaignBasicVO);
        return campaignBasicVO;
    }
    
    private CategoryDefine getCategoryEnum(Integer categoryId)
    {
        List<CategoryDefine> categoryDefineList = campaignRserviceBO.getCategoryEnum(Long.valueOf(categoryId),
                CategoryDefine.CAMPAGIN_TYPE);
        return ValidateUtil.isNotEmpty(categoryDefineList) ? categoryDefineList.get(0) : null;
    }
    
    private void setCategoryNameForCampaignVo(Integer categoryId, PageInfo<CampaignVO> pageInfo)
    {
        CategoryDefine categoryDefine = null;
        if (ValidateUtil.isNotNull(categoryId))
        {
            categoryDefine = getCategoryEnum(categoryId);
        }
        if (ValidateUtil.isNull(pageInfo)|| ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return;
        }
        for (CampaignVO campaignVo : pageInfo.getResult())
        {
            if (categoryDefine== null)
            {
                categoryDefine = getCategoryEnum(campaignVo.getCategoryId());
            }
            campaignVo.setCategoryName(categoryDefine.getCategoryName());
        }
    }
    
    public PageInfo<CampaignActivityHistoryVO> getCampaignActivityHistoryList(Long custId, Integer pageSize, Integer pageNo)
    {
        PageInfo<CampaignActivityHistoryVO> campaignActivityList = campaignProfileBO.getCampaignActivityHistoryList(custId,
                pageSize, pageNo);
        if (ValidateUtil.isEmpty(campaignActivityList))
        {
            return campaignActivityList;
        }
        if (campaignActivityList.getResult()!= null)
        {
            for (int i = 0; i< campaignActivityList.getResult().size(); i++ )
            {
                CampaignActivityHistoryVO campaignActivityHistoryVO = campaignActivityList.getResult().get(i);
                setRewardUnitForCampaignActivitiyHistoryVO(campaignActivityHistoryVO);
                setActionName(campaignActivityHistoryVO);
                setCampaignNameAndId(campaignActivityHistoryVO);
            }
        }
        return campaignActivityList;
    }
    
    private void setActionName(CampaignActivityHistoryVO campaignActivityHistoryVO)
    {
        List<ActionDefine> actionDefineList = campaignRserviceBO.getTriggerAction(campaignActivityHistoryVO.getActionId());
        ActionDefine actionDefine = ValidateUtil.isNotEmpty(actionDefineList) ? actionDefineList.get(0) : null;
        if (actionDefine== null)
        {
            return;
        }
        campaignActivityHistoryVO.setActionName(actionDefine.getActionName());
    }
    
    private void setCampaignNameAndId(CampaignActivityHistoryVO campaignActivityHistoryVO)
    {
        Campaign campaign = campaignProfileBO.getActiveCampaignByCampaignNo(campaignActivityHistoryVO.getCampaignNo());
        if (campaign== null)
        {
            return;
        }
        campaignActivityHistoryVO.setCampaignId(campaign.getCampaignId());
        campaignActivityHistoryVO.setCampaignName(campaign.getCampaignName());
    }
    
    private void setRewardUnitForCampaignActivitiyHistoryVO(CampaignActivityHistoryVO campaignActivityHistoryVO)
    {
        Long rewardType = Long.valueOf(campaignActivityHistoryVO.getRewardType());
        Long rewardId = Long.valueOf(campaignActivityHistoryVO.getRewardId());
        List<MeasureDefine> measureDefineList = null;
        if (rewardType== SysMeasureDef.CURRENCY_UNIT_TYPE)
        {
            measureDefineList = campaignRserviceBO.getCurrencyUnit(rewardId);
        }
        else if (rewardType== SysMeasureDef.TIME_UNIT_TYPE)
        {
            measureDefineList = campaignRserviceBO.getTimeUnit(rewardId);
        }
        MeasureDefine measureDefine = ValidateUtil.isEmpty(measureDefineList) ? null : measureDefineList.get(0);
        if (measureDefine== null)
        {
            return;
        }
        campaignActivityHistoryVO.setRewardUnit(measureDefine.getName());
    }
    
    // 判断参数是否可以为空
    private void throwCampaignParamCommonException(CampaignCommonVO campaignCommonVO)
    {
        if (ValidateUtil.isNull(campaignCommonVO))
        {
            LOG.debug("campaign common properties is null or empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_COMMON });
        }
    }
    
    private void throwCampaignPriceException(boolean action, List<CampaignPriceVO> campaignPrices, List<Long> uiPriceIds)
    {
        Set<Integer> uiPriceTypeSet = new HashSet<Integer>();
        if (ValidateUtil.isNotEmpty(campaignPrices))
        {
            for (CampaignPriceVO campaignPriceVO : campaignPrices)
            {
                if (uiPriceTypeSet.contains(campaignPriceVO.getPriceType()))
                {
                    LOG.debug("price type can not be repeated");
                    throw new MeoException(CampaignErrorConstant.PRICE_TYPE_CAN_NOT_BE_REPEATED);
                }
                else
                {
                    uiPriceTypeSet.add(campaignPriceVO.getPriceType());
                }
                if (ValidateUtil.isNotNull(campaignPriceVO.getPriceId()))
                {
                    uiPriceIds.add(campaignPriceVO.getPriceId());
                }
            }
        }
        
        if (action&& ValidateUtil.isNotEmpty(uiPriceIds))
        {
            LOG.debug("save campaign,should be no priceId");
            // 这里需要抛出异常，告知前端在save draft Campaign做save 操作的时候不应该传递priceId过来。
            throw new MeoException(CampaignErrorConstant.SAVE_CAMAPIGN_SHOULD_BE_NO_PRICEID);
        }
    }
    
    // 做save draft Campaign接口save操作的時候，判斷前端是否传递propertyId，如果传了则抛出异常
    private void throwCampaignDetailException(int campaignType, boolean action, List<CampaignDetailVO> campaignDetailVOs,
            List<Long> uiCampaignPropertyIds)
    {
        List<String> propertyCodes = new ArrayList<String>();
        for (CampaignDetailVO campaignDetailVO : campaignDetailVOs)
        {
            if (ValidateUtil.isNotNull(campaignDetailVO.getPropertyId()))
            {
                uiCampaignPropertyIds.add(campaignDetailVO.getPropertyId());
            }
            propertyCodes.add(campaignDetailVO.getPropertyCode());
        }
        List<String> mandatoryProperties = campaignProfileBO.getMandatoryPropertyCampaignPropertyDefByCampaignType(campaignType);
        // 判断该campaign类型的数据库定义必填项，是否已经填写
        if (ValidateUtil.isNotEmpty(mandatoryProperties))
        {
            for (String property : mandatoryProperties)
            {
                if (!propertyCodes.contains(property))
                {
                    LOG.debug("mandatory campaign properties incomplete,"+ property+ " is null");
                    throw new MeoException(CampaignErrorConstant.MANDATORY_CAMPAIGN_PROPERTY_INCOMPLETE, new Object[]{property });
                }
            }
        }
        if (action&& ValidateUtil.isNotEmpty(uiCampaignPropertyIds))
        {
            LOG.debug("save camapign should be no propertyId");
            throw new MeoException(CampaignErrorConstant.SAVE_CAMAPIGN_SHOULD_BE_NO_PROPERTYID);
        }
    }
    
    private void throwCampaignTaskException(boolean action, List<CampaignTaskVO> campaignTaskVOs, List<Long> uiTaskIds,
            List<Long> uiTaskParamInstanceIds, List<Long> uiActionIds)
    {
        for (int i = 0; i< campaignTaskVOs.size(); i++ )
        {
            CampaignTaskVO campaignTaskVO = campaignTaskVOs.get(i);
            if (ValidateUtil.isNull(campaignTaskVO))
            {
                continue;
            }
            if (ValidateUtil.isNotNull(campaignTaskVO.getTaskId()))
            {
                uiTaskIds.add(campaignTaskVO.getTaskId());
            }
            if (ValidateUtil.isNull(campaignTaskVO.getActionId()))
            {
                throw new MeoException(CampaignErrorConstant.ACTIONID_CAN_NOT_NULL);
            }
            uiActionIds.add(campaignTaskVO.getActionId());
            List<TaskParamVO> taskParamList = campaignTaskVO.getTaskParamList();
            if (ValidateUtil.isNotEmpty(taskParamList))
            {
                for (TaskParamVO taskParamVO : taskParamList)
                {
                    if (ValidateUtil.isNotNull(taskParamVO)&& ValidateUtil.isNotNull(taskParamVO.getParamInstanceId()))
                    {
                        uiTaskParamInstanceIds.add(taskParamVO.getParamInstanceId());
                    }
                }
            }
            List<TriggerTaskVO> triggerTaskList = campaignTaskVO.getTriggerTaskList();
            if (ValidateUtil.isNotEmpty(triggerTaskList))
            {
                for (TriggerTaskVO triggerTaskVO : triggerTaskList)
                {
                    if (ValidateUtil.isNull(triggerTaskVO))
                    {
                        continue;
                    }
                    if (ValidateUtil.isNotNull(triggerTaskVO.getTaskId()))
                    {
                        uiTaskIds.add(triggerTaskVO.getTaskId());
                    }
                    if (ValidateUtil.isNull(triggerTaskVO.getActionId()))
                    {
                        throw new MeoException(CampaignErrorConstant.ACTIONID_CAN_NOT_NULL);
                    }
                    uiActionIds.add(triggerTaskVO.getActionId());
                    List<TaskParamVO> triggerTaskParamList = triggerTaskVO.getTaskParamList();
                    if (ValidateUtil.isNotEmpty(triggerTaskParamList))
                    {
                        for (TaskParamVO triggerTaskParam : triggerTaskParamList)
                        {
                            if (ValidateUtil.isNotNull(triggerTaskParam)
                                    && ValidateUtil.isNotNull(triggerTaskParam.getParamInstanceId()))
                            {
                                uiTaskParamInstanceIds.add(triggerTaskParam.getParamInstanceId());
                            }
                        }
                    }
                    else
                    {
                        throw new MeoException(CampaignErrorConstant.CAMPAIGN_TRIGGER_TASK_PARAM_LIST_CAN_NOT_NULL);
                    }
                }
            }
        }
        if (action)
        {
            if (ValidateUtil.isNotEmpty(uiTaskIds))
            {
                LOG.debug("save camapign should be no taskid");
                throw new MeoException(CampaignErrorConstant.SAVE_CAMAPIGN_SHOULD_BE_NO_TASKID);
            }
            if (ValidateUtil.isNotEmpty(uiTaskParamInstanceIds))
            {
                LOG.debug("save camapign should be no paramTaskId");
                throw new MeoException(CampaignErrorConstant.SAVE_CAMAPIGN_SHOULD_BE_NO_PARAMTASKID);
            }
        }
    }
    
    private void throwPublishCampaignParamPriceException(CampaignCommonVO campaignCommonVO)
    {
        if (ValidateUtil.isEmpty(campaignCommonVO.getCampaignPrices()))
        {
            LOG.debug("campaign price is null or empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_PRICE });
        }
    }
    
    // 判断参数是否可以为空
    private void throwSaveOrPublishCampaignParamException(CampaignCommonVO campaignCommonVO,
            List<CampaignDetailVO> campaignDetailVOs, List<CampaignTaskVO> campaignTaskVOs)
    {
        if (ValidateUtil.isEmpty(campaignDetailVOs))
        {
            LOG.debug("campaign detail is null or empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_DETAIL });
        }
        
        if (ValidateUtil.isEmpty(campaignTaskVOs))
        {
            LOG.debug("campaign task is null or empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_TASK });
        }
    }
    
    private void throwActionDefineIsNotFoundException(Long actionId)
    {
        ActionDefine actionDefine = campaignRserviceBO.getActionDefineByActionId(actionId);
        if (ValidateUtil.isNull(actionDefine))
        {
            throw new MeoException(CampaignErrorConstant.ACTIONDEFINE_IS_NOT_FOUND, new Object[]{actionId });
        }
    }
    
    private void throwCampaignTaskNotFound(List<Long> oldTaskIds, Long taskId)
    {
        if (!oldTaskIds.contains(taskId))
        {
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_IS_NOT_FOUND_BY_ID, new Object[]{taskId });
        }
    }
    
    // 对CampaignTaskVO做真正的处理，新增或者修改
    private void handleCampaignTaskVOToDB(List<CampaignTask> oldCampaignTaskList, List<CampaignTaskVO> campaignTaskVOs,
            Campaign campaign, List<Long> oldTaskIds)
    {
        Long parentTaskId = null;
        for (CampaignTaskVO campaignTaskVO : campaignTaskVOs)
        {
            boolean operation = ValidateUtil.isNull(campaignTaskVO.getTaskId());
            CampaignTask pCampaignTask = new CampaignTask();
            throwActionDefineIsNotFoundException(campaignTaskVO.getActionId());
            if (operation)
            {
                pCampaignTask.setActionId(campaignTaskVO.getActionId());
                pCampaignTask.setCreateDate(DateTimeUtil.getNow());
                pCampaignTask.setCampaignId(campaign.getCampaignId());
                pCampaignTask.setSts(CampaignTask.STS_VALID);
                pCampaignTask = campaignTaskBO.saveCampaignTask(pCampaignTask);
            }
            else
            {
                throwCampaignTaskNotFound(oldTaskIds, campaignTaskVO.getTaskId());
                for (CampaignTask tempCampaignTask : oldCampaignTaskList)
                {
                    if (tempCampaignTask.getTaskId()== campaignTaskVO.getTaskId())
                    {
                        tempCampaignTask.setActionId(campaignTaskVO.getActionId());
                        tempCampaignTask.setCampaignId(campaign.getCampaignId());
                        tempCampaignTask.setModifyDate(DateTimeUtil.getNow());
                        pCampaignTask = campaignTaskBO.updateCampaignTask(tempCampaignTask);
                        break;
                    }
                }
            }
            if (ValidateUtil.isNull(campaignTaskVO.getParentActionId()))
            {
                parentTaskId = pCampaignTask.getTaskId();
            }
            campaignTaskVO.setTaskId(pCampaignTask.getTaskId());
            if (operation)
            {
                saveCampaignTaskRel(campaign, campaignTaskVO, oldTaskIds, parentTaskId);
                createCampaignTaskRule(campaignTaskVO.getActionId(), campaignTaskVO.getTaskId());
            }
            List<TaskParamVO> taskParamVOList = campaignTaskVO.getTaskParamList();
            saveTaskParamInstance(taskParamVOList, campaignTaskVO.getTaskId());
            // /////////////////////////////////son///////////////////////////////////////////////
            List<TriggerTaskVO> triggerTaskVOList = campaignTaskVO.getTriggerTaskList();
            if (ValidateUtil.isNotEmpty(triggerTaskVOList))
            {
                for (TriggerTaskVO triggerTaskVO : triggerTaskVOList)
                {
                    throwActionDefineIsNotFoundException(triggerTaskVO.getActionId());
                    boolean sOperation = ValidateUtil.isNull(triggerTaskVO.getTaskId());
                    CampaignTask sCampaignTask = new CampaignTask();
                    if (sOperation)
                    {
                        sCampaignTask.setActionId(triggerTaskVO.getActionId());
                        sCampaignTask.setCreateDate(DateTimeUtil.getNow());
                        sCampaignTask.setCampaignId(campaign.getCampaignId());
                        sCampaignTask.setSts(CampaignTask.STS_VALID);
                        sCampaignTask = campaignTaskBO.saveCampaignTask(sCampaignTask);
                    }
                    else
                    {
                        throwCampaignTaskNotFound(oldTaskIds, triggerTaskVO.getTaskId());
                        for (CampaignTask tempCampaignTask : oldCampaignTaskList)
                        {
                            if (tempCampaignTask.getTaskId()== triggerTaskVO.getTaskId())
                            {
                                tempCampaignTask.setActionId(triggerTaskVO.getActionId());
                                tempCampaignTask.setCampaignId(campaign.getCampaignId());
                                tempCampaignTask.setModifyDate(DateTimeUtil.getNow());
                                sCampaignTask = campaignTaskBO.updateCampaignTask(tempCampaignTask);
                                break;
                            }
                        }
                    }
                    triggerTaskVO.setTaskId(sCampaignTask.getTaskId());
                    if (sOperation)
                    {
                        saveTriggerCampaignTaskRel(campaign, triggerTaskVO, campaignTaskVO.getTaskId());
                        createCampaignTaskRule(triggerTaskVO.getActionId(), triggerTaskVO.getTaskId());
                    }
                    List<TaskParamVO> triggerParamList = triggerTaskVO.getTaskParamList();
                    saveTaskParamInstance(triggerParamList, triggerTaskVO.getTaskId());
                }
            }
        }
    }
    
    private void createCampaignTaskRule(Long actionId, Long taskId)
    {
        // 从sys_action_def中查询到rule_id,然后创建对象实例化到CM_CAMPAIGN_TASK_RULE_REL
        List<Long> triggerActionIds = new ArrayList<Long>();
        triggerActionIds.add(actionId);
        List<CampaignTaskRule> tempTriggerCampaignTaskRuleList = campaignTaskBO
                .getCampaignTaskRuleListByActionIds(triggerActionIds);
        if (ValidateUtil.isEmpty(tempTriggerCampaignTaskRuleList))
        {
            LOG.debug("campaign task rule is not found by action id,action id is "+ triggerActionIds);
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_RULE_IS_NOT_FOUND_BY_ACTION_ID,
                    new Object[]{triggerActionIds });
        }
        CampaignTaskRule tempTriggerCampaignTaskRule = tempTriggerCampaignTaskRuleList.get(0);
        CampaignTaskRuleRel campaignTaskRuleRel = new CampaignTaskRuleRel();
        campaignTaskRuleRel.setCreateDate(DateTimeUtil.getNow());
        campaignTaskRuleRel.setRuleId(tempTriggerCampaignTaskRule.getRuleId());
        campaignTaskRuleRel.setTaskId(taskId);
        campaignTaskRuleRel.setSts(CampaignTaskRuleRel.STS_VALID);
        campaignTaskBO.saveCampaignTaskRuleRel(campaignTaskRuleRel);
    }
    
    // 处理需要被删除的数据
    private void handleNeedDeleteDatas(List<CampaignTaskRel> oldCampaignTaskRelList, List<Long> oldTaskIds, List<Long> uiTaskIds,
            List<Long> oldTaskParamInstanceIds, List<Long> uiTaskParamInstanceIds,
            List<CampaignTaskRuleRel> oldCampaignTaskRuleRelList, List<CampaignTaskRule> uiCampaignTaskRuleList,
            List<Long> uiRuleIds, boolean action)
    {
        // action :true表示可以删除旧的关系数据
        if (!action)
        {
            return;
        }
        for (int i = 0; i< uiCampaignTaskRuleList.size(); i++ )
        {
            CampaignTaskRule campaignTaskRule = uiCampaignTaskRuleList.get(i);
            uiRuleIds.add(campaignTaskRule.getRuleId());
        }
        
        // 需要被删除的campaignTask的主键id
        Set<Long> campaignTaskIdsDelete = new HashSet<Long>();
        // 需要被删除的campaignParamInstance的主键id
        Set<Long> campaignParamInstanceIdsDelete = new HashSet<Long>();
        // 需要被删除的campaignTaskRuleRel的主键id
        // Set<Long> campaignTaskRuleRelIdsDelete = new HashSet<Long>();
        // 刪除不需要的campaignTask
        for (Long taskId : oldTaskIds)
        {
            if (!uiTaskIds.contains(taskId))
            {
                campaignTaskIdsDelete.add(taskId);
                campaignTaskBO.deleteCampaignTaskByTaskId(taskId);
                // 删除不需要的campaignTaskRuleRel
                for (int i = 0; i< oldCampaignTaskRuleRelList.size(); i++ )
                {
                    CampaignTaskRuleRel campaignTaskRuleRel = oldCampaignTaskRuleRelList.get(i);
                    if (taskId== campaignTaskRuleRel.getTaskId())
                    {
                        // campaignTaskRuleRelIdsDelete.add(campaignTaskRuleRel.getId());
                        campaignTaskBO.deleteCampaignTaskRuleRelById(campaignTaskRuleRel.getId());
                    }
                }
            }
        }
        // 删除不需要的TaskParamInstance
        for (Long campaignParamInstanceId : oldTaskParamInstanceIds)
        {
            if (!uiTaskParamInstanceIds.contains(campaignParamInstanceId))
            {
                campaignParamInstanceIdsDelete.add(campaignParamInstanceId);
                campaignTaskBO.deleteTaskParamInstanceById(campaignParamInstanceId);
            }
        }
        // 删除不需要的campaignTaskRel
        for (int i = 0; i< oldCampaignTaskRelList.size(); i++ )
        {
            CampaignTaskRel campaignTaskRel = oldCampaignTaskRelList.get(i);
            if (campaignTaskIdsDelete.contains(campaignTaskRel.getDestTaskId())
                    || campaignTaskIdsDelete.contains(campaignTaskRel.getSrcTaskId()))
            {
                campaignTaskBO.deleteCampaignTaskRelByRid(campaignTaskRel.getRelId());
            }
        }
        
    }
    
    private void handleCampaignDetailVOs(List<CampaignDetailVO> campaignDetailVOs, Campaign campaign,
            List<Long> oldCampaignPropertyIds, List<Long> uiCampaignPropertyIds, boolean action)
    {
        // action :true表示可以删除旧的关系数据
        for (CampaignDetailVO campaignDetailVO : campaignDetailVOs)
        {
            Long propertyId = campaignDetailVO.getPropertyId();
            if (ValidateUtil.isNull(propertyId))
            {
                // 如果为空做新增操作
                campaignProfileBO.saveCampaignProperty(campaignDetailVO, campaign.getCampaignType(), campaign.getCampaignId());
            }
            else
            {
                CampaignProperty campaignProperty = campaignProfileBO.getCampaignPropertyById(propertyId);
                if (ValidateUtil.isNull(campaignProperty))
                {
                    LOG.debug("campaign task rule is not found by action id,action id is "+ propertyId);
                    // 这里需要抛出异常，告知前端说没有查询到相应的数据
                    throw new MeoException(CampaignErrorConstant.CAMPAIGN_PROPERTY_IS_NOT_FOUND, new Object[]{propertyId });
                }
                campaignProfileBO.updateCampaignProperty(campaignDetailVO, campaign.getCampaignType(), campaign.getCampaignId());
            }
        }
        // 这里需要加上action一起验证
        if (!action)
        {
            return;
        }
        // 需要删除的
        for (Long campaignPropertyId : oldCampaignPropertyIds)
        {
            if (!uiCampaignPropertyIds.contains(campaignPropertyId))
            {
                // 逻辑删除
                campaignProfileBO.deleteCampaignProperty(campaignPropertyId);
            }
        }
    }
    
    // 处理campaign
    private void handleCampaignPrices(Campaign campaign, List<CampaignPriceRel> oldCampaignPriceRleList, List<Long> oldPriceIds,
            List<Long> uiPriceIds, List<CampaignPriceVO> campaignPrices, Long contractId, boolean publish, boolean action)
    {
        // publish :true表示为发布，false为不发布
        // action :true表示可以刪除就的关系数据
        // 如果前端没有传递campaignPrice，做逻辑删除
        if (ValidateUtil.isEmpty(campaignPrices))
        {
            if (publish)
            {
                LOG.debug("publish campaign, price can not be null");
                throw new MeoException(CampaignErrorConstant.PUBLISH_CAMPAIGN_PRICE_CAN_NOT_BE_NULL);
            }
            for (CampaignPriceRel campaignPriceRel : oldCampaignPriceRleList)
            {
                campaignProfileBO.updateCampaignPriceStsRelInvalid(campaignPriceRel);
            }
            return;
        }
        
        for (CampaignPriceVO campaignPriceVo : campaignPrices)
        {
            Price price = new Price();
            price.setUnitPrice(campaignPriceVo.getUnitPrice());
            price.setUnit(campaignPriceVo.getUnit());
            price.setChargeUnit(campaignPriceVo.getChargeUnit());
            price.setPriceType(campaignPriceVo.getPriceType());
            price.setSts(Price.STS_VALID);
            price.setValidDate(campaign.getValidDate());
            price.setExpiredDate(campaign.getExpiredDate());
            Long priceId = campaignPriceVo.getPriceId();
            // action 为true的话表示修改，否则表示新增了一条campaign
            if (ValidateUtil.isNull(priceId))
            {
                // 如果priceId为空做新增操作
                price = campaignRserviceBO.addPrice(price);
                CampaignPriceRel campaignPriceRel = new CampaignPriceRel();
                campaignPriceRel.setValidDate(campaign.getValidDate());
                campaignPriceRel.setExpiredDate(campaign.getExpiredDate());
                campaignPriceRel.setCampaignId(campaign.getCampaignId());
                campaignPriceRel.setPriceId(price.getPriceId());
                campaignPriceRel.setContractId(contractId);
                campaignPriceRel.setSts(CampaignPriceRel.STS_VALID);
                campaignProfileBO.saveCampaignPriceRel(campaignPriceRel);
            }
            else
            {
                CampaignPriceRel tempCampaignPriceRel = campaignProfileBO.getCampaignPriceRelByPriceIdAndCampaignId(priceId,
                        campaign.getCampaignId());
                if (ValidateUtil.isNull(tempCampaignPriceRel))
                {
                    LOG.debug("campaign price rel is not found,price id is "+ priceId);
                    // 这里需要抛出异常告知前端，传递过来的priceId有问题
                    throw new MeoException(CampaignErrorConstant.CAMPAIGN_PRICE_REL_IS_NOT_FOUND, new Object[]{priceId });
                }
                Price tempPrice = campaignRserviceBO.getPrice(priceId);
                if (ValidateUtil.isNull(tempPrice))
                {
                    LOG.debug("campaign task rule is not found by action id,action id is "+ priceId);
                    // 这里需要抛出异常，说明前端传递过来的priceId没有在数据库中找到对应的数据
                    throw new MeoException(CampaignErrorConstant.CAMPAIGN_PRICE_IS_NOT_FOUND, new Object[]{priceId });
                }
                // 需要被修改的price
                price.setModifyDate(DateTimeUtil.getNow());
                price.setPriceId(priceId);
                BeanUtils.copyProperties(price, tempPrice);
                price = campaignRserviceBO.updatePrice(tempPrice);
                
                for (CampaignPriceRel campaignPriceRel : oldCampaignPriceRleList)
                {
                    if (price.getPriceId()== campaignPriceRel.getPriceId())
                    {
                        campaignPriceRel.setValidDate(price.getValidDate());
                        campaignPriceRel.setExpiredDate(price.getExpiredDate());
                        campaignPriceRel.setModifyDate(DateTimeUtil.getNow());
                        campaignProfileBO.updateCampaignPriceRel(campaignPriceRel);
                    }
                }
            }
        }
        if (!action)
        {
            return;
        }
        // 需要被删除的price
        if (ValidateUtil.isNotEmpty(oldPriceIds))
        {
            for (Long oldPriceId : oldPriceIds)
            {
                if (!uiPriceIds.contains(oldPriceId))
                {
                    // 这里先不出price，但是可能变为了脏数据
                    // campaignRserviceBO.deletePrice(oldPriceId);
                    for (CampaignPriceRel campaignPriceRel : oldCampaignPriceRleList)
                    {
                        if (campaignPriceRel.getPriceId()== oldPriceId)
                        {
                            campaignProfileBO.updateCampaignPriceStsRelInvalid(campaignPriceRel);
                        }
                    }
                }
            }
        }
    }
    
    private void saveTriggerCampaignTaskRel(Campaign campaign, TriggerTaskVO triggerTaskVO, Long srcTaskId)
    {
        CampaignTaskRel sonCampaignTaskRel = new CampaignTaskRel();
        sonCampaignTaskRel.setDestTaskId(triggerTaskVO.getTaskId());
        sonCampaignTaskRel.setCreateDate(DateTimeUtil.getNow());
        sonCampaignTaskRel.setRelTypeId(CampaignTaskRel.REL_TYPE_TRIGGER);
        sonCampaignTaskRel.setSrcTaskId(srcTaskId);
        sonCampaignTaskRel.setCampaignId(campaign.getCampaignId());
        sonCampaignTaskRel.setSts(CampaignTaskRel.STS_VALID);
        campaignTaskBO.saveCampaignTaskRel(sonCampaignTaskRel);
    }
    
    private void saveCampaignTaskRel(Campaign campaign, CampaignTaskVO campaignTaskVO, List<Long> oldTaskIds, Long parentTaskId)
    {
        if (ValidateUtil.isNull(campaignTaskVO.getParentTaskId())&& ValidateUtil.isNull(campaignTaskVO.getParentActionId()))
        {
            return;
        }
        // CampaignTask campaignTask = null;
        Long srcTaskId = campaignTaskVO.getParentTaskId();
        if (ValidateUtil.isNotNull(srcTaskId))
        {
            throwCampaignTaskNotFound(oldTaskIds, srcTaskId);
        }
        if (ValidateUtil.isNull(srcTaskId)&& ValidateUtil.isNotNull(campaignTaskVO.getParentActionId())
                && ValidateUtil.isNotNull(parentTaskId))
        {
            // List<CampaignTask> campaignTaskList = campaignTaskBO.getCampaignTaskByActionIdAndCampaignId(
            // campaignTaskVO.getParentActionId(), campaign.getCampaignId());
            // if (ValidateUtil.isEmpty(campaignTaskList))
            // {
            // LOG.debug("campaign task is not found by action id,action id is "+ campaignTaskVO.getParentActionId());
            // throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_IS_NOT_FOUND_BY_ACTION_ID,
            // new Object[]{campaignTaskVO.getParentActionId() });
            // }
            // else if (campaignTaskList.size()> 1)
            // {
            // LOG.debug("campaign parent task is worry ruel,action id is "+ campaignTaskVO.getParentActionId());
            // throw new MeoException(CampaignErrorConstant.CAMPAIGN_PARENT_TASK_IS_WORRY_RULE,
            // new Object[]{campaignTaskVO.getParentActionId() });
            // }
            // else
            // {
            // campaignTask = campaignTaskList.get(0);
            // }
            srcTaskId = parentTaskId;
            // srcTaskId = campaignTaskVO.getTaskId();
        }
        CampaignTaskRel campaignTaskRel = new CampaignTaskRel();
        campaignTaskRel.setCampaignId(campaign.getCampaignId());
        campaignTaskRel.setDestTaskId(campaignTaskVO.getTaskId());
        campaignTaskRel.setCreateDate(DateTimeUtil.getNow());
        campaignTaskRel.setRelTypeId(CampaignTaskRel.REL_TYPE_PARENT);
        campaignTaskRel.setSrcTaskId(srcTaskId);
        campaignTaskRel.setSts(CampaignTaskRel.STS_VALID);
        campaignTaskBO.saveCampaignTaskRel(campaignTaskRel);
    }
    
    private void saveTaskParamInstance(List<TaskParamVO> taskParamVOList, Long taskId)
    {
        if (ValidateUtil.isEmpty(taskParamVOList))
        {
            return;
        }
        
        for (TaskParamVO taskParamVO : taskParamVOList)
        {
            Long taskParamId = taskParamVO.getParamInstanceId();
            TaskParamInstance taskParamInstance = new TaskParamInstance();
            taskParamInstance.setTaskId(taskId);
            taskParamInstance.setCreateDate(DateTimeUtil.getNow());
            if (ValidateUtil.isNull(taskParamVO.getParamId()))
            {
                throw new MeoException(CampaignErrorConstant.SYS_ACTION_PARAM_ID_CAN_NOT_BE_NULL);
            }
            SysActionParamDefineVO tempSysActionParamDefineVo = campaignRserviceBO.getParamByParamId(taskParamVO.getParamId());
            if (ValidateUtil.isNull(tempSysActionParamDefineVo))
            {
                throw new MeoException(CampaignErrorConstant.SYS_ACTION_PARAM_IS_NOT_FOUND,
                        new Object[]{taskParamVO.getParamId() });
            }
            taskParamInstance.setParamId(taskParamVO.getParamId());
            taskParamInstance.setParamValue(taskParamVO.getParamValue());
            taskParamInstance.setParamValueUnit(taskParamVO.getParamValueUnit());
            taskParamInstance.setSts(TaskParamInstance.STS_VALID);
            if (ValidateUtil.isNull(taskParamId))
            {
                // 如果taskparamid为空做新增操作
                campaignTaskBO.saveTaskParamInstance(taskParamInstance);
            }
            else
            {
                // 如果不为空做修改操作
                TaskParamInstance tempTaskParamInstance = campaignTaskBO.getTaskParamInstanceById(taskParamId);
                if (ValidateUtil.isNull(tempTaskParamInstance))
                {
                    LOG.debug("task param instance is not found,paramInstance id is "+ taskParamId);
                    throw new MeoException(CampaignErrorConstant.TASK_PARAM_INSTANCE_IS_NOT_FOUND, new Object[]{taskParamId });
                    // 这里需要抛出异常，告知前端说没有查询到相应的数据
                }
                taskParamInstance.setParamInstanceId(taskParamId);
                taskParamInstance.setModifyDate(DateTimeUtil.getNow());
                BeanUtils.copyProperties(taskParamInstance, tempTaskParamInstance);
                campaignTaskBO.updateTaskParamInstance(tempTaskParamInstance);
            }
        }
    }
    
    private void copyProperties(Long campaignId, List<CampaignTaskRel> oldCampaignTaskRelList,
            List<CampaignTask> oldCampaignTaskList, List<TaskParamInstance> oldTaskParamInstanceList,
            List<CampaignProperty> oldCampaignPropertyList, List<CampaignTaskRuleRel> oldCampaignTaskRuleRelList,
            List<CampaignTaskRule> oldCampaignTaskRuleList)
    {
        for (CampaignProperty campaignProperty : oldCampaignPropertyList)
        {
            CampaignProperty tempCampaignProperty = new CampaignProperty();
            BeanUtils.copyProperties(campaignProperty, tempCampaignProperty, new String[]{"propertyId", "campaignId",
                    "modifyDate", "createDate" });
            tempCampaignProperty.setCampaignId(campaignId);
            tempCampaignProperty.setCreateDate(DateTimeUtil.getNow());
            campaignProfileBO.saveCampaignProperty(tempCampaignProperty);
        }
        
        List<CampaignTaskRel> newCampaignTaskRelList = new ArrayList<CampaignTaskRel>();
        for (CampaignTaskRel campaignTaskRel : oldCampaignTaskRelList)
        {
            CampaignTaskRel tempCampaignTaskRel = new CampaignTaskRel();
            BeanUtils.copyProperties(campaignTaskRel, tempCampaignTaskRel, new String[]{"relId", "campaignId", "modifyDate",
                    "createDate" });
            tempCampaignTaskRel.setCampaignId(campaignId);
            tempCampaignTaskRel.setCreateDate(DateTimeUtil.getNow());
            newCampaignTaskRelList.add(tempCampaignTaskRel);
        }
        
        for (CampaignTask oldCampaignTask : oldCampaignTaskList)
        {
            CampaignTask newCampaignTask = new CampaignTask();
            Long oldTaskId = oldCampaignTask.getTaskId();
            BeanUtils.copyProperties(oldCampaignTask, newCampaignTask, new String[]{"taskId", "campaignId", "modifyDate",
                    "createDate" });
            newCampaignTask.setCampaignId(campaignId);
            newCampaignTask.setCreateDate(DateTimeUtil.getNow());
            newCampaignTask = campaignTaskBO.saveCampaignTask(newCampaignTask);
            Long newTaskId = newCampaignTask.getTaskId();
            for (TaskParamInstance taskParamInstance : oldTaskParamInstanceList)
            {
                if (oldTaskId== taskParamInstance.getTaskId())
                {
                    TaskParamInstance tempTaskParamInstance = new TaskParamInstance();
                    BeanUtils.copyProperties(taskParamInstance, tempTaskParamInstance, new String[]{"paramInstanceId", "taskId",
                            "modifyDate", "createDate" });
                    tempTaskParamInstance.setTaskId(newTaskId);
                    tempTaskParamInstance.setCreateDate(DateTimeUtil.getNow());
                    campaignTaskBO.saveTaskParamInstance(tempTaskParamInstance);
                }
            }
            
            for (CampaignTaskRel campaignTaskRel : newCampaignTaskRelList)
            {
                if (campaignTaskRel.getSrcTaskId()== oldTaskId)
                {
                    campaignTaskRel.setSrcTaskId(newTaskId);
                }
                if (campaignTaskRel.getDestTaskId()== oldTaskId)
                {
                    campaignTaskRel.setDestTaskId(newTaskId);
                }
            }
            
            for (CampaignTaskRuleRel campaignTaskRuleRel : oldCampaignTaskRuleRelList)
            {
                if (campaignTaskRuleRel.getTaskId()== oldTaskId)
                {
                    CampaignTaskRuleRel tempCampaignTaskRuleRel = new CampaignTaskRuleRel();
                    BeanUtils.copyProperties(campaignTaskRuleRel, tempCampaignTaskRuleRel, new String[]{"id", "modifyDate",
                            "createDate" });
                    tempCampaignTaskRuleRel.setTaskId(newTaskId);
                    tempCampaignTaskRuleRel.setCreateDate(DateTimeUtil.getNow());
                    campaignTaskBO.saveCampaignTaskRuleRel(tempCampaignTaskRuleRel);
                }
            }
        }
        
        for (CampaignTaskRel campaignTaskRel : newCampaignTaskRelList)
        {
            campaignTaskBO.saveCampaignTaskRel(campaignTaskRel);
        }
    }
    
    @Override
    public Campaign saveDraftCampaign(CampaignCommonVO campaignCommonVO, List<CampaignDetailVO> campaignDetailVOs,
            List<CampaignTaskVO> campaignTaskVOs)
    {
        // 判断参数是否可以为空
        throwCampaignParamCommonException(campaignCommonVO);
        throwSaveOrPublishCampaignParamException(campaignCommonVO, campaignDetailVOs, campaignTaskVOs);
        // 判断partner、contract以及相应的时间是否合法
        Contract contact = throwPartnerAndTimeException(campaignCommonVO);
        Long oldCampaignId = campaignCommonVO.getCampaignId();
        boolean action = oldCampaignId== null;
        
        // 前端传递过来的priceId
        List<Long> uiPriceIds = new ArrayList<Long>();
        List<CampaignPriceVO> campaignPrices = campaignCommonVO.getCampaignPrices();
        
        // 判断priceType的类型是否有重复
        throwCampaignPriceException(action, campaignPrices, uiPriceIds);
        
        // 前端传递过来的campaignPropertyId
        List<Long> uiCampaignPropertyIds = new ArrayList<Long>();
        throwCampaignDetailException(campaignCommonVO.getCampaignType(), action, campaignDetailVOs, uiCampaignPropertyIds);
        
        // 前端传递过来的taskid
        List<Long> uiTaskIds = new ArrayList<Long>();
        // 前端传递过来的taskParamInstanceId
        List<Long> uiTaskParamInstanceIds = new ArrayList<Long>();
        // 前端传递过来的campaignTaskRule中的actionid
        List<Long> uiActionIds = new ArrayList<Long>();
        // 判断如果是新增操作，前端是否有taskId或paramTaskId，如果有则抛出异常
        throwCampaignTaskException(action, campaignTaskVOs, uiTaskIds, uiTaskParamInstanceIds, uiActionIds);
        Campaign campaign = null;
        // --------------------------------------------------------------------------------------------------------
        // 查询出数据库已经存在的campaignTaskRel关系
        List<CampaignTaskRel> oldCampaignTaskRelList = new ArrayList<CampaignTaskRel>();
        // 查询到数据库已经存在的campaignTask
        List<CampaignTask> oldCampaignTaskList = new ArrayList<CampaignTask>();
        // 数据库中已经存在的taskid
        List<Long> oldTaskIds = new ArrayList<Long>();
        
        // 查询到数据库中已经存在的taskParamInstance
        List<TaskParamInstance> oldTaskParamInstanceList = new ArrayList<TaskParamInstance>();
        // 数据库中已经存在的taskParamInstanceId
        List<Long> oldTaskParamInstanceIds = new ArrayList<Long>();
        
        // 查询到数据库中已经存在的campaignPriceRel
        List<CampaignPriceRel> oldCampaignPriceRleList = new ArrayList<CampaignPriceRel>();
        // 查询到数据库中已经存在价格Id
        List<Long> oldPriceIds = new ArrayList<Long>();
        
        // 查询到数据库中已经存在的campaignProperty
        List<CampaignProperty> oldCampaignPropertyList = new ArrayList<CampaignProperty>();
        // 数据库中存在的campaignPropertyId
        List<Long> oldCampaignPropertyIds = new ArrayList<Long>();
        
        // 数据库中已经存在的CampaignTaskRuleRel
        List<CampaignTaskRuleRel> oldCampaignTaskRuleRelList = new ArrayList<CampaignTaskRuleRel>();
        // 数据库中已经存在CampaignTaskRule
        List<CampaignTaskRule> oldCampaignTaskRuleList = new ArrayList<CampaignTaskRule>();
        // 数据库中已经存在的ruleId
        List<Long> oldRuleIds = new ArrayList<Long>();
        // 数据库中存在的campaignTaskRule中的actingId
        List<Long> oldActionIds = new ArrayList<Long>();
        // 根据前端传递的actionId查询数据库中已经存在CampaignTaskRule
        List<CampaignTaskRule> uiCampaignTaskRuleList = new ArrayList<CampaignTaskRule>();
        
        // 通过前端传来的actionId到数据库中查询存在的ruleId
        List<Long> uiRuleIds = new ArrayList<Long>();
        // ------------------------------------------------------------------------------------------------------
        if (action)
        {
            // 如果campaignId为空则为新增操作，否则为修改操作。
            campaign = campaignProfileBO.addCampaign(campaignCommonVO);
        }
        else
        {
            campaign = campaignProfileBO.getCampaignByCampaignId(oldCampaignId);
            // STS代表本条数据的状态，campaign_sts业务状态
            throwCampaignIsNullException(campaign, oldCampaignId);
            // 如果查询到的campaign不是draft状态，则告知前端状态不对，不能修改
            if (campaign.getCampaignSts()!= Campaign.STS_DRAF)
            {
                LOG.debug("campaign sts is not draft,campaign id is "+ oldCampaignId);
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_STS_IS_NOT_DRAFT, new Object[]{oldCampaignId });
            }
            oldCampaignTaskRelList = campaignTaskBO.getCampaignTaskRelList(oldCampaignId);
            oldCampaignTaskList = campaignTaskBO.getCampaignTaskList(oldCampaignId);
            for (CampaignTask campaignTask : oldCampaignTaskList)
            {
                oldTaskIds.add(campaignTask.getTaskId());
            }
            
            oldTaskParamInstanceList = campaignTaskBO.getTaskParamInstanceListByTaskIds(oldTaskIds);
            for (int i = 0; i< oldTaskParamInstanceList.size(); i++ )
            {
                TaskParamInstance taskParamInstance = oldTaskParamInstanceList.get(i);
                oldTaskParamInstanceIds.add(taskParamInstance.getParamInstanceId());
            }
            
            oldCampaignPriceRleList = campaignProfileBO.getCampaignPriceRelsByCampaignId(oldCampaignId);
            Long[] tempOldPriceIds = new Long[oldCampaignPriceRleList.size()];
            if (ValidateUtil.isNotEmpty(oldCampaignPriceRleList))
            {
                for (int i = 0; i< oldCampaignPriceRleList.size(); i++ )
                {
                    CampaignPriceRel campaignPriceRle = oldCampaignPriceRleList.get(i);
                    tempOldPriceIds[i] = campaignPriceRle.getPriceId();
                }
                oldPriceIds = Arrays.asList(tempOldPriceIds);
            }
            
            oldCampaignPropertyList = campaignProfileBO.getCampaignPropertyListByCampaignId(oldCampaignId);
            for (int i = 0; i< oldCampaignPropertyList.size(); i++ )
            {
                Long propertyId = oldCampaignPropertyList.get(i).getPropertyId();
                oldCampaignPropertyIds.add(propertyId);
            }
            
            oldCampaignTaskRuleRelList = campaignTaskBO.getCampaignTaskRuleRelListByTaskIds(oldTaskIds);
            for (int i = 0; i< oldCampaignTaskRuleRelList.size(); i++ )
            {
                CampaignTaskRuleRel campaignTaskRuleRel = oldCampaignTaskRuleRelList.get(i);
                oldRuleIds.add(campaignTaskRuleRel.getRuleId());
            }
            
            for (int i = 0; i< oldCampaignTaskRuleList.size(); i++ )
            {
                CampaignTaskRule campaignTaskRule = oldCampaignTaskRuleList.get(i);
                oldActionIds.add(campaignTaskRule.getActionId());
            }
            
            campaign = campaignProfileBO.updateCampaign(campaign, campaignCommonVO);
        }
        // 处理campaignPrice
        handleCampaignPrices(campaign, oldCampaignPriceRleList, oldPriceIds, uiPriceIds, campaignPrices, contact.getContractId(),
                false, true);
        // campaignDetail已经处理完
        handleCampaignDetailVOs(campaignDetailVOs, campaign, oldCampaignPropertyIds, uiCampaignPropertyIds, true);
        // uiRuleIds
        uiCampaignTaskRuleList = campaignTaskBO.getCampaignTaskRuleListByActionIds(uiActionIds);
        // 处理需要删除的数据
        handleNeedDeleteDatas(oldCampaignTaskRelList, oldTaskIds, uiTaskIds, oldTaskParamInstanceIds, uiTaskParamInstanceIds,
                oldCampaignTaskRuleRelList, uiCampaignTaskRuleList, uiRuleIds, true);
        // 开始新增或修改campaignTask 、campaignTaskRuleRel、taskParamInstance
        handleCampaignTaskVOToDB(oldCampaignTaskList, campaignTaskVOs, campaign, oldTaskIds);
        return campaign;
    }
    
    private void throwCampaignIsNullException(Campaign campaign, Long campaignId)
    {
        if (ValidateUtil.isNull(campaign))
        {
            LOG.debug("campaign is not found,campaign id is "+ campaignId);
            // 要向前端抛出异常，告知没有找到数据
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_IS_NOT_FOUND, new Object[]{campaignId });
        }
    }
    
    // 判断partner、contract以及相应的时间是否合法
    private Contract throwPartnerAndTimeException(CampaignCommonVO campaignCommonVO)
    {
        Long partnerId = campaignCommonVO.getPartnerId();
        if (ValidateUtil.isNull(partnerId))
        {
            LOG.debug("partner id is null or empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        Partner partner = campaignRserviceBO.getPartnerById(partnerId);
        if (ValidateUtil.isNull(partner))
        {
            throw new MeoException(CampaignErrorConstant.PARTNER_IS_NOT_FOUND, new Object[]{partnerId });
        }
        if (partner.getSts()!= Partner.STS_ACTIVE)
        {
            throw new MeoException(CampaignErrorConstant.PARTNER_STS_IS_NOT_ACTIVE, new Object[]{partnerId });
        }
        // 查询出有效的合同，来判断campaign的有效期是否正确
        Contract contact = throwCampaingContractIsValidOrNot(partnerId);
        
        Long validDate = contact.getValidDate().getTime();
        Long expiredDate = contact.getExpiredDate().getTime();
        Long campaignStartDate = campaignCommonVO.getStartDate();
        Long campaignEndDate = campaignCommonVO.getEndDate();
        Long campaignValidDate = campaignCommonVO.getValidDate();
        LOG.debug("contract validDate:"+ validDate+ ",transformation to long is :"+ validDate);
        LOG.debug("contract expiredDate:"+ expiredDate+ ",transformation to long is :"+ expiredDate);
        LOG.debug("campaignCommonVO startDate is :"+ campaignStartDate);
        LOG.debug("campaignCommonVO endDate:"+ campaignEndDate);
        LOG.debug("campaignCommonVO validDate:"+ campaignValidDate);
        
        // 得到明天开始时间
        Long tomorrowTime = DateTimeUtil.getNextDayStartTime().getTime();
        LOG.debug("nextDay is :"+ tomorrowTime);
        
        if (!campaignValidDate.equals(campaignStartDate))
        {
            LOG.debug("the first version campaign validDate must be equal to the campaign startDate,the partner id is "
                    + partnerId);
            throw new MeoException(CampaignErrorConstant.FIRST_VERSION_CAMPAIGN_VAILDDATE_NOT_EQUAL_STARTDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignStartDate< validDate)
        {
            LOG.debug("campaignBeginDate can not as early as contract validDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNBEGINDATE_CAN_NOT_LT_CONTRACT_VALIDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignEndDate< validDate)
        {
            LOG.debug("campaignEndDate can not as early as contract validDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNENDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE, new Object[]{partnerId });
        }
        
        // if (ValidateUtil.isNotNull(campaignValidDate))
        // {
        // if (campaignValidDate< validDate)
        // {
        // LOG.debug("campaign validDate can not as early as contract validDate,the partner id is "+ partnerId);
        // throw new MeoException(CampaignErrorConstant.CAMPAIGN_VALIDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE,
        // new Object[]{partnerId });
        // }
        
        // if (campaignValidDate> campaignStartDate)
        // {
        // LOG.debug("the first version campaign validDate can not more than campaign startDate,the partner id is "+ partnerId);
        // throw new MeoException(CampaignErrorConstant.FIRST_VERSION_CAMPAIGN_VALIDDATE_CAN_NOT_GT_CAMPAIGN_STARTDATE,
        // new Object[]{partnerId });
        // }
        
        // if (campaignValidDate< tomorrowTime)
        // {
        // LOG.debug("campaign validDate can not as early as tomorrow,the partner id is "+ partnerId);
        // throw new MeoException(CampaignErrorConstant.CAMPAIGN_VALIDDATE_CAN_NOT_LT_TOMORROW, new Object[]{partnerId });
        // }
        
        if (campaignEndDate< campaignValidDate)
        {
            LOG.debug("campaignEndDate can not as early as  campaign validDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNENDDATE_CAN_NOT_LT_CAMPAIGN_VALIDDATE, new Object[]{partnerId });
        }
        
        // if (campaignValidDate> expiredDate)
        // {
        // LOG.debug("campaign validDate can not more than contract expiredDate,the partner id is "+ partnerId);
        // throw new MeoException(CampaignErrorConstant.CAMPAIGN_VALIDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE,
        // new Object[]{partnerId });
        // }
        // }
        
        if (campaignStartDate> expiredDate)
        {
            LOG.debug("campaignBeginDate can not more than contract expiredDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNBEGINDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignEndDate> expiredDate)
        {
            LOG.debug("campaignEndDate can not more than contract expiredDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNENDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignStartDate< tomorrowTime)
        {
            LOG.debug("campaignBeginDate can not as early as tomorrow,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNBEGINDATE_CAN_NOT_LT_TOMORROW, new Object[]{partnerId });
        }
        
        if (campaignEndDate< tomorrowTime)
        {
            LOG.debug("campaignEndDate can not as early as tomorrow,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNENDDATE_CAN_NOT_LT_TOMORROW, new Object[]{partnerId });
        }
        
        if (campaignStartDate> campaignEndDate)
        {
            LOG.debug("campaignBeginDate can not more than campaignEndDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGNBEGINDATE_CAN_NOT_GT_CAMPAIGNENDDATE, new Object[]{partnerId });
        }
        return contact;
    }
    
    @Override
    public Campaign publishCampaign(CampaignCommonVO campaignCommonVO, List<CampaignDetailVO> campaignDetailVOs,
            List<CampaignTaskVO> campaignTaskVOs)
    {
        // 判断参数是否可以为空
        throwCampaignParamCommonException(campaignCommonVO);
        throwPublishCampaignParamPriceException(campaignCommonVO);
        throwSaveOrPublishCampaignParamException(campaignCommonVO, campaignDetailVOs, campaignTaskVOs);
        // 判断partner、contract以及相应的时间是否合法
        Contract contact = throwPartnerAndTimeException(campaignCommonVO);
        // 1、新增的数据直接发布为publish状态
        // 2、将数据库中的数据，做一下修改再发布为publish状态
        Long oldCampaignId = campaignCommonVO.getCampaignId();
        boolean action = oldCampaignId== null;
        Campaign campaign = null;
        
        // 前端传递过来的priceId
        List<Long> uiPriceIds = new ArrayList<Long>();
        List<CampaignPriceVO> campaignPrices = campaignCommonVO.getCampaignPrices();
        
        // 判断priceType的类型是否有重复
        throwCampaignPriceException(action, campaignPrices, uiPriceIds);
        
        // 前端传递过来的campaignPropertyId
        List<Long> uiCampaignPropertyIds = new ArrayList<Long>();
        throwCampaignDetailException(campaignCommonVO.getCampaignType(), action, campaignDetailVOs, uiCampaignPropertyIds);
        
        // 前端传递过来的taskid
        List<Long> uiTaskIds = new ArrayList<Long>();
        // 前端传递过来的taskParamInstanceId
        List<Long> uiTaskParamInstanceIds = new ArrayList<Long>();
        // 前端传递过来的campaignTaskRule中的actionId
        List<Long> uiActionIds = new ArrayList<Long>();
        // 判断如果是新增操作，前端是否有taskId或paramTaskId，如果有则抛出异常
        throwCampaignTaskException(action, campaignTaskVOs, uiTaskIds, uiTaskParamInstanceIds, uiActionIds);
        
        // 查询出数据库已经存在的campaignTaskRel关系
        List<CampaignTaskRel> oldCampaignTaskRelList = new ArrayList<CampaignTaskRel>();
        // 查询到数据库已经存在的campaignTask
        List<CampaignTask> oldCampaignTaskList = new ArrayList<CampaignTask>();
        // 数据库中已经存在的taskId
        List<Long> oldTaskIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的taskParamInstance
        List<TaskParamInstance> oldTaskParamInstanceList = new ArrayList<TaskParamInstance>();
        // 数据库中已经存在的taskParamInstanceId
        List<Long> oldTaskParamInstanceIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的campaignPriceRel
        List<CampaignPriceRel> oldCampaignPriceRleList = new ArrayList<CampaignPriceRel>();
        // 查询到数据库中已经存在价格Id
        List<Long> oldPriceIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的campaignProperty
        List<CampaignProperty> oldCampaignPropertyList = new ArrayList<CampaignProperty>();
        // 数据库中存在的campaignPropertyId
        List<Long> oldCampaignPropertyIds = new ArrayList<Long>();
        // 数据库中已经存在的CampaignTaskRuleRel
        List<CampaignTaskRuleRel> oldCampaignTaskRuleRelList = new ArrayList<CampaignTaskRuleRel>();
        // 数据库中已经存在CampaignTaskRule
        List<CampaignTaskRule> oldCampaignTaskRuleList = new ArrayList<CampaignTaskRule>();
        // 数据库中已经存在的ruleId
        List<Long> oldRuleIds = new ArrayList<Long>();
        // 数据库中存在的campaignTaskRule中的actingId
        List<Long> oldActionIds = new ArrayList<Long>();
        // 根据前端传递的actionId查询数据库中已经存在CampaignTaskRule
        List<CampaignTaskRule> uiCampaignTaskRuleList = new ArrayList<CampaignTaskRule>();
        // 通过前端传来的actionId到数据库中查询存在的ruleId
        List<Long> uiRuleIds = new ArrayList<Long>();
        
        if (action)
        {
            // 如果campaignId为空则直接publish该条数据
            campaign = campaignProfileBO.addPublishCampaign(campaignCommonVO);
            // 实例化CmEntityParticipant
            campaignRserviceBO.addEntityParticipant(campaign.getCampaignNo());
        }
        else
        {
            campaign = campaignProfileBO.getCampaignByCampaignId(oldCampaignId);
            // STS代表本条数据的状态，campaign_sts业务状态
            throwCampaignIsNullException(campaign, oldCampaignId);
            // 如果查询到的campaign不是draft状态，则告知前端状态不对，该条数据不能被published修改
            if (campaign.getCampaignSts()!= Campaign.STS_DRAF)
            {
                LOG.debug("campaign sts is not draft,campaign id is "+ oldCampaignId);
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_STS_IS_NOT_DRAFT, new Object[]{oldCampaignId });
            }
            
            oldCampaignTaskRelList = campaignTaskBO.getCampaignTaskRelList(oldCampaignId);
            oldCampaignTaskList = campaignTaskBO.getCampaignTaskList(oldCampaignId);
            
            for (CampaignTask campaignTask : oldCampaignTaskList)
            {
                oldTaskIds.add(campaignTask.getTaskId());
            }
            oldTaskParamInstanceList = campaignTaskBO.getTaskParamInstanceListByTaskIds(oldTaskIds);
            
            for (int i = 0; i< oldTaskParamInstanceList.size(); i++ )
            {
                TaskParamInstance taskParamInstance = oldTaskParamInstanceList.get(i);
                oldTaskParamInstanceIds.add(taskParamInstance.getParamInstanceId());
            }
            
            oldCampaignPriceRleList = campaignProfileBO.getCampaignPriceRelsByCampaignId(oldCampaignId);
            Long[] tempOldPriceIds = new Long[oldCampaignPriceRleList.size()];
            if (ValidateUtil.isNotEmpty(oldCampaignPriceRleList))
            {
                for (int i = 0; i< oldCampaignPriceRleList.size(); i++ )
                {
                    CampaignPriceRel campaignPriceRle = oldCampaignPriceRleList.get(i);
                    tempOldPriceIds[i] = campaignPriceRle.getPriceId();
                }
                oldPriceIds = Arrays.asList(tempOldPriceIds);
            }
            oldCampaignPropertyList = campaignProfileBO.getCampaignPropertyListByCampaignId(oldCampaignId);
            
            for (int i = 0; i< oldCampaignPropertyList.size(); i++ )
            {
                Long propertyId = oldCampaignPropertyList.get(i).getPropertyId();
                oldCampaignPropertyIds.add(propertyId);
            }
            
            oldCampaignTaskRuleRelList = campaignTaskBO.getCampaignTaskRuleRelListByTaskIds(oldTaskIds);
            for (int i = 0; i< oldCampaignTaskRuleRelList.size(); i++ )
            {
                CampaignTaskRuleRel campaignTaskRuleRel = oldCampaignTaskRuleRelList.get(i);
                oldRuleIds.add(campaignTaskRuleRel.getRuleId());
            }
            
            oldCampaignTaskRuleList = campaignTaskBO.getCampaignTaskRuleListByRuleIds(oldRuleIds);
            for (int i = 0; i< oldCampaignTaskRuleList.size(); i++ )
            {
                CampaignTaskRule campaignTaskRule = oldCampaignTaskRuleList.get(i);
                oldActionIds.add(campaignTaskRule.getActionId());
            }
            
            campaign = campaignProfileBO.updatePublishCampaign(campaign, campaignCommonVO);
            // 实例化CmEntityParticipant
            campaignRserviceBO.addEntityParticipant(campaign.getCampaignNo());
        }
        // 处理campaignPrice
        handleCampaignPrices(campaign, oldCampaignPriceRleList, oldPriceIds, uiPriceIds, campaignPrices, contact.getContractId(),
                true, true);
        // campaign和prices已经处理完
        handleCampaignDetailVOs(campaignDetailVOs, campaign, oldCampaignPropertyIds, uiCampaignPropertyIds, true);
        // uiRuleIds
        uiCampaignTaskRuleList = campaignTaskBO.getCampaignTaskRuleListByActionIds(uiActionIds);
        // 处理需要删除的数据
        handleNeedDeleteDatas(oldCampaignTaskRelList, oldTaskIds, uiTaskIds, oldTaskParamInstanceIds, uiTaskParamInstanceIds,
                oldCampaignTaskRuleRelList, uiCampaignTaskRuleList, uiRuleIds, true);
        // 开始新增或修改campaignTask 、campaignTaskRuleRel、taskParamInstance
        handleCampaignTaskVOToDB(oldCampaignTaskList, campaignTaskVOs, campaign, oldTaskIds);
        return campaign;
    }
    
    @Override
    public Campaign modifyRenewCampaign(CampaignCommonVO campaignCommonVO)
    {
        throwCampaignParamCommonException(campaignCommonVO);
        Long oldCampaignId = campaignCommonVO.getCampaignId();
        throwRenewCampaignIdIsNull(oldCampaignId);
        // 前端传递过来的priceId
        List<Long> uiPriceIds = new ArrayList<Long>();
        List<CampaignPriceVO> campaignPrices = campaignCommonVO.getCampaignPrices();
        // 判断priceType的类型是否有重复
        throwCampaignPriceException(false, campaignPrices, uiPriceIds);
        // ////////////////////////////需要拷贝的信息//////////////////////////////////////////////
        // 数据库中已经存在的taskid
        List<Long> oldTaskIds = new ArrayList<Long>();
        // 查询出数据库已经存在的campaignTaskRel关系
        List<CampaignTaskRel> oldCampaignTaskRelList = new ArrayList<CampaignTaskRel>();
        // 查询到数据库已经存在的campaignTask
        List<CampaignTask> oldCampaignTaskList = new ArrayList<CampaignTask>();
        // 数据库中已经存在的taskParamInstanceId
        List<Long> oldTaskParamInstanceIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的taskParamInstance
        List<TaskParamInstance> oldTaskParamInstanceList = new ArrayList<TaskParamInstance>();
        // 查询到数据库中已经存在的campaignPriceRel
        List<CampaignPriceRel> oldCampaignPriceRleList = new ArrayList<CampaignPriceRel>();
        // 数据库中存在的campaignPropertyId
        List<Long> oldCampaignPropertyIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的campaignProperty
        List<CampaignProperty> oldCampaignPropertyList = new ArrayList<CampaignProperty>();
        // 数据库中已经存在的CampaignTaskRuleRel
        List<CampaignTaskRuleRel> oldCampaignTaskRuleRelList = new ArrayList<CampaignTaskRuleRel>();
        // 数据库中已经存在CampaignTaskRule
        List<CampaignTaskRule> oldCampaignTaskRuleList = new ArrayList<CampaignTaskRule>();
        // 数据库中已经存在的ruleId
        List<Long> oldRuleIds = new ArrayList<Long>();
        // 数据库中存在的campaignTaskRule中的actingId
        List<Long> oldActionIds = new ArrayList<Long>();
        // /////////////////////////////////////////////////////////////////////////
        
        boolean action = false;
        // 查询到数据库中已经存在价格Id
        List<Long> oldPriceIds = new ArrayList<Long>();
        oldCampaignPriceRleList = campaignProfileBO.getCampaignPriceRelsByCampaignId(oldCampaignId);
        Long[] tempOldPriceIds = new Long[oldCampaignPriceRleList.size()];
        for (int i = 0; i< oldCampaignPriceRleList.size(); i++ )
        {
            CampaignPriceRel campaignPriceRle = oldCampaignPriceRleList.get(i);
            tempOldPriceIds[i] = campaignPriceRle.getPriceId();
        }
        oldPriceIds = Arrays.asList(tempOldPriceIds);
        Campaign campaign = campaignProfileBO.getCampaignByCampaignId(oldCampaignId);
        throwCampaignIsNullException(campaign, oldCampaignId);
        // 查询出有效的合同，来判断campaign的有效期是否正确
        Contract contact = throwCampaingContractIsValidOrNot(campaign.getPartnerId());
        Long validDate = contact.getValidDate().getTime();
        Long expiredDate = contact.getExpiredDate().getTime();
        // STS代表本条数据的状态，campaign_sts业务状态
        throwRenewCampaignException(campaignCommonVO, oldCampaignId, campaign, validDate, expiredDate);
        
        if (Campaign.STS_ISEDIT!= campaign.getIsEdit())
        {
            LOG.debug("current campaign can not be modified,the campaign id is "+ oldCampaignId);
            throw new MeoException(CampaignErrorConstant.CURRENT_CAMPAIGN_CAN_NOT_BE_MODIFIED, new Object[]{oldCampaignId });
        }
        
        if (campaign.getCampaignSts()== Campaign.STS_DRAF)
        {
            // campaign的状态为 draft时不允许进修改动作
            LOG.debug("campaign sts is draft,campaign id is "+ oldCampaignId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_STS_IS_DRAFT, new Object[]{oldCampaignId });
        }
        else if (campaign.getCampaignSts()== Campaign.STS_ACTIVE|| campaign.getCampaignSts()== Campaign.STS_SUSPENDED)
        {
            List<Campaign> campaignList = campaignProfileBO.getCampaignListByCampaignNo(campaign.getCampaignNo());
            if (ValidateUtil.isEmpty(campaignList))
            {
                LOG.debug("valid campaign is not found by campaignNo,campaignNo is "+ campaign.getCampaignNo());
                throw new MeoException(CampaignErrorConstant.VALID_CAMPAIGN_IS_NOT_FOUND_BY_CAMPAIGNNO,
                        new Object[]{campaign.getCampaignNo() });
            }
            if (campaignList.size()> 1)
            {
                LOG.debug("in the same campaignno,valid and isedit campaign should only one version,the campaignNo is "
                        + campaign.getCampaignNo());
                throw new MeoException(
                        CampaignErrorConstant.VALID_ISEDIT_CAMPAIGN_IN_THE_SAME_CAMPAIGNNO_SHOULD_ONLY_ONE_VERSION,
                        new Object[]{campaign.getCampaignNo() });
            }
            // 判断可用的campaign版本数量不能大于2
            throwAvailableVersionCampaignException(campaign.getCampaignNo());
            if (("1".equals(campaign.getVersion())&& campaign.getCampaignBeginDate().getTime()> DateTimeUtil.getNow().getTime())
                    || (Integer.valueOf(campaign.getVersion())> 1&& campaign.getValidDate().getTime()> DateTimeUtil.getNow()
                            .getTime()))
            {
                // 如果为active状态，还没有到生效日期，则可以直接修改
                campaignProfileBO.updateActiveCampaign(campaign, campaignCommonVO);
                campaign = campaignProfileBO.getCampaignByCampaignId(campaign.getCampaignId());
            }
            else
            {
                if (ValidateUtil.isNotEmpty(uiPriceIds))
                {
                    LOG.debug("save campaign,should be no priceId");
                    // 这里需要抛出异常，告知前端在save Campaign做save 操作的时候不应该传递priceId过来。
                    throw new MeoException(CampaignErrorConstant.SAVE_CAMAPIGN_SHOULD_BE_NO_PRICEID);
                }
                campaign = campaignProfileBO.addRenewCampaign(campaign, campaignCommonVO);
            }
        }
        else if (campaign.getCampaignSts()== Campaign.STS_RENEW)
        {
            campaign = campaignProfileBO.updateRenewCampaign(campaign, campaignCommonVO);
        }
        
        // action为true的时候表示新增，否则表示修改
        action = oldCampaignId!= campaign.getCampaignId();
        if (action)
        {
            oldCampaignTaskRelList = campaignTaskBO.getCampaignTaskRelList(oldCampaignId);
            oldCampaignTaskList = campaignTaskBO.getCampaignTaskList(oldCampaignId);
            for (CampaignTask campaignTask : oldCampaignTaskList)
            {
                oldTaskIds.add(campaignTask.getTaskId());
            }
            oldTaskParamInstanceList = campaignTaskBO.getTaskParamInstanceListByTaskIds(oldTaskIds);
            
            for (int i = 0; i< oldTaskParamInstanceList.size(); i++ )
            {
                TaskParamInstance taskParamInstance = oldTaskParamInstanceList.get(i);
                oldTaskParamInstanceIds.add(taskParamInstance.getParamInstanceId());
            }
            
            for (int i = 0; i< oldCampaignPriceRleList.size(); i++ )
            {
                CampaignPriceRel campaignPriceRle = oldCampaignPriceRleList.get(i);
                tempOldPriceIds[i] = campaignPriceRle.getPriceId();
            }
            oldPriceIds = Arrays.asList(tempOldPriceIds);
            oldCampaignPropertyList = campaignProfileBO.getCampaignPropertyListByCampaignId(oldCampaignId);
            
            for (int i = 0; i< oldCampaignPropertyList.size(); i++ )
            {
                Long propertyId = oldCampaignPropertyList.get(i).getPropertyId();
                oldCampaignPropertyIds.add(propertyId);
            }
            
            oldCampaignTaskRuleRelList = campaignTaskBO.getCampaignTaskRuleRelListByTaskIds(oldTaskIds);
            for (int i = 0; i< oldCampaignTaskRuleRelList.size(); i++ )
            {
                CampaignTaskRuleRel campaignTaskRuleRel = oldCampaignTaskRuleRelList.get(i);
                oldRuleIds.add(campaignTaskRuleRel.getRuleId());
            }
            oldCampaignTaskRuleList = campaignTaskBO.getCampaignTaskRuleListByRuleIds(oldRuleIds);
            for (int i = 0; i< oldCampaignTaskRuleList.size(); i++ )
            {
                CampaignTaskRule campaignTaskRule = oldCampaignTaskRuleList.get(i);
                oldActionIds.add(campaignTaskRule.getActionId());
            }
        }
        // 处理campaignPrice
        handleCampaignPrices(campaign, oldCampaignPriceRleList, oldPriceIds, uiPriceIds, campaignPrices, contact.getContractId(),
                false, !action);
        
        // 如果是新增，则需要拷贝老版本的campaignDetail(即campaignProperty)、
        // campaignTask、campaignTaskRel、taskParam(即TaskParamInstance)、
        // campaignTaskRuel、campaignTaskRuelRel的关系到更新的版本中去
        if (action)
        {
            copyProperties(campaign.getCampaignId(), oldCampaignTaskRelList, oldCampaignTaskList, oldTaskParamInstanceList,
                    oldCampaignPropertyList, oldCampaignTaskRuleRelList, oldCampaignTaskRuleList);
        }
        return campaign;
    }
    
    // 判断可用的campaign版本数量不能大于2
    private void throwAvailableVersionCampaignException(String campaignNo)
    {
        List<Campaign> activeCampaignVersionList = campaignProfileBO.getActiveVersionCampaignByCampaignNo(campaignNo);
        if (ValidateUtil.isEmpty(activeCampaignVersionList))
        {
            LOG.debug("campaign available version is not found by campaignNo,campaignNo is "+ campaignNo);
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_AVAILABLE_VERSION_IS_NOT_FOUND_BY_CAMPAIGNNO,
                    new Object[]{campaignNo });
        }
        else if (activeCampaignVersionList.size()> 2)
        {
            LOG.debug("campaign available version num can not gt two,campaignNo is "+ campaignNo);
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_AVAILABLE_VERSION_NUM_CAN_NOT_GT_TWO, new Object[]{campaignNo });
        }
    }
    
    private void throwRenewCampaignIdIsNull(Long campaignId)
    {
        if (ValidateUtil.isNull(campaignId))
        {
            LOG.debug("publish renew campaign  or modify renew campaign ,campaignId is null or empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY);
        }
    }
    
    private Contract throwCampaingContractIsValidOrNot(Long partnerId)
    {
        Contract contact = campaignRserviceBO.getContractByPartnerId(partnerId);
        if (ValidateUtil.isNull(contact)|| ValidateUtil.isNull(contact.getContractId()))
        {
            LOG.debug("valid contract is not found by partner id,partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.VALID_CONTRACT_IS_NOT_FOUND_BY_PARTNER_ID, new Object[]{partnerId });
        }
        return contact;
    }
    
    private void throwRenewCampaignException(CampaignCommonVO campaignCommonVO, Long oldCampaignId, Campaign campaign,
            Long validDate, Long expiredDate)
    {
        Long campaignEndDate = campaignCommonVO.getEndDate();
        Long campaignValidDate = campaignCommonVO.getValidDate();
        LOG.debug("campaignCommonVO endDate:"+ campaignEndDate);
        LOG.debug("campaignCommonVO validDate:"+ campaignValidDate);
        Long tomorrowTime = DateTimeUtil.getNextDayStartTime().getTime();
        LOG.debug("nextDay is :"+ tomorrowTime);
        Long partnerId = campaign.getPartnerId();
        // if (ValidateUtil.isNotNull(campaignValidDate))
        // {
        if (campaign.getCampaignBeginDate().getTime()< DateTimeUtil.getNow().getTime()
                && campaign.getCampaignSts()== Campaign.STS_ACTIVE)
        {
            // 表示该条记录已经生效
            if (campaignValidDate< campaign.getValidDate().getTime())
            {
                LOG.debug("the renew campaign validDate can not as early as the active campaign validDate,the partner id is "
                        + partnerId);
                throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_ACTIVE_CAMPAIGN_VALIDDATE,
                        new Object[]{partnerId });
            }
            
            if (campaignValidDate< campaign.getCampaignBeginDate().getTime())
            {
                LOG.debug("the renew campaign validDate can not as early as campaign campaignBeginDate,the partner id is "
                        + partnerId);
                throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_CAMPAIGN_CAMPAIGNBEGINDATE,
                        new Object[]{partnerId });
            }
            
            if (campaignEndDate< campaign.getValidDate().getTime())
            {
                LOG.debug("the renew campaign campaignEndDate can not as early as the active campaign validDate,the partner id is "
                        + partnerId);
                throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_ACTIVE_CAMPAIGN_VALIDDATE,
                        new Object[]{partnerId });
            }
        }
        else
        {
            // 表示该条记录没有生效【上一个版本的数据肯定已经生效】
            if (!"1".equals(campaign.getVersion()))
            {
                Campaign previousVersion = campaignProfileBO.getPreviousVersionCampaign(campaign.getCampaignNo(),
                        campaign.getVersion());
                if (campaignValidDate< previousVersion.getCampaignBeginDate().getTime())
                {
                    LOG.debug("the renew campaign validDate can not as early as campaign campaignBeginDate,the partner id is "
                            + partnerId);
                    throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_CAMPAIGN_CAMPAIGNBEGINDATE,
                            new Object[]{partnerId });
                }
                
                if (campaignEndDate< previousVersion.getValidDate().getTime())
                {
                    LOG.debug("the renew campaign campaignEndDate can not as early as the active campaign validDate,the partner id is "
                            + partnerId);
                    throw new MeoException(
                            CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_ACTIVE_CAMPAIGN_VALIDDATE,
                            new Object[]{partnerId });
                }
            }
            else
            {
                // 表示当前还是在第一个版本，已经激活但是还没有到生效日期
                // 第一个版本的validDate 必须要和startDate相等
                if (!campaignValidDate.equals(campaign.getCampaignBeginDate().getTime()))
                {
                    LOG.debug("the first version campaign validDate must be equal to the campaign startDate,the partner id is "
                            + partnerId);
                    throw new MeoException(CampaignErrorConstant.FIRST_VERSION_CAMPAIGN_VAILDDATE_NOT_EQUAL_STARTDATE,
                            new Object[]{partnerId });
                }
                // if (campaignValidDate> campaign.getCampaignBeginDate().getTime())
                // {
                // LOG.debug("the first version campaign validDate can not more than campaign startDate,the partner id is "
                // + partnerId);
                // throw new MeoException(CampaignErrorConstant.FIRST_VERSION_CAMPAIGN_VALIDDATE_CAN_NOT_GT_CAMPAIGN_STARTDATE,
                // new Object[]{partnerId });
                // }
                
                if (campaignEndDate< campaign.getValidDate().getTime())
                {
                    LOG.debug("the renew campaign campaignEndDate can not as early as the active campaign validDate,the partner id is "
                            + partnerId);
                    throw new MeoException(
                            CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_ACTIVE_CAMPAIGN_VALIDDATE,
                            new Object[]{partnerId });
                }
            }
        }
        
        if (campaignValidDate< tomorrowTime)
        {
            LOG.debug("the renew campaign validDate can not as early as tomorrow,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_TOMORROW, new Object[]{partnerId });
        }
        
        if (campaignEndDate< campaignValidDate)
        {
            LOG.debug("the renew campaign campaignEndDate can not as early as the renew campaign validDate,the partner id is "
                    + partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_RENEW_CAMPAIGN_VALIDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignValidDate< validDate)
        {
            LOG.debug("the renew campaign validDate can not as early as the contract validDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignValidDate> expiredDate)
        {
            LOG.debug("the renew campaign validDate can not more than the contract expiredDate,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE,
                    new Object[]{partnerId });
        }
        // }
        
        if (campaignEndDate< validDate)
        {
            LOG.debug("the renew campaign campaignEndDate can not as early as the contract validDate,the partner id is "
                    + partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignEndDate> expiredDate)
        {
            LOG.debug("the renew campaign campaignEndDate can not more than the contract expiredDate,the partner id is "
                    + partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE,
                    new Object[]{partnerId });
        }
        
        if (campaignEndDate< tomorrowTime)
        {
            LOG.debug("the renew campaign campaignEndDate can not as early as tomorrow,the partner id is "+ partnerId);
            throw new MeoException(CampaignErrorConstant.RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_TOMORROW,
                    new Object[]{partnerId });
        }
    }
    
    @Override
    public Campaign publishRenewcampaign(CampaignCommonVO campaignCommonVO)
    {
        throwCampaignParamCommonException(campaignCommonVO);
        throwPublishCampaignParamPriceException(campaignCommonVO);
        Long oldCampaignId = campaignCommonVO.getCampaignId();
        throwRenewCampaignIdIsNull(oldCampaignId);
        
        // campaign designer 修改已发布campaign信息后直接发布campaign ，也可以选择已经保存的renew 状态的campaign进行发布.
        // 提醒：前端需要判断当前状态为非draft状态才可以调用此接口
        // 前端传递过来的priceId
        List<Long> uiPriceIds = new ArrayList<Long>();
        List<CampaignPriceVO> campaignPrices = campaignCommonVO.getCampaignPrices();
        // 判断priceType的类型是否有重复
        throwCampaignPriceException(false, campaignPrices, uiPriceIds);
        // ////////////////////////////需要拷贝的信息//////////////////////////////////////////////
        // 数据库中已经存在的taskid
        List<Long> oldTaskIds = new ArrayList<Long>();
        // 查询出数据库已经存在的campaignTaskRel关系
        List<CampaignTaskRel> oldCampaignTaskRelList = new ArrayList<CampaignTaskRel>();
        // 查询到数据库已经存在的campaignTask
        List<CampaignTask> oldCampaignTaskList = new ArrayList<CampaignTask>();
        // 数据库中已经存在的taskParamInstanceId
        List<Long> oldTaskParamInstanceIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的taskParamInstance
        List<TaskParamInstance> oldTaskParamInstanceList = new ArrayList<TaskParamInstance>();
        // 查询到数据库中已经存在的campaignPriceRel
        List<CampaignPriceRel> oldCampaignPriceRleList = new ArrayList<CampaignPriceRel>();
        // 数据库中存在的campaignPropertyId
        List<Long> oldCampaignPropertyIds = new ArrayList<Long>();
        // 查询到数据库中已经存在的campaignProperty
        List<CampaignProperty> oldCampaignPropertyList = new ArrayList<CampaignProperty>();
        // 数据库中已经存在的CampaignTaskRuleRel
        List<CampaignTaskRuleRel> oldCampaignTaskRuleRelList = new ArrayList<CampaignTaskRuleRel>();
        // 数据库中已经存在CampaignTaskRule
        List<CampaignTaskRule> oldCampaignTaskRuleList = new ArrayList<CampaignTaskRule>();
        // 数据库中已经存在的ruleId
        List<Long> oldRuleIds = new ArrayList<Long>();
        // 数据库中存在的campaignTaskRule中的actingId
        List<Long> oldActionIds = new ArrayList<Long>();
        // /////////////////////////////////////////////////////////////////////////
        
        boolean action = false;
        
        Campaign campaign = campaignProfileBO.getCampaignByCampaignId(oldCampaignId);
        throwCampaignIsNullException(campaign, oldCampaignId);
        Contract contact = throwCampaingContractIsValidOrNot(campaign.getPartnerId());
        Long validDate = contact.getValidDate().getTime();
        Long expiredDate = contact.getExpiredDate().getTime();
        throwRenewCampaignException(campaignCommonVO, oldCampaignId, campaign, validDate, expiredDate);
        if (campaign.getIsEdit()!= Campaign.STS_ISEDIT)
        {
            LOG.debug("campaign sts is unedit,campaign id is "+ oldCampaignId);
            throw new MeoException(CampaignErrorConstant.CAMPAIGN_STS_IS_UNEDIT, new Object[]{oldCampaignId });
        }
        // 判断可用的campaign版本数量不能大于2
        throwAvailableVersionCampaignException(campaign.getCampaignNo());
        
        // 查询到数据库中已经存在价格Id
        List<Long> oldPriceIds = new ArrayList<Long>();
        oldCampaignPriceRleList = campaignProfileBO.getCampaignPriceRelsByCampaignId(oldCampaignId);
        Long[] tempOldPriceIds = new Long[oldCampaignPriceRleList.size()];
        for (int i = 0; i< oldCampaignPriceRleList.size(); i++ )
        {
            CampaignPriceRel campaignPriceRle = oldCampaignPriceRleList.get(i);
            tempOldPriceIds[i] = campaignPriceRle.getPriceId();
        }
        oldPriceIds = Arrays.asList(tempOldPriceIds);
        
        // campaign的状态为draft时不允许进行renew publish动作
        if (campaign.getCampaignSts()== Campaign.STS_RENEW)
        {
            campaign = campaignProfileBO.updatePublishRenewCampaign(campaign, campaignCommonVO);
        }
        else if (campaign.getCampaignSts()== Campaign.STS_ACTIVE)
        {
            if (("1".equals(campaign.getVersion())&& campaign.getCampaignBeginDate().getTime()> DateTimeUtil.getNow().getTime())
                    || (Integer.valueOf(campaign.getVersion())> 1&& campaign.getValidDate().getTime()> DateTimeUtil.getNow()
                            .getTime()))
            {
                campaignProfileBO.updateActiveCampaign(campaign, campaignCommonVO);
                campaign = campaignProfileBO.getCampaignByCampaignId(campaign.getCampaignId());
            }
            else
            {
                if (ValidateUtil.isNotEmpty(uiPriceIds))
                {
                    LOG.debug("save campaign,should be no priceId");
                    // 这里需要抛出异常，告知前端在save draft Campaign做save 操作的时候不应该传递priceId过来。
                    throw new MeoException(CampaignErrorConstant.SAVE_CAMAPIGN_SHOULD_BE_NO_PRICEID);
                }
                campaign = campaignProfileBO.addPublishRenewCampaign(campaign, campaignCommonVO);
            }
        }
        else
        {
            // 提示状态不对
            LOG.debug("the state of the campaign can not be published,campaign id is "+ oldCampaignId);
            throw new MeoException(CampaignErrorConstant.THE_CAMPAIGN_STS_CAN_NOT_BE_PUBLISHED, new Object[]{oldCampaignId });
        }
        
        // 过action为true表示新增，否则表示修改
        action = oldCampaignId!= campaign.getCampaignId();
        if (action)
        {
            oldCampaignTaskRelList = campaignTaskBO.getCampaignTaskRelList(oldCampaignId);
            oldCampaignTaskList = campaignTaskBO.getCampaignTaskList(oldCampaignId);
            for (CampaignTask campaignTask : oldCampaignTaskList)
            {
                oldTaskIds.add(campaignTask.getTaskId());
            }
            oldTaskParamInstanceList = campaignTaskBO.getTaskParamInstanceListByTaskIds(oldTaskIds);
            
            for (int i = 0; i< oldTaskParamInstanceList.size(); i++ )
            {
                TaskParamInstance taskParamInstance = oldTaskParamInstanceList.get(i);
                oldTaskParamInstanceIds.add(taskParamInstance.getParamInstanceId());
            }
            
            for (int i = 0; i< oldCampaignPriceRleList.size(); i++ )
            {
                CampaignPriceRel campaignPriceRle = oldCampaignPriceRleList.get(i);
                tempOldPriceIds[i] = campaignPriceRle.getPriceId();
            }
            oldPriceIds = Arrays.asList(tempOldPriceIds);
            oldCampaignPropertyList = campaignProfileBO.getCampaignPropertyListByCampaignId(oldCampaignId);
            
            for (int i = 0; i< oldCampaignPropertyList.size(); i++ )
            {
                Long propertyId = oldCampaignPropertyList.get(i).getPropertyId();
                oldCampaignPropertyIds.add(propertyId);
            }
            oldCampaignTaskRuleRelList = campaignTaskBO.getCampaignTaskRuleRelListByTaskIds(oldTaskIds);
            for (int i = 0; i< oldCampaignTaskRuleRelList.size(); i++ )
            {
                CampaignTaskRuleRel campaignTaskRuleRel = oldCampaignTaskRuleRelList.get(i);
                oldRuleIds.add(campaignTaskRuleRel.getRuleId());
            }
            oldCampaignTaskRuleList = campaignTaskBO.getCampaignTaskRuleListByRuleIds(oldRuleIds);
            for (int i = 0; i< oldCampaignTaskRuleList.size(); i++ )
            {
                CampaignTaskRule campaignTaskRule = oldCampaignTaskRuleList.get(i);
                oldActionIds.add(campaignTaskRule.getActionId());
            }
        }
        // 处理campaignPrice
        handleCampaignPrices(campaign, oldCampaignPriceRleList, oldPriceIds, uiPriceIds, campaignPrices, contact.getContractId(),
                true, !action);
        // 如果是新增，则需要拷贝老版本的campaignDetail(即campaignProperty)、
        // campaignTask、campaignTaskRel、taskParam(即TaskParamInstance)、
        // campaignTaskRuel、campaignTaskRuelRel的关系到更新的版本中去
        if (action)
        {
            copyProperties(campaign.getCampaignId(), oldCampaignTaskRelList, oldCampaignTaskList, oldTaskParamInstanceList,
                    oldCampaignPropertyList, oldCampaignTaskRuleRelList, oldCampaignTaskRuleList);
        }
        return campaign;
    }
    
    @Override
    public List<CampaignTask> getRewardCampaignTask(Long actionId, Long campaignId)
    {
        return campaignTaskBO.getRewardTask(actionId, campaignId);
    }
    
    @Override
    public List<CampaignPriceVO> getCampaignPricesByCampaignId(Long campaignId)
    {
        return campaignProfileBO.getCampaignPricesByCampaignId(campaignId);
    }
    
    @Override
    public CampaignAllBasicVO getCampaignDetailInfoById(Long campaignId)
    {
        CampaignAllBasicVO campaignAllBasicVO = new CampaignAllBasicVO();
        Campaign campaign = campaignProfileBO.getCampaignByCampaignId(campaignId);
        if (null != campaign)
        {
            BeanUtils.copyProperties(campaign, campaignAllBasicVO);
            campaignAllBasicVO.setParticipant(campaign.getParticipant());
            int remainParticipant = campaignProfileBO.countRemainParticipantByCampaignNo(campaign.getCampaignNo(), campaign.getParticipant());
            campaignAllBasicVO.setRemainParticipant(remainParticipant);
            campaignAllBasicVO.setCampaginBeginDate(campaign.getCampaignBeginDate()!= null ? campaign.getCampaignBeginDate()
                    .getTime() : null);
            campaignAllBasicVO.setCampaginEndDate(campaign.getCampaignEndDate()!= null ? campaign.getCampaignEndDate().getTime()
                    : null);
            
            EnumDefine campaignType = campaignRserviceBO.getEnumDefByEnumCode(String.valueOf(campaign.getCampaignType()),
                    Integer.valueOf(EnumDefine.CAMPAGIN_TYPE));
            campaignAllBasicVO.setCampaginTypeId(campaign.getCampaignType());
            campaignAllBasicVO.setCampaginType(campaignType!= null ? campaignType.getEnumName() : null);
        
            PartnerInfo partner = campaignRserviceBO.getPartnerInfo(campaign.getPartnerId());
            // company is the partner ,so the companyName is the partnerName
            if (partner!= null&& ValidateUtil.isNotEmpty(partner.getCompanyName()))
            {
                campaignAllBasicVO.setPartnerName(partner.getCompanyName());
            }
            PartnerFileInfo partnerFileInfo = campaignRserviceBO.getPartnerFileInfo(campaign.getPartnerId(),
                    PartnerFileInfo.FILE_TYPE_AVATAR);
            if (partnerFileInfo!= null&& ValidateUtil.isNotEmpty(partnerFileInfo.getFileUrl()))
            {
                campaignAllBasicVO.setPartnerAvatar(partnerFileInfo.getFileUrl());
            }
            
            if (null!= campaign.getCategoryId())
            {
                List<CategoryDefine> campaignTypeList = campaignRserviceBO.getCategoryEnum(campaign.getCategoryId().longValue(),
                        CategoryDefine.CAMPAGIN_TYPE);
                for (CategoryDefine category : campaignTypeList)
                {
                    campaignAllBasicVO.setCategoryId(campaign.getCategoryId());
                    campaignAllBasicVO.setCategory(category!= null ? category.getCategoryName() : null);
                }
            }
        }
        
        // 获取该用户参加该campaign的步骤
        List<CampaignTaskDetail> campaignTaskDetailList = campaignTaskBO.getCampaignTaskDetailByCustIdAndCampaignNo(BoContext
                .getBoContext().getUserId(), campaign.getCampaignNo());
        if (ValidateUtil.isNotEmpty(campaignTaskDetailList))
        {
            for (CampaignTaskDetail campaignTaskDetail : campaignTaskDetailList)
            {
                if (campaignTaskDetail.getSts().equals(CampaignTaskDetail.STS_VALID))
                {
                    campaignAllBasicVO.setCurrentTaskId(campaignTaskDetail.getCurrentTaskId());
                    campaignAllBasicVO.setNextTaskId(campaignTaskDetail.getNextTaskId());
                }
            }
        }
        
        // 参加并且完成 该campaign后，最多可以获得的cion数量
        Integer totalCoins = getCampaignTotalCoins(campaignId);
        campaignAllBasicVO.setTotalCoins(totalCoins);
        
        return campaignAllBasicVO;
    }
    
    @Override
    public String getNextVersion(String campaignNo)
    {
        return campaignProfileBO.getNextVersion(campaignNo);
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRuleByActionId(Long actionId)
    {
        return campaignTaskBO.getCampaignTaskRuleByActionId(actionId);
    }
    
    @Override
    public TaskParamInstance getTaskParamInstanceByTaskIdAndParamId(Long taskId, Long paramId)
    {
        if (ValidateUtil.isNull(taskId))
        {
            LOG.debug("query TaskParamInstance by taskId and paramId,taskId can not null");
            throw new MeoException(CampaignErrorConstant.QUERY_TASKPARAMINSTANCE_BY_TASKID_PARAMID_PARAM_CAN_NOT_NULL,
                    new Object[]{CampaignConstantDefine.TASK_ID });
        }
        if (ValidateUtil.isNull(paramId))
        {
            LOG.debug("query TaskParamInstance by taskId and paramId,paramId can not null");
            throw new MeoException(CampaignErrorConstant.QUERY_TASKPARAMINSTANCE_BY_TASKID_PARAMID_PARAM_CAN_NOT_NULL,
                    new Object[]{CampaignConstantDefine.PARAM_ID });
        }
        return campaignTaskBO.getTaskParamInstanceByTaskIdAndParamId(taskId, paramId);
    }
    
    @Override
    public List<TaskParamInstance> getTaskParamInstanceByTaskId(Long taskId)
    {
        if (ValidateUtil.isNull(taskId))
        {
            LOG.debug("query TaskParamInstance list by taskId ,taskId can not null");
            throw new MeoException(CampaignErrorConstant.QUERY_TASKPARAMINSTANCE_LIST_BY_TASKID_TASKID_CAN_NOT_NULL);
        }
        return campaignTaskBO.getTaskParamInstanceByTaskId(taskId);
    }
    
    @Override
    public HotCampaignRankVO get(Long campaignId)
    {
        return campaignProfileBO.get(campaignId);
    }
    
    @Override
    public void saveHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        campaignProfileBO.saveHotCampaignRank(hotCampaignRank);
    }
    
    @Override
    public void updateHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        campaignProfileBO.updateHotCampaignRank(hotCampaignRank);
    }
    
    @Override
    public PageInfo<CampaignPropertyDefVO> getCampaignPropertyDefByCampaignType(CampaignPropertyDefQueryConditionVO conditionVO)
    {
        return campaignProfileBO.getCampaignPropertyDefByCampaignType(conditionVO);
    }
    
    @Override
    public void addCampaignPropertyDef(List<CampaignPropertyDefineVO> campaignPropertyDefineVOList)
    {
        campaignProfileBO.addCampaignPropertyDef(campaignPropertyDefineVOList);
    }
    
    @Override
    public List<CampaignVO> getCampaignVOList(String groupCode, List<Integer> categoryIds)
    {
        if (ValidateUtil.isEmpty(groupCode))
        {
            LOG.debug("query campaign by groupCode and categoryIds ，groupCode is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.GROUPCODE });
        }
        /**
         * 24/07/2015 : Nick : categoryIds is not mandatory for this function
         */
        /*
         * if (ValidateUtil.isEmpty(categoryIds)) { LOG.debug("query campaign by groupCode and categoryIds ，categoryIds is null");
         * throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object[]{SysConstantDefine.CATEGORYIDS }); }
         */
        List<Integer> campaignTypes = getSysEnumCodeByGroupcode(groupCode);
        return campaignProfileBO.getCampaignListByCampaignTypesAndCategoryIds(campaignTypes, categoryIds);
    }
    
    @Override
    public Long getProductIdByCampaignId(Long campaignId)
    {
        if (ValidateUtil.isEmpty(campaignId))
        {
            LOG.debug("query product id by campaign id ，campaign id is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{"campaignId" });
        }
        return campaignTaskBO.getProductIdByCampaignId(campaignId);
    }
    
    @Override
    public Integer getCampaignTotalCoins(Long campaignId)
    {
        return campaignProfileBO.getCampaignTotalCoins(campaignId);
    }
    
    @Override
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        return campaignTaskBO.addCampaignTaskDetail(campaignTaskDetail);
    }
    
    @Override
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        return campaignTaskBO.updateCampaignTaskDetail(campaignTaskDetail);
    }
    
    @Override
    public void deleteCampaignTaskDetailByDetailId(Long detailId)
    {
        campaignTaskBO.deleteCampaignTaskDetailByDetailId(detailId);
        return;
    }
    
    @Override
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId)
    {
        return campaignTaskBO.getCampaignTaskDetailByDetailId(detailId);
    }
    
    @Override
    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId, String campaignNo)
    {
        if (custId== null)
        {
            LOG.debug("the method getCampaignTaskDetailByCustIdAndCampaignNo,input parameter custId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{CampaignConstantDefine.CUSTID });
        }
        if (ValidateUtil.isEmpty(campaignNo))
        {
            LOG.debug("the method getCampaignTaskDetailByCustIdAndCampaignNo,input parameter campaignNo is isEmpty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_NO });
        }
        return campaignTaskBO.getCampaignTaskDetailByCustIdAndCampaignNo(custId, campaignNo);
    }
    
    @Override
    public List<CampaignTaskRel> getCampaignTaskRelBySrcTaskIdAndCampaignId(Long srcTaskid, Long campaignId, int relType)
    {
        if (srcTaskid== null)
        {
            LOG.debug("the method getCampaignTaskRelBySrcTaskIdAndCampaignId,input parameter srcTaskid was null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.SRC_TASKID });
        }
        if (campaignId== null)
        {
            LOG.debug("the method getCampaignTaskRelBySrcTaskIdAndCampaignId,input parameter campaignId was null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_ID });
        }
        if (relType!= CampaignTaskRel.REL_TYPE_PARENT&& relType!= CampaignTaskRel.REL_TYPE_TRIGGER)
        {
            LOG.debug("the method getCampaignTaskRelBySrcTaskIdAndCampaignId,input parameter relType type was error");
            throw new MeoException(CommonErrorConstant.WRONG_PARAMETER, new Object[]{CampaignConstantDefine.RELTYPE });
        }
        return campaignTaskBO.getCampaignTaskRelBySrcTaskIdAndCampaignId(srcTaskid, campaignId, relType);
    }
    
    @Override
    public CampaignTask getCampaignTaskByActionIdAndCampaignId(Long actionId, Long campaignId)
    {
        if (actionId== null)
        {
            LOG.debug("the method getCampaignTaskByActionIdAndCampaignId,input parameter actionId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.ACTION_ID });
        }
        if (campaignId== null)
        {
            LOG.debug("the method getCampaignTaskByActionIdAndCampaignId,input parameter campaignId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_ID });
        }
        List<CampaignTask> list = campaignTaskBO.getCampaignTaskByActionIdAndCampaignId(actionId, campaignId);
        return ValidateUtil.isNotEmpty(list) ? list.get(0) : null;
    }
    
    @Override
    public void updateCampaignTaskDetailByDetailIdNotInDetailIds(Long custId, String campaignNo, List<Long> detailIds)
    {
        if (ValidateUtil.isEmpty(detailIds))
        {
            LOG.debug("the method updateCampaignTaskDetailByDetailIdNotInDetailIds,input parameter detailIds is empty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.DETAILIDS });
        }
        if (ValidateUtil.isEmpty(campaignNo))
        {
            LOG.debug("the method updateCampaignTaskDetailByDetailIdNotInDetailIds,input parameter campaignNo is isEmpty");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_NO });
        }
        if (custId== null)
        {
            LOG.debug("the method updateCampaignTaskDetailByDetailIdNotInDetailIds,input parameter custId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{CampaignConstantDefine.CUSTID });
        }
        campaignTaskBO.updateCampaignTaskDetailByDetailIdNotInDetailIds(custId, campaignNo, detailIds);
    }
    
    @Override
    public CampaignTaskRule getCampaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType)
    {
        return campaignTaskBO.getCampaignTaskRuleByActionIdAndRuleType(actionId, ruleType);
    }
    
    @Override
    public void modifyCampaignPropertyDef(CampaignPropertyDefVO campaignPropertyDefVO)
    {
        campaignProfileBO.modifyCampaignPropertyDef(campaignPropertyDefVO);
    }
    
    @Override
    public List<Campaign> getCampaignList(Long currentTime)
    {
        return campaignProfileBO.getCampaignList(currentTime);
    }
    
    @Override
    public Campaign updateCampaign(Campaign campaign)
    {
        return campaignProfileBO.updateCampaign(campaign);
    }
    
    @Override
    public PageInfo<String> getAppDownLoad(Long custId, Integer pageNo, Integer pageSize)
    {
        return campaignProfileBO.getAppDownLoad(custId, pageNo, pageSize);
    }
    
    @Override
    public PageInfo<NewFeedCampaignVO> getNewsFeedCampaignPageInfo(Integer pageNo, Integer pageSize)
    {
        return campaignProfileBO.getNewsFeedCampaignPageInfo(pageNo, pageSize);
    }
    
}
