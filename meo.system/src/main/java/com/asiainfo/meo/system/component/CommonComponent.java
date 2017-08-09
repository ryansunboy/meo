package com.asiainfo.meo.system.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.cache.Cache;
import com.asiainfo.meo.common.core.cache.distributed.DistributedCache;
import com.asiainfo.meo.common.core.cache.local.LocalCache;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.RandomUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysActionDef;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCategoryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureDef;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.CampaignTypeDefine;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.common.app.model.vo.OneTimePasswordVO;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefineVO;
import com.asiainfo.meo.system.common.app.repository.CommonRepository;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysObjectVO;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;


public class CommonComponent
{
    
    private static final Log LOG = LogFactory.getLog(CommonComponent.class);
    
    @Resource
    private CommonRepository sysRepository;
    
    public void checkCreateEnumDefParam(EnumDefine enumDef) throws MeoException
    {
        
        if (EnumIsExisted(enumDef.getEnumCode(), enumDef.getEnumType()))
        {
            throw new MeoException(null);
            
        }
    }
    
    public void createEnumDef(EnumDefine enumDef)
    {
        if (ValidateUtil.isEmpty(enumDef))
        {
            return;
        }
        SysEnumDef def = new SysEnumDef();
        def.setEnumId(ServiceLocatorFactory.getService(Sequence.class).next(""));
        def.setCreateDate(DateTimeUtil.getNow());
        if (ValidateUtil.isNotEmpty(enumDef.getEnumDipName()))
        {
            def.setDispName(enumDef.getEnumDipName());
        }
        if (ValidateUtil.isNotEmpty(enumDef.getEnumCode()))
        {
            def.setEnumCode(enumDef.getEnumCode());
        }
        if (ValidateUtil.isNotEmpty(enumDef.getEnumName()))
        {
            def.setEnumName(enumDef.getEnumName());
        }
        if (ValidateUtil.isNotEmpty(enumDef.getEnumType()))
        {
            def.setEnumType(enumDef.getEnumType().toString());
        }
        
        def.setSts(SysEnumDef.STS_ENUM_ACTIVATE);
        sysRepository.createEnumDef(def);
    }
    
    public boolean EnumIsExisted(String enumCode, Integer enumType)
    {
        SysEnumDef enumDef = sysRepository.getEnumDefByEnumCode(enumCode, enumType);
        if (ValidateUtil.isEmpty(enumDef))
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        
        if (ValidateUtil.isNotEmpty(enumCode))
        {
            SysEnumDef enumDef = sysRepository.getEnumDefByEnumCode(enumCode, enumType);
            if (ValidateUtil.isNotEmpty(enumDef))
            {
                EnumDefine def = new EnumDefine();
                def.setEnumCode(enumDef.getEnumCode());
                def.setEnumDipName(enumDef.getDispName());
                def.setEnumName(enumDef.getEnumName());
                def.setEnumType(Integer.valueOf(enumDef.getEnumType()));
                //added by zhengzy 2015-9-11 13:41:34
                def.setEnumId(enumDef.getEnumId());
                return def;
                
            }
        }
        return null;
        
    }
    
    public CategoryDefine getCategoryDefByCateId(Long categoryId)
    {
        CategoryDefine cateDef = new CategoryDefine();
        if (ValidateUtil.isNotEmpty(categoryId))
        {
            SysCategoryDef def = sysRepository.getCategoryDefsByCateId(categoryId);
            if (ValidateUtil.isEmpty(def))
            {
                return null;
            }
            cateDef.setCategoryId(def.getCategoryId());
            cateDef.setParentCateId(def.getParentCateId());
            cateDef.setCategoryName(def.getName());
        }
        return cateDef;
    }
    
