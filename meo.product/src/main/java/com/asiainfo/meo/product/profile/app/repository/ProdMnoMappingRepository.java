package com.asiainfo.meo.product.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;

public interface ProdMnoMappingRepository
{
    ProdMnoMapping getProdMnoMappingById(Long id);
    
    List<ProdMnoMapping> getProdMnoMappingListByProductId(Long productId); 
    
    ProdMnoMapping addProdMnoMapping(ProdMnoMapping prodMnoMapping);
    
    ProdMnoMapping updateProdMnoMapping(ProdMnoMapping prodMnoMapping);
    
    public void deleteProdMnoMappingById(Long id);

    ProdMnoMapping getProdMnoMappingByProdIdAndMnoId(Long productId, Integer mnoId);
    
    ProdMnoMapping getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(Integer mnoId,Long productId,Long mnoProdId);

    List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId);
}
