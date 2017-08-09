package com.asiainfo.meo.product.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.product.profile.app.model.entity.DiscountRule;
import com.asiainfo.meo.product.profile.app.model.entity.PdPrice;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.entity.ProductSpec;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoQueryConditionVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;

public interface ProductRepository
{
    public List<Product> getPdProduct(ProductBasicInfoVO pdProductVO, Integer pageNo, Integer pageSize);
    
    public List<ProductSpec> getPdProductSpec(ProductBasicInfoVO pdProductSpec);
    
    public List<Product> getProductList(ProductBasicInfoQueryConditionVO conditionVO, Integer pageSize, Integer pageNo);
    
    public Integer getProductListTotalSize(ProductBasicInfoQueryConditionVO conditionVO);
    
    public Product getProductById(Long id);
    
    public Product addProduct(Product product);
    
    public Product updateProduct(Product product);
    
    public List<Product> getProduct(Long productId);
    
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize);
    
    public void createPriceDef(PdPrice price);
    
    public void modifyPriceDef(PdPrice price);
    
    public PdPrice getPriceDefByPriceId(Long priceId);
    
    public List<PdPrice> getPriceDefList(Integer pageNo, Integer pageSize, Long priceId, String priceName);
    
    public Integer getPriceDefTotalSize();
    
    public void createDiscountRuleDef(DiscountRule discountRule);
    
    public void modifyDiscountRuleDef(DiscountRule discountRule);
    
    public DiscountRule getDiscountRuleDefByDiscountId(Long priceId);
    
    public List<DiscountRule> getDiscountRuleDefList(Integer pageNo, Integer pageSize, Long discountId, String discountName);
    
    public Integer getDiscountRuleDefTotalSize();
    
    
    
}
