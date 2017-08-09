package com.asiainfo.meo.web.component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.activity.app.model.vo.CmActivityRecordHisVO;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
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
import com.asiainfo.meo.campaign.profile.app.model.vo.NewFeedCampaignVO;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
import com.asiainfo.meo.campaign.task.app.model.vo.TaskParamVO;
import com.asiainfo.meo.campaign.task.app.model.vo.TriggerTaskVO;
import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.service.provide.ActivityPserviceBO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignActivityHistoryVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignAllBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignBasicInfoVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignCategoryVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignCommonVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignDetailVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignGroupVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPriceVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPropertyDefVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPropertyDefineVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignSimpleBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignTaskVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignVO;
import com.asiainfo.meo.web.campaign.model.vo.UINewFeedCampaignVO;
import com.asiainfo.meo.web.campaign.model.vo.UIReNewCampaignCommonVO;
import com.asiainfo.meo.web.campaign.model.vo.UITaskParamVO;
import com.asiainfo.meo.web.campaign.model.vo.UITriggerTaskVO;

public class CampaignComponent
{
    private static final Log LOG = LogFactory.getLog(CampaignComponent.class);
    
    @Resource
    ActivityPserviceBO       activityPserviceBO;
    
    /**
     * @Description: 将CampaignVO中的属性拷贝到UICampaignVO中
     * @Description: copy CampaignVO property to UICampaignVO
     * @modifyReason:
     * @author zhengzy
     * @param campaignVolist
     * @return
     */
    public List<UICampaignVO> transformUICampaignVOList(List<CampaignVO> campaignVolist)
    {
        List<UICampaignVO> uiCampaignVOList = new ArrayList<UICampaignVO>();
        if (ValidateUtil.isEmpty(campaignVolist))
        {
            return uiCampaignVOList;
        }
        for (CampaignVO campaignVo : campaignVolist)
        {
            UICampaignVO uiCampaignVO = new UICampaignVO();
            BeanUtils.copyProperties(campaignVo, uiCampaignVO, new String[]{});
            uiCampaignVOList.add(uiCampaignVO);
        }
        return uiCampaignVOList;
    }
    
    public PageInfo<UICampaignVO> transformUICampaignVOList(PageInfo<CampaignVO> campaignVOPageInfo)
    {
        PageInfo<UICampaignVO> uiCampaignPageInfo = new PageInfo<UICampaignVO>();
        if (campaignVOPageInfo == null || ValidateUtil.isEmpty(campaignVOPageInfo.getResult()))
        {
            return uiCampaignPageInfo.emptyPageInfo();
        }
        List<UICampaignVO> uiCampaignVOList = new ArrayList<UICampaignVO>();
        List<CampaignVO> campaignVOList = campaignVOPageInfo.getResult();
        for (CampaignVO campaignVo : campaignVOList)
        {
            UICampaignVO uiCampaignVO = new UICampaignVO();
            BeanUtils.copyProperties(campaignVo, uiCampaignVO);
            uiCampaignVOList.add(uiCampaignVO);
        }
        BeanUtils.copyProperties(campaignVOPageInfo, uiCampaignPageInfo);
        uiCampaignPageInfo.setResult(uiCampaignVOList);
        return uiCampaignPageInfo;
    }
    
