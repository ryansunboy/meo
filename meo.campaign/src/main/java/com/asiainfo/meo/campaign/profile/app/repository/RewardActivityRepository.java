package com.asiainfo.meo.campaign.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.RewardActivity;

public interface RewardActivityRepository
{
    /**
     * 
      * @Description: 根据操作客户Id查询其对应的操作记录
      * @Description: query customer operation record by custId
      * @modifyReason: 
      * @author zhengzy
      * @param criteria
      * @param firstResults
      * @param maxResults
      * @return
     */
    List<RewardActivity> getRewardActivityList(Long custId, Integer pageSize, Integer pageNo);
    
    public Integer getRewardActivityListTotalSize(Long custId);
}
