package com.asiainfo.meo.web.common.define.controller.v1;

import java.util.ArrayList;
import java.util.Hashtable;
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
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.CampaignAction;
import com.asiainfo.meo.system.common.app.model.vo.CampaignEnumGroupQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.CampaignTypeDefine;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumGroupDefVO;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.SysCategoryDefVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.web.common.define.model.vo.UIActionInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UIActionParamDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UICampaginTypeInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UICampaignGroupDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UICategoryDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UICategoryInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UIChargeUnitInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UICountryVO;
import com.asiainfo.meo.web.common.define.model.vo.UIDenominationInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UIEnumGroupDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UILanguageVO;
import com.asiainfo.meo.web.common.define.model.vo.UIMeasureInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UIPartnerTypeInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UIProductCategoryInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UIServiceTypeInfoVO;
import com.asiainfo.meo.web.common.define.model.vo.UITimeUnitInfoVO;
import com.asiainfo.meo.web.component.EnumsComponent;
import com.asiainfo.meo.web.customer.model.vo.UIEnumDefineVO;

@RestController
@RequestMapping("/common/define")
public class EnumsController
{
    
    @Resource
    private SystemPserviceBO systemPserviceBO;
    
    private static final Log LOG = LogFactory.getLog(EnumsController.class);
    
    /**
     * @Description: 获取campaign分类
     * @Description: get the campagin type
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UICategoryInfoVO>>
     */
    @RequestMapping(params = "method=meo.category.app.list", method = {RequestMethod.POST, RequestMethod.GET })
    public PageInfo<UICategoryInfoVO> getCampaignCategory(@RequestParam(required = false) Long categoryId)
    {
        LOG.debug("get the campagin Category begin!");
        PageInfo<CategoryDefine> catdefs = systemPserviceBO.getCategoryEnumTotalSize(categoryId, CategoryDefine.CAMPAGIN_TYPE);
        PageInfo<UICategoryInfoVO> categoryinfoList = EnumsComponent.transformPartnerProfileToPartnerProfileList(catdefs);
        LOG.debug("get the campagin Category end!");
        return categoryinfoList;
    }
    
    /**
     * @Description: 获取campaign分类
     * @Description: get the campagin type
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UICategoryInfoVO>>
     */
    @RequestMapping(params = "method=meo.define.category.product.get", method = {RequestMethod.GET })
    public PageInfo<UIProductCategoryInfoVO> getProductCategory(@RequestParam(required = false) Long productCateId)
    {
        LOG.debug("get the product Category begin!");
        PageInfo<CategoryDefine> catdefs = systemPserviceBO.getCategoryEnumTotalSize(productCateId, CategoryDefine.PRODUCT_TYPE);
        PageInfo<UIProductCategoryInfoVO> categoryinfoList = EnumsComponent.transformProductCategoryToProductCategoryeList(catdefs);
        LOG.debug("get the product Category end!");
        return categoryinfoList;
    }
    
    /**
     * @Description: 获取货币单位
     * @Description: get the currency unit
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UIMeasureInfoVO>>
     */
    @RequestMapping(params = "method=meo.unit.currency.list", method = {RequestMethod.POST, RequestMethod.GET })
    public PageInfo<UIMeasureInfoVO> getCurrencyUnit(@RequestParam(required = false) Long measureId) 
    {
        LOG.debug("get get the currency unit begin!");
        PageInfo<MeasureDefine> measuredefs = systemPserviceBO.getMeasureDefTotalSize(measureId, MeasureDefine.CURRENCY_UNIT_TYPE);
        PageInfo<UIMeasureInfoVO> measureinfoList = EnumsComponent.transformMeasureDefineToMeasureinfoList(measuredefs);
        LOG.debug("get get the currency unit end!");
        return measureinfoList;
    }
    
