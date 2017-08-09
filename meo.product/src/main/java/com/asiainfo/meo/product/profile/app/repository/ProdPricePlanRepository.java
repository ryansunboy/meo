package com.asiainfo.meo.product.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.product.profile.app.model.entity.ProdPricePlan;

public interface ProdPricePlanRepository
{
    ProdPricePlan getProdPricePlanById(Long pricePlanId);
    
    public ProdPricePlan getProdPricePlanByPriceId(Long priceId);
    
    ProdPricePlan addProdPricePlan(ProdPricePlan prodPricePlan);
    
    ProdPricePlan updateProdPricePlan(ProdPricePlan prodPricePlan);
    
    public List<ProdPricePlan> getProdPricePlanByProdId(Long productId);
    
    List<ProdPricePlan> getProdPricePlanListByProductId(Long productId);
    
    public ProdPricePlan getVaildProdPricePlanById(Long pricePlanId);
   
}
