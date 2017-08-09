package com.asiainfo.meo.prm.service.provide;


import java.text.ParseException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractVO;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.profile.app.model.vo.StaffInfoVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmLoginVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmPartnerLoginVO;
import com.asiainfo.meo.prm.service.entity.vo.PrmPasswordVO;

/**
 * @Description: (杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜绫荤殑浣滅敤)
 * @Description: (English description)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015骞�鏈�1鏃�涓嬪崍6:14:14
 * @version 1.0
 */
public interface PartnerPserviceBO
{
    
    /**
     * @Description: (partner 鐧诲綍)
     * @Description: (partner login )
     * @modifyReason:
     * @author liuyang
     * @param prmLogin
     * @return
     */
    PrmPartnerLoginVO loginForPartner(PrmLoginVO prmLogin);
    
    /** 
     * @Description: (鍒涘缓鍚堝悓鍙婁环鏍�
     * @Description: (create contract and price)
     * @modifyReason:
     * @author lill
     * @param contractPriceVO
     */
    Contract savePartnerContract(ContractPriceVO contractPriceVO);
    
    void resetPortalPassword(PrmPasswordVO password, Integer userType);
    
    /**
     * @Description: partner娉ㄥ唽,淇濆瓨partner profile
     * @Description: sign up partner,save partner profile
     * @author zhaozx
     * @param partnerProfile
     * @param saleId
     * @return
     */
    long savePartnerProfile(PartnerProfileVO partnerProfile, Long saleId);
    
    /**
     * @Description: (鏍规嵁partnerId鏌ヨpartner)
     * @Description: (get partner by partnerId)
     * @modifyReason:
     * @author zhengzy
     * @param partnerId
     * @return
     */
    Partner getPartner(Long partnerId);
    
    /**
     * @Description: (婵�椿partner)
     * @Description: (active partner)
     * @modifyReason:
     * @author zhengzy
     * @param partnerId
     */
    void updatePartnerActive(long partnerId);
    
    /**
     * 
      * @Description: (婵�椿contract)
      * @Description: (active contract)
      * @modifyReason: 
      * @author zhengzy
      * @param contractId
     */
    void updateContractActive(long contractId);
    
    /**
     * @Description: 淇敼partner淇℃伅
     * @Description: update partner 淇℃伅
     * @author zhaozx
     * @param partnerInfoVO
     */
    void updatePartnerProfile(PartnerInfoVO partnerInfoVO);
    
    /**
     * @Description: 淇敼partner鑱旂郴浜轰俊鎭�
     * @Description: update partner contact information
     * @author zhaozx
     * @param partnerInfoVO
     */
    void updatePartnerContact(PartnerInfoVO partnerInfoVO);
    
    /**
     * @Description: 鑾峰緱partner profile
     * @Description: get partner profile
     * @author zhaozx
     * @param partnerId
     * @return
     */
    PartnerProfileVO getPartnerProfile(Long partnerId);
    
    /**
     * @Description: 鏌ヨpartner淇℃伅
     * @Description: select partner info
     * @author zhaozx
     * @param partnerCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<PartnerProfileVO> getPartnerInfo(List<PartnerInfoVO> partnerCondition, Integer pageSize, Integer pageNo);
    
    /**
     * @Description: 鏌ヨpartner list淇℃伅
     * @Description: select partner list
     * @author zhaozx
      * @param saleId
      * @param partnerId
      * @param companyName
      * @param pageSize
      * @param pageNo
      * @return
      */
    PageInfo<PartnerProfileVO> getPartnerList(Long saleId,Long partnerId, String companyName, Integer pageSize, Integer pageNo);
    
    List<ContractPriceVO> getPartnerContract(List<Long> partnerIdList, Long validDate, Long expiredDate,
            String contractNo, String contractName, Integer pageSize, Integer pageNo, Long partnerId) throws ParseException;

    List<Long> getPartnerId(Long userId);
    
    List<Contract> getContract(DetachedCriteria criteria, Integer firstResults, Integer maxResults);
    
