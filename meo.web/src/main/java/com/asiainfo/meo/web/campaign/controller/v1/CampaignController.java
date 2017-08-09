package com.asiainfo.meo.web.campaign.controller.v1;

import java.util.HashMap;
import java.util.List;
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

import com.asiainfo.meo.campaign.define.CampaignConstantDefine;
import com.asiainfo.meo.campaign.profile.app.constant.CampaignErrorConstant;
import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
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
import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignActivityHistoryVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignAllBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignSimpleBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignBasicInfoVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignCommonVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignDetailVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignGroupConditionsVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignGroupVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPriceVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPropertyDefVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPropertyDefineVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignTaskVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignVO;
import com.asiainfo.meo.web.campaign.model.vo.UIDraftCampaignVO;
import com.asiainfo.meo.web.campaign.model.vo.UINewFeedCampaignVO;
import com.asiainfo.meo.web.campaign.model.vo.UIReNewCampaignCommonVO;
import com.asiainfo.meo.web.campaign.model.vo.UITaskParamVO;
import com.asiainfo.meo.web.campaign.model.vo.UITriggerTaskVO;
import com.asiainfo.meo.web.component.CampaignComponent;
import com.asiainfo.meo.web.core.Message;

/**
 * @Description: campaign 对应 controller
 * @Description: campaign controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhengzy
 * @Date 2015年4月20日 下午3:24:20
 * @version 1.0
 */
@RestController
@RequestMapping("/campaign")
public class CampaignController
{
    private static final Log   LOG = LogFactory.getLog(CampaignController.class);
    
    @Resource
    private CampaignPserviceBO campaignPserviceBO;
    
    @Resource
    private CampaignComponent  campaignComponent;
    
    /**
     * @Description: 获取受欢迎的campaign
     * @Description: query hot campaign
     * @modifyReason:
     * @author zhengzy
     * @param pageSize
     * @param pageNo
     */
    @RequestMapping(params = "method=meo.campaign.hot.cust.list", method = {RequestMethod.GET, RequestMethod.POST })
    public PageInfo<UICampaignVO> getHotCampaign(@RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer categoryId,
            @RequestParam String groupCode)
    {
        LOG.debug("-------------meo.campaign.hot.cust.list--------begin-------");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<CampaignVO> campaignVOPageInfo = campaignPserviceBO.getHotCampaignList(groupCode, categoryId, pageSize, pageNo);
        PageInfo<UICampaignVO> pageInfo = campaignComponent.transformUICampaignVOList(campaignVOPageInfo);
        LOG.debug("-------------meo.campaign.hot.cust.list--------end-------");
        return pageInfo;
    }
    
