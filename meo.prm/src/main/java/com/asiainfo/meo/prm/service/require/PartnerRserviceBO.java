package com.asiainfo.meo.prm.service.require;

import java.util.List;

import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.common.app.model.vo.MeasureDefine;
import com.asiainfo.meo.system.service.entity.vo.SysPasswordVO;
import com.asiainfo.meo.system.user.app.model.vo.UserLoginVO;



public interface PartnerRserviceBO 
{

    UserLoginVO getPartnerLoginInfo(String loginAcct);
    
    void validBundleAccount(String bundelAcct,String validPwd);
    
    void updatePortalPassword(SysPasswordVO password);

    /**
      * @Description: 閸掓稑缂損artner鐢劕褰�
      * @Description: create partner account
      * @author zhaozx
      * @param user
      */
    void createPartnerAccount(UserLoginVO user);
    
    void updatePartnerLoginAccount(Long partnerId, String newLoginAcct);
    
    Partner getPartner(Long partnerId);

    /**
     * 
      * @Description: 鐏忓敆oken缂冾喖銇戦弫锟�      * @Description: expired token 
      * @modifyReason: 
      * @author liuyang
     */
    void expiredToken();
    
    Contract getContractByPartnerId(Long partnerId);
    
    public List<MeasureDefine> getCurrencyUnit(Long measureId);
    
    public EnumDefine getEnumDefByEnumCode(String enumCode, Integer enumType);

    void updatePortalIsFirstLoginFlagToFalse(String loginAcct);
    
    public void expiredAccessTokens(long entityId);
}
