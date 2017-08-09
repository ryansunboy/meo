package com.asiainfo.meo.system.common.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
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
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureUnitExchangeRule;
import com.asiainfo.meo.system.common.app.model.entity.SysOperatorDef;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.common.app.model.entity.SysProvDef;
import com.asiainfo.meo.system.common.app.model.entity.SysRegionDef;
import com.asiainfo.meo.system.common.app.model.vo.CampaignEnumGroupQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefQueryConditionVO;
import com.asiainfo.meo.system.common.app.repository.CommonRepository;
import com.asiainfo.meo.system.define.SysConstantDefine;

public class CommonRepositoryImpl implements CommonRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_ENUM_GROUP_DEF_ID = "SEQ_ENUM_GROUP_DEF_ID";
    
//    private static final String SEQ_CATEGORY_DEF_ID = "SEQ_CATEGORY_DEF_ID";
    
    public List<SysCategoryDef> getCategoryDefsByParentCateId(Long parentCateId) throws MeoException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCategoryDef.class);
        criteria.add(Restrictions.eq("parentCateId", parentCateId));
        // criteria.add(Restrictions.eq("sts", EnumCodeDefine.VALID));
        return (List<SysCategoryDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public SysCategoryDef getCategoryDefsByCateId(Long cateId) throws MeoException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCategoryDef.class);
        criteria.add(Restrictions.eq("categoryId", cateId));
        // criteria.add(Restrictions.eq("sts", EnumCodeDefine.VALID));
        return (SysCategoryDef) hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public void CreateCategoryDefsByCateId(SysCategoryDef sysCategoryDef) throws MeoException
    {
        // TODO Auto-generated method stub
        
    }
    
    public void ModifyCategoryDefsByCateId(SysCategoryDef sysCategoryDef) throws MeoException
    {
        // TODO Auto-generated method stub
        
    }
    
    public List<SysEnumDef> getEnumDefByEnumType(String enumType) throws MeoException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCategoryDef.class).add(Restrictions.eq("enumType", enumType));
        return (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public SysEnumDef getEnumDefByEnumCode(String enumCode, Integer enumType) throws MeoException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        criteria.add(Restrictions.eq("enumCode", enumCode));
        criteria.add(Restrictions.eq("enumType", enumType.toString()));
        // criteria.add(Restrictions.eq("sts", EnumCodeDefine.VALID));
        List<SysEnumDef> enumDefs = (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(enumDefs))
        {
            return enumDefs.get(0);
        }
        return null;
    }
    
    @Override
    public SysEnumDef getEnumDefCampaignTypeByEnumId(Long enumId) throws MeoException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        criteria.add(Restrictions.eq("sts", SysEnumDef.STS_ENUM_ACTIVATE));
        criteria.add(Restrictions.eq("enumType", SysEnumDef.CAMPAGIN_TYPE));
        if (ValidateUtil.isNull(enumId)) {
            return null;
        }
        criteria.add(Restrictions.eq("enumId", enumId));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public void createEnumDef(SysEnumDef sysEnumDef) throws MeoException
    {
        // hibernateRepository.saveObject(sysEnumDef);
        
    }
    
    public void modifyEnumDef(SysEnumDef sysEnumDef) throws MeoException
    {
        // hibernateRepository.updateObject(sysEnumDef);
        
    }
    
    public SysLanguageDef getLanguageDefByLanguageCode(Integer languageCode) throws MeoException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysLanguageDef.class);
        criteria.add(Restrictions.eq("languareCode", languageCode));
        // criteria.add(Restrictions.eq("sts", EnumCodeDefine.VALID));
        return (SysLanguageDef) hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public void createLanguageDef(SysLanguageDef sysLanguageDef) throws MeoException
    {
        // TODO Auto-generated method stub
        
    }
    
    public void modifyLanguageDef(SysLanguageDef sysLanguageDef) throws MeoException
    {
        // TODO Auto-generated method stub
        
    }
    
    public void createSysOperatorDef(SysOperatorDef sysOperDef) throws MeoException
    {
        // TODO Auto-generated method stub
        
    }
    
    public SysPortalUser getObjectByObjectId(Long objectId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPortalUser.class);
        criteria.add(Restrictions.eq("objectId", objectId));
        criteria.add(Restrictions.eq("sts", SysPortalUser.STS_ACTIVATE));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
        
    }
    
    public void createSysPortalUser(SysPortalUser portalUser)
    {
        if (ValidateUtil.isNotEmpty(portalUser))
        {
            portalUser.setCreateDate(DateTimeUtil.getNow());
            portalUser.setSts(SysPortalUser.STS_ACTIVATE);
            portalUser.setUserId(SequenceUtil.getSequence(SysPortalUser.SEQ_SYS_PORTAL_USER));
            hibernateRepository.saveObject(portalUser);
        }
        
    }
    
    public SysMeasureUnitExchangeRule getSysMeasureUnitExchangeRule(Integer oriMeasureId, Integer desMeasureId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMeasureUnitExchangeRule.class);
        criteria.add(Restrictions.eq("measureId", oriMeasureId));
        criteria.add(Restrictions.eq("destMeasureId", desMeasureId));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public List<SysCategoryDef> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCategoryDef.class);
        if (ValidateUtil.isNotNull(categoryId))
        {
            criteria.add(Restrictions.eq("categoryId", categoryId));
        }
        criteria.add(Restrictions.eq("categoryType", categoryType));
        return (List<SysCategoryDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public Integer getCategoryEnumTotalSize(Long categoryId, Integer categoryType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCategoryDef.class);
        if (ValidateUtil.isNotNull(categoryId))
        {
            criteria.add(Restrictions.eq("categoryId", categoryId));
        }
        criteria.add(Restrictions.eq("categoryType", categoryType));
        List<SysCategoryDef> list = (List<SysCategoryDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }

    public List<SysMeasureDef> getMeasureDef(Long measureId, Long measureTypeId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMeasureDef.class);
        if (ValidateUtil.isNotNull(measureId))
        {
            criteria.add(Restrictions.eq("measureId", measureId));// 1
        }
        criteria.add(Restrictions.eq("measureTypeId", measureTypeId));// 1
        return (List<SysMeasureDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public Integer getMeasureDefTotalSize(Long measureId, Long measureTypeId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMeasureDef.class);
        if (ValidateUtil.isNotNull(measureId))
        {
            criteria.add(Restrictions.eq("measureId", measureId));// 1
        }
        criteria.add(Restrictions.eq("measureTypeId", measureTypeId));// 1
        List<SysMeasureDef> list = (List<SysMeasureDef>) hibernateRepository.findByCriteria(criteria);
        
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    public List<SysActionDef> getActionDefine(Long actionId, Integer actionType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysActionDef.class);
        if (ValidateUtil.isNotNull(actionId))
        {
            criteria.add(Restrictions.eq("actionId", actionId));
        }
        if (ValidateUtil.isNotNull(actionType))
        {
            criteria.add(Restrictions.eq("actionType", actionType));// 1
        }
        return (List<SysActionDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public Integer getActionDefineTotalSize(Long actionId, Integer actionType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysActionDef.class);
        if (ValidateUtil.isNotNull(actionId))
        {
            criteria.add(Restrictions.eq("actionId", actionId));
        }
        if (ValidateUtil.isNotNull(actionType))
        {
            criteria.add(Restrictions.eq("actionType", actionType));// 1
        }
        List<SysActionDef> list = (List<SysActionDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    public SysActionParamDef getSysActionParamDef(Long paramId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysActionParamDef.class).add(Restrictions.eq("paramId", paramId));
        List<SysActionParamDef> list = (List<SysActionParamDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list) ? list.get(0) : null;
    }
    
    private DetachedCriteria getSysActionParamDefDetachedCriteria(
            final SysActionParamDefQueryConditionVO conditionVO)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysActionParamDef.class);
        criteria.add(Restrictions.eq("sts", SysActionParamDef.STS_VALID));
        if (ValidateUtil.isNotNull(conditionVO))
        {
            if (ValidateUtil.isNotEmpty(conditionVO.getActionId()))
            {
                criteria.add(Restrictions.eq("actionId", conditionVO.getActionId()));
            }
        }
        
        return criteria;
    }
    
    @Override
    public Integer getSysActionParamDefByActionIdTotalSize(
            SysActionParamDefQueryConditionVO conditionVO)
    {
        Integer totalSize = null;
        DetachedCriteria criteria = getSysActionParamDefDetachedCriteria(conditionVO);
        final List<SysActionParamDef> list = (List<SysActionParamDef>) 
                hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            totalSize = list.size();
        }
        return totalSize;
    }
    
    public List<SysActionParamDef> getSysActionParamDefByActionId(
            final SysActionParamDefQueryConditionVO conditionVO, 
            final Integer pageSize,
            final Integer pageNo)
    {
        DetachedCriteria criteria = getSysActionParamDefDetachedCriteria(conditionVO);
        return (List<SysActionParamDef>) hibernateRepository.findByCriteria(criteria, 
                (pageNo - 1) * pageSize, pageSize);
    }
    
    public List<SysEnumDef> getCampaginType(String campaignTypeId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        criteria.add(Restrictions.eq("sts", SysEnumDef.STS_ENUM_ACTIVATE));
        if (ValidateUtil.isNotNull(campaignTypeId))
        {
            criteria.add(Restrictions.eq("enumCode", campaignTypeId));
        }
        criteria.add(Restrictions.eq("enumType", SysEnumDef.CAMPAGIN_TYPE));
        return (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public Integer getCampaginTypeTotalSize(String campaignTypeId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        if (ValidateUtil.isNotNull(campaignTypeId))
        {
            criteria.add(Restrictions.eq("enumCode", campaignTypeId));
        }
        criteria.add(Restrictions.eq("enumType", SysEnumDef.CAMPAGIN_TYPE));
        List<SysEnumDef> list = (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysConfigurationDef.class);
        if (ValidateUtil.isNotEmpty(configValueCode))
        {
            criteria.add(Restrictions.eq("configValueCode", configValueCode));
            return (SysConfigurationDef) hibernateRepository.findUniqueObjectByCriteria(criteria);
        }
        return null;
        
    }
    
    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysConfigurationDef.class);
        if (ValidateUtil.isNotEmpty(configCode))
        {
            criteria.add(Restrictions.eq("configCode", configCode));
            return (List<SysConfigurationDef>) hibernateRepository.findByCriteria(criteria);
        }
        return null;
        
    }
    
    @Override
    public List<SysEnumDef> getEnumDefByCode(String enumCode, String enumType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        if (ValidateUtil.isNotEmpty(enumCode))
        {
            criteria.add(Restrictions.eq("enumCode", enumCode));
        }
        criteria.add(Restrictions.eq("enumType", enumType));
        return (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public Integer getEnumDefByCodeTotalSize(String enumCode, String enumType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        if (ValidateUtil.isNotEmpty(enumCode))
        {
            criteria.add(Restrictions.eq("enumCode", enumCode));
        }
        criteria.add(Restrictions.eq("enumType", enumType));
        List<SysEnumDef> list = (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    @Override
    public List<SysCountryDef> getCountryDef(Long countryCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCountryDef.class);
        if (ValidateUtil.isNotNull(countryCode))
        {
            criteria.add(Restrictions.eq("countryCode", countryCode));
        }
        return (List<SysCountryDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public Integer getCountryDefTotalSize(Long countryCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCountryDef.class);
        if (ValidateUtil.isNotNull(countryCode))
        {
            criteria.add(Restrictions.eq("countryCode", countryCode));
        }
        List<SysCountryDef> list = (List<SysCountryDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    @Override
    public List<SysProvDef> getProvDef(Long provCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysProvDef.class);
        if (ValidateUtil.isNotNull(provCode))
        {
            criteria.add(Restrictions.eq("provCode", provCode));
        }
        return (List<SysProvDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public List<SysRegionDef> getRegionDef(Long regionCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysRegionDef.class);
        if (ValidateUtil.isNotNull(regionCode))
        {
            criteria.add(Restrictions.eq("regionCode", regionCode));
        }
        return (List<SysRegionDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    public CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CmEntityParticipant.class);
        criteria.add(Restrictions.eq("sts", CmEntityParticipant.STS_ACTIVE));
        criteria.add(Restrictions.eq("entityId", campaignNo));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public int addEntityParticipantCount(String campaignNo,int paticipant)
    {
        if (ValidateUtil.isNull(campaignNo))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{"campaignNo"});
        }
        Object[] objs = new Object[]{campaignNo, CmEntityParticipant.STS_ACTIVE, paticipant};
        return hibernateRepository.executeByNameSql(SysConstantDefine.UPDATE_PARTICIPANT, objs);
        
    }
    
    public void createEntityParticipant(CmEntityParticipant parti)
    {
        if (ValidateUtil.isEmpty(parti))
        {
            return;
        }
        parti.setCreateDate(DateTimeUtil.getNow());
        parti.setSts(CmEntityParticipant.STS_ACTIVE);
        parti.setPartiId(SequenceUtil.getSequence(CmEntityParticipant.CM_ENTITY_PARTICIPANT_SEQ));
        hibernateRepository.saveObject(parti);
    }

    @Override
    public SysEnumGroupDef createCampaignGroup(SysEnumGroupDef sysEnumGroupDef)
    {
        Long groupId = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_ENUM_GROUP_DEF_ID);
        sysEnumGroupDef.setGroupId(groupId);
        hibernateRepository.saveObject(sysEnumGroupDef);
        return sysEnumGroupDef;
    }

    @Override
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupDef.class);
        criteria.add(Restrictions.eq("groupCode", groupCode))
        .add(Restrictions.eq("groupType", SysEnumGroupDef.CAMPAIGN_TYPE));
        // criteria.add(Restrictions.eq("sts", EnumCodeDefine.VALID));
        return (List<SysEnumGroupDef>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public void createProductCategory(SysCategoryDef sysCategoryDef)
    {
//        Long categoryId = ((Sequence)ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_CATEGORY_DEF_ID);
//        sysCategoryDef.setCategoryId(categoryId);
        hibernateRepository.saveObject(sysCategoryDef);
    }
    
    @Override
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds)
    {
        if(ValidateUtil.isEmpty(enumIds))
        {
            return null;
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumDef.class);
        criteria.add(Restrictions.eq("sts", SysEnumDef.STS_ENUM_ACTIVATE));
        criteria.add(Restrictions.in("id", enumIds));
        return (List<SysEnumDef>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<SysLanguageDef> getLanguageDef(Integer languageCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysLanguageDef.class);
        if (ValidateUtil.isNotEmpty(languageCode))
        {
            criteria.add(Restrictions.eq("languareCode", languageCode));    
        }
        List<SysLanguageDef> list = (List<SysLanguageDef>) hibernateRepository.findByCriteria(criteria);
        return list;
    }
    
    public Integer getLanguageDefTotalSize(Integer languageCode)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysLanguageDef.class);
        if (ValidateUtil.isNotEmpty(languageCode))
        {
            criteria.add(Restrictions.eq("languareCode", languageCode));    
        }
        List<SysLanguageDef> list = (List<SysLanguageDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public Long getCampaignTaskParamValue(String actionId, Long campaignId)
    {        
        List<Object> vs = (List<Object>) hibernateRepository.findByNamedQuery("getCampaignTaskParamValue",
                    actionId, campaignId, actionId);
        if (ValidateUtil.isNotEmpty(vs))
        {
            Long paramValue = null;
            if (ValidateUtil.isNotNull(vs.get(0)) || ValidateUtil.isNotEmpty(vs.get(0)))
            {
                paramValue = new Long(vs.get(0).toString());
            }
            return paramValue;
        }
        return null;
    }

    @Override
    public List<SysCampaignActionDef> getSysCampaignActionDefList(Integer campaignType, Integer pageNo, Integer pageSize)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCampaignActionDef.class);
        criteria.add(Restrictions.eq("campaignType", campaignType));
        criteria.add(Restrictions.eq("sts", SysCampaignActionDef.STS_VALID));
        return (List<SysCampaignActionDef>) hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public List<SysActionRel> getSysActionRefList(Integer relType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysActionRel.class);
        criteria.add(Restrictions.eq("relType", relType));
        criteria.add(Restrictions.eq("sts", SysActionRel.STS_VALID));
        return (List<SysActionRel>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public Integer getSysCampaignActionDefListTotalSize(Integer campaignType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCampaignActionDef.class);
        criteria.add(Restrictions.eq("campaignType", campaignType)); 
        criteria.add(Restrictions.eq("sts", SysCampaignActionDef.STS_VALID));
        List<SysCampaignActionDef> list = (List<SysCampaignActionDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public SysActionDef getActionDefine(Long actionId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysActionDef.class);
        criteria.add(Restrictions.eq("actionId", actionId));
        return (SysActionDef) hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public List<SysEnumGroupDef> getCampaignEnumGroup(CampaignEnumGroupQueryConditionVO conditionVO,
            Integer pageSize, Integer pageNo)
    {
        final DetachedCriteria criteria = getCampaignEnumGroupDetachedCriteria(conditionVO);
        return (List<SysEnumGroupDef>) hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }
    
    private DetachedCriteria getCampaignEnumGroupDetachedCriteria(final CampaignEnumGroupQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupDef.class);
        criteria.add(Restrictions.eq("sts", SysEnumGroupDef.STS_VALID));
        criteria.add(Restrictions.eq("groupType", SysEnumGroupDef.CAMPAIGN_TYPE));
        if (ValidateUtil.isNotNull(conditionVO))
        {
            if (ValidateUtil.isNotEmpty(conditionVO.getGroupCode()))
            {
                criteria.add(Restrictions.eq("groupCode", conditionVO.getGroupCode()));
            }
            //TODO more criteria of campaign enum group place here
            
        }
        return criteria;
    }
    
    @Override
    public Integer getCampaignEnumGroupTotalSize(CampaignEnumGroupQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria = getCampaignEnumGroupDetachedCriteria(conditionVO);
        final List<SysEnumGroupDef> list = (List<SysEnumGroupDef>) hibernateRepository.findByCriteria(criteria);        
        return (ValidateUtil.isNull(list)) ? 0 : list.size() ;
    }
    
    @Override
    public void modifyCampaignGroup(SysEnumGroupDef sysEnumGroupDef)
    {
        sysEnumGroupDef.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(sysEnumGroupDef);
    }
    
    @Override
    public List<SysEnumGroupDef> getCampaignEnumGroupByGroupID(Long groupID)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupDef.class);
        criteria.add(Restrictions.eq("groupId", groupID));
        criteria.add(Restrictions.eq("groupType", SysEnumGroupDef.CAMPAIGN_TYPE));
        criteria.add(Restrictions.eq("sts", SysEnumGroupDef.STS_VALID));
        return (List<SysEnumGroupDef>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public void deactivateSysEnumGroupRelByGroupId(Long groupId)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupRel.class);
        criteria.add(Restrictions.eq("sts", SysEnumGroupRel.STS_VALID));
        criteria.add(Restrictions.eq("groupId", groupId));
        final List<SysEnumGroupRel> list = (List<SysEnumGroupRel>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            for (final SysEnumGroupRel sysEnumGroupRel : list)
            {
                sysEnumGroupRel.setSts(SysEnumGroupRel.STS_INVALID);
                hibernateRepository.updateObject(sysEnumGroupRel);
            }
            
        }
    }

    @Override
    public SysEnumGroupDef getEnumGroupByGroupId(Long groupId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysEnumGroupDef.class);
        criteria.add(Restrictions.eq("groupId", groupId));
        criteria.add(Restrictions.eq("sts", SysEnumGroupDef.STS_VALID));
        return (SysEnumGroupDef) hibernateRepository.findUniqueObjectByCriteria(criteria);
    }    

}