    /**
     * @Description: 将CampaignActivityHistoryVO中的属性拷贝到UICampaignActivityHistoryVO中
     * @Description: copy CampaignActivityHistoryVO property to UICampaignActivityHistoryVO
     * @modifyReason:
     * @author zhengzy
     * @param campaignActivityHistoryVOPageInfo
     * @return
     */
    public PageInfo<UICampaignActivityHistoryVO> transformUICampaignActivityHistoryVOList(
            PageInfo<CampaignActivityHistoryVO> campaignActivityHistoryVOPageInfo)
    {
        PageInfo<UICampaignActivityHistoryVO> page = new PageInfo<UICampaignActivityHistoryVO>();
        if (campaignActivityHistoryVOPageInfo == null
                || ValidateUtil.isEmpty(campaignActivityHistoryVOPageInfo.getResult()))
        {
            return page.emptyPageInfo();
        }
        List<UICampaignActivityHistoryVO> uiCampaignActivityHistoryVOList = new ArrayList<UICampaignActivityHistoryVO>();
        for (CampaignActivityHistoryVO campaignActivityHistoryVO : campaignActivityHistoryVOPageInfo.getResult())
        {
            UICampaignActivityHistoryVO uiCampaignActivityHistoryVO = new UICampaignActivityHistoryVO();
            BeanUtils.copyProperties(campaignActivityHistoryVO, uiCampaignActivityHistoryVO, new String[]{});
            CmActivityRecordHisVO cmActivityRecordHisVO = activityPserviceBO.getCmActivityRecordHis(campaignActivityHistoryVO
                    .getActivityId());
            uiCampaignActivityHistoryVO.setContent(cmActivityRecordHisVO!= null ? cmActivityRecordHisVO.getContent() : null);
            uiCampaignActivityHistoryVOList.add(uiCampaignActivityHistoryVO);
        }
        BeanUtils.copyProperties(campaignActivityHistoryVOPageInfo, page);
        page.setResult(uiCampaignActivityHistoryVOList);
        return page;
    }
    
    public CampaignBasicVO transformUICampaignVOToCampaignBasicVO(UICampaignBasicVO capaignCondition)
    {
        CampaignBasicVO campaignBasicVO = new CampaignBasicVO();
        if (capaignCondition==null)
        {
            return campaignBasicVO;
        }
        campaignBasicVO.setPartnerId(capaignCondition.getPartnerId());
        // campaignBasicVO.setCampaignId(capaignCondition.getCampaignId());
        campaignBasicVO.setCampaignType(capaignCondition.getCampaignType());
        campaignBasicVO.setCampaignNo(capaignCondition.getCampaignNo());
        campaignBasicVO.setCampaignName(capaignCondition.getCampaignName());
        campaignBasicVO.setValidDate(capaignCondition.getValidDate()!= null ? new Timestamp(capaignCondition.getValidDate())
        : null);
        campaignBasicVO.setExpiredDate(capaignCondition.getExpiredDate()!= null ? new Timestamp(capaignCondition
                .getExpiredDate()) : null);
        campaignBasicVO.setCampaignStsId(capaignCondition.getCampaignStsId());
        return campaignBasicVO;
    }
    
    public PageInfo<UICampaignBasicInfoVO> transformCampaignBasicVOPageInfoToUICampaignVOPageInfo(
            PageInfo<CampaignBasicVO> campaignBasicVOPageInfo)
    {
        PageInfo<UICampaignBasicInfoVO> pageInfo = new PageInfo<UICampaignBasicInfoVO>();
        if (campaignBasicVOPageInfo == null || ValidateUtil.isEmpty(campaignBasicVOPageInfo.getResult()))
        {
            return pageInfo.emptyPageInfo();
        }
        BeanUtils.copyProperties(campaignBasicVOPageInfo, pageInfo);
        List<UICampaignBasicInfoVO> campaignVOList = new ArrayList<UICampaignBasicInfoVO>();
        List<CampaignBasicVO> campaignBasicVOlist = campaignBasicVOPageInfo.getResult();
        
        Set<String> keyset = new TreeSet<String>();
        
        for (CampaignBasicVO campaignBasicVO : campaignBasicVOlist)
        {
            keyset.add(campaignBasicVO.getCampaignNo());
        }
        final List<String> list = new ArrayList<String>();
        for (final String value : keyset)
        {
            list.add(value);
        }
        Collections.sort(list);
        for (String key : list)
        {
            UICampaignBasicInfoVO uiCampaignBasicInfoVO = new UICampaignBasicInfoVO();
            List<UICampaignBasicVO> campaignBasicVOList = new ArrayList<UICampaignBasicVO>();
            for (CampaignBasicVO campaignBasicVO : campaignBasicVOlist)
            {
                if (key.equals(campaignBasicVO.getCampaignNo()))
                {
                    UICampaignBasicVO campaignVO = copyCampaignBasicVoToUICampaignBasicVo(campaignBasicVO);
                    campaignBasicVOList.add(campaignVO);
                }
            }
            uiCampaignBasicInfoVO.setCampaignNo(key);
            uiCampaignBasicInfoVO.setCampaignBasicInfo(campaignBasicVOList);
            campaignVOList.add(uiCampaignBasicInfoVO);
        }
        pageInfo.setResult(campaignVOList);
        return pageInfo;
    }

