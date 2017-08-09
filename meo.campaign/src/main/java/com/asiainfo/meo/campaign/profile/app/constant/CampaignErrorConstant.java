package com.asiainfo.meo.campaign.profile.app.constant;

public class CampaignErrorConstant
{
    public static final String ENTITY_IS_EMPTY                                                      = "4005001001";
    
    public static final String HAS_DUPLICATED                                                       = "4005001002";
    
    public static final String HAS_EXISTED                                                          = "4005001003";
    
    public static final String GET_FAILED                                                           = "4005001004";
    
    public static final String NOT_EXIST                                                            = "4005001005";
    
    public static final String NOT_FOUND                                                            = "4005001006";
    
    public static final String CAMPAIGN_IS_NOT_FOUND                                                = "4005001007";
    
    public static final String CAMPAIGN_PRICE_IS_NOT_FOUND                                          = "4005001008";
    
    public static final String CAMPAIGN_PROPERTY_IS_NOT_FOUND                                       = "4005001009";
    
    public static final String TASK_PARAM_INSTANCE_IS_NOT_FOUND                                     = "4005001010";
    
    public static final String CAMPAIGN_TASK_IS_NOT_FOUND_BY_ID                                     = "4005001011";
    
    public static final String CAMPAIGN_TASK_IS_NOT_FOUND_BY_ACTION_ID                              = "4005001012";
    
    public static final String CAMPAIGN_TASK_RULE_IS_NOT_FOUND_BY_ACTION_ID                         = "4005001013";
    
    public static final String CAMPAIGN_STS_IS_NOT_DRAFT                                            = "4005001014";
    
    public static final String CAMPAIGN_STS_IS_DRAFT                                                = "4005001015";
    
    public static final String CAMPAIGN_VALIDDATE_IS_INCORRECT                                      = "4005001016";
    
    public static final String CAMPAIGN_STS_IS_UNEDIT                                               = "4005001017";
    
    public static final String CAMPAIGN_PRICE_REL_IS_NOT_FOUND                                      = "4005001018";
    
    public static final String SAVE_CAMAPIGN_SHOULD_BE_NO_PRICEID                                   = "4005001019";
    
    public static final String PRICE_TYPE_CAN_NOT_BE_REPEATED                                       = "4005001020";
    
    public static final String SAVE_CAMAPIGN_SHOULD_BE_NO_PROPERTYID                                = "4005001021";
    
    public static final String SAVE_CAMAPIGN_SHOULD_BE_NO_TASKID                                    = "4005001022";
    
    public static final String SAVE_CAMAPIGN_SHOULD_BE_NO_PARAMTASKID                               = "4005001023";
    
    public static final String PUBLISH_CAMPAIGN_PRICE_CAN_NOT_BE_NULL                               = "4005001024";
    
    public static final String CURRENT_CAMPAIGN_CAN_NOT_BE_MODIFIED                                 = "4005001025";
    
    public static final String MANDATORY_CAMPAIGN_PROPERTY_INCOMPLETE                               = "4005001026";
    
    public static final String CAMPAIGN_STS_IS_NOT_RENEW                                            = "4005001027";
    
    public static final String ACTIONID_CAN_NOT_NULL                                                = "4005001028";
    
    public static final String ACTIONDEFINE_IS_NOT_FOUND                                            = "4005001029";
    
    public static final String SYS_ACTION_PARAM_IS_NOT_FOUND                                        = "4005001030";
    
    public static final String SYS_ACTION_PARAM_ID_CAN_NOT_BE_NULL                                  = "4005001031";
    
    public static final String THE_CAMPAIGN_STS_CAN_NOT_BE_PUBLISHED                                = "4005001032";
    
    public static final String CAMPAIGN_PRICE_PRICETYPE_CAN_NOT_NULL                                = "4005001033";
    
    public static final String CAMPAIGN_PRICE_UNITPRICE_CAN_NOT_NULL                                = "4005001034";
    
    public static final String CAMPAIGN_PRICE_CHARGEUNIT_CAN_NOT_NULL                               = "4005001035";
    
    public static final String CAMPAIGN_PRICE_UNIT_CAN_NOT_NULL                                     = "4005001036";
    
    public static final String CAMPAIGN_TASK_PARAM_ID_CAN_NOT_NULL                                  = "4005001037";
    
    public static final String CAMPAIGN_TASK_PARAM_VALUE_CAN_NOT_NULL                               = "4005001038";
    
    public static final String CAMPAIGN_TRIGGER_TASK_PARAM_LIST_CAN_NOT_NULL                        = "4005001039";
    
    public static final String CAMPAIGN_TRIGGER_TASK_PARAM_ACTIONID_CAN_NOT_NULL                    = "4005001040";
    
    public static final String CAMPAIGN_PARENT_TASK_IS_WORRY_RULE                                   = "4005001041";
    
