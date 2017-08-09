package com.asiainfo.meo.activity.app.bo.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.activity.app.bo.ActivityBO;
import com.asiainfo.meo.activity.app.model.vo.CmActivityRecordHisVO;
import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.bean.impl.ActivateResourceBean;
import com.asiainfo.meo.activity.business.bean.impl.FinishDownLoadActivityBean;
import com.asiainfo.meo.activity.business.bean.impl.RedeemProductBean;
import com.asiainfo.meo.activity.business.model.entity.CmActivityRecordHis;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.vo.JoinBusinessActionVO;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.activity.manager.factory.ActivityManagerFactory;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerBundle;
import com.asiainfo.meo.activity.manager.model.vo.ActivityManagerConduit;
import com.asiainfo.meo.activity.manager.proxy.ActivityManagerProxy;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.component.ActivityComponent;
import com.asiainfo.meo.define.ActivityEnumCodeDefine;

public class ActivityBOImpl implements ActivityBO
{
    @Resource
    ActivityComponent activityComp;
    
    @Resource
    ActivityRepository activityRepository;
    
    public void summaryCompaignActivity() throws MeoException
    {
        
    }
    
    public void downLoadActivity(Long custId, Long campaignId)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();
        ActivityManagerConduit conduit = new ActivityManagerConduit(FinishDownLoadActivityBean.class);
        ActivityManagerBundle bundle = new ActivityManagerBundle();
        bundle.setCustId(123456l);
        conduit.setBundle(bundle);
        conduit.addParams(custId);
        conduit.addParams(campaignId);
        proxy.startActivity(conduit);        
    }
    
    public Boolean joinCampaign(Long custId, Long campaignId, String actionId)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();        
        ActivityBean bean = ActivityManagerFactory.getActivityBean(ActivityEnumCodeDefine.ACTIVITY_JOIN_CAMPAIGN);
        ActivityManagerConduit conduit = new ActivityManagerConduit(bean.getClass());
        conduit.setActivity(false);
        conduit.addParams(custId);
        conduit.addParams(campaignId);
        conduit.addParams(actionId);
        return (Boolean) proxy.startActivity(conduit);
        
    }
    
    public Boolean executeJoinCampaignActionRule(Long custId, Long campaignId, String actionId)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();        
        ActivityBean bean = ActivityManagerFactory.getActivityBean(ActivityEnumCodeDefine.ACTIVITY_JOIN_CAMPAIGN, ActivityEnumCodeDefine.ACTION_RULE_TYPE);
        ActivityManagerConduit conduit = new ActivityManagerConduit(bean.getClass());
        conduit.setActivity(false);
        conduit.addParams(custId);
        conduit.addParams(campaignId);
        conduit.addParams(actionId);
        return (Boolean) proxy.startActivity(conduit);        
    }
    
    public void redeemMeoProduct(Long custId, Long meoCampaignId)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();
        ActivityManagerConduit conduit = new ActivityManagerConduit(RedeemProductBean.class);
        ActivityManagerBundle bundle = new ActivityManagerBundle();
        bundle.setActionId(ActivityEnumCodeDefine.ACTIVITY_REDEEM_PRODUCT);
        bundle.setCustId(custId);
        conduit.setBundle(bundle);
        conduit.addParams(custId);
        conduit.addParams(meoCampaignId);
        proxy.startActivity(conduit);
    }
    
    public void activateCustomerResource(Long custId, Long resourceId)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();
        ActivityManagerConduit conduit = new ActivityManagerConduit(ActivateResourceBean.class);
        ActivityManagerBundle bundle = new ActivityManagerBundle();
        bundle.setActionId(ActivityEnumCodeDefine.ACTIVITY_ACTIVE_RESOURCE);
        bundle.setCustId(custId);
        conduit.setBundle(bundle);
        conduit.addParams(custId);
        conduit.addParams(resourceId);
        proxy.startActivity(conduit);
    }
    
    public void executeActivity(ActivityManagerConduit conduit)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();
        proxy.startActivity(conduit);
    }
    
    public void executeBusinessAction(JoinBusinessActionVO joinAction)
    {
        ActivityManagerProxy proxy = ActivityManagerFactory.getActivityManagerProxy();
        ActivityManagerConduit conduit = new ActivityManagerConduit(joinAction.getCustId(), joinAction.getActionId(),
                joinAction.getEntityId(),joinAction.getEntityType());
        conduit.addParams(joinAction.getCustId());
        conduit.addParams(joinAction.getEntityId());
        proxy.startActivity(conduit);
    }
    
    public void executeAction(JoinBusinessActionVO joinAction)
    {
        
    }

    @Override
    public CmActivityRecordHisVO getCmActivityRecordHis(Long activityId)
    {
        CmActivityRecordHis cmActivityRecordHis = activityRepository.getCmActivityRecordHis(activityId);
        if (ValidateUtil.isNotEmpty(cmActivityRecordHis))
        {
            CmActivityRecordHisVO cmActivityRecordHisVO = new CmActivityRecordHisVO();
            cmActivityRecordHisVO.setActivityId(cmActivityRecordHis.getActivityId());
            cmActivityRecordHisVO.setContent(cmActivityRecordHis.getContent());
            return cmActivityRecordHisVO;
        }
        return null;
    }

    @Override
    public CmCampaignActivity getCmCampaignActivityByCodition(String campaignNo, Long custId, String actionId)
    {
        return activityRepository.getCmCampaignActivityByCodition(campaignNo, custId, actionId);
    }   
    
}
