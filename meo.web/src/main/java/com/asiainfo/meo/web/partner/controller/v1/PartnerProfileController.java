package com.asiainfo.meo.web.partner.controller.v1;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.prm.contract.app.constant.PartnerContractErrorConstant;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.profile.app.constant.PartnerProfileErrorConstant;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.profile.app.model.vo.StaffInfoVO;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.web.component.PartnerComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.partner.model.vo.UIPartnerInfoVO;
import com.asiainfo.meo.web.partner.model.vo.UIStaffInfoVO;

/**
 * @Description: partner控制器
 * @Description: partner controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-18 下午01:23:09
 * @version 1.0
 */
@RestController
@RequestMapping("/partner/profile")
public class PartnerProfileController
{
    private static final Log  LOG = LogFactory.getLog(PartnerProfileController.class);
    
    @Resource
    private PartnerComponent  partnerComponent;
    
    @Resource
    private PartnerPserviceBO partnerPserviceBO;
    
    @Resource
    private SystemPserviceBO  systemPserviceBO;
    
    /**
     * @Description: 修改partner基本信息
     * @Description: modify partner basic information
     * @author zhaozx
     * @param partnerInfo partnerInfo VO entity
     * @throws IOException
     */
    @RequestMapping(params = "method=modifyPartnerInfo", method = RequestMethod.POST)
    public void modifyPartnerBasicInfo(@RequestBody @Validated(value = {Update.class }) UIPartnerInfoVO partnerInfo)
            throws MeoException
    {
        LOG.info("modify  partner basic info begin!");
        LOG.info("modify info"+ JsonUtil.writeObjectAsString(partnerInfo));
        // update partner basic info
        partnerPserviceBO.updatePartnerProfile(partnerComponent.transformPartnerInfoUIVOToPartnerInfoVO(partnerInfo));
        
        LOG.info("modify  partner basic info end!");
        return;
    }
    
    /**
     * @Description: 修改partner联系人信息
     * @Description: modify partner contact information
     * @author zhaozx
     * @param modifyPartnerContactInfoReq
     * @throws IOException
     */
    @RequestMapping(params = "method=modifyPartnerContactInfo", method = RequestMethod.POST)
    public void modifyPartnerContactInfo(@RequestBody UIPartnerInfoVO partnerInfo) throws MeoException
    {
        LOG.debug("modify  PartnerContactInfo begin!");
        LOG.debug("modify partnerInfo="+ JsonUtil.writeObjectAsString(partnerInfo));
        partnerInfo.setPartnerId(BoContext.getBoContext().getUserId());
        
        if (ValidateUtil.isNotEmpty(partnerInfo))
        {
            // update partner contact info
            partnerPserviceBO.updatePartnerContact(partnerComponent.transformPartnerContactInfoToPartnerInfoVO(partnerInfo));
        }
        LOG.info("modify  PartnerContactInfo end!");
        return;
    }
    
    /**
     * @Description: partner注册信息
     * @Description: sign up partner
     * @author zhaozx
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(params = "method=createPartnerInfo", method = RequestMethod.POST)
    public Message<Map<String, Object>> signUpPartner(@RequestBody @Validated(value = {Insert.class }) UIPartnerInfoVO partnerInfo)
            throws ParseException, IOException
    {
        LOG.debug("sign up partner begin!");
        LOG.debug("partnerInfo:"+ JsonUtil.writeObjectAsString(partnerInfo));
        Message<Map<String, Object>> message = new Message<Map<String, Object>>();
        
        long saleId = BoContext.getBoContext().getUserId();// get sale person id
        // save partner profile
        long partnerId = partnerPserviceBO.savePartnerProfile(
                partnerComponent.transformPartnerInfoUIVOToPartnerProfile(partnerInfo), saleId);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("partnerId", partnerId);
        message.setBody(map);
        LOG.debug("sign up partner end!");
        return message;
    }
    
    /**
     * @Description: 获得partner profile，提供给partner自己
     * @Description: get partner profile ,support partner self
     * @author zhaozx
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "method=getPartnerProfile", method = RequestMethod.GET)
    public Message<UIPartnerInfoVO> getPartnerProfile() throws MeoException
    {
        LOG.debug("get partner profile begin!");        
        long partnerId = BoContext.getBoContext().getUserId();
        LOG.debug("partnerId="+ partnerId);
        Message<UIPartnerInfoVO> message = new Message<UIPartnerInfoVO>();
        // get partner profile
        PartnerProfileVO partnerProfile = partnerPserviceBO.getPartnerProfile(partnerId);
        // transform partner profile to partner info
        UIPartnerInfoVO partnerInfoReq = partnerComponent.transformPartnerProfileToPartnerInfoUIVO(partnerProfile);
        message.setBody(partnerInfoReq);
        LOG.debug("partnerProfile="+ JsonUtil.writeObjectAsString(partnerInfoReq));
        LOG.debug("get partner profile end!");
        return message;
    }
    
    /**
     * @Description: 获得partner info,提供给help desk
     * @Description: get partner info,support for desk
     * @author zhaozx
     * @param partnerCondition
     * @param pageSize
     * @param pageNo
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "method=getPartnerInfo", method = RequestMethod.POST)
    public Message<List<UIPartnerInfoVO>> getPartnerInfo(@RequestBody List<UIPartnerInfoVO> partnerCondition,
            @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNo) throws MeoException
    {
        LOG.debug("get partnerInfo begin!");
        LOG.debug("select partnerInfo condition="+ partnerCondition);
        
        Message<List<UIPartnerInfoVO>> message = new Message<List<UIPartnerInfoVO>>();
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        // get partner profile
        List<PartnerProfileVO> partnerProfileList = partnerPserviceBO.getPartnerInfo(
                partnerComponent.transformPartnerInfoConditionToPartnerInfoVO(partnerCondition), pageSize, pageNo);
        // transform partner profile list to partner info list
        List<UIPartnerInfoVO> partnerInfoList = partnerComponent.transformPartnerProfileToPartnerInfoUIVO(partnerProfileList);
        LOG.debug("partnerInfoList="+ JsonUtil.writeObjectAsString(partnerInfoList));
        message.setBody(partnerInfoList);
        LOG.debug("get partnerInfo end!");
        return message;
    }
    
    /**
     * @Description: 获得partner list
     * @Description: get partner list
     * @author zhaozx
     * @param saleId
     * @return List<GetPartnerInfoRes>
     * @throws IOException
     */
    @RequestMapping(params = "method=getPartnerList", method = RequestMethod.GET)
    public PageInfo<UIPartnerInfoVO> getPartnerList(@RequestParam(required = false) Long partnerId,
            @RequestParam(required = false) String companyName, @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo) throws MeoException
    {
        LOG.debug("get partner list begin!");
        PageInfo<UIPartnerInfoVO> pageInfo = new PageInfo<UIPartnerInfoVO>();
        long saleId = BoContext.getBoContext().getUserId();
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        // get partner profile
        PageInfo<PartnerProfileVO> partnerProfilePageInfo = partnerPserviceBO.getPartnerList(saleId, partnerId, companyName, pageSize,
                pageNo);
        // transform partner profile pageInfo to partner pageInfo
        pageInfo = partnerComponent.transformPartnerProfilePageInfoToPartnerPageInfo(partnerProfilePageInfo);
        LOG.debug("get partner list end!");
        return pageInfo;
    }
    
