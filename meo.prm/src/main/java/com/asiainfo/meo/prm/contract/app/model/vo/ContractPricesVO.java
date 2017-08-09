package com.asiainfo.meo.prm.contract.app.model.vo;

public class ContractPricesVO
{
    
    private Long    priceId;
    
    private Long    price;
    
    private Integer priceUnitId;
    
    private String  priceUnit;
    
    private Integer priceType;
    
    public Long getPriceId()
    {
        return priceId;
    }
    
    public void setPriceId(Long priceId)
    {
        this.priceId = priceId;
    }
    
    public Long getPrice()
    {
        return price;
    }
    
    public void setPrice(Long price)
    {
        this.price = price;
    }
    
    public Integer getPriceUnitId()
    {
        return priceUnitId;
    }
    
    public void setPriceUnitId(Integer priceUnitId)
    {
        this.priceUnitId = priceUnitId;
    }
    
    public String getPriceUnit()
    {
        return priceUnit;
    }
    
    public void setPriceUnit(String priceUnit)
    {
        this.priceUnit = priceUnit;
    }
    
    public Integer getPriceType()
    {
        return priceType;
    }
    
    public void setPriceType(Integer priceType)
    {
        this.priceType = priceType;
    }
    
}