    private UICampaignBasicVO copyCampaignBasicVoToUICampaignBasicVo(CampaignBasicVO campaignBasicVO)
    {
        UICampaignBasicVO campaignVO = new UICampaignBasicVO();
        campaignVO.setCampaignId(campaignBasicVO.getCampaignId());
        campaignVO.setCampaignName(campaignBasicVO.getCampaignName());
        campaignVO.setPartnerName(campaignBasicVO.getPartnerName());
        if(campaignBasicVO.getValidDate()!=null)
        {
            campaignVO.setValidDate(campaignBasicVO.getValidDate().getTime());
        }
        if(campaignBasicVO.getExpiredDate()!=null)
        {
            campaignVO.setExpiredDate(campaignBasicVO.getExpiredDate().getTime());
        }
        campaignVO.setCampaignId(campaignBasicVO.getCampaignId());
        campaignVO.setCampaignStsId(campaignBasicVO.getSts());
        campaignVO.setCampaignSts(campaignBasicVO.getCampaignSts());
        return campaignVO;
    }
    
    public UICampaignSimpleBasicVO transformCampaignBasicVOToUICampaignVO(CampaignBasicVO campaignBasicVO)
    {
        UICampaignSimpleBasicVO campaignVO = new UICampaignSimpleBasicVO();
        if (campaignBasicVO== null)
        {
            return campaignVO;
        }
        BeanUtils.copyProperties(campaignBasicVO, campaignVO);
        if(campaignBasicVO.getCampaignBeginDate()!= null)
        {
            campaignVO.setStartDate(campaignBasicVO.getCampaignBeginDate().getTime());
        }
        if(campaignBasicVO.getCampaignEndDate()!= null)
        {
            campaignVO.setEndDate(campaignBasicVO.getCampaignEndDate().getTime());
        }
        return campaignVO;
    }
    
    /**
     * @Description: 将CampaignDetailVO中的属性拷贝到UICampaignDetailVO中
     * @Description: copy CampaignDetailVO property to UICampaignDetailVO
     * @param campaignDetailList
     * @author zhengzy
     * @return
     */
    public List<UICampaignDetailVO> transformCampaignDetailVOToUICampaignDetailVO(List<CampaignDetailVO> campaignDetailList,boolean ignore)
    {
        LOG.debug("campaignDetailVO mapping UIcampaignDetailVO begin!");
        List<UICampaignDetailVO> uICampaignDetailList = new ArrayList<UICampaignDetailVO>();
        if (ValidateUtil.isEmpty(campaignDetailList))
        {
            return uICampaignDetailList;
        }
        for (CampaignDetailVO campaignDetail : campaignDetailList)
        {
            UICampaignDetailVO uICampaignDetail = new UICampaignDetailVO();
            String ignoreProperties [] = new String []{};
            if(ignore)
            {
                ignoreProperties = new String[]{"propertyValueType","propertyName","mandatory"};
            }
            BeanUtils.copyProperties(campaignDetail, uICampaignDetail, ignoreProperties);// copy
            uICampaignDetailList.add(uICampaignDetail);
        }
        LOG.debug("campaignDetailVO mapping UIcampaignDetailVO end!");
        return uICampaignDetailList;
    }
    
