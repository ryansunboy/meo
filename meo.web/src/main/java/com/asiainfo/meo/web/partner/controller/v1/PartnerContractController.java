package com.asiainfo.meo.web.partner.controller.v1;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.constant.PartnerContractErrorConstant;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoQueryConditionVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceInfoVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractPriceVO;
import com.asiainfo.meo.prm.contract.app.model.vo.ContractVO;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.web.component.PartnerComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.partner.model.vo.UIContractInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractPriceInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractPriceVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractProfileVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractQueryVO;
import com.asiainfo.meo.web.partner.model.vo.UISimpleContractVO;

/**
 * @Description: partner控制器
 * @Description: partner controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-18 下午01:23:09
 * @version 1.0
 */
@RestController
@RequestMapping("/partner/contract")
public class PartnerContractController
{
    private static final Log  LOG = LogFactory.getLog(PartnerContractController.class);
    
    @Resource
    private PartnerComponent  partnerComponent;
    
    @Resource
    private PartnerPserviceBO partnerPserviceBO;
    
    /**
     * @Description: (创建合同及合同价格)
     * @Description: (create contract and contract price)
     * @modifyReason:
     * @author lill
     * @param partnerPriceUIVO
     */
    @RequestMapping(params = "method=createContract", method = RequestMethod.POST)
    public Message<Map<String, Object>> createContractPrice(@RequestBody @Valid UIContractPriceVO uIContractPriceVO)
    {
        Long userId = BoContext.getBoContext().getUserId();
        if (ValidateUtil.isNull(uIContractPriceVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.CONTRACT_PRICEVO });
        if (ValidateUtil.isNull(uIContractPriceVO.getContract()))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        if (ValidateUtil.isNull(uIContractPriceVO.getContract().getPartnerId()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        
        ContractPriceVO contractPriceVO = partnerComponent.transformCreateContractPriceVO(uIContractPriceVO, userId);
        if (ValidateUtil.isNull(contractPriceVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.CONTRACT_PRICEVO });
        Contract contract = partnerPserviceBO.savePartnerContract(contractPriceVO);
        if (ValidateUtil.isEmpty(contract))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractNo", contract.getContractNo());
        map.put("contractId", contract.getContractId());
        Message<Map<String, Object>> msg = new Message<Map<String, Object>>();
        msg.setBody(map);
        return msg;
    }
    
    /**
     * @Description: (获取合同及价格信息)
     * @Description: (get contract and price information)
     * @modifyReason:
     * @author lill
     * @return
     * @throws ParseException
     */
    @RequestMapping(params = "method=getContract", method = RequestMethod.POST)
    public Message<List<UIContractPriceVO>> getPartnerContract(@RequestBody(required = false) UIContractQueryVO uIContractQueryVO)
    {
        uIContractQueryVO = uIContractQueryVO== null ? new UIContractQueryVO() : uIContractQueryVO;
        Message<List<UIContractPriceVO>> message = new Message<List<UIContractPriceVO>>();
        List<UIContractPriceVO> contractPriceUIVOList = new ArrayList<UIContractPriceVO>();
        try
        {
            contractPriceUIVOList = partnerComponent.getContract(uIContractQueryVO);
        }
        catch(Exception e)
        {
            LOG.error("getContract"+ e.getMessage());
            throw new MeoException(PartnerContractErrorConstant.GET_FAILED, new Object[]{PrmConstantDefine.CONTRACT });
        }
        message.setBody(contractPriceUIVOList);
        return message;
        
    }
    
    /**
     * @Description: (修改合同及合同价格)
     * @Description: (modify contract and contract price)
     * @modifyReason:
     * @author lill
     * @param partnerPriceUIVO
     * @throws ParseException
     */
    @RequestMapping(params = "method=modifyContract", method = RequestMethod.POST)
    public Message<Map<String, Object>> modifyContractPrice(@RequestBody @Valid UIContractInfoVO uIContractInfoVO)
            throws ParseException
    {
        if (ValidateUtil.isEmpty(uIContractInfoVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.UI_CONTRACT_INFOVO });
        
        UIContractProfileVO contractInfo = uIContractInfoVO.getContractInfo();
        
        if (ValidateUtil.isEmpty(contractInfo))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        
        if (ValidateUtil.isNull(contractInfo.getContractId()))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.CONTRACT_ID });
        
        Long contractId = contractInfo.getContractId();
        Contract contract = partnerComponent.getContract(contractId);
        
        if (ValidateUtil.isEmpty(contract))
            throw new MeoException(PartnerContractErrorConstant.CONTRACT_IS_NOT_FOUND, new Object[]{contractId });
        
        ContractPriceVO contractPriceVO = partnerComponent.transferContractInfoUIVOToContractPrice(contractId, contract,
                uIContractInfoVO);
        
        if (ValidateUtil.isEmpty(contractPriceVO))
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY,
                    new Object[]{PrmConstantDefine.CONTRACT_PRICEVO });
        
