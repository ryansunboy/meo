package com.asiainfo.meo.customer.asset.app.bo.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.bo.AssetBO;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.asset.app.model.entity.AssetChgDetail;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCoinBlanceVO;
import com.asiainfo.meo.customer.asset.app.model.vo.AssetCustVO;
import com.asiainfo.meo.customer.asset.app.model.vo.SummaryCoinsVO;
import com.asiainfo.meo.customer.asset.app.repository.AssetRepository;
import com.asiainfo.meo.customer.define.CmConstantDefine;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;

public class AssetBOImpl implements AssetBO
{
    private static final Log     LOG       = LogFactory.getLog(AssetBOImpl.class);
    
    @Resource
    private AssetRepository      assetRepository;
    
    private static final Integer SEVENDAYS = 7;
    
    @Override
    public void saveAssestChgDetail(AssetChgDetail assetChgDetail)
    {
        assetRepository.saveAssetChgDetail(assetChgDetail);
    }
    
    public List<Asset> getAsset(Long custId, Integer assetType)
    {
        LOG.debug("AssetBO getAsset begin");
        
        if (ValidateUtil.isNull(custId))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{CmConstantDefine.CUSTOMER_ID });
        }
        
        DetachedCriteria condition = DetachedCriteria.forClass(Asset.class).add(Restrictions.eq("custId", custId));
        
        if (ValidateUtil.isNotNull(assetType))
        {
            condition.add(Restrictions.eq("assetType", assetType));
        }
        
        List<Asset> assetList = assetRepository.getAsset(condition);
        
        LOG.debug("AssetBO getAsset end");
        
        return assetList;
    }
    
    @Override
    public void updateAsset(Asset asset)
    {
        List<Asset> assets = getAssetByCustId(asset.getCustId());
        if (ValidateUtil.isNotEmpty(assets))
        {
            for (Asset aset : assets)
            {
                if(asset.getAssetType()==aset.getAssetType())
                {
                    if (aset.getAssetType()== Asset.ASSET_TYPE_COINS)
                    {
                        aset.setAmount(asset.getAmount()+ aset.getAmount());
                        AssetChgDetail assetChgDetail = new AssetChgDetail();
                        assetChgDetail.setAmount(asset.getAmount());
                        assetChgDetail.setAssetType(asset.getAssetType());
                        assetChgDetail.setCustId(asset.getCustId());
                        assetChgDetail.setDoneTime(DateTimeUtil.getNow());
                        assetChgDetail.setAssetId(aset.getAssetId());
                        saveAssestChgDetail(assetChgDetail);
                    }
                    else if (aset.getAssetType()== Asset.ASSET_TYPE_TOTAL_COINS&& asset.getAmount()> 0)
                    {
                        aset.setAmount(asset.getAmount()+ aset.getAmount());
                    }
                    else
                    {
                        throw new MeoException(CustomerErrorConstant.VALID_ASSET_TYPE_NOT_FOUND);
                    }
                    assetRepository.updateAsset(aset);
                }
            }
        }
        
    }
    
    public List<Asset> getAssetByCustId(Long custId)
    {
        
        if (ValidateUtil.isNull(custId))
        {
            return null;
        }
        List<Asset> assetList = assetRepository.getAssetByCustId(custId);
        return assetList;
    }
    
    public AssetCoinBlanceVO checkCoinBalance(Long custId)
    {
        AssetCoinBlanceVO assetCoinBlanceVO = new AssetCoinBlanceVO();
        List<SummaryCoinsVO> summaryCoins = new ArrayList<SummaryCoinsVO>();
        List<Date> datelist1 = new ArrayList<Date>();
        List<Object> list = assetRepository.checkCoinBalance(custId);
        for (Object summaryCoin : list)
        {
            SummaryCoinsVO summaryCoinsVO = new SummaryCoinsVO();
            Object[] object = (Object[]) summaryCoin;
            summaryCoinsVO.setCoins(((BigDecimal) object[0]).intValue());
            summaryCoinsVO.setSummaryDate(Timestamp.valueOf((String) object[1]+ DateTimeUtil.DAY_TIME_START).getTime());// 数据库中存在的数据
            summaryCoins.add(summaryCoinsVO);
        }
        // 数据库不存在的数据
        List<Date> dateList = DateTimeUtil.getBeforeNDays(SEVENDAYS);
        for (SummaryCoinsVO summaryCoin : summaryCoins)
        {
            if (summaryCoin.getSummaryDate()== null)
            {
                continue;
            }
            datelist1.add(new Date(summaryCoin.getSummaryDate()));
        }
        dateList.removeAll(datelist1);
        for (Date dateTime : dateList)
        {
            SummaryCoinsVO summaryCoinsVO = new SummaryCoinsVO();
            summaryCoinsVO.setCoins(0);
            summaryCoinsVO.setSummaryDate(dateTime.getTime());
            summaryCoins.add(summaryCoinsVO);
        }
        Collections.sort(summaryCoins);
        assetCoinBlanceVO.setSummaryCoins(summaryCoins);
        return assetCoinBlanceVO;
    }
    
    @Override
    public List<Asset> createCustomerAssets(Long custId)
    {
        List<Asset> assets = new ArrayList<Asset>();
        Asset experience = new Asset();
        experience = createAsset(custId, 0, Asset.ASSET_TYPE_EXPERIENCE);
        Asset coins = new Asset();
        coins = createAsset(custId, 0, Asset.ASSET_TYPE_COINS);
        Asset periodCoins = new Asset();
        periodCoins = createAsset(custId, 0, Asset.ASSET_TYPE_PERIOD_COINS);
        Asset totalCoins = new Asset();
        totalCoins = createAsset(custId, 0, Asset.ASSET_TYPE_TOTAL_COINS);
        experience = assetRepository.saveAsset(experience);
        coins = assetRepository.saveAsset(coins);
        periodCoins = assetRepository.saveAsset(periodCoins);
        totalCoins = assetRepository.saveAsset(totalCoins);
        assets.add(experience);
        assets.add(coins);
        assets.add(periodCoins);
        assets.add(totalCoins);
        // assetRepository.initCustomerAssets(assets);
        return assets;
    }
    
    private Asset createAsset(Long custId, Integer unit, int type)
    {
        Asset asset = new Asset();
        asset.setAmount(0);
        asset.setAssetType(type);
        asset.setUnit(unit);
        asset.setCustId(custId);
        asset.setCreateDate(DateTimeUtil.getNow());
        asset.setSts(Asset.STS_VALID);
        return asset;
    }
    
    @Override
    public AssetCustVO getAssetInfoByCustIdAndAssetType(Long custId, Integer assetType)
    {
        AssetCustVO assetCustVO = new AssetCustVO();
        Asset asset = assetRepository.getAssetBalanceByCustIdAndAssetType(custId, assetType);
        if (ValidateUtil.isNotEmpty(asset))
        {
            assetCustVO.setCustomerID(custId);
            assetCustVO.setAssetType(assetType);
            assetCustVO.setAssetBalance(asset.getAmount());
        }
        else
        {
            assetCustVO.setCustomerID(custId);
            assetCustVO.setAssetType(assetType);
            assetCustVO.setAssetBalance(0L);
        }
        return assetCustVO;
    }
    
}
