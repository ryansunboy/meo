package com.asiainfo.meo.campaign.service.provide;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignActivityHistoryVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignAllBasicVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignCommonVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignDetailVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPriceVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefQueryConditionVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefineVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.HotCampaignRankVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.NewFeedCampaignVO;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTask;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRel;
import com.asiainfo.meo.campaign.task.app.model.entity.CampaignTaskRule;
import com.asiainfo.meo.campaign.task.app.model.entity.TaskParamInstance;
import com.asiainfo.meo.campaign.task.app.model.vo.CampaignTaskVO;
import com.asiainfo.meo.common.core.utils.PageInfo;

public interface CampaignPserviceBO
{
    /**
     * 
      * @Description: 查询最新发布的campaign 集合
      * @Description: query the new campaign profile
      * @modifyReason: 
      * @author zhengzy
      * @param groupCode
      * @param categoryId
      * @param pageSize
      * @param pageNo
      * @return
     */
    PageInfo<CampaignVO> getNewCampaignList(String groupCode,Integer categoryId,Integer pageSize,Integer pageNo);   
    
    /**
     * 
      * @Description: 查询受欢迎的campaign
      * @Description: query hot campaign
      * @modifyReason: 
      * @author zhengzy
      * @param groupCode
      * @param categoryId
      * @param pageSize
      * @param pageNo
      * @return
     */
    PageInfo<CampaignVO> getHotCampaignList(String groupCode,Integer categoryId,Integer pageSize,Integer pageNo);
    
    
    /**
      * @Description: 通过campaign id获得campaign的属性
      * @Description: get campaign detail by campaign id
      * @author zhaozx
      * @param campaignId
      * @return
      */
    List<CampaignDetailVO> getCampaignDetail(Long campaignId);
    
    /**
      * @Description: 通过campaign id获得campaign 活动
      * @Description: get campaign activity by campaign id
      * @author zhaozx
      * @param CampaignId
      * @return
      */
    List<CampaignTaskVO> getCampaignActivity(Long campaignId);
    
    /**
     * 
      * @Description: 获取Campaign基本信息
      * @Description: get Campaign Basic Info
      * @modifyReason: 
      * @author zhoujj
      * @param capaignCondition
      * @param pageSize
      * @param pageNo
      * @return
     */
    public PageInfo<CampaignBasicVO> getCampaignBasicInfo(CampaignBasicVO capaignCondition, Integer pageSize, Integer pageNo);
    
    /**
     * 
      * @Description: 根据CampaignId获取Campaign信息
      * @Description: get Campaign Info By CampaignId
      * @modifyReason: 
      * @author zhoujj
      * @param campaignId
      * @return
     */
    public CampaignBasicVO getCampaignInfoById(Long campaignId);
    
    /**
     * 
      * @Description: 根据campaignId查询Campaign
      * @Description: query Campaign by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    public Campaign getCampaignById(Long campaignId);
    
    /**
     * 
      * @Description: 根据操作客户Id查询其对应的操作记录
      * @Description: query customer operation record by custId
      * @modifyReason: 
      * @author zhengzy
      * @param custId
      * @param pageSize
      * @param pageNo
      * @return
     */
    PageInfo<CampaignActivityHistoryVO> getCampaignActivityHistoryList(Long custId,Integer pageSize,Integer pageNo);
    
    /**
     * 
      * @Description: 发布campaign为publish状态
      * @Description: update the campaign publish
      * @modifyReason: 
      * @author zhengzy
      * @param campaignCommonVO
      * @param campaignDetailVOs
      * @param campaignTaskVOs
      * @return
     */
    Campaign  publishCampaign(CampaignCommonVO campaignCommonVO,List<CampaignDetailVO> campaignDetailVOs,List<CampaignTaskVO> campaignTaskVOs);
    
    /**
     * 
      * @Description: 新增campaign为draft状态
      * @Description: save the campaign draft
      * @modifyReason: 
      * @author zhengzy
      * @param uICampaignCommonVO
      * @param uICampaignDetailVOs
      * @param uICampaignTaskVOs
      * @return
     */
    Campaign saveDraftCampaign(CampaignCommonVO campaignCommonVO,List<CampaignDetailVO> campaignDetailVOs, List<CampaignTaskVO> campaignTaskVOs);
    
