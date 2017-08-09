package com.asiainfo.meo.campaign.profile.app.bo;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.Agreement;
import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignActivitySummary;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPriceRel;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignProperty;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignActivityHistoryVO;
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
import com.asiainfo.meo.common.core.utils.PageInfo;

public interface CampaignProfileBO
{
    /**
     * @Description: 通过campaign id获得campaign的属性
     * @Description: get campaign detail by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    List<CampaignDetailVO> getCampaignDetail(Long campaignId);
    
    /**
     * @Description: 根据条件查询campaign 集合
     * @Description: query campaign list by conditions
     * @modifyReason:
     * @author zhengzy
     * @param criteria
     * @param firstResults
     * @param maxResults
     * @return
     */
    public PageInfo<CampaignVO> getCampaignList(List<Integer> campaignTypes,Integer categoryId, Integer pageSize, Integer pageNo);
    
    /**
     * @Description: 根据类别降序查询 HotCampaignRank
     * @Description: query hotCampaignRan by participateCount desc
     * @modifyReason:
     * @author zhengzy
     * @param categoryId
     * @param pageSize
     * @param pageNo
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
     * @Description: 查询受欢迎的campaign
     * @Description: query hot campaign
     * @modifyReason:
     * @author zhengzy
     * @param hotCampaignRankList
     * @param categoryId
     * @param pageSize
     * @param pageNo
     * @return
     */
    PageInfo<CampaignVO> getHotCampaignList(List<Integer> campaignTypes,List<HotCampaignRank> hotCampaignRankList, Integer categoryId, Integer pageSize,
            Integer pageNo);
    
    /**
     * @Description: 根据操作客户Id查询其对应的操作记录
     * @Description: query customer operation record by custId
     * @modifyReason:
     * @author zhengzy
     * @param custId
     * @param pageSize
     * @param pageNo
     * @return
     */
    PageInfo<CampaignActivityHistoryVO> getCampaignActivityHistoryList(Long custId, Integer pageSize, Integer pageNo);
    
    /**
     * @Description: 根据campaignNo查询active的campaign
     * @Description: query active campaign by campaignNo
     * @modifyReason:
     * @author zhengzy
     * @param campaignNo
     * @return
     */
    public Campaign getActiveCampaignByCampaignNo(String campaignNo);
    
    /**
     * @Description: 获得Agreement定义信息
     * @Description: get all agreement info if agreementId is not null
     * @author zhoujj
     * @param agreementId
     * @return
     */
    public List<Agreement> getAgreementInfo(Long agreementId);
    
    /**
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
    
    /**
     * @Description: 获取Campaign基本信息totalSize
     * @Description: get Campaign Basic Info  totalSize
     * @modifyReason:
     * @author zhengzy
     * @param capaignCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    public int getCampaignBasicInfoTotalSize(CampaignBasicVO capaignCondition);
    
    /**
     * @Description: 通过campaign id获得campaign
     * @Description: get campaign by campaign id
     * @author zhoujj
     * @return
     */
    public Campaign getCampaignByCampaignId(long campaignId);
    
