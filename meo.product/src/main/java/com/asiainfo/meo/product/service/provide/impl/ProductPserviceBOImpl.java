package com.asiainfo.meo.product.service.provide.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.product.define.ProductConstantDefine;
import com.asiainfo.meo.product.profile.app.bo.ProductProfileBO;
import com.asiainfo.meo.product.profile.app.constant.ProductErrorConstant;
import com.asiainfo.meo.product.profile.app.model.entity.DiscountRule;
import com.asiainfo.meo.product.profile.app.model.entity.PdPrice;
import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;
import com.asiainfo.meo.product.profile.app.model.entity.ProdPricePlan;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.entity.ProductSpec;
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
import com.asiainfo.meo.product.service.require.ProductRserviceBO;

public class ProductPserviceBOImpl implements ProductPserviceBO
{
    private static final Log  LOG = LogFactory.getLog(ProductPserviceBOImpl.class);
    
    @Resource
    private ProductProfileBO  productProfileBO;
    
    @Resource
    private ProductRserviceBO productRserviceBO;
    
    public List<ProductBasicInfoVO> getProductBasicInfo(ProductBasicInfoVO productBasicInfoVO, Integer pageNo, Integer pageSize)
    {
        return productProfileBO.getProductBasicInfo(productBasicInfoVO, pageNo, pageSize);
    }
    
    public PageInfo<ProductBasicInfoVO> getProductList(final ProductBasicInfoQueryConditionVO conditionVO)
    {
        return productProfileBO.getProductList(conditionVO);
    }
    
    public Product saveDraftProduct(ProductVO productVO, List<ProdMnoMappingVO> prodMnoMappings,
            List<ProdPricePlanVO> productPriceDiscounts, ProductSpecVO productSpecVO)
    {
        throwProductException(productVO);
        Long oldProductId = productVO.getProductId();
        // 如果action为true 表示新增，否则表示修改
        boolean action = oldProductId== null;
        Product product = null;
        List<Long> uiProductMappingIds = new ArrayList<Long>();
        List<Long> oldProductMappingIds = new ArrayList<Long>();
        List<ProdMnoMapping> oldProdMnoMappingList = new ArrayList<ProdMnoMapping>();
        
        ProductSpec oldProductSpec = null;
        //
        throwProductPriceException(productPriceDiscounts, oldProductId);
        throwSaveDraftProductMnoMappingException(prodMnoMappings, action, uiProductMappingIds, oldProductId);
        oldProductSpec = throwSaveDraftProductSpecException(productSpecVO, action, oldProductSpec, oldProductId);
        product = throwSaveDraftProductException(oldProductId, product, productPriceDiscounts);
        
        if (action)
        {
            product = productProfileBO.addProduct(productVO, Product.STS_INACTIVE);
        }
        else
        {
            product = productProfileBO.updateProduct(product, productVO, Product.STS_INACTIVE);
        }
        
        if (ValidateUtil.isNotNull(oldProductSpec))
        {
            oldProductSpec.setProductId(product.getProductId());
            productProfileBO.updateProductSpec(oldProductSpec, productSpecVO, ProductSpec.STS_INVALID);
        } 
        else
        {
            productProfileBO.addProductSpec(productSpecVO, product.getProductId(), ProductSpec.STS_INVALID);
        }
        
        if (!action)//modify
        {
            oldProdMnoMappingList = productProfileBO.getProdMnoMappingListByProductId(oldProductId);
            for (ProdMnoMapping pmm : oldProdMnoMappingList)
            {
                oldProductMappingIds.add(pmm.getId());
               
            }
        }
        handleProductPrices(productPriceDiscounts, product);
        handleProdMnoMappings(prodMnoMappings, product, uiProductMappingIds, oldProductMappingIds, oldProdMnoMappingList);
        return product;
    }
    
