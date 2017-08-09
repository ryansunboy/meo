package com.asiainfo.meo.campaign.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPropertyDef;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefQueryConditionVO;

public interface CampaignPropertyDefRepository
{
    /**
     * @Description: 获得campaign属性定义信息
     * @Description: get campaign property
     * @author zhaozx
     * @param propertyKey
     * @return
     */
    public CampaignPropertyDef getCampaignPropertyDef(long propertyKey);
    
    /**
     * 
      * @Description: 根據propertyCode和campaignType查詢CampaignPropertyDef
      * @Description: query campaignPropertyDef by campaignType and campaignType
      * @modifyReason: 
      * @author zhengzy
      * @param propertyCode
      * @param campaignType
      * @return
     */
    public CampaignPropertyDef getCampaignPropertyDefByCode(String propertyCode,int campaignType);
    
    /**
     * 
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param campaignType
      * @return
     */
    public List<CampaignPropertyDef> getMandatoryPropertyCampaignPropertyDefByCampaignType(int campaignType);
    
    
    public List<CampaignPropertyDef> getCampaignPropertyDefByCampaignType(
            CampaignPropertyDefQueryConditionVO conditionVO,
            Integer pageSize,
            Integer pageNo);
    
    public Integer getCampaignPropertyDefByCampaignTypeTotalSize(CampaignPropertyDefQueryConditionVO conditionVO);
    /**
     * 
      * @Description: 新增campaign  property def 对象
      * @Description: add campaign property def 
      * @modifyReason: 
      * @author zhengzy
      * @param campaignPropertyDef
     */
    public void addCampaignPropertyDef(CampaignPropertyDef campaignPropertyDef);
    
    public void modifyCampaignPropertyDef(CampaignPropertyDef campaignPropertyDef);
}
