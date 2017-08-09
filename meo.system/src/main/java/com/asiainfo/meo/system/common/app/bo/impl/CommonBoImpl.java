package com.asiainfo.meo.system.common.app.bo.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.VLongWritable;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.common.app.bo.CommonBo;
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
import com.asiainfo.meo.system.common.app.model.entity.SysFileTemplate;
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
import com.asiainfo.meo.system.common.app.repository.CommonRepository;
import com.asiainfo.meo.system.common.app.repository.SysEnumGroupRelRepository;
import com.asiainfo.meo.system.common.app.repository.SysFileTemplateRepository;
import com.asiainfo.meo.system.component.CommonComponent;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysObjectVO;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;

public class CommonBoImpl implements CommonBo
{
    
    private static final Log          LOG = LogFactory.getLog(CommonBoImpl.class);
    
    @Resource
    private CommonComponent           sysComponent;

    @Resource
    private CommonRepository          sysRepository;
    
    @Resource
    private SysFileTemplateRepository sysFileTemplateRepository;
    
    @Resource
    private SysEnumGroupRelRepository enumGroupRelRepository;
    
    
    public void createEnumDef(EnumDefine enumDef) throws MeoException, IOException
    {
        LOG.info("begin to invoke method:[checkCreateEnumDefParam]");
        sysComponent.checkCreateEnumDefParam(enumDef);
        LOG.info("begin to invoke method:[createEnumDef]");
        sysComponent.createEnumDef(enumDef);
    }
    
    public void modifyEnumDef(EnumDefine enumDef) throws MeoException
    {
        
    }
    