    /**
     * @Description: 填写计算费用的方式
     * @Description: get the charge unit
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UIEnumInfoVO>>
     */
    @RequestMapping(params = "method=meo.unit.charge.list", method = {RequestMethod.POST, RequestMethod.GET })
    public PageInfo<UIChargeUnitInfoVO> getChargeUnit(@RequestParam(required = false) String chargeUnitId)
    {
        LOG.debug("get the charge unit begin!");
        PageInfo<EnumDefine> enumdefs = systemPserviceBO.getEnumTypeTotalSize(chargeUnitId, EnumDefine.CHARGE_UNIT);
        PageInfo<UIChargeUnitInfoVO> enuminfoList = EnumsComponent.transformChargeUnitToChargeUnitinfoList(enumdefs);
        LOG.debug("get the charge unit end!");
        return enuminfoList;
    }
    
    /**
     * @Description: 获取时间单位
     * @Description: get the time nuit
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UIMeasureInfoVO>>
     */
    @RequestMapping(params = "method=meo.unit.time.list", method = {RequestMethod.POST, RequestMethod.GET })
    public PageInfo<UITimeUnitInfoVO> getTimeUnit(@RequestParam(required = false) Long timeUnitId)
    {
        LOG.debug("get the time nuit begin!");
        PageInfo<MeasureDefine> measuredefs = systemPserviceBO.getMeasureDefTotalSize(timeUnitId, MeasureDefine.TIME_UNIT_TYPE);
        PageInfo<UITimeUnitInfoVO> measureinfoList = EnumsComponent.transformMeasureDefineToTimeUnitinfoList(measuredefs);
        LOG.debug("measureinfoList="+ JsonUtil.writeObjectAsString(measureinfoList));
        LOG.debug("get the time nuit end!");
        return measureinfoList;
    }
    
    /**
     * @Description: 获取动作信息
     * @Description: get the action definition
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UIActionInfoVO>>
     */
    @RequestMapping(params = "method=meo.action.reward.list", method = {RequestMethod.GET })
    public PageInfo<UIActionInfoVO> getActionDefine(@RequestParam(required = false) Long actionId, Integer actionType)
    {
        LOG.debug("get the action definition begin!");
        PageInfo<ActionDefine> actiondefs = null;
        Integer actionTypeDefine = EnumsComponent.checkActionType(actionType);
        actiondefs = systemPserviceBO.getActionDefineTotalSize(actionId, actionTypeDefine);
        PageInfo<UIActionInfoVO> actioninfoList = EnumsComponent.transformActionDefineToActioninfoList(actiondefs);
        LOG.debug("actioninfoList="+ JsonUtil.writeObjectAsString(actioninfoList));
        LOG.debug("get the action definition end!");
        return actioninfoList;
    }
    
    /**
     * @Description: 填写计算费用的方式
     * @Description: get the list of Campagin Type define
     * @modifyReason:
     * @author zhoujj
     * @param
     * @return Message<List<UIEnumInfoVO>>
     */
    @RequestMapping(params = "method=meo.type.compaign.list", method = {RequestMethod.POST, RequestMethod.GET })
    public PageInfo<UICampaginTypeInfoVO> getCampaginType(@RequestParam(required = false) String campaignTypeId)
    {
        LOG.debug("get Campagin Type begin!");
        PageInfo<CampaignTypeDefine> campaignTypeDefineList = systemPserviceBO.getCampaginTypeTotalSize(campaignTypeId);
        PageInfo<UICampaginTypeInfoVO> campaignTypeinfoList = EnumsComponent
                .transformCampaignDefListToCampaignDefineList(campaignTypeDefineList);
        LOG.debug("campaignTypeinfoList="+ JsonUtil.writeObjectAsString(campaignTypeinfoList));
        LOG.debug("get Campagin Type end!");
        return campaignTypeinfoList;
    }
    
