package com.asiainfo.meo.customer.service.require;

import java.util.List;

import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.system.common.app.model.vo.CategoryDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.service.entity.vo.SysOtpVO;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;

public interface CustomerRserviceBO
{
    
    UserLoginVO getUserLoginInfo(String loginAcct);
    
    void validBundleAccount(String bundelAcct, String validPwd);
    
    void modifyCustPassword(SysPasswordVO password);
    
    void createSysCustLoginInfo(UserLoginVO userLogin);
    
    void setOTPToUsed(SysOtpVO sysOtp);
    
    EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType);
    
    Customer getCustomer(Long custId);
    
    void   updateCustomer(Customer customer);
    
    public List<com.asiainfo.meo.product.profile.app.model.entity.Product> getProduct(Long productId);

    void expiredToken();
    
    com.asiainfo.meo.product.profile.app.model.entity.Product getProductById(Long id);
    
    public List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId);
    
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize);
    
    public List<CategoryDefine> getCategoryEnum(Long categoryId, Integer categoryType);
    
    public List<EnumDefine> getEnumByCode(String enumCode, String enumType);
    
    public void expiredAccessTokens(long entityId);
    
}
