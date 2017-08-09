package com.asiainfo.meo.prm.contract.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;

public interface ContractRepository
{
    /**
     * @Description: 保存partner合同
     * @Description: save partner contract
     * @author zhaozx
     * @param contract
     */
    Contract saveContract(Contract contract);
    
    /**
     * @Description: 修改partner合同
     * @Description: update partner contract
     * @author zhaozx
     * @param contract
     */
    Contract updateContract(Contract contract);
    
    /**
     * @Description: 获得合同
     * @Description: get contract
     * @author zhaozx
     * @param contractId
     */
    Contract getContract(long contractId);
    
    
    /**
      * @Description: (根据参数分页查询合同)
      * @Description: (get contract from param order by page)
      * @modifyReason: 
      * @author lill
      * @param criteria
      * @param firstResults
      * @param maxResults
      * @return    
      */
    List<Contract> getContract(DetachedCriteria criteria, Integer firstResults, Integer maxResults);

    /**
     * 
      * @Description: 根据partnerId查询一个有效的contract
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
    
    /**
     * 
      * @Description: 查询出所有状态的contract包括数据是否有效
      * @Description: get all contract by partnerId
      * @modifyReason: 
      * @author zhoujj
      * @param partnerId
      * @return
     */
    List<Contract> getContractListByPartnerId(ContractPriceInfoQueryConditionVO conditionVO,
            Integer pageSize, Integer pageNo);
    
    public Integer getContractListByPartnerIdTotalSize(ContractPriceInfoQueryConditionVO conditionVO);
    
    public List<Contract> getContractExpiredList();
    
}
