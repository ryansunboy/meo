package com.asiainfo.meo.product.profile.app.repository;

import com.asiainfo.meo.product.profile.app.model.entity.ProductSpec;

public interface ProductSpecRepository
{
    ProductSpec getProductSpecById(Long id);
    
    ProductSpec addProductSpec(ProductSpec productSpec);
    
    ProductSpec updateProductSpec(ProductSpec productSpec);
    
    ProductSpec getProductSpecificationInfo(Long productId);
}
