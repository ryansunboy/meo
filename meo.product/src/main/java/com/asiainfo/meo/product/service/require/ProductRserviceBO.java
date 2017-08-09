package com.asiainfo.meo.product.service.require;

import java.util.List;

import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.system.common.app.model.entity.SysCountryDef;
import com.asiainfo.meo.system.common.app.model.entity.SysProvDef;
import com.asiainfo.meo.system.common.app.model.entity.SysRegionDef;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;

public interface ProductRserviceBO 
{
    public List<MeasureDefine> getCurrencyUnit(Long measureId);
   
    public EnumDefine getEnumDefByEnumCode(String enumCode,Integer enumType) ;
    
    public Price savePrice(Price price);
    
    public Price updatePrice(Price price);
    
    public Price getPrice(Long priceId);
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType);
    
    public List<SysCountryDef> getCountryDef(Long countryCode);
    
    public List<SysProvDef> getProvDef(Long provCode);
    
    public List<SysRegionDef> getRegionDef(Long regionCode);
    
}
