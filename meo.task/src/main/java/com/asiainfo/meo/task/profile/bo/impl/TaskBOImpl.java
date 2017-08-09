package com.asiainfo.meo.task.profile.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.HotCampaignRankVO;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.task.profile.bo.TaskBO;
import com.asiainfo.meo.task.profile.repository.TaskRepository;
import com.asiainfo.meo.task.profile.service.require.TaskRserviceBO;

public class TaskBOImpl implements TaskBO
{
    private static final Log LOG = LogFactory.getLog(TaskBOImpl.class);
    
    @Resource
    private TaskRepository taskRepository;
    
    @Resource
    private TaskRserviceBO taskRserviceBO;
    
    public void saveHotCampaign()
    {
        LOG.debug("...................saveHotCampaign invoke begin.............");
        Integer configValue = null;
        SysConfigurationDef  sysConfigurationDef  = taskRserviceBO.getConfigDefByConfigValueCode(SysConstantDefine.HOT_CAMPAGIN_CONFIG_CODE);
        if (ValidateUtil.isEmpty(sysConfigurationDef))
        {
            LOG.error("Please configure config_code=["+ SysConstantDefine.HOT_CAMPAGIN_CONFIG_CODE+ "] in table [sys_configuration_def]");
            throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
        }
        configValue = sysConfigurationDef.getConfigValue();
        if (ValidateUtil.isEmpty(configValue))
        {
            LOG.error("Please configure config_code=["+ SysConstantDefine.HOT_CAMPAGIN_CONFIG_CODE+ "] in table [sys_configuration_def]");
            throw new MeoException(SysErrorCodeDefine.HAS_NOT_CONFIG_SYS_CONSTANT);
        }
        List<Object> entityPatcipantList = taskRepository.getEntityPatcipant(sysConfigurationDef.getConfigValue());
        
        for (Object entityPatcipant : entityPatcipantList)
        {
            Object[] object = (Object[])entityPatcipant;
            Long campaginId = Long.valueOf((String)object[0]);
            CampaignBasicVO campaignBasicVO = taskRserviceBO.getCampaignInfoById(campaginId);
            if (ValidateUtil.isEmpty(campaignBasicVO))
            {
                throw new MeoException(CommonErrorConstant.NOT_FOUND, new Object[]{"Campaign Id is " + campaginId});
            }
            HotCampaignRankVO hotCampaignRankVO = taskRserviceBO.get(campaginId);
            HotCampaignRank hotCampaignRank = new HotCampaignRank();
            hotCampaignRank.setCampaignId(campaginId);
            hotCampaignRank.setRefreshDate(DateTimeUtil.getNow());
            hotCampaignRank.setParticipateCount(((BigDecimal)object[1]).intValue());
            BeanUtils.copyProperties(campaignBasicVO, hotCampaignRank);
            hotCampaignRank.setInventory(campaignBasicVO.getParticipant());
            //E表中得到的数据，H表中不存在就insert，存在就update
            if (ValidateUtil.isEmpty(hotCampaignRankVO))
            {
                taskRserviceBO.saveHotCampaignRank(hotCampaignRank);
            }else
            {
                taskRserviceBO.updateHotCampaignRank(hotCampaignRank);
            }
        }
        LOG.debug("...................saveHotCampaign invoke end.............");
    }

    public void updateCampaignSTS()
    {
        LOG.debug("...................updateCampaignSTS invoke begin.............");
        List<Campaign> campaignList = taskRserviceBO.getCampaignList();
        for (Campaign campaign : campaignList)
        {
            if (campaign.getExpiredDate() != null && campaign.getExpiredDate().getTime() < DateTimeUtil.getNow().getTime())
            {
                campaign.setCampaignSts(Campaign.STS_EXPIRED);
                taskRserviceBO.updateCampaign(campaign);
            }
        }
        LOG.debug("...................updateCampaignSTS invoke end.............");
        
    }

    public void updateContractSTS()
    {
        LOG.debug("...................updateContractSTS invoke begin.............");
        List<Contract> contractList =  taskRserviceBO.getContractExpiredList();
        for (Contract contract : contractList)
        {
            contract.setSts(Contract.STS_EXPIREDACTIVE);
            taskRserviceBO.updateContract(contract);
        }
        LOG.debug("...................updateContractSTS invoke end.............");
    }
    
}
