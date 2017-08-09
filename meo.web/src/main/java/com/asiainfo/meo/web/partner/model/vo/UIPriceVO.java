package com.asiainfo.meo.web.partner.model.vo;


public class UIPriceVO
{
    private Long unitPrice;
    private Integer unit;
    private Integer priceType;
    private Integer status;
    private String validDate;
    private String expiredDate;
    
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public String getValidDate()
    {
        return validDate;
    }
    public void setValidDate(String validDate)
    {
        this.validDate = validDate;
    }
    public String getExpiredDate()
    {
        return expiredDate;
    }
    public void setExpiredDate(String expiredDate)
    {
        this.expiredDate = expiredDate;
    }
    public Long getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(Long unitPrice)
    {
        this.unitPrice = unitPrice;
    }
   
    public Integer getUnit()
    {
        return unit;
    }
    public void setUnit(Integer unit)
    {
        this.unit = unit;
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
