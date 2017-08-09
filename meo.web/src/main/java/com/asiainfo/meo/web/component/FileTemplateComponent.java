package com.asiainfo.meo.web.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.common.app.model.entity.SysFileTemplate;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateQueryConditionVO;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateVO;
import com.asiainfo.meo.web.partner.model.vo.UIContractTemplateVO;

public class FileTemplateComponent
{
    private static final Log  LOG = LogFactory.getLog(FileTemplateComponent.class);
    
    public PageInfo<UIContractTemplateVO> transformContractFileTemplatePageInfoToUIContractTemplatePageInfo
        (PageInfo<ContractFileTemplateVO> pageInfo)
    {
        final PageInfo<UIContractTemplateVO> uiPageInfo = new PageInfo<UIContractTemplateVO>();        
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UIContractTemplateVO> list = new ArrayList<UIContractTemplateVO>();
        for (ContractFileTemplateVO fileTemplate : pageInfo.getResult())
        {
            UIContractTemplateVO contractTemplate = new UIContractTemplateVO();
            BeanUtils.copyProperties(fileTemplate, contractTemplate);
            list.add(contractTemplate);
        }
        uiPageInfo.setResult(list);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());
        return uiPageInfo;
    }
    
    public ContractFileTemplateQueryConditionVO transformContractFileTemplateCriteriaToQueryConditionVO(
            Long templateId, String templateName, final Integer pageNo, final Integer pageSize)
    {
        final ContractFileTemplateQueryConditionVO conditionVO = new ContractFileTemplateQueryConditionVO();
        conditionVO.setTemplateId(templateId);
        conditionVO.setTemplateName(templateName);
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        conditionVO.setTemplateType(SysFileTemplate.CONTRACT_TYPE);
        return conditionVO;
    }
    
    public UIContractTemplateVO transformContractFileTemplateVOToUIContractTemplateVO(ContractFileTemplateVO fileTemplate)
    {
        if(ValidateUtil.isNull(fileTemplate))
        {
            LOG.debug("the method transformContractTemplateVOToUIContractTemplateVO,input parameter fileTemplate is null");
            throw new MeoException("the method transformContractTemplateVOToUIContractTemplateVO,input parameter fileTemplate is null");
        }
        UIContractTemplateVO contractTemplate = new UIContractTemplateVO();
        BeanUtils.copyProperties(fileTemplate, contractTemplate);
        return contractTemplate;
    }
    
    public ContractFileTemplateVO transformUIContractFileTemplateVOToContractFileTemplateVO(UIContractTemplateVO contractTemplate)
    {
        if(ValidateUtil.isNull(contractTemplate))
        {
            LOG.debug("the method transformContractFileTemplateVOToContractTemplateVO,input parameter contractTemplate is null");
            throw new MeoException("the method transformContractFileTemplateVOToContractTemplateVO,input parameter contractTemplate is null");
        }
        ContractFileTemplateVO fileTemplate = new ContractFileTemplateVO();
        BeanUtils.copyProperties(contractTemplate, fileTemplate);
        return fileTemplate;
    }
}
