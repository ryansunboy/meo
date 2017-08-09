package com.asiainfo.meo.prm.contract.app.bo.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.bo.PartnerContractBO;
import com.asiainfo.meo.prm.contract.app.constant.PartnerContractErrorConstant;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.ContractPriceRel;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPricesVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractVO;
import com.asiainfo.meo.prm.contract.app.repository.ContractPriceRelRepository;
import com.asiainfo.meo.prm.contract.app.repository.ContractRepository;
import com.asiainfo.meo.prm.contract.app.repository.PriceRepository;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.profile.app.bo.PartnerProfileBO;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
import com.asiainfo.meo.prm.service.require.PartnerRserviceBO;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;

public class PartnerContractBOImpl implements PartnerContractBO
{
    private static final Log   LOG = LogFactory.getLog(PartnerContractBOImpl.class);
    
    @Resource
    ContractPriceRelRepository contractPriceRelRepository;
    
    @Resource
    ContractRepository         contractRepository;
    
    @Resource
    PriceRepository            priceRepository;
    
    @Resource
    PartnerRserviceBO          prmRserviceBO;
    
    @Resource
    PartnerProfileBO           partnerProfileBO;
    
    /**
     * @Description: 鑾峰緱鍚堝悓
     * @Description: get contract
     * @author zhaozx
     * @param contractId
     * @return
     */
    public Contract getContract(long contractId)
    {
        return contractRepository.getContract(contractId);
    }
    
    public List<Contract> getContract(DetachedCriteria criteria, Integer firstResults, Integer maxResults)
    {
        return contractRepository.getContract(criteria, firstResults, maxResults);
    }
    
    /**
     * @Description: 淇濆瓨鍚堝悓
     * @Description: save contract
     * @author zhaozx
     * @param contract
     */
    private Contract saveContract(Contract contract)
    {
        return contractRepository.saveContract(contract);
    }
    
    /**
     * @Description: 淇濆瓨鍚堝悓浠锋牸
     * @Description: save price
     * @author zhaozx
     * @param price
     */
    private void savePrice(Price price)
    {
        priceRepository.savePrice(price);
    }
    
    /**
     * @Description: 淇濆瓨鍚堝悓浠锋牸鍏崇郴
     * @Description: save contract price relation
     * @author zhaozx
     * @param contractPriceRel
     */
    private void saveContractPriceRel(ContractPriceRel contractPriceRel)
    {
        contractPriceRelRepository.saveContractPriceRel(contractPriceRel);
    }
    
    /**
     * @Description: 鑾峰緱partner
     * @Description: get partner
     * @author zhaozx
     * @param partnerId
     * @return
     */
    private Partner getPartner(long partnerId)
    {
        return prmRserviceBO.getPartner(partnerId);
    }
    
    /**
     * @Description: 鍒濆鍖栦环鏍煎叧绯诲疄浣�
     * @Description: init contract price relation entity
     * @author zhaozx
     * @param contractId
     * @param priceId
     * @param modifyDate
     * @param expiredDate
     * @return
     */
    private ContractPriceRel initParnterContractPriceRel(long contractId, long priceId, Timestamp modifyDate,
            Timestamp expiredDate)
    {
        ContractPriceRel contractPriceRel = new ContractPriceRel();
        contractPriceRel.setContractId(contractId);// set contract id
        contractPriceRel.setPriceId(priceId);// set contract price id
        contractPriceRel.setExpiredDate(expiredDate);
        contractPriceRel.setValidDate(modifyDate);
        contractPriceRel.setSts(ContractPriceRel.STS_VALID);
        contractPriceRel.setModifyDate(modifyDate);
        return contractPriceRel;
    }
    
    /**
     * @Description: 淇敼鍚堝悓浠锋牸鍏崇郴
     * @Description: update contract price relation
     * @author zhaozx
     * @param contractPriceRel
     */
    private void updateContractPriceRel(ContractPriceRel contractPriceRel)
    {
        contractPriceRelRepository.updateContractPriceRel(contractPriceRel);
    }
    
    /**
     * @Description: 淇敼鍚堝悓
     * @Description: update contract
     * @modifyReason:
     * @author zhaozx
     * @param contract
     */
    public Contract updateContract(Contract contract)
    {
        return contractRepository.updateContract(contract);
    }
    
