package com.asiainfo.meo.product.profile.app.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
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
import com.asiainfo.meo.product.profile.app.model.vo.ProdPriceVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoQueryConditionVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductVO;
import com.asiainfo.meo.product.profile.app.repository.ProdMnoMappingRepository;
import com.asiainfo.meo.product.profile.app.repository.ProdPricePlanRepository;
import com.asiainfo.meo.product.profile.app.repository.ProductRepository;
import com.asiainfo.meo.product.profile.app.repository.ProductSpecRepository;
import com.asiainfo.meo.product.service.require.ProductRserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysProvDef;
import com.asiainfo.meo.system.common.app.model.entity.SysRegionDef;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;

public class ProductProfileBOImpl implements ProductProfileBO
{
    @Resource
    private ProductRepository        productRepository;
    
    @Resource
    private ProductRserviceBO        productRserviceBO;
    
    @Resource
    private ProdMnoMappingRepository prodMnoMappingRepository;
    
    @Resource
    private ProdPricePlanRepository  prodPricePlanRepository;
    
    @Resource
    private ProductSpecRepository    productSpecRepository;
    
    public List<ProductBasicInfoVO> getProductBasicInfo(ProductBasicInfoVO productBasicInfoVO, Integer pageNo, Integer pageSize)
    {
        ProductBasicInfoVO productBasicInfo = null;
        List<ProductBasicInfoVO> productBasicInfoVOList = new ArrayList<ProductBasicInfoVO>();
        List<Product> pdProductVOList = productRepository.getPdProduct(productBasicInfoVO, pageNo, pageSize);
        List<ProductSpec> pdProductSpecVOList = productRepository.getPdProductSpec(productBasicInfoVO);
        for (Product pdProduct : pdProductVOList)
        {
            for (ProductSpec pdProductSpec : pdProductSpecVOList)
            {
                if (pdProduct.getProductId()!= pdProductSpec.getProductId())
                {
                    continue;
                }
                productBasicInfo = new ProductBasicInfoVO();
                productBasicInfo.setProductId(pdProduct.getProductId());
                productBasicInfo.setProductName(pdProduct.getProductName());
                productBasicInfo.setProductDescription(pdProduct.getDescription());
                productBasicInfo.setDenomination(pdProduct.getDenomination());
                if (pdProduct.getDenominationUnit()!= null)
                {
                    EnumDefine denominationUnit = productRserviceBO.getEnumDefByEnumCode(
                            String.valueOf(pdProduct.getDenominationUnit()), Integer.valueOf(EnumDefine.DENOMINATION_UNIT));
                    productBasicInfo.setDenominationUnitId(pdProduct.getDenominationUnit());
                    productBasicInfo.setDenominationUnit(denominationUnit!= null ? denominationUnit.getEnumName() : null);
                }
                if (pdProductSpec.getProdCycleType()!= null)
                {
                    EnumDefine productCyclePeriodUnit = productRserviceBO.getEnumDefByEnumCode(
                            String.valueOf(pdProductSpec.getProdCycleType()), Integer.valueOf(EnumDefine.PRODUCT_CYCLE_TYPE));
                    productBasicInfo.setProductCyclePeriod(pdProductSpec.getProdCycleType());
                    productBasicInfo.setProductCyclePeriodUnit(productCyclePeriodUnit!= null ? productCyclePeriodUnit
                            .getEnumName() : null);
                }
                if (pdProduct.getProdSts()!= null)
                {
                    EnumDefine prodSts = productRserviceBO.getEnumDefByEnumCode(String.valueOf(pdProduct.getProdSts()),
                            Integer.valueOf(EnumDefine.PRODUCT_STATUS_TYPE));
                    productBasicInfo.setProductStsId(pdProduct.getProdSts());
                    productBasicInfo.setProductSts(prodSts!= null ? prodSts.getEnumName() : null);
                }
                productBasicInfo.setProductValidDate(pdProduct.getValidDate());
                productBasicInfo.setProductExpiredDate(pdProduct.getExpiredDate());
            }
            if (ValidateUtil.isNotEmpty(productBasicInfo)){
                productBasicInfoVOList.add(productBasicInfo);
                productBasicInfo = null;
            }
        }
        return productBasicInfoVOList;
    }
    
