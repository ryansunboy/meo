package com.asiainfo.meo.prm.profile.app.repository;

import java.util.List;
import java.util.Map;

import com.asiainfo.meo.prm.profile.app.model.entity.Partner;

public interface PartnerRepository
{
    /**
     * @Description: 保存partner
     * @Description: save partner
     * @author zhaozx
     * @param partner
     */
    void savePartner(Partner partner);
    
    /**
     * @Description: 修改partner
     * @Description: update partner
     * @author zhaozx
     * @param partner
     */
    void updatePartner(Partner partner);
    
    /**
     * @Description: 获得partner
     * @Description: get partner
     * @author zhaozx
     * @param partnerId partner id
     * @return
     */
    Partner getPartner(long partnerId);
    
    /**
     * @Description: 获得特定状态的partner
     * @Description: get partner
     * @author zhaozx
     * @param partnerId partner id
     * @param partnerSts partner sts
     * @return
     */
    Partner getPartner(long partnerId, int partnerSts);
    
    /**
      * @Description: 通过sql查询partner信息
      * @Description: select partner information by sql
      * @author zhaozx
      * @param sqlName
      * @param values
      * @return
      */
    List<Map<String, Object>> queryPartnerInfoByNameSql(String sqlName, Object... values);

    List<Partner> getPartnerList(Long partnerId, Integer pageSize, Integer pageNo);
}