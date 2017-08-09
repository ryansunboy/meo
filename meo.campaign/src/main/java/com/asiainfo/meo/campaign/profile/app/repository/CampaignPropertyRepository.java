package com.asiainfo.meo.campaign.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignProperty;

public interface CampaignPropertyRepository
{
    /**
     * @Description: 通过campaignId查找campaign的属性
     * @Description: get campaign property by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public List<CampaignProperty> getCampaignPropertyByCampaignId(long campaignId);
    
    /**
     * @Description: 保存 CampaignProperty
     * @Description: save CampaignProperty
     * @modifyReason:
     * @author zhengzy
     * @param campaignProperty
     * @return
     */
    public CampaignProperty saveCampaignProperty(CampaignProperty campaignProperty);
    
    /**
     * @Description:根据ID查询CampaignProperty
     * @Description: query CampaignProperty by id
     * @modifyReason:
     * @author zhengzy
     * @param id
     * @return
     */
    public CampaignProperty getCampaignPropertyById(Long id);
    
    /**
     * 
      * @Description: 保存 CampaignProperty
      * @Description: modify CampaignProperty
      * @modifyReason: 
      * @author zhengzy
      * @param campaignProperty
      * @return
     */
    public CampaignProperty updateCampaignProperty(CampaignProperty campaignProperty);
    
    /**
     * 
      * @Description: 根据Id删除CampaignProperty
      * @Description: remove CampaignProperty by id
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public void deleteCampaignProperty(Long id);
    
    /**
     * 
      * @Description: 根据campaignId 查询CampaignProperty
      * @Description: query CampaignProperty by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    public List<CampaignProperty> getCampaignPropertyListByCampaignId(Long campaignId);
}
