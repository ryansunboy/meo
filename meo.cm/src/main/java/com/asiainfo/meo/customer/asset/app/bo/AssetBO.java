package com.asiainfo.meo.customer.asset.app.bo;

import java.util.List;

import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.entity.AssetChgDetail;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCoinBlanceVO;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCustVO;

public interface AssetBO
{
//    void saveAsset(Asset asset);

    void saveAssestChgDetail(AssetChgDetail assetChgDetail);

    /**
      * @Description: (根据custId查询资产)
      * @Description: (get asset information)
      * @modifyReason: 
      * @author lill
      * @param map
      * @return    
      */
    List<Asset> getAsset(Long custId,Integer assetType);
    
    /**
     * 
      * @Description: 更新客户的asset信息
      * @Description: update the asset information
      * @modifyReason: 
      * @author liuyang
      * @param asset
     */
    
    void updateAsset(Asset asset);
    
    AssetCoinBlanceVO checkCoinBalance(Long custId);

    List<Asset> createCustomerAssets(Long custId);
    
    AssetCustVO getAssetInfoByCustIdAndAssetType(Long custId, Integer assetType);
}
 