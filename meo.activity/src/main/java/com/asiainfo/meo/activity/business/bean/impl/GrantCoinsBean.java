package com.asiainfo.meo.activity.business.bean.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.model.entity.CmRewardActivity;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.define.ActivityEnumCodeDefine;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class GrantCoinsBean implements ActivityBean
{
    @Resource
    ActivityRepository activityRepository;
    
    @Resource
    ActivityRserviceBO activityRserviceBO;
    
    private Long       activityId;
    
    private Long       actionId;
    
    private Long       custId;
    
    private String     campaignNo;
    
    private Long       taskId;
    
    private Long       campaignId;
    
    public void init(Object... input)
    {
        activityId = null;
        actionId = null;
        custId = (Long) input[0];
        campaignId = (Long) input[1];
        actionId = (Long) input[2];
        activityId = (Long) input[3];
        taskId = (Long) input[4];
        activityRepository = ServiceLocatorFactory.getService(ActivityRepository.class);
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        
    }
    
    public void validate(Object... input)
    {
        
    }
    
    public Object business(Object... input)
    {
        Long coins = activityRserviceBO.getRewardCoins(taskId);
        if (ValidateUtil.isNull(coins))
        {
            return null;
        }
        CmRewardActivity rewardActivity = new CmRewardActivity();
        rewardActivity.setActionId(actionId);
        rewardActivity.setActivityId(activityId);
        rewardActivity.setCustId(custId);
        rewardActivity.setCampaignNo(campaignNo);
        rewardActivity.setRewardType(CmRewardActivity.REWARD_TYPE_COIN);
        
        rewardActivity.setRewardValue(coins);
        rewardActivity.setRewardUnit(ActivityEnumCodeDefine.UNIT_COIN);
        
        rewardActivity.setTaskId(taskId);
        activityRepository.saveRewardActivity(rewardActivity);
        Asset asset = new Asset();
        asset.setAmount(coins);
        asset.setAssetType(Asset.ASSET_TYPE_COINS);
        asset.setCustId(custId);
        asset.setUnit(ActivityEnumCodeDefine.UNIT_COIN);
        activityRserviceBO.rewardAsset(asset);
        return null;
    }
    
}
