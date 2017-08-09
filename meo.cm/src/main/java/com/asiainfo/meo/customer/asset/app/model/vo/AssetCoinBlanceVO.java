package com.asiainfo.meo.customer.asset.app.model.vo;

import java.util.ArrayList;
import java.util.List;

public class AssetCoinBlanceVO
{
    private List<SummaryCoinsVO> summaryCoins = new ArrayList<SummaryCoinsVO>();

    public List<SummaryCoinsVO> getSummaryCoins()
    {
        return summaryCoins;
    }

    public void setSummaryCoins(List<SummaryCoinsVO> summaryCoins)
    {
        this.summaryCoins = summaryCoins;
    }
    
 
    
}