    public List<UICampaignTaskVO> transformCampaignTaskVOToUICampaignTaskVO(String campaignNo,
            List<CampaignTaskVO> campaignTaskList)
    {
        LOG.debug("campaignTaskVO mapping UIcampaignTaskVO begin!");
        Long custId = BoContext.getBoContext().getUserId();
        List<UICampaignTaskVO> uIcampaignTaskList = new ArrayList<UICampaignTaskVO>();
        if (ValidateUtil.isEmpty(campaignTaskList))
        {
            return uIcampaignTaskList;
        }
        
        for (CampaignTaskVO campaignTaskVO : campaignTaskList)
        {
            UICampaignTaskVO uICampaignTaskVO = new UICampaignTaskVO();
            Long actionId = campaignTaskVO.getActionId();
            if (actionId!= null)
            {
                uICampaignTaskVO.setActionId(actionId);
                if (ValidateUtil.isNotEmpty(campaignNo))
                {
                    CmCampaignActivity activity = activityPserviceBO.getCmCampaignActivityByCodition(campaignNo, custId,
                            actionId.toString());
                    if (activity!= null)
                    {
                        uICampaignTaskVO.setJoinStatus(UICampaignTaskVO.JOINED_STS);
                    }
                    else
                    {
                        uICampaignTaskVO.setJoinStatus(UICampaignTaskVO.NOT_JOINED_STS);
                    }
                }
            }
            
            if (ValidateUtil.isNotEmpty(campaignTaskVO.getActionName()))
            {
                uICampaignTaskVO.setActionName(campaignTaskVO.getActionName());
            }
            
            if (campaignTaskVO.getParentActionId()!= null)
            {
                uICampaignTaskVO.setParentActionId(campaignTaskVO.getParentActionId());
            }
            
            if (campaignTaskVO.getParentTaskId()!= null)
            {
                uICampaignTaskVO.setParentTaskId(campaignTaskVO.getParentTaskId());
            }
            
            if (campaignTaskVO.getTaskId()!= null)
            {
                uICampaignTaskVO.setTaskId(campaignTaskVO.getTaskId());
            }
            
            if (ValidateUtil.isNotEmpty(campaignTaskVO.getTaskParamList()))
            {
                List<UITaskParamVO> uITaskParamList = new ArrayList<UITaskParamVO>();
                for (TaskParamVO taskParamVO : campaignTaskVO.getTaskParamList())
                {
                    UITaskParamVO uITaskParamVO = new UITaskParamVO();
                    BeanUtils.copyProperties(taskParamVO, uITaskParamVO);
                    uITaskParamList.add(uITaskParamVO);
                }
                uICampaignTaskVO.setTaskParamList(uITaskParamList);
            }
            
            if (ValidateUtil.isNotEmpty(campaignTaskVO.getTriggerTaskList()))
            {
                List<UITriggerTaskVO> uITriggerTaskList = new ArrayList<UITriggerTaskVO>();
                
                for (TriggerTaskVO triggerTaskVO : campaignTaskVO.getTriggerTaskList())
                {
                    UITriggerTaskVO uITriggerTaskVO = new UITriggerTaskVO();
                    BeanUtils.copyProperties(triggerTaskVO, uITriggerTaskVO);
                    uITriggerTaskList.add(uITriggerTaskVO);
                }
                uICampaignTaskVO.setTriggerTaskList(uITriggerTaskList);
            }
            uIcampaignTaskList.add(uICampaignTaskVO);
        }
        LOG.debug("campaignTaskVO mapping UIcampaignTaskVO end!");
        return uIcampaignTaskList;
    }
    
    /**
     * @Description: 将UIReNewCampaignCommonVO中的属性拷贝到CampaignCommonVO中
     * @Description: copy UIReNewCampaignCommonVO property to CampaignCommonVO
     * @author zhengzy
     * @param uICampaignCommonVO
     * @return
     */
    public CampaignCommonVO transformUIReNewCampaignCommonVO(UIReNewCampaignCommonVO uIReNewCampaignCommonVO)
    {
        if (uIReNewCampaignCommonVO== null)
        {
            return null;
        }
        CampaignCommonVO campaignCommonVO = new CampaignCommonVO();
        BeanUtils.copyProperties(uIReNewCampaignCommonVO, campaignCommonVO);
        List<CampaignPriceVO> campaignPriceVOlist = new ArrayList<CampaignPriceVO>();
        List<CampaignPriceVO> tempCampaignPriceVOlist = campaignCommonVO.getCampaignPrices();
        List<UICampaignPriceVO> uiCampaignPriceVOlist = uIReNewCampaignCommonVO.getCampaignPrices();
        for (UICampaignPriceVO uiCampaignPriceVO : uiCampaignPriceVOlist)
        {
            CampaignPriceVO campaignPriceVO = new CampaignPriceVO();
            BeanUtils.copyProperties(uiCampaignPriceVO, campaignPriceVO);
            campaignPriceVOlist.add(campaignPriceVO);
        }
        tempCampaignPriceVOlist.removeAll(tempCampaignPriceVOlist);
        campaignCommonVO.setCampaignPrices(campaignPriceVOlist);
        return campaignCommonVO;
    }
    