    @RequestMapping(params = "method=getAllPartnerList", method = RequestMethod.GET)
    public PageInfo<UIPartnerInfoVO> getAllPartnerList(@RequestParam(required = false) Long partnerId,
            @RequestParam(required = false) String companyName, @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo) throws MeoException
    {
        LOG.debug("get All partner list begin!");
        PageInfo<UIPartnerInfoVO> pageInfo = new PageInfo<UIPartnerInfoVO>();
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        // get partner profile
        PageInfo<PartnerProfileVO> partnerProfilePageInfo = partnerPserviceBO.getAllPartnerList(partnerId, companyName,pageSize,pageNo);
        // transform partner profile list to partner pageInfo
        pageInfo = partnerComponent.transformPartnerProfilePageInfoToPartnerPageInfo(partnerProfilePageInfo);
        LOG.debug("get partner list end!");
        return pageInfo;
    }
    
    /**
     * @Description: 根据partnerId激活partner
     * @Description: active partner by partnerId
     * @modifyReason:
     * @author zhengzy
     * @param partnerId
     * @return
     */
    @RequestMapping(params = "method=activatePartner", method = RequestMethod.POST)
    public Message<Map<String, Long>> activatePartner(@RequestBody Map<String, Long> map)
    {
        Long partnerId = map.get("partnerId");
        if (ValidateUtil.isNull(partnerId))
        {
            LOG.error("active partner by partnerId,partnerId can not be null");
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{PrmConstantDefine.PARTNER_ID });
        }
        Partner partner = partnerPserviceBO.getPartner(partnerId);
        if (ValidateUtil.isEmpty(partner))
        {
            LOG.error("active partner by partnerId,entity partner is not found");
            throw new MeoException(PartnerContractErrorConstant.PARTNER_IS_NOT_FOUND, new Object[]{partnerId });
        }
        if (partner.getSts()== Partner.STS_ACTIVE)
        {
            LOG.error("partner is already actived!");
            throw new MeoException(PartnerProfileErrorConstant.PARTNER_IS_ALREADY_ACTIVED, new Object[]{partnerId });
        }
        partnerPserviceBO.updatePartnerActive(partnerId);
        systemPserviceBO.activateLoginAcct(partnerId, SysPortalUser.OBJECT_TYPE_PARTNER);
        Map<String, Long> msgMap = new HashMap<String, Long>();
        msgMap.put("partnerId", partnerId);
        Message<Map<String, Long>> msg = new Message<Map<String, Long>>();
        msg.setBody(msgMap);
        return msg;
    }
    
    @RequestMapping(params = "method=meo.staffInfo.update", method = RequestMethod.POST)
    public void updateStaffInfo(@RequestBody UIStaffInfoVO staffCondition) throws MeoException
    {
        LOG.debug("get staffInfo begin!");
        LOG.debug("select staffInfo condition="+ staffCondition);
        long staffId = BoContext.getBoContext().getUserId();
        staffCondition.setStaffId(staffId);
        StaffInfoVO staffInfo = partnerComponent.transformStaffInfoConditionToStaffInfoVO(staffCondition);
        partnerPserviceBO.updateStaff(staffInfo);
        LOG.debug("get partnerInfo end!");
    }
    
}
