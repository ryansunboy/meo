package com.asiainfo.meo.campaign.define;
/**
 * 
  * @Description: campaignType define 
  * @Description: campaignType define                 
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author zhengzy                                                                                                                                                                                                                                                                           
  * @Date 2015年7月21日 下午4:53:03 
  * @version 1.0
 */
public class CampaignTypeDefine
{
    private CampaignTypeDefine()
    {
    }
    
    //1   App Downloading to earn coin
    public static final int APP_DOWNLOAD_EARN_COIN = 1;
    
    //2   Campaign Type II: Registration/Subscription to earn data voucher/coin
    public static final int REGISTRATION_SUBSCRIPTION_EARN_DATA_VOCHER_COIN = 2;
    
    //3   quiz
    public static final int QUIZ = 3;
    
    //4   MEO Promotion Campaign
    public static final int MEO_PROMOTION_CAMPAIGN = 4;

    //5   App Specific
    public static final int APP_SPECIFIC = 5;
    
    //6   Data Package Redemption
    public static final int DATA_PACKAGE_REDEMPTION = 6;
    
    //7   Sponsored download usage
    public static final int SPONSORED_DOWNLOAD_USAGE = 7;

}
