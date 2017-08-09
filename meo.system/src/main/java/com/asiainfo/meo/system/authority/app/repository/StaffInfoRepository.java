 package com.asiainfo.meo.system.authority.app.repository;                                                                                                                                                                                                                                                                       

import java.util.List;
import java.util.Set;

import com.asiainfo.meo.system.authority.app.model.entity.StaffInfo;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;

                                                                                                                                                                                                                                                                                             
 public interface StaffInfoRepository
{
     /**
      * @Description: 查询员工信息
      * @Description: get Staff information
      * @author zhaozx
      * @param staffId
      * @return
      */
    StaffInfo getStaffInfo(Long staffId);
    
    /**
      * @Description: 保存员工信息
      * @Description: save staff information
      * @author zhaozx
      * @param staffInfo
      */
    StaffInfo saveStaffInfo(StaffInfo staffInfo);
    
    /**
     * 
      * @Description: 修改员工信息
      * @Description: update the staffInfo
      * @modifyReason: 
      * @author zhengzy
      * @param staff
      * @return
     */
    StaffInfo updateStaffInfo(StaffInfo staff);
    
    /**
     * 
      * @Description: 删除员工信息
      * @Description: delete staffinfo by staffId
      * @modifyReason: 
      * @author zhengzy
      * @param staffId
     */
    void deleteStaffInfoByStaffId(Long staffId);
    
    /**
     * 
      * @Description: 查询用户信息集合
      * @Description: query staffInfo list
      * @modifyReason: 
      * @author zhengzy
      * @param pageNo
      * @param pageSize
      * @return
     */
    List<StaffInfo> getStaffInfoList(SystemUserQueryConditionVO systemUserQueryCodition,Set<Long> queryUserIds,int pageSize,int pageNo);
    
    int getStaffInfoListTotalSize(SystemUserQueryConditionVO systemUserQueryCodition,Set<Long> queryUserIds);
    
    List<SysRoleUser> getSysRoleUserByUserId(Long userId);
    
    List<SysRoleUser> getSysRoleUserByRoleId(Long roleId);
}
