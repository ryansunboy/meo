package com.asiainfo.meo.meo.notification.service.require;

import java.util.List;

import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;


public interface NotifyRserviceBO
{
    public MessageTemplateDefVO getSysMessageTemplateDefByTemplateId(Long templateId);
    
    public List<EnumDefine> getEnumByCode(String enumCode, String enumType);
    
    public List<ActionDefine> getActionDefine(Long actionId);
}
