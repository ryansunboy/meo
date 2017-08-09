package com.asiainfo.meo.prm.contract.app.repository.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.prm.contract.app.model.entity.ContractPriceRel;
import com.asiainfo.meo.prm.contract.app.repository.ContractPriceRelRepository;

/**
 * @Description: TODO(partner contract price)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author zhaozx
 * @Date 2015-2-27
 */
public class ContractPriceRelRepositoryImpl implements ContractPriceRelRepository
{
    private final static String SEQ_CONTRACT_PRICE_REL_ID = "SEQ_CONTRACT_PRICE_REL_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 保存partner合同价格关系
     * @Description: save partner contract price relation
     * @author zhaozx
     * @param contractPriceRel
     */
    @Override
    public void saveContractPriceRel(ContractPriceRel contractPriceRel)
    {
        contractPriceRel.setId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CONTRACT_PRICE_REL_ID));
        hibernateRepository.saveObject(contractPriceRel);
    }
    
    /**
     * @Description: 修改partner合同价格关系
     * @Description: update partner contract price relation
     * @author zhaozx
     * @param contractPriceRel
     */
    @Override
    public void updateContractPriceRel(ContractPriceRel contractPriceRel)
    {
        hibernateRepository.updateObject(contractPriceRel);
    }
    
    /**
     * @Description: 获得合同价格关系
     * @Description: get contract price relation
     * @author zhaozx
     * @param criteria select condition
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ContractPriceRel> getContractPriceRel(DetachedCriteria criteria)
    {
        return (List<ContractPriceRel>) hibernateRepository.findByCriteria(criteria);
    }
}
