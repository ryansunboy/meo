package com.asiainfo.meo.prm.profile.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;

public interface PartnerInfoRepository
{
    /**
     * @Description: 保存partner信息
     * @Description: save partner information
     * @author zhaozx
     * @param partnerInfo
     */
    void savePartnerinfo(PartnerInfo partnerInfo);
    
    /**
     * @Description: 获得partner信息
     * @Description: get partner information
     * @author zhaozx
     * @param partnerId
     * @return PartnerInfo partnerInfo entity
     */
    PartnerInfo getPartnerInfo(long partnerId);
    
    List<PartnerInfo> getAllPartnerInfo(Long partnerId);
    
    /**
     * @Description: 修改partner信息
     * @Description: update partner information
     * @author zhaozx
     * @param partnerInfo partner信息实体 partnerInfo entity
     */
    void updatePartnerInfo(PartnerInfo partnerInfo);
    
    /**
     * @Description: 注销partner信息
     * @Description: delete partner information
     * @author zhaozx
     * @param partnerInfo partner信息实体 partner information entity
     */
    void deletePartnerInfo(PartnerInfo partnerInfo);
    
    /**
      * @Description: 获得partner
      * @Description: get partner info list
      * @author zhaozx
      * @param detachedCriteria
      * @return List<PartnerInfo>
      */
    List<PartnerInfo> getPartnerInfo(DetachedCriteria selectCondition);
    
    List<PartnerInfo> getpartnerInfo(List<Long> partnerIds,String companyName);
    
    /**
      * @Description: 获得partner信息
      * @Description: get partner info 
      * @author zhaozx
      * @param selectCondition
      * @param pageSize
      * @param pageNo
      * @return
      */
    List<PartnerInfo> getPartnerInfo(DetachedCriteria selectCondition,int pageSize,int pageNo);
    
    List<PartnerInfo> getPartnerInfo(Long partnerId, String companyName, Integer pageSize, Integer pageNo);
    
    int getPartnerInfoTotalSize(Long partnerId, String companyName);
}
