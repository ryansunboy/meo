package com.asiainfo.meo.campaign.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPriceRel;

public interface CampaignPriceRelRepository
{
    /**
     * 
      * @Description: 保存CampaignPriceRel
      * @Description: save CampaignPriceRel
      * @modifyReason: 
      * @author zhengzy
      * @param campaignPriceRel
      * @return
     */
    public CampaignPriceRel saveCampaignPriceRel(CampaignPriceRel campaignPriceRel);
    
    /**
     * 
      * @Description: 
      * @Description: save CampaignPriceRel
      * @modifyReason: 
      * @author zhengzy
      * @param id
      * @return
     */
    public CampaignPriceRel getCampaignPriceRelById(Long id);
    
    /**
     * 
      * @Description: 根据campaignId查询CampaignPriceRel
      * @Description: query CampaignPriceRel by campaignId
      * @modifyReason: 
      * @author zhengzy
      * @param campaignId
      * @return
     */
    public List<CampaignPriceRel> getCampaignPriceRelsByCampaignId(Long campaignId);
    
    /**
     * 
      * @Description: 删除campaignPriceRel
      * @Description: remove campaignPriceRel
      * @modifyReason: 
      * @author zhengzy
      * @param id
      * @return
     */
    public void deleteCampaignPriceRel(Long id);
    
    /**
     * 
      * @Description: 修改campaignPriceRel
      * @Description: update campaignPriceRel
      * @modifyReason: 
      * @author zhengzy
      * @param campaignPriceRel
      * @return
     */
    public CampaignPriceRel updateCampaignPriceRel(CampaignPriceRel campaignPriceRel);
    
    
    public CampaignPriceRel getCampaignPriceRelByPriceIdAndCampaignId(Long priceId,Long campaignId);
}