    private void throwProductException(ProductVO productVO)
    {
        if (ValidateUtil.isNull(productVO))
        {
            LOG.debug("the input parameter product is empty!");
            throw new MeoException(ProductErrorConstant.ENTITY_IS_EMPTY, new Object[]{ProductConstantDefine.PRODUCT });
        }
        // 判断产品生效日期不能早于结束日期
        if (productVO.getValidDate()> productVO.getExpiredDate())
        {
            LOG.debug("product validdate can not as early as product expiredDate");
            throw new MeoException(ProductErrorConstant.PRODUCT_VALIDDATE_CAN_NOT_LT_PRODUCT_EXPIREDDATE);
        }
        if (productVO.getValidDate()< DateTimeUtil.getNextDayStartTime().getTime())
        {
            LOG.debug("product validdate can not as early as tomorrow");
            throw new MeoException(ProductErrorConstant.PRODUCT_VALIDDATE_CAN_NOT_LT_TOMORROW);
            
        }
        if (productVO.getExpiredDate()< DateTimeUtil.getNextDayStartTime().getTime())
        {
            LOG.debug("product expiredDate can not as early as tomorrow");
            throw new MeoException(ProductErrorConstant.PRODUCT_EXPIREDDATE_CAN_NOT_LT_TOMORROW);
        }
    }
    
    private void handleProdMnoMappings(List<ProdMnoMappingVO> prodMnoMappings, Product product, List<Long> uiProductMappingIds,
            List<Long> oldProductMappingIds, List<ProdMnoMapping> oldProdMnoMappingList)
    {
        if (ValidateUtil.isNotNull(prodMnoMappings))
        {
            for (ProdMnoMappingVO pm : prodMnoMappings)
            {
                Long pmId = pm.getProductMappingId();
                if (ValidateUtil.isNotNull(pmId))
                {
                    for (ProdMnoMapping pmm : oldProdMnoMappingList)
                    {
                        if (pmm.getId()== pmId)
                        {
                            productProfileBO.updateProdMnoMapping(pmm, pm, product.getProductId());
                        }
                    }
                }
                else
                {
                    productProfileBO.addProdMnoMapping(pm, product.getProductId());
                }
            }
            
        }
        
        if (ValidateUtil.isNotNull(uiProductMappingIds))
        {
            for (int i = 0; i< oldProductMappingIds.size(); i++ )
            {
                Long pmid = oldProductMappingIds.get(i);
                if (!uiProductMappingIds.contains(pmid))
                {
                    // 需要被删除
                    productProfileBO.deleteProdMnoMappingById(pmid);
                }
            }
        }
    }
    
    private void handleProductPrices(List<ProdPricePlanVO> prodPricePlanVOs, Product product)
    {
        if (ValidateUtil.isNotEmpty(prodPricePlanVOs))
        {
            List<Long> inputprodPricePlanId = new ArrayList<Long>();
            List<Long> dbprodPricePlanId = new ArrayList<Long>();
            List<ProdPricePlan> prodPricePlanList = productProfileBO.getProdPricePlanByProductId(product.getProductId());
            for (ProdPricePlan prodPricePlan : prodPricePlanList)
            {
                dbprodPricePlanId.add(prodPricePlan.getPricePlanId());
            }
            for (ProdPricePlanVO prodPricePlanVo : prodPricePlanVOs)
            {
                Long pricePlanId = prodPricePlanVo.getPricePlanId();
                if (ValidateUtil.isNotEmpty(pricePlanId))
                {
                    inputprodPricePlanId.add(pricePlanId);
                }
                if (ValidateUtil.isEmpty(pricePlanId))//add
                {
                    ProdPricePlan prodPricePlan = new ProdPricePlan();
                    prodPricePlan.setProductId(product.getProductId());
                    prodPricePlan.setCreateDate(DateTimeUtil.getNow());
                    prodPricePlan.setPriceId(prodPricePlanVo.getPriceId());
                    prodPricePlan.setSts(ProdPricePlan.STS_VALID);
                    prodPricePlan.setOperaterId(BoContext.getBoContext().getUserId());
                    prodPricePlan.setDiscountId(prodPricePlanVo.getDiscountId());
                    productProfileBO.addProdPricePlan(prodPricePlan);
                    
                }//modify
                else
                {
                    ProdPricePlan prodPricePlan = productProfileBO.getVaildProdPricePlanById(pricePlanId);
                    if (ValidateUtil.isEmpty(prodPricePlan))
                    {
                        throw new MeoException(ProductErrorConstant.PRODPRICEPLAN_IS_NOT_FOUND_BY_ID, new Object[]{ pricePlanId});
                    }
                    prodPricePlan.setModifyDate(DateTimeUtil.getNow());
                    prodPricePlan.setSts(ProdPricePlan.STS_VALID);
                    prodPricePlan.setPriceId(prodPricePlanVo.getPriceId());
                    prodPricePlan.setDiscountId(prodPricePlanVo.getDiscountId());
                    prodPricePlan.setOperaterId(BoContext.getBoContext().getUserId());
                    productProfileBO.updateProdPricePlan(prodPricePlan);
                }
            }
            boolean containFlag = dbprodPricePlanId.containsAll(inputprodPricePlanId);
            if (!containFlag)
            {
                throw new MeoException(ProductErrorConstant.THE_PRODUCTID_CORRESPONDING_PRICEPLANID_IS_NOT_FOUND);
            }
            dbprodPricePlanId.removeAll(inputprodPricePlanId);
            for (Long pricePlanId : dbprodPricePlanId)
            {
                productProfileBO.deleteProdPricePlan(pricePlanId);
            }
        }
    }
    
