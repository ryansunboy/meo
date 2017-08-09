 package com.asiainfo.meo.campaign.task.app.repository;                                                                                                                                                                                                                                                                       

import java.util.List;

import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
                                                                                                                                                                                                                                                                                              
 public interface TaskParamInstanceRepository
{
    /**
      * @Description: 通过task id查询task参数实例
      * @Description: get task parameter instance by task id
      * @author zhaozx
      * @param taskId
      * @return
      */
    public List<TaskParamInstance> getTaskParamInstanceByTaskId(long taskId);
    
    /**
     * 
      * @Description: 新增 taskParamInstance
      * @Description: save TaskParamInstance
      * @modifyReason: 
      * @author zhengzy
      * @param taskParamInstance
      * @return
     */
    public TaskParamInstance saveTaskParamInstance(TaskParamInstance taskParamInstance);
    
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
      * @Description: 根據id刪除TaskParamInstance
      * @Description: remove TaskParamInstance by id
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public void deleteTaskParamInstanceById(Long id);
    
    /**
     * 
      * @Description: 根据taskIds查询TaskParamInstance
      * @Description: query TaskParamInstance by taskIds
      * @modifyReason: 
      * @author zhengzy
      * @param taskIds
      * @return
     */
    public List<TaskParamInstance> getTaskParamInstanceListByTaskIds(List<Long> taskIds);
    
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
      * @return Long (productId)
     */
    public Long getProductIdByCampaignId(Long campaignId);
}
