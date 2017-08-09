package com.asiainfo.meo.web.component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.CampaignEnumGroupQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.CampaignTypeDefine;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumGroupDefVO;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.SysCategoryDefVO;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
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
import com.asiainfo.meo.web.customer.model.vo.UIEnumDefineVO;

public class EnumsComponent
{   
    @Resource
    CustomerPserviceBO custPserviceBo;
    //private static final Log LOG = LogFactory.getLog(EnumsComponent.class);
   
    /**
     * 
      * @Description: CategoryDefine映射为UICategoryInfoVO
      * @Description: transform CategoryDefine to UICategoryInfoVO
      * @modifyReason: 
      * @author 
      * @param catdefs 
      * @return
     */
    public static PageInfo<UICategoryInfoVO> transformPartnerProfileToPartnerProfileList(PageInfo<CategoryDefine> catdefs)
    {
        List<UICategoryInfoVO> categoryinfoList = new ArrayList<UICategoryInfoVO>();
        PageInfo<UICategoryInfoVO> pageInfo = new PageInfo<UICategoryInfoVO>();
        Integer totalSize = null;
        if (ValidateUtil.isNotEmpty(catdefs))
        {
            for (CategoryDefine catdef : catdefs.getResult())
            {
                UICategoryInfoVO categoryInfo = new UICategoryInfoVO();
                categoryInfo.setParentCateId(catdef.getParentCateId());
                categoryInfo.setName(catdef.getCategoryName());
                categoryInfo.setCategoryId(catdef.getCategoryId());
                categoryInfo.setIconURL(catdef.getIconURL());
                categoryinfoList.add(categoryInfo);
            }
            totalSize = catdefs.getTotalSize();
        }
        return pageInfo.createPageInfo(totalSize, categoryinfoList);
    }
    
    /**
     * 
      * @Description: CategoryDefine映射为UICategoryInfoVO
      * @Description: transform CategoryDefine to UICategoryInfoVO
      * @modifyReason: 
      * @author 
      * @param catdefs 
      * @return
     */
    public static PageInfo<UIProductCategoryInfoVO> transformProductCategoryToProductCategoryeList(PageInfo<CategoryDefine> catdefs)
    {
        List<UIProductCategoryInfoVO> categoryinfoList = new ArrayList<UIProductCategoryInfoVO>();
        PageInfo<UIProductCategoryInfoVO> pageInfo = new PageInfo<UIProductCategoryInfoVO>();
        Integer totalSize = null;
        if (ValidateUtil.isNotEmpty(catdefs))
        {
            for (CategoryDefine catdef : catdefs.getResult())
            {
                UIProductCategoryInfoVO categoryInfo = new UIProductCategoryInfoVO();
                categoryInfo.setProductCategory(catdef.getCategoryName());
                categoryInfo.setProductCateId(catdef.getCategoryId());
                categoryinfoList.add(categoryInfo);
            }
            totalSize = catdefs.getTotalSize();
        }
        return pageInfo.createPageInfo(totalSize, categoryinfoList);
    }

    /**
     * 
      * @Description: MeasureDefine映射为UIMeasureInfoVO
      * @Description: transform MeasureDefine to UIMeasureInfoVO
      * @modifyReason: 
      * @author 
      * @param measuredefs
      * @return
     */
    public static PageInfo<UITimeUnitInfoVO> transformMeasureDefineToTimeUnitinfoList(PageInfo<MeasureDefine> measuredefs)
    {
        PageInfo<UITimeUnitInfoVO> page = new PageInfo<UITimeUnitInfoVO>();
        List<UITimeUnitInfoVO> measureInfoList = new ArrayList<UITimeUnitInfoVO>();
        Integer totalSize = null;
        if (measuredefs!= null)
        {
            for (MeasureDefine catdef : measuredefs.getResult())
            {
                UITimeUnitInfoVO measureInfo = new UITimeUnitInfoVO();
                measureInfo.setTimeUnitId(catdef.getMeasureId());
                measureInfo.setTimeUnitName(catdef.getName());
                measureInfoList.add(measureInfo);
            }
            totalSize = measuredefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, measureInfoList);
    }
    