    private Product throwSaveDraftProductException(Long oldProductId, Product product, List<ProdPricePlanVO> productPriceDiscounts)
    {
        if (ValidateUtil.isNotNull(oldProductId))
        {
            product = productProfileBO.getProductById(oldProductId);
            if (ValidateUtil.isNull(product))
            {
                LOG.debug("save draft product,product is not found ,the product id is "+ oldProductId);
                throw new MeoException(ProductErrorConstant.PRODUCT_IS_NOT_FOUND, new Object[]{oldProductId });
            }
        }else
        {
            for (ProdPricePlanVO prodPricePlanVO : productPriceDiscounts) 
            {
                if (ValidateUtil.isNotEmpty(prodPricePlanVO.getPricePlanId()))
                {
                    throw new MeoException(ProductErrorConstant.CREATE_PRODUCT_DO_NOT_NEED_PRICEPLANID, new Object[]{prodPricePlanVO.getPricePlanId() });
                }
            }
        }
        return product;
    }
    
    private ProductSpec throwSaveDraftProductSpecException(ProductSpecVO productSpecVO, boolean action, ProductSpec oldProductSpec, Long oldProductId)
    {
        if (ValidateUtil.isNotNull(productSpecVO))
        {
            Long productSpecId = productSpecVO.getProductSpecId();
            if (ValidateUtil.isNotNull(productSpecId))
            {
                if (action)
                {
                    LOG.debug("save draft product should be no productSpecId");
                    throw new MeoException(ProductErrorConstant.SHOULD_BE_NO_PRODUCTSPECID, new Object[]{"save product" });
                }
                oldProductSpec = productProfileBO.getProductSpecById(productSpecId);
                if (ValidateUtil.isNull(oldProductSpec))
                {
                    LOG.debug("save draft product,productSpec is not found by productSpecId");
                    throw new MeoException(ProductErrorConstant.PRODUCTSPEC_IS_NOT_FOUND_BY_PRODUCTSPECID,
                            new Object[]{productSpecId });
                }
            }else if (ValidateUtil.isNotNull(oldProductId))
            {
                throw new MeoException(ProductErrorConstant.MODIFY_PRODUCT_SPEC_SHOULD_INPUT_PRODUCTSPECID);
            }
        }
        return oldProductSpec;
    }
    