   /**
    * 
     * @Description: 获取partner的类型
     * @Description: get Partner type by partnerTypeId
     * @modifyReason: 
     * @author zhoujj
     * @param partnerTypeId
     * @return
    */
    @RequestMapping(params = "method=meo.type.partner.list", method = {RequestMethod.GET })
    public PageInfo<UIPartnerTypeInfoVO> getPartnerType(@RequestParam(required = false) String partnerTypeId)
    {
        LOG.debug("get the partner type begin!");
        PageInfo<EnumDefine> enumdefs = systemPserviceBO.getEnumTypeTotalSize(partnerTypeId, EnumDefine.PARTNER_TYPE);
        PageInfo<UIPartnerTypeInfoVO> enuminfoList = EnumsComponent.transformEnumDefineToPartnerTypeinfoList(enumdefs);
        LOG.debug("enuminfoList="+ JsonUtil.writeObjectAsString(enuminfoList));
        LOG.debug("get the partner type end!");
        return enuminfoList;
    }
    
    /**
     * 
      * @Description: 获取服务类型
      * @Description: (get Service type)
      * @modifyReason: 
      * @author zhoujj
      * @param serviceTypeId
      * @return
     */
     @RequestMapping(params = "method=meo.type.service.list", method = {RequestMethod.GET })
     public List<UIServiceTypeInfoVO> getServiceType(@RequestParam(required = false) String serviceTypeId)
     {
         LOG.debug("get the partner type begin!");
         List<EnumDefine> enumdefs = systemPserviceBO.getEnumByCode(serviceTypeId, EnumDefine.SERVICE_TYPE);
         List<UIServiceTypeInfoVO> enuminfoList = EnumsComponent.transformEnumDefineToServiceTypeinfoList(enumdefs);
         LOG.debug("enuminfoList="+ JsonUtil.writeObjectAsString(enuminfoList));
         LOG.debug("get the partner type end!");
         return enuminfoList;
     }
     
     /**
      * 
       * @Description: 获取面值单位
       * @Description: get Denomination Unit
       * @modifyReason: 
       * @author zhoujj
       * @param denominationUnitId
       * @return
      */
     @RequestMapping(params = "method=meo.unit.denomination.list", method = {RequestMethod.GET })
     public PageInfo<UIDenominationInfoVO> getDenominationUnit(@RequestParam(required = false) String denominationUnitId)
     {
         LOG.debug("get the denomination type begin!");
         PageInfo<EnumDefine> enumdefs = systemPserviceBO.getEnumTypeTotalSize(denominationUnitId, EnumDefine.DENOMINATION_UNIT);
         PageInfo<UIDenominationInfoVO> enuminfoList = EnumsComponent.transformEnumDefineToDenominationInfoList(enumdefs);
         LOG.debug("enuminfoList="+ JsonUtil.writeObjectAsString(enuminfoList));
         LOG.debug("get the denomination type end!");
         return enuminfoList;
     }
     
     @RequestMapping(params = "method=meo.action.param.get", method = {RequestMethod.GET })
     public PageInfo<UIActionParamDefVO> getSysActionParamDefByActionId(@RequestParam String actionId,
             @RequestParam(required = false) Integer pageNo,
             @RequestParam(required = false) Integer pageSize)
     {
         LOG.debug("get Action Param begin!");
         final SysActionParamDefQueryConditionVO conditionVO = 
                 EnumsComponent.transformActionParamDefCritriaToQueryConditionVO(actionId, pageNo, pageSize);
         PageInfo<SysActionParamDef> pageInfo = systemPserviceBO.getSysActionParamDefByActionId(conditionVO);
         PageInfo<UIActionParamDefVO> uiPageInfo = EnumsComponent.transformActionParamDefToUIActionParamDefVO(pageInfo);
         LOG.debug("enuminfoList="+ JsonUtil.writeObjectAsString(uiPageInfo));
         LOG.debug("get Action Param end!");
         return uiPageInfo;
     }
     
     
     @RequestMapping(params = "method=meo.enum.list", method = {RequestMethod.GET })
     public PageInfo<UIEnumDefineVO> getEnumByCodelist(@RequestParam(required = false) String enumCode, @RequestParam String enumType)
     {
         LOG.debug("meo.enum.list begin!");
         PageInfo<EnumDefine> enumdefs = systemPserviceBO.getEnumTypeTotalSize(enumCode, enumType);
         PageInfo<UIEnumDefineVO> enuminfoList = EnumsComponent.transformEnumDefineToEnumDefineVOList(enumdefs);
         LOG.debug("meo.enum.list end!");
         return enuminfoList;
     }
     
