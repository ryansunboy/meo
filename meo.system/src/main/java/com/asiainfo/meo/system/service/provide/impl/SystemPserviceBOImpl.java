package com.asiainfo.meo.system.service.provide.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.authority.app.bo.AuthorityBO;
import com.asiainfo.meo.system.authority.app.bo.StaffInfoBO;
import com.asiainfo.meo.system.authority.app.model.entity.SysRolePrivilege;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.MenuQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.OperatorQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.RolePrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserVO;
import com.asiainfo.meo.system.authority.app.model.vo.UPrivilegeVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserMenuVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserOperaterVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.UserRoleVO;
import com.asiainfo.meo.system.common.app.bo.CommonBo;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
import com.asiainfo.meo.system.common.app.model.entity.SysMeasureUnitExchangeRule;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
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
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysObjectVO;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.template.app.bo.TemplateBO;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamDef;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;
import com.asiainfo.meo.system.template.app.model.vo.TemplateParamDefVO;
import com.asiainfo.meo.system.user.app.bo.UserBO;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;

public class SystemPserviceBOImpl implements SystemPserviceBO
{
    private static final Log LOG = LogFactory.getLog(SystemPserviceBOImpl.class);
    
    @Resource
    CommonBo                 systemBo;
    
    @Resource
    UserBO                   userBo;
    
    @Resource
    AuthorityBO              authorityBO;
    
    @Resource
    StaffInfoBO              staffInfoBO;
    
    @Resource
    TemplateBO               templateBO;
    
    public void createEnumDef(EnumDefine enumDef) throws MeoException, IOException
    {
        systemBo.createEnumDef(enumDef);
        
    }
    
    public void modifyEnumDef(EnumDefine enumDef)
    {
        
    }
    
