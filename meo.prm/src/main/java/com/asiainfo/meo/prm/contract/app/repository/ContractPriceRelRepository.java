package com.asiainfo.meo.prm.contract.app.repository;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.asiainfo.meo.prm.contract.app.model.entity.ContractPriceRel;

public interface ContractPriceRelRepository
{
    /**
     * @Description: 保存partner合同价格关系
     * @Description: save partner contract price relation
     * @author zhaozx
     * @param contractPriceRel
     */
    void saveContractPriceRel(ContractPriceRel contractPriceRel);
    
    /**
     * @Description: 修改partner合同价格关系
     * @Description: update partner contract price relation
     * @author zhaozx
     * @param contractPriceRel
     */
    void updateContractPriceRel(ContractPriceRel contractPriceRel);
    
    /**
     * @Description: 获得合同价格关系
     * @Description: get contract price relation
     * @author zhaozx
     * @param criteria select condition
     */
    List<ContractPriceRel> getContractPriceRel(DetachedCriteria criteria);
}
