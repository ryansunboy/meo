package com.asiainfo.meo.prm.contract.app.bo;

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

/**
 * @Description: partner鍚堝悓BO
 * @Description: partner contract BO
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-14 涓嬪崍05:42:56
 * @version 1.0
 */
public interface PartnerContractBO
{
    /**
     * @Description: 鍒涘缓鍚堝悓浣嗕环鏍兼槸鏂颁环鏍�
     * @Description: save partner contract and contract price,but the price need new
     * @modifyReason:
     * @author zhaozx
     * @param contractPriceVO
     */
    Contract savePartnerContract(ContractPriceVO contractPriceVO);
    
    /**
     * @Description: 鍒涘缓鍚堝悓浣嗕环鏍兼槸宸茬粡鏈変簡鐨�
     * @Description: save partner contract and contract price,but the price has had
     * @author zhaozx
     * @param contract
     * @param priceIdList
     */
    void savePartnerContract(Contract contract, List<Long> priceIdList);
    
    /**
     * @Description: 淇敼鍚堝悓浠锋牸浣嗕环鏍兼槸鏂板缓鐨�
     * @Description: update partner contract price, but the contract price is new
     * @author zhaozx
     * @param cancelContractId
     * @param contractPriceVO
     */
    void updatePartnerContract(long cancelContractId, ContractPriceVO contractPriceVO);
    
    /**
     * @Description: 灏嗗悎鍚屽彉澶辨晥
     * @Description: deactive contract
     * @author zhaozx
     * @param partnerId
     * @param contractId
     */
    void deletePartnerContract(long partnerId, long contractId);
    
    /**
     * @Description: 鏍规嵁鏉′欢鑾峰緱鍚堝悓鍜屽悎鍚屼环鏍�
     * @Description: partner get contract and contract price
     * @modifyReason:
     * @author lill
     * @param contractId
     * @param partnerIdList
     * @param validDate
     * @param expiredDate
     * @param contractNo
     * @param contractName
     * @param pageSize
     * @param pageNo
     * @return
     * @throws ParseException 
     */
    List<ContractPriceVO> getPartnerContract(List<Long> partnerIdList, Long validDate, Long expiredDate,
            String contractNo, String contractName, Integer pageSize, Integer pageNo, Long partnerId) throws ParseException;
    
    List<Contract> getContract(DetachedCriteria criteria, Integer firstResults, Integer maxResults);
    
	/**
	 * 
	  * @Description: (婵�椿contract)
	  * @Description: (active contract)
	  * @modifyReason: 
	  * @author zhengzy
	  * @param contractId
	 */
	void updateContractActive(long contractId);
	
	Contract getContract(long contractId);

    List<Price> getContractPrice(Long contractId);

    void updateContractPrice(Long contractId, ContractPriceVO contractPriceVO);

    void updateContractActiveToDeactive(Long contractId);
    
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
    
    /**
     * 
      * @Description: 通过saleId查询partnerIds
      * @Description: query partnerids by saleId
      * @modifyReason: 
      * @author zhengzy
      * @param saleId
      * @return
     */
    List<Long> getPartnerIdsBySaleId(Long saleId);
    
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
    
    public List<Contract> getContractExpiredList();
    
    Contract updateContract(Contract contract);
    
}
