package com.asiainfo.meo.campaign.profile.app.repository;

import java.sql.Timestamp;
import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignActivitySummary;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
/**
 * 
  * @Description: campaign 对应接口 
  * @Description: campaign interface                  
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author zhengzy                                                                                                                                                                                                                                                                           
  * @Date 2015年4月21日 下午5:33:39 
  * @version 1.0
 */
public interface CampaignRepository
{
    /**
     * 
      * @Description: 根据条件查询campaign 集合
      * @Description: query campaign list by conditions
      * @modifyReason: 
      * @author zhengzy
      * @param categoryId
      * @param campaignIds
      * @param pageSize
      * @param pageNo
      * @return
     */
    public List<Campaign> getCampaignList(Timestamp currentTime,List<Integer> campaignTypes,Integer categoryId,Long[] campaignIds, Integer pageSize, Integer pageNo);

    
    public int getCampaignListTotalSize(Timestamp currentTime,List<Integer> campaignTypes,Integer categoryId,Long[] campaignIds);
    
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
    public List<Campaign> getCampaignBasicInfo(CampaignBasicVO capaignCondition, Integer pageSize, Integer pageNo);
    
    public int getCampaignBasicInfoTotalSize(CampaignBasicVO capaignCondition);

    
    /**
      * @Description: 通过campaignId获得campaign信息
      * @Description: get campaign by campaign id
      * @author zhaozx
      * @param campaignId
      * @return
      */
    public Campaign getCampaignByCampaignId(long campaignId);
    
    /**
     * 
      * @Description: 根据campaignNo查询active的campaign
      * @Description: query active campaign by campaignNo 
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @return
     */
    public Campaign getActiveCampaignByCampaignNo(String campaignNo);
    
    /**
     * 
      * @Description: 新增campaign
      * @Description: save campaign
      * @modifyReason: 
      * @author zhengzy
      * @param campaign
     */
    Campaign addCampaign(Campaign campaign);
    
    /**
     * 
      * @Description: 修改campaign
      * @Description: modify campaign
      * @modifyReason: 
      * @author zhengzy
      * @param campaign
      * @return
     */
    Campaign updateCampaign(Campaign campaign);
    
    /**
     * 
      * @Description: 根据CampaignNo和CampaignSts来查询Campaign
      * @Description: query Campaign by campaignNo and CampaignSts
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @param campaignSts
      * @return
     */
    public Campaign getCampaignByCampaignNoAndCampaignSts(String campaignNo,int campaignSts);
    
    /**
     * 
      * @Description: 查询出有效的Campaign
      * @Description: query valid Campaign
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @return
     */
    public List<Campaign> getCampaignListByCampaignNo(String campaignNo);
    
    public String getNextVersion(String campaignNo);
    
    /**
     * 查询当前可用的版本
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @return
     */
    public List<Campaign> getActiveVersionCampaignByCampaignNo(String campaignNo);
    
    public CampaignActivitySummary getCampaignSummaryByCampaignNoAndParticipantType(String campaignNo);
    
    public Campaign getPreviousVersionCampaign(String campaignNo, String version);
    
    public void updateCampaignEndDate(String campaignNo,Timestamp campaignEndDate);

    /**
     * 
      * @Description: 根据campaignType和category分类查询campaign
      * @Description: query campaign by campaignType and category
      * @author zhengzy
      * @param campaignTypes
      * @param categoryIds
      * @return
     */
    public List<Campaign> getCampaignListByCampaignTypesAndCategoryIds(List<Integer> campaignTypes,List<Integer> categoryIds);
    
    public List<Campaign> getCampaignList(Long currentTime);
    
    public List<Object> getAppDownLoad(Long custId, Integer pageNo, Integer pageSize);
    
    public Integer getAppDownLoadTotalSize(Long custId);
}
