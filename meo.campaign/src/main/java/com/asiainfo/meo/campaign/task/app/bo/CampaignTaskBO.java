 package com.asiainfo.meo.campaign.task.app.bo;                                                                                                                                                                                                                                                                       

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRuleRel;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
                                                                                                                                                                                                                                                                                              
 public interface CampaignTaskBO
{
    /**
      * @Description: 通过campaign id获得campaign task
      * @Description: get campaign task by campaign id
      * @author zhaozx
      * @param campaignId
      * @return
      */
    public List<CampaignTaskVO> getCampaignTaskByCampaignId(Long campaignId);
    
    /**
     * 
      * @Description: 新增TaskParamInstance
      * @Description: save TaskParamInstance
      * @modifyReason: 
      * @author zhengzy
      * @param taskParamInstance
      * @return
     */
    public TaskParamInstance saveTaskParamInstance(TaskParamInstance taskParamInstance);
    
    /**
     * 
      * @Description: 新增 CampaignTask
      * @Description: save CampaignTask
      * @modifyReason: 
      * @author zhengzy
      * @param campaignTask
      * @return
     */
    public CampaignTask saveCampaignTask(CampaignTask campaignTask);
    
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
      * @Description: 修改CampaignTask
      * @Description: modify CampaignTask
      * @modifyReason: 
      * @author zhengzy
      * @param campaignTask
      * @return
     */
    public CampaignTask updateCampaignTask(CampaignTask campaignTask);
    
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
      * @Description: 根据rid逻辑删除CampaignTaskRel
      * @Description: remove CampaignTaskRel by rid
      * @modifyReason: 
      * @author zhengzy
      * @param rid
     */
    public CampaignTaskRel deleteCampaignTaskRelByRid(Long rid);
    
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
    
    /**
     * 
      * @Description: 修改TaskParamInstance
      * @Description: modify TaskParamInstance
      * @modifyReason: 
      * @author zhengzy
      * @param taskParamInstance
      * @return
     */
    public TaskParamInstance updateTaskParamInstance(TaskParamInstance taskParamInstance);
    
    /**
     * 
      * @Description: 根據id查詢TaskParamInstance
      * @Description: query TaskParamInstance by id
      * @modifyReason: 
      * @author zhengzy
      * @param id
      * @return
     */
    public TaskParamInstance getTaskParamInstanceById(Long id);
    
    /**
     * 
      * @Description: 根據id刪除逻辑TaskParamInstance
      * @Description: remove TaskParamInstance by id
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public TaskParamInstance deleteTaskParamInstanceById(Long id);
    
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
      * @Description: 根据campaignId查询 campaignTask
      * @Description: query campaigntask by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    public List<CampaignTask> getCampaignTaskList(Long campaignId);
    
    /**
     * 
      * @Description: 根据taskIds查询TaskParamInstance
      * @Description: query TaskParamInstance by taskIds
      * @modifyReason: 
      * @author zhengzy
      * @param taskIds
      * @return
     */
    public List<TaskParamInstance> getTaskParamInstanceListByTaskIds(List<Long> oldTaskIds);
    
    /**
     * 
      * @Description: 根據taskId逻辑刪除CampaignTask
      * @Description: delete CampaignTask by taskId
      * @modifyReason: 
      * @author zhengzy
      * @param taskId
     */
    public CampaignTask deleteCampaignTaskByTaskId(Long taskId);
    
    public CampaignTaskRule saveCampaignTaskRule(CampaignTaskRule campaignTaskRule);
    
    public CampaignTaskRule updateCampaignTaskRule(CampaignTaskRule campaignTaskRule);
    
    public CampaignTaskRule getCampaignTaskRule(Long id);
   
    public CampaignTaskRule getCampaignTaskRuleByActionId(Long actionId);
    
    public CampaignTaskRule getCampaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType);
    
    public List<CampaignTaskRule> getCampaignTaskRuleListByActionIds(List<Long> actionIds);
    
    public List<CampaignTaskRule> getCampaignTaskRuleListByRuleIds(List<Long> ruleIds);
    
    public CampaignTaskRuleRel saveCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel);
    
    public CampaignTaskRuleRel updateCampaignTaskRuleRel(CampaignTaskRuleRel campaignTaskRuleRel);
    
    public CampaignTaskRuleRel getCampaignTaskRuleRelById(Long id);
    
    public List<CampaignTaskRuleRel> getCampaignTaskRuleRelListByTaskIds(List<Long> taskIds);
    
    public CampaignTaskRuleRel deleteCampaignTaskRuleRelById(Long id);
    
    public CampaignTask getCampaignTaskById(Long id);
    
    public List<CampaignTask> getCampaignTaskByActionIdAndCampaignId(Long actionId,Long campaignId);
    
    List<CampaignTask> getRewardTask(Long actionId,Long campaignId);
    
    /**
     * @Description: 通过task id查询task参数
     * @Description: get task parameter by task id
     * @author zhaozx
     * @param taskId
     * @return
     */
    public List<TaskParamInstance> getTaskParamInstanceByTaskId(Long taskId);
    
    /**
     * 
      * @Description: 根据taskId和paramId查询TaskParamInstance
      * @Description: query TaskParamInstance by taskId and paramId
      * @modifyReason: 
      * @author zhengzy
      * @param taskId
      * @param paramId
      * @return
     */
    public TaskParamInstance getTaskParamInstanceByTaskIdAndParamId(Long taskId,Long paramId);
    
    /**
     * 
      * @Description: 
      * @Description: get product id (which is kept in task param instance) by campaign id
      * @modifyReason: 
      * @author nick
      * @param campaignId
      * @return
     */
    public Long getProductIdByCampaignId(Long campaignId);
    
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
    
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
    
    public void deleteCampaignTaskDetailByDetailId(Long detailId);
    
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId);

    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId, String campaignNo);
    
    public void  updateCampaignTaskDetailByDetailIdNotInDetailIds(Long custId,String campaignNo,List<Long> detailIds);
}