    /**
     * @Description: 将UICampaignCommonVO中的属性拷贝到CampaignCommonVO中
     * @Description: copy UICampaignCommonVO property to CampaignCommonVO
     * @author zhengzy
     * @param uICampaignCommonVO
     * @return
     */
    public CampaignCommonVO transformUICampaignCommonVOToCampaignCommonVO(UICampaignCommonVO uICampaignCommonVO)
    {
        if (uICampaignCommonVO== null)
        {
            return null;
        }
        CampaignCommonVO campaignCommonVO = new CampaignCommonVO();
        BeanUtils.copyProperties(uICampaignCommonVO, campaignCommonVO);
        
        List<CampaignPriceVO> campaignPriceVOlist = new ArrayList<CampaignPriceVO>();
        List<CampaignPriceVO> tempCampaignPriceVOlist = campaignCommonVO.getCampaignPrices();
        List<UICampaignPriceVO> uiCampaignPriceVOlist = uICampaignCommonVO.getCampaignPrices();
        
        for (UICampaignPriceVO uiCampaignPriceVO : uiCampaignPriceVOlist)
        {
            CampaignPriceVO campaignPriceVO = new CampaignPriceVO();
            BeanUtils.copyProperties(uiCampaignPriceVO, campaignPriceVO);
            campaignPriceVOlist.add(campaignPriceVO);
        }
        tempCampaignPriceVOlist.removeAll(tempCampaignPriceVOlist);
        campaignCommonVO.setCampaignPrices(campaignPriceVOlist);
        
        return campaignCommonVO;
    }
    
    public List<CampaignDetailVO> transformUICampaignDetailVOToCampaignDetailVO(List<UICampaignDetailVO> uICampaignDetailVOs)
    {
        if (ValidateUtil.isEmpty(uICampaignDetailVOs))
        {
            return null;
        }
        List<CampaignDetailVO> campaignDetailVOList = new ArrayList<CampaignDetailVO>();
        for (UICampaignDetailVO uICampaignDetailVO : uICampaignDetailVOs)
        {
            CampaignDetailVO campaignDetailVO = new CampaignDetailVO();
            BeanUtils.copyProperties(uICampaignDetailVO, campaignDetailVO);
            campaignDetailVOList.add(campaignDetailVO);
        }
        return campaignDetailVOList;
    }
    