    /**
     * @Description: 新增campaign
     * @Description: save campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaignCommonVO
     */
    public Campaign addCampaign(CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: update campaign
     * @Description: update campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaign
     * @param campaignCommonVO
     */
    public Campaign updateCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: 新增一条状态为publish 的 campaign
     * @Description: add publish campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaignCommonVO
     * @return
     */
    public Campaign addPublishCampaign(CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: 將renew状态的数据更改为active
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaign
     * @param campaignCommonVO
     * @return
     */
    public Campaign updatePublishCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: update publish campaign
     * @Description: update publish campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaign
     * @param campaignCommonVO
     */
    public Campaign updateRenewCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: update active campaign
     * @Description: update active campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaign
     * @param campaignCommonVO
     */
    public void updateActiveCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: 新增一条状态为renew 的 campaign
     * @Description: add renew campaign
     * @modifyReason:
     * @author zhengzy
     * @param oldCampaign
     * @param campaignCommonVO
     * @return
     */
    public Campaign addRenewCampaign(Campaign oldCampaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: (用中文描述一下这个方法)
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaign
     * @param campaignCommonVO
     * @return
     */
    public Campaign updatePublishRenewCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: (用中文描述一下这个方法)
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaign
     * @param campaignCommonVO
     * @return
     */
    public Campaign addPublishRenewCampaign(Campaign campaign, CampaignCommonVO campaignCommonVO);
    
    /**
     * @Description: 根据CampaignNo和CampaignSts来查询Campaign
     * @Description: query Campaign by campaignNo and CampaignSts
     * @modifyReason:
     * @author zhengzy
     * @param campaignNo
     * @param campaignSts
     * @return
     */
    public Campaign getCampaignByCampaignNoAndCampaignSts(String campaignNo, int campaignSts);
    
    /**
     * @Description: 查询上一个版本的campaign
     * @Description: 查询上一个版本的campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaignNo
     * @param version
     * @return
     */
    public Campaign getPreviousVersionCampaign(String campaignNo, String version);
    
    /**
     * @Description: 保存CampaignPriceRel
     * @Description: save CampaignPriceRel
     * @modifyReason:
     * @author zhengzy
     * @param campaignPriceRel
     * @return
     */
    public CampaignPriceRel saveCampaignPriceRel(CampaignPriceRel campaignPriceRel);
    
    /**
     * @Description: 保存CampaignProperty
     * @Description: save CampaignProperty
     * @modifyReason:
     * @author zhengzy
     * @param campaignDetailVO
     * @param campaignType
     * @param campaignId
     * @return
     */
    public CampaignProperty saveCampaignProperty(CampaignDetailVO campaignDetailVO, int campaignType, Long campaignId);
    
    /**
     * @Description: 根据campaignId查询CampaignPriceRel
     * @Description: query CampaignPriceRel by campaignId
     * @modifyReason:
     * @author zhengzy
     * @param id
     * @return
     */
    public List<CampaignPriceRel> getCampaignPriceRelsByCampaignId(Long id);
    
    /**
     * @Description: 删除campaignPriceRel
     * @Description: remove campaignPriceRel
     * @modifyReason:
     * @author zhengzy
     * @param id
     * @return
     */
    public void deleteCampaignPriceRel(Long id);
    
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
     * @Description: 修改CampaignProperty
     * @Description: modify CampaignProperty
     * @modifyReason:
     * @author zhengzy
     * @param campaignDetailVO
     * @param campaignType
     * @param campaignId
     * @return
     */
    public CampaignProperty updateCampaignProperty(CampaignDetailVO campaignDetailVO, int campaignType, Long campaignId);
    
    /**
     * @Description: 根据Id逻辑删除CampaignProperty
     * @Description: remove CampaignProperty by id
     * @modifyReason:
     * @author zhengzy
     * @param id
     */
    public CampaignProperty deleteCampaignProperty(Long id);
    
    /**
     * @Description: 根据campaignId 查询CampaignProperty
     * @Description: query CampaignProperty by campaignId
     * @modifyReason:
     * @author zhengzy
     * @param campaignId
     * @return
     */
    public List<CampaignProperty> getCampaignPropertyListByCampaignId(Long campaignId);
    
    /**
     * @Description: 逻辑删除campaignPriceRel
     * @Description: update campaignPriceRel sts_invalid
     * @modifyReason:
     * @author zhengzy
     * @param campaignPriceRel
     * @return
     */
    public CampaignPriceRel updateCampaignPriceStsRelInvalid(CampaignPriceRel campaignPriceRel);
    
    /**
     * @Description: 查询出有效的campaignPriceRel
     * @Description: query valid campaignRel
     * @modifyReason:
     * @author zhengzy
     * @param priceId
     * @param campaignId
     * @return
     */
    public CampaignPriceRel getCampaignPriceRelByPriceIdAndCampaignId(Long priceId, Long campaignId);
    
    /**
     * @Description: 查询出有效的Campaign
     * @Description: query valid Campaign
     * @modifyReason:
     * @author zhengzy
     * @param campaignNo
     * @return
     */
    public List<Campaign> getCampaignListByCampaignNo(String campaignNo);
    
    /**
     * @Description: (用中文描述一下这个方法)
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaignProperty
     * @return
     */
    public CampaignProperty saveCampaignProperty(CampaignProperty campaignProperty);
    
    /**
     * @Description: (用中文描述一下这个方法)
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaignType
     * @return
     */
    public List<String> getMandatoryPropertyCampaignPropertyDefByCampaignType(int campaignType);
    
    /**
     * @Description: 修改CampaignPriceRel ，主要是修改有效期
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaignPriceRel
     * @return
     */
    public CampaignPriceRel updateCampaignPriceRel(CampaignPriceRel campaignPriceRel);
    
    /**
     * 查询当前可用的版本
     * 
     * @Description: (用中文描述一下这个方法)
     * @Description: (English description)
     * @modifyReason:
     * @author zhengzy
     * @param campaignNo
     * @return
     */
    public List<Campaign> getActiveVersionCampaignByCampaignNo(String campaignNo);
    
    public String getNextVersion(String campaignNo);
    
    public List<CampaignPriceVO> getCampaignPricesByCampaignId(Long campaignId);
    
    public CampaignActivitySummary getCampaignSummaryByCampaignNoAndParticipantType(String campaignNo);
    
    HotCampaignRankVO get(Long campaignId);
    
    void saveHotCampaignRank(HotCampaignRank hotCampaignRank);
    
    public void updateHotCampaignRank(HotCampaignRank hotCampaignRank);
    
    public PageInfo<CampaignPropertyDefVO> getCampaignPropertyDefByCampaignType(CampaignPropertyDefQueryConditionVO conditionVO);
    
    public void addCampaignPropertyDef(List<CampaignPropertyDefineVO> campaignPropertyDefineVOList);
    
    /**
     * @Description: 根据campaignType和category分类查询campaign
     * @Description: query campaign by campaignType and category
     * @modifyReason:
     * @author zhengzy
     * @param campaignTypes
     * @param categoryIds
     * @return
     */
    public List<CampaignVO> getCampaignListByCampaignTypesAndCategoryIds(List<Integer> campaignTypes, List<Integer> categoryIds);

    public void modifyCampaignPropertyDef(CampaignPropertyDefVO campaignPropertyDefVO);
    
    public Integer getCampaignTotalCoins(Long campaignId);
    
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
    
    /**
     * 根据campaignNo和campaign最大参加人员查询剩余可参加人数
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param campaignNo
      * @param participant
      * @return
     */
    public int countRemainParticipantByCampaignNo(String campaignNo,int participant);
}
