package com.asiainfo.meo.test.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.web.product.model.vo.UIProductCommonVO;
import com.asiainfo.meo.web.product.model.vo.UIProductMappingVO;
import com.asiainfo.meo.web.product.model.vo.UIProductPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProductSpecVO;
import com.asiainfo.meo.web.product.model.vo.UIProductVO;

public class ProductTest
{
    private static final String HTTPSURL = "https://localhost:8443/meo/rest/product";
    private String accessToken = "91e2aa2c4a03476d9ce5a085821cf60b";    
    private String methodName = "meo.product.publish";
    
    @Test
    public void testfunction() throws Exception
    {
        long currentTime = DateTimeUtil.getCurrentTimeMillis();
        System.err.println(currentTime+24*60*60*1000*6);
        String queryString = "method="+methodName+"&accessToken="+accessToken+"&timestamp="+currentTime+"&format=json&v=1";
        System.out.println(HTTPSURL+"?"+queryString);
    }
    
    @Test
    public void createObject1()
    {
        UIProductCommonVO productCommon = new UIProductCommonVO();
        UIProductVO              product = new UIProductVO();       
        product.setProductType(1L);
        product.setInventory(1);
        product.setProductName("the campaign productName");
        product.setDescription("the description");
        product.setImagUrl("D:/Local/MyEclipse/Common");
        product.setCategoryId(1);
        product.setDenomination(1);
        product.setDenominationUnit(1);
        product.setValidDate(Timestamp.valueOf("2015-06-01 00:00:00").getTime());
        product.setExpiredDate(Timestamp.valueOf("2016-04-31 23:59:59").getTime());
        product.setServiceTypeId(2L);      
        productCommon.setProduct(product);
        
        UIProductSpecVO          productSpec = new UIProductSpecVO();
        productSpec.setProductSpecId(100000001000L);
        productSpec.setLevelId(1);
        productSpec.setValidationPeriod(1);
        productSpec.setValidationPeriodUnitId(1);
        productSpec.setActivationPeriod(1);
        productSpec.setActivationPeriodUnitId(1);
        productSpec.setCountryCode(442500);
        productSpec.setProvCode(330100);
        productSpec.setProdCycleTypeId(1);
        productSpec.setCityCode(442501);
        productSpec.setPriceCoin(12);
        productCommon.setProductSpec(productSpec);
        
        List<UIProductMappingVO> productMappings = new ArrayList<UIProductMappingVO>();
        UIProductMappingVO umv1 = new UIProductMappingVO();
        umv1.setMnoId(1);
        umv1.setMnoProdId(1);
        productMappings.add(umv1);
        productCommon.setProductMappings(productMappings);
        
        UIProductPriceVO         productPrice = new UIProductPriceVO();
        productPrice.setPrice(10);
        productPrice.setPriceUnit(2);
        productCommon.setProductPrice(productPrice);
        
        System.out.println(JsonUtil.writeObjectAsString(productCommon));   
    }
    
    
    @Test
    public void createObject2()
    {
        UIProductCommonVO productCommon = new UIProductCommonVO();
        UIProductVO              product = new UIProductVO();       
        product.setProductType(1L);
        product.setInventory(1);
        product.setProductName("the campaign productName2");
        product.setDescription("the description2");
        product.setImagUrl("D:/Local/MyEclipse/Common2");
        product.setCategoryId(2);
        product.setDenomination(2);
        product.setDenominationUnit(2);
        product.setValidDate(Timestamp.valueOf("2015-07-01 00:00:00").getTime());
        product.setExpiredDate(Timestamp.valueOf("2016-09-31 23:59:59").getTime());
        product.setServiceTypeId(3L);      
        productCommon.setProduct(product);
        
        UIProductSpecVO          productSpec = new UIProductSpecVO();
        productSpec.setLevelId(2);
        productSpec.setValidationPeriod(2);
        productSpec.setValidationPeriodUnitId(2);
        productSpec.setActivationPeriod(2);
        productSpec.setActivationPeriodUnitId(2);
        productSpec.setCountryCode(442500);
        productSpec.setProvCode(330100);
        productSpec.setProdCycleTypeId(1);
        productSpec.setCityCode(442501);
        productSpec.setPriceCoin(12);
        productCommon.setProductSpec(productSpec);
        
        List<UIProductMappingVO> productMappings = new ArrayList<UIProductMappingVO>();
        UIProductMappingVO umv1 = new UIProductMappingVO();
        umv1.setMnoId(2);
        umv1.setMnoProdId(2);
        productMappings.add(umv1);
        productCommon.setProductMappings(productMappings);
        
        UIProductPriceVO         productPrice = new UIProductPriceVO();
        productPrice.setPriceId(1000010001L);
        productPrice.setPrice(10);
        productPrice.setPriceUnit(2);
        productCommon.setProductPrice(productPrice);
        
        System.out.println(JsonUtil.writeObjectAsString(productCommon));   
    }
}
