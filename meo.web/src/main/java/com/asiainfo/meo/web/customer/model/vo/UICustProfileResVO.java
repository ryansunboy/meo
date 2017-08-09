package com.asiainfo.meo.web.customer.model.vo;

public class UICustProfileResVO
{
    
    private UICustomerInfoVO  customerInfo;
    private UIAssetInfoVO assetInfo;

    public void setAssetInfo(UIAssetInfoVO assetInfo)
    {
        this.assetInfo = assetInfo;
    }

    public UIAssetInfoVO getAssetInfo()
    {
        return assetInfo;
    }

    public UICustomerInfoVO getCustomerInfo()
    {
        return customerInfo;
    }

    public void setCustomerInfo(UICustomerInfoVO customerInfo)
    {
        this.customerInfo = customerInfo;
    }

     
    
}