    private void throwSaveDraftProductMnoMappingException(List<ProdMnoMappingVO> prodMnoMappings, boolean action,
            List<Long> uiProductMappingIds, Long productId)
    {
        if (ValidateUtil.isNotEmpty(prodMnoMappings))
        {
            Map<Integer, Long> mnoMapping = new HashMap<Integer, Long>();
            
            for (ProdMnoMappingVO prodMnoMappingVO : prodMnoMappings)
            {
                Integer mnoId = prodMnoMappingVO.getMnoId();
                Long mnoProdId = prodMnoMappingVO.getMnoProdId();
                if (mnoMapping.get(mnoId)== mnoProdId)
                {
                    LOG.debug("the mnoMapping can not be repeat");
                    throw new MeoException(ProductErrorConstant.MNOMAPPING_CAN_NOT_BE_REPEAT);
                }
                mnoMapping.put(mnoId, mnoProdId);
                Long pmId = prodMnoMappingVO.getProductMappingId();
                
                if (ValidateUtil.isNotNull(productId))
                {
                    ProdMnoMapping pmm = productProfileBO.getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(mnoId, productId,
                            mnoProdId);
                    if (ValidateUtil.isNotNull(pmm)&& ValidateUtil.isNull(pmId))
                    {
                        LOG.debug("the mnoMapping is already exists");
                        throw new MeoException(ProductErrorConstant.MNOMAPPING_IS_ALREADY_EXISTS);
                    }
                }
                
                if (ValidateUtil.isNotNull(pmId))
                {
                    if (action)
                    {
                        LOG.debug("save draft product should be no productMappingId");
                        throw new MeoException(ProductErrorConstant.SHOULD_BE_NO_PRODUCTMAPPINGID, new Object[]{"save product" });
                    }
                    ProdMnoMapping tempProdMnoMapping = productProfileBO.getProdMnoMappingById(pmId);
                    if (ValidateUtil.isNull(tempProdMnoMapping))
                    {
                        LOG.debug("save draft product,productMapping is not found by id,the productMappingId is "+ pmId);
                        throw new MeoException(ProductErrorConstant.PRODMNOMAPPING_IS_NOT_FOUND_BY_ID, new Object[]{pmId });
                    }
                    uiProductMappingIds.add(pmId);
                }
            }
        }
    }
    
    // 处理product price中的异常
    private void throwProductPriceException(List<ProdPricePlanVO> productPriceDiscounts, Long oldProductId)
    {
        if (ValidateUtil.isNotEmpty(productPriceDiscounts))
        {
            boolean pricePlanFlag = true;
            List<Integer> priceTypeList = new ArrayList<Integer>();
            for (ProdPricePlanVO prodPricePlanVo : productPriceDiscounts)
            {
                if (ValidateUtil.isNotEmpty(prodPricePlanVo.getPricePlanId()))
                {
                    pricePlanFlag = false;
                }
                Long priceId = prodPricePlanVo.getPriceId();
                Long discountId = prodPricePlanVo.getDiscountId();
                if (ValidateUtil.isNotNull(priceId))
                {
                    PdPrice pdPrice = productProfileBO.getPdPriceDefByPriceId(priceId);
                    if (ValidateUtil.isNull(pdPrice))
                    {
                        throw new MeoException(ProductErrorConstant.PD_PRICE_NOT_EXIST, new Object[]{priceId });
                    }
                    boolean flag = priceTypeList.contains(pdPrice.getPriceType()); 
                    if (flag)
                    {
                        throw new MeoException(ProductErrorConstant.PRICE_TYPE_CAN_NOT_BE_REPEATED);
                    }
                    priceTypeList.add(pdPrice.getPriceType());
                }
                if (ValidateUtil.isNotNull(discountId))
                {
                    DiscountRule discountRule = productProfileBO.getSysDiscountRuleDefByDiscountId(discountId);
                    if (ValidateUtil.isNull(discountRule))
                    {
                        throw new MeoException(ProductErrorConstant.PD_DISCOUNT_RUL_NOT_EXIST, new Object[]{discountId });
                    }
                }
            }
            if (ValidateUtil.isNotEmpty(oldProductId) && pricePlanFlag)
            {
                throw new MeoException(ProductErrorConstant.MODIFY_PRODUCT_PRICE_SHOULD_INPUT_PRICEPLANID);
            }
        }
    }
    
