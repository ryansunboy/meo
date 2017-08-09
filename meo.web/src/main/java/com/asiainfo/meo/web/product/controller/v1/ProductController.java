package com.asiainfo.meo.web.product.controller.v1;

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

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.product.profile.app.constant.ProductErrorConstant;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.vo.DiscountRuleVO;
import com.asiainfo.meo.product.profile.app.model.vo.PriceVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdPricePlanVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdPriceVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoQueryConditionVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductVO;
import com.asiainfo.meo.product.service.provide.ProductPserviceBO;
import com.asiainfo.meo.web.component.ProductComponent;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.product.model.vo.UIDiscountRuleVO;
import com.asiainfo.meo.web.product.model.vo.UIPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProdMnoMappingInfoVO;
import com.asiainfo.meo.web.product.model.vo.UIProdPricePlanVO;
import com.asiainfo.meo.web.product.model.vo.UIProdPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicInfoVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicListVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicOutputVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicVO;
import com.asiainfo.meo.web.product.model.vo.UIProductCommonVO;
import com.asiainfo.meo.web.product.model.vo.UIProductMappingVO;
import com.asiainfo.meo.web.product.model.vo.UIProductMappingsVO;
import com.asiainfo.meo.web.product.model.vo.UIProductPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProductSpecVO;
import com.asiainfo.meo.web.product.model.vo.UIProductSpecialVO;
import com.asiainfo.meo.web.product.model.vo.UIProductVO;

