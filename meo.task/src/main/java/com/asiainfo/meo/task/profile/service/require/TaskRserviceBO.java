package com.asiainfo.meo.task.profile.service.require;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
import com.asiainfo.meo.campaign.profile.app.model.vo.HotCampaignRankVO;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.system.common.app.model.entity.SysConfigurationDef;

public interface TaskRserviceBO
{
    public SysConfigurationDef getConfigDefByConfigValueCode(String configValueCode);
    
    public HotCampaignRankVO get(Long campaignId);
    
    public CampaignBasicVO getCampaignInfoById(Long campaignId);
    
    public void saveHotCampaignRank(HotCampaignRank hotCampaignRank);
    
    public void updateHotCampaignRank(HotCampaignRank hotCampaignRank);
    
    public List<Campaign> getCampaignList();

    public Campaign updateCampaign(Campaign campaign);
    
    public List<Contract> getContractExpiredList();
    
    public Contract updateContract(Contract contract);
}
