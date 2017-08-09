package com.asiainfo.meo.web.activity.controller.v1;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.utils.MessageUtil;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.web.activity.model.vo.UICoinsEligibleVO;
import com.asiainfo.meo.web.activity.model.vo.UIJoinCampaignVO;
import com.asiainfo.meo.web.component.ActivityComponent;
import com.asiainfo.meo.web.core.Message;

/**
 * @Description: 用户活动的controller
 * @Description: customer activity controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author liuyang
 * @Date 2015年6月4日 上午11:57:35
 * @version 1.0
 */
@RestController
@RequestMapping("/activity")
public class ActivityController
{
    @Resource
    ActivityComponent activityComponent;
    
    /**
     * @Description: 用户参加活动
     * @Description: customer join campaign activity
     * @modifyReason:
     * @author liuyang
     * @param campaignId
     * @return
     */
    @RequestMapping(params = "method=meo.activity.campaign.join", method = RequestMethod.GET)
    public Message<UICoinsEligibleVO> joinCampaignActivity(@RequestParam Long campaignId, @RequestParam String actionId)
    {
        final UICoinsEligibleVO coinsEligibleVO = new UICoinsEligibleVO();
        coinsEligibleVO.setCoinsEligible(activityComponent.joinCampaignActivity(campaignId, actionId));
        coinsEligibleVO.setCoinsEligibleDescription(MessageUtil.getMessage(ActivityErrorCodeDefine.COINS_ELIGIBLE_DESCRIPTION, null, null));
                
        final Message<UICoinsEligibleVO> msg = new Message<UICoinsEligibleVO>();
        msg.setBody(coinsEligibleVO);
        
        return msg;
    }
    
    @RequestMapping(params = "method=meo.activity.campaign.execute", method = RequestMethod.POST)
    public void executeCampaignActivity(@RequestBody @Valid UIJoinCampaignVO joinCampaign)
    {
        activityComponent.executeCampaignActivity(joinCampaign);
        return;
    }
    
    @RequestMapping(params = "method=meo.activity.resource.active", method = RequestMethod.GET)
    public void activeCustomerResource(@RequestParam Long resourceId)
    {
        activityComponent.activeCustomerResource(resourceId);
        return;
    }
    
    @RequestMapping(params = "method=meo.activity.product.redeem", method = RequestMethod.GET)
    public void redeemProduct(@RequestParam Long campaignId)
    {
        activityComponent.redeemProduct(campaignId);
        return;
    }
}