    /**
     * 
      * @Description: MeasureDefine映射为UIMeasureInfoVO
      * @Description: transform MeasureDefine to UIMeasureInfoVO
      * @modifyReason: 
      * @author 
      * @param measuredefs
      * @return
     */
    public static PageInfo<UIMeasureInfoVO> transformMeasureDefineToMeasureinfoList(PageInfo<MeasureDefine> measuredefs)
    {
        PageInfo<UIMeasureInfoVO> page = new PageInfo<UIMeasureInfoVO>();
        List<UIMeasureInfoVO> measureInfoList = new ArrayList<UIMeasureInfoVO>();
        Integer totalSize = null;
        if (ValidateUtil.isNotEmpty(measuredefs))
        {
            for (MeasureDefine catdef : measuredefs.getResult())
            {
                UIMeasureInfoVO measureInfo = new UIMeasureInfoVO();
                measureInfo.setMeasureId(catdef.getMeasureId());
                measureInfo.setMeasureName(catdef.getName());
                measureInfoList.add(measureInfo);
            }
            totalSize = measuredefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, measureInfoList);
    }

    /**
     * 
      * @Description: EnumDefine映射为UIEnumInfoVO
      * @Description: transform EnumDefine to UIEnumInfoVO
      * @modifyReason: 
      * @author 
      * @param enumdefs
      * @return
     */
    public static PageInfo<UIChargeUnitInfoVO> transformChargeUnitToChargeUnitinfoList(PageInfo<EnumDefine> enumdefs)
    {
        PageInfo<UIChargeUnitInfoVO> page = new PageInfo<UIChargeUnitInfoVO>();
        List<UIChargeUnitInfoVO> enumInfoList = new ArrayList<UIChargeUnitInfoVO>();
        Integer totalSize = null;
        if (enumdefs!= null)
        {
            for (EnumDefine enumdef : enumdefs.getResult())
            {
                UIChargeUnitInfoVO enumInfo = new UIChargeUnitInfoVO();
                enumInfo.setChargeUnit(enumdef.getEnumName());
                enumInfo.setChargeUnitId(enumdef.getEnumCode());
                enumInfoList.add(enumInfo);
            }
            totalSize = enumdefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, enumInfoList);
    }
    
    /**
     * 
      * @Description: CampaignTypeDefine映射为UICampaginTypeInfoVO
      * @Description: transform CampaignTypeDefine to UICampaginTypeInfoVO
      * @modifyReason: 
      * @author zhoujj
      * @param enumdefs 
      * @return
     */
    public static PageInfo<UICampaginTypeInfoVO> transformCampaignDefListToCampaignDefineList(PageInfo<CampaignTypeDefine> enumdefs)
    {
        PageInfo<UICampaginTypeInfoVO> page = new PageInfo<UICampaginTypeInfoVO>();
        List<UICampaginTypeInfoVO> enumInfoList = new ArrayList<UICampaginTypeInfoVO>();
        Integer totalSize = null;
        if (enumdefs!= null)
        {
            for (CampaignTypeDefine enumdef : enumdefs.getResult())
            {
                UICampaginTypeInfoVO enumInfo = new UICampaginTypeInfoVO();
                enumInfo.setCampaignTypeId(enumdef.getCampaignTypeId());
                enumInfo.setCampaignTypeName(enumdef.getCampaignTypeName());
                enumInfoList.add(enumInfo);
            }
            totalSize = enumdefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, enumInfoList);
    }

    /**
     * 
      * @Description: ActionDefine映射为UIActionInfoVO
      * @Description: transform ActionDefine to UIActionInfoVO
      * @modifyReason: 
      * @author 
      * @param actiondefs
      * @return
     */
    public static PageInfo<UIActionInfoVO> transformActionDefineToActioninfoList(PageInfo<ActionDefine> actiondefs)
    {
        PageInfo<UIActionInfoVO> page = new PageInfo<UIActionInfoVO>();
        List<UIActionInfoVO> actionInfoList = new ArrayList<UIActionInfoVO>();
        Integer totalSize = null;
        if (actiondefs!= null)
        {
            for (ActionDefine actdef : actiondefs.getResult())
            {
                UIActionInfoVO actioninfo = new UIActionInfoVO();
                actioninfo.setActionName(actdef.getActionName());
                actioninfo.setAtcionId(actdef.getAtcionId());
                actionInfoList.add(actioninfo);
            }
            totalSize = actiondefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, actionInfoList);
    }

    public static PageInfo<UIPartnerTypeInfoVO> transformEnumDefineToPartnerTypeinfoList(PageInfo<EnumDefine> enumdefs)
    {
        PageInfo<UIPartnerTypeInfoVO> page = new PageInfo<UIPartnerTypeInfoVO>();
        List<UIPartnerTypeInfoVO> enumInfoList = new ArrayList<UIPartnerTypeInfoVO>();
        Integer totalSize = null;
        if (enumdefs!= null)
        {
            for (EnumDefine enumdef : enumdefs.getResult())
            {
                UIPartnerTypeInfoVO enumInfo = new UIPartnerTypeInfoVO();
                enumInfo.setPartnerTypeId(enumdef.getEnumCode());
                enumInfo.setPartnerType(enumdef.getEnumName());
                enumInfoList.add(enumInfo);
            }
            totalSize = enumdefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, enumInfoList);
    }

