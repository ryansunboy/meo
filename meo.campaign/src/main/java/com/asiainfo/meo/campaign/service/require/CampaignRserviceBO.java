package com.asiainfo.meo.campaign.service.require;

import java.util.List;

import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
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


public interface CampaignRserviceBO
{
     public String getCampaignNoByCampaignId(long campaignId);
     
     public String getActionNameByActionId(long actionId);
     
     public String getParamNameByParamId(long paramId);
     
     public List<PartnerInfoVO> getAllPartnerInfo(Long partnerId);
     
     public PartnerInfo getPartnerInfo(Long partnerId);
     
     public PartnerProfileVO getPartnerProfile(Long partnerId);
     
     public PartnerFileInfo getPartnerFileInfo(Long partnerId, int fileType);
     
     public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType);
     
     /**
      * 
       * @Description: 获取动作信息
       * @Description: get the action definition
       * @modifyReason: 
       * @return
      */
     public List<ActionDefine> getTriggerAction(Long actionId);
     
     /**
      * 
       * @Description: 获取货币单位
       * @Description: get the currency unit
       * @modifyReason: 
       * @return
      */
     public List<MeasureDefine> getCurrencyUnit(Long measureId); 
     
     /**
      * 
       * @Description: 获取时间单位
       * @Description: get the time nuit
       * @modifyReason: 
       * @return
      */
     public List<MeasureDefine> getTimeUnit(Long measureId);
     
     /**
      * 
       * @Description: 保存价格
       * @Description: save price
       * @modifyReason: 
       * @author zhengzy
       * @return
      */
     public Price addPrice(Price price);
     
     /**
      * 
       * @Description: 根据priceId查询price
       * @Description: query price by priceId
       * @modifyReason: 
       * @author zhengzy
       * @param priceId
       * @return
      */
     Price getPrice(Long priceId);
     
     /**
      * 
       * @Description: 修改价格
       * @Description: modify price
       * @modifyReason: 
       * @author zhengzy
       * @param price
       * @return
      */
     public Price updatePrice(Price price);
     
     /**
      * 
       * @Description: 删除价格
       * @Description: remove price
       * @modifyReason: 
       * @author zhengzy
       * @param id
      */
     public void deletePrice(Long id);
     
     /**
      * 
       * @Description: 根据partnerId查询一个有效的contract
       * @Description: query valid  contract by partnerId
       * @modifyReason: 
       * @author zhengzy
       * @param partnerId
       * @return
      */
     Contract getContractByPartnerId(Long partnerId);
     
     public SysActionParamDefineVO getParamByParamId(Long paramId);
     
     public Partner getPartnerById(Long partnerId);
     
     public EnumDefine getEnumDefByEnumCode(String enumCode,Integer enumType) ;
     
     /**
      * 
       * @Description: 根据actionId查询actionDefine
       * @Description: query actionDefine by actionId
       * @modifyReason: 
       * @author zhengzy
       * @param actionId
       * @return
      */
     public ActionDefine getActionDefineByActionId(Long actionId);
     
     /**
      * 
       * @Description: 实例化客户参加campaign数据
       * @Description: (English description)
       * @modifyReason: 
       * @author zhengzy
       * @param campaignNo
      */
     public void addEntityParticipant(String campaignNo);
     
     public List<CampaignTypeDefine> getCampaginType(String campaginTypeId);
     
     public List<SysEnumDef> getSysEnumDefListByIds(List<Long> enumIds);

     public List<SysEnumGroupDef> getCampaignEnumGroup(String groupCode);
     
     public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds);
     
     public CmEntityParticipant getEntityParticipantByCamapginNo(String campaignNo);
     
     public List<SysEnumGroupDef> getSysEnumGroupRelListByEnumId(Long enumId);

}