    /**
      * @Description: (閫氳繃contractId鑾峰緱contract )
      * @Description: (get contract from cntractId)
      * @modifyReason: 
      * @author lill
      * @param contractId
      * @return    
      */
    Contract getContract(long contractId);

    /**
      * @Description: (閫氳繃contractId 鑾峰緱鍚堝悓浠锋牸鍒楄〃)
      * @Description: (get contract price list from contractId)
      * @modifyReason: 
      * @author lill
      * @param contractId
      * @return    
      */    
    List<Price> getContractPrice(Long contractId);

    /**
      * @Description: (鏇存柊鍚堝悓鍙婁环鏍�
      * @Description: (update contract and price)
      * @modifyReason: 
      * @author lill
      * @param contractId
      * @param contractPriceVO    
      */
    void updateContractPrice(Long contractId, ContractPriceVO contractPriceVO);

    /**
      * @Description: (灏嗗悎鍚岀殑鐘舵�浠巃ctive鍙樹负deactive)
      * @Description: (update contract status from active to deactive)
      * @modifyReason: 
      * @author lill
      * @param contractId    
      */
    void updateContractActiveToDeactive(Long contractId);
    
    /**
     * @Description: 濡傛灉partnerId绛変簬null灏辨煡鍑烘墍鏈夌殑partnerInfo
     * @Description: if partnerId is null then get all partner info
     * @author zhoujj
     * @param partnerId
     * @return List<PartnerInfoVO>
     */
    public List<PartnerInfoVO> getAllPartnerInfo(Long partnerId);
    /**
     * 
      * @Description: 淇濆瓨浠锋牸
      * @Description: save price
      * @modifyReason: 
      * @author zhengzy
      * @return
     */
    public Price addPrice(Price price);
    
    /**
     * 
      * @Description: 淇敼浠锋牸
      * @Description: modify price
      * @modifyReason: 
      * @author zhengzy
      * @param price
      * @return
     */
    public Price updatePrice(Price price);
    
    /**
     * 
      * @Description: 鍒犻櫎浠锋牸
      * @Description: remove price
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public void deletePrice(Long id);
    
    /**
     * 
      * @Description: 鏍规嵁浠锋牸id闆嗗悎鏌ヨ浠锋牸
      * @Description: query prices by ids
      * @modifyReason: 
      * @author zhengzy
      * @param ids
      * @return
     */
    public List<Price> getPricesByIds(Long[] ids);
    
    /**
     * 
      * @Description: 鏍规嵁priceId鏌ヨprice
      * @Description: query price by priceId
      * @modifyReason: 
      * @author zhengzy
      * @param priceId
      * @return
     */
    Price getPrice(Long priceId);
    
    /**
     * 
      * @Description: 鏍规嵁partnerId鏌ヨ涓�釜鏈夋晥鐨刢ontract
      * @Description: query valid  contract by partnerId
      * @modifyReason: 
      * @author zhengzy
      * @param partnerId
      * @return
     */
    Contract getContractByPartnerId(Long partnerId);
    
    public void updateStaff(StaffInfoVO staff);
    
    public PageInfo<ContractPriceInfoVO> getContractListById(ContractPriceInfoQueryConditionVO conditionVO);
    
    /**
     * 
      * @Description: 根据partnerId查询合同集合
      * @Description: query contract List by partnerId
      * @modifyReason: 
      * @author zhengzy
      * @param partnerId
      * @return
     */
    public List<ContractVO> getContractListByPartnerId(ContractPriceInfoQueryConditionVO conditionVO);
    
    public ContractPriceInfoVO getContractInfoByContractId(Long contractId);

    PageInfo<PartnerProfileVO> getAllPartnerList(Long partnerId, String companyName, Integer pageSize, Integer pageNo);

    public List<Contract> getContractExpiredList();
    
    public Contract updateContract(Contract contract);
    
    PartnerInfo getPartnerInfo(Long partnerId);
    
    PartnerFileInfo getPartnerFileInfo(Long partnerId, int fileType);
    
}