    public List<CampaignTaskVO> transformUICampaignTaskVOToCampaignTaskVO(List<UICampaignTaskVO> uICampaignTaskVOs)
    {
        if (ValidateUtil.isEmpty(uICampaignTaskVOs))
        {
            return null;
        }
        List<CampaignTaskVO> campaignTaskVOList = new ArrayList<CampaignTaskVO>();
        for (UICampaignTaskVO uICampaignTaskVO : uICampaignTaskVOs)
        {
            CampaignTaskVO campaignTaskVO = new CampaignTaskVO();
            
            if (uICampaignTaskVO.getActionId()!= null)
            {
                campaignTaskVO.setActionId(uICampaignTaskVO.getActionId());
            }
            
            if (uICampaignTaskVO.getParentActionId()!= null)
            {
                campaignTaskVO.setParentActionId(uICampaignTaskVO.getParentActionId());
            }
            
            if (uICampaignTaskVO.getParentTaskId()!= null)
            {
                campaignTaskVO.setParentTaskId(uICampaignTaskVO.getParentTaskId());
            }
            if (uICampaignTaskVO.getTaskId()!= null)
            {
                campaignTaskVO.setTaskId(uICampaignTaskVO.getTaskId());
            }
            
            if (ValidateUtil.isNotEmpty(uICampaignTaskVO.getTaskParamList()))
            {
                List<TaskParamVO> taskParmVOList = new ArrayList<TaskParamVO>();
                for (UITaskParamVO uITaskParamVO : uICampaignTaskVO.getTaskParamList())
                {
                    TaskParamVO taskParamVO = new TaskParamVO();
                    BeanUtils.copyProperties(uITaskParamVO, taskParamVO, new String[]{});
                    taskParmVOList.add(taskParamVO);
                }
                campaignTaskVO.setTaskParamList(taskParmVOList);
            }
            
            if (ValidateUtil.isNotEmpty(uICampaignTaskVO.getTriggerTaskList()))
            {
                List<TriggerTaskVO> triggerTaskVOList = new ArrayList<TriggerTaskVO>();
                for (UITriggerTaskVO uITriggerTaskVO : uICampaignTaskVO.getTriggerTaskList())
                {
                    TriggerTaskVO triggerTaskVO = new TriggerTaskVO();
                    
                    if (uITriggerTaskVO.getActionId()!= null)
                    {
                        triggerTaskVO.setActionId(uITriggerTaskVO.getActionId());
                    }
                    
                    if (uITriggerTaskVO.getTaskId()!= null)
                    {
                        triggerTaskVO.setTaskId(uITriggerTaskVO.getTaskId());
                    }
                    
                    if (ValidateUtil.isNotEmpty(uITriggerTaskVO.getTaskParamList()))
                    {
                        List<TaskParamVO> taskParmVOList = new ArrayList<TaskParamVO>();
                        for (UITaskParamVO uITriggerTaskParamVO : uITriggerTaskVO.getTaskParamList())
                        {
                            TaskParamVO taskParamVO = new TaskParamVO();
                            BeanUtils.copyProperties(uITriggerTaskParamVO, taskParamVO);
                            taskParmVOList.add(taskParamVO);
                        }
                        triggerTaskVO.setTaskParamList(taskParmVOList);
                    }
                    triggerTaskVOList.add(triggerTaskVO);
                }
                campaignTaskVO.setTriggerTaskList(triggerTaskVOList);
            }
            campaignTaskVOList.add(campaignTaskVO);
        }
        return campaignTaskVOList;
    }
    
    public List<UICampaignPriceVO> transformUICampaignPriceVOToUICampaignPriceVO(List<CampaignPriceVO> campaignPriceVOList)
    {
        if (ValidateUtil.isEmpty(campaignPriceVOList))
        {
            return null;
        }
        List<UICampaignPriceVO> uiCampaignPriceVOList = new ArrayList<UICampaignPriceVO>();
        for (CampaignPriceVO campaignPriceVO : campaignPriceVOList)
        {
            UICampaignPriceVO UICampaignPriceVO = new UICampaignPriceVO();
            BeanUtils.copyProperties(campaignPriceVO, UICampaignPriceVO);
            UICampaignPriceVO.setChargeUnit(null);
            UICampaignPriceVO.setPriceType(null);
            uiCampaignPriceVOList.add(UICampaignPriceVO);
        }
        return uiCampaignPriceVOList;
    }
    
    public UICampaignAllBasicVO transformCampaignAllBasicVOToUICampaignAllBasicVO(CampaignAllBasicVO campaignBasicVO)
    {
        UICampaignAllBasicVO uiCampaignAllBasicVO = new UICampaignAllBasicVO();
        if (ValidateUtil.isNotEmpty(campaignBasicVO))
        {
            BeanUtils.copyProperties(campaignBasicVO, uiCampaignAllBasicVO);
            // uiCampaignAllBasicVO.setParticipant(campaignBasicVO.getRemainParticipant());
        }
        return uiCampaignAllBasicVO;
    }
    
