package com.asiainfo.meo.system.common.app.repository;

import java.util.List;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysActionDef;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysActionRel;
import com.asiainfo.meo.system.common.app.model.entity.SysCampaignActionDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCategoryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureUnitExchangeRule;
import com.asiainfo.meo.system.common.app.model.entity.SysOperatorDef;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.common.app.model.entity.SysProvDef;
import com.asiainfo.meo.system.common.app.model.entity.SysRegionDef;
import com.asiainfo.meo.system.common.app.model.vo.CampaignEnumGroupQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefQueryConditionVO;


public interface CommonRepository {

	public List<SysCategoryDef> getCategoryDefsByParentCateId(Long parentCateId) throws MeoException;

	public SysCategoryDef getCategoryDefsByCateId(Long CateId) throws MeoException;

	public void CreateCategoryDefsByCateId(SysCategoryDef sysCategoryDef) throws MeoException;
    
	public void ModifyCategoryDefsByCateId(SysCategoryDef sysCategoryDef) throws MeoException;
    
	public List<SysEnumDef> getEnumDefByEnumType(String enumType) throws MeoException;
	
	public SysEnumDef getEnumDefByEnumCode(String enumCode, Integer enumType) throws MeoException;
	
	public SysEnumDef getEnumDefCampaignTypeByEnumId(Long enumId) throws MeoException;
	
	public List<SysEnumDef> getEnumDefByCode(String enumCode, String enumType) throws MeoException;
	
	public void createEnumDef(SysEnumDef sysEnumDef) throws MeoException;
	
	public void modifyEnumDef(SysEnumDef sysEnumDef) throws MeoException;
	
	public SysLanguageDef getLanguageDefByLanguageCode(Integer languageCode) throws MeoException;
	
	public void createLanguageDef(SysLanguageDef sysLanguageDef) throws MeoException;
	
	public void modifyLanguageDef(SysLanguageDef sysLanguageDef) throws MeoException;
	
	public void createSysOperatorDef(SysOperatorDef sysOperDef) throws MeoException;

    public void createSysPortalUser(SysPortalUser portalUser);

    public SysPortalUser getObjectByObjectId(Long objectId);

    public SysMeasureUnitExchangeRule getSysMeasureUnitExchangeRule(Integer oriMeasureId, Integer desMeasureId);
	
    public List<SysCategoryDef> getCategoryEnum(Long categoryId, Integer categoryType);
    
    public List<SysMeasureDef> getMeasureDef(Long measureId, Long measureTypeId);
    
    public List<SysActionDef> getActionDefine(Long actionId, Integer actionType);
    
    public List<SysEnumDef> getCampaginType(String campaignTypeId);
    
    public SysActionParamDef getSysActionParamDef(Long paramId);

    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode);

    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode);
    
    public List<SysCountryDef> getCountryDef(Long countryCode);
    
    public List<SysProvDef> getProvDef(Long provCode);
    
    public List<SysRegionDef> getRegionDef(Long regionCode);
    
    public CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo);
    
    public int addEntityParticipantCount(String campaignNo,int paticipant);
    
    public void createEntityParticipant(CmEntityParticipant parti);
    
    public List<SysActionParamDef> getSysActionParamDefByActionId(SysActionParamDefQueryConditionVO conditionVO,
            Integer pageSize, Integer pageNo);
    
    public Integer getSysActionParamDefByActionIdTotalSize(SysActionParamDefQueryConditionVO conditionVO);
    
    public SysEnumGroupDef createCampaignGroup(SysEnumGroupDef sysEnumGroupDef);
    
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode);
    
    public void createProductCategory(SysCategoryDef sysCategoryDef);
    
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds);
    
    public List<SysLanguageDef> getLanguageDef(Integer languageCode);
    
    public Long  getCampaignTaskParamValue(final String actionId, final Long campaignId);
    
    public Integer getCategoryEnumTotalSize(Long categoryId, Integer categoryType);
    
    public Integer getMeasureDefTotalSize(Long measureId, Long measureTypeId);
    
    public Integer getEnumDefByCodeTotalSize(String enumCode, String enumType);
    
    public Integer getCampaginTypeTotalSize(String campaignTypeId);
    
    public Integer getActionDefineTotalSize(Long actionId, Integer actionType);
    
    public Integer getLanguageDefTotalSize(Integer languageCode);
    
    public Integer getCountryDefTotalSize(Long countryCode);
    
    public List<SysCampaignActionDef> getSysCampaignActionDefList(Integer campaignType, Integer pageNo, Integer pageSize);
    
    public Integer getSysCampaignActionDefListTotalSize(Integer campaignType);
    
    public List<SysActionRel> getSysActionRefList(Integer relType);
    
    public SysActionDef getActionDefine(Long actionId);
    
    public void modifyCampaignGroup(SysEnumGroupDef sysEnumGroupDef);
    
    public Integer getCampaignEnumGroupTotalSize(CampaignEnumGroupQueryConditionVO conditionVO); 
    
    public List<SysEnumGroupDef> getCampaignEnumGroup(CampaignEnumGroupQueryConditionVO conditionVO,
                Integer pageSize, Integer pageNo);
    
    public List<SysEnumGroupDef> getCampaignEnumGroupByGroupID(Long groupID);
    
    public void deactivateSysEnumGroupRelByGroupId(final Long groupId);
    
    public SysEnumGroupDef getEnumGroupByGroupId(Long groupId);
    
}