     @RequestMapping(params = "method=meo.enum.group.save", method = {RequestMethod.POST })
     public void createEnumGroup(@RequestBody @Validated(value=Insert.class) UIEnumGroupDefVO uiEnumGroupDefVO)
     {
         LOG.debug("meo.enum.group.save begin!");
         Long userId = BoContext.getBoContext().getUserId();
         uiEnumGroupDefVO.setOperatorId(userId);
         EnumGroupDefVO enumGroupDefVO = EnumsComponent.transformUIEnumGroupDefVOToEnumGroupDefVO(uiEnumGroupDefVO);
         systemPserviceBO.createCampaignGroup(enumGroupDefVO);
         LOG.debug("meo.enum.group.save end!");
     }
     
     @RequestMapping(params = "method=meo.campagin.group.save", method = {RequestMethod.POST })
     public void createCampaginGroup(@RequestBody @Validated(value=Insert.class) UICampaignGroupDefVO uiEnumGroupDefVO)
     {
         LOG.debug("meo.campagin.group.save begin!");
         Long userId = BoContext.getBoContext().getUserId();
         uiEnumGroupDefVO.setOperatorId(userId);
         uiEnumGroupDefVO.setGroupType(SysEnumGroupDef.CAMPAIGN_TYPE);
         EnumGroupDefVO enumGroupDefVO = EnumsComponent.transformUICampaignGroupDefVOToEnumGroupDefVO(uiEnumGroupDefVO);
         systemPserviceBO.createCampaignGroup(enumGroupDefVO);
         LOG.debug("meo.campagin.group.save end!");
     }
    
     @RequestMapping(params = "method=meo.product.category.save", method = {RequestMethod.POST })
     public void createProductCategory(@RequestBody @Validated UICategoryDefVO uiCategoryDefVO)
     {
         LOG.debug("meo.product.category.save begin!");
         Long userId = BoContext.getBoContext().getUserId();
         uiCategoryDefVO.setOperatorId(userId);
         SysCategoryDefVO sysCategoryDefVO = EnumsComponent.transformUISysCategoryDefVOToSysCategoryDefVO(uiCategoryDefVO);
         systemPserviceBO.createProductCategory(sysCategoryDefVO);
         LOG.debug("meo.product.category.save end!");
     }
     
     
     @RequestMapping(params = "method=meo.language.list", method = {RequestMethod.GET })
     public PageInfo<UILanguageVO> getLanguageDef(@RequestParam(required = false) Integer languageCode)
     {
         LOG.debug("get meo.language.list begin!");
         PageInfo<SysLanguageDef> languageDefs = systemPserviceBO.getLanguageDefTotalSize(languageCode);
         PageInfo<UILanguageVO> uiLanguageVOList = EnumsComponent.transformSysLanguageDefToUILanguageVOList(languageDefs);
         LOG.debug("get meo.language.list end!");
         return uiLanguageVOList;
     }
     
     @RequestMapping(params = "method=meo.country.list", method = {RequestMethod.GET })
     public PageInfo<UICountryVO> getCountryDef(
             @RequestParam(required = false) Long countryCode)
     {
         LOG.debug("get meo.country.list begin!");
         PageInfo<SysCountryDef> countryDefs = systemPserviceBO.getCountryDefTotalSize(countryCode);
         PageInfo<UICountryVO> uiCountryVOList = EnumsComponent.transformSysCountryDefToUICountryVOList(countryDefs);
         LOG.debug("get meo.country.list end!");
         return uiCountryVOList;
     }
     