    public List<CategoryDefine> getCateDefByParentCateId(Long partnerCategoryId)
    {
        List<CategoryDefine> cateDefs = new ArrayList<CategoryDefine>();
        
        if (ValidateUtil.isNotEmpty(partnerCategoryId))
        {
            List<SysCategoryDef> defs = sysRepository.getCategoryDefsByParentCateId(partnerCategoryId);
            for (SysCategoryDef sysCategoryDef : defs)
            {
                CategoryDefine cateDef = new CategoryDefine();
                cateDef.setCategoryId(sysCategoryDef.getCategoryId());
                cateDef.setCategoryName(sysCategoryDef.getName());
                cateDef.setParentCateId(sysCategoryDef.getParentCateId());
                cateDefs.add(cateDef);
            }
        }
        return cateDefs;
    }
    
    public String getLanguageNameByCode(Integer languageCode)
    {
        if (ValidateUtil.isNotEmpty(languageCode))
        {
            SysLanguageDef def = sysRepository.getLanguageDefByLanguageCode(languageCode);
            if (ValidateUtil.isNotEmpty(def))
            {
                return def.getName();
            }
        }
        return null;
    }
    
    public SysObjectVO getObjectByObjectId(Long objectId)
    {
        
        SysPortalUser portalUser = sysRepository.getObjectByObjectId(objectId);
        if (ValidateUtil.isNotEmpty(portalUser))
        {
            SysObjectVO object = new SysObjectVO();
            object.setObjectId(portalUser.getObjectId());
            object.setObjectType(portalUser.getObjectType());
            object.setOperatorId(portalUser.getObjectId());
            return object;
        }
        
        return null;
    }
    
    public void createSysPortalUser(SysObjectVO objectVo)
    {
        if (ValidateUtil.isNotEmpty(objectVo))
        {
            SysPortalUser portalUser = new SysPortalUser();
            portalUser.setObjectId(objectVo.getObjectId());
            portalUser.setObjectType(objectVo.getObjectType());
            portalUser.setOperatorId(objectVo.getOperatorId());
            sysRepository.createSysPortalUser(portalUser);
        }
    }
    
    public Integer getObjectTypeByObjectId(Long objectId)
    {
        SysPortalUser portalUser = sysRepository.getObjectByObjectId(objectId);
        if (ValidateUtil.isNotEmpty(portalUser))
        {
            return portalUser.getObjectType();
        }
        return null;
    }
    
