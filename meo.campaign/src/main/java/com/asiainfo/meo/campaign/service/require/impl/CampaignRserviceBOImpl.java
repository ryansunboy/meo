package com.asiainfo.meo.campaign.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.campaign.service.provide.CampaignPserviceBO;
import com.asiainfo.meo.campaign.service.require.CampaignRserviceBO;
import com.asiainfo.meo.campaign.task.app.bo.CampaignTaskBO;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.CmEntityParticipant;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.CampaignTypeDefine;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.common.app.model.vo.SysActionParamDefineVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;

public class CampaignRserviceBOImpl implements CampaignRserviceBO
{
    @Resource
    private CampaignPserviceBO campaignPserviceBO;
    
    @Resource
    private SystemPserviceBO   systemPserviceBO;
    
    @Resource
    private PartnerPserviceBO  partnerPserviceBO;
    
    @Resource
    private CampaignTaskBO     campaignTaskBO;
    
    public String getCampaignNoByCampaignId(long campaignId)
    {
        return campaignPserviceBO.getCampaignById(campaignId).getCampaignNo();
    }
    
    public String getActionNameByActionId(long actionId)
    {
        //List<ActionDefine> actionDefineList = systemPserviceBO.getActionDefine(actionId, ActionDefine.USER_TYPE);
        List<ActionDefine> actionDefineList = systemPserviceBO.getActionDefine(actionId, null);
        for (ActionDefine actionDefine : actionDefineList)
        {
            return actionDefine.getActionName();
        }
        return null;
    }
    
    public String getParamNameByParamId(long paramId)
    {
        SysActionParamDefineVO sysActionParamDefineVO = systemPserviceBO.getSysActionParamDefByParamId(paramId);
        return sysActionParamDefineVO== null ? null : sysActionParamDefineVO.getParamName();
    }
    
    public List<PartnerInfoVO> getAllPartnerInfo(Long partnerId)
    {
        return partnerPserviceBO.getAllPartnerInfo(partnerId);
    }
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        return systemPserviceBO.getCategoryEnum(categoryId, categoryType);
    }
    
    @Override
    public List<ActionDefine> getTriggerAction(Long actionId)
    {
        return systemPserviceBO.getActionDefine(actionId, ActionDefine.REWARD_TYPE);
    }
    
    @Override
    public List<MeasureDefine> getCurrencyUnit(Long measureId)
    {
        return systemPserviceBO.getMeasureDef(measureId, MeasureDefine.CURRENCY_UNIT_TYPE);
    }
    
    @Override
    public List<MeasureDefine> getTimeUnit(Long measureId)
    {
        return systemPserviceBO.getMeasureDef(measureId, MeasureDefine.TIME_UNIT_TYPE);
    }
    
    @Override
    public Price addPrice(Price price)
    {
        return partnerPserviceBO.addPrice(price);
    }
    
    @Override
    public Price getPrice(Long priceId)
    {
        return partnerPserviceBO.getPrice(priceId);
    }
    
    @Override
    public Price updatePrice(Price price)
    {
        return partnerPserviceBO.updatePrice(price);
    }
    
    @Override
    public void deletePrice(Long id)
    {
        partnerPserviceBO.deletePrice(id);
    }
    
    @Override
    public Contract getContractByPartnerId(Long partnerId)
    {
        return partnerPserviceBO.getContractByPartnerId(partnerId);
    }
    
    @Override
    public SysActionParamDefineVO getParamByParamId(Long paramId)
    {
        return systemPserviceBO.getSysActionParamDefByParamId(paramId);
    }
    
    @Override
    public Partner getPartnerById(Long partnerId)
    {
        return partnerPserviceBO.getPartner(partnerId);
    }
    
    @Override
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        return systemPserviceBO.getEnumDefByEnumCode(enumCode, enumType);
    }

    @Override
    public ActionDefine getActionDefineByActionId(Long actionId)
    {
        List<ActionDefine> list = systemPserviceBO.getActionDefine(actionId, null);
        return ValidateUtil.isEmpty(list)?null:list.get(0);
    }
  
    @Override
    public void addEntityParticipant(String campaignNo)
    {
        CmEntityParticipant entityParticipant = new CmEntityParticipant();
        entityParticipant.setCreateDate(DateTimeUtil.getNow());
        entityParticipant.setEntityType(CmEntityParticipant.ENTITY_TYPE_CAMPAIGN);
        entityParticipant.setParticipant(0);
        entityParticipant.setEntityId(campaignNo);
        entityParticipant.setSts(CmEntityParticipant.STS_ACTIVE);
        systemPserviceBO.createEntityParticipant(entityParticipant);
    }

    @Override
    public List<CampaignTypeDefine> getCampaginType(String campaginTypeId)
    {
       return systemPserviceBO.getCampaginType(campaginTypeId);        
    }
    
    public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds)
    {
        return systemPserviceBO.getSysEnumDefListByIds(enumIds);
    }

    @Override
    public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode)
    {
        return systemPserviceBO.getCampaignEnumGroup(groupCode);
    }

    @Override
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds)
    {
        return systemPserviceBO.getSysEnumGroupRelListByGroupIds(groupIds);
    }
    
    @Override
    public CmEntityParticipant getEntityParticipantByCamapginNo(String campaignNo)
    {
        return systemPserviceBO.getEntityParticipantByCamapginNo(campaignNo);
    }

    @Override
    public PartnerInfo getPartnerInfo(Long partnerId)
    {
        return partnerPserviceBO.getPartnerInfo(partnerId);
    }

    @Override
    public PartnerProfileVO getPartnerProfile(Long partnerId)
    {
        return partnerPserviceBO.getPartnerProfile(partnerId);
    }

    @Override
    public PartnerFileInfo getPartnerFileInfo(Long partnerId, int fileType)
    {
        return partnerPserviceBO.getPartnerFileInfo(partnerId, fileType);
    }

    @Override
    public List<SysEnumGroupDef> getSysEnumGroupRelListByEnumId(Long enumId)
    {
        return systemPserviceBO.getSysEnumGroupRelListByEnumId(enumId);
    }
}
