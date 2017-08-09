package com.asiainfo.meo.system.common.app.repository;

import java.util.List;

import com.asiainfo.meo.system.common.app.model.entity.SysFileTemplate;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateQueryConditionVO;

public interface SysFileTemplateRepository
{
   public  SysFileTemplate addSysFileTemplate(SysFileTemplate sysFileTemplate);
   
   public void deleteSysFileTemplateByTemplateId(Long templateId);
   
   public SysFileTemplate updateSysFileTemplate(SysFileTemplate sysFileTemplate);
   
   public List<SysFileTemplate> getSysFileTemplateByTemplateType(ContractFileTemplateQueryConditionVO conditionVO,
           Integer pageSize, Integer pageNo);
   public Integer getSysFileTemplateByTemplateTypeTotalSize(ContractFileTemplateQueryConditionVO conditionVO);
   
   public SysFileTemplate getSysFileTemplateByTemplateId(Long templateId);
}