    public PageInfo<CategoryDefine> getCategoryEnumTotalSize(Long categoryId, Integer categoryType)
    {
        List<CategoryDefine> cateDefs = new ArrayList<CategoryDefine>();
        PageInfo<CategoryDefine> pageInfo = new PageInfo<CategoryDefine>();
        List<SysCategoryDef> defs = sysRepository.getCategoryEnum(categoryId, categoryType);
        Integer totalSize = sysRepository.getCategoryEnumTotalSize(categoryId, categoryType);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysCategoryDef sysCategoryDef : defs)
            {
                CategoryDefine cateDef = new CategoryDefine();
                cateDef.setCategoryId(sysCategoryDef.getCategoryId());
                cateDef.setCategoryName(sysCategoryDef.getName());
                cateDef.setParentCateId(sysCategoryDef.getParentCateId());
                cateDef.setIconURL(sysCategoryDef.getIconURL());
                cateDefs.add(cateDef);
            }
        }
        return pageInfo.createPageInfo(totalSize, cateDefs);
    }
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        List<CategoryDefine> cateDefs = new ArrayList<CategoryDefine>();
        List<SysCategoryDef> defs = sysRepository.getCategoryEnum(categoryId, categoryType);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysCategoryDef sysCategoryDef : defs)
            {
                CategoryDefine cateDef = new CategoryDefine();
                cateDef.setCategoryId(sysCategoryDef.getCategoryId());
                cateDef.setCategoryName(sysCategoryDef.getName());
                cateDef.setParentCateId(sysCategoryDef.getParentCateId());
                cateDefs.add(cateDef);
            }
        }
        return cateDefs;
    }
    
    public List<MeasureDefine> getMeasureDef(Long measureId, Long measureTypeId)
    {
        List<MeasureDefine> measureDefs = new ArrayList<MeasureDefine>();
        List<SysMeasureDef> defs = sysRepository.getMeasureDef(measureId, measureTypeId);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysMeasureDef sysMeasureDef : defs)
            {
                MeasureDefine measureDef = new MeasureDefine();
                measureDef.setMeasureId(sysMeasureDef.getMeasureId());
                measureDef.setName(sysMeasureDef.getName());
                measureDefs.add(measureDef);
            }
        }
        return measureDefs;
    }
    
    public PageInfo<MeasureDefine> getMeasureDefTotalSize(Long measureId, Long measureTypeId)
    {
        PageInfo<MeasureDefine> page = new PageInfo<MeasureDefine>();
        List<MeasureDefine> measureDefs = new ArrayList<MeasureDefine>();
        List<SysMeasureDef> defs = sysRepository.getMeasureDef(measureId, measureTypeId);
        Integer totalSize = sysRepository.getMeasureDefTotalSize(measureId, measureTypeId);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysMeasureDef sysMeasureDef : defs)
            {
                MeasureDefine measureDef = new MeasureDefine();
                measureDef.setMeasureId(sysMeasureDef.getMeasureId());
                measureDef.setName(sysMeasureDef.getName());
                measureDefs.add(measureDef);
            }
        }
        return page.createPageInfo(totalSize, measureDefs);
    }
    
    public List<EnumDefine> getEunmByCode(String eunmCode, String eunmType)
    {
        List<EnumDefine> enumDefs = new ArrayList<EnumDefine>();
        List<SysEnumDef> defs = sysRepository.getEnumDefByCode(eunmCode, eunmType);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysEnumDef sysEnumDef : defs)
            {
                EnumDefine enumDef = new EnumDefine();
                enumDef.setEnumId(sysEnumDef.getEnumId());
                enumDef.setEnumCode(sysEnumDef.getEnumCode());
                enumDef.setEnumName(sysEnumDef.getEnumName());
                enumDefs.add(enumDef);
            }
        }
        return enumDefs;
    }
    
    public PageInfo<EnumDefine> getEunmByCodeTotalSize(String eunmCode, String eunmType)
    {
        PageInfo<EnumDefine> page = new PageInfo<EnumDefine>();
        List<EnumDefine> enumDefs = new ArrayList<EnumDefine>();
        List<SysEnumDef> defs = sysRepository.getEnumDefByCode(eunmCode, eunmType);
        Integer totalSize = sysRepository.getEnumDefByCodeTotalSize(eunmCode, eunmType);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysEnumDef sysEnumDef : defs)
            {
                EnumDefine enumDef = new EnumDefine();
                enumDef.setEnumId(sysEnumDef.getEnumId());
                enumDef.setEnumCode(sysEnumDef.getEnumCode());
                enumDef.setEnumName(sysEnumDef.getEnumName());
                enumDefs.add(enumDef);
            }
        }
        return page.createPageInfo(totalSize, enumDefs);
    }
    
    public List<ActionDefine> getActionDefine(Long actionId, Integer actionType)
    {
        List<ActionDefine> actionDefs = new ArrayList<ActionDefine>();
        List<SysActionDef> defs = sysRepository.getActionDefine(actionId, actionType);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysActionDef sysActionDef : defs)
            {
                ActionDefine actionDef = new ActionDefine();
                actionDef.setActionDesc(sysActionDef.getActionDesc());
                actionDef.setActionName(sysActionDef.getActionName());
                actionDef.setAtcionId(sysActionDef.getActionId());
                actionDefs.add(actionDef);
            }
        }
        return actionDefs;
    }
    
    public PageInfo<ActionDefine> getActionDefineTotalSize(Long actionId, Integer actionType)
    {
        PageInfo<ActionDefine> page = new PageInfo<ActionDefine>();
        List<ActionDefine> actionDefs = new ArrayList<ActionDefine>();
        List<SysActionDef> defs = sysRepository.getActionDefine(actionId, actionType);
        Integer totalSize = sysRepository.getActionDefineTotalSize(actionId, actionType);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysActionDef sysActionDef : defs)
            {
                ActionDefine actionDef = new ActionDefine();
                actionDef.setActionDesc(sysActionDef.getActionDesc());
                actionDef.setActionName(sysActionDef.getActionName());
                actionDef.setAtcionId(sysActionDef.getActionId());
                actionDefs.add(actionDef);
            }
        }
        return page.createPageInfo(totalSize, actionDefs);
    }
    
    public SysActionParamDefineVO getSysActionParamDefByParamId(Long paramId)
    {
        SysActionParamDefineVO sysActionParamDefineVO = new SysActionParamDefineVO();
        
        if (ValidateUtil.isNotNull(paramId))
        {
            SysActionParamDef sysActionParamDef = new SysActionParamDef();
            
            sysActionParamDef = sysRepository.getSysActionParamDef(paramId);
            if(ValidateUtil.isNull(sysActionParamDef)){
                return null;
            }
            sysActionParamDefineVO.setParamName(sysActionParamDef.getParamName());
        }
        
        return sysActionParamDefineVO;
    }
    
    public OneTimePasswordVO generateOtp(@Valid SysOtpVO otpVo)
    {
        Integer otpLength = null;
        Integer otpPeriod = null;
        List<SysConfigurationDef> defineList = getConfigDefByConfigCode(SysConstantDefine.OTP_CONFIG_DEFINE);
        if (ValidateUtil.isEmpty(defineList))
        {
            LOG.error("Please configure config_code=["+ SysConstantDefine.OTP_CONFIG_DEFINE+ "] in table [sys_configuration_def]");
            throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
        }
        for (SysConfigurationDef def : defineList)
        {
            if (def.getConfigValueCode().equals(SysConstantDefine.OTP_LENGTH_CONFIG_CODE))
            {
                otpLength = def.getConfigValue();
            }
            else if (def.getConfigValueCode().equals(SysConstantDefine.OTP_PERIOD_CONFIG_CODE))
            {
                otpPeriod = def.getConfigValue();
            }
        }
        if (ValidateUtil.isEmpty(otpLength))
        {
            LOG.error("Please configure config_value_code=["+ SysConstantDefine.OTP_LENGTH_CONFIG_CODE
                    + "] in table [sys_configuration_def]");
            throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
        }
        else if (ValidateUtil.isEmpty(otpPeriod))
        {
            LOG.error("Please configure config_value_code=["+ SysConstantDefine.OTP_PERIOD_CONFIG_CODE
                    + "] in table [sys_configuration_def]");
            throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
            
        }
        Cache otpCache = ServiceLocatorFactory.getService(DistributedCache.class);
        String otp = RandomUtil.randomNum(otpLength);
        String otpCacheKey = "";
        if(otpVo.getAcctType().equals(SysEnumCodeDefine.BUNDLE_ACCT_TYPE_MSISDN)&& ValidateUtil.isNotEmpty(otpVo.getCountryCode())){
            otpCacheKey=otpVo.getCountryCode().toString().concat(otpVo.getBundleAccount());
        }else {
            otpCacheKey=otpVo.getBundleAccount();
        }
        OneTimePasswordVO password = new OneTimePasswordVO();
        password.setContactAccount(otpVo.getBundleAccount());
        password.setOtp(otp);
        password.setPwdPeriod(otpPeriod);
        password.setValidDate(DateTimeUtil.getCurrentTimeMillis());
        password.setExpireDate(password.getValidDate()+ password.getPwdPeriod() * 1000);
        password.setUsed(false);
        otpCache.put(SysConstantDefine.OTP_CACHE_NAME, otpCacheKey, password);
        return password;
    }
    
    public List<CampaignTypeDefine> getCampaginType(String campaignTypeId)
    {
        List<CampaignTypeDefine> enumDefs = new ArrayList<CampaignTypeDefine>();
        List<SysEnumDef> defs = sysRepository.getCampaginType(campaignTypeId);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysEnumDef sysEnumDef : defs)
            {
                CampaignTypeDefine campaignTypeDef = new CampaignTypeDefine();
                campaignTypeDef.setCampaignTypeId(sysEnumDef.getEnumCode());
                campaignTypeDef.setCampaignTypeName(sysEnumDef.getDispName());
                enumDefs.add(campaignTypeDef);
            }
        }
        return enumDefs;
    }
    
    public PageInfo<CampaignTypeDefine> getCampaginTypeTotalSize(String campaignTypeId)
    {
        PageInfo<CampaignTypeDefine> page = new PageInfo<CampaignTypeDefine>();
        List<CampaignTypeDefine> enumDefs = new ArrayList<CampaignTypeDefine>();
        List<SysEnumDef> defs = sysRepository.getCampaginType(campaignTypeId);
        Integer totalSize = sysRepository.getCampaginTypeTotalSize(campaignTypeId);
        if (ValidateUtil.isNotEmpty(defs))
        {
            for (SysEnumDef sysEnumDef : defs)
            {
                CampaignTypeDefine campaignTypeDef = new CampaignTypeDefine();
                campaignTypeDef.setCampaignTypeId(sysEnumDef.getEnumCode());
                campaignTypeDef.setCampaignTypeName(sysEnumDef.getDispName());
                enumDefs.add(campaignTypeDef);
            }
        }
        return page.createPageInfo(totalSize, enumDefs);
    }
    
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode)
    {
        Cache localCache = ServiceLocatorFactory.getService(LocalCache.class);
        List<SysConfigurationDef> defList = new ArrayList<SysConfigurationDef>();
        defList = localCache.get(SysConstantDefine.SYS_CONFIG_DEFINE_CACHE, SysConstantDefine.SYS_CONFIG_DEFINE_LIST,
                defList.getClass());
        if (ValidateUtil.isEmpty(defList))
        {
            return sysRepository.getConfigDefByConfigValueCode(configValueCode);
        } 
        for (SysConfigurationDef def : defList)
        {
            if (def.getConfigValueCode().equals(configValueCode))
            {
                return def;
            }
        }
        return null;
    }
    
    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode)
    {
        List<SysConfigurationDef> defs = new ArrayList<SysConfigurationDef>();
        Cache localCache = ServiceLocatorFactory.getService(LocalCache.class);
        List<SysConfigurationDef> defList = new ArrayList<SysConfigurationDef>();
        defList = localCache.get(SysConstantDefine.SYS_CONFIG_DEFINE_CACHE, SysConstantDefine.SYS_CONFIG_DEFINE_LIST,
                defList.getClass());
        if (ValidateUtil.isEmpty(defList))
        {
            defs = sysRepository.getConfigDefByConfigCode(configCode);
            return defs;
        }
        for (SysConfigurationDef def : defList)
        {
            if (def.getConfigCode().equals(configCode))
            {
                defs.add(def);
            }
        }
        
        return defs;
    }

    public void setOTPTOUsed(SysOtpVO sysOtp)
    {
        Cache otpCache = ServiceLocatorFactory.getService(DistributedCache.class);
        String key = sysOtp.getCountryCode().toString().concat(sysOtp.getBundleAccount());
        OneTimePasswordVO otp = otpCache.get(SysConstantDefine.OTP_CACHE_NAME, key, OneTimePasswordVO.class);
        if(ValidateUtil.isEmpty(otp) || otp.isUsed()){
            throw new MeoException(SysErrorCodeDefine.OTP_IS_INVALID);
        }else {
            otp.setUsed(true);
            otpCache.put(SysConstantDefine.OTP_CACHE_NAME, key, otp);
        }
        
    }

    public void createEntityParitcipant(CmEntityParticipant paticipant)
    {
        sysRepository.createEntityParticipant(paticipant);
        
    }

    public CmEntityParticipant getEntityParticipantByCampaignNo(String campaignNo)
    {
        
        return sysRepository.getEntityParticipantByCampaignNo(campaignNo);
    }

    public int addEntityParticipantCount(String campaignNo,int paticipant)
    {
       return sysRepository.addEntityParticipantCount(campaignNo,paticipant);
    }
    
}