    public PageInfo<ProductBasicInfoVO> getProductList(ProductBasicInfoQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
            
        }
        final PageInfo<ProductBasicInfoVO> pageInfo = new PageInfo<ProductBasicInfoVO>();
        Integer totalSize = productRepository.getProductListTotalSize(conditionVO);
        if (ValidateUtil.isNull(totalSize))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        
        List<ProductBasicInfoVO> productBasicInfoVOList = new ArrayList<ProductBasicInfoVO>();
        List<Product> pdProductVOList = productRepository.getProductList(conditionVO,
                pageSize, pageNo);
        for (Product pdProduct : pdProductVOList)
        {
            ProductBasicInfoVO ProductBasicInfoVO = getProductInfomation(pdProduct);
            if (ValidateUtil.isNotEmpty(ProductBasicInfoVO))
                productBasicInfoVOList.add(ProductBasicInfoVO);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, productBasicInfoVOList);
    }
    
    private ProductBasicInfoVO getProductInfomation(Product pdProduct)
    {
        ProductBasicInfoVO ProductBasicInfoVO = new ProductBasicInfoVO();
        ProductBasicInfoVO.setProductId(pdProduct.getProductId());
        ProductBasicInfoVO.setProductName(pdProduct.getProductName());
        ProductBasicInfoVO.setProductDescription(pdProduct.getDescription());
        ProductBasicInfoVO.setDenomination(pdProduct.getDenomination());
        ProductBasicInfoVO.setImgURL(pdProduct.getImgUrl());
        ProductBasicInfoVO.setProductValidDate(pdProduct.getValidDate());
        ProductBasicInfoVO.setProductExpiredDate(pdProduct.getExpiredDate());
        if (ValidateUtil.isNotEmpty(pdProduct.getCategoryId()))
        {
            List<CategoryDefine> campaignTypeList = productRserviceBO.getCategoryEnum(pdProduct.getCategoryId().longValue(), CategoryDefine.PRODUCT_TYPE);
            for (CategoryDefine campaignType : campaignTypeList)
            {
                ProductBasicInfoVO.setProductCategoryId(pdProduct.getCategoryId());
                ProductBasicInfoVO.setProductCategory(campaignType!= null ? campaignType.getCategoryName() : null);
            }
        }
        if (pdProduct.getDenominationUnit()!= null)
        {
            EnumDefine denominationUnit = productRserviceBO.getEnumDefByEnumCode(
                    String.valueOf(pdProduct.getDenominationUnit()), Integer.valueOf(EnumDefine.DENOMINATION_UNIT));
            ProductBasicInfoVO.setDenominationUnitId(pdProduct.getDenominationUnit());
            ProductBasicInfoVO.setDenominationUnit(denominationUnit!= null ? denominationUnit.getEnumName() : null);
        }
        if (pdProduct.getProdSts()!= null)
        {
            EnumDefine prodSts = productRserviceBO.getEnumDefByEnumCode(String.valueOf(pdProduct.getProdSts()),
                    Integer.valueOf(EnumDefine.PRODUCT_STATUS_TYPE));
            ProductBasicInfoVO.setProductStsId(pdProduct.getProdSts());
            ProductBasicInfoVO.setProductSts(prodSts!= null ? prodSts.getEnumName() : "");
        }
        return ProductBasicInfoVO;
    }
    
    public Product getProductById(Long id)
    {
        return productRepository.getProductById(id);
    }
    
    public Product addProduct(ProductVO productVO,int sts)
    {
        Product product = new Product();
        product.setProductName(productVO.getProductName());
        product.setDescription(productVO.getDescription());
        product.setImgUrl(productVO.getImgUrl());
        product.setCategoryId(productVO.getCategoryId());
        product.setDenomination(productVO.getDenomination());
        product.setDenominationUnit(productVO.getDenominationUnit());
        Long validDate = productVO.getValidDate();
        product.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        Long expiredDate = productVO.getExpiredDate();
        product.setExpiredDate(DateTimeUtil.getEndDayTimestamp(expiredDate));
        product.setProdSts(sts);
        product.setProdStsDate(DateTimeUtil.getNow());
        product.setCreateDate(DateTimeUtil.getNow());
        product.setSts(Product.STS_VALID);
        product.setOperatorId(BoContext.getBoContext().getUserId());
        return productRepository.addProduct(product);
    }
    
    public Product updateProduct(Product product, ProductVO productVO,int sts)
    {
        product.setProductName(productVO.getProductName());
        product.setDescription(productVO.getDescription());
        product.setImgUrl(productVO.getImgUrl());
        product.setCategoryId(productVO.getCategoryId());
        product.setDenomination(productVO.getDenomination());
        product.setDenominationUnit(productVO.getDenominationUnit());
        Long validDate = productVO.getValidDate();
        product.setValidDate(DateTimeUtil.getBeginDayTimestamp(validDate));
        Long expiredDate = productVO.getExpiredDate();
        product.setExpiredDate(DateTimeUtil.getEndDayTimestamp(expiredDate));
        product.setProdSts(sts);
        product.setProdStsDate(DateTimeUtil.getNow());
        product.setModifyDate(DateTimeUtil.getNow());
        product.setSts(Product.STS_VALID);
        product.setOperatorId(BoContext.getBoContext().getUserId());
        return productRepository.updateProduct(product);
    }
    
    public ProdMnoMapping getProdMnoMappingById(Long id)
    {
        return prodMnoMappingRepository.getProdMnoMappingById(id);
    }
    
    public List<ProdMnoMapping> getProdMnoMappingListByProductId(Long productId)
    {
        return prodMnoMappingRepository.getProdMnoMappingListByProductId(productId);
    }
    
    public ProdPricePlan getProdPricePlanById(Long pricePlanId)
    {
        return prodPricePlanRepository.getProdPricePlanById(pricePlanId);
    }
    
    public ProdPricePlan getProdPricePlanByPriceId(Long priceId)
    {
        return prodPricePlanRepository.getProdPricePlanByPriceId(priceId);
    }
    
    public ProductSpec getProductSpecById(Long id)
    {
        return productSpecRepository.getProductSpecById(id);
    }
    
    public ProductSpec addProductSpec(ProductSpecVO productSpecVO,Long productId,int sts)
    {
        ProductSpec productSpec = new ProductSpec();
        productSpec.setValidationPeriod(productSpecVO.getValidationPeriod());
        productSpec.setValidationPeriodUnit(productSpecVO.getValidationPeriodUnitId());
        productSpec.setActivationPeriod(productSpecVO.getActivationPeriod());
        productSpec.setActivationPeriodUnit(productSpecVO.getActivationPeriodUnitId());
        productSpec.setCountryCode(productSpecVO.getCountryCode());
        productSpec.setProvCode(productSpecVO.getProvCode());
        productSpec.setProdCycleType(productSpecVO.getProdCycleTypeId());
        productSpec.setCityCode(productSpecVO.getCityCode());
        productSpec.setSts(sts);
        productSpec.setCreateTime(DateTimeUtil.getNow());
        productSpec.setIp(productSpecVO.getIp());
        productSpec.setPort(productSpecVO.getPort());
        productSpec.setProductId(productId);
        productSpec.setLevelId(0);
        return productSpecRepository.addProductSpec(productSpec);
    }
    
    public ProductSpec updateProductSpec(ProductSpec productSpec, ProductSpecVO productSpecVO,int sts)
    {
        productSpec.setValidationPeriod(productSpecVO.getValidationPeriod());
        productSpec.setValidationPeriodUnit(productSpecVO.getValidationPeriodUnitId());
        productSpec.setActivationPeriod(productSpecVO.getActivationPeriod());
        productSpec.setActivationPeriodUnit(productSpecVO.getActivationPeriodUnitId());
        productSpec.setCountryCode(productSpecVO.getCountryCode());
        productSpec.setProvCode(productSpecVO.getProvCode());
        productSpec.setProdCycleType(productSpecVO.getProdCycleTypeId());
        productSpec.setCityCode(productSpecVO.getCityCode());
        productSpec.setSts(sts);
        productSpec.setModifyDate(DateTimeUtil.getNow());
        productSpec.setIp(productSpecVO.getIp());
        productSpec.setPort(productSpecVO.getPort());
        productSpec.setLevelId(0);
        return productSpecRepository.updateProductSpec(productSpec);
    }

    public ProdMnoMapping addProdMnoMapping(ProdMnoMappingVO prodMnoMappingVO,Long productId)
    {
        ProdMnoMapping mapping = new ProdMnoMapping();
        mapping.setCreateDate(DateTimeUtil.getNow());
        mapping.setMnoId(prodMnoMappingVO.getMnoId());
        Long mnoProdId = prodMnoMappingVO.getMnoProdId();
        mapping.setMnoProdId(mnoProdId);
        mapping.setProductId(productId);
        mapping.setSts(ProdMnoMapping.STS_VALID);
        return prodMnoMappingRepository.addProdMnoMapping(mapping);
    }

    public ProdMnoMapping updateProdMnoMapping(ProdMnoMapping prodMnoMapping, ProdMnoMappingVO prodMnoMappingVO,Long productId)
    {
        prodMnoMapping.setModifyDate(DateTimeUtil.getNow());
        prodMnoMapping.setMnoId(prodMnoMappingVO.getMnoId());
        Long mnoProdId = prodMnoMappingVO.getMnoProdId();
        prodMnoMapping.setMnoProdId(mnoProdId);
        prodMnoMapping.setProductId(productId);
        prodMnoMapping.setSts(ProdMnoMapping.STS_VALID);
        return prodMnoMappingRepository.updateProdMnoMapping(prodMnoMapping);
    }


    public void deleteProdMnoMappingById(Long id)
    {
        ProdMnoMapping pmm = getProdMnoMappingById(id); 
        pmm.setSts(ProdMnoMapping.STS_INVALID);
        prodMnoMappingRepository.updateProdMnoMapping(pmm);
    }
    
    public ProdPricePlan addProdPricePlan(ProdPricePlan prodPricePlan)
    {
        return prodPricePlanRepository.addProdPricePlan(prodPricePlan);
    }

    public ProdPricePlan updateProdPricePlan(ProdPricePlan prodPricePlan)
    {
        return prodPricePlanRepository.updateProdPricePlan(prodPricePlan);
    }
    
    public List<Product> getProduct(Long productId)
    {
        return productRepository.getProduct(productId);
    }

    public List<ProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(Long productId)
    {
        List<ProdMnoMappingInfoVO> prodMnoMappingInfoVOList = new ArrayList<ProdMnoMappingInfoVO>();
        List<ProdMnoMapping> prodMnoMappingList = prodMnoMappingRepository.getProdMnoMappingListByProductId(productId);
        for (ProdMnoMapping prodMnoMapping : prodMnoMappingList)
        {
            ProdMnoMappingInfoVO prodMnoMappingInfoVO = new ProdMnoMappingInfoVO();
            prodMnoMappingInfoVO.setProductId(prodMnoMapping.getProductId());
            prodMnoMappingInfoVO.setProductMappingId(prodMnoMapping.getId());//productMappingId
            prodMnoMappingInfoVO.setMnoProdId(prodMnoMapping.getMnoProdId());
            if (ValidateUtil.isNotEmpty(prodMnoMapping.getMnoId()))
            {
                EnumDefine mnoType = productRserviceBO.getEnumDefByEnumCode(String.valueOf(prodMnoMapping.getMnoId()),
                        Integer.valueOf(EnumDefine.MNO_TYPE));
                prodMnoMappingInfoVO.setMnoId(prodMnoMapping.getMnoId());
                prodMnoMappingInfoVO.setMnoName(mnoType != null ? mnoType.getEnumName() : "");
            }
            prodMnoMappingInfoVOList.add(prodMnoMappingInfoVO);
        }
        return prodMnoMappingInfoVOList;
    }

    public ProductSpecInfoVO getProductSpecificationInfo(Long productId)
    {
        ProductSpecInfoVO productSpecInfoVO= new ProductSpecInfoVO();
        ProductSpec productSpec = productSpecRepository.getProductSpecificationInfo(productId);
        if (ValidateUtil.isEmpty(productSpec))
        {
            return productSpecInfoVO;
        }
        productSpecInfoVO.setProductSpecId(productSpec.getProSpecId());
        productSpecInfoVO.setProductId(productSpec.getProductId());
        productSpecInfoVO.setValidationPeriod(productSpec.getValidationPeriod());
        productSpecInfoVO.setValidationPeriodUnitId(productSpec.getValidationPeriodUnit());
        productSpecInfoVO.setActivationPeriod(productSpec.getActivationPeriod());
        productSpecInfoVO.setActivationPeriodUnitId(productSpec.getActivationPeriodUnit());
        if (productSpec.getCountryCode() != null)
        {
            List<SysCountryDef> countryUnitList = productRserviceBO.getCountryDef(productSpec.getCountryCode().longValue());
            for (SysCountryDef countryDef : countryUnitList)
            {
                productSpecInfoVO.setCountryCode(productSpec.getCountryCode());
                productSpecInfoVO.setCountry(countryDef.getName());
            }
        }
        if (productSpec.getProvCode() != null)
        {
            List<SysProvDef> provinceUnitList = productRserviceBO.getProvDef(productSpec.getProvCode().longValue());
            for (SysProvDef provDef : provinceUnitList)
            {
                productSpecInfoVO.setProvCode(productSpec.getProvCode());
                productSpecInfoVO.setProvince(provDef.getProvName());
            }
        }
        if (productSpec.getProdCycleType() != null)
        {
            EnumDefine prodCycleUnit = productRserviceBO.getEnumDefByEnumCode(
                    String.valueOf(productSpec.getProdCycleType()), Integer.valueOf(EnumDefine.PRODUCT_CYCLE_TYPE));
            productSpecInfoVO.setProdCycleTypeId(productSpec.getProdCycleType());
            productSpecInfoVO.setProdCycleType(prodCycleUnit!= null ? prodCycleUnit.getEnumName() : null);
        }
        if (productSpec.getCityCode() != null)
        {
            List<SysRegionDef> cityUnitList = productRserviceBO.getRegionDef(productSpec.getCityCode().longValue());
            for (SysRegionDef regionDef : cityUnitList)
            {
                productSpecInfoVO.setCityCode(productSpec.getCityCode());
                productSpecInfoVO.setCity(regionDef.getRegionName());
            }
        }
        productSpecInfoVO.setIp(productSpec.getIp());
        productSpecInfoVO.setPort(productSpec.getPort());
        return productSpecInfoVO;
    }

    public List<ProdPriceVO> getProdPricePlanByProdId(Long productId)
    {
        List<ProdPriceVO> prodPricePlanVOList = new ArrayList<ProdPriceVO>();
        List<ProdPricePlan> ProdPricePlanList = prodPricePlanRepository.getProdPricePlanByProdId(productId);
        for (ProdPricePlan prodPricePlan : ProdPricePlanList)
        {
            ProdPriceVO prodPriceVO = new ProdPriceVO();
            prodPriceVO.setProductPriceId(prodPricePlan.getPriceId());
            prodPriceVO.setProductId(prodPricePlan.getProductId());
            if (prodPricePlan.getPriceId() != null)
            {
                Price price = productRserviceBO.getPrice(prodPricePlan.getPriceId());
                if (price != null)
                {
                    if (price.getUnit() != null)
                    {
                        List<MeasureDefine> MeasureDefineList = productRserviceBO.getCurrencyUnit((long)price.getUnit());
                        for (MeasureDefine MeasureDefine : MeasureDefineList)
                        {
                            prodPriceVO.setPriceUnitId(price.getUnit());
                            prodPriceVO.setPriceUnit(MeasureDefine.getName());
                        }
                    }
                    if (price.getPriceType() != null)
                    {
                        EnumDefine priceType = productRserviceBO.getEnumDefByEnumCode(
                                String.valueOf(price.getPriceType()), Integer.valueOf(EnumDefine.PRICE_TYPE));
                        prodPriceVO.setPriceTypeId(price.getPriceType());
                        prodPriceVO.setPriceType(priceType!= null ? priceType.getEnumName() : null);
                    }
                    prodPriceVO.setPrice(price.getUnitPrice());
                }
            }
            prodPricePlanVOList.add(prodPriceVO);
        }
        return prodPricePlanVOList;
    }


    public List<ProdPricePlan> getProdPricePlanListByProductId(Long productId)
    {
        return prodPricePlanRepository.getProdPricePlanListByProductId(productId);
    }

    public void deleteProdPricePlan(Long pricePlanId)
    {
        ProdPricePlan  prodPricePlan =  prodPricePlanRepository.getProdPricePlanById(pricePlanId);
        prodPricePlan.setSts(ProdPricePlan.STS_INVALID);
        prodPricePlan.setModifyDate(DateTimeUtil.getNow());
        prodPricePlanRepository.updateProdPricePlan(prodPricePlan);
    }

    public ProductBasicInfoVO getProductBasicInfoById(Long productId)
    {
        List<Product> ProductList = productRepository.getProduct(productId); 
        ProductBasicInfoVO ProductBasicInfoVO = null;
        for (Product pdProduct : ProductList)
        {
            ProductBasicInfoVO = getProductInfomation(pdProduct);
        }
        return ProductBasicInfoVO;
    }

    @Override
    public ProdMnoMappingVO getProductMappingByProdIdAndMnoId(Long productId, Integer mnoId)
    {
        ProdMnoMapping mapping = prodMnoMappingRepository.getProdMnoMappingByProdIdAndMnoId(productId,mnoId);
        if(ValidateUtil.isNotEmpty(mapping)){
            ProdMnoMappingVO mappingVo = new ProdMnoMappingVO();
            mappingVo.setMnoId(mapping.getMnoId());
            mappingVo.setMnoProdId(mapping.getMnoProdId());
            return mappingVo;
        }
        return null;
    }

    @Override
    public ProdMnoMapping getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(Integer mnoId, Long productId, Long mnoProdId)
    {
        return prodMnoMappingRepository.getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(mnoId, productId, mnoProdId);
    }


    @Override
    public List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId)
    {
        return prodMnoMappingRepository.getProdMnoMappingByMnoId(mnoId);
    }

    @Override
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize)
    {
        return productRepository.getAppProductList(productIds, pageNo, pageSize);
    }

    @Override
    public Long createPriceDef(PriceVO priceVO)
    {
        PdPrice pdPrice = new PdPrice();
        pdPrice.setPriceName(priceVO.getPriceName());
        pdPrice.setPrice(priceVO.getPrice());
        pdPrice.setPriceUnit(priceVO.getPriceUnit());
        pdPrice.setPriceType(priceVO.getPriceType());
        productRepository.createPriceDef(pdPrice);
        return pdPrice.getPriceId();
    }

    @Override
    public void modifyPriceDef(PriceVO priceVO)
    {
        PdPrice pdPrice = productRepository.getPriceDefByPriceId(priceVO.getPriceId());
        if (ValidateUtil.isEmpty(pdPrice))
        {
            throw new MeoException(ProductErrorConstant.PD_PRICE_NOT_EXIST, new Object[]{priceVO.getPriceId()});
        }
        pdPrice.setPriceName(priceVO.getPriceName());
        pdPrice.setPrice(priceVO.getPrice());
        if (priceVO.getPriceUnit() != null)
        {
            pdPrice.setPriceUnit(priceVO.getPriceUnit());
        }
        productRepository.modifyPriceDef(pdPrice);
        
    }

    @Override
    public PriceVO getPriceDefByPriceId(Long priceId)
    {
        PdPrice pdPrice = productRepository.getPriceDefByPriceId(priceId);
        PriceVO priceVO = new PriceVO();
        if (ValidateUtil.isEmpty(pdPrice))
        {
             return priceVO;
        }
        priceVO.setPriceId(pdPrice.getPriceId());
        priceVO.setPriceName(pdPrice.getPriceName());
        priceVO.setPriceUnit(pdPrice.getPriceUnit());
        if (pdPrice.getPriceUnit() != null)
        {
            List<MeasureDefine> measureDefineList = productRserviceBO.getCurrencyUnit((long)pdPrice.getPriceUnit());
            for (MeasureDefine measureDefine : measureDefineList)
            {
                priceVO.setUnit(measureDefine.getName());
            }
        }
        priceVO.setPriceType(pdPrice.getPriceType());
        if (pdPrice.getPriceType() != null)
        {
            EnumDefine enumDefine = productRserviceBO.getEnumDefByEnumCode(String.valueOf(pdPrice.getPriceType()), Integer.valueOf(EnumDefine.PRICE_TYPE));
            if (ValidateUtil.isNotEmpty(enumDefine))
            {
                priceVO.setType(enumDefine.getEnumName());
            }
        }
        priceVO.setPrice(pdPrice.getPrice());
        return priceVO;
    }

    @Override
    public PageInfo<PriceVO> getPriceDefList(Integer pageNo, Integer pageSize, Long priceId, String priceName)
    {
        PageInfo<PriceVO> page = new PageInfo<PriceVO>();
        List<PdPrice> pdPriceList = productRepository.getPriceDefList(pageNo, pageSize, priceId, priceName);
        Integer totalSize = productRepository.getPriceDefTotalSize();
        List<PriceVO> list = new ArrayList<PriceVO>();
        for (PdPrice pdPrice : pdPriceList)
        {
            PriceVO priceVO = new PriceVO();
            priceVO.setPriceId(pdPrice.getPriceId());
            priceVO.setPriceName(pdPrice.getPriceName());
            priceVO.setPriceUnit(pdPrice.getPriceUnit());
            if (pdPrice.getPriceUnit() != null)
            {
                List<MeasureDefine> measureDefineList = productRserviceBO.getCurrencyUnit((long)pdPrice.getPriceUnit());
                for (MeasureDefine measureDefine : measureDefineList)
                {
                    priceVO.setUnit(measureDefine.getName());
                }
            }
            if (pdPrice.getPriceType() != null)
            {
                EnumDefine enumDefine = productRserviceBO.getEnumDefByEnumCode(String.valueOf(pdPrice.getPriceType()), Integer.valueOf(EnumDefine.PRICE_TYPE));
                if (ValidateUtil.isNotEmpty(enumDefine))
                {
                    priceVO.setType(enumDefine.getEnumName());
                }
            }
            priceVO.setPrice(pdPrice.getPrice());
            list.add(priceVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, list);
    }

    @Override
    public void deletePriceDefByPriceId(Long priceId)
    {
        PdPrice pdPrice = productRepository.getPriceDefByPriceId(priceId);
        if (ValidateUtil.isEmpty(pdPrice))
        {
            throw new MeoException(ProductErrorConstant.PD_PRICE_NOT_EXIST, new Object[]{priceId});
        }
        pdPrice.setSts(PdPrice.STS_INVALID);
        productRepository.modifyPriceDef(pdPrice);
    }

    @Override
    public Long createDiscountRuleDef(DiscountRuleVO discountRuleVO)
    {
        DiscountRule discountRule = new DiscountRule();
        discountRule.setDiscountName(discountRuleVO.getDiscountName());
        discountRule.setMinCount(discountRuleVO.getMinCount());
        discountRule.setMaxCount(discountRuleVO.getMaxCount());
        discountRule.setDiscountPrice(discountRuleVO.getDiscountPrice());
        discountRule.setUnit(discountRuleVO.getPriceUnit());
        discountRule.setDiscountType(discountRuleVO.getDiscountTypeId());
        productRepository.createDiscountRuleDef(discountRule);
        return discountRule.getDiscountId();
    }

    @Override
    public void modifyDiscountRuleDef(DiscountRuleVO discountRuleVO)
    {
        DiscountRule discountRule = productRepository.getDiscountRuleDefByDiscountId(discountRuleVO.getDiscountId());
        if (ValidateUtil.isEmpty(discountRule))
        {
            throw new MeoException(ProductErrorConstant.PD_DISCOUNT_RUL_NOT_EXIST, new Object[]{discountRuleVO.getDiscountId()});
        }
        discountRule.setDiscountName(discountRuleVO.getDiscountName());
        discountRule.setMinCount(discountRuleVO.getMinCount());
        discountRule.setMaxCount(discountRuleVO.getMaxCount());
        discountRule.setDiscountPrice(discountRuleVO.getDiscountPrice());
        discountRule.setUnit(discountRuleVO.getPriceUnit());
        discountRule.setDiscountType(discountRuleVO.getDiscountTypeId());
        productRepository.modifyDiscountRuleDef(discountRule);
    }

    @Override
    public void deleteDiscountRuleDefByDiscountId(Long discountId)
    {
        DiscountRule discountRule = productRepository.getDiscountRuleDefByDiscountId(discountId);
        if (ValidateUtil.isEmpty(discountRule))
        {
            throw new MeoException(ProductErrorConstant.PD_DISCOUNT_RUL_NOT_EXIST, new Object[]{discountId});
        }
        discountRule.setSts(DiscountRule.STS_INVALID);
        productRepository.modifyDiscountRuleDef(discountRule);
    }

    @Override
    public DiscountRuleVO getDiscountRuleDefByDiscountId(Long discountId)
    {
        DiscountRule discountRule = productRepository.getDiscountRuleDefByDiscountId(discountId);
        DiscountRuleVO discountRuleVO = new DiscountRuleVO();
        if (ValidateUtil.isEmpty(discountRule))
        {
             return discountRuleVO;
        }
        discountRuleVO.setDiscountId(discountRule.getDiscountId());
        discountRuleVO.setDiscountName(discountRule.getDiscountName());
        discountRuleVO.setMinCount(discountRule.getMinCount());
        discountRuleVO.setMaxCount(discountRule.getMaxCount());
        discountRuleVO.setDiscountPrice(discountRule.getDiscountPrice());
        discountRuleVO.setPriceUnit(discountRule.getUnit());
        discountRuleVO.setDiscountTypeId(discountRule.getDiscountType());
        if (discountRule.getUnit() != null)
        {
            List<MeasureDefine> measureDefineList = productRserviceBO.getCurrencyUnit((long)discountRule.getUnit());
            for (MeasureDefine measureDefine : measureDefineList)
            {
                discountRuleVO.setUnit(measureDefine.getName());
            }
        }
        if (discountRule.getDiscountType() != null)
        {
            EnumDefine enumDefine = productRserviceBO.getEnumDefByEnumCode(
                    String.valueOf(discountRule.getDiscountType()), Integer.valueOf(EnumDefine.DISCOUNT_TYPE));
            if (enumDefine != null)
            {
                discountRuleVO.setDiscountType(enumDefine.getEnumName());
            }
        }
        return discountRuleVO;
    }

    @Override
    public PageInfo<DiscountRuleVO> getDiscountRuleDefList(Integer pageNo, Integer pageSize, Long discountId, String discountName)
    {
        PageInfo<DiscountRuleVO> page = new PageInfo<DiscountRuleVO>();
        List<DiscountRule> pdPriceList = productRepository.getDiscountRuleDefList(pageNo, pageSize, discountId, discountName);
        Integer totalSize = productRepository.getDiscountRuleDefTotalSize();
        List<DiscountRuleVO> list = new ArrayList<DiscountRuleVO>();
        for (DiscountRule discountRule : pdPriceList)
        {
            DiscountRuleVO discountRuleVO = new DiscountRuleVO();
            discountRuleVO.setDiscountId(discountRule.getDiscountId());
            discountRuleVO.setDiscountName(discountRule.getDiscountName());
            discountRuleVO.setMinCount(discountRule.getMinCount());
            discountRuleVO.setMaxCount(discountRule.getMaxCount());
            discountRuleVO.setDiscountPrice(discountRule.getDiscountPrice());
            discountRuleVO.setPriceUnit(discountRule.getUnit());
            discountRuleVO.setDiscountTypeId(discountRule.getDiscountType());
            if (discountRule.getUnit() != null)
            {
                List<MeasureDefine> measureDefineList = productRserviceBO.getCurrencyUnit((long)discountRule.getUnit());
                for (MeasureDefine measureDefine : measureDefineList)
                {
                    discountRuleVO.setUnit(measureDefine.getName());
                }
            }
            if (discountRule.getDiscountType() != null)
            {
                EnumDefine enumDefine = productRserviceBO.getEnumDefByEnumCode(
                        String.valueOf(discountRule.getDiscountType()), Integer.valueOf(EnumDefine.DISCOUNT_TYPE));
                if (enumDefine != null)
                {
                    discountRuleVO.setDiscountType(enumDefine.getEnumName());
                }
            }
            list.add(discountRuleVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, list);
    }
    
    public List<ProdPricePlan> getProdPricePlanByProductId(Long productId)
    {
        return prodPricePlanRepository.getProdPricePlanListByProductId(productId);
    }
    
    public ProdPricePlan getVaildProdPricePlanById(Long pricePlanId)
    {
        return prodPricePlanRepository.getVaildProdPricePlanById(pricePlanId);
    }
    
    public PdPrice getPdPriceDefByPriceId(Long priceId)
    {
        return productRepository.getPriceDefByPriceId(priceId);
    }

    @Override
    public DiscountRule getSysDiscountRuleDefByDiscountId(Long discountId)
    {
        return productRepository.getDiscountRuleDefByDiscountId(discountId);
    }
}
