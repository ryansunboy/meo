package com.asiainfo.meo.web.component;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.activity.business.model.vo.JoinBusinessActionVO;
import com.asiainfo.meo.activity.business.model.vo.JoinCampaignVO;
import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.service.provide.ActivityPserviceBO;
import com.asiainfo.meo.web.activity.model.vo.UIJoinCampaignVO;

public class ActivityComponent
{
    @Resource
    ActivityPserviceBO       activityPserviceBO;
    private static final Log logger = LogFactory.getLog(ActivityComponent.class);
    
    public Boolean joinCampaignActivity(final Long campaignId, final String actionId)
    {
        Boolean coinEligible = new Boolean(false);
        logger.info("******begin to invoke joinCampaign method*****");
        JoinCampaignVO join = new JoinCampaignVO();
        join.setCampaignId(campaignId);
        join.setActionId(actionId);
        join.setCustId(BoContext.getBoContext().getUserId());
        coinEligible = activityPserviceBO.joinCampaign(join);
        logger.info("******end to invoke joinCampaign method*****");
        return coinEligible;
    }

    public void activeCustomerResource(Long resourceId)
    {
        Long custId = BoContext.getBoContext().getUserId();
        activityPserviceBO.activateCustomerResource(custId, resourceId);;
    }

    public void redeemProduct(Long campaignId)
    {
        Long custId = BoContext.getBoContext().getUserId();
        activityPserviceBO.redeemMeoProduct(custId, campaignId);
    }

    public void executeCampaignActivity(UIJoinCampaignVO joinCampaign)
    {
        Long custId = BoContext.getBoContext().getUserId();
        JoinBusinessActionVO join = new JoinBusinessActionVO();
        join.setActionId(joinCampaign.getActionId());
        join.setCustId(custId);
        join.setEntityId(joinCampaign.getCampaignId());
        activityPserviceBO.executeCampaignActivity(join);
    }

   
    
}
