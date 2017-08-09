package com.asiainfo.meo.system.common.app.bo;



import java.io.IOException;
import java.util.List;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureUnitExchangeRule;
import com.asiainfo.meo.system.common.app.model.entity.SysProvDef;
import com.asiainfo.meo.system.common.app.model.entity.SysRegionDef;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.CampaignAction;
import com.asiainfo.meo.system.common.app.model.vo.CampaignEnumGroupQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.CampaignTypeDefine;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateVO;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumGroupDefVO;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefineVO;
import com.asiainfo.meo.system.common.app.model.vo.SysCategoryDefVO;
import com.asiainfo.meo.system.service.entity.vo.SysObjectVO;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;

public interface CommonBo {
    
    public void createEnumDef(EnumDefine enumDef) throws MeoException, IOException;
    
    public void modifyEnumDef(EnumDefine enumDef) throws MeoException;
    
    public void deactivateEnum(String enumCode) throws MeoException;
    
    public EnumDefine getEnumDefByEnumCode(String enumCode,Integer enumType) throws MeoException;
    
    public CategoryDefine getCategoryDefByCateId(Long categoryId) throws MeoException;
    
    public List<CategoryDefine> getCategoryDefByParentCateId(Long partnerCategoryId) throws MeoException;
    
    public void createCategoryDefine(CategoryDefine cateDef) throws MeoException;
    
    public void modifyCategoryDefine(CategoryDefine cateDef) throws MeoException;
    
    public void deactivateCategoryDefine(String cateId) throws MeoException;
    
    public String getLanguageNameByCode(Integer languageCode) throws MeoException;

    public SysObjectVO getObjectByObjectId(Long objectId);

    public Integer getObjectTypeByObjectId(Long objectId);
    
    public void createSysPortalUser(SysObjectVO object);
    
    public long currencyTransform(Integer oriUnit,Integer desUnit);

    public SysMeasureUnitExchangeRule getSysMeasureUnitExchangeRule(Integer oriMeasureId, Integer desMeasureId);
    
    public PageInfo<CategoryDefine> getCategoryEnumTotalSize(Long categoryId, Integer categoryType);
    
    public List<MeasureDefine> getMeasureDef(Long measureId, Long measureTypeId);
    
    public List<EnumDefine> getEnumType(String enumCode, String enumType);
    
    public List<ActionDefine> getActionDefine(Long actionId, Integer actionType);
   
    public SysActionParamDefineVO getSysActionParamDef(Long paramId);

    public List<CampaignTypeDefine> getCampaginType(String campaignTypeId);
    
    public OneTimePasswordVO generateOtp(SysOtpVO otp);

    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode);

    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode);

    public void setOTPToUsed(SysOtpVO sysOtp);
    
    public List<SysCountryDef> getCountryDef(Long countryCode);
    
    public List<SysProvDef> getProvDef(Long provCode);
    
    public List<SysRegionDef> getRegionDef(Long regionCode);
    
    public void createEntityParticipant(CmEntityParticipant paticipant);

    public CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo);

    public int addEntityParticipantCount(String campaignNo,int paticipant);
    
    public PageInfo<SysActionParamDef> getSysActionParamDefByActionId(
            SysActionParamDefQueryConditionVO conditionVO);
    
    public void createCampaignGroup(EnumGroupDefVO enumGroupDefVO);
    
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode);
    
    public void createProductCategory(SysCategoryDefVO sysCategoryDefVO);
    
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds);
    
    public ContractFileTemplateVO createContractFileTemplate(ContractFileTemplateVO contractFileTemplate);
    
    public ContractFileTemplateVO getContractFileTemplateVOByTemplateId(Long templateId);
    
    public PageInfo<ContractFileTemplateVO> getContractFileTemplateVOList(ContractFileTemplateQueryConditionVO conditionVO);
    
    public ContractFileTemplateVO modifyContractFileTemplate(ContractFileTemplateVO contractFileTemplate);
    
    public void deleteContractFileTemplate(Long templateId) throws MeoException;
    
    public List<SysLanguageDef> getLanguageDef(Integer languageCode);
    
    public Long getCampaignTaskParamValue(String actionId, Long campaignId);
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType);
    
    public PageInfo<MeasureDefine> getMeasureDefTotalSize(Long measureId, Long measureTypeId);
    
    public PageInfo<EnumDefine> getEnumTypeTotalSize(String enumCode, String enumType);
    
    public PageInfo<CampaignTypeDefine> getCampaginTypeTotalSize(String campaignTypeId);
    
    public PageInfo<ActionDefine> getActionDefineTotalSize(Long actionId, Integer actionType);
    
    public PageInfo<SysLanguageDef> getLanguageDefTotalSize(Integer languageCode);
    
    public PageInfo<SysCountryDef> getCountryDefTotalSize(Long countryCode);
    
    public PageInfo<CampaignAction> getActionTotalSizeByCampaginType(Integer campaginType, Integer pageNo, Integer pageSize);
    
    /**
     * get the sysEnumGroupRel by relId
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param relId
      * @return
     */
    public SysEnumGroupRel getSysEnumGroupRel(Long relId);
    
    /**
     * get the sysEnumGroupRel list by enumId and groupId
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param enumId
      * @param groupId
      * @return
     */
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumIdAndGroupId(Long enumId,Long groupId);
    
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds);
    
    public void modifyCampaignEnumGroup(EnumGroupDefVO enumGroupDefVO);
    
    public void deleteCampaignEnumGroup(Long groupDefID);
    
    public PageInfo<SysEnumGroupDef> getCampaignEnumGroup(CampaignEnumGroupQueryConditionVO conditionVO);
    
    public List<SysEnumGroupDef> getSysEnumGroupRelListByEnumId(Long enumId);
}
