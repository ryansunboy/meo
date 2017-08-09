package com.asiainfo.meo.activity.business.bean.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.define.ActivityErrorCodeDefine;
import com.asiainfo.meo.product.profile.app.constant.ProductErrorConstant;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class ActivateResourceBean implements ActivityBean
{
    
    ActivityRserviceBO activityRserviceBO;
    
    private Long       custId;
    
    private Long       resourceId;
    
    public void init(Object... input)
    {
        custId = (Long) input[0];
        resourceId = (Long) input[1];
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
    }
    
    public void validate(Object... input)
    {
        if (ValidateUtil.isNull(custId))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{"custId" });
        }
        Customer customer = activityRserviceBO.getCustomerByCustId(custId);
        if (ValidateUtil.isNull(customer))
        {
            throw new MeoException(CustomerErrorConstant.CUST_DO_NOT_EXIST);
        }
        if (ValidateUtil.isNull(resourceId))
        {
            throw new MeoException(CustomerErrorConstant.INPUT_PARAM_IS_EMPTY, new Object[]{"resourceId" });
        }
    }
    
    public Object business(Object... input)
    {
        CustomerBundleInfo bundleInfo = activityRserviceBO.getBundleInfoByCustId(custId);
        if (ValidateUtil.isNull(bundleInfo))
        {
            throw new MeoException(CustomerErrorConstant.CUST_NOT_BIND_ACCT);
        }
        
        ProdMnoMappingVO mapping = activityRserviceBO.getMnoProductMappingByResourceIdAndMnoId(resourceId, bundleInfo.getMnoId());
        if (ValidateUtil.isNull(mapping)|| ValidateUtil.isNull(mapping.getMnoProdId()))
        {
            throw new MeoException(ProductErrorConstant.MNO_PROD_NOT_FOUND);
        }
        CmResource resource = activityRserviceBO.getResourceByResourceId(resourceId);
        if (resource.getResSts()!= CmResource.RES_STATUS_REDEEM)
        {
            throw new MeoException(ActivityErrorCodeDefine.RESOURCE_NOT_ALLOW_ACTIVATE);
        }
        activityRserviceBO.orderMnoProduct(bundleInfo.getBundleAcct(), mapping.getMnoId(), mapping.getMnoProdId());
        ProductSpecInfoVO spec = activityRserviceBO.getProductSpecByProductId(resource.getProductId());
        resource.setResSts(CmResource.RES_STATUS_ACTIVE);
        Date currentDate = DateTimeUtil.getNow();
        Timestamp beginDate = DateTimeUtil.getBeginTimestampDay(currentDate);
        Timestamp endDate = DateTimeUtil.getEndTimestampDay(DateTimeUtil.addDay(beginDate, spec.getValidationPeriod()));
        resource.setCycleBeginDate(beginDate);
        resource.setCycleEndDate(endDate);
        activityRserviceBO.updateResource(resource);
        return null;
    }
    
}