    /**
     * @Description: 获取最新发布的campaign
     * @Description: query the latest release campaign
     * @modifyReason:
     * @author zhengzy
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.new.cust.list", method = {RequestMethod.GET, RequestMethod.POST })
    public PageInfo<UICampaignVO> getNewCampaign(@RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer categoryId,
            @RequestParam String groupCode)
    {
        LOG.debug("-------------meo.campaign.new.cust.list---------begin------");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<CampaignVO> campaignVoPageInfo = campaignPserviceBO.getNewCampaignList(groupCode, categoryId, pageSize, pageNo);
        PageInfo<UICampaignVO> pageInfo = campaignComponent.transformUICampaignVOList(campaignVoPageInfo);
        LOG.debug("-------------meo.campaign.new.cust.list---------end------");
        return pageInfo;
    }
    
    /**
     * @Description: 根据客户id查询，活动历史记录
     * @Description: get activity history by custId
     * @modifyReason:
     * @author zhengzy
     * @param custId
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping(params = "method=meo.activity.coin.earned.list", method = {RequestMethod.GET, RequestMethod.POST })
    public PageInfo<UICampaignActivityHistoryVO> getActivityHistory(@RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo)
    {
        LOG.debug("--------------meo.activity.coin.earned.list------start--------");
        Long custId = BoContext.getBoContext().getUserId();
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<CampaignActivityHistoryVO> campaignActivityHistoryVOList = campaignPserviceBO.getCampaignActivityHistoryList(
                custId, pageSize, pageNo);
        PageInfo<UICampaignActivityHistoryVO> pageInfo = campaignComponent
                .transformUICampaignActivityHistoryVOList(campaignActivityHistoryVOList);
        LOG.debug("--------------meo.activity.coin.earned.list------end--------");
        return pageInfo;
    }
    
    /**
     * @Description: 获得campaign基本信息
     * @Description: get Campaign Basic Info
     * @author zhoujj
     * @param campaignId
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.basic.partner.search", method = RequestMethod.POST)
    public PageInfo<UICampaignBasicInfoVO> getCampaignBasicInfo(
            @RequestBody(required = false) UICampaignBasicVO campaignCondition, @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo)
    {
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        CampaignBasicVO campaignBasicVO = campaignComponent.transformUICampaignVOToCampaignBasicVO(campaignCondition);
        PageInfo<CampaignBasicVO> campaignBasicVOPageInfo = campaignPserviceBO.getCampaignBasicInfo(campaignBasicVO, pageSize,
                pageNo);
        PageInfo<UICampaignBasicInfoVO> pageInfo = campaignComponent
                .transformCampaignBasicVOPageInfoToUICampaignVOPageInfo(campaignBasicVOPageInfo);
        LOG.debug(JsonUtil.writeObjectAsString(pageInfo));
        LOG.debug("get Campaign Basic Info end!");
        return pageInfo;
    }
    
    /**
     * @Description: 获得campaign基本信息
     * @Description: get Campaign Basic Info
     * @author zhoujj
     * @param campaignId
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.basic.get", method = {RequestMethod.GET, RequestMethod.POST })
    public Message<UICampaignSimpleBasicVO> getCampaignInfoById(@RequestParam Long campaignId)
    {
        LOG.debug("get Campaign Info By Id begin!");
        Message<UICampaignSimpleBasicVO> msg = new Message<UICampaignSimpleBasicVO>();
        CampaignBasicVO campaignBasicVO = campaignPserviceBO.getCampaignInfoById(campaignId);
        UICampaignSimpleBasicVO campaignVO = campaignComponent.transformCampaignBasicVOToUICampaignVO(campaignBasicVO);
        LOG.debug((JsonUtil.writeObjectAsString(campaignVO)));
        msg.setBody(campaignVO);
        LOG.debug("get Campaign Info By Id end!");
        return msg;
    }
    
    /**
     * @Description: 获得campaign属性参数
     * @Description: get campaign detail
     * @author zhaozx
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.detail.get", method = {RequestMethod.GET, RequestMethod.POST })
    public List<UICampaignDetailVO> getCampaignDetail(@RequestParam Long campaignId)
    {
        LOG.debug("get campaign detail by campaign id begin!");
        List<CampaignDetailVO> campaignDetail = campaignPserviceBO.getCampaignDetail(campaignId);
        List<UICampaignDetailVO> uIcampaignDetail = campaignComponent
                .transformCampaignDetailVOToUICampaignDetailVO(campaignDetail,false);
        LOG.debug("get campaign detail by campaign id end!");
        return uIcampaignDetail;
    }
    
    /**
     * @Description: 新增campaign为draft状态
     * @Description: save the campaign draft
     * @modifyReason:
     * @author zhengzy
     * @param uICampaignCommonVO
     * @param uICampaignDetailVOs
     * @param uICampaignTaskVOs
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.partner.save", method = {RequestMethod.POST })
    public Message<Map<String, Long>> saveDraftCampaign(@RequestBody @Valid UIDraftCampaignVO darftCampaign)
    {
        LOG.debug("----------------------meo.campaign.partner.save--------------begin---------");
        
        UICampaignCommonVO campaignCommon = darftCampaign.getCampaignCommon();
        List<UICampaignDetailVO> campaignDetails = darftCampaign.getCampaignDetails();
        List<UICampaignTaskVO> campaignTasks = darftCampaign.getCampaignTasks();
        
        // the first version campaign,validDate must be equal to the startDate
        judgeValidDateAndStartDateInFirstVersion(campaignCommon);
        
        if (ValidateUtil.isNotEmpty(campaignCommon.getCampaignPrices()))
        {
            throwCampaignPriceException(campaignCommon.getCampaignPrices());
        }
        
        throwCampaignTaskException(campaignTasks);
        
        CampaignCommonVO campaignCommonVO = campaignComponent.transformUICampaignCommonVOToCampaignCommonVO(campaignCommon);
        List<CampaignDetailVO> campaignDetailVOs = campaignComponent
                .transformUICampaignDetailVOToCampaignDetailVO(campaignDetails);
        List<CampaignTaskVO> campaignTaskVOs = campaignComponent.transformUICampaignTaskVOToCampaignTaskVO(campaignTasks);
        Campaign campaign = campaignPserviceBO.saveDraftCampaign(campaignCommonVO, campaignDetailVOs, campaignTaskVOs);
        if (campaign== null)
        {
            throw new MeoException(CampaignErrorConstant.ENTITY_IS_EMPTY, new String[]{Campaign.class.getSimpleName() });
        }
        Message<Map<String, Long>> msg = new Message<Map<String, Long>>();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("campaignId", campaign.getCampaignId());
        msg.setBody(map);
        LOG.debug("----------------------meo.campaign.partner.save--------------end---------");
        return msg;
    }
    
    // the first version campaign,validDate must be equal to the startDate
    private void judgeValidDateAndStartDateInFirstVersion(UICampaignCommonVO campaignCommon)
    {
        if (!campaignCommon.getValidDate().equals(campaignCommon.getStartDate()))
        {
            LOG.debug("the first version campaign validDate must be equal to the campaign startDate,the partner id is "
                    + campaignCommon.getPartnerId());
            throw new MeoException(CampaignErrorConstant.FIRST_VERSION_CAMPAIGN_VAILDDATE_NOT_EQUAL_STARTDATE,
                    new Object[]{campaignCommon.getPartnerId() });
        }
    }
    
    // check the campaignTask
    private void throwCampaignTaskException(List<UICampaignTaskVO> uICampaignTaskVOs)
    {
        for (UICampaignTaskVO campaignTask : uICampaignTaskVOs)
        {
            List<UITaskParamVO> taskParamList = campaignTask.getTaskParamList();
            if (ValidateUtil.isNotEmpty(taskParamList))
            {
                for (UITaskParamVO taskParam : taskParamList)
                {
                    if (taskParam.getParamId()== null)
                    {
                        throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_PARAM_ID_CAN_NOT_NULL);
                    }
                    if (taskParam.getParamValue()== null)
                    {
                        throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_PARAM_VALUE_CAN_NOT_NULL);
                    }
                }
            }
            
            List<UITriggerTaskVO> triggerTaskList = campaignTask.getTriggerTaskList();
            if (ValidateUtil.isNotEmpty(triggerTaskList))
            {
                for (UITriggerTaskVO triggerTask : triggerTaskList)
                {
                    List<UITaskParamVO> triggerParamList = triggerTask.getTaskParamList();
                    if (triggerTask.getActionId()== null)
                    {
                        throw new MeoException(CampaignErrorConstant.CAMPAIGN_TRIGGER_TASK_PARAM_ACTIONID_CAN_NOT_NULL);
                    }
                    if (ValidateUtil.isEmpty(triggerParamList))
                    {
                        throw new MeoException(CampaignErrorConstant.CAMPAIGN_TRIGGER_TASK_PARAM_LIST_CAN_NOT_NULL);
                    }
                    
                    for (UITaskParamVO triggerParam : triggerParamList)
                    {
                        if (triggerParam.getParamId()== null)
                        {
                            throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_PARAM_ID_CAN_NOT_NULL);
                        }
                        if (triggerParam.getParamValue()== null)
                        {
                            throw new MeoException(CampaignErrorConstant.CAMPAIGN_TASK_PARAM_VALUE_CAN_NOT_NULL);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * @Description: 发布campaign为publish状态
     * @Description: update the campaign publish
     * @modifyReason:
     * @author zhengzy
     * @param uICampaignCommonVO
     * @param uICampaignDetailVOs
     * @param uICampaignTaskVOs
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.partner.publish", method = {RequestMethod.POST })
    public Message<Map<String, Long>> publishCampaign(@RequestBody @Valid UIDraftCampaignVO darftCampaign)
    {
        LOG.debug("----------------------meo.campaign.partner.publish--------------begin---------");
        
        UICampaignCommonVO campaignCommon = darftCampaign.getCampaignCommon();
        List<UICampaignDetailVO> campaignDetails = darftCampaign.getCampaignDetails();
        List<UICampaignTaskVO> campaignTasks = darftCampaign.getCampaignTasks();
        
        // the first version campaign,validDate must be equal to the startDate
        judgeValidDateAndStartDateInFirstVersion(campaignCommon);
        
        if (ValidateUtil.isEmpty(campaignCommon.getCampaignPrices()))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_PRICE });
        }
        throwCampaignPriceException(campaignCommon.getCampaignPrices());
        
        throwCampaignTaskException(campaignTasks);
        
        CampaignCommonVO campaignCommonVO = campaignComponent.transformUICampaignCommonVOToCampaignCommonVO(campaignCommon);
        List<CampaignDetailVO> campaignDetailVOs = campaignComponent
                .transformUICampaignDetailVOToCampaignDetailVO(campaignDetails);
        List<CampaignTaskVO> campaignTaskVOs = campaignComponent.transformUICampaignTaskVOToCampaignTaskVO(campaignTasks);
        Campaign campaign = campaignPserviceBO.publishCampaign(campaignCommonVO, campaignDetailVOs, campaignTaskVOs);
        if (campaign== null)
        {
            throw new MeoException(CampaignErrorConstant.ENTITY_IS_EMPTY, new String[]{Campaign.class.getSimpleName() });
        }
        Message<Map<String, Long>> msg = new Message<Map<String, Long>>();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("campaignId", campaign.getCampaignId());
        msg.setBody(map);
        LOG.debug("----------------------meo.campaign.partner.publish--------------end---------");
        return msg;
    }
    
    /**
     * @Description: 新增一条campaign为renew状态
     * @Description: save the campaign renew
     * @modifyReason:
     * @author zhengzy
     * @param uICampaignCommonVO
     * @param uICampaignDetailVOs
     * @param uICampaignTaskVOs
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.renewed.partner.save", method = {RequestMethod.POST })
    public Message<Map<String, Long>> modifyRnewCampaign(@RequestBody @Valid UIReNewCampaignCommonVO campaignCommon)
    {
        LOG.debug("----------------------meo.campaign.renewed.partner.save--------------begin---------");
        CampaignCommonVO campaignCommonVO = campaignComponent.transformUIReNewCampaignCommonVO(campaignCommon);
        if (ValidateUtil.isNotEmpty(campaignCommon.getCampaignPrices()))
        {
            throwCampaignPriceException(campaignCommon.getCampaignPrices());
        }
        Campaign campaign = campaignPserviceBO.modifyRenewCampaign(campaignCommonVO);
        if (campaign== null)
        {
            throw new MeoException(CampaignErrorConstant.ENTITY_IS_EMPTY, new String[]{Campaign.class.getSimpleName() });
        }
        Message<Map<String, Long>> msg = new Message<Map<String, Long>>();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("campaignId", campaign.getCampaignId());
        msg.setBody(map);
        LOG.debug("----------------------meo.campaign.renewed.partner.save--------------end---------");
        return msg;
    }
    
    /**
     * @Description: 发布新增一条campaign为publish状态
     * @Description: update the campaign publish
     * @modifyReason:
     * @author zhengzy
     * @param campaignCommon
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.renewed.partner.publish", method = {RequestMethod.POST })
    public Message<Map<String, Long>> publishRenewCampaign(@RequestBody @Valid UIReNewCampaignCommonVO campaignCommon)
    {
        LOG.debug("----------------------meo.campaign.renewed.partner.publish--------------begin---------");
        List<UICampaignPriceVO> campaignPrices = campaignCommon.getCampaignPrices();
        if (ValidateUtil.isEmpty(campaignPrices))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{CampaignConstantDefine.CAMPAIGN_PRICE });
        }
        
        throwCampaignPriceException(campaignPrices);
        CampaignCommonVO campaignCommonVO = campaignComponent.transformUIReNewCampaignCommonVO(campaignCommon);
        Campaign campaign = campaignPserviceBO.publishRenewcampaign(campaignCommonVO);
        if (campaign== null)
        {
            throw new MeoException(CampaignErrorConstant.ENTITY_IS_EMPTY, new String[]{Campaign.class.getSimpleName() });
        }
        Message<Map<String, Long>> msg = new Message<Map<String, Long>>();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("campaignId", campaign.getCampaignId());
        msg.setBody(map);
        LOG.debug("----------------------meo.campaign.renewed.partner.publish--------------end---------");
        return msg;
    }
    
    // check the campaignPrice
    private void throwCampaignPriceException(List<UICampaignPriceVO> campaignPrices)
    {
        for (UICampaignPriceVO campaignPrice : campaignPrices)
        {
            if (campaignPrice.getChargeUnit()== null)
            {
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_PRICE_CHARGEUNIT_CAN_NOT_NULL);
            }
            if (campaignPrice.getPriceType()== null)
            {
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_PRICE_PRICETYPE_CAN_NOT_NULL);
            }
            if (campaignPrice.getUnit()== null)
            {
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_PRICE_UNIT_CAN_NOT_NULL);
            }
            if (campaignPrice.getUnitPrice()== null)
            {
                throw new MeoException(CampaignErrorConstant.CAMPAIGN_PRICE_UNITPRICE_CAN_NOT_NULL);
            }
        }
    }
    
    @RequestMapping(params = "method=meo.campaign.get", method = {RequestMethod.GET, RequestMethod.POST })
    public UICampaignAllBasicVO getCampaignDetailInfoById(@RequestParam Long campaignId)
    {
        LOG.debug("get meo.campaign.get begin!");
        // campaignBasic
        CampaignAllBasicVO campaignBasicVO = campaignPserviceBO.getCampaignDetailInfoById(campaignId);
        UICampaignAllBasicVO uiCampaignAllBasicVO = campaignComponent
                .transformCampaignAllBasicVOToUICampaignAllBasicVO(campaignBasicVO);
        
        // campaignPropertys
        List<CampaignDetailVO> campaignPropertys = campaignPserviceBO.getCampaignDetail(campaignId);
        List<UICampaignDetailVO> uIcampaignDetail = campaignComponent
                .transformCampaignDetailVOToUICampaignDetailVO(campaignPropertys,true);
        uiCampaignAllBasicVO.setCampaignPropertys(uIcampaignDetail);
        String campaignNo = campaignBasicVO.getCampaignNo();
        // campaignTaskList
        List<CampaignTaskVO> campaignTaskList = campaignPserviceBO.getCampaignActivity(campaignId);
        List<UICampaignTaskVO> uIcampaignTaskList = campaignComponent.transformCampaignTaskVOToUICampaignTaskVO(campaignNo,
                campaignTaskList);
        uiCampaignAllBasicVO.setCampaignTasks(uIcampaignTaskList);
        
        // campaignPrices
        List<CampaignPriceVO> campaignPriceVOList = campaignPserviceBO.getCampaignPricesByCampaignId(campaignId);
        List<UICampaignPriceVO> uiCampaignPriceVO = campaignComponent
                .transformUICampaignPriceVOToUICampaignPriceVO(campaignPriceVOList);
        uiCampaignAllBasicVO.setCampaignPrices(uiCampaignPriceVO);
        
        LOG.debug("get meo.campaign.get end!");
        
        return uiCampaignAllBasicVO;
    }
    
    @RequestMapping(params = "method=meo.campaign.porperty.get", method = {RequestMethod.GET })
    public PageInfo<UICampaignPropertyDefVO> getCampaignPropertyDefByCampaignType(@RequestParam Integer campaignType,
            @RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize)
    {
        LOG.debug("get meo.campaign.porperty.get begin!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        final CampaignPropertyDefQueryConditionVO conditionVO = campaignComponent
                .transformCampaignPropertyDefCriteriaToQueryConsitionVO(campaignType, pageNo, pageSize);
        PageInfo<CampaignPropertyDefVO> pageInfo = campaignPserviceBO.getCampaignPropertyDefByCampaignType(conditionVO);
        PageInfo<UICampaignPropertyDefVO> uiPageInfo = campaignComponent
                .transformCampaignPropertyDefPageInfoToUIPageInfo(pageInfo);
        LOG.debug("get meo.campaign.porperty.get end!");
        
        return uiPageInfo;
    }
    
    /**
     * @Description: 新增campaign属性
     * @Description: add campaignPropertyDef
     * @modifyReason:
     * @author zhengzy
     * @param campaignPropertyDefine
     */
    @RequestMapping(params = "method=meo.campaign.porperty.define.create", method = {RequestMethod.POST })
    public void createCampaignPropertyDef(
            @RequestBody @Validated(value = Insert.class) List<UICampaignPropertyDefineVO> campaignPropertyDefineList)
    {
        LOG.debug("----------------meo.campaign.porperty.create----------begin-----------------");
        List<CampaignPropertyDefineVO> campaignPropertyDefineVOList = campaignComponent
                .transformUICampaignPropertyDefineVOToCampaignPropertyDefineVO(campaignPropertyDefineList);
        campaignPserviceBO.addCampaignPropertyDef(campaignPropertyDefineVOList);
        LOG.debug("----------------meo.campaign.porperty.create----------end-----------------");
        return;
    }
    