    /**
     * 
      * @Description: 修改campaign
      * @Description: modify renew campaign
      * @modifyReason: 
      * @author zhengzy
      * @param campaignCommonVO
      * @return
     */
    public Campaign modifyRenewCampaign(CampaignCommonVO campaignCommonVO);
    
    /**
     * 
      * @Description: 重新发布一条campaign
      * @Description: publish renew campaign
      * @modifyReason: 
      * @author zhengzy
      * @param campaignCommonVO
      * @return
     */
    public Campaign publishRenewcampaign(CampaignCommonVO campaignCommonVO);
    
    /**
     * 
      * @Description: 获取赠送的任务
      * @Description: get the reward campaign task
      * @modifyReason: 
      * @author liuyang
      * @param actionId
      * @param campaignId
      * @return
     */
    List<CampaignTask> getRewardCampaignTask(Long actionId,Long campaignId);
    
    /**
     * 
      * @Description: 根据campaignId查询campaignPrice list
      * @Description: query the campaignPrice list by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    public List<CampaignPriceVO> getCampaignPricesByCampaignId(Long campaignId);

    /**
     * 
      * @Description: 根据campaignNo查询campaign nextVersion
      * @Description: query the campaign nextVersion by campaignNo
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @return
     */
    public String getNextVersion(String campaignNo);
    
    /**
     * 
      * @Description: 根据 campaignId查询campaign详细信息
      * @Description: query campaign detail info by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    CampaignAllBasicVO getCampaignDetailInfoById(Long campaignId);
    
    /**
     * 
      * @Description: 根据actionId查询CampaignTaskRule
      * @Description: query CampaignTaskRule by actionId
      * @modifyReason: 
      * @author zhengzy
      * @param actionId
      * @return
     */
    public CampaignTaskRule getCampaignTaskRuleByActionId(Long actionId);
    
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
     * @Description: 通过task id查询task参数实例
     * @Description: get task parameter instance by task id
     * @author zhengzy
     * @param taskId
     * @return
     */
   public List<TaskParamInstance> getTaskParamInstanceByTaskId(Long taskId);
   
   /**
    * 
     * @Description: 根据campaignId查询HotCampaignRank
     * @Description: query HotCampaignRank by campaignId
     * @modifyReason: 
     * @author zhengzy
     * @param campaignId
     * @return
    */
   HotCampaignRankVO get(Long campaignId);
   
   /**
    * 
     * @Description: 新增hotCampaignRank
     * @Description: add hotCampaignRank entity
     * @modifyReason: 
     * @author zhengzy
     * @param hotCampaignRank
    */
   public void saveHotCampaignRank(HotCampaignRank hotCampaignRank);
   
   /**
    * 
     * @Description: 修改hotCampaignRank
     * @Description: modify the hotCampaignRank entity
     * @modifyReason: 
     * @author zhengzy
     * @param hotCampaignRank
    */
   void updateHotCampaignRank(HotCampaignRank hotCampaignRank);
   
   /**
    * 
     * @Description: 根据campaignType查询CampaignPropertyDef 集合
     * @Description: query CampaignPropertyDef list by campaignType
     * @modifyReason: 
     * @author zhengzy
     * @param campaignType
     * @return
    */
   public PageInfo<CampaignPropertyDefVO> getCampaignPropertyDefByCampaignType(CampaignPropertyDefQueryConditionVO conditionVO);
   
   /**
    * 
     * @Description: 新增campaign  property def 对象
     * @Description: add campaign property def 
     * @modifyReason: 
     * @author zhengzy
     * @param campaignPropertyDefineVO
    */
   public void addCampaignPropertyDef(List<CampaignPropertyDefineVO> campaignPropertyDefineVOList);
   
