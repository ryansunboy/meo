package com.asiainfo.meo.activity.business.bean.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.business.model.entity.CmRewardActivity;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.asset.app.model.entity.Asset;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.define.ActivityEnumCodeDefine;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.service.require.ActivityRserviceBO;

public class GrantProductBean implements ActivityBean
{
    @Resource
    ActivityRepository activityRepository;
    
    @Resource
    ActivityRserviceBO activityRserviceBO;
    
    private Long       activityId;
    
    private Long       actionId;
    
    private Long       custId;
    
    private String     campaignNo;
    
    private Long       taskId;
    
    private Long       campaignId;
    
    public void init(Object... input)
    {
        activityId = null;
        actionId = null;
        custId = (Long) input[0];
        campaignId = (Long) input[1];
        actionId = (Long) input[2];
        activityId = (Long) input[3];
        taskId = (Long) input[4];
        activityRepository = ServiceLocatorFactory.getService(ActivityRepository.class);
        activityRserviceBO = ServiceLocatorFactory.getService(ActivityRserviceBO.class);
        
    }
    
    public void validate(Object... input)
    {
        
    }
    
    public Object business(Object... input)
    {
        Long productId = activityRserviceBO.getGrantProduct(taskId);
        if (ValidateUtil.isNull(productId))
        {
            return null;
        }
        ProductSpecInfoVO spec = activityRserviceBO.getProductSpecByProductId(productId);
        CmResource resouce = new CmResource();
        resouce.setCustId(custId);
        resouce.setProductId(productId);
        resouce.setResType(CmResource.RES_TYPE_DATA_PACKAGE);
        resouce.setResSts(CmResource.RES_STATUS_REDEEM);
        Date currentDate = DateTimeUtil.getNow();
        Timestamp beginDate = DateTimeUtil.getBeginTimestampDay(currentDate);
        Timestamp endDate = DateTimeUtil.getEndTimestampDay(DateTimeUtil.addDay(beginDate, spec.getActivationPeriod()));
        resouce.setCycleBeginDate(beginDate);
        resouce.setCycleEndDate(endDate);
        activityRserviceBO.redeemProduct(resouce);
        return null;
    }
    
}
