package com.asiainfo.meo.campaign.task.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;

/**
 * @Description: (这里用一句话描述这个类的作用)
 * @Description: (English description)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015年4月24日 下午7:27:12
 * @version 1.0
 */

public interface CampaignTaskRelRepository
{
    
    /**
      * @Description:  获得父活动的task id
      * @Description: get parent campaign task
      * @author zhaozx
      * @param srcTaskId
      * @return
      */
    public Long getParentCampaignTaskId(long srcTaskId);
    
    /**
      * @Description: 通过campaign id获得触发的活动关系列表
      * @Description: get campaign task relation list by campaign id
      * @author zhaozx
      * @param campaignId
      * @return
      */
    public List<CampaignTaskRel> getTriggerCampaignTaskRelList(long campaignId);
    
    /**
     * 
      * @Description: 保存campaignTaskRel
      * @Description: save campaignTaskRel
      * @modifyReason: 
      * @author zhengzy
      * @param campaignTaskRel
      * @return
     */
    public CampaignTaskRel saveCampaignTaskRel(CampaignTaskRel campaignTaskRel);
    
    /**
     * 
      * @Description: 根据rid 查询CampaignTaskRel
      * @Description: query CampaignTaskRel by rid
      * @modifyReason: 
      * @author zhengzy
      * @param rid
      * @return
     */
    public CampaignTaskRel getCampaignTaskRelByRid(Long rid);
    
    /**
     * 
      * @Description: 根据rid 删除CampaignTaskRel
      * @Description: remove CampaignTaskRel by rid
      * @modifyReason: 
      * @author zhengzy
      * @param rid
     */
    public void deleteCampaignTaskRelByRid(Long rid);
    
    /**
     * 
      * @Description: 根據campaignId查詢campaignTaskRel
      * @Description: query campaignTaskRel by campaignid
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    public List<CampaignTaskRel> getCampaignTaskRelList(Long campaignId);
    
    /**
     * 
      * @Description: 修噶campaignTaskRel
      * @Description: modify campaignTaskRel
      * @modifyReason: 
      * @author zhengzy
      * @param campaignTaskRel
      * @return
     */
    public CampaignTaskRel updateCampaignTaskRel(CampaignTaskRel campaignTaskRel);
    
    /**
     * 
      * @Description: 根据task id 和campaign id 查询reward 的task 信息
      * @Description: get the reward task by task id and campaign id.
      * @modifyReason: 
      * @author liuyang
      * @param taskId
      * @param campaignId
     */

    public List<CampaignTaskRel> getCampaignTaskRelByTaskIdAndCampaignId(long taskId, Long campaignId);

    /**
     * 
      * @Description: 根据taskId查询campaign task 信息
      * @Description: get campaign task by task id
      * @modifyReason: 
      * @author liuyang
      * @param taskId
      * @return
     */
    public CampaignTask getCampaignTaskByTaskId(long taskId);
    
    
    /**
     * 
     * @Description: 根据relTypeId和relTypeId查询CampaignTaskRel
     * @Description: query CampaignTaskRel by relTypeId and relTypeId
     * @modifyReason: 
     * @author zhengzy
     * @param srcTaskid
     * @param campaignId
    */
    public List<CampaignTaskRel> getCampaignTaskRelBySrcTaskIdAndCampaignId(Long srcTaskid, Long campaignId,int relType);
}
