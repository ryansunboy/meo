package com.asiainfo.meo.customer.asset.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.entity.AssetChgDetail;

public interface AssetRepository
{
    Asset saveAsset(Asset asset);
    
    List<Asset> initCustomerAssets(List<Asset> assets);
    
    void saveAssetChgDetail(AssetChgDetail assetChgDetail);
    
    // Map<String, Object> getAsset(String string, Object... objects);
   
    // List<Asset> getAssetList(String string, Object... objects );
    
    List<Asset> getAsset(DetachedCriteria condition);
    
    List<Asset> getAssetByCustId(Long custId);
    
    void updateAsset(Asset aset);
    
    Asset getAssetBalanceByCustIdAndAssetType(Long custId, Integer assetType);
    
    List<Object> checkCoinBalance(Long custId);
    
    Asset getAssetInfoByAssetId(Long assetId);
    
}
