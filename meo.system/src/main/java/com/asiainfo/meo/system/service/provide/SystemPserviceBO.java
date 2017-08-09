package com.asiainfo.meo.system.service.provide;

import java.io.IOException;
import java.util.List;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
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
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysActionParamDef;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.entity.SysLanguageDef;
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
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamDef;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;
import com.asiainfo.meo.system.template.app.model.vo.TemplateParamDefVO;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;


public interface SystemPserviceBO
{
    public void createEnumDef(EnumDefine enumDef) throws MeoException, IOException;
    
    public void modifyEnumDef(EnumDefine enumDef) ;
    
    public void deactivateEnum(String enumCode) ;
    
    public EnumDefine getEnumDefByEnumCode(String enumCode,Integer enumType) ;
    
    public CategoryDefine getCategoryDefByCateId(Long categoryId) ;
    
    public List<CategoryDefine> getCategoryDefByParentCateId(Long partnerCategoryId) ;
    
    public void createCategoryDefine(CategoryDefine cateDef) ;
    
    public void modifyCategoryDefine(CategoryDefine cateDef) ;
    
    public void deactivateCategoryDefine(String cateId) ;
    
    public String getLanguageNameByCode(Integer languageCode) ;
    
    public UserLoginVO getUserLoginInfo(String loginAcct,Integer userType);
    
    public UserLoginVO getUserLoginInfoByUserId(Long userId, Integer userType);

    public void createUserLoginInfo( UserLoginVO userLoginVo);
    
    public Integer getObjectTypeByObjectId(Long objectId);
    
    public SysObjectVO getObjectByObjectId(Long objectId);
    
    public void createSysObjectUser(SysObjectVO object);
    
    public void validBundleAccount(String bundleAct,String validPwd);
    
    public void updatePassword(SysPasswordVO password);
    
    public void activateLoginAcct(Long objectId,Integer objectType);
    
    public Long getSystemDefaultMeasureUnit(Integer measureId,Long amount);
    
    /**
     * 
      * @Description: campaign鐨勫垎绫�
      * @Description: get the campaign type
      * @modifyReason: 
      * @return       
     */
    public PageInfo<CategoryDefine> getCategoryEnumTotalSize(Long categoryId, Integer categoryType); 
   
    
    /**
     * 
      * @Description: 濉啓璁＄畻璐圭敤鐨勬柟寮�
      * @Description: get the charge unit
      * @modifyReason: 
      * @return
     */
    public List<EnumDefine> getEnumByCode(String enumCode, String enumType);
   
    
    public List<MeasureDefine> getMeasureDef(Long measureId, Long measureTypeId);
    
    /**
     * 
      * @Description: 鑾峰彇鍔ㄤ綔淇℃伅
      * @Description: get the action definition
      * @modifyReason: 
      * @return
     */
    public List<ActionDefine> getActionDefine(Long actionId, Integer actionType);
    
    /**
     * 
      * @Description: 
      * @Description: get the list of campaign type define
      * @modifyReason: 
      * @author zhoujj
      * @return
     */
    public List<CampaignTypeDefine> getCampaginType(String campaignTypeId);

    public SysActionParamDefineVO getSysActionParamDefByParamId(Long paramId); 

    /**
     * 
      * @Description: 鐢熸垚涓�鎬у瘑鐮�
      * @Description: generate otp
      * @modifyReason: 
      * @author liuyang
      * @param sysOtp
      * @return
     */
    public OneTimePasswordVO genarateOtp(SysOtpVO sysOtp); 
    
