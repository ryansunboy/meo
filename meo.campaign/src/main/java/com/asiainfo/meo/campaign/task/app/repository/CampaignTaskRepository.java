package com.asiainfo.meo.campaign.task.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;

public interface CampaignTaskRepository
{
    /**
     * @Description: 通过task id查询campaign task
     * @Description: get campaign task by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    public CampaignTask getCampaignTaskByTaskId(long taskId);
    
    /**
     * @Description: 通过campaign编号去查询campaign task
     * @Description: get campaign task by campaign number
     * @author zhaozx
     * @param campaignNo
     * @return
     */
    public List<CampaignTask> getCampaignTaskByCampaignNo(String campaignNo);
    
    /**
     * @Description: 保存CampaignTask
     * @Description: save CampaignTask
     * @modifyReason:
     * @author zhengzy
     * @param campaignTask
     * @return
     */
    public CampaignTask saveCampaignTask(CampaignTask campaignTask);
    
    /**
     * @Description: 修改CampaignTask
     * @Description: modify CampaignTask
     * @modifyReason:
     * @author zhengzy
     * @param campaignTask
     * @return
     */
    public CampaignTask updateCampaignTask(CampaignTask campaignTask);
    
    /**
     * @Description: 根据campaignId查询 campaignTask
     * @Description: query campaigntask by campaignId
     * @modifyReason:
     * @author zhengzy
     * @param campaignId
     * @return
     */
    public List<CampaignTask> getCampaignTaskList(Long campaignId);
    
    /**
     * @Description: 根據taskId刪除CampaignTask
     * @Description: delete CampaignTask by taskId
     * @modifyReason:
     * @author zhengzy
     * @param taskId
     */
    public void deleteCampaignTaskByTaskId(Long taskId);
    
    public List<CampaignTask> getCampaignTaskByActionIdAndCampaignId(Long actionId,Long campaignId);
}
