 package com.asiainfo.meo.system.authority.app.bo;                                                                                                                                                                                                                                                                       

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.system.authority.app.model.entity.SysRoleUser;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserQueryConditionVO;
import com.asiainfo.meo.system.authority.app.model.vo.SystemUserVO;

                                                                                                                                                                                                                                                                                              
 public interface StaffInfoBO
{
     /**
      * @Description: 保存用户信息
      * @Description: save systemUser information
      * @author zhaozx
      * @param systemUser
      */
     SystemUserVO saveSystemUser(SystemUserVO systemUser);
     
    /**
      * @Description: 查询用户信息
      * @Description: get systemUser information
      * @author zhaozx
      * @param userId
      * @return
      */
     
     SystemUserVO getSystemUser(Long userId);
    
    /**
     * 
      * @Description: 修改用户信息
      * @Description: update the systemUser
      * @modifyReason: 
      * @author zhengzy
      * @param systemUser
      * @return
     */
     SystemUserVO updateSystemUser(SystemUserVO systemUser);
    
    /**
     * 
      * @Description: 删除用户信息
      * @Description: delete systemUser by userId
      * @modifyReason: 
      * @author zhengzy
      * @param userId
     */
    void deleteSystemUserByuserId(Long userId);
    
    /**
     * 
      * @Description: 查询系统用户列表
      * @Description: query system user list
      * @modifyReason: 
      * @author zhengzy
      * @param pageNo
      * @param pageSize
      * @return
     */
    //public List<SystemUserVO> getSystemUserVOList(final Integer pageNo, final Integer pageSize,String userName,Long userId);
    public PageInfo<SystemUserVO> getSystemUserVOPageInfo(SystemUserQueryConditionVO systemUserQueryCodition);
    
    public List<SysRoleUser> getSysRoleUserByRoleId(Long roleId);
}
