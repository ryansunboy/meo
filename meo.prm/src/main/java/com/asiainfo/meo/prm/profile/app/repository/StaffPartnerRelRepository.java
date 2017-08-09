 package com.asiainfo.meo.prm.profile.app.repository;                                                                                                                                                                                                                                                                       

import java.util.List;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
                                                                                                                                                                                                                                                                                              
 public interface StaffPartnerRelRepository
{
    /**
      * @Description: 閼惧嘲绶眕artner閸涙ê浼愰崗宕囬兇閸掓銆�
      * @Description: get partner and staff relation list
      * @author zhaozx
      * @param saleId
      * @return
      */
    List<StaffPartnerRel> getPartnerIdList(long saleId);
    /**
      * @Description: 閸掓稑缂撻崨妯轰紣閸滃artner閻ㄥ嫬鍙х化锟�      * @Description: create partner and staff relation
      * @author zhaozx
      * @param staffPartnerRel
      */
    void saveStaffPartnerRel(StaffPartnerRel staffPartnerRel);
    
    /**
      * @Description: 閼惧嘲绶眕artner閸涙ê浼愰崗宕囬兇閸掓銆�
      * @Description: get partner and staff relation list
      * @author zhaozx
      * @param saleId
      * @param partnerId
      * @param pageSize
      * @param pageNo
      * @return
      */
    List<StaffPartnerRel> getPartnerIdList(long saleId,Long partnerId,int pageSize, int pageNo );
    
    int getPartnerIdListTotalSize(long saleId,Long partnerId);
    
    StaffPartnerRel getStaffPartnerRel(Long partnerId);
}
