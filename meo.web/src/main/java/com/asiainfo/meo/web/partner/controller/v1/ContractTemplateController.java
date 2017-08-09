package com.asiainfo.meo.web.partner.controller.v1;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.web.component.FileTemplateComponent;
import com.asiainfo.meo.web.partner.model.vo.UIContractTemplateVO;

/**
 * @Description: contractTemplate控制器
 * @Description: contractTemplate controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhengzy
 * @Date 2015年7月9日
 * @version 1.0
 */

@RestController
@RequestMapping("/contract/template")
public class ContractTemplateController
{
    private static final Log LOG = LogFactory.getLog(ContractTemplateController.class);
    
    @Resource
    SystemPserviceBO         systemPserviceBO;
    
    @Resource
    FileTemplateComponent    fileTemplateComponent;
    
    /**
     * @Description: 添加contract template
     * @Description: add contract template
     * @modifyReason:
     * @author zhengzy
     * @param contractTemplate
     */
    @RequestMapping(params = "method=meo.template.contract.create", method = RequestMethod.POST)
    public Map<String, Long> createContractTemplate(
            @RequestBody @Validated(value = {Insert.class }) UIContractTemplateVO contractTemplate)
    {
        LOG.debug("----------------------meo.template.contract.create--------------begin---------");
        ContractFileTemplateVO contractFileTemplateVO = fileTemplateComponent
                .transformUIContractFileTemplateVOToContractFileTemplateVO(contractTemplate);
        ContractFileTemplateVO contractFileTemplate = systemPserviceBO.createContractFileTemplate(contractFileTemplateVO);
        if (ValidateUtil.isNull(contractFileTemplate))
        {
            LOG.debug("create contractTemplate,contractFileTemplate is null");
            throw new MeoException("create contractTemplate,contractFileTemplate is null");
        }
        if (ValidateUtil.isNull(contractFileTemplate.getTemplateId()))
        {
            LOG.debug("create contractTemplate,contractFileTemplate templateId is null");
            throw new MeoException("create contractTemplate,contractFileTemplate templateId is null");
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("templateId", contractFileTemplate.getTemplateId());
        LOG.debug("----------------------meo.template.contract.create--------------end---------");
        return map;
    }
    
    /**
     * @Description: modify contract template
     * @Description: modify contract template
     * @modifyReason:
     * @author nick
     * @param contractTemplate
     */
    @RequestMapping(params = "method=meo.template.contract.modify", method = RequestMethod.POST)
    public Map<String, Long> modifyContractTemplate(
            @RequestBody @Validated(value = {Update.class }) UIContractTemplateVO contractTemplate)
    {
        LOG.debug("----------------------meo.template.contract.modify--------------begin---------");
        ContractFileTemplateVO contractFileTemplateVO = fileTemplateComponent
                .transformUIContractFileTemplateVOToContractFileTemplateVO(contractTemplate);
        ContractFileTemplateVO contractFileTemplate = systemPserviceBO.modifyContractFileTemplate(contractFileTemplateVO);
        if (ValidateUtil.isNull(contractFileTemplate))
        {
            LOG.debug("modify contractTemplate,contractFileTemplate is null");
            throw new MeoException("modify contractTemplate,contractFileTemplate is null");
        }
        if (ValidateUtil.isNull(contractFileTemplate.getTemplateId()))
        {
            LOG.debug("modify contractTemplate,contractFileTemplate templateId is null");
            throw new MeoException("modify contractTemplate,contractFileTemplate templateId is null");
        }
        final Map<String, Long> map = new HashMap<String, Long>();
        map.put("templateId", contractFileTemplate.getTemplateId());
        LOG.debug("----------------------meo.template.contract.modify--------------end---------");
        return map;
    }
    
    /**
     * @Description: delete contract template
     * @Description: delete contract template
     * @modifyReason:
     * @author nick
     * @param templateId
     */
    @RequestMapping(params = "method=meo.template.contract.delete", method = RequestMethod.GET)
    public void deleteContractTemplate(@RequestParam Long templateId)
    {
        LOG.debug("----------------------meo.template.contract.delete--------------begin---------");
        if (ValidateUtil.isNull(templateId))
        {
            LOG.debug("delete contractTemplate,contractFileTemplate templateId is null");
            throw new MeoException("delete contractTemplate,contractFileTemplate templateId is null");
        }
        systemPserviceBO.deleteContractTemplate(templateId);
        LOG.debug("----------------------meo.template.contract.delete--------------end---------");
    }
    
    /**
     * @Description: 查询contract template list
     * @Description: query contract template list
     * @modifyReason:
     * @author zhengzy
     * @return
     */
    @RequestMapping(params = "method=meo.template.contract.list", method = {RequestMethod.GET })
    public PageInfo<UIContractTemplateVO> getContractTemplateList(
            @RequestParam(required = false) Long templateId,
            @RequestParam(required = false) String templateName,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize)
    {
        LOG.debug("----------------------meo.template.contract.list--------------begin---------");
        final ContractFileTemplateQueryConditionVO conditionVO = fileTemplateComponent.
                transformContractFileTemplateCriteriaToQueryConditionVO(templateId, templateName, pageNo, pageSize);
        PageInfo<ContractFileTemplateVO> pageInfo = systemPserviceBO.
                getContractFileTemplateVOList(conditionVO);
        PageInfo<UIContractTemplateVO> uiPageInfo = fileTemplateComponent
                .transformContractFileTemplatePageInfoToUIContractTemplatePageInfo(pageInfo);
        LOG.debug("----------------------meo.template.contract.list--------------end---------");
        return uiPageInfo;
    }
    
    /**
     * @Description: 根据templateId查询contract template
     * @Description: query contract template by templateId
     * @modifyReason:
     * @author zhengzy
     * @param templateId
     * @return
     */
    @RequestMapping(params = "method=meo.template.contract.get", method = {RequestMethod.GET })
    public UIContractTemplateVO getContractTemplateByTemplateId(@RequestParam Long templateId)
    {
        LOG.debug("----------------------meo.template.contract.get--------------begin---------");
        ContractFileTemplateVO contractFileTemplateVO = systemPserviceBO.getContractFileTemplateVOByTemplateId(templateId);
        UIContractTemplateVO contractTemplateVO = fileTemplateComponent
                .transformContractFileTemplateVOToUIContractTemplateVO(contractFileTemplateVO);
        LOG.debug("----------------------meo.template.contract.get--------------end---------");
        return contractTemplateVO;
    }    
    
}