    public Product publishProduct(ProductVO productVO, List<ProdMnoMappingVO> prodMnoMappings,
            List<ProdPricePlanVO> prodPricePlanVOs, ProductSpecVO productSpecVO)
    {
        throwProductException(productVO);
        Long oldProductId = productVO.getProductId();
        // 如果action为true表示直接发布数据，否则为在原来数据基础上做发布
        boolean action = oldProductId== null;
        Product product = null;
        List<Long> uiProductMappingIds = new ArrayList<Long>();
        List<Long> oldProductMappingIds = new ArrayList<Long>();
        List<ProdMnoMapping> oldProdMnoMappingList = new ArrayList<ProdMnoMapping>();
        List<ProdMnoMapping> uiProdMnoMappingList = new ArrayList<ProdMnoMapping>();
        
        ProductSpec oldProductSpec = null;
        
        if (ValidateUtil.isEmpty(prodPricePlanVOs))
        {
            LOG.debug("publish product price can not be null!");
            throw new MeoException(ProductErrorConstant.PUBLISH_PRODUCT_PRICE_CAN_NOT_NULL);
        }
        throwProductPriceException(prodPricePlanVOs, oldProductId);
        throwPublishProductMnoMappings(prodMnoMappings, action, uiProductMappingIds, uiProdMnoMappingList, oldProductId);
        oldProductSpec = throwPublishProductSpecException(productSpecVO, action, oldProductSpec, oldProductId);
        product = throwPublishProductException(oldProductId, product, prodPricePlanVOs);
        
        if (action)
        {
            product = productProfileBO.addProduct(productVO, Product.STS_ACTIVE);
        }
        else
        {
            product = productProfileBO.updateProduct(product, productVO, Product.STS_ACTIVE);
        }
        
        if (ValidateUtil.isNotNull(oldProductSpec))
        {
            oldProductSpec.setProductId(product.getProductId());
            productProfileBO.updateProductSpec(oldProductSpec, productSpecVO, ProductSpec.STS_VALID);
        }
        else
        {
            productProfileBO.addProductSpec(productSpecVO, product.getProductId(), ProductSpec.STS_VALID);
        }
        
        if (!action)
        {
            oldProdMnoMappingList = productProfileBO.getProdMnoMappingListByProductId(oldProductId);
            for (ProdMnoMapping pmm : oldProdMnoMappingList)
            {
                oldProductMappingIds.add(pmm.getId());
            }
        }
        handleProductPrices(prodPricePlanVOs, product);
        handleProdMnoMappings(prodMnoMappings, product, uiProductMappingIds, oldProductMappingIds, oldProdMnoMappingList);
        return product;
    }
    
    private Product throwPublishProductException(Long oldProductId, Product product, List<ProdPricePlanVO> prodPricePlanVOs)
    {
        if (ValidateUtil.isNotNull(oldProductId))
        {
            product = productProfileBO.getProductById(oldProductId);
            if (ValidateUtil.isNull(product))
            {
                LOG.debug("publish product,product is not found ,the product id is "+ oldProductId);
                throw new MeoException(ProductErrorConstant.PRODUCT_IS_NOT_FOUND, new Object[]{oldProductId });
            }
            if (product.getProdSts()!= Product.STS_INACTIVE)
            {
                LOG.debug("publish product,product sts must be inActive!");
                throw new MeoException(ProductErrorConstant.PUBLISH_PRODUCT_PRODSTS_IS_NOT_INACTIVE, new Object[]{oldProductId });
            }
        }else
        {
            for (ProdPricePlanVO prodPricePlanVO : prodPricePlanVOs)
            {
                if (ValidateUtil.isNotEmpty(prodPricePlanVO.getPricePlanId()))
                {
                    throw new MeoException(ProductErrorConstant.CREATE_PRODUCT_DO_NOT_NEED_PRICEPLANID, new Object[]{prodPricePlanVO.getPricePlanId() });
                }
            }
        }
        return product;
    }
    
    private ProductSpec throwPublishProductSpecException(ProductSpecVO productSpecVO, boolean action, ProductSpec oldProductSpec, Long oldProductId)
    {
        if (ValidateUtil.isNull(productSpecVO))
        {
            LOG.debug("publish product productSpec can not be null!");
            throw new MeoException(ProductErrorConstant.PUBLISH_PRODUCT_PRODUCTSPEC_CAN_NOT_NULL);
        }
        Long productSpecId = productSpecVO.getProductSpecId();
        if (ValidateUtil.isNotNull(productSpecId))
        {
            if (action)
            {
                LOG.debug("directly publish product should be no productSpecId");
                throw new MeoException(ProductErrorConstant.SHOULD_BE_NO_PRODUCTSPECID, new Object[]{"directly publish product" });
            }
            oldProductSpec = productProfileBO.getProductSpecById(productSpecId);
            if (ValidateUtil.isNull(oldProductSpec))
            {
                LOG.debug("publish product productSpec is not found by productSpecId");
                throw new MeoException(ProductErrorConstant.PRODUCTSPEC_IS_NOT_FOUND_BY_PRODUCTSPECID,
                        new Object[]{productSpecId });
            }
        }else if (ValidateUtil.isNotNull(oldProductId))
        {
            throw new MeoException(ProductErrorConstant.MODIFY_PRODUCT_SPEC_SHOULD_INPUT_PRODUCTSPECID);
        }
        return oldProductSpec;
    }
    