        if (contract.getSts()== Contract.STS_INACTIVE)
            partnerComponent.updateContractPriceVO(contractId, contractPriceVO);
        else if (contract.getSts()== Contract.STS_ACTIVE|| contract.getSts()== Contract.STS_EXPIREDACTIVE)
        {
            if (contract.getSts()== Contract.STS_ACTIVE)
                partnerPserviceBO.updateContractActiveToDeactive(contractId);
            contract = partnerPserviceBO.savePartnerContract(contractPriceVO);
        }
        
        if (ValidateUtil.isEmpty(contract))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractId", contract.getContractId());
        map.put("contractNo", contract.getContractNo());
        Message<Map<String, Object>> msg = new Message<Map<String, Object>>();
        msg.setBody(map);
        return msg;
    }
    
    /**
     * @Description: 激活合同
     * @Description: active contract by contractId
     * @modifyReason:
     * @author zhengzy
     * @param contractId
     * @return
     */
    @RequestMapping(params = "method=activateContract", method = RequestMethod.POST)
    public Message<Map<String, Long>> activateContract(@RequestBody Map<String, Long> map)
    {
        Long contractId = map.get("contractId");
        if (ValidateUtil.isNull(contractId))
        {
            LOG.error("active contract by contractId,contractId can not be null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.CONTRACT_ID });
        }
        Contract contract = partnerPserviceBO.getContract(contractId);
        if (ValidateUtil.isEmpty(contract))
        {
            LOG.error("active contract by contractId,entity contract can not be empty");
            throw new MeoException(PartnerContractErrorConstant.ENTITY_IS_EMPTY, new Object[]{PrmConstantDefine.CONTRACT });
        }
        partnerPserviceBO.updateContractActive(contractId);
        Map<String, Long> msgMap = new HashMap<String, Long>();
        msgMap.put("contractId", contractId);
        Message<Map<String, Long>> msg = new Message<Map<String, Long>>();
        msg.setBody(msgMap);
        return msg;
    }
    
    @RequestMapping(params = "method=meo.contract.list", method = RequestMethod.GET)
    public PageInfo<UIContractPriceInfoVO> getContractListById(@RequestParam Long partnerId,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        LOG.debug("get meo.contract.list begin!");
        final ContractPriceInfoQueryConditionVO conditionVO = partnerComponent.
                transformContractCriteriaToQueryConditionVO(partnerId, pageNo, pageSize);
        PageInfo<ContractPriceInfoVO> pageInfo = partnerPserviceBO.getContractListById(conditionVO);
        PageInfo<UIContractPriceInfoVO> uiPageInfo = partnerComponent
                .transferContractPriceInfoUIContractPriceInfoVO(pageInfo);
        LOG.debug("get meo.contract.list end!");
        return uiPageInfo;
    }
    
    /**
     * @Description: 通過partnerId查询合同集合
     * @Description: query contract List by partnerId
     * @modifyReason:
     * @author zhengzy
     * @param partnerId
     * @return
     */
    @RequestMapping(params = "method=meo.contract.partner.list", method = {RequestMethod.GET, RequestMethod.POST })
    public List<UISimpleContractVO> getSimpleContractListByPartnerId(@RequestParam(required = true) Long partnerId,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        if (ValidateUtil.isNull(partnerId))
        {
            LOG.debug("the parameter partnerId is null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        final ContractPriceInfoQueryConditionVO conditionVO = partnerComponent.
                transformContractCriteriaToQueryConditionVO(partnerId, pageNo, pageSize);
        List<ContractVO> voList = partnerPserviceBO.getContractListByPartnerId(conditionVO);
        List<UISimpleContractVO> simpleContractList = partnerComponent.transferContractVOToUISimipleContractVO(voList);
        return simpleContractList;
    }
    
    @RequestMapping(params = "method=meo.contract.get", method = {RequestMethod.GET })
    public UIContractPriceInfoVO getContract(@RequestParam Long contractId)
    {
        LOG.debug("meo.contract.get begin!");
        ContractPriceInfoVO contractPriceInfoVO = partnerPserviceBO.getContractInfoByContractId(contractId);
        UIContractPriceInfoVO uiContractPriceInfoVO = partnerComponent
                .transferContractPriceInfoToUIContractPriceInfoVO(contractPriceInfoVO);
        LOG.debug("meo.contract.get end!");
        return uiContractPriceInfoVO;
    }
}
