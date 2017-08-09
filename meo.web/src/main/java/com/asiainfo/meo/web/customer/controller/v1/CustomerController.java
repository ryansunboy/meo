package com.asiainfo.meo.web.customer.controller.v1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCoinBlanceVO;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCustVO;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.customer.social.app.bo.SocialBO;
import com.asiainfo.meo.customer.social.app.model.entity.SocialInfo;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.web.component.CustomerComponent;
import com.asiainfo.meo.web.customer.model.vo.UIPResourceVO;
import com.asiainfo.meo.web.customer.model.vo.UISocialInfoReqVO;
import com.asiainfo.meo.web.customer.model.vo.UIWishListVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicListVO;

/**
 * @author liuyang
 */
@RestController
@RequestMapping("/customer")
public class CustomerController
{
    private static final Log   LOG = LogFactory.getLog(CustomerController.class);
    
    @Resource
    private CustomerComponent  customerComponent;
    
    @Resource
    private SocialBO           socialBO;
    
    @Resource
    private CustomerPserviceBO customerPserviceBO;
    
    /**
     * @Description: (获取社会关系信息)
     * @Description: (get social information)
     * @modifyReason:
     * @author lill
     * @param custId
     * @param socialInfoReq
     * @return
     */
    @RequestMapping(params = "method=createSocialInfo", method = RequestMethod.POST)
    public Long linkSocialInfo(@RequestParam Long custId, @RequestBody UISocialInfoReqVO socialInfoReq)
    {
        SocialInfo socialInfo = new SocialInfo();
        socialInfo.setSocialNumber(socialInfoReq.getSocialAccount());
        socialInfo.setChannel(socialInfoReq.getSocialType());
        socialInfo.setMsisdn(socialInfoReq.getMobileNo());
        socialInfo.setEmailAddr(socialInfoReq.getEmailAddr());
        socialInfo.setCountryCode(socialInfoReq.getCountryCode());
        socialInfo.setGender(socialInfoReq.getGender());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            socialInfo.setBirthday(sdf.parse(socialInfoReq.getBirthday()));
        }
        catch(ParseException e)
        {
            throw new MeoException("SocialInfoErrorContant.BRITHDAT_ERROR");
        }
        socialInfo.setLanguageCode(socialInfoReq.getLanguageCode());
        socialInfo.setCityCode(socialInfoReq.getCityCode());
        socialInfo.setProvinceCode(socialInfoReq.getProvCode());
        socialInfo.setCustId(custId);
        socialBO.saveSocial(socialInfo);
        return custId;
        
    }
    
    /**
     * @Description: (get wishList information of customer )
     * @Description: (获取wishlist信息)
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     */
    @RequestMapping(params = "method=getWishList", method = RequestMethod.GET)
    public List<UIWishListVO> getWishList(@RequestParam Long custId)
    {
        List<UIWishListVO> wishLists = new ArrayList<UIWishListVO>();
        wishLists.add(null);
        return wishLists;
    }
    
    /**
     * @Description: 根据customer ID来获取资源列表
     * @Description: get the list of resource by customer id
     * @modifyReason:
     * @author zhoujj
     * @param custId
     * @return
     */
    @RequestMapping(value = "/resource", params = "method=meo.resource.get", method = RequestMethod.GET)
    public List<UIPResourceVO> getResourceListbycustid(@RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo)
    {
        LOG.debug("-------------meo.resource.get--------begin-------");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        Long custId = BoContext.getBoContext().getUserId();
        List<PResource> pResource = customerPserviceBO.getResourceListbycustid(custId, pageSize, pageNo);
        List<UIPResourceVO> uiPResourceVOList = customerComponent.transformPResourceListToUIPResourceVOList(pResource);
        LOG.debug("-------------meo.resource.get--------end-------");
        return uiPResourceVOList;
    }
    
    /**
     * 
      * @Description: 根据 custId 、restType、resSts、pageNo、pageSize分页查询resource 信息
      * @Description: query resource pageInfo by custId 、restType、resSts、pageNo、pageSize
      * @modifyReason: 
      * @author zhengzy
      * @param resType
      * @param resSts
      * @param pageSize
      * @param pageNo
      * @return
     */
    @RequestMapping(value = "/resource", params = "method=meo.resource.list", method = RequestMethod.GET)
    public PageInfo<UIPResourceVO> getResourcePageInfoByCustId(@RequestParam(required = false) Integer resType,
            @RequestParam(required = false) Integer resSts, @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo)
    {
        LOG.debug("-------------meo.resource.pageInfo.get--------begin-------");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        Long custId = BoContext.getBoContext().getUserId();
        PageInfo<UIPResourceVO> uiPageInfo = new PageInfo<UIPResourceVO>();
        PageInfo<PResource> pageInfo = customerPserviceBO.getResourcePageInfoBycustId(custId, resType, resSts, pageSize, pageNo);
        uiPageInfo = customerComponent.transformPResourcePageInfoToUIPResourceVOPageInfo(pageInfo);
        LOG.debug("-------------meo.resource.pageInfo.get--------end-------");
        return uiPageInfo;
    }
    
    /**
     * @Description: 获取过去7天每天的coin数
     * @Description: get coin balance
     * @modifyReason:
     * @author zhoujj
     * @return
     */
    @RequestMapping(value = "/asset", params = "method=meo.asset.list", method = RequestMethod.GET)
    public AssetCoinBlanceVO checkCoinBalance()
    {
        LOG.debug("-------------meo.asset.list--------begin-------");
        Long custId = BoContext.getBoContext().getUserId();
        AssetCoinBlanceVO assetCoinBlanceVO = customerPserviceBO.checkCoinBalance(custId);
        LOG.debug("-------------meo.asset.list--------end-------");
        return assetCoinBlanceVO;
    }
    
    @RequestMapping(value = "/asset", params = "method=meo.asset.get", method = RequestMethod.GET)
    public AssetCustVO getAssetBalanceByCustIdAndAssetType(@RequestParam Integer assetType)
    {
        LOG.debug("-------------meo.asset.get--------begin-------");
        Long custId = BoContext.getBoContext().getUserId();
        AssetCustVO assetCustVO = customerPserviceBO.getAssetInfoByCustIdAndAssetType(custId, assetType);
        LOG.debug("-------------meo.asset.get--------end-------");
        return assetCustVO;
    }
    
    @RequestMapping(params = "method=meo.app.product.get", method = RequestMethod.GET)
    public List<UIProductBasicListVO> getAppProductList(@RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo)
    {
        LOG.debug("get APP Product list start!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        Long customerId = BoContext.getBoContext().getUserId();
        List<ProductBasicInfoVO> productBasicInfoVOList = customerPserviceBO.getAppProductList(customerId, pageNo, pageSize);
        List<UIProductBasicListVO> uiProductBasicOutputVO = customerComponent
                .transformProductVOListToUIProductBasicListVOList(productBasicInfoVOList);
        LOG.debug(JsonUtil.writeObjectAsString(productBasicInfoVOList));
        LOG.debug("get APP Product list end!");
        return uiProductBasicOutputVO;
    }
    
    @RequestMapping(params = "method=meo.mmo.modify")
    public void updateMNO(@RequestParam Integer mnoId, @RequestParam String otp)
    {
        LOG.debug("meo.mmo.modify start!");
        Long custId = BoContext.getBoContext().getUserId();
        customerPserviceBO.updateMNO(custId, mnoId, otp);
        LOG.debug("meo.mmo.modify end!");
    }
    
    @RequestMapping(params = "method=meo.bound.mobile.modify")
    public void updateMobileNO(@RequestParam String mobileNo, @RequestParam String otp, @RequestParam Integer countryCode)
    {
        LOG.debug("meo.bound.mobile.modify start!");
        Long custId = BoContext.getBoContext().getUserId();
        customerPserviceBO.updateBoundMobile(custId, mobileNo, otp, countryCode);
        LOG.debug("meo.bound.mobile.modify end!");
    }
}
