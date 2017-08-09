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
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.ContractPrice;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.prm.app.bo.PartnerInfoBO;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;

public class TestPartnerInfoOperation
{
    Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * @Description: 创建合同但价格是新的
     * @Description: (English description)
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testCreateContractAndNewPrice() throws IOException
    {
        Timestamp time = DateTimeUtil.getNow();
        Timestamp timeExpired = Timestamp.valueOf("9999-12-31 23:59:59");
        long partnerId = 100000022000L;
        
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
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.savePartnerContract(contractPriceVO);
        
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
        long partnerId = 100000022000L;
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
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.savePartnerContract(contract, priceIdList);
        
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
        long partnerId = 100000022000L;
        long contractId = 100000037000L;
        logger.info("in:"+ "partnerId="+ partnerId+ "\t"+ "contractId="+ contractId);
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        ContractPriceVO contractPriceVO = partnerInfoBO.getPartnerContract(partnerId, contractId);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(contractPriceVO));
    }
    
    /**
     * @Description: 测试注销合同
     * @Description: (English description)
     * @author zhaozx
     */
    @Test
    public void testDeactiveContract()
    {
        long partnerId = 100000022000L;
        long cancelContractId = 100000038000L;
        
        logger.info("in:"+ "partnerId="+ partnerId+ "\t"+ "cancelContractId="+ cancelContractId);
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.deletePartnerContract(partnerId, cancelContractId);
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
        long partnerId = 100000022000L;
        long cancelContractId = 100000037000L;
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
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        
        partnerInfoBO.updatePartnerContract(cancelContractId, contractPriceVO);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(contractPriceVO));
    }
    
    @Test
    public void testCreatePartnerProfile() throws IOException
    {
        Timestamp timeExpired = Timestamp.valueOf("9999-12-31 23:59:59");
        
        Partner partner = new Partner();
        partner.setPartnerType(1);
        
        PartnerInfo partnerInfo = new PartnerInfo();
        partnerInfo.setBillAddr("123456");
        partnerInfo.setBusinessCode("111111");
        partnerInfo.setCountryCode(1);
        partnerInfo.setProvinceCode(2);
        partnerInfo.setCityCode(3);
        partnerInfo.setPostCode(123L);
        partnerInfo.setExpiredDate(timeExpired);
        partnerInfo.setMsisdn("110");
        partnerInfo.setTaxCode("222222");
        partnerInfo.setCompanyName("AIS");
        partnerInfo.setContactEmail("123456@qq.com");
        partnerInfo.setContactMsidn("111");
        partnerInfo.setContactName("Tom");
        partnerInfo.setEmailAddr("654321@qq.com");
        
        PartnerProfileVO partnerProfile = new PartnerProfileVO();
        partnerProfile.setPartner(partner);
        partnerProfile.setPartnerInfo(partnerInfo);
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(partnerProfile));
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.savePartnerProfile(partnerProfile);
        
        logger.info("out:"+ JsonUtil.writeObjectAsString(partnerProfile));
    }
    
    /**
     * @Description: 修改partner信息
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testUpdatePartnerProfile() throws IOException
    {
        PartnerInfoVO info = new PartnerInfoVO();
        info.setPartnerId(100000022000L);
        info.setPartnerType(0);
        info.setBillAddr("123");
        info.setBusinessCode("111");
        info.setCountryCode(1);
        info.setProvinceCode(2);
        info.setCityCode(3);
        info.setPostCode(123L);
        info.setMsisdn("2222");
        info.setTaxCode("111");
        info.setCompanyName("AIS1");
        info.setConfirmEmail("123@qq.com");
        info.setContactMsidn("111");
        info.setContactName("Tom1");
        info.setEmailAddr("654@qq.com");
        
        logger.info("in:"+ JsonUtil.writeObjectAsString(info));
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.updatePartnerProfile(info);
        
    }
    
    /**
     * @Description: 获得partner信息
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException
     */
    @Test
    public void testGetPartnerProfile() throws IOException
    {
        long partnerId = 100000022000L;
        
        logger.info("in:"+ "partnerId="+ partnerId);
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        PartnerProfileVO partnerProfile = partnerInfoBO.getPartnerProfile(partnerId);
        if (partnerProfile.getPartnerInfo()!= null)
        {
            logger.info("out:"+ JsonUtil.writeObjectAsString(partnerProfile));
        }
    }
    
    /**
     * @Description: 将partner变失效
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     */
    @Test
    public void testDeactivePartner()
    {
        long partnerId = 100000022000L;
        
        logger.info("in:"+ "partnerId="+ partnerId);
        
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.deletePartner(partnerId);
    }
    
    /**
     * @Description: 修改partner状态
     * @Description: update partner status
     * @author zhaozx
     */
    @Test
    public void testUpdatePartnerSts()
    {
        long partnerId = 100000022000L;
        int oldSts = 0;
        int newSts = 1;
        logger.info("in:"+ "partnerId="+ partnerId+ "\t"+ "oldSts="+ oldSts+ "\t"+ "newSts="+ newSts);
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        partnerInfoBO.updatePartnerSts(partnerId, oldSts, newSts);
    }
    
    /**
      * @Description: 通过销售员id获得partnerProfile
      * @Description: (English description)
      * @author zhaozx
      * @throws IOException
      */
    @Test
    public void testGetPartnerProfileList() throws IOException{
        long saleId = 111;
        logger.info("in:saleId="+saleId);
        PartnerInfoBO partnerInfoBO = ServiceLocatorFactory.getService(PartnerInfoBO.class);
        List<PartnerProfileVO> partnerProfileVOList=   partnerInfoBO.getPartnerProfileList(saleId);
        logger.info(JsonUtil.writeObjectAsString(partnerProfileVOList));
   
    }
}
