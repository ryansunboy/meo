package com.asiainfo.meo.test.prm;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.prm.contract.app.bo.PartnerContractBO;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.ContractPrice;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;

public class TestPartnerContractOperation
{
    Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * @Description: 创建合同但价格是新的
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testCreateContractAndNewPrice() throws IOException
    {
        Timestamp time = DateTimeUtil.getNow();
        Timestamp timeExpired = Timestamp.valueOf("9999-12-31 23:59:59");
        long partnerId = 100000021000L;
        
        ContractPrice contractPrice = new ContractPrice();
        contractPrice.setDiscountId(111111L);
        contractPrice.setExpiredDate(timeExpired);
        contractPrice.setPriceType(1);
        contractPrice.setUnit(1);
        contractPrice.setUnitPrice(1L);
        contractPrice.setValidDate(time);
        contractPrice.setModifyDate(time);
        
        ContractPrice contractPrice1 = new ContractPrice();
        contractPrice1.setDiscountId(22222L);
        contractPrice1.setExpiredDate(timeExpired);
        contractPrice1.setPriceType(2);
        contractPrice1.setUnit(2);
        contractPrice1.setUnitPrice(2L);
        contractPrice1.setValidDate(time);
        contractPrice1.setModifyDate(time);
        
        Contract contract = new Contract();
        contract.setPartnerId(partnerId);
        contract.setSaleId(100000006000L);
        contract.setBuyers("aa");
        contract.setContractName("AIS");
        contract.setContractNo(11111L);
        contract.setContractUrl("232323.jpg");
        contract.setExpiredDate(timeExpired);
        contract.setSellers("bb");
        contract.setSignedDate(time);
        contract.setSignedPlace("china");
        contract.setSts(1);
        contract.setTempleteId(1000000L);
        contract.setValidDate(time);
        
        ContractPriceVO contractPriceVO = new ContractPriceVO();
        contractPriceVO.setContract(contract);
        contractPriceVO.getContractPricelList().add(contractPrice);
        contractPriceVO.getContractPricelList().add(contractPrice1);
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(contractPriceVO));
        
        PartnerContractBO partnerContractBO = ServiceLocatorFactory.getService(PartnerContractBO.class);
        partnerContractBO.savePartnerContract(contractPriceVO);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(contractPriceVO));
    }
    
    /**
     * @Description: 测试创建合同但价格是旧的
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testCreateContractAndOldPrice() throws IOException
    {
        long partnerId = 100000021000L;
        long priceId1 = 100000027000L;
        long priceId2 = 100000027001L;
        Timestamp time = DateTimeUtil.getNow();
        Timestamp timeExpired = Timestamp.valueOf("9999-12-31 23:59:59");
        
        Contract contract = new Contract();
        contract.setPartnerId(partnerId);
        contract.setSaleId(100000006000L);
        contract.setBuyers("cc");
        contract.setContractName("AIS");
        contract.setContractNo(11111L);
        contract.setContractUrl("2343323.jpg");
        contract.setExpiredDate(timeExpired);
        contract.setSellers("dd");
        contract.setSignedDate(time);
        contract.setSignedPlace("USA");
        contract.setSts(1);
        contract.setTempleteId(222222L);
        contract.setValidDate(time);
        
        List<Long> priceIdList = new ArrayList<Long>();
        priceIdList.add(priceId1);
        priceIdList.add(priceId2);
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(contract)+ "\t"+ JsonUtil.writeObjectAsString(priceIdList));
        
        PartnerContractBO partnerContractBO = ServiceLocatorFactory.getService(PartnerContractBO.class);
        partnerContractBO.savePartnerContract(contract, priceIdList);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(contract)+ "\t"+ JsonUtil.writeObjectAsString(priceIdList));
    }
    
    /**
     * @Description: 测试得到 partner contract
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException 
     */
    @Test
    public void testGetPartnerContract() throws IOException
    {
        long partnerId = 100000021000L;
        long contractId = 100000034000L;
        logger.info("in:"+ "partnerId="+partnerId+"\t"+"contractId="+contractId);
        
        PartnerContractBO partnerContractBO = ServiceLocatorFactory.getService(PartnerContractBO.class);
        ContractPriceVO contractPriceVO = partnerContractBO.getPartnerContract(partnerId, contractId);
        
        logger.info("out:"+JsonUtil.writeObjectAsString(contractPriceVO));
    }
    
    /**
     * @Description: 测试注销合同
     * @Description: (English description)
     * @author zhaozx
     */
    @Test
    public void testDeactiveContract()
    {
        long partnerId = 100000021000L;
        long cancelContractId = 100000034000L;
        
        logger.info("in:"+ "partnerId="+partnerId+"\t"+"cancelContractId="+cancelContractId);
        PartnerContractBO partnerContractBO = ServiceLocatorFactory.getService(PartnerContractBO.class);
        partnerContractBO.deletePartnerContract(partnerId, cancelContractId);
    }
    
    /**
     * @Description: 修改合同
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws Exception
     */
    @Test
    public void testUpdatePartnerContractAndNewPrice() throws Exception
    {
        long partnerId = 100000021000L;
        long cancelContractId = 100000033000L;
        Timestamp time = DateTimeUtil.getNow();
        Timestamp timeExpired = Timestamp.valueOf("9999-12-31 23:59:59");
        
        Contract contract = new Contract();
        contract.setPartnerId(partnerId);
        contract.setSaleId(100000006000L);
        contract.setBuyers("aa");
        contract.setContractName("AIS");
        contract.setContractNo(11111L);
        contract.setContractUrl("232323.jpg");
        contract.setExpiredDate(timeExpired);
        contract.setSellers("bb");
        contract.setSts(1);
        contract.setSignedDate(time);
        contract.setSignedPlace("china");
        contract.setTempleteId(1000000L);
        contract.setValidDate(time);
        
        ContractPrice contractPrice = new ContractPrice();
        contractPrice.setDiscountId(111111L);
        contractPrice.setExpiredDate(timeExpired);
        contractPrice.setPriceType(1);
        contractPrice.setUnit(1);
        contractPrice.setUnitPrice(1L);
        contractPrice.setValidDate(time);
        contractPrice.setModifyDate(time);
        
        ContractPrice contractPrice1 = new ContractPrice();
        contractPrice1.setDiscountId(22222L);
        contractPrice1.setExpiredDate(timeExpired);
        contractPrice1.setPriceType(2);
        contractPrice1.setUnit(2);
        contractPrice1.setUnitPrice(2L);
        contractPrice1.setValidDate(time);
        contractPrice1.setModifyDate(time);
        
        List<ContractPrice> newContractPriceList = new ArrayList<ContractPrice>();
        newContractPriceList.add(contractPrice);
        newContractPriceList.add(contractPrice1);
        
        ContractPriceVO contractPriceVO = new ContractPriceVO();
        contractPriceVO.setContract(contract);
        contractPriceVO.setContractPricelList(newContractPriceList);
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(contractPriceVO)+ "\t"+ "cancelContractId="+ cancelContractId);
        
        PartnerContractBO partnerContractBO = ServiceLocatorFactory.getService(PartnerContractBO.class);
        
        partnerContractBO.updatePartnerContract(cancelContractId, contractPriceVO);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(contractPriceVO));
    }
    
}
