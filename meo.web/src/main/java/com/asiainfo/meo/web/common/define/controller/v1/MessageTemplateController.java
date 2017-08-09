package com.asiainfo.meo.web.common.define.controller.v1;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;
import com.asiainfo.meo.system.template.app.model.vo.TemplateParamDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UIMessageTemplateDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UITemplateParamDefVO;
import com.asiainfo.meo.web.component.NotificationsComponent;

@RestController
@RequestMapping("/system/template")
public class MessageTemplateController
{
    
    @Resource
    private SystemPserviceBO systemPserviceBO;
    
    private static final Log LOG = LogFactory.getLog(MessageTemplateController.class);
    
    @RequestMapping(params = "method=meo.template.create", method = {RequestMethod.POST })
    public void createMessageTemplate(@RequestBody @Validated(value = Insert.class) UIMessageTemplateDefVO uiMessageTemplateDefVO)
    {
        LOG.debug("meo.template.create begin!");
        Long userId = BoContext.getBoContext().getUserId();
        uiMessageTemplateDefVO.setOperatorId(userId);
        MessageTemplateDefVO messageTemplateDefVO = NotificationsComponent.transformUIMessageTemplateDefVOToMessageTemplateDefVO(uiMessageTemplateDefVO);
        systemPserviceBO.createSysMessageTemplateDef(messageTemplateDefVO);
        LOG.debug("meo.template.create end!");
    }
    
    @RequestMapping(params = "method=meo.template.modify", method = {RequestMethod.POST })
    public void modifyMessageTemplate(@RequestBody @Validated(value = Update.class) UIMessageTemplateDefVO uiMessageTemplateDefVO)
    {
        LOG.debug("meo.template.modify begin!");
        MessageTemplateDefVO messageTemplateDefVO = NotificationsComponent.transformUIMessageTemplateDefVOToMessageTemplateDefVO(uiMessageTemplateDefVO);
        systemPserviceBO.modifySysMessageTemplateDef(messageTemplateDefVO);
        LOG.debug("meo.template.modify end!");
    }
    
    @RequestMapping(params = "method=meo.template.delete")
    public void deleteMessageTemplate(@RequestParam Long templateId)
    {
        LOG.debug("meo.template.delete begin!");
        systemPserviceBO.deleteSysMessageTemplateDef(templateId);
        LOG.debug("meo.template.delete end!");
    }
    
    @RequestMapping(params = "method=meo.template.list")
    public PageInfo<UIMessageTemplateDefVO> ListMessageTemplate(
            @RequestParam(required = false) Integer pageNo, 
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long templateId,
            @RequestParam(required = false) String templateName)
    {
        LOG.debug("meo.template.list begin!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<MessageTemplateDefVO> pageInfo = systemPserviceBO.getMessageTemplateList(pageNo, pageSize, templateId, templateName);
        PageInfo<UIMessageTemplateDefVO> page = NotificationsComponent.transformPageMessageTemplateDefVOToPageUIMessageTemplateDefVO(pageInfo);
        LOG.debug("meo.template.list end!");
        return page;
    }
    
    @RequestMapping(params = "method=meo.template.get")
    public UIMessageTemplateDefVO getMessageTemplate(@RequestParam Long templateId)
    {
        LOG.debug("meo.template.get begin!");
        MessageTemplateDefVO messageTemplateDefVO = systemPserviceBO.getSysMessageTemplateDefByTemplateId(templateId);
        UIMessageTemplateDefVO uivo = NotificationsComponent.transformMessageTemplateDefVOToUIMessageTemplateDefVO(messageTemplateDefVO);
        LOG.debug("meo.template.get end!");
        return uivo;
    }
    
    @RequestMapping(value = "/parameter", params = "method=meo.parameter.template.create", method = {RequestMethod.POST })
    public void createMessageTemplateParam(@RequestBody @Validated(value = Insert.class) UITemplateParamDefVO uiTemplateParamDefVO)
    {
        LOG.debug("meo.parameter.template.create begin!");
        Long userId = BoContext.getBoContext().getUserId();
        uiTemplateParamDefVO.setOperatorId(userId);
        TemplateParamDefVO templateParamDefVO = NotificationsComponent.transformUITemplateParamDefVOToTemplateParamDefVO(uiTemplateParamDefVO);
        systemPserviceBO.createSysTemplateParamDefine(templateParamDefVO);
        LOG.debug("meo.parameter.template.create end!");
    }
    
    @RequestMapping(value = "/parameter", params = "method=meo.paramter.template.modify", method = {RequestMethod.POST })
    public void modifyMessageTemplateParam(@RequestBody @Validated(value = Update.class) UITemplateParamDefVO uiTemplateParamDefVO)
    {
        LOG.debug("meo.paramter.template.modify begin!");
        TemplateParamDefVO templateParamDefVO = NotificationsComponent.transformUITemplateParamDefVOToTemplateParamDefVO(uiTemplateParamDefVO);
        systemPserviceBO.modifySysTemplateParamDefine(templateParamDefVO);
        LOG.debug("meo.paramter.template.modify end!");
    }
    
    @RequestMapping(value = "/parameter", params = "method=meo.parameter.template.delete")
    public void deleteMessageTemplateParam(@RequestParam Long paramId)
    {
        LOG.debug("meo.parameter.template.delete begin!");
        systemPserviceBO.deleteSysTemplateParamDefine(paramId);
        LOG.debug("meo.parameter.template.delete end!");
    }
    
    @RequestMapping(value = "/parameter", params = "method=meo.parameter.template.list")
    public PageInfo<UITemplateParamDefVO> ListTemplateParam(
            @RequestParam(required = false) Integer pageNo, 
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long paramId,
            @RequestParam(required = false) String paramName)
    {
        LOG.debug("meo.parameter.template.list begin!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<TemplateParamDefVO> pageInfo = systemPserviceBO.getSysTemplateParamList(pageNo, pageSize, paramId, paramName);
        PageInfo<UITemplateParamDefVO> page = NotificationsComponent.transformPageTemplateParamDefVOToPageUITemplateParamDefVO(pageInfo);
        LOG.debug("meo.parameter.template.list end!");
        return page;
    }
    
    @RequestMapping(value = "/parameter", params = "method=meo.parameter.template.get")
    public UITemplateParamDefVO getTemplateParamDef(@RequestParam Long paramId)
    {
        LOG.debug("meo.parameter.template.get begin!");
        TemplateParamDefVO templateParamDefVO = systemPserviceBO.getTemplateParamDefVOByParamId(paramId);
        UITemplateParamDefVO uivo = NotificationsComponent.transformTemplateParamDefVOVOToUITemplateParamDefVO(templateParamDefVO);
        LOG.debug("meo.parameter.template.get end!");
        return uivo;
    }
}