    private void throwPublishProductMnoMappings(List<ProdMnoMappingVO> prodMnoMappings, boolean action,
            List<Long> uiProductMappingIds, List<ProdMnoMapping> uiProdMnoMappingList, Long productId)
    {
        if (ValidateUtil.isNull(prodMnoMappings))
        {
            LOG.debug("publish product productMapping can not be null!");
            throw new MeoException(ProductErrorConstant.PUBLISH_PRODUCT_PRODUCTMAPPING_CAN_NOT_NULL);
        }
        Map<Integer, Long> mnoMapping = new HashMap<Integer, Long>();
        for (ProdMnoMappingVO prodMnoMappingVO : prodMnoMappings)
        {
            Integer mnoId = prodMnoMappingVO.getMnoId();
            Long mnoProdId = prodMnoMappingVO.getMnoProdId();
            if (mnoMapping.get(mnoId)== mnoProdId)
            {
                LOG.debug("the mnoMapping can not be repeat");
                throw new MeoException(ProductErrorConstant.MNOMAPPING_CAN_NOT_BE_REPEAT);
            }
            mnoMapping.put(mnoId, mnoProdId);
            Long pmId = prodMnoMappingVO.getProductMappingId();
            
            if (ValidateUtil.isNotNull(productId))
            {
                ProdMnoMapping pmm = productProfileBO.getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(mnoId, productId,
                        mnoProdId);
                if (ValidateUtil.isNotNull(pmm)&& ValidateUtil.isNull(pmId))
                {
                    LOG.debug("the mnoMapping is already exists");
                    throw new MeoException(ProductErrorConstant.MNOMAPPING_IS_ALREADY_EXISTS);
                }
            }
            if (ValidateUtil.isNotNull(pmId))
            {
                if (action)
                {
                    LOG.debug("directly publish product should be no productMappingId");
                    throw new MeoException(ProductErrorConstant.SHOULD_BE_NO_PRODUCTMAPPINGID,
                            new Object[]{"directly publish product" });
                }
                ProdMnoMapping tempProdMnoMapping = productProfileBO.getProdMnoMappingById(pmId);
                if (ValidateUtil.isNull(tempProdMnoMapping))
                {
                    LOG.debug("publish product,productMapping is not found by id,the productMappingId is "+ pmId);
                    throw new MeoException(ProductErrorConstant.PRODMNOMAPPING_IS_NOT_FOUND_BY_ID, new Object[]{pmId });
                }
                uiProdMnoMappingList.add(tempProdMnoMapping);
                uiProductMappingIds.add(pmId);
            }
        }
    }
    
    public Product getProductById(Long id)
    {
        return productProfileBO.getProductById(id);
    }
    
    public List<Product> getProduct(Long productId)
    {
        return productProfileBO.getProduct(productId);
    }
    
    public List<ProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(Long productId)
    {
        return productProfileBO.getProductMappingInfoWithMNOByProductId(productId);
    }
    
    public ProductBasicInfoVO getProductBasicInfoById(Long productId)
    {
        return productProfileBO.getProductBasicInfoById(productId);
        
    }
    
    public ProductSpecInfoVO getProductSpecificationInfo(Long productId)
    {
        return productProfileBO.getProductSpecificationInfo(productId);
    }
    
    public List<ProdPriceVO> getProdPriceByProdId(Long productId)
    {
        return productProfileBO.getProdPricePlanByProdId(productId);
    }
    
    @Override
    public ProdMnoMappingVO getProductMappingInfoByProdIdAndMnoId(Long productId, Integer mnoId)
    {
        return productProfileBO.getProductMappingByProdIdAndMnoId(productId, mnoId);
    }
    
