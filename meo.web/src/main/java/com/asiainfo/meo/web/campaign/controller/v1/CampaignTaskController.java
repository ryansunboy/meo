package com.asiainfo.meo.web.campaign.controller.v1;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignTaskVO;
import com.asiainfo.meo.web.component.CampaignComponent;

/**
 * @Description: campaign 对应 controller
 * @Description: campaign controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhengzy
 * @Date 2015年4月20日 下午3:24:20
 * @version 1.0
 */
@RestController
@RequestMapping("/campaign/task")
public class CampaignTaskController
{
    private static final Log   LOG = LogFactory.getLog(CampaignTaskController.class);
    
    @Resource
    private CampaignPserviceBO campaignPserviceBO;
    
    @Resource
    private CampaignComponent  campaignComponent;
    
    /**
     * @Description: 获得campaign的activity
     * @Description: get campaign activity
     * @author zhaozx
     * @param campaignId
     * @return
     */
    @RequestMapping(params = "method=meo.campaign.task.partner.get", method = {RequestMethod.GET, RequestMethod.POST })
    public List<UICampaignTaskVO> getCampaignTask(@RequestParam(required = true) Long campaignId)
    {
        LOG.debug("get campaign activity by campaign id begin!");
        List<CampaignTaskVO> campaignTaskList = campaignPserviceBO.getCampaignActivity(campaignId);
        String campaignNo = "";
        List<UICampaignTaskVO> uIcampaignTaskList = campaignComponent.transformCampaignTaskVOToUICampaignTaskVO(campaignNo,campaignTaskList);
        LOG.debug("get campaign activity by campaign id end!");
        return uIcampaignTaskList;
    }
    
}
