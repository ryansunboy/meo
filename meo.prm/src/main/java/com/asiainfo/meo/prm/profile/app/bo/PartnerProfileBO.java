package com.asiainfo.meo.prm.profile.app.bo;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerInfoVO;
import com.asiainfo.meo.prm.profile.app.model.vo.PartnerProfileVO;
import com.asiainfo.meo.prm.profile.app.model.vo.StaffInfoVO;

/**
 * @Description: partner娣団剝浼匓O
 * @Description: partner profile BO
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-12 娑撳宕�7:24:01
 * @version 1.0
 */
public interface PartnerProfileBO
{
    
    /**
      * @Description: 娣囨繂鐡╬artner profile
      * @Description: save partner profile
      * @author zhaozx
      * @param partnerProfile
      * @param saleId
      */
    void savePartnerProfile(PartnerProfileVO partnerProfile,Long saleId);
    
    /**
     * @Description: 閼惧嘲绶眕artner娣団剝浼�
     * @Description: get partner profile
     * @author zhaozx
     * @param partnerId
     * @return
     */
    PartnerProfileVO getPartnerProfile(long partnerId);
    
    /**
     * @Description: 濞夈劑鏀artner
     * @Description: deactivate partner
     * @author zhaozx
     * @param partnerId partner id
     * @param modifyDate modify date
     */
    void deletePartner(long partnerId);
    
    /**
     * @Description: 娣囶喗鏁奸悩鑸碉拷
     * @Description: update status
     * @author zhaozx
     * @param partnerId partner id
     * @param oldSts old status
     * @param newSts new status
     */
    void updatePartnerSts(long partnerId, int oldSts, int newSts);
    
    /**
     * @Description: 娣囶喗鏁紁artner 閸滐拷partner information
     * @Description: update partner and partner information
     * @author zhaozx
     * @param partnerInfoVO partner info
     */
    void updatePartnerProfile(PartnerInfoVO partnerInfoVO);
    
   
    /**
      * @Description: 閺屻儴顕梡artner娣団剝浼�
      * @Description: select partner info
      * @author zhaozx
      * @param partnerCondition
      * @param pageSize
      * @param pageNo
      * @return
      */
    List<PartnerProfileVO>   getPartnerInfo(List<PartnerInfoVO> partnerCondition, Integer pageSize, Integer pageNo);
    
  
    /**
      * @Description: 閺屻儴顕梡artner list
      * @Description:  select partner list
      * @author zhaozx
      * @param saleId
      * @param partnerId
      * @param companyName
      * @param pageSize
      * @param pageNo
      * @return
      */
    PageInfo<PartnerProfileVO> getPartnerList(Long saleId,Long partnerId, String companyName, Integer pageSize, Integer pageNo);
    
    /**
      * @Description: 閼惧嘲绶眕artner娣団剝浼�
      * @Description: get partner info
      * @author zhaozx
      * @param partnerId
      * @return
      */
    PartnerInfo getPartnerInfo(long partnerId);
    
    /**
      * @Description: 濡拷鐓artner閺勵垰鎯侀弰顖涙箒閺佸牏娈�
      * @Description: check partner valid
      * @author zhaozx
      * @param partnerId
      * @return
      */
    boolean checkPartnerValid(Long partnerId);
    /**
     * 
     * @Description: (閺嶈宓乸artnerId閼惧嘲褰噋artner)
     * @Description: (get partner by partnerId)
     * @modifyReason: 
     * @author zhengzy
     * @param partnerId
    */
    Partner getPartner(long partnerId);
    /**
     * 
      * @Description: (婵″倹鐏塸artnerId娑撶皠ull鐏忚鲸鐓＄拠銏犲毉閹碉拷婀侀惃鍒綼rtner娣団剝浼�
      * @Description: (if partnerid is null then get all partner infomation)
      * @modifyReason: 
      * @author zhoujj
      * @param partnerId
      * @return
     */
    List<PartnerInfoVO> getAllPartnerInfo(Long partnerId);
    
    public void updateStaff(StaffInfoVO staff);
    
    StaffPartnerRel getStaffPartnerRel(Long partnerId);

    PageInfo<PartnerProfileVO> getAllPartnerList(Long partnerId, String companyName, Integer pageSize, Integer pageNo);
}