    /**
     * @Description: 鑾峰緱鍚堝悓浠锋牸
     * @Description: get contract price
     * @author zhaozx
     * @param priceId
     * @return
     */
    @SuppressWarnings("unused")
    private Price getPrice(long priceId)
    {
        return priceRepository.getPrice(priceId);
    }
    
    /**
     * @Description: 浣跨敤DetachedCriteria鑾峰緱
     * @Description: get contract price relation entity list
     * @author zhaozx
     * @param criteria select condition
     * @return
     */
    private List<ContractPriceRel> getContractPriceRelList(DetachedCriteria criteria)
    {
        return contractPriceRelRepository.getContractPriceRel(criteria);
    }
    
    /**
     * @Description: 灏嗗悎鍚屼环鏍煎叧绯昏澶辨晥
     * @Description: deactive contract price relation
     * @author zhaozx
     * @param contractId
     */
    @SuppressWarnings("unused")
    private void deactiveContractPriceRel(long contractId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ContractPriceRel.class)
                .add(Restrictions.eq("contractId", contractId)).add(Restrictions.eq("sts", ContractPriceRel.STS_VALID));
        
        List<ContractPriceRel> contractPriceRelList = getContractPriceRelList(criteria);
        if (contractPriceRelList== null|| contractPriceRelList.size()<= 0)
            return;
        for (ContractPriceRel contractPriceRel : contractPriceRelList)
        {
            Timestamp modifyDate = DateTimeUtil.getNow();
            contractPriceRel.setExpiredDate(modifyDate);
            contractPriceRel.setModifyDate(modifyDate);
            contractPriceRel.setSts(ContractPriceRel.STS_INVALID);// set relation invalid
            updateContractPriceRel(contractPriceRel);// update contract relation
        }
    }
    
    /**
     * @Description: 鍒涘缓鍚堝悓浣嗕环鏍兼槸鏂颁环鏍�
     * @Description: save partner contract and contract price,but the price need new
     * @author zhaozx
     * @param contractPriceVO
     */
    @Override
    public Contract savePartnerContract(ContractPriceVO contractPriceVO)
    {
        LOG.info("create partner contract and the contract price is new price begin!");
        if (ValidateUtil.isEmpty(contractPriceVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.CONTRACT_PRICEVO });
        Contract contract = contractPriceVO.getContract();// contract entity
        
        if (ValidateUtil.isEmpty(contract))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        
        long partnerId = contract.getPartnerId();
        boolean flag = checkPartnerId(partnerId);
        if (!flag)
        {
            LOG.info("partner is not found when you create partner contract, and the contract price is new price end!");
            throw new MeoException(PartnerContractErrorConstant.PARTNER_IS_NOT_FOUND, new Object[]{partnerId });
        }
        /**
         * 
         */
        Contract tempContract = getContractByPartnerId(partnerId);
        if (ValidateUtil.isNotNull(tempContract))
        {
            LOG.info("the validDate contract already exist,the partnerId is "+ partnerId);
            throw new MeoException(PartnerContractErrorConstant.VALID_CONTRACT_ALREADY_EXIST_BY_PARTNER_ID,
                    new Object[]{partnerId });
        }
        
        contract.setSts(Contract.STS_INACTIVE);// set contract status
        saveContract(contract);// save contract entity
        List<Price> priceList = contractPriceVO.getPrices();// get contract price entity list
        for (Price price : priceList)
        {
            price.setSts(Price.STS_VALID);// set contract price
            savePrice(price);// save contract price enity
            ContractPriceRel contractPriceRel = initParnterContractPriceRel(contract.getContractId(), price.getPriceId(),
                    contract.getValidDate(), price.getExpiredDate());// init contract
            saveContractPriceRel(contractPriceRel);// save contract price relation entity
        }
        LOG.info("create partner contract and the contract price is new price end!");
        return contract;
    }
    
    private boolean checkPartnerId(long partnerId)
    {
        boolean flag = false;
        if (ValidateUtil.isNull(partnerId))
            return flag;
        Partner partner = getPartner(partnerId);
        if (ValidateUtil.isEmpty(partner))
            return flag;
        else if (Partner.STS_INACTIVE== partner.getSts())
            return flag;
        return true;
    }
    
    /**
     * @Description: 淇敼鍚堝悓浠锋牸浣嗕环鏍兼槸鏂板缓鐨�
     * @Description: update partner contract price, but the contract price is new
     * @author zhaozx
     * @param cancelContractId
     * @param contractPriceVO
     */
    @Override
    public void updatePartnerContract(long cancelContractId, ContractPriceVO contractPriceVO)
    {
        LOG.info("modify partner contract begin!");
        deletePartnerContract(contractPriceVO.getContract().getPartnerId(), cancelContractId);// deactive contract and contract
        savePartnerContract(contractPriceVO);// save new contract
        LOG.info("modify partner contract end!");
    }
    
    @Override
    public List<ContractPriceVO> getPartnerContract(List<Long> partnerIdList, Long validDate, Long expiredDate,
            String contractNo, String contractName, Integer pageSize, Integer pageNo, Long partnerId) throws ParseException
    {
        LOG.info("get partner contract begin!");
        
        DetachedCriteria criteria = DetachedCriteria.forClass(Contract.class);
        if (ValidateUtil.isNotEmpty(partnerIdList))
            criteria.add(Restrictions.in("partnerId", partnerIdList));
        
        if (ValidateUtil.isNotEmpty(validDate))
            criteria.add(Restrictions.ge("validDate", new Timestamp(validDate)));
        
        if (ValidateUtil.isNotEmpty(expiredDate))
            criteria.add(Restrictions.le("expiredDate", new Timestamp(expiredDate)));
        
        if (ValidateUtil.isNotEmpty(contractNo))
            criteria.add(Restrictions.like("contractNo", "%"+ contractNo+ "%"));
        
        if (ValidateUtil.isNotEmpty(contractName))
            criteria.add(Restrictions.like("contractName", "%"+ contractName+ "%"));
        
        if (ValidateUtil.isNotEmpty(partnerId))
            criteria.add(
            // Restrictions.like("partnerId", "%"+partnerId+"%")
            Restrictions
                    .sqlRestriction("CAST({alias}.PARTNER_ID AS CHAR) like ?", "%"+ partnerId+ "%", StandardBasicTypes.STRING));
        
        List<ContractPriceVO> contractPriceVOList = new ArrayList<ContractPriceVO>();
        List<Contract> contractList = contractRepository.getContract(criteria, (pageNo- 1)* pageSize, pageNo* pageSize);
        for (Contract contract : contractList)
        {
            ContractPriceVO contractPriceVO = new ContractPriceVO();
            contractPriceVO.setContract(contract);
            DetachedCriteria criteria1 = DetachedCriteria.forClass(ContractPriceRel.class);
            criteria1.add(Restrictions.eq("contractId", contract.getContractId()));
            
            List<ContractPriceRel> contractPriceRelList = getContractPriceRelList(criteria1);
            List<Long> priceIdList = new ArrayList<Long>();
            
            for (ContractPriceRel contractPriceRel : contractPriceRelList)
                priceIdList.add(contractPriceRel.getPriceId());
            
            DetachedCriteria criteria2 = DetachedCriteria.forClass(Price.class);
            criteria2.add(Restrictions.in("priceId", priceIdList));
            List<Price> priceList = priceRepository.getPrice(criteria2);
            
            for (Price price : priceList)
                contractPriceVO.getPrices().add(price);
            contractPriceVOList.add(contractPriceVO);
        }
        return contractPriceVOList;
    }
    
    @Override
    public void savePartnerContract(Contract contract, List<Long> priceIdList)
    {
        
    }
    
    @Override
    public void deletePartnerContract(long partnerId, long contractId)
    {
        
    }
    
    /**
     * @Description: (婵�椿contract)
     * @Description: (active contract)
     * @modifyReason:
     * @author zhengzy
     * @param contractId
     * @param sts
     */
    @Override
    public void updateContractActive(long contractId)
    {
        LOG.info("update contract sts begin!");
        Contract contract = contractRepository.getContract(contractId);
        if (ValidateUtil.isEmpty(contract))
        {
            LOG.info("contract is not found,contract id is "+ contractId);
            throw new MeoException(PartnerContractErrorConstant.CONTRACT_IS_NOT_FOUND, new Object[]{contractId });
        }
        if (contract.getSts()== Contract.STS_ACTIVE)
        {
            LOG.info("contract is already actived,contractId is"+ contractId);
            throw new MeoException(PartnerContractErrorConstant.CONTRACT_IS_ALREADY_ACTIVED, new Object[]{contractId });
        }
        contract.setSts(Contract.STS_ACTIVE);
        contractRepository.updateContract(contract);
        LOG.info("update contract sts end!");
    }
    
    @Override
    public List<Price> getContractPrice(Long contractId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ContractPriceRel.class);
        criteria.add(Restrictions.eq("contractId", contractId));
        
        List<ContractPriceRel> contractPriceRelList = getContractPriceRelList(criteria);
        List<Long> priceIdList = new ArrayList<Long>();
        for (ContractPriceRel contractPriceRel : contractPriceRelList)
            priceIdList.add(contractPriceRel.getPriceId());
        DetachedCriteria criteria1 = DetachedCriteria.forClass(Price.class);
        criteria1.add(Restrictions.in("priceId", priceIdList));
        return priceRepository.getPrice(criteria1);
    }
    
    @Override
    public void updateContractPrice(Long contractId, ContractPriceVO contractPriceVO)
    {
        if (ValidateUtil.isNull(contractPriceVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.CONTRACT_PRICEVO });
        
        if (ValidateUtil.isNull(contractPriceVO.getContract()))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        
        updateContract(contractPriceVO.getContract());
        List<Price> priceList = contractPriceVO.getPrices();
        if (ValidateUtil.isEmpty(priceList))
            return;
        for (Price price : priceList)
            updatePrice(price);
    }
    
    public Price updatePrice(Price price)
    {
        return priceRepository.updatePrice(price);
    }
    
    @Override
    public void updateContractActiveToDeactive(Long contractId)
    {
        LOG.info("update contract sts begin!");
        Contract contract = contractRepository.getContract(contractId);
        if (ValidateUtil.isEmpty(contract))
        {
            LOG.info("contract is not found,contract id is "+ contractId);
            throw new MeoException(PartnerContractErrorConstant.CONTRACT_IS_NOT_FOUND, new Object[]{contractId });
        }
        contract.setSts(Contract.STS_DEACTIVE);
        contractRepository.updateContract(contract);
        LOG.info("update contract sts end!");
    }
    
    @Override
    public Price addPrice(Price price)
    {
        return priceRepository.savePrice(price);
    }
    
    @Override
    public void deletePrice(Long id)
    {
        priceRepository.deletePrice(id);
    }
    
    @Override
    public List<Price> getPricesByIds(Long[] ids)
    {
        return priceRepository.getPricesByIds(ids);
    }
    
    @Override
    public Price getPrice(Long priceId)
    {
        return priceRepository.getPrice(priceId);
    }
    
    @Override
    public Contract getContractByPartnerId(Long partnerId)
    {
        return contractRepository.getContractByPartnerId(partnerId);
    }
    
    @Override
    public List<Long> getPartnerIdsBySaleId(Long saleId)
    {
        return contractRepository.getPartnerIdsBySaleId(saleId);
    }
    
    @Override
    public PageInfo<ContractPriceInfoVO> getContractListById(ContractPriceInfoQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
        }
        
        final PageInfo<ContractPriceInfoVO> pageInfo = new PageInfo<ContractPriceInfoVO>();
        Integer totalSize = contractRepository.getContractListByPartnerIdTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        
        List<Contract> contractList = contractRepository.getContractListByPartnerId(conditionVO, pageSize, pageNo);
        List<ContractPriceInfoVO> ContractPriceInfoVOList = new ArrayList<ContractPriceInfoVO>();
        for (Contract contract : contractList)
        {
            List<ContractPricesVO> contractPrices = new ArrayList<ContractPricesVO>();
            ContractPriceInfoVO contractPriceInfoVO = new ContractPriceInfoVO();
            BeanUtils.copyProperties(contract, contractPriceInfoVO);
            contractPriceInfoVO.setSignedDate(contract.getSignedDate()!= null ? contract.getSignedDate().getTime() : null);
            contractPriceInfoVO.setValidate(contract.getValidDate()!= null ? contract.getValidDate().getTime() : null);
            contractPriceInfoVO.setExpiredDate(contract.getExpiredDate()!= null ? contract.getExpiredDate().getTime() : null);
            if (contract.getSts()!= null)
            {
                EnumDefine enumDefine = prmRserviceBO.getEnumDefByEnumCode(String.valueOf(contract.getSts()),
                        Integer.valueOf(Contract.CONTRACT_STS));
                contractPriceInfoVO.setStatusCode(contract.getSts());
                contractPriceInfoVO.setStatusName(enumDefine!= null ? enumDefine.getEnumName() : null);
            }
            List<Price> priceList = getContractPrice(contract.getContractId());
            for (Price price : priceList)
            {
                ContractPricesVO contractPricesVO = new ContractPricesVO();
                contractPricesVO.setPrice(price.getUnitPrice());
                contractPricesVO.setPriceId(price.getPriceId());
                if (price.getUnit()!= null)
                {
                    List<MeasureDefine> measureDefineList = prmRserviceBO.getCurrencyUnit(price.getUnit().longValue());
                    for (MeasureDefine measureDefine : measureDefineList)
                    {
                        contractPricesVO.setPriceUnitId(price.getUnit());
                        contractPricesVO.setPriceUnit(measureDefine.getName());
                    }
                }
                contractPricesVO.setPriceType(price.getPriceType());
                contractPrices.add(contractPricesVO);
            }
            StaffPartnerRel staffPartnerRel = partnerProfileBO.getStaffPartnerRel(conditionVO.getPartnerId());
            if (staffPartnerRel!= null)
            {
                contractPriceInfoVO.setSaleId(staffPartnerRel.getStaffId());
            }
            contractPriceInfoVO.setContractPrices(contractPrices);
            ContractPriceInfoVOList.add(contractPriceInfoVO);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, ContractPriceInfoVOList);
    }
    
    @Override
    public List<ContractVO> getContractListByPartnerId(final ContractPriceInfoQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
        }
        List<Contract> list = contractRepository.getContractListByPartnerId(conditionVO, pageSize, pageNo);
        if (ValidateUtil.isEmpty(list))
        {
            return null;
        }
        List<ContractVO> voList = new ArrayList<ContractVO>();
        for (Contract contract : list)
        {
            ContractVO vo = new ContractVO();
            vo.setContractId(contract.getContractId());
            vo.setContractName(contract.getContractName());
            vo.setContractNo(contract.getContractNo());
            vo.setContractStatus(contract.getSts());
            vo.setValidDate(contract.getValidDate().getTime());
            vo.setExpiredDate(contract.getExpiredDate().getTime());
            voList.add(vo);
        }
        return voList;
    }
    
    @Override
    public ContractPriceInfoVO getContractInfoByContractId(Long contractId)
    {
        Contract contract = contractRepository.getContract(contractId);
        ContractPriceInfoVO contractPriceInfoVO = new ContractPriceInfoVO();
        List<ContractPricesVO> contractPrices = new ArrayList<ContractPricesVO>();
        BeanUtils.copyProperties(contract, contractPriceInfoVO);
        contractPriceInfoVO.setSignedDate(contract.getSignedDate()!= null ? contract.getSignedDate().getTime() : null);
        contractPriceInfoVO.setValidate(contract.getValidDate()!= null ? contract.getValidDate().getTime() : null);
        contractPriceInfoVO.setExpiredDate(contract.getExpiredDate()!= null ? contract.getExpiredDate().getTime() : null);
        if (contract.getSts()!= null)
        {
            EnumDefine enumDefine = prmRserviceBO.getEnumDefByEnumCode(String.valueOf(contract.getSts()),
                    Integer.valueOf(Contract.CONTRACT_STS));
            contractPriceInfoVO.setStatusCode(contract.getSts());
            contractPriceInfoVO.setStatusName(enumDefine!= null ? enumDefine.getEnumName() : null);
        }
        List<Price> priceList = getContractPrice(contract.getContractId());
        for (Price price : priceList)
        {
            ContractPricesVO contractPricesVO = new ContractPricesVO();
            contractPricesVO.setPrice(price.getUnitPrice());
            contractPricesVO.setPriceId(price.getPriceId());
            if (price.getUnit()!= null)
            {
                List<MeasureDefine> measureDefineList = prmRserviceBO.getCurrencyUnit(price.getUnit().longValue());
                for (MeasureDefine measureDefine : measureDefineList)
                {
                    contractPricesVO.setPriceUnitId(price.getUnit());
                    contractPricesVO.setPriceUnit(measureDefine.getName());
                }
            }
            contractPricesVO.setPriceType(price.getPriceType());
            contractPrices.add(contractPricesVO);
        }
        if (contract.getPartnerId()!= null)
        {
            StaffPartnerRel staffPartnerRel = partnerProfileBO.getStaffPartnerRel(contract.getPartnerId());
            if (staffPartnerRel!= null)
            {
                contractPriceInfoVO.setSaleId(staffPartnerRel.getStaffId());
            }
        }
        contractPriceInfoVO.setContractPrices(contractPrices);
        return contractPriceInfoVO;
    }

    @Override
    public List<Contract> getContractExpiredList()
    {
        return contractRepository.getContractExpiredList();
    }
}
