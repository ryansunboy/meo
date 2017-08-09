package com.asiainfo.meo.task.profile.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.HotCampaignRankVO;
import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.task.profile.service.require.TaskRserviceBO;

public class TaskRserviceBOImpl implements TaskRserviceBO
{
    @Resource
    private SystemPserviceBO systemPserviceBO;
    
    @Resource
    private PartnerPserviceBO partnerPserviceBO;
    
    @Resource
    private CampaignPserviceBO campaignPserviceBO;
    
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode)
    {
        return systemPserviceBO.getConfigDefByConfigValueCode(configValueCode);
    }

    public HotCampaignRankVO get(Long campaignId)
    {
        return campaignPserviceBO.get(campaignId);
    }
    
    public CampaignBasicVO getCampaignInfoById(Long campaignId)
    {
        return campaignPserviceBO.getCampaignInfoById(campaignId);
    }

    public void saveHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        campaignPserviceBO.saveHotCampaignRank(hotCampaignRank);
    }

    public void updateHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        campaignPserviceBO.updateHotCampaignRank(hotCampaignRank);
    }

    public List<Campaign> getCampaignList()
    {
        return campaignPserviceBO.getCampaignList();
    }

    public Campaign updateCampaign(Campaign campaign)
    {
        return campaignPserviceBO.updateCampaign(campaign);
    }

    public List<Contract> getContractExpiredList()
    {
        return partnerPserviceBO.getContractExpiredList();
    }

    public Contract updateContract(Contract contract)
    {
        return partnerPserviceBO.updateContract(contract);
    }
}
