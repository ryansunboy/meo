package com.asiainfo.meo.prm.prm.app.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.prm.contract.app.bo.PartnerContractBO;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.prm.app.bo.PartnerInfoBO;
import com.asiainfo.meo.prm.profile.app.bo.PartnerProfileBO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;

/**
 * @Description: TODO(partner information include all partner operation)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author zhaozx
 * @Date 2015-3-2
 */
public class PartnerInfoBOImpl implements PartnerInfoBO
{
    @Resource
    PartnerProfileBO  partnerProfileBO;
    
    @Resource
    PartnerContractBO partnerContractBO;
    
    /**
     * @Description: 保存partner profile
     * @Description: save partner profile
     * @author zhaozx
     * @param partnerProfile partner and partnerinfo
     */
    public void savePartnerProfile(PartnerProfileVO partnerProfile)
    {
      //  partnerProfileBO.savePartnerProfile(partnerProfile);
    }
    
    /**
     * @Description: 获得partner信息
     * @Description: get partner profile
     * @author zhaozx
     * @param partnerId
     * @return
     */
    public PartnerProfileVO getPartnerProfile(long partnerId)
    {
        return partnerProfileBO.getPartnerProfile(partnerId);
    }
    
    /**
     * @Description: 注销partner
     * @Description: deactivate partner
     * @author zhaozx
     * @param partnerId partner id
     * @param modifyDate modify date
     */
    public void deletePartner(long partnerId)
    {
        partnerProfileBO.deletePartner(partnerId);
    }
    
    /**
     * @Description: 修改状态
     * @Description: update status
     * @author zhaozx
     * @param partnerId partner id
     * @param oldSts old status
     * @param newSts new status
     */
    public void updatePartnerSts(long partnerId, int oldSts, int newSts)
    {
        partnerProfileBO.updatePartnerSts(partnerId, oldSts, newSts);
    }
    
    /**
     * @Description: 修改partner 和 partner information
     * @Description: update partner and partner information
     * @author zhaozx
     * @param partnerInfoVO partner info
     */
    public void updatePartnerProfile(PartnerInfoVO partnerInfoVO)
    {
        partnerProfileBO.updatePartnerProfile(partnerInfoVO);
    }
    
    /**
     * @Description: 创建合同但价格是新价格
     * @Description: save partner contract and contract price,but the price need new
     * @modifyReason:
     * @author zhaozx
     * @param contractPriceVO
     */
    public void savePartnerContract(ContractPriceVO contractPriceVO)
    {
        partnerContractBO.savePartnerContract(contractPriceVO);
    }
    
    /**
     * @Description: 创建合同但价格是已经有了的
     * @Description: save partner contract and contract price,but the price has had
     * @author zhaozx
     * @param contract
     * @param priceIdList
     */
    public void savePartnerContract(Contract contract, List<Long> priceIdList)
    {
        partnerContractBO.savePartnerContract(contract, priceIdList);
    }
    
    /**
     * @Description: 修改合同价格但价格是新建的
     * @Description: update partner contract price, but the contract price is new
     * @author zhaozx
     * @param cancelContractId
     * @param contractPriceVO
     */
    public void updatePartnerContract(long cancelContractId, ContractPriceVO contractPriceVO)
    {
        partnerContractBO.updatePartnerContract(cancelContractId, contractPriceVO);
    }
    
    /**
     * @Description: 将合同变失效
     * @Description: deactive contract
     * @author zhaozx
     * @param partnerId
     * @param contractId
     */
    public void deletePartnerContract(long partnerId, long contractId)
    {
        partnerContractBO.deletePartnerContract(partnerId, contractId);
    }
    
    /**
     * @Description: 获得合同和合同价格
     * @Description: get contract and contract price
     * @author zhaozx
     * @param partnerId
     * @param contractId
     * @return
     */
    public ContractPriceVO getPartnerContract(long partnerId, long contractId)
    {

//        partnerContractBO.getPartnerContract(partnerId,contractId);
        return null;
    }
    /**
     * @Description: 通过销售员id获得partner信息
     * @Description: get partner profile list
     * @author zhaozx
     * @param saleId
     * @return  List<PartnerProfileVO>
     */
    @Override
    public List<PartnerProfileVO> getPartnerProfileList(long saleId)
    {
         //return partnerProfileBO.getPartnerProfileList(saleId);
        return null;
    }
    
}
