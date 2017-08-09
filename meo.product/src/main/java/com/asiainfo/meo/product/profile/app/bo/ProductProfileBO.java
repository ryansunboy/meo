package com.asiainfo.meo.product.profile.app.bo;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
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

public interface ProductProfileBO
{
    public List<ProductBasicInfoVO> getProductBasicInfo(ProductBasicInfoVO productBasicInfoVO, Integer pageNo, Integer pageSize);

    public PageInfo<ProductBasicInfoVO> getProductList(ProductBasicInfoQueryConditionVO conditionVO);
    
    public Product getProductById(Long id);
    
    public Product addProduct(ProductVO productVO,int sts);
    
    public Product updateProduct(Product product,ProductVO productVO,int sts);
    
    ProdMnoMapping getProdMnoMappingById(Long id);
    
    List<ProdMnoMapping> getProdMnoMappingListByProductId(Long productId);
    
    public ProdPricePlan getProdPricePlanById(Long pricePlanId);
    
    public ProdPricePlan getProdPricePlanByPriceId(Long priceId);
    
    ProductSpec getProductSpecById(Long id);
    
    ProductSpec addProductSpec(ProductSpecVO productSpecVO,Long productId,int sts);
    
    ProductSpec updateProductSpec(ProductSpec productSpec,ProductSpecVO productSpecVO,int sts);
    
    ProdMnoMapping addProdMnoMapping(ProdMnoMappingVO prodMnoMappingVO,Long productId);
    
    ProdMnoMapping getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(Integer mnoId,Long productId,Long mnoProdId);
    
    ProdMnoMapping updateProdMnoMapping(ProdMnoMapping prodMnoMapping,ProdMnoMappingVO prodMnoMappingVO,Long productId);
    
    void deleteProdMnoMappingById(Long id);
    
    ProdPricePlan addProdPricePlan(ProdPricePlan prodPricePlan);
    
    ProdPricePlan updateProdPricePlan(ProdPricePlan prodPricePlan);
    
    void deleteProdPricePlan(Long pricePlanId);
    
    public List<Product> getProduct(Long productId);
    
    List<ProdMnoMappingInfoVO> getProductMappingInfoWithMNOByProductId(Long productId);

    ProductSpecInfoVO getProductSpecificationInfo(Long productId);
    
    public List<ProdPriceVO> getProdPricePlanByProdId(Long productId);
    
    List<ProdPricePlan> getProdPricePlanListByProductId(Long productId);
    
    public ProductBasicInfoVO getProductBasicInfoById(Long productId);

    public ProdMnoMappingVO getProductMappingByProdIdAndMnoId(Long productId, Integer mnoId);
    
    List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId);
    
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
    
    public List<ProdPricePlan> getProdPricePlanByProductId(Long productId);
    
    public ProdPricePlan getVaildProdPricePlanById(Long pricePlanId);
    
    public PdPrice getPdPriceDefByPriceId(Long priceId);
    
    public DiscountRule getSysDiscountRuleDefByDiscountId(Long discountId);
    
}