/**
 * @Description: product 对应 controller
 * @Description: product controller
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhengzy
 * @Date 2015年5月20日 下午3:24:20
 * @version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController
{
    private static final Log  LOG = LogFactory.getLog(ProductController.class);
    
    @Resource
    private ProductPserviceBO productPserviceBO;
    
    @Resource
    private ProductComponent  productComponent;
    
    /**
     * @Description: (获取product的基本信息)
     * @Description: get product basic info
     * @modifyReason:
     * @author zhoujj
     * @param productCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping(params = "method=meo.product.search", method = RequestMethod.POST)
    public List<UIProductBasicOutputVO> getProductBasicInfo(
            @RequestBody(required = false) UIProductBasicInfoVO searchCondition,
            @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNo)
    {
        LOG.debug("get Product Basic Info start!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        ProductBasicInfoVO productCondition = productComponent
                .transformUIProductBasicInfoVOToProductBasicOutputVO(searchCondition);
        List<ProductBasicInfoVO> productBasicInfoVOList = productPserviceBO.getProductBasicInfo(productCondition, pageNo,
                pageSize);
        List<UIProductBasicOutputVO> uiProductBasicOutputVO = productComponent
                .transformProductVOListToUIProductBasicOutputVOList(productBasicInfoVOList);
        LOG.debug(JsonUtil.writeObjectAsString(productBasicInfoVOList));
        LOG.debug("get Product Basic Info end!");
        return uiProductBasicOutputVO;
    }
    
    /**
     * @Description: (获取product的列表)
     * @Description: (get product list)
     * @modifyReason:
     * @author zhoujj
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping(params = "method=meo.product.get", method = RequestMethod.GET)
    public PageInfo<UIProductBasicListVO> getProductList(@RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo)
    {
        LOG.debug("get Product list start!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        final ProductBasicInfoQueryConditionVO conditionVO = productComponent.
                transformProductVOCriteriaToQueryConditionVO(pageNo, pageSize);
        PageInfo<ProductBasicInfoVO> pageInfo = productPserviceBO.getProductList(conditionVO);
        PageInfo<UIProductBasicListVO> uiPageInfo = productComponent
                .transformProductVOListToUIProductBasicListVOList(pageInfo);
        LOG.debug(JsonUtil.writeObjectAsString(uiPageInfo));
        LOG.debug("get Product list end!");
        return uiPageInfo;
    }
    
    /**
     * @Description: 新增或者修改draft状态的product
     * @Description: add or modify the product ,and the product sts is draft
     * @modifyReason:
     * @author zhengzy
     * @param productCommon
     * @return
     */
    @RequestMapping(params = "method=meo.product.save", method = {RequestMethod.POST })
    public Map<String, Long> saveDraftProduct(@RequestBody @Validated(value = Insert.class) UIProductCommonVO productCommon)
    {
        LOG.debug("----------------------meo.product.save--------------start---------");
        UIProductVO uiProduct = productCommon.getProduct();
        UIProductSpecVO uiProductSpec = productCommon.getProductSpec();
        List<UIProductMappingVO> uiProductMappings = productCommon.getProductMappings();
        //prices
        List<UIProdPricePlanVO> uiProductPriceDiscounts = productCommon.getProductPriceDiscounts();
        
        List<ProdMnoMappingVO> prodMnoMappings = productComponent
                .transformUIProductMappingVOListtoProdMnoMappingVOList(uiProductMappings);
        ProductVO productVO = productComponent.transformUIProductVOtoProductVO(uiProduct);
        ProductSpecVO productSpecVO = productComponent.transformUIProductSpecVOtoProductSpecVO(uiProductSpec);
        //transformUIVOtoVO
        List<ProdPricePlanVO> productPriceDiscounts = productComponent.transformUIProductPriceVOListtoProdPricePlanVOList(uiProductPriceDiscounts);
        
        Product product = productPserviceBO.saveDraftProduct(productVO, prodMnoMappings, productPriceDiscounts, productSpecVO);
        if (ValidateUtil.isNull(product))
        {
            throw new MeoException(ProductErrorConstant.ENTITY_IS_EMPTY, new String[]{Product.class.getSimpleName() });
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("productId", product.getProductId());
        LOG.debug("----------------------meo.product.save--------------end---------");
        return map;
    }
    
    /**
     * @Description: 发布product
     * @Description: publish product
     * @modifyReason:
     * @author zhengzy
     * @param productCommon
     * @return
     */
    @RequestMapping(params = "method=meo.product.publish", method = {RequestMethod.POST })
    public Map<String, Long> publishProduct(@RequestBody @Validated(value = Update.class) UIProductCommonVO productCommon)
    {
        LOG.debug("----------------------meo.product.publish--------------start---------");
        
        UIProductVO uiProduct = productCommon.getProduct();
        UIProductSpecVO uiProductSpec = productCommon.getProductSpec();
        List<UIProductMappingVO> uiProductMappings = productCommon.getProductMappings();
        List<UIProdPricePlanVO> uiProductPriceDiscounts = productCommon.getProductPriceDiscounts();
        
        List<ProdMnoMappingVO> prodMnoMappings = productComponent
                .transformUIProductMappingVOListtoProdMnoMappingVOList(uiProductMappings);
        ProductVO productVO = productComponent.transformUIProductVOtoProductVO(uiProduct);
        ProductSpecVO productSpecVO = productComponent.transformUIProductSpecVOtoProductSpecVO(uiProductSpec);
        List<ProdPricePlanVO> prodPricePlanVOs = productComponent.transformUIProductPriceVOListtoProdPricePlanVOList(uiProductPriceDiscounts);
        
        Product product = productPserviceBO.publishProduct(productVO, prodMnoMappings, prodPricePlanVOs, productSpecVO);
        if (ValidateUtil.isNull(product))
        {
            throw new MeoException(ProductErrorConstant.ENTITY_IS_EMPTY, new String[]{Product.class.getSimpleName() });
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("productId", product.getProductId());
        LOG.debug("----------------------meo.product.publish--------------end---------");
        return map;
    }
    
    /**
     * @Description: 获取产品和mmo产品映射关系
     * @Description: Get Product Mapping Info With MNO By Product Id
     * @modifyReason:
     * @author zhoujj
     * @param productId
     * @return
     */
    @RequestMapping(params = "method=meo.product.mapping.get", method = {RequestMethod.GET })
    public List<UIProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(@RequestParam Long productId)
    {
        LOG.debug("----------------------meo.product.mapping.get--------------start---------");
        List<ProdMnoMappingInfoVO> prodMnoMappingInfoVOList = productPserviceBO
                .getProductMappingInfoWithMNOByProductId(productId);
        List<UIProdMnoMappingInfoVO> UIProdMnoMappingInfoVOList = productComponent
                .transformProductMappingVOListtoUIrodMnoMappingVOList(prodMnoMappingInfoVOList);
        LOG.debug("----------------------meo.product.mapping.get--------------end---------");
        return UIProdMnoMappingInfoVOList;
    }
    
    /**
     * @Description: 获取产品基本信息根据id
     * @Description: Get Product Basic Info By Id
     * @modifyReason:
     * @author zhoujj
     * @param productId
     * @return
     */
    @RequestMapping(params = "method=meo.product.basic.get", method = {RequestMethod.GET })
    public UIProductBasicVO getProductBasicInfoById(@RequestParam Long productId)
    {
        LOG.debug("----------------------meo.product.basic.get--------------start---------");
        ProductBasicInfoVO productBasicInfoVO = productPserviceBO.getProductBasicInfoById(productId);
        UIProductBasicVO uiProductBasicVO = productComponent.transformProductBasicInfoVOtoUIProductBasicVO(productBasicInfoVO);
        LOG.debug("----------------------meo.product.basic.get--------------end---------");
        return uiProductBasicVO;
    }
    
    /**
     * 
      * @Description: 根据产品id获取产品的详细信息
      * @Description: get product specification infomation by product Id;
      * @modifyReason: 
      * @author zhoujj
      * @param productId
      * @return
     */
    @RequestMapping(params = "method=meo.product.specification.get", method = RequestMethod.GET)
    public UIProductSpecialVO getProductSpecificationInfo(@RequestParam Long productId)
    {
        LOG.debug("get meo.product.specification start!");
        ProductSpecInfoVO productSpecInfoVO  = productPserviceBO.getProductSpecificationInfo(productId);
        UIProductSpecialVO uiProductSpecialVO = productComponent.transformProductSpecialVOtoUIProductSpecialVO(productSpecInfoVO);
        LOG.debug("get meo.product.specification end!");
        return uiProductSpecialVO;
    }
    
    /**
     * 
      * @Description: 根据id来获取产品的价格
      * @Description: get product price by product Id
      * @modifyReason: 
      * @author zhoujj
      * @param productId
      * @return
     */
    @RequestMapping(params = "method=meo.product.price.get", method = RequestMethod.GET)
    public List<UIProdPriceVO> getProdPriceByProdId(@RequestParam Long productId)
    {
        LOG.debug("get meo.product.price.get start!");
        List<ProdPriceVO> prodPriceVOList = productPserviceBO.getProdPriceByProdId(productId);
        List<UIProdPriceVO> uiProdPriceVOList = productComponent.transformProdPriceVOListtoUIProdPriceVOList(prodPriceVOList);
        LOG.debug("get meo.product.price.get end!");
        return uiProdPriceVOList;
    }
    
    /**
     * 
      * @Description: 在原有的product上新增mappings
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param productMappings
      * @return
     */
    @RequestMapping(params = "method=meo.product.mappings.modify" ,method = RequestMethod.POST)
    public Message<Void> addMnoMappingsToProduct(@RequestBody @Validated(value=Update.class) UIProductMappingsVO prodMappings)
    {
        LOG.debug("----------------meo.product.mappings.modify start---------------------------");
        Long productId = prodMappings.getProductId();        
        List<UIProductMappingVO> mpList = prodMappings.getProductMappings();
        List<ProdMnoMappingVO> mnoMappingList = productComponent.transformUIProductMappingVOListtoProdMnoMappingVOList(mpList);
        productPserviceBO.addMnoMappingsToProduct(productId, mnoMappingList);
        LOG.debug("---------------meo.product.mappings.modify end -----------------------------");
        Message<Void> msg = new Message<Void>();
        return msg;
    }
    
    @RequestMapping(value = "/price", params = "method=meo.price.create", method = {RequestMethod.POST })
    public Map<String, Long> createPdPriceParam(@RequestBody @Validated(value = Insert.class) UIPriceVO uiPriceVO)
    {
        LOG.debug("meo.price.create begin!");
        Map<String, Long> map = new HashMap<String, Long>();
        PriceVO priceVO = productComponent.transformUIPriceVOToPriceVO(uiPriceVO);
        Long notifyId = productPserviceBO.createPriceDef(priceVO);
        map.put("notifyId", notifyId);
        LOG.debug("meo.price.create end!");
        return map;
    }
    
    @RequestMapping(value = "/price", params = "method=meo.price.delete")
    public void deletePdPriceParam(@RequestParam Long priceId)
    {
        LOG.debug("meo.price.delete begin!");
        productPserviceBO.deletePriceDefByPriceId(priceId);
        LOG.debug("meo.price.delete end!");
    }
    
    @RequestMapping(value = "/price", params = "method=meo.price.modify", method = {RequestMethod.POST })
    public void modifyPdPriceParam(@RequestBody @Validated(value = Update.class) UIPriceVO uiPriceVO)
    {
        LOG.debug("meo.price.modify begin!");
        PriceVO priceVO = productComponent.transformUIPriceVOToPriceVO(uiPriceVO);
        productPserviceBO.modifyPriceDef(priceVO);
        LOG.debug("meo.price.modify end!");
    }
    
    @RequestMapping(value = "/price", params = "method=meo.price.list")
    public PageInfo<UIPriceVO> ListPdPriceParam(
            @RequestParam(required = false) Integer pageNo, 
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long priceId,
            @RequestParam(required = false) String priceName)
    {
        LOG.debug("meo.price.list begin!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<PriceVO> pageInfo = productPserviceBO.getPriceDefList(pageNo, pageSize, priceId, priceName);
        PageInfo<UIPriceVO> page = productComponent.transformPagePriceVOToPageUIPriceVO(pageInfo);
        LOG.debug("meo.price.list end!");
        return page;
    }
    
    @RequestMapping(value = "/price", params = "method=meo.price.get")
    public UIPriceVO getPdPrice(@RequestParam Long priceId)
    {
        LOG.debug("meo.price.get begin!");
        PriceVO priceVO = productPserviceBO.getPriceDefByPriceId(priceId);
        UIPriceVO uivo = productComponent.transformPriceVOToUIPriceVO(priceVO);
        LOG.debug("meo.price.get end!");
        return uivo;
    }
    
    @RequestMapping(value = "/discount", params = "method=meo.discount.create", method = {RequestMethod.POST })
    public Map<String, Long> createDiscountRuleParam(@RequestBody @Validated(value = Insert.class) UIDiscountRuleVO uiDiscountRuleVO)
    {
        LOG.debug("meo.discount.create begin!");
        Map<String, Long> map = new HashMap<String, Long>();
        DiscountRuleVO discountRuleVO = productComponent.transformUIDiscountRuleVOToDiscountRuleVO(uiDiscountRuleVO);
        Long discountId = productPserviceBO.createDiscountRuleDef(discountRuleVO);
        map.put("discountId", discountId);
        LOG.debug("meo.discount.create end!");
        return map;
    }
    
    @RequestMapping(value = "/discount", params = "method=meo.discount.delete")
    public void deleteDiscountRuleParam(@RequestParam Long discountId)
    {
        LOG.debug("meo.discount.delete begin!");
        productPserviceBO.deleteDiscountRuleDefByDiscountId(discountId);
        LOG.debug("meo.discount.delete end!");
    }
    
    @RequestMapping(value = "/discount", params = "method=meo.discount.modify", method = {RequestMethod.POST })
    public void modifyDiscountRuleParam(@RequestBody @Validated(value = Update.class) UIDiscountRuleVO uiDiscountRuleVO)
    {
        LOG.debug("meo.discount.modify begin!");
        DiscountRuleVO discountRuleVO = productComponent.transformUIDiscountRuleVOToDiscountRuleVO(uiDiscountRuleVO);
        productPserviceBO.modifyDiscountRuleDef(discountRuleVO);
        LOG.debug("meo.discount.modify end!");
    }
    
    @RequestMapping(value = "/discount", params = "method=meo.discount.list")
    public PageInfo<UIDiscountRuleVO> ListDiscountRuleParam(
            @RequestParam(required = false) Integer pageNo, 
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long discountId,
            @RequestParam(required = false) String discountName)
    {
        LOG.debug("meo.discount.list begin!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<DiscountRuleVO> pageInfo = productPserviceBO.getDiscountRuleDefList(pageNo, pageSize, discountId, discountName);
        PageInfo<UIDiscountRuleVO> page = productComponent.transformDiscountRuleVOToPageDiscountRuleVO(pageInfo);
        LOG.debug("meo.discount.list end!");
        return page;
    }
    
    @RequestMapping(value = "/discount", params = "method=meo.discount.get")
    public UIDiscountRuleVO getDiscountRule(@RequestParam Long discountId)
    {
        LOG.debug("meo.discount.get begin!");
        DiscountRuleVO discountRuleVO = productPserviceBO.getDiscountRuleDefByDiscountId(discountId);
        UIDiscountRuleVO uivo = productComponent.transformDiscountRuleVOToUIDiscountRuleVO(discountRuleVO);
        LOG.debug("meo.discount.get end!");
        return uivo;
    }
    
}