    /**
     * @Description: 根据groupCode和categoryId来查询campaign集合
     * @Description: query campaign List by groupCode and categoryId
     * @modifyReason:
     * @author zhengzy
     * @param campaignGroupConditions
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.list", method = {RequestMethod.POST })
    public UICampaignGroupVO getCampaignList(@RequestBody @Valid UICampaignGroupConditionsVO campaignGroupCondition)
    {
        LOG.debug("----------------------meo.campaign.list--------------begin---------");
        String groupCode = campaignGroupCondition.getGroupCode();
        List<Integer> categoryIds = campaignGroupCondition.getCategoryList();
        List<CampaignVO> campaignVOList = campaignPserviceBO.getCampaignVOList(groupCode, categoryIds);
        UICampaignGroupVO campaignGroup = campaignComponent.transformCampaignVOListToUICampaignGroupVO(groupCode, categoryIds,
                campaignVOList);
        LOG.debug("----------------------meo.campaign.list--------------end---------");
        return campaignGroup;
    }
    
    @RequestMapping(params = "method=meo.campaign.porperty.define.modify", method = {RequestMethod.POST })
    public void modifyCampaignPropertyDef(
            @RequestBody @Validated(value = Update.class) UICampaignPropertyDefineVO uiCampaignPropertyDefineVO)
    {
        LOG.debug("----------------------meo.campaign.porperty.define.modify---------begin---------");
        CampaignPropertyDefVO campaignPropertyDefVO = campaignComponent
                .transformUICampaignPropertyDefineVOToCampaignPropertyDefineVO(uiCampaignPropertyDefineVO);
        campaignPserviceBO.modifyCampaignPropertyDef(campaignPropertyDefVO);
        LOG.debug("----------------------meo.campaign.porperty.define.modify-----------end---------");
        return;
    }
    
    @RequestMapping(params = "method=meo.app.download.list", method = {RequestMethod.GET })
    public PageInfo<String> getAppDownLoad(@RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        LOG.debug("----------------------meo.app.download.list---------begin---------");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        Long userId = BoContext.getBoContext().getUserId();
        PageInfo<String> page = campaignPserviceBO.getAppDownLoad(userId, pageNo, pageSize);
        LOG.debug("----------------------meo.app.download.list-----------end---------");
        return page;
    }
    
    /**
     * @Description: 展示最新发布的campaign,根据时间倒序排列
     * @Description: list the newly campaign ,according to the date arranged in reverse chronological order
     * @modifyReason:
     * @author zhengzy
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.all.list", method = {RequestMethod.GET })
    public PageInfo<UINewFeedCampaignVO> getNewsFeedCampaignPageInfo(@RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        LOG.debug("----------------------meo.campaign.all.list---------begin---------");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<NewFeedCampaignVO> pageInfo = campaignPserviceBO.getNewsFeedCampaignPageInfo(pageNo, pageSize);
        PageInfo<UINewFeedCampaignVO> page = new PageInfo<UINewFeedCampaignVO>();
        page = campaignComponent.transformNewFeedCampaignVOPageInfoToUINewFeedCampaignVOPageInfo(pageInfo);
        LOG.debug("----------------------meo.campaign.all.list-----------end---------");
        return page;
    }
}
