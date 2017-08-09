package com.asiainfo.meo.product.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.product.service.require.ProductRserviceBO;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysProvDef;
import com.asiainfo.meo.system.common.app.model.entity.SysRegionDef;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;

public class ProductRserviceBOImpl implements ProductRserviceBO
{

    @Resource
    private SystemPserviceBO systemPserviceBO;
    
    @Resource
    private PartnerPserviceBO partnerPserviceBO;
    
    public List<MeasureDefine> getCurrencyUnit(Long measureId)
    {
        return systemPserviceBO.getMeasureDef(measureId, MeasureDefine.CURRENCY_UNIT_TYPE);
    }

    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        return systemPserviceBO.getEnumDefByEnumCode(enumCode, enumType);
    }

    public Price savePrice(Price price)
    {
        return partnerPserviceBO.addPrice(price);
    }

    public Price updatePrice(Price price)
    {
        return partnerPserviceBO.updatePrice(price);
    }

    public Price getPrice(Long priceId)
    {
        return partnerPserviceBO.getPrice(priceId);
    }
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        return systemPserviceBO.getCategoryEnum(categoryId, categoryType);
    }

    @Override
    public List<SysCountryDef> getCountryDef(Long countryCode)
    {
        return systemPserviceBO.getCountryDef(countryCode);
    }

    @Override
    public List<SysProvDef> getProvDef(Long provCode)
    {
        return systemPserviceBO.getProvDef(provCode);
    }

    @Override
    public List<SysRegionDef> getRegionDef(Long regionCode)
    {
        return systemPserviceBO.getRegionDef(regionCode);
    }
}
