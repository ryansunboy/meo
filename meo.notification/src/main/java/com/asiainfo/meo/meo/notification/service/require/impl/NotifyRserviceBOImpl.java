package com.asiainfo.meo.meo.notification.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.meo.notification.service.require.NotifyRserviceBO;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;

public class NotifyRserviceBOImpl implements NotifyRserviceBO
{
    @Resource
    SystemPserviceBO sysPserviceBo;

    @Override
    public MessageTemplateDefVO getSysMessageTemplateDefByTemplateId(Long templateId)
    {
        return sysPserviceBo.getSysMessageTemplateDefByTemplateId(templateId);
    }

    @Override
    public List<EnumDefine> getEnumByCode(String enumCode, String enumType)
    {
        return sysPserviceBo.getEnumByCode(enumCode, enumType);
    }

    @Override
    public List<ActionDefine> getActionDefine(Long actionId)
    {
        return sysPserviceBo.getActionDefine(actionId, null);
    }
    
    
}