    public void deactivateEnum(String enumCode)
    {
        
    }
    
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        
        return systemBo.getEnumDefByEnumCode(enumCode, enumType);
    }
    
    public CategoryDefine getCategoryDefByCateId(Long categoryId)
    {
        return null;
    }
    
    public List<CategoryDefine> getCategoryDefByParentCateId(Long partnerCategoryId)
    {
        return null;
    }
    
    public void createCategoryDefine(CategoryDefine cateDef)
    {
        
    }
    
    public void modifyCategoryDefine(CategoryDefine cateDef)
    {
        
    }
    
    public void deactivateCategoryDefine(String cateId)
    {
        
    }
    
    public String getLanguageNameByCode(Integer languageCode)
    {
        return null;
    }
    
    public UserLoginVO getUserLoginInfo(String loginAcct, Integer userType)
    {
        
        return userBo.getUserLoginInfo(loginAcct, userType);
    }
    
    public void createUserLoginInfo(UserLoginVO userLoginVo)
    {
        userBo.createUserLoginInfo(userLoginVo);
    }
    
    public Integer getObjectTypeByObjectId(Long objectId)
    {
        return systemBo.getObjectTypeByObjectId(objectId);
    }
    
    public SysObjectVO getObjectByObjectId(Long objectId)
    {
        return systemBo.getObjectByObjectId(objectId);
    }
    
    public void createSysObjectUser(SysObjectVO object)
    {
        systemBo.createSysPortalUser(object);
        
    }
    
    public void validBundleAccount(String bundleAcct, String validPwd)
    {
        userBo.validateBundleAccount(validPwd, bundleAcct);
        
    }
    
    public void updatePassword(SysPasswordVO password)
    {
        userBo.updatePassword(password);
        
    }
    
    public void activateLoginAcct(Long objectId, Integer objectType)
    {
        userBo.activateLoginAcct(objectId, objectType);
        
    }
    
    public Long getSystemDefaultMeasureUnit(Integer measureId, Long amount)
    {
        SysMeasureUnitExchangeRule rule = systemBo.getSysMeasureUnitExchangeRule(measureId, 5);
        if (ValidateUtil.isNotEmpty(rule))
        {
            return amount* rule.getExchangeDenonumerator()/ rule.getExchangeNumerator();
        }
        
        return null;
    }
    
    public PageInfo<CategoryDefine> getCategoryEnumTotalSize(Long categoryId, Integer categoryType)
    {
        return systemBo.getCategoryEnumTotalSize(categoryId, categoryType);
    }
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        return systemBo.getCategoryEnum(categoryId, categoryType);
    }
    
    public List<MeasureDefine> getMeasureDef(Long measureId, Long measureTypeId)
    {
        return systemBo.getMeasureDef(measureId, measureTypeId);
    }
    
    public PageInfo<MeasureDefine> getMeasureDefTotalSize(Long measureId, Long measureTypeId)
    {
        return systemBo.getMeasureDefTotalSize(measureId, measureTypeId);
    }
    
    public List<EnumDefine> getEnumByCode(String enumCode, String enumType)
    {
        return systemBo.getEnumType(enumCode, enumType);
    }
    
    public PageInfo<EnumDefine> getEnumTypeTotalSize(String enumCode, String enumType)
    {
        return systemBo.getEnumTypeTotalSize(enumCode, enumType);
    }
    
    public List<ActionDefine> getActionDefine(Long actionId, Integer actionType)
    {
        return systemBo.getActionDefine(actionId, actionType);
    }
    
    public PageInfo<ActionDefine> getActionDefineTotalSize(Long actionId, Integer actionType)
    {
        return systemBo.getActionDefineTotalSize(actionId, actionType);
    }
    
    public List<CampaignTypeDefine> getCampaginType(String campaignTypeId)
    {
        return systemBo.getCampaginType(campaignTypeId);
    }
    
    public SysActionParamDefineVO getSysActionParamDefByParamId(Long paramId)
    {
        return systemBo.getSysActionParamDef(paramId);
    }
    
    public OneTimePasswordVO genarateOtp(SysOtpVO otp)
    {
        
        return systemBo.generateOtp(otp);
    }
    
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode)
    {
        return systemBo.getConfigDefByConfigValueCode(configValueCode);
    }
    
    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode)
    {
        return systemBo.getConfigDefByConfigCode(configCode);
    }
    
    public void updatePartnerLoginAcct(Long partnerId, String newLoginAcct)
    {
        userBo.updatePartnerLoginAcct(partnerId, newLoginAcct);
        
    }
    
    public void setOTPToUsed(SysOtpVO sysOtp)
    {
        systemBo.setOTPToUsed(sysOtp);
        
    }
    
    @Override
    public List<SysCountryDef> getCountryDef(Long countryCode)
    {
        return systemBo.getCountryDef(countryCode);
    }
    
    public PageInfo<SysCountryDef> getCountryDefTotalSize(Long countryCode)
    {
        return systemBo.getCountryDefTotalSize(countryCode);
    }
    
    @Override
    public List<SysProvDef> getProvDef(Long provCode)
    {
        return systemBo.getProvDef(provCode);
    }
    
    @Override
    public List<SysRegionDef> getRegionDef(Long regionCode)
    {
        return systemBo.getRegionDef(regionCode);
    }
    
    @Override
    public void createEntityParticipant(CmEntityParticipant paticipant)
    {
        systemBo.createEntityParticipant(paticipant);
        
    }
    
    @Override
    public CmEntityParticipant getEntityParticipantByCamapginNo(String campaignNo)
    {
        
        return systemBo.getEntityParticipantByCampaignNo(campaignNo);
    }
    
    @Override
    public int addEntityParticipantCount(String campaignNo, int paticipant)
    {
        return systemBo.addEntityParticipantCount(campaignNo, paticipant);
    }
    
    @Override
    public PageInfo<SysActionParamDef> getSysActionParamDefByActionId(SysActionParamDefQueryConditionVO conditionVO)
    {
        return systemBo.getSysActionParamDefByActionId(conditionVO);
    }
    
    @Override
    public void createCampaignGroup(EnumGroupDefVO enumGroupDefVO)
    {
        if (enumGroupDefVO== null)
        {
            LOG.info("create campaign group,the input parameter enumGroupDefVo can't null");
            throw new MeoException(SysErrorCodeDefine.ENUMGROUPDEFVO_CAN_NOT_NULL);
        }
        if (ValidateUtil.isEmpty(enumGroupDefVO.getCampaignTypeIds()))
        {
            LOG.info("create campaign group,the campaignTypeIds can't empty");
            throw new MeoException(SysErrorCodeDefine.CAMPAIGNTYPEIDS_CAN_NOT_EMPTY);
        }
        systemBo.createCampaignGroup(enumGroupDefVO);
    }
    
    @Override
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode)
    {
        return systemBo.getCampaignEnumGroup(groupCode);
    }
    
    @Override
    public void createProductCategory(SysCategoryDefVO sysCategoryDefVO)
    {
        systemBo.createProductCategory(sysCategoryDefVO);
    }
    
    @Override
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds)
    {
        return systemBo.getSysEnumDefListByIds(enumIds);
    }
    
    @Override
    public PageInfo<UserRoleVO> getRoleInfoList(final UserRoleQueryConditionVO conditionVO)
    {
        return authorityBO.getRoleInfoList(conditionVO);
    }
    
    @Override
    public UserRoleVO addRole(UserRoleVO userRole)
    {
        if (ValidateUtil.isNull(userRole))
        {
            LOG.debug("the method addRole ,input parameter userRole is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.USER_ROLE });
        }
        return authorityBO.addRole(userRole);
    }
    
    @Override
    public UserRoleVO updateRole(UserRoleVO userRole)
    {
        if (ValidateUtil.isNull(userRole))
        {
            LOG.debug("the method updateRole,input parameter userRole is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.USER_ROLE });
        }
        if (ValidateUtil.isNull(userRole.getRoleId()))
        {
            LOG.debug("the method updateRole,input parameter userRole.roleId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.ROLE_ID });
        }
        return authorityBO.updateRole(userRole);
    }
    
    @Override
    public void deleteRoleByRoleId(Long roleId)
    {
        if (ValidateUtil.isNull(roleId))
        {
            LOG.debug("the method deleteRoleByRoleId,input parameter roleId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.ROLE_ID });
        }
        UserRoleVO userRole = authorityBO.getRole(roleId);
        if (ValidateUtil.isNull(userRole))
        {
            LOG.debug("can not found the roel, roleId is "+ roleId);
            throw new MeoException(SysErrorCodeDefine.ROLE_DOES_NOT_EXIST, new Object[]{roleId });
        }
        List<SysRoleUser> sysRoleUserList = staffInfoBO.getSysRoleUserByRoleId(roleId);
        if (ValidateUtil.isNotEmpty(sysRoleUserList))
        {
            LOG.debug("the role can't be deleted,the role is referenced with the user ,the roleId is "+ roleId);
            throw new MeoException(SysErrorCodeDefine.ROLE_REFERENCED_USER, new Object[]{roleId });
        }
        List<SysRolePrivilege> sysRolePrivilegeList = authorityBO.getRolePrivilegeByRoleId(roleId);
        if (ValidateUtil.isNotEmpty(sysRolePrivilegeList))
        {
            LOG.debug("the role can't be deleted,the role is referenced with the privilege ,the roleId is "+ roleId);
            throw new MeoException(SysErrorCodeDefine.ROLE_REFERENCED_PRIVILEGE, new Object[]{roleId });
        }
        authorityBO.deleteRoleByRoleId(roleId);
    }
    
    @Override
    public PageInfo<UserMenuVO> getMenuList(final MenuQueryConditionVO conditionVO)
    {
        return authorityBO.getMenuList(conditionVO);
    }
    
    @Override
    public ContractFileTemplateVO createContractFileTemplate(ContractFileTemplateVO contractFileTemplate)
    {
        if (ValidateUtil.isNull(contractFileTemplate))
        {
            LOG.debug("create ContractFileTemplate ,the parameter entity contractFileTemplate is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{SysConstantDefine.CONTRACT_FILE_TEMPLATE_VO });
        }
        return systemBo.createContractFileTemplate(contractFileTemplate);
    }
    
    @Override
    public ContractFileTemplateVO modifyContractFileTemplate(ContractFileTemplateVO contractFileTemplate)
    {
        if (ValidateUtil.isNull(contractFileTemplate))
        {
            LOG.debug("modify ContractFileTemplate ,the parameter entity contractFileTemplate is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,
                    new Object[]{SysConstantDefine.CONTRACT_FILE_TEMPLATE_VO });
        }
        return systemBo.modifyContractFileTemplate(contractFileTemplate);
    }
    
    @Override
    public void deleteContractTemplate(Long templateId)
    {
        if (ValidateUtil.isNull(templateId))
        {
            LOG.debug("the method deleteContractTemplate,the input parameter templateId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.TEMPLATEID });
        }
        systemBo.deleteContractFileTemplate(templateId);
    }
    
    @Override
    public ContractFileTemplateVO getContractFileTemplateVOByTemplateId(Long templateId)
    {
        if (ValidateUtil.isNull(templateId))
        {
            LOG.debug("the method getContractFileTemplateVOByTemplateId,the input parameter templateId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.TEMPLATEID });
        }
        return systemBo.getContractFileTemplateVOByTemplateId(templateId);
    }
    
    @Override
    public PageInfo<ContractFileTemplateVO> getContractFileTemplateVOList(final ContractFileTemplateQueryConditionVO conditionVO)
    {
        return systemBo.getContractFileTemplateVOList(conditionVO);
    }
    
    @Override
    public Long saveMenu(UserMenuVO userMenuVO)
    {
        return authorityBO.saveMenu(userMenuVO);
    }
    
    @Override
    public void modifyMenu(UserMenuVO userMenuVO)
    {
        authorityBO.modifyMenu(userMenuVO);
    }
    
    @Override
    public void deleteMenu(Long menuId)
    {
        authorityBO.deleteMenu(menuId);
    }
    
    @Override
    public PageInfo<UserOperaterVO> getOperationList(final OperatorQueryConditionVO conditionsVO)
    {
        return authorityBO.getOperationList(conditionsVO);
    }
    
    @Override
    public Long saveOperation(String operName)
    {
        return authorityBO.saveOperation(operName);
    }
    
    public void deleteOperation(Long operId)
    {
        authorityBO.deleteOperation(operId);
    }
    
    @Override
    public void saveRolePrivilege(RolePrivilegeVO rolePrivilegeVO)
    {
        authorityBO.saveRolePrivilege(rolePrivilegeVO);
    }
    
    @Override
    public void deleteRolePrivilege(Long roleId)
    {
        authorityBO.deleteRolePrivilege(roleId);
    }
    
    @Override
    public SystemUserVO saveSystemUser(SystemUserVO systemUser)
    {
        if (ValidateUtil.isNull(systemUser))
        {
            LOG.debug("save systemUser ,the input parameter systemUser is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.SYSTEM_USER });
        }
        SystemUserVO systemUserVo = staffInfoBO.saveSystemUser(systemUser);
        
        // add the login user
        UserLoginVO userLogin = new UserLoginVO();
        userLogin.setLoginAcct(systemUserVo.getEmail());
        userLogin.setObjectType(SysPortalUser.OBJECT_TYPE_SYSTEM);
        userLogin.setObjectId(systemUserVo.getUserId());
        createUserLoginInfo(userLogin);
        
        return systemUserVo;
    }
    
    @Override
    public SystemUserVO getSystemUser(Long userId)
    {
        if (ValidateUtil.isNull(userId))
        {
            LOG.debug("get getSystemUser by userId ,the input parameter userId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.USER_ID });
        }
        return staffInfoBO.getSystemUser(userId);
    }
    
    @Override
    public SystemUserVO updateSystemUser(SystemUserVO systemUser)
    {
        if (ValidateUtil.isNull(systemUser))
        {
            LOG.debug("update systemUser ,the input parameter systemUser is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.SYSTEM_USER });
        }
        if (ValidateUtil.isNull(systemUser.getUserId()))
        {
            LOG.debug("update systemUser ,the input parameter systemUser.userid is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.USER_ID });
        }
        return staffInfoBO.updateSystemUser(systemUser);
    }
    
    @Override
    public void deleteSystemUserByuserId(Long userId)
    {
        staffInfoBO.deleteSystemUserByuserId(userId);
    }
    
    @Override
    public PageInfo<SystemUserVO> getSystemUserVOPageInfo(SystemUserQueryConditionVO systemUserQueryCodition)
    {
        return staffInfoBO.getSystemUserVOPageInfo(systemUserQueryCodition);
    }
    
    @Override
    public void resetSystmeUserPwd(Long userId)
    {
        userBo.resetSystmeUserPwd(userId);
    }
    
    /**
     * @Description: 鏌ヨ鎵�湁瑙掕壊鐨勬潈闄�
     * @Description: get all role privilege
     * @author zhengzy
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<RPrivilegeVO> getAllRolePrivilegeList(final RolePrivilegeQueryConditionVO conditionVO)
    {
        return authorityBO.getAllRolePrivilegeList(conditionVO);
    }
    
    @Override
    public List<UPrivilegeVO> getUPrivilegeListByUserId(Long userId)
    {
        return authorityBO.getUPrivilegeListByUserId(userId);
    }
    
    @Override
    public void deletePrivlegeMenu(Long privilegeId, Long menuId)
    {
        authorityBO.deletePrivlegeMenu(privilegeId, menuId);
    }
    
    @Override
    public void deletePrivlegeOperate(Long privilegeId, Long operateId)
    {
        authorityBO.deletePrivlegeOperate(privilegeId, operateId);
    }
    
    @Override
    public UserLoginVO getUserLoginInfoByUserId(Long userId, Integer userType)
    {
        return userBo.getUserLoginInfoByUserId(userId, userType);
    }
    
    @Override
    public List<SysLanguageDef> getLanguageDef(Integer languageCode)
    {
        return systemBo.getLanguageDef(languageCode);
    }
    
    @Override
    public String generateActivityMessageByActionId(Long actionId, String... params)
    {
        
        return templateBO.generateActivityMessageByActionId(actionId, params);
    }
    
    @Override
    public Long getCampaignTaskParamValue(String actionId, Long campaignId)
    {
        return systemBo.getCampaignTaskParamValue(actionId, campaignId);
    }
    
    @Override
    public void updateUserIsFirstLoginFlagToFalse(String loginAcct, Integer userTypePortal)
    {
        userBo.updateUserIsFirstLoginFlagToFalse(loginAcct, userTypePortal);
        
    }
    
    @Override
    public OperaterVO updateOperator(OperaterVO operator)
    {
        if (ValidateUtil.isNull(operator))
        {
            LOG.debug("the method updateOperator,input parameter operator is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.OPERATOR });
        }
        if (ValidateUtil.isNull(operator.getOperaterId()))
        {
            LOG.debug("the method updateOperator,input parameter operator.operatorId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{SysConstantDefine.OPERATOR_ID });
        }
        return authorityBO.updateOperator(operator);
    }
    
    @Override
    public PageInfo<CampaignTypeDefine> getCampaginTypeTotalSize(String campaignTypeId)
    {
        return systemBo.getCampaginTypeTotalSize(campaignTypeId);
    }
    
    @Override
    public PageInfo<SysLanguageDef> getLanguageDefTotalSize(Integer languageCode)
    {
        return systemBo.getLanguageDefTotalSize(languageCode);
    }
    
    @Override
    public PageInfo<CampaignAction> getActionTotalSizeByCampaginType(Integer campaginType, Integer pageNo, Integer pageSize)
    {
        return systemBo.getActionTotalSizeByCampaginType(campaginType, pageNo, pageSize);
    }
    
    @Override
    public SysEnumGroupRel getSysEnumGroupRel(Long relId)
    {
        if (relId== null)
        {
            LOG.info("the method getSysEnumGroupRel ,the input parameter relId can't null");
            throw new MeoException("getSysEnumGroupRel ,the input parameter relId can't null");
        }
        return systemBo.getSysEnumGroupRel(relId);
    }
    
    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumIdAndGroupId(Long enumId, Long groupId)
    {
        if (enumId== null&& groupId== null)
        {
            LOG.info("the method getSysEnumGroupRelListByEnumIdAndGroupId the input parameters enumId and groupId can't all is empty");
            throw new MeoException(
                    "getSysEnumGroupRelListByEnumIdAndGroupId the input parameters enumId and groupId can't all is empty");
        }
        return systemBo.getSysEnumGroupRelListByEnumIdAndGroupId(enumId, groupId);
    }

    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds)
    {
        return systemBo.getSysEnumGroupRelListByGroupIds(groupIds);
    }

    @Override
    public void createSysMessageTemplateDef(MessageTemplateDefVO template)
    {
        templateBO.createSysMessageTemplateDef(template);
    }
    
    public void modifySysMessageTemplateDef(MessageTemplateDefVO template)
    {
        templateBO.modifySysMessageTemplateDef(template);
    }

    @Override
    public void deleteSysMessageTemplateDef(Long templateId)
    {
        templateBO.deleteSysMessageTemplateDef(templateId);
    }

    @Override
    public PageInfo<MessageTemplateDefVO> getMessageTemplateList(Integer pageNo, Integer pageSize, Long templateId, String templateName)
    {
        return templateBO.getMessageTemplateList(pageNo, pageSize, templateId, templateName);
    }

    @Override
    public MessageTemplateDefVO getSysMessageTemplateDefByTemplateId(Long templateId)
    {
        return templateBO.getSysMessageTemplateDefByTemplateId(templateId);
    }
    
    @Override
    public PageInfo<SysEnumGroupDef> getCampaignEnumGroup(CampaignEnumGroupQueryConditionVO conditionVO)
    {
       return systemBo.getCampaignEnumGroup(conditionVO);
    }
    
    @Override
    public void modifyCampaignGroup(EnumGroupDefVO enumGroupDefVO)
    {
        systemBo.modifyCampaignEnumGroup(enumGroupDefVO);
    }

    @Override
    public void deleteCampaignGroup(Long groupDefID)
    {
        systemBo.deleteCampaignEnumGroup(groupDefID);        
    }

    @Override
    public void createSysTemplateParamDefine(TemplateParamDefVO templateParam)
    {
        templateBO.createSysTemplateParamDefine(templateParam);
    }

    @Override
    public void modifySysTemplateParamDefine(TemplateParamDefVO templateParam)
    {
        templateBO.modifySysTemplateParamDefine(templateParam);
    }

    @Override
    public void deleteSysTemplateParamDefine(Long paramId)
    {
        templateBO.deleteSysTemplateParamDefine(paramId);
    }

    @Override
    public PageInfo<TemplateParamDefVO> getSysTemplateParamList(Integer pageNo, Integer pageSize, Long paramId, String paramName)
    {
        return templateBO.getSysTemplateParamList(pageNo, pageSize, paramId, paramName);
    }

    @Override
    public SysCustomerLogin querySysCustLoginByCustId(Long objectId)
    {
        return userBo.querySysCustLoginByCustId(objectId);
    }

    @Override
    public List<SysEnumGroupDef> getSysEnumGroupRelListByEnumId(Long enumId)
    {
        return systemBo.getSysEnumGroupRelListByEnumId(enumId);
    }

    public TemplateParamDefVO getTemplateParamDefVOByParamId(Long paramId)
    {
        SysTemplateParamDef  sysTemplateParamDef = templateBO.getSysTemplateParamDefineByParamId(paramId);
        TemplateParamDefVO templateParamDefVO = new TemplateParamDefVO();
        if (ValidateUtil.isEmpty(sysTemplateParamDef))
        {
            return templateParamDefVO;
        }   
        templateParamDefVO.setParamDesc(sysTemplateParamDef.getParamDesc());
        templateParamDefVO.setParamId(sysTemplateParamDef.getParamId());
        templateParamDefVO.setParamName(sysTemplateParamDef.getParamName());
        return templateParamDefVO;
    }
    
}
