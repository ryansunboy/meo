package com.asiainfo.meo.prm.prm.app.bo;

import java.util.List;

import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;

public interface PartnerInfoBO
{
    /**
     * @Description: 保存partner profile
     * @Description: save partner profile
     * @author zhaozx
     * @param partnerProfile partner and partnerinfo
     */
    void savePartnerProfile(PartnerProfileVO partnerProfile);
    
    /**
     * @Description: 获得partner信息
     * @Description: get partner profile
     * @author zhaozx
     * @param partnerId
     * @return
     */
    PartnerProfileVO getPartnerProfile(long partnerId);
    
    /**
     * @Description: 注销partner
     * @Description: deactivate partner
     * @author zhaozx
     * @param partnerId partner id
     * @param modifyDate modify date
     */
    void deletePartner(long partnerId);
    
    /**
     * @Description: 修改状态
     * @Description: update status
     * @author zhaozx
     * @param partnerId partner id
     * @param oldSts old status
     * @param newSts new status
     */
    void updatePartnerSts(long partnerId, int oldSts, int newSts);
    
    /**
     * @Description: 修改partner 和 partner information
     * @Description: update partner and partner information
     * @author zhaozx
     * @param partnerInfoVO partner info
     */
    void updatePartnerProfile(PartnerInfoVO partnerInfoVO);
    
    /**
     * @Description: 创建合同但价格是新价格
     * @Description: save partner contract and contract price,but the price need new
     * @modifyReason:
     * @author zhaozx
     * @param contractPriceVO
     */
    void savePartnerContract(ContractPriceVO contractPriceVO);
    
    /**
     * @Description: 创建合同但价格是已经有了的
     * @Description: save partner contract and contract price,but the price has had
     * @author zhaozx
     * @param contract
     * @param priceIdList
     */
    void savePartnerContract(Contract contract, List<Long> priceIdList);
    
    /**
     * @Description: 修改合同价格但价格是新建的
     * @Description: update partner contract price, but the contract price is new
     * @author zhaozx
     * @param cancelContractId
     * @param contractPriceVO
     */
    void updatePartnerContract(long cancelContractId, ContractPriceVO contractPriceVO);
    
    /**
     * @Description: 将合同变失效
     * @Description: deactive contract
     * @author zhaozx
     * @param partnerId
     * @param contractId
     */
    void deletePartnerContract(long partnerId, long contractId);
    
    /**
     * @Description: 获得合同和合同价格
     * @Description: get contract and contract price
     * @author zhaozx
     * @param partnerId
     * @param contractId
     * @return
     */
    ContractPriceVO getPartnerContract(long partnerId, long contractId);
    
    /**
     * @Description: 通过销售员id获得partner信息
     * @Description: get partner profile list
     * @author zhaozx
     * @param saleId
     * @return  List<PartnerProfileVO>
     */
    List<PartnerProfileVO> getPartnerProfileList(long saleId);
}