    public static int checkActionType(Integer actionType)
    {
        Integer actionTypeDefine = null;
        if (actionType == ActionDefine.REWARD_TYPE)
        {
            actionTypeDefine = ActionDefine.REWARD_TYPE;
        }else if (actionType == ActionDefine.USER_TYPE)
        {
            actionTypeDefine = ActionDefine.USER_TYPE;
        }else
        {
            throw new MeoException(SysErrorCodeDefine.ACTION_TYPE_DO_NOT_EXISTED, new Object[]{actionType});
        }
        return actionTypeDefine;
    }

    public static List<UIServiceTypeInfoVO> transformEnumDefineToServiceTypeinfoList(List<EnumDefine> enumdefs)
    {
        List<UIServiceTypeInfoVO> enumInfoList = new ArrayList<UIServiceTypeInfoVO>();
        
        if (enumdefs!= null&& enumdefs.size()> 0)
        {
            for (EnumDefine enumdef : enumdefs)
            {
                UIServiceTypeInfoVO enumInfo = new UIServiceTypeInfoVO();
                enumInfo.setServiceTypeId(enumdef.getEnumCode());
                enumInfo.setServiceType(enumdef.getEnumName());
                enumInfoList.add(enumInfo);
            }
        }
        return enumInfoList;
    }

    public static PageInfo<UIDenominationInfoVO> transformEnumDefineToDenominationInfoList(PageInfo<EnumDefine> enumdefs)
    {
        PageInfo<UIDenominationInfoVO> page = new PageInfo<UIDenominationInfoVO>();
        List<UIDenominationInfoVO> enumInfoList = new ArrayList<UIDenominationInfoVO>();
        Integer totalSize = null;
        if (enumdefs!= null)
        {
            for (EnumDefine enumdef : enumdefs.getResult())
            {
                UIDenominationInfoVO enumInfo = new UIDenominationInfoVO();
                enumInfo.setDenominationUnitId(Integer.valueOf(enumdef.getEnumCode()));
                enumInfo.setDenominationUnit(enumdef.getEnumName());
                enumInfoList.add(enumInfo);
            }
            totalSize = enumdefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, enumInfoList);
    }

    public static PageInfo<UIActionParamDefVO> transformActionParamDefToUIActionParamDefVO(
            PageInfo<SysActionParamDef> pageInfo)
    {
        final PageInfo<UIActionParamDefVO> uiPageInfo = new PageInfo<UIActionParamDefVO>();
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UIActionParamDefVO> uiActionParamDefVOList = new ArrayList<UIActionParamDefVO>();
        for (SysActionParamDef sysActionParamDef : pageInfo.getResult())
        {
            UIActionParamDefVO uiActionParamDefVO = new UIActionParamDefVO();
            BeanUtils.copyProperties(sysActionParamDef, uiActionParamDefVO);
            uiActionParamDefVOList.add(uiActionParamDefVO);
        }
        uiPageInfo.setResult(uiActionParamDefVOList);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        return uiPageInfo;
    }
    
    public static SysActionParamDefQueryConditionVO transformActionParamDefCritriaToQueryConditionVO(
            final String actionId,
            final Integer pageNo,
            final Integer pageSize)
    {
        final SysActionParamDefQueryConditionVO conditionVO = new SysActionParamDefQueryConditionVO();
        conditionVO.setActionId(actionId);
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        return conditionVO;
    }

    public static PageInfo<UIEnumDefineVO> transformEnumDefineToEnumDefineVOList(PageInfo<EnumDefine> enumdefs)
    {
      PageInfo<UIEnumDefineVO> page = new PageInfo<UIEnumDefineVO>();       
      List<UIEnumDefineVO> enumInfoList = new ArrayList<UIEnumDefineVO>();
        Integer totalSize = null;
        if (enumdefs!= null)
        {
            for (EnumDefine enumdef : enumdefs.getResult())
            {
                UIEnumDefineVO enumInfo = new UIEnumDefineVO();
                enumInfo.setEnumId(enumdef.getEnumId());
                enumInfo.setEnumCode(enumdef.getEnumCode());
                enumInfo.setEnumName(enumdef.getEnumName());
                enumInfoList.add(enumInfo);
            }
            totalSize = enumdefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, enumInfoList);
    }