    public static final String PARTNER_IS_NOT_FOUND                                                 = "4005001042";
    
    public static final String CAMPAIGN_PROPERTY_IS_NOT_FOUND_BY_PROPERTYCODE_AND_CAMAPIGNTYPE      = "4005001043";
    
    public static final String VALID_CAMPAIGN_IS_NOT_FOUND_BY_CAMPAIGNNO                            = "4005001044";
    
    public static final String VALID_ISEDIT_CAMPAIGN_IN_THE_SAME_CAMPAIGNNO_SHOULD_ONLY_ONE_VERSION = "4005001045";
    
    public static final String VALID_CONTRACT_IS_NOT_FOUND_BY_PARTNER_ID                            = "4005011046";
    
    public static final String RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_ACTIVE_CAMPAIGN_VALIDDATE        = "4005011047";
    
    public static final String RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_CAMPAIGN_CAMPAIGNBEGINDATE       = "4005011048";
    
    public static final String RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_TOMORROW                         = "4005011049";
    
    public static final String RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_RENEW_CAMPAIGN_VALIDDATE   = "4005011050";
    
    public static final String CAMPAIGNBEGINDATE_CAN_NOT_LT_CONTRACT_VALIDDATE                      = "4005011051";
    
    public static final String CAMPAIGNENDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE                        = "4005011052";
    
    public static final String CAMPAIGN_VALIDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE                     = "4005011053";
    
    public static final String CAMPAIGNBEGINDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE                    = "4005011054";
    
    public static final String CAMPAIGNENDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE                      = "4005011055";
    
    public static final String CAMPAIGN_VALIDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE                   = "4005011056";
    
    public static final String CAMPAIGNBEGINDATE_CAN_NOT_LT_TOMORROW                                = "4005011057";
    
    public static final String CAMPAIGNENDDATE_CAN_NOT_LT_TOMORROW                                  = "4005011058";
    
    public static final String CAMPAIGN_VALIDDATE_CAN_NOT_LT_TOMORROW                               = "4005011059";
    
    public static final String CAMPAIGNBEGINDATE_CAN_NOT_GT_CAMPAIGNENDDATE                         = "4005011060";
    
    public static final String CAMPAIGNENDDATE_CAN_NOT_LT_CAMPAIGN_VALIDDATE                        = "4005011061";
    
    public static final String CAMPAIGN_AVAILABLE_VERSION_IS_NOT_FOUND_BY_CAMPAIGNNO                = "4005011062";
    
    public static final String CAMPAIGN_AVAILABLE_VERSION_NUM_CAN_NOT_GT_TWO                        = "4005011063";
    
    public static final String CAMPAIGN_AVAILABLE_VERSION_NUM_IS_TWO                                = "4005011064";
    
    public static final String RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE               = "4005011065";
    
    public static final String RENEW_CAMPAIGN_VALIDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE             = "4005011066";
    
    public static final String RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_CONTRACT_VALIDDATE         = "4005011067";
    
    public static final String RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_GT_CONTRACT_EXPIREDDATE       = "4005011068";
    
    public static final String RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_ACTIVE_CAMPAIGN_VALIDDATE  = "4005011069";
    
    public static final String RENEW_CAMPAIGN_CAMPAIGNENDDATE_CAN_NOT_LT_TOMORROW                   = "4005011070";
    
    public static final String PARTNER_STS_IS_NOT_ACTIVE                                            = "4005011071";
    
    public static final String FIRST_VERSION_CAMPAIGN_VALIDDATE_CAN_NOT_GT_CAMPAIGN_STARTDATE       = "4005011072";
    
    public static final String QUERY_TASKPARAMINSTANCE_BY_TASKID_PARAMID_PARAM_CAN_NOT_NULL         = "4005011073";
    
    public static final String QUERY_TASKPARAMINSTANCE_LIST_BY_TASKID_TASKID_CAN_NOT_NULL           = "4005011074";
    
    public static final String CAN_NOT_FOUND_VALID_CAMPAIGN_TYPE                                    = "4005011075";
    
    public static final String CAMPAIGN_TYPE_MORE_THAN_ONE                                          = "4005011076";
    
    public static final String CAMPAIGN_PROPERTYCODE_EXIST                                          = "4005011077";
    
    public static final String SAVE_CAMPAIGN_TASK_DETAIL_FAILURE                                    = "4005011078";
    
    public static final String FIRST_VERSION_CAMPAIGN_VAILDDATE_NOT_EQUAL_STARTDATE                 = "4005011079";
    
    public static final String CAMPAIGNPROPERTYDEF_MANDATORY_INCORRECT                              = "4005011080";
    
    public static final String NOT_FOUND_SYSENUMGROUPREL                                            = "4005011081";
    
    private CampaignErrorConstant()
    {
    }
    
}