    public CampaignPropertyDefQueryConditionVO transformCampaignPropertyDefCriteriaToQueryConsitionVO(final Integer campaignType,
            final Integer pageNo, final Integer pageSize)
    {
        final CampaignPropertyDefQueryConditionVO conditionVO = new CampaignPropertyDefQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        conditionVO.setCampaignType(campaignType);
        return conditionVO;
    }
    
    public PageInfo<UICampaignPropertyDefVO> transformCampaignPropertyDefPageInfoToUIPageInfo(
            PageInfo<CampaignPropertyDefVO> pageInfo)
    {
        final PageInfo<UICampaignPropertyDefVO> uiPageInfo = new PageInfo<UICampaignPropertyDefVO>();
        if (pageInfo== null|| ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UICampaignPropertyDefVO> uiCampaignPropertyDefVOList = new ArrayList<UICampaignPropertyDefVO>();
        for (CampaignPropertyDefVO campaignPropertyDefVO : pageInfo.getResult())
        {
            UICampaignPropertyDefVO uiCampaignPropertyDefVO = new UICampaignPropertyDefVO();
            BeanUtils.copyProperties(campaignPropertyDefVO, uiCampaignPropertyDefVO);
            uiCampaignPropertyDefVOList.add(uiCampaignPropertyDefVO);
        }
        BeanUtils.copyProperties(pageInfo, uiPageInfo);
        uiPageInfo.setResult(uiCampaignPropertyDefVOList);
        return uiPageInfo;
    }
    
    /**
     * @Description: 将UICampaignPropertyDefineVO中的属性拷贝到CampaignPropertyDefineVO中
     * @Description: copy UICampaignPropertyDefineVO property to CampaignPropertyDefineVO
     * @author zhengzy
     * @param uICampaignPropertyDefineVO
     * @return
     */
    public List<CampaignPropertyDefineVO> transformUICampaignPropertyDefineVOToCampaignPropertyDefineVO(
            List<UICampaignPropertyDefineVO> uICampaignPropertyDefineVOList)
    {
        List<CampaignPropertyDefineVO> campaignPropertyDefineVOList = new ArrayList<CampaignPropertyDefineVO>();
        for (UICampaignPropertyDefineVO uICampaignPropertyDefineVO : uICampaignPropertyDefineVOList)
        {
            CampaignPropertyDefineVO campaignPropertyDefineVO = new CampaignPropertyDefineVO();
            BeanUtils.copyProperties(uICampaignPropertyDefineVO, campaignPropertyDefineVO);
            campaignPropertyDefineVOList.add(campaignPropertyDefineVO);
        }
        return campaignPropertyDefineVOList;
    }
    
    /**
     * @Description: 根据groupCode、categoryIds、campaignVOList和将CampaignVO中的属性拷贝到UICampaignGroupVO中
     * @Description: copy CampaignVO property to UICampaignGroupVO according the input parameters groupCode、categoryIds and
     *               campaignVOList
     * @author zhengzy
     * @param groupCode
     * @param categoryIds
     * @param campaignVOList
     * @return
     */
    public UICampaignGroupVO transformCampaignVOListToUICampaignGroupVO(String groupCode, List<Integer> categoryIds,
            List<CampaignVO> campaignVOList)
    {
        UICampaignGroupVO campaignGroup = new UICampaignGroupVO();
        campaignGroup.setGroupCode(groupCode);
        List<UICampaignCategoryVO> campaignCategories = new ArrayList<UICampaignCategoryVO>();
        
        List<UICampaignVO> tempUICampaignVOList = transformUICampaignVOList(campaignVOList);
        
        if (ValidateUtil.isNotEmpty(categoryIds))
        {
            for (int i = 0; i< categoryIds.size(); i++ )
            {
                UICampaignCategoryVO campaignCategory = new UICampaignCategoryVO();
                campaignCategory.setCategoryId(categoryIds.get(i));
                List<UICampaignVO> campaigns = new ArrayList<UICampaignVO>();
                if (ValidateUtil.isNotEmpty(tempUICampaignVOList))
                {
                    for (UICampaignVO vo : tempUICampaignVOList)
                    {
                        if (vo.getCategoryId()== categoryIds.get(i))
                        {
                            UICampaignVO tempVo = new UICampaignVO();
                            BeanUtils.copyProperties(vo, tempVo, new String[]{"categoryId" });
                            campaigns.add(tempVo);
                        }
                    }
                }
                campaignCategory.setCampaigns(campaigns);
                campaignCategories.add(campaignCategory);
            }
        }
        else
        {
            UICampaignCategoryVO campaignCategory = new UICampaignCategoryVO();
            List<UICampaignVO> campaigns = new ArrayList<UICampaignVO>();
            if (ValidateUtil.isNotEmpty(tempUICampaignVOList))
            {
                for (UICampaignVO vo : tempUICampaignVOList)
                {
                    UICampaignVO tempVo = new UICampaignVO();
                    BeanUtils.copyProperties(vo, tempVo, new String[]{"categoryId" });
                    campaigns.add(tempVo);
                }
            }
            campaignCategory.setCampaigns(campaigns);
            campaignCategories.add(campaignCategory);
        }
        campaignGroup.setCampaignCategories(campaignCategories);
        return campaignGroup;
    }
    
    public CampaignPropertyDefVO transformUICampaignPropertyDefineVOToCampaignPropertyDefineVO(
            UICampaignPropertyDefineVO uiCampaignPropertyDefineVO)
    {
        CampaignPropertyDefVO campaignPropertyDefVO = new CampaignPropertyDefVO();
        BeanUtils.copyProperties(uiCampaignPropertyDefineVO, campaignPropertyDefVO);
        return campaignPropertyDefVO;
    }
    
    public PageInfo<UINewFeedCampaignVO> transformNewFeedCampaignVOPageInfoToUINewFeedCampaignVOPageInfo(
            PageInfo<NewFeedCampaignVO> pageInfo)
    {
        PageInfo<UINewFeedCampaignVO> page = new PageInfo<UINewFeedCampaignVO>();
        if (pageInfo== null|| ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return page.emptyPageInfo();
        }
        List<NewFeedCampaignVO> list = pageInfo.getResult();
        List<UINewFeedCampaignVO> result = new ArrayList<UINewFeedCampaignVO>();
        
        // copy the pageInfo properties
        BeanUtils.copyProperties(pageInfo, page);
        
        for (NewFeedCampaignVO vo : list)
        {
            UINewFeedCampaignVO uivo = new UINewFeedCampaignVO();
            
            // copy the partner information
            BeanUtils.copyProperties(vo, uivo);
            
            // copy the campaignBasic information
            CampaignBasicVO campaignBasicVo = vo.getCampaignBasic();
            if (campaignBasicVo!= null)
            {
                UICampaignBasicVO uiCampaignBasicVo = new UICampaignBasicVO();
                BeanUtils.copyProperties(campaignBasicVo, uiCampaignBasicVo);
                uiCampaignBasicVo.setValidDate(campaignBasicVo.getValidDate().getTime());
                uiCampaignBasicVo.setExpiredDate(campaignBasicVo.getExpiredDate().getTime());
                uivo.setCampaignBasic(uiCampaignBasicVo);
            }
            
            // copy the campaignDetail information
            List<CampaignDetailVO> campaignDetails = vo.getCampaignDetails();
            if (ValidateUtil.isNotEmpty(campaignDetails))
            {
                List<UICampaignDetailVO> uiCampaignDetails = new ArrayList<UICampaignDetailVO>();
                for (CampaignDetailVO campaignDetailVO : campaignDetails)
                {
                    UICampaignDetailVO uiCampaignDetailVO = new UICampaignDetailVO();
                    BeanUtils.copyProperties(campaignDetailVO, uiCampaignDetailVO, new String[]{"propertyValueType","propertyName","mandatory"});
                    uiCampaignDetails.add(uiCampaignDetailVO);
                }
                uivo.setCampaignDetails(uiCampaignDetails);
            }
            result.add(uivo);
        }
        page.setResult(result);
        return page;
    }
}
