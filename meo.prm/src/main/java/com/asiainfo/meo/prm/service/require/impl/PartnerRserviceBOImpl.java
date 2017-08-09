package com.asiainfo.meo.prm.service.require.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.service.provide.PartnerPserviceBO;
import com.asiainfo.meo.prm.service.require.PartnerRserviceBO;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.define.SysEnumCodeDefine;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;


public class PartnerRserviceBOImpl implements PartnerRserviceBO
{
    
    @Resource
    SystemPserviceBO sysPserviceBo;
    
    @Resource
    TokenBO tokenBO;
    
    @Resource
    PartnerPserviceBO partnerPserviceBO;
    
    @Override
    public UserLoginVO getPartnerLoginInfo(String loginAcct)
    {
        return sysPserviceBo.getUserLoginInfo(loginAcct, SysEnumCodeDefine.USER_TYPE_PORTAL);
    }
    
    @Override
    public void validBundleAccount(String bundelAcct, String validPwd)
    {
        sysPserviceBo.validBundleAccount(bundelAcct, validPwd);
    }
    
    @Override
    public void updatePortalPassword(SysPasswordVO password)
    {
        sysPserviceBo.updatePassword(password);
    }
    
    
    /**
     * @Description: 创建partner帐号
     * @Description: create partner account
     * @author zhaozx
     * @param user
     */
    @Override
    public void createPartnerAccount(UserLoginVO user)
    {
        sysPserviceBo.createUserLoginInfo(user);
    }
    
    @Override
    public void updatePartnerLoginAccount(Long partnerId, String newLoginAcct)
    {
        if (ValidateUtil.isNull(partnerId)|| ValidateUtil.isEmpty(newLoginAcct))
            return;
        sysPserviceBo.updatePartnerLoginAcct(partnerId, newLoginAcct);
    }

    @Override
    public Partner getPartner(Long partnerId)
    {
         return partnerPserviceBO.getPartner(partnerId);
    }

    @Override
    public void expiredToken()
    {
        tokenBO.expiredToken(BoContext.getBoContext().getContent(Token.class).getAccessToken());
        
    }

    @Override
    public Contract getContractByPartnerId(Long partnerId)
    {
        return partnerPserviceBO.getContractByPartnerId(partnerId);
    }
    
    public List<MeasureDefine> getCurrencyUnit(Long measureId)
    {
        return sysPserviceBo.getMeasureDef(measureId, MeasureDefine.CURRENCY_UNIT_TYPE);
    }
    
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType)
    {
        return sysPserviceBo.getEnumDefByEnumCode(enumCode, enumType);
    }

    @Override
    public void updatePortalIsFirstLoginFlagToFalse(String loginAcct)
    {
       sysPserviceBo.updateUserIsFirstLoginFlagToFalse(loginAcct,SysEnumCodeDefine.USER_TYPE_PORTAL);
        
    }

    @Override
    public void expiredAccessTokens(long entityId)
    {
        tokenBO.expiredAccessTokens(entityId);
    }
    
}
