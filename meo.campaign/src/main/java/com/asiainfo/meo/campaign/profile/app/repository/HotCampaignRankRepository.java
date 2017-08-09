package com.asiainfo.meo.campaign.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;

public interface HotCampaignRankRepository
{
    /**
     * 
      * @Description: 根据campaignId查询 HotCampaignRank
      * @Description: query HotCampaignRank by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    HotCampaignRank get(Long campaignId);
    
    /**
     * 
      * @Description: 保存hotCampaignRank
      * @Description: save the entity hotCampaignRank
      * @modifyReason: 
      * @author zhengzy
      * @param hotCampaignRank
     */
    void saveHotCampaignRank(HotCampaignRank hotCampaignRank);
    
    /**
     * 
      * @Description: 根据类别降序查询 HotCampaignRank
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param criteria
      * @param firstResults
      * @param maxResults
      * @return
     */
    List<HotCampaignRank> getHotCampaignRankDescOrder(Integer categoryId, Integer pageSize, Integer pageNo);
    
    /**
     * @Description 根据categoryId查询 受欢迎的campaign，并且作倒序排列
     * @Description query hot campaign list by categoryId ,and desc order
     * @modifyReason:
     * @author zhengzy
     * @param categoryId
     * @return
     */
    List<HotCampaignRank> getHotCampaignRankDescOrder(Integer categoryId);
    
    /**
     * @Description 修改hotCampaignRank
     * @Description update the hotCampaignRank
     * @modifyReason:
     * @author zhengzy
     * @param categoryId
     * @return
     */
    void updateHotCampaignRank(HotCampaignRank hotCampaignRank);
}