    @Override
    public void addMnoMappingsToProduct(Long productId, List<ProdMnoMappingVO> mnoMappingList)
    {
        if (ValidateUtil.isNull(productId))
        {
            LOG.debug("productId can not be null");
            throw new MeoException(ProductErrorConstant.PRODUCT_ID_IS_NULL);
        }
        Product product = productProfileBO.getProductById(productId);
        if (ValidateUtil.isNull(product))
        {
            LOG.debug("product is not found ,productId is "+ productId);
            throw new MeoException(ProductErrorConstant.PRODUCT_IS_NOT_FOUND, new Object[]{productId });
        }
        //if (product.getSts()!= Product.STS_ACTIVE)
        //{
        //    LOG.debug("product sts is not active,productId is "+ productId);
        //    throw new MeoException(ProductErrorConstant.PRODUCT_STS_UNACTIVE, new Object[]{productId });
        //}
        if (ValidateUtil.isEmpty(mnoMappingList))
        {
            LOG.debug("mnoMappings is can not be null");
            throw new MeoException(ProductErrorConstant.MNOMAPPINGS_IS_NULL);
        }
        
        Map<Integer, Long> mnoMapping = new HashMap<Integer, Long>();
        
        for (ProdMnoMappingVO prodMnoMappingVO : mnoMappingList)
        {
            Long pmId = prodMnoMappingVO.getProductMappingId();
            if (ValidateUtil.isNotNull(pmId))
            {
                LOG.debug("add mnoMappings to product should be no productMappingId");
                throw new MeoException(ProductErrorConstant.SHOULD_BE_NO_PRODUCTMAPPINGID,
                        new Object[]{"add mnoMappings to product" });
            }
            Integer mnoId = prodMnoMappingVO.getMnoId();
            Long mnoProdId = prodMnoMappingVO.getMnoProdId();
            if (mnoMapping.get(mnoId)== mnoProdId)
            {
                LOG.debug("the mnoMapping can not be repeat");
                throw new MeoException(ProductErrorConstant.MNOMAPPING_CAN_NOT_BE_REPEAT);
            }
            mnoMapping.put(mnoId, mnoProdId);
            
            ProdMnoMapping pmm = productProfileBO.getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(mnoId, productId, mnoProdId);
            if (ValidateUtil.isNotNull(pmm))
            {
                LOG.debug("the mnoMapping is already exists");
                throw new MeoException(ProductErrorConstant.MNOMAPPING_IS_ALREADY_EXISTS);
            }
        }
        
        for (ProdMnoMappingVO pm : mnoMappingList)
        {
            productProfileBO.addProdMnoMapping(pm, productId);
        }
    }

    @Override
    public List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId)
    {
        return productProfileBO.getProdMnoMappingByMnoId(mnoId);
    }

    @Override
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize)
    {
        return productProfileBO.getAppProductList(productIds, pageNo, pageSize);
    }

    @Override
    public Long createPriceDef(PriceVO priceVO)
    {
        return productProfileBO.createPriceDef(priceVO);
    }

    @Override
    public void modifyPriceDef(PriceVO priceVO)
    {
        productProfileBO.modifyPriceDef(priceVO);
    }

    @Override
    public void deletePriceDefByPriceId(Long priceId)
    {
        productProfileBO.deletePriceDefByPriceId(priceId);
    }

    @Override
    public PriceVO getPriceDefByPriceId(Long priceId)
    {
        return productProfileBO.getPriceDefByPriceId(priceId);
    }

    @Override
    public PageInfo<PriceVO> getPriceDefList(Integer pageNo, Integer pageSize, Long priceId, String priceName)
    {
        return productProfileBO.getPriceDefList(pageNo, pageSize, priceId, priceName);
    }

    @Override
    public Long createDiscountRuleDef(DiscountRuleVO discountRuleVO)
    {
        return productProfileBO.createDiscountRuleDef(discountRuleVO);
    }

    @Override
    public void modifyDiscountRuleDef(DiscountRuleVO discountRuleVO)
    {
        productProfileBO.modifyDiscountRuleDef(discountRuleVO);        
    }

    @Override
    public void deleteDiscountRuleDefByDiscountId(Long discountId)
    {
        productProfileBO.deleteDiscountRuleDefByDiscountId(discountId);        
    }

    @Override
    public DiscountRuleVO getDiscountRuleDefByDiscountId(Long discountId)
    {
        return productProfileBO.getDiscountRuleDefByDiscountId(discountId);
    }

    @Override
    public PageInfo<DiscountRuleVO> getDiscountRuleDefList(Integer pageNo, Integer pageSize, Long discountId, String discountName)
    {
        return productProfileBO.getDiscountRuleDefList(pageNo, pageSize, discountId, discountName);
    }

    
}