    public static com.asiainfo.meo.system.common.app.model.vo.EnumGroupDefVO transformUIEnumGroupDefVOToEnumGroupDefVO(
            UIEnumGroupDefVO uiEnumGroupDefVO)
    {
        EnumGroupDefVO enumGroupDefVO = new EnumGroupDefVO();
        BeanUtils.copyProperties(uiEnumGroupDefVO, enumGroupDefVO);
        return enumGroupDefVO;
    }
    
    public static com.asiainfo.meo.system.common.app.model.vo.EnumGroupDefVO transformUICampaignGroupDefVOToEnumGroupDefVO(
            UICampaignGroupDefVO uiEnumGroupDefVO)
    {
        EnumGroupDefVO enumGroupDefVO = new EnumGroupDefVO();
        if(uiEnumGroupDefVO==null)
        {
            return enumGroupDefVO;
        }
        BeanUtils.copyProperties(uiEnumGroupDefVO, enumGroupDefVO);
        enumGroupDefVO.setCampaignTypeIds(uiEnumGroupDefVO.getCampaignTypeIds());
        return enumGroupDefVO;
    }

    public static SysCategoryDefVO transformUISysCategoryDefVOToSysCategoryDefVO(UICategoryDefVO uiCategoryDefVO)
    {
        SysCategoryDefVO sysCategoryDefVO = new SysCategoryDefVO();
        BeanUtils.copyProperties(uiCategoryDefVO, sysCategoryDefVO);
        return sysCategoryDefVO;
    }

    public static PageInfo<UILanguageVO> transformSysLanguageDefToUILanguageVOList(PageInfo<SysLanguageDef> languageDefs)
    {
        PageInfo<UILanguageVO> page = new PageInfo<UILanguageVO>();
        List<UILanguageVO> uiLanguageVOList = new ArrayList<UILanguageVO>();
        Integer totalSize = null;
        if (languageDefs != null)
        {
            for (SysLanguageDef sysLanguageDef : languageDefs.getResult())
            {
                UILanguageVO uiLanguageVO = new UILanguageVO();
                uiLanguageVO.setLanguageCode(sysLanguageDef.getLanguareCode());
                uiLanguageVO.setName(sysLanguageDef.getName());
                uiLanguageVOList.add(uiLanguageVO);
            }
            totalSize = languageDefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, uiLanguageVOList);
    }

    public static PageInfo<UICountryVO> transformSysCountryDefToUICountryVOList(PageInfo<SysCountryDef> countryDefs)
    {
        PageInfo<UICountryVO> page = new PageInfo<UICountryVO>();
        List<UICountryVO> uiCountryVOList = new ArrayList<UICountryVO>();
        Integer totalSize = null;
        if (countryDefs != null)
        {
            for (SysCountryDef sysCountryDef : countryDefs.getResult())
            {
                UICountryVO uiCountryVO = new UICountryVO();
                uiCountryVO.setCountryCode(sysCountryDef.getCountryCode());
                uiCountryVO.setName(sysCountryDef.getName());
                uiCountryVOList.add(uiCountryVO);
            }
            totalSize = countryDefs.getTotalSize();
        }
        return page.createPageInfo(totalSize, uiCountryVOList);
    }
    
    public static CampaignEnumGroupQueryConditionVO transformCampaignEnumGroupToConditionVO(
            final Integer pageNo,
            final Integer pageSize,
            final String groupCode)
    {
       final CampaignEnumGroupQueryConditionVO vo = new CampaignEnumGroupQueryConditionVO();
       vo.setPageNo(pageNo);
       vo.setPageSize(pageSize);
       vo.setGroupCode(groupCode);
       return vo;
    }
    
    public static PageInfo<UICampaignGroupDefVO> transformCampaignGroupPageInfoToUIPageInfo(
            final PageInfo<SysEnumGroupDef> pageInfo,
            final Hashtable<Long,List<Long>> groupRelMap)
    {
        final PageInfo<UICampaignGroupDefVO> uiPageInfo = new PageInfo<UICampaignGroupDefVO>();
        if (pageInfo == null || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UICampaignGroupDefVO> uiList = new ArrayList<UICampaignGroupDefVO>();
        for (final SysEnumGroupDef vo :  pageInfo.getResult())
        {
            final UICampaignGroupDefVO uiVO = new UICampaignGroupDefVO();
            BeanUtils.copyProperties(vo, uiVO);
            uiVO.setCampaignTypeIds(groupRelMap.get(uiVO.getGroupId()));
            uiList.add(uiVO);
        }
        return uiPageInfo.createPageInfo(pageInfo.getTotalSize(), uiList);
    }

}