     @RequestMapping(params = "method=meo.trigger.action.campaign.list", method = {RequestMethod.GET })
     public PageInfo<CampaignAction> getActionTotalSizeByCampaignType(
             @RequestParam(required = true) Integer campaignType,
             @RequestParam(required = false) Integer pageNo,
             @RequestParam(required = false) Integer pageSize)
     {
         LOG.debug("meo.trigger.action.campaign.list begin!");
         pageNo = ValidateUtil.checkPageNo(pageNo);
         pageSize = ValidateUtil.checkPageSize(pageSize);
         PageInfo<CampaignAction> pageinfo = systemPserviceBO.getActionTotalSizeByCampaginType(campaignType, pageNo, pageSize);
         LOG.debug("meo.trigger.action.campaign.list end!");
         return pageinfo;
     }
     
     @RequestMapping(params = "method=meo.campaign.group.modify", method = {RequestMethod.POST })
     public void modifyCampaignGroup(@RequestBody @Validated(value=Update.class) UICampaignGroupDefVO uiEnumGroupDefVO)
     {
         LOG.debug("meo.campaign.group.modify begin!");
         final Long userId = BoContext.getBoContext().getUserId();
         uiEnumGroupDefVO.setOperatorId(userId);
         uiEnumGroupDefVO.setGroupType(SysEnumGroupDef.CAMPAIGN_TYPE);
         EnumGroupDefVO sysEnumGroupDefVO = EnumsComponent.transformUICampaignGroupDefVOToEnumGroupDefVO(uiEnumGroupDefVO);
         systemPserviceBO.modifyCampaignGroup(sysEnumGroupDefVO);
         LOG.debug("meo.campaign.group.modify end!");
     }
     
     @RequestMapping(params = "method=meo.campaign.group.delete", method = {RequestMethod.GET })
     public void deleteCampaignGroup(@RequestParam Long campaignGroupID)
     {
         LOG.debug("meo.campaign.group.delete begin!");
         systemPserviceBO.deleteCampaignGroup(campaignGroupID);
         LOG.debug("meo.campaign.group.delete end!");
     }
     
     @RequestMapping(params = "method=meo.campaign.group.list", method = {RequestMethod.GET })
     public PageInfo<UICampaignGroupDefVO> getCampaignGroup(@RequestParam(required = false) Integer pageNo,
             @RequestParam(required = false) Integer pageSize,
             @RequestParam(required = false) String groupCode)
     {
         LOG.debug("meo.campaign.group.list begin!");
         final CampaignEnumGroupQueryConditionVO conditionVO = EnumsComponent.
                 transformCampaignEnumGroupToConditionVO(pageNo, pageSize, groupCode);
         final PageInfo<SysEnumGroupDef> pageInfo = systemPserviceBO.getCampaignEnumGroup(conditionVO);
         //TODO gathering campaign type id list
         final List<Long> campaignGroupList = new ArrayList<Long>();
         final Hashtable<Long, List<Long>> groupRelHash = new Hashtable<Long, List<Long>>();
         if (ValidateUtil.isNotEmpty(pageInfo.getResult())) {
             for (SysEnumGroupDef groupdef : pageInfo.getResult())
             {
                 campaignGroupList.add(groupdef.getGroupId());
             }
         }
         /** filled up with related enum id */
         List<SysEnumGroupRel> groupRelList = systemPserviceBO.getSysEnumGroupRelListByGroupIds(campaignGroupList);
         if (ValidateUtil.isNotEmpty(groupRelList))
         {
             for (final SysEnumGroupRel groupRel : groupRelList)
             {
                 if (ValidateUtil.isNull(groupRelHash.get(groupRel.getGroupId())))
                 {
                     groupRelHash.put(groupRel.getGroupId(), new ArrayList<Long>());
                 }
                 
                 groupRelHash.get(groupRel.getGroupId()).add(groupRel.getEnumId());                 
             }
         }
         
         final PageInfo<UICampaignGroupDefVO> uiPageInfo = EnumsComponent.transformCampaignGroupPageInfoToUIPageInfo(pageInfo, groupRelHash);         
         LOG.debug("meo.campaign.group.list end!");
         return uiPageInfo;
     }
}
