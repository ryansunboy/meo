package com.asiainfo.meo.customer.asset.app.model.vo;

public class SummaryCoinsVO implements Comparable<SummaryCoinsVO>
{
    private Long    summaryDate;
    
    private Integer coins;
    
    public Long getSummaryDate()
    {
        return summaryDate;
    }
    
    public void setSummaryDate(Long summaryDate)
    {
        this.summaryDate = summaryDate;
    }
    
    public Integer getCoins()
    {
        return coins;
    }
    
    public void setCoins(Integer coins)
    {
        this.coins = coins;
    }

    @Override
    public int compareTo(SummaryCoinsVO arg0)
    {
        return this.getSummaryDate().compareTo(arg0.getSummaryDate()); 
    }
    
}
