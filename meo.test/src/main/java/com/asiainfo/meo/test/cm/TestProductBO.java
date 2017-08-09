package com.asiainfo.meo.test.cm;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.product.app.bo.ProductBO;
import com.asiainfo.meo.customer.product.app.model.entity.CmResource;

public class TestProductBO
{
    Logger log = Logger.getLogger(this.getClass());
    @Test
    public void testGetProduct() throws IOException{
        ProductBO productBO = ServiceLocatorFactory.getService(ProductBO.class);
        List<CmResource> cmResoucelist = productBO.getResouce(100000012000L);
       
        for(CmResource cmResource : cmResoucelist){
            
            log.info(JsonUtil.writeObjectAsString(cmResource));
        }
    }
}
