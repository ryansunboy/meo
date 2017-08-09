package com.asiainfo.meo.customer.asset.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.entity.AssetChgDetail;
import com.asiainfo.meo.customer.asset.app.repository.AssetRepository;

public class AssentRepositoryImpl implements AssetRepository
{
    private final static String SEQ_ASSET_ID = "SEQ_ASSET_ID";
    
    private final static String SEQ_ASSET_CHG_DETAIL_ID = "SEQ_ASSET_CHG_DETAIL_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public Asset saveAsset(Asset asset)
    {
        Long assetId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_ASSET_ID);
        asset.setAssetId(assetId);
        hibernateRepository.saveObject(asset);
        return getAssetInfoByAssetId(assetId);
    }
    
    
     public void saveAssetChgDetail(AssetChgDetail assetChgDetail)
     {
         Long id = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_ASSET_CHG_DETAIL_ID);
         assetChgDetail.setId(id);
         hibernateRepository.saveObject(assetChgDetail);
     }
    //
    // public Map<String, Object> getAsset(String string, Object... object)
    // {
    // return hiberRepository.findUniqueMapByNativeSql(string, object);
    // }
    //
    // public List<Asset> getAssetList(String string, Object... objects)
    // {
    // List<Asset> list = new ArrayList<Asset>();
    // // List<Asset> assetList = (List<Asset>) hiberRepository.findByNativeSql(string, objects);
    // // (Asset asset : assetList){
    // // Asset asset1 = new Asset();
    // // asset1.setAmount(asset.getAmount());
    // // asset1.setCustId(asset.getCustId());
    // // list.add(asset1);
    // // }
    //
    // return list;
    // }
    
    @SuppressWarnings("unchecked")
    public List<Asset> getAsset(DetachedCriteria condition)
    {
        return (List<Asset>) hibernateRepository.findByCriteria(condition);
    }
    
    @Override
    public List<Asset> getAssetByCustId(Long custId)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(Asset.class).add(Restrictions.eq("custId", custId));
        
        return (List<Asset>) hibernateRepository.findByCriteria(condition);
    }
    
    @Override
    public void updateAsset(Asset aset)
    {
        hibernateRepository.updateObject(aset);
        
    }
    
    @Override
    public Asset getAssetBalanceByCustIdAndAssetType(Long custId, Integer assetType)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(Asset.class).add(Restrictions.eq("custId", custId))
                .add(Restrictions.eq("assetType", assetType));
        return (Asset) hibernateRepository.findUniqueObjectByCriteria(condition);
    }
    
    @Override
    public List<Object> checkCoinBalance(Long custId)
    {
        return (List<Object>) hibernateRepository.findByNamedQuery("meo.asset.getCoinBalance", custId);
    }

    @Override
    public Asset getAssetInfoByAssetId(Long assetId)
    {
        return hibernateRepository.get(Asset.class, assetId);
    }
    

    @Override
    public List<Asset> initCustomerAssets(List<Asset> assets)
    {
        for(Asset asset:assets)
        {
            Long assetId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_ASSET_ID);
            asset.setAssetId(assetId);
        }
        hibernateRepository.saveOrUpdataAll(assets);
        return assets;
    }
    
 
    
}
