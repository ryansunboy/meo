package com.asiainfo.meo.test.cm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.asset.app.bo.AssetBO;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;

public class TestAssetBO
{
    @Test
    public void testAsset()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("assetId", 100000002000L);
        map.put("assetType", 3);
        AssetBO assetBO = ServiceLocatorFactory.getService(AssetBO.class);
        List<Asset> assetlist = assetBO.getAsset(map);
        for(Asset asset: assetlist){
            System.out.println(asset.getAmount());
        }
    }
    
}

 
