package com.asiainfo.meo.product.service.provide;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;
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

public interface ProductPserviceBO 
{
    public List<ProductBasicInfoVO> getProductBasicInfo(ProductBasicInfoVO productBasicInfoVO, Integer pageNo, Integer pageSize);

    public PageInfo<ProductBasicInfoVO> getProductList(ProductBasicInfoQueryConditionVO conditionVO);
    
    public Product saveDraftProduct(ProductVO productVO,List<ProdMnoMappingVO> prodMnoMappings,List<ProdPricePlanVO> prodPricePlanVOs,ProductSpecVO productSpecVO);
    
    public Product publishProduct(ProductVO productVO,List<ProdMnoMappingVO> prodMnoMappings,List<ProdPricePlanVO> prodPricePlanVOs,ProductSpecVO productSpecVO);
    
    public Product getProductById(Long id);
    
    public List<Product> getProduct(Long productId);
    
    public List<ProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(Long productId);
    
    ProductBasicInfoVO getProductBasicInfoById(Long productId);
    
    public ProductSpecInfoVO getProductSpecificationInfo(Long productId);
    
    public List<ProdPriceVO> getProdPriceByProdId(Long productId);

    public ProdMnoMappingVO getProductMappingInfoByProdIdAndMnoId(Long productId, Integer mnoId);
    
    /**
     * 
      * @Description: 在原有product的基础上新增mnoMappings
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param productId
      * @param mnoMappingList
     */
    public void addMnoMappingsToProduct(Long productId,List<ProdMnoMappingVO> mnoMappingList);
    
    public List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId);
    
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize);
    
    public Long createPriceDef(PriceVO priceVO);
    
    public void modifyPriceDef(PriceVO priceVO);
    
    public void deletePriceDefByPriceId(Long priceId);
    
    public PriceVO getPriceDefByPriceId(Long priceId);
    
    public PageInfo<PriceVO> getPriceDefList(Integer pageNo, Integer pageSize, Long priceId, String priceName);

    public Long createDiscountRuleDef(DiscountRuleVO discountRuleVO);
    
    public void modifyDiscountRuleDef(DiscountRuleVO discountRuleVO);
    
    public void deleteDiscountRuleDefByDiscountId(Long discountId);
    
    public DiscountRuleVO getDiscountRuleDefByDiscountId(Long discountId);
    
    public PageInfo<DiscountRuleVO> getDiscountRuleDefList(Integer pageNo, Integer pageSize, Long discountId, String discountName);
    
}