    /**
     * 
      * @Description: 灏唎tp淇敼涓哄凡浣跨敤
      * @Description: set the OTP to be used
      * @modifyReason: 
      * @author liuyang
      * @param sysOtp
     */
    public void setOTPToUsed(SysOtpVO sysOtp);
    /**
     * 
      * @Description: 鏍规嵁configValueCode鑾峰彇绯荤粺閰嶇疆甯搁噺淇℃伅
      * @Description: get the system configuration define information by configValueCode
      * @modifyReason: 
      * @author liuyang
      * @param configValueCode
      * @return
     */
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode);

    /**
     * 
      * @Description: 鏍规嵁configCode鑾峰彇绯荤粺閰嶇疆甯搁噺淇℃伅
      * @Description: get the system configuration define information by configCode
      * @modifyReason: 
      * @author liuyang
      * @param configCode
      * @return
     */
    public List<SysConfigurationDef> getConfigDefByConfigCode(String configCode);

    /**
     * 
      * @Description: 淇敼partner鐧诲綍璐︽埛
      * @Description: modify the partner login account
      * @modifyReason: 
      * @author liuyang
      * @param partnerId
      * @param newLoginAcct
     */
    public void updatePartnerLoginAcct(Long partnerId,String newLoginAcct);
    
    public List<SysCountryDef> getCountryDef(Long countryCode);
    
    public List<SysProvDef> getProvDef(Long provCode);
    
    public List<SysRegionDef> getRegionDef(Long regionCode);
    
    /**
     *  @Description: 缁熻瀹㈡埛鍙傚姞campaign鐨勭粺璁℃暟
      * @Description: statistics the customer participant 
      * @modifyReason: 
      * @author liuyang
      * @param paticipant
     */
    public void createEntityParticipant(CmEntityParticipant paticipant);

    public CmEntityParticipant getEntityParticipantByCamapginNo(String campaignNo);

    public int addEntityParticipantCount(String campaignNo,int paticipant);
    
    public PageInfo<SysActionParamDef> getSysActionParamDefByActionId(SysActionParamDefQueryConditionVO conditionVO);
    
    public void createCampaignGroup(EnumGroupDefVO sysEnumGroupDef);
    
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode);
 
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds);
    
    public void createProductCategory(SysCategoryDefVO sysCategoryDefVO);
    
    /**
     * 
      * @Description: 鏌ヨ瑙掕壊鍒楄〃
      * @Description: query role list
      * @modifyReason: 
      * @author zhengzy
      * @param conditionVO
      * @return
     */
    public PageInfo<UserRoleVO> getRoleInfoList(UserRoleQueryConditionVO conditionVO);
    
    /**
     * 
      * @Description: 鏂板瑙掕壊
      * @Description: add role
      * @modifyReason: 
      * @author zhengzy
      * @param userRole
      * @return
     */
    public UserRoleVO addRole(UserRoleVO userRole);
    
    /**
     * 
      * @Description: 淇敼瑙掕壊
      * @Description: update role
      * @modifyReason: 
      * @author zhengzy
      * @param userRole
      * @return
     */
    public UserRoleVO updateRole(UserRoleVO userRole);
    
    /**
     * 
     * @Description: 鏍规嵁瑙掕壊Id鍒犻櫎瑙掕壊
      * @Description: delete role by roleId
      * @modifyReason: 
      * @author zhengzy
      * @param roleId
     */
    public void deleteRoleByRoleId(Long roleId);
    
    public PageInfo<UserMenuVO> getMenuList(MenuQueryConditionVO conditionVO);
    
    public ContractFileTemplateVO createContractFileTemplate(ContractFileTemplateVO contractFileTemplate);
    
    public ContractFileTemplateVO modifyContractFileTemplate(ContractFileTemplateVO contractFileTemplate);
    
    public void deleteContractTemplate(Long templateid);
    
    public ContractFileTemplateVO getContractFileTemplateVOByTemplateId(Long templateId);
    
    public PageInfo<ContractFileTemplateVO> getContractFileTemplateVOList(ContractFileTemplateQueryConditionVO conditionVO);
    
    public Long saveMenu(UserMenuVO sysMenu);
    
    public void modifyMenu(UserMenuVO userMenuVO);
    
    public void deleteMenu(Long menuId);
    
    public PageInfo<UserOperaterVO> getOperationList(OperatorQueryConditionVO conditionsVO);
    
    public Long saveOperation(String operName);
    
    public void deleteOperation(Long operId);
    
    public void saveRolePrivilege(RolePrivilegeVO rolePrivilegeVO);

    public void deleteRolePrivilege(Long roleId);
    
    /**
     * @Description: 淇濆瓨鐢ㄦ埛淇℃伅
     * @Description: save systemUser information
     * @author zhaozx
     * @param systemUser
     */
    SystemUserVO saveSystemUser(SystemUserVO systemUser);
    
   /**
     * @Description: 鏌ヨ鐢ㄦ埛淇℃伅
     * @Description: get systemUser information
     * @author zhaozx
     * @param userId
     * @return
     */
    
    SystemUserVO getSystemUser(Long userId);
   
   /**
    * 
     * @Description: 淇敼鐢ㄦ埛淇℃伅
     * @Description: update the systemUser
     * @modifyReason: 
     * @author zhengzy
     * @param systemUser
     * @return
    */
    SystemUserVO updateSystemUser(SystemUserVO systemUser);
   
   /**
    * 
     * @Description: 鍒犻櫎鐢ㄦ埛淇℃伅
     * @Description: delete systemUser by userId
     * @modifyReason: 
     * @author zhengzy
     * @param userId
    */
   void deleteSystemUserByuserId(Long userId);
   
   /**
    * 
     * @Description: 鏌ヨsystemUser鍒楄〃
     * @Description: query systemUser list
     * @modifyReason: 
     * @author zhengzy
     * @param systemUserQueryConditionVO
     * @return
    */
   public PageInfo<SystemUserVO> getSystemUserVOPageInfo(SystemUserQueryConditionVO systemUserQueryConditionVO);
   
   public void resetSystmeUserPwd(Long userId);
   
   
   /**
    * @Description: 鏌ヨ鎵�湁瑙掕壊鐨勬潈闄�
    * @Description: get all role privilege
    * @author zhengzy
    * @param pageNo
    * @param pageSize
    * @return
    */
  public PageInfo<RPrivilegeVO> getAllRolePrivilegeList(RolePrivilegeQueryConditionVO conditionVO);
  
  /**
   * 
    * @Description: 鏍规嵁鐢ㄦ埛Id鏌ヨ鐢ㄦ埛鏉冮檺
    * @Description: query user privilege list by userId
    * @modifyReason: 
    * @author zhengzy
    * @param userId
    * @return
   */
  public List<UPrivilegeVO> getUPrivilegeListByUserId(Long userId);
  
  public void deletePrivlegeMenu(Long privilegeId, Long menuId);
  
  public void deletePrivlegeOperate(Long privilegeId, Long operateId);
  
  public List<SysLanguageDef> getLanguageDef(Integer languageCode);
  
  public String generateActivityMessageByActionId(Long actionId,String... params);
  
  public Long getCampaignTaskParamValue(String actionId, Long campaignId);

  public void updateUserIsFirstLoginFlagToFalse(String loginAcct, Integer userTypePortal);
    
  public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType);

    /**
     * 
      * @Description: update operator
      * @modifyReason: 
      * @author Thanapol
      * @param operator
      * @return
     */
    public OperaterVO updateOperator(OperaterVO operator);
    
    public PageInfo<MeasureDefine> getMeasureDefTotalSize(Long measureId, Long measureTypeId);
    
    public PageInfo<EnumDefine> getEnumTypeTotalSize(String enumCode, String enumType);
    
    public PageInfo<CampaignTypeDefine> getCampaginTypeTotalSize(String campaignTypeId);
    
    public PageInfo<ActionDefine> getActionDefineTotalSize(Long actionId, Integer actionType);
    
    public PageInfo<SysLanguageDef> getLanguageDefTotalSize(Integer languageCode);
    
    public PageInfo<SysCountryDef> getCountryDefTotalSize(Long countryCode);
    
    public PageInfo<CampaignAction> getActionTotalSizeByCampaginType(Integer campaginType, Integer pageNo, Integer pageSize);
    
    /**
     * get the sysEnumGroupRel by relId
      * @Description: (鐢ㄤ腑鏂囨弿杩颁竴涓嬭繖涓柟娉�
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param relId
      * @return
     */
    public SysEnumGroupRel getSysEnumGroupRel(Long relId);
    
    /**
     * get the sysEnumGroupRel list by enumId and groupId
      * @Description: (鐢ㄤ腑鏂囨弿杩颁竴涓嬭繖涓柟娉�
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param enumId
      * @param groupId
      * @return
     */
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumIdAndGroupId(Long enumId,Long groupId);
    
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds);
    
    public void createSysMessageTemplateDef(MessageTemplateDefVO template);
    
    public void modifySysMessageTemplateDef(MessageTemplateDefVO template);
    
    public void deleteSysMessageTemplateDef(Long templateId);
    
    public PageInfo<MessageTemplateDefVO> getMessageTemplateList(Integer pageNo, Integer pageSize, Long templateId, String templateName);
    
    public MessageTemplateDefVO getSysMessageTemplateDefByTemplateId(Long templateId);
    
    public void modifyCampaignGroup(EnumGroupDefVO sysEnumGroupDef);
    
    public void deleteCampaignGroup(Long groupDefID);
    
    public PageInfo<SysEnumGroupDef> getCampaignEnumGroup(CampaignEnumGroupQueryConditionVO conditionVO);
    
    public void createSysTemplateParamDefine(TemplateParamDefVO templateParam);
    
    public void modifySysTemplateParamDefine(TemplateParamDefVO templateParam);
    
    public void deleteSysTemplateParamDefine(Long paramId);
    
    public PageInfo<TemplateParamDefVO> getSysTemplateParamList(Integer pageNo, Integer pageSize, Long paramId, String paramName);

    public SysCustomerLogin querySysCustLoginByCustId(Long objectId);
    
    public List<SysEnumGroupDef> getSysEnumGroupRelListByEnumId(Long enumId);
    
    public TemplateParamDefVO getTemplateParamDefVOByParamId(Long paramId);
}
