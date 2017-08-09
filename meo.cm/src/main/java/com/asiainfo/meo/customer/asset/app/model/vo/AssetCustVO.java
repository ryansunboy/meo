package com.asiainfo.meo.customer.asset.app.model.vo;

public class AssetCustVO
{
    private Long assetBalance;
    
    private Long customerID;
    
    private Integer assetType;

    public Integer getAssetType()
    {
        return assetType;
    }

    public void setAssetType(Integer assetType)
    {
        this.assetType = assetType;
    }

    public Long getAssetBalance()
    {
        return assetBalance;
    }

    public void setAssetBalance(Long assetBalance)
    {
        this.assetBalance = assetBalance;
    }

    public Long getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(Long customerID)
    {
        this.customerID = customerID;
    }
    
}