    public void deactivateEnum(String enumCode) throws MeoException
    {
        
    }
    
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType) throws MeoException
    {
        return sysComponent.getEnumDefByEnumCode(enumCode, enumType);
    }
    
    public CategoryDefine getCategoryDefByCateId(Long categoryId) throws MeoException
    {
        
        return sysComponent.getCategoryDefByCateId(categoryId);
        
    }
    
    public List<CategoryDefine> getCategoryDefByParentCateId(Long partnerCategoryId) throws MeoException
    {
        
        return sysComponent.getCateDefByParentCateId(partnerCategoryId);
    }
    
    public void createCategoryDefine(CategoryDefine cateDef) throws MeoException
    {
        
    }
    
    public void modifyCategoryDefine(CategoryDefine cateDef) throws MeoException
    {
        
    }
    
    public void deactivateCategoryDefine(String cateId) throws MeoException
    {
        
    }
    
    public String getLanguageNameByCode(Integer languageCode) throws MeoException
    {
        
        return sysComponent.getLanguageNameByCode(languageCode);
    }
    
    public SysObjectVO getObjectByObjectId(Long objectId)
    {
        
        return sysComponent.getObjectByObjectId(objectId);
    }
    
    public Integer getObjectTypeByObjectId(Long objectId)
    {
        
        return sysComponent.getObjectTypeByObjectId(objectId);
    }
    
    public void createSysPortalUser(SysObjectVO object)
    {
        sysComponent.createSysPortalUser(object);
        
    }
    
    public long currencyTransform(Integer oriUnit, Integer desUnit)
    {
        return desUnit;
    }
    
    public SysMeasureUnitExchangeRule getSysMeasureUnitExchangeRule(Integer oriMeasureId, Integer desMeasureId)
    {
        return sysRepository.getSysMeasureUnitExchangeRule(oriMeasureId, desMeasureId);
    }
    
    public PageInfo<CategoryDefine> getCategoryEnumTotalSize(Long categoryId, Integer categoryType)
    {
        return sysComponent.getCategoryEnumTotalSize(categoryId, categoryType);
    }
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        return sysComponent.getCategoryEnum(categoryId, categoryType);
    }
    
    public List<MeasureDefine> getMeasureDef(Long measureId, Long measureTypeId)
    {
        return sysComponent.getMeasureDef(measureId, measureTypeId);
    }
    
    public PageInfo<MeasureDefine> getMeasureDefTotalSize(Long measureId, Long measureTypeId)
    {
        return sysComponent.getMeasureDefTotalSize(measureId, measureTypeId);
    }
    
    public List<ActionDefine> getActionDefine(Long actionId, Integer actionType)
    {
        return sysComponent.getActionDefine(actionId, actionType);
    }
    
    public PageInfo<ActionDefine> getActionDefineTotalSize(Long actionId, Integer actionType)
    {
        return sysComponent.getActionDefineTotalSize(actionId, actionType);
    }
    
    public SysActionParamDefineVO getSysActionParamDef(Long paramId)
    {
        return sysComponent.getSysActionParamDefByParamId(paramId);
    }
    
    public OneTimePasswordVO generateOtp(SysOtpVO otp)
    {
        return sysComponent.generateOtp(otp);
    }
    
    public List<CampaignTypeDefine> getCampaginType(String campaignTypeId)
    {
        return sysComponent.getCampaginType(campaignTypeId);
    }
    
    public PageInfo<CampaignTypeDefine> getCampaginTypeTotalSize(String campaignTypeId)
    {
        return sysComponent.getCampaginTypeTotalSize(campaignTypeId);
    }
    
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode)
    {
        return sysComponent.getConfigDefByConfigValueCode(configValueCode);
    }
    
    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode)
    {
        return sysComponent.getConfigDefByConfigCode(configCode);
    }
    
    public void setOTPToUsed(SysOtpVO sysOtp)
    {
        sysComponent.setOTPTOUsed(sysOtp);
    }
    
    public List<EnumDefine> getEnumType(String enumCode, String enumType)
    {
        return sysComponent.getEunmByCode(enumCode, enumType);
    }
    
    public PageInfo<EnumDefine> getEnumTypeTotalSize(String enumCode, String enumType)
    {
        return sysComponent.getEunmByCodeTotalSize(enumCode, enumType);
    }
    
    @Override
    public List<SysCountryDef> getCountryDef(Long countryCode)
    {
        return sysRepository.getCountryDef(countryCode);
    }
    
    public PageInfo<SysCountryDef> getCountryDefTotalSize(Long countryCode)
    {
        PageInfo<SysCountryDef> page = new PageInfo<SysCountryDef>();
        List<SysCountryDef> list = sysRepository.getCountryDef(countryCode);
        Integer totalSize = sysRepository.getCountryDefTotalSize(countryCode);
        return page.createPageInfo(totalSize, list);
    }
    
    @Override
    public List<SysProvDef> getProvDef(Long provCode)
    {
        return sysRepository.getProvDef(provCode);
    }
    
    @Override
    public List<SysRegionDef> getRegionDef(Long regionCode)
    {
        return sysRepository.getRegionDef(regionCode);
    }
    
    @Override
    public void createEntityParticipant(CmEntityParticipant paticipant)
    {
        sysComponent.createEntityParitcipant(paticipant);
        
    }
    
    @Override
    public CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo)
    {
        
        return sysComponent.getEntityParticipantByCampaignNo(campaignNo);
    }
    
    @Override
    public int addEntityParticipantCount(String campaignNo, int paticipant)
    {
        return sysComponent.addEntityParticipantCount(campaignNo, paticipant);
        
    }
    
    public PageInfo<SysActionParamDef> getSysActionParamDefByActionId(final SysActionParamDefQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
            
        }
        final PageInfo<SysActionParamDef> pageInfo = new PageInfo<SysActionParamDef>();
        Integer totalSize = sysRepository.getSysActionParamDefByActionIdTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        
        final List<SysActionParamDef> list = sysRepository.getSysActionParamDefByActionId(conditionVO, pageSize, pageNo);
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, list);
    }
    
    @Override
    public void createCampaignGroup(EnumGroupDefVO enumGroupDefVO)
    {
        List<Long> campaignTypeIds = enumGroupDefVO.getCampaignTypeIds();
        if (ValidateUtil.isEmpty(campaignTypeIds))
        {
            throw new MeoException(SysErrorCodeDefine.ENUMGROUPDEFVO_CAN_NOT_NULL);
        }
        SysEnumGroupDef sysEnumGroupDef = new SysEnumGroupDef();
        BeanUtils.copyProperties(enumGroupDefVO, sysEnumGroupDef);
        sysEnumGroupDef.setCreateDate(DateTimeUtil.getNow());
        sysEnumGroupDef.setSts(SysEnumGroupDef.STS_VALID);
        sysEnumGroupDef = sysRepository.createCampaignGroup(sysEnumGroupDef);
        for (Long campaignTypeId : campaignTypeIds)
        {
            List<SysEnumDef> sysEnumDefList = sysRepository.getCampaginType(campaignTypeId.toString());
            if (ValidateUtil.isNotEmpty(sysEnumDefList))
            {
                SysEnumDef enumDef = sysEnumDefList.get(0);
                Long enumId = enumDef.getEnumId();
                //add the sysEnumDef and sysEnumGroupDef rel
                createSysEnumGroupRel(enumId, sysEnumGroupDef);
            }
        }
    }

    private void createSysEnumGroupRel(Long enumId, SysEnumGroupDef sysEnumGroupDef)
    {
        SysEnumGroupRel enumGroupRel = new SysEnumGroupRel();
        enumGroupRel.setEnumId(enumId);
        enumGroupRel.setGroupId(sysEnumGroupDef.getGroupId());
        enumGroupRel.setSts(SysEnumGroupRel.STS_VALID);
        enumGroupRelRepository.addSysEnumGroupRel(enumGroupRel);
    }
    
    @Override
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode)
    {
        return sysRepository.getCampaignEnumGroup(groupCode);
    }
    
    @Override
    public void createProductCategory(SysCategoryDefVO sysCategoryDefVO)
    {
        CategoryDefine categoryDefine = getCategoryDefByCateId(sysCategoryDefVO.getCategoryId());
        if (ValidateUtil.isNotEmpty(categoryDefine))
        {
            throw new MeoException(SysErrorCodeDefine.CATEGORY_ID_EXISTED);
        }
        SysCategoryDef sysCategoryDef = new SysCategoryDef();
        BeanUtils.copyProperties(sysCategoryDefVO, sysCategoryDef);
        sysCategoryDef.setCreateDate(DateTimeUtil.getNow());
        sysCategoryDef.setSts(SysCategoryDef.STS_ACTIVE);
        sysCategoryDef.setCategoryType(SysCategoryDef.PRODUCT_TYPE);
        sysRepository.createProductCategory(sysCategoryDef);
    }
    
    @Override
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds)
    {
        return sysRepository.getSysEnumDefListByIds(enumIds);
    }
    
    @Override
    public ContractFileTemplateVO createContractFileTemplate(ContractFileTemplateVO contractFileTemplate)
    {
        SysFileTemplate sysFileTemplate = new SysFileTemplate();
        sysFileTemplate.setTemplateDesc(contractFileTemplate.getTemplateDesc());
        sysFileTemplate.setTemplateName(contractFileTemplate.getTemplateName());
        sysFileTemplate.setTemplateUrl(contractFileTemplate.getTemplateUrl());
        sysFileTemplate.setTemplateType(SysFileTemplate.CONTRACT_TYPE);
        sysFileTemplate.setSts(SysFileTemplate.STS_VALID);
        SysFileTemplate temp = sysFileTemplateRepository.addSysFileTemplate(sysFileTemplate);
        if (ValidateUtil.isNull(temp))
        {
            LOG.debug("create SysFileTemplate,SysFileTemplate is null");
            throw new MeoException("create SysFileTemplate,SysFileTemplate is null");
        }
        if (ValidateUtil.isNull(temp.getTemplateId()))
        {
            LOG.debug("create SysFileTemplate,SysFileTemplate templateId is null");
            throw new MeoException("create SysFileTemplate,SysFileTemplate templateId is null");
        }
        ContractFileTemplateVO tempc = new ContractFileTemplateVO();
        copyToContractFileTemplateVO(temp, tempc);
        return tempc;
    }
    
    @Override
    public ContractFileTemplateVO modifyContractFileTemplate(ContractFileTemplateVO contractFileTemplate)
    {
        SysFileTemplate sysFileTemplate = new SysFileTemplate();
        if (ValidateUtil.isNull(contractFileTemplate))
        {
            LOG.debug("modify SysFileTemplate,SysFileTemplate is null");
            throw new MeoException("modify SysFileTemplate,SysFileTemplate is null");
        }
        if (ValidateUtil.isNull(contractFileTemplate.getTemplateId()))
        {
            LOG.debug("modify SysFileTemplate,SysFileTemplate templateId is null");
            throw new MeoException("modify SysFileTemplate,SysFileTemplate templateId is null");
        }
        copyToSysFileTemplate(contractFileTemplate, sysFileTemplate);
        sysFileTemplate = sysFileTemplateRepository.updateSysFileTemplate(sysFileTemplate);
        copyToContractFileTemplateVO(sysFileTemplate, contractFileTemplate);
        return contractFileTemplate;
    }
    
    @Override
    public void deleteContractFileTemplate(Long templateId) throws MeoException
    {
        SysFileTemplate temp = sysFileTemplateRepository.getSysFileTemplateByTemplateId(templateId);
        if (ValidateUtil.isNull(temp))
        {
            LOG.debug("getContractFileTemplateVOByTemplateId,SysFileTemplate is null");
            throw new MeoException(SysErrorCodeDefine.SYSFILETEMPLATE_NOT_FOUND, new Object[]{templateId });
        }
        temp.setSts(SysFileTemplate.STS_INVALID);
        sysFileTemplateRepository.updateSysFileTemplate(temp);
    }
    
    private void copyToContractFileTemplateVO(SysFileTemplate temp, ContractFileTemplateVO tempc)
    {
        if (ValidateUtil.isNotNull(temp.getModifyDate()))
        {
            tempc.setModifyDate(temp.getModifyDate().getTime());
        }
        tempc.setTemplateDesc(temp.getTemplateDesc());
        tempc.setTemplateId(temp.getTemplateId());
        tempc.setTemplateName(temp.getTemplateName());
        tempc.setTemplateUrl(temp.getTemplateUrl());
        if (ValidateUtil.isNotNull(temp.getCreateDate()))
        {
            tempc.setCreateDate(temp.getCreateDate().getTime());
        }
    }
    
    private void copyToSysFileTemplate(ContractFileTemplateVO vo, SysFileTemplate sysFile)
    {
        if (ValidateUtil.isNotNull(vo.getModifyDate()))
        {
            sysFile.setModifyDate(new Timestamp(vo.getModifyDate()));
        }
        sysFile.setTemplateId(vo.getTemplateId());
        sysFile.setTemplateName(vo.getTemplateName());
        sysFile.setTemplateDesc(vo.getTemplateDesc());
        sysFile.setTemplateUrl(vo.getTemplateUrl());
        sysFile.setTemplateType(SysFileTemplate.CONTRACT_TYPE);
        sysFile.setSts(SysFileTemplate.STS_VALID);
    }
    
    @Override
    public ContractFileTemplateVO getContractFileTemplateVOByTemplateId(Long templateId)
    {
        SysFileTemplate temp = sysFileTemplateRepository.getSysFileTemplateByTemplateId(templateId);
        if (ValidateUtil.isNull(temp))
        {
            LOG.debug("getContractFileTemplateVOByTemplateId,SysFileTemplate is null");
            throw new MeoException(SysErrorCodeDefine.SYSFILETEMPLATE_NOT_FOUND, new Object[]{templateId });
        }
        ContractFileTemplateVO tempc = new ContractFileTemplateVO();
        copyToContractFileTemplateVO(temp, tempc);
        return tempc;
    }
    
    @Override
    public PageInfo<ContractFileTemplateVO> getContractFileTemplateVOList(final ContractFileTemplateQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
        }
        final PageInfo<ContractFileTemplateVO> pageInfo = new PageInfo<ContractFileTemplateVO>();
        Integer totalSize = sysFileTemplateRepository.getSysFileTemplateByTemplateTypeTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<SysFileTemplate> tempList = sysFileTemplateRepository
                .getSysFileTemplateByTemplateType(conditionVO, pageSize, pageNo);
        
        List<ContractFileTemplateVO> tempCList = new ArrayList<ContractFileTemplateVO>();
        for (SysFileTemplate sysFileTemplate : tempList)
        {
            ContractFileTemplateVO tempc = new ContractFileTemplateVO();
            copyToContractFileTemplateVO(sysFileTemplate, tempc);
            tempCList.add(tempc);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, tempCList);
    }
    
    @Override
    public List<SysLanguageDef> getLanguageDef(Integer languageCode)
    {
        return sysRepository.getLanguageDef(languageCode);
    }
    
    public PageInfo<SysLanguageDef> getLanguageDefTotalSize(Integer languageCode)
    {
        PageInfo<SysLanguageDef> page = new PageInfo<SysLanguageDef>();
        List<SysLanguageDef> list = sysRepository.getLanguageDef(languageCode);
        Integer totalSize = sysRepository.getLanguageDefTotalSize(languageCode);
        return page.createPageInfo(totalSize, list);
        
    }
    
    @Override
    public Long getCampaignTaskParamValue(String actionId, Long campaignId)
    {
        return sysRepository.getCampaignTaskParamValue(actionId, campaignId);
    }
    
    @Override
    public PageInfo<CampaignAction> getActionTotalSizeByCampaginType(Integer campaginType, Integer pageNo, Integer pageSize)
    {
        PageInfo<CampaignAction> page = new PageInfo<CampaignAction>();
        List<CampaignAction> campaignActionList = new ArrayList<CampaignAction>();
        List<SysCampaignActionDef> sysCampaignActionDefList = sysRepository.getSysCampaignActionDefList(campaginType, pageNo,
                pageSize);
        List<SysActionRel> sysActionRefList = sysRepository.getSysActionRefList(SysActionRel.TRIGGER_TYPE);
        for (SysCampaignActionDef sysCampaignActionDef : sysCampaignActionDefList)
        {
            CampaignAction campaignAction = new CampaignAction();
            List<CampaignAction> triggerAction = new ArrayList<CampaignAction>();
            long actionId = sysCampaignActionDef.getActionId();
            if (ValidateUtil.isEmpty(actionId))
            {
                throw new MeoException("SYS_CAMPAIGN_ACTION_DEF actionId is not exit");
            }
            for (SysActionRel sysActionRel : sysActionRefList)
            {
                if (sysActionRel.getSrcActionId()!= actionId)
                {
                    continue;
                }
                CampaignAction triggerCampaignAction = new CampaignAction();
                Long destActionId = sysActionRel.getDestActionId();
                triggerCampaignAction.setActionId(destActionId);
                SysActionDef sysActionDef = sysRepository.getActionDefine(destActionId);
                if (ValidateUtil.isEmpty(sysActionDef))
                {
                    throw new MeoException("SYS__ACTION_DEF actionId is not exit,  actionId [ "+ actionId+ " ]");
                }
                triggerCampaignAction.setActionName(sysActionDef.getActionName());
                triggerAction.add(triggerCampaignAction);
            }
            SysActionDef sysActionDef = sysRepository.getActionDefine(actionId);
            if (ValidateUtil.isEmpty(sysActionDef))
            {
                throw new MeoException("SYS__ACTION_DEF actionId is not exit,  actionId [ "+ actionId+ " ]");
            }
            campaignAction.setActionId(actionId);
            campaignAction.setActionName(sysActionDef.getActionName());
            campaignAction.setTriggerAction(triggerAction);
            campaignActionList.add(campaignAction);
        }
        Integer totalSize = sysRepository.getSysCampaignActionDefListTotalSize(campaginType);
        
        return page.createPageInfo(pageNo, pageSize, totalSize, campaignActionList);
    }

    @Override
    public SysEnumGroupRel getSysEnumGroupRel(Long relId)
    {
        return enumGroupRelRepository.getSysEnumGroupRel(relId);
    }

    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumIdAndGroupId(Long enumId, Long groupId)
    {
        return enumGroupRelRepository.getSysEnumGroupRelListByEnumIdAndGroupId(enumId, groupId);
    }

    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds)
    {
        return enumGroupRelRepository.getSysEnumGroupRelListByGroupIds(groupIds);
    }

    @Override
    public void modifyCampaignEnumGroup(EnumGroupDefVO enumGroupDefVO)
    {                
        if (ValidateUtil.isNull(enumGroupDefVO))
        {
            LOG.debug("modifyCampaignEnumGroup, EnumGroupDefVO is null");
            throw new MeoException("modifyCampaignEnumGroup, EnumGroupDefVO is null");
        }
        if (ValidateUtil.isNull(enumGroupDefVO.getGroupId()))
        {
            LOG.debug("modifyCampaignEnumGroup, EnumGroupDefVO groupID is null");
            throw new MeoException("modifyCampaignEnumGroup, EnumGroupDefVO groupID is null");
        }
        final List<SysEnumGroupDef> list = sysRepository.getCampaignEnumGroupByGroupID(enumGroupDefVO.getGroupId());
        if (ValidateUtil.isNotEmpty(list))
        {
            final SysEnumGroupDef sysEnumGroupDef = list.get(0);
            BeanUtils.copyProperties(enumGroupDefVO, sysEnumGroupDef);
            sysEnumGroupDef.setModifyDate(DateTimeUtil.getNow());
            sysRepository.modifyCampaignGroup(sysEnumGroupDef);
            /** handle 'SYS_ENUM_GROUP_REL' */
            if (ValidateUtil.isNotEmpty(enumGroupDefVO.getCampaignTypeIds()))
            {
                /** Check that campaign type ids are all exist or not */                
                final List<SysEnumDef> sysEnumDefListByIds = sysRepository.getSysEnumDefListByIds(enumGroupDefVO.getCampaignTypeIds());
                if (ValidateUtil.isNull(sysEnumDefListByIds) || ValidateUtil.isEmpty(sysEnumDefListByIds))
                {
                    LOG.debug("modifyCampaignEnumGroup, EnumGroupDefVO campaign type ids are not exist in the system");
                    throw new MeoException("modifyCampaignEnumGroup, EnumGroupDefVO campaign type ids are not exist in the system");
                } 
                else
                {
                    if (sysEnumDefListByIds.size() != enumGroupDefVO.getCampaignTypeIds().size())
                    {
                        LOG.debug("modifyCampaignEnumGroup, Some of EnumGroupDefVO campaign type ids are not exist in the system");
                        throw new MeoException("modifyCampaignEnumGroup, Some of EnumGroupDefVO campaign type ids are not exist in the system");
                    }
                }
                
                /** Deactivate all existing campaign group id from 'SYS_ENUM_GROUP_REL' */
                sysRepository.deactivateSysEnumGroupRelByGroupId(sysEnumGroupDef.getGroupId());
                for (final Long campaignTypeId : enumGroupDefVO.getCampaignTypeIds())
                {
                    final SysEnumDef sysEnumDef = sysRepository.getEnumDefCampaignTypeByEnumId(campaignTypeId);
                    if (ValidateUtil.isNotNull(sysEnumDef))
                    {
                        createSysEnumGroupRel(sysEnumDef.getEnumId(), sysEnumGroupDef);
                    }
                }
            }
        } else {
            LOG.debug("modifyCampaignEnumGroup, EnumGroupDefVO groupID is not found");
            throw new MeoException("modifyCampaignEnumGroup, EnumGroupDefVO groupID is not found");
        }
        
    }

    @Override
    public void deleteCampaignEnumGroup(Long groupDefID)
    {
        final List<SysEnumGroupDef> list = (List<SysEnumGroupDef>) sysRepository.getCampaignEnumGroupByGroupID(groupDefID);
        if (ValidateUtil.isEmpty(list))
        {
            throw new MeoException("Campaign Enum group ID is not exist,  CampaignEnumGroupID [ "+ groupDefID+ " ]");
        }
        final SysEnumGroupDef sysEnumGroupDef = list.get(0);
        sysEnumGroupDef.setSts(SysEnumGroupDef.STS_INVALID);
        sysRepository.modifyCampaignGroup(sysEnumGroupDef);
        /** Deactivate all existing campaign group id from 'SYS_ENUM_GROUP_REL' */
        sysRepository.deactivateSysEnumGroupRelByGroupId(sysEnumGroupDef.getGroupId());
    }

    @Override
    public PageInfo<SysEnumGroupDef> getCampaignEnumGroup(CampaignEnumGroupQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
            
        }
        final PageInfo<SysEnumGroupDef> pageInfo = new PageInfo<SysEnumGroupDef>();
        Integer totalSize = sysRepository.getCampaignEnumGroupTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize) || totalSize == 0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        final List<SysEnumGroupDef> list = sysRepository.getCampaignEnumGroup(conditionVO, pageSize, pageNo);
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, list);
    }

    @Override
    public List<SysEnumGroupDef> getSysEnumGroupRelListByEnumId(Long enumId)
    {
        List<SysEnumGroupDef> list = new ArrayList<SysEnumGroupDef>();
        List<SysEnumGroupRel> sysEnumGroupRelList = enumGroupRelRepository.getSysEnumGroupRelListByEnumId(enumId);
        for (SysEnumGroupRel sysEnumGroupRel : sysEnumGroupRelList)
        {
            Long groupId = sysEnumGroupRel.getGroupId();
            SysEnumGroupDef sysEnumGroupDef = sysRepository.getEnumGroupByGroupId(groupId);
            if (ValidateUtil.isEmpty(sysEnumGroupDef))
            {
                continue;
            }
            list.add(sysEnumGroupDef);
        }
        return list;
    }
    
}