   /**
    * 
     * @Description: 根据groupCode和categoryIds查询campaignVo集合 
     * @Description: query CampaignVO list by groupCode and categoryIds
     * @modifyReason: 
     * @author zhengzy
     * @param groupCode
     * @param categoryIds
     * @return
    */
   public List<CampaignVO> getCampaignVOList(String groupCode,List<Integer> categoryIds);
   
   /**
    * 
     * @Description: 
     * @Description: get product id by using campaign id
     * @modifyReason: 
     * @author nick
     * @param campaignId
     * @return Long (product id)
    */
   public Long getProductIdByCampaignId(Long campaignId);
   
   /**
    * 根据campaignId获取，参加并且完成所有任务 可以获得coin数量
    * @param campaignId
    * @return
    */
   public Integer getCampaignTotalCoins(Long campaignId);
   
   /**
    * 
     * @Description: 新增campaignTaskDetail
     * @Description: add campaignTaskDetail
     * @modifyReason: 
     * @author zhengzy
     * @param campaignTaskDetail
     * @return
    */
   public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
   
   /**
    * 
     * @Description: 修改campaignTaskDetail
     * @Description: update campaignTaskDetail
     * @modifyReason: 
     * @author zhengzy
     * @param campaignTaskDetail
     * @return
    */
   public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail);
   
   /**
    * 
     * @Description: 根据detailId删除campaignTaskDetail
     * @Description: delete campaignTaskDetail by detailId
     * @modifyReason: 
     * @author zhengzy
     * @param detailId
    */
   public void deleteCampaignTaskDetailByDetailId(Long detailId);
   
   /**
    * 
     * @Description: 根据detailId查询campaignTaskDetail
     * @Description: query campaignTaskDetail by detailId
     * @modifyReason: 
     * @author zhengzy
     * @param detailId
     * @return
    */
   public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId);
   
   /**
    * 
     * @Description: 根据campaignNo和custId查询CampaignTaskDetail 集合
     * @Description: query CampaignTaskDetail list by campaignNo and custId
     * @modifyReason: 
     * @author zhengzy
     * @param custId
     * @param campaignNo
     * @return
    */
   public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId,String campaignNo);
   
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
     * @Description: 根据actionId和campaignId查询CampaignTask
     * @Description: query CampaignTask by actionId and campaignId
     * @modifyReason: 
     * @author zhengzy
     * @param actionId
     * @param campaignId
     * @return
    */
   public CampaignTask getCampaignTaskByActionIdAndCampaignId(Long actionId,Long campaignId);
   
   /**
    * 
     * @Description: 根据custId、campaignNo来修改detailId没有在detailIds中的campaignTaskDetail
     * @Description: query the campaignTaskDetail list by custId and campaignNo first,then update the campaignTaskDetail where detailId not in the detailIds collection 
     * @modifyReason: 
     * @author zhengzy
     * @param custId
     * @param campaignNo
     * @param detailIds
    */
   public void  updateCampaignTaskDetailByDetailIdNotInDetailIds(Long custId,String campaignNo,List<Long> detailIds);
   
   /**
    * 
     * @Description: Get campaign Task rule by using action id and rue type
     * @modifyReason: 
     * @author Thanapol
     * @param actionId
     * @param ruleType
     * @return
    */
   public CampaignTaskRule getCampaignTaskRuleByActionIdAndRuleType(Long actionId, Integer ruleType);
   
   public void modifyCampaignPropertyDef(CampaignPropertyDefVO campaignPropertyDefVO);
   
   public List<Campaign> getCampaignList(Long currentTime);
   
   public Campaign updateCampaign(Campaign campaign);
   
   public PageInfo<String> getAppDownLoad(Long custId, Integer pageNo, Integer pageSize);
   
   /**
    * 
     * @Description: 展示最新发布的campaign,根据时间倒序排列
     * @Description: list the newly campaign ,according to the date arranged in reverse chronological order
     * @modifyReason: 
     * @author zhengzy
     * @param pageNo
     * @param pageSize
     * @return
    */
   public PageInfo<NewFeedCampaignVO> getNewsFeedCampaignPageInfo(final Integer pageNo,final Integer pageSize);
}

