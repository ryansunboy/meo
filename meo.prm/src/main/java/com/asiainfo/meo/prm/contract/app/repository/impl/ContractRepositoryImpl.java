package com.asiainfo.meo.prm.contract.app.repository.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;
import com.asiainfo.meo.prm.contract.app.repository.ContractRepository;

/**
 * @Description: TODO(partner contract)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author zhaozx
 * @Date 2015-2-27
 */
public class ContractRepositoryImpl implements ContractRepository
{
    private final static String SEQ_CONTRACT_ID = "SEQ_CONTRACT_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 保存partner合同
     * @Description: save partner contract
     * @author zhaozx
     * @param contract
     */
    @Override
    public Contract saveContract(Contract contract)
    {
        Long contractId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CONTRACT_ID);
        contract.setContractId(contractId);
        if(ValidateUtil.isNull(contract.getContractNo()))
        {
            contract.setContractNo(contractId+"");
        }
        hibernateRepository.saveObject(contract);
        return getContract(contract.getContractId());
    }
    
    /**
     * @Description: 修改partner合同
     * @Description: update partner contract
     * @author zhaozx
     * @param contract
     */
    @Override
    public Contract updateContract(Contract contract)
    {
        hibernateRepository.updateObject(contract);
        return getContract(contract.getContractId());
    }
    
    /**
     * @Description: 获得合同
     * @Description: get contract
     * @author zhaozx
     * @param contractId
     */
    @Override
    public Contract getContract(long contractId)
    {
        return hibernateRepository.get(Contract.class, contractId);
    }
   
    @SuppressWarnings("unchecked")
    public List<Contract> getContract(DetachedCriteria criteria, Integer firstResults, Integer maxResults)
    {
        return (List<Contract>)hibernateRepository.findByCriteria(criteria, firstResults, maxResults);
    }

    @Override
    public Contract getContractByPartnerId(Long partnerId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Contract.class);
        criteria.add(Restrictions.eq("partnerId", partnerId));
        criteria.add(Restrictions.eq("sts", Contract.STS_ACTIVE));
        
        //add the date time judge,the currentTime should between validDate and expiredDate
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        criteria.add(Restrictions.le("validDate", currentTime));
        criteria.add(Restrictions.ge("expiredDate", currentTime));
        
        List<Contract> list = (List<Contract>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list)?list.get(0):null;
    }

    @Override
    public List<Long> getPartnerIdsBySaleId(Long saleId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Contract.class);
        criteria.add(Restrictions.eq("operatorId", saleId));
        criteria.add(Restrictions.eq("sts", Contract.STS_ACTIVE));
        List<Contract> list = (List<Contract>)hibernateRepository.findByCriteria(criteria);
        List<Long> partnerIds = new ArrayList<Long>();
        if(ValidateUtil.isNotNull(list))
        {
            for(Contract contract :list)
            {
                partnerIds.add(contract.getPartnerId());
            }
        }
        return partnerIds;
    }
    
    private DetachedCriteria getContractListDetachedCriteria(final ContractPriceInfoQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria = DetachedCriteria.forClass(Contract.class);
        if (ValidateUtil.isNotNull(conditionVO))
        {
            if (ValidateUtil.isNotEmpty(conditionVO.getPartnerId()))
            {
                criteria.add(Restrictions.eq("partnerId", conditionVO.getPartnerId()));
            }
        }
        return criteria;
    }

    @Override
    public List<Contract> getContractListByPartnerId(final ContractPriceInfoQueryConditionVO conditionVO,
            final Integer pageSize,
            final Integer pageNo)
    {
        DetachedCriteria criteria = getContractListDetachedCriteria(conditionVO);
        return (List<Contract>) hibernateRepository.findByCriteria(criteria,
                (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getContractListByPartnerIdTotalSize(final ContractPriceInfoQueryConditionVO conditionVO)
    {
        Integer totalSize = null;
        DetachedCriteria criteria = getContractListDetachedCriteria(conditionVO);
        final List<Contract> list = (List<Contract>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            totalSize = list.size();
        }
        return totalSize;
    }

    @Override
    public List<Contract> getContractExpiredList()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Contract.class);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        criteria.add(Restrictions.lt("expiredDate", currentTime));
        return (List<Contract>)hibernateRepository.findByCriteria(criteria);
    }
    
}
