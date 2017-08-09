package com.asiainfo.meo.web.product.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIProductSpecVO
{
    private Long    productSpecId;
    
    @NotNull(groups = {Update.class })
    private Integer validationPeriod;
    
    private Integer validationPeriodUnitId;
    
    @NotNull(groups = {Update.class })
    private Integer activationPeriod;
    
    @NotNull(groups = {Insert.class, Update.class })
    private Integer activationPeriodUnitId;
    
    private Integer countryCode;
    
    private Integer provCode;
    
    @NotNull(groups = {Update.class })
    private Integer prodCycleTypeId;
    
    private Integer cityCode;
    
    private String  ip;
    
    private String  port;
    
    public Long getProductSpecId()
    {
        return productSpecId;
    }
    
    public void setProductSpecId(Long productSpecId)
    {
        this.productSpecId = productSpecId;
    }
    
    public Integer getValidationPeriod()
    {
        return validationPeriod;
    }
    
    public void setValidationPeriod(Integer validationPeriod)
    {
        this.validationPeriod = validationPeriod;
    }
    
    public Integer getValidationPeriodUnitId()
    {
        return validationPeriodUnitId;
    }
    
    public void setValidationPeriodUnitId(Integer validationPeriodUnitId)
    {
        this.validationPeriodUnitId = validationPeriodUnitId;
    }
    
    public Integer getActivationPeriod()
    {
        return activationPeriod;
    }
    
    public void setActivationPeriod(Integer activationPeriod)
    {
        this.activationPeriod = activationPeriod;
    }
    
    public Integer getActivationPeriodUnitId()
    {
        return activationPeriodUnitId;
    }
    
    public void setActivationPeriodUnitId(Integer activationPeriodUnitId)
    {
        this.activationPeriodUnitId = activationPeriodUnitId;
    }
    
    public Integer getCountryCode()
    {
        return countryCode;
    }
    
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public Integer getProvCode()
    {
        return provCode;
    }
    
    public void setProvCode(Integer provCode)
    {
        this.provCode = provCode;
    }
    
    public Integer getProdCycleTypeId()
    {
        return prodCycleTypeId;
    }
    
    public void setProdCycleTypeId(Integer prodCycleTypeId)
    {
        this.prodCycleTypeId = prodCycleTypeId;
    }
    
    public Integer getCityCode()
    {
        return cityCode;
    }
    
    public void setCityCode(Integer cityCode)
    {
        this.cityCode = cityCode;
    }
    
    public String getIp()
    {
        return ip;
    }
    
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }
    
}
