package com.asiainfo.meo.customer.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.service.provide.CustomerPserviceBO;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.service.provide.ProductPserviceBO;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;

public class CustomerRserviceBOImpl implements CustomerRserviceBO
{
    @Resource
    SystemPserviceBO           sysPserviceBo;
    
    @Resource
    TokenBO tokenBO;
    
    @Resource
    ProductPserviceBO productPserviceBO;
    
    @Resource
    private CustomerPserviceBO customerPserviceBO;
    
    public UserLoginVO getUserLoginInfo(String loginAcct)
    {
        return sysPserviceBo.getUserLoginInfo(loginAcct, SysEnumCodeDefine.USER_TYPE_CUSTOMER);
    }
    
    public void validBundleAccount(String bundelAcct, String validPwd)
    {
        sysPserviceBo.validBundleAccount(bundelAcct, validPwd);
    }
    
    public void modifyCustPassword(SysPasswordVO password)
    {
        sysPserviceBo.updatePassword(password);
    }
    
    public void createSysCustLoginInfo(UserLoginVO userLogin)
    {
        sysPserviceBo.createUserLoginInfo(userLogin);
    }
    
    public void setOTPToUsed(SysOtpVO sysOtp)
    {
        sysPserviceBo.setOTPToUsed(sysOtp);
    }
    
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        return sysPserviceBo.getEnumDefByEnumCode(enumCode, enumType);
    }
    
    public Customer getCustomer(Long custId)
    {
        if (ValidateUtil.isNull(custId))
        {
            return null;
        }
        
        return customerPserviceBO.getCustomer(custId);
    }
    
    public void updateCustomer(Customer customer)
    {
        customerPserviceBO.updateCustomer(customer);
    }

    @Override
    public List<com.asiainfo.meo.product.profile.app.model.entity.Product> getProduct(Long productId)
    {
         return productPserviceBO.getProduct(productId);
    }

    @Override
    public void expiredToken()
    {
         tokenBO.expiredToken(BoContext.getBoContext().getContent(Token.class).getAccessToken());
    }

    @Override
    public com.asiainfo.meo.product.profile.app.model.entity.Product getProductById(Long id)
    {
        // TODO Auto-generated method stub
        return productPserviceBO.getProductById(id);
    }

    @Override
    public List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId)
    {
        return productPserviceBO.getProdMnoMappingByMnoId(mnoId);
    }

    @Override
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize)
    {
        return productPserviceBO.getAppProductList(productIds, pageNo, pageSize);
    }

    @Override
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType)
    {
        return sysPserviceBo.getCategoryEnum(categoryId, categoryType);
    }

    @Override
    public List<EnumDefine> getEnumByCode(String enumCode, String enumType)
    {
        return sysPserviceBo.getEnumByCode(enumCode, enumType);
    }

    @Override
    public void expiredAccessTokens(long entityId)
    {
        tokenBO.expiredAccessTokens(entityId);
    }
}
