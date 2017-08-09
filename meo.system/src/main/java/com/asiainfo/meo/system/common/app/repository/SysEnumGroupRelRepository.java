package com.asiainfo.meo.system.common.app.repository;

import java.util.List;

import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupRel;

public interface SysEnumGroupRelRepository
{
    /**
     *  add the sysEnumGroupRel
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param sysEnumGroupRel
     */
    public void addSysEnumGroupRel(SysEnumGroupRel sysEnumGroupRel);
   
    /**
     * update the sysEnumGroupRel
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param sysEnumGroupRel
     */
    public void updateSysEnumGroupRel(SysEnumGroupRel sysEnumGroupRel);
    
    /**
     * get the sysEnumGroupRel by relId
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param relId
      * @return
     */
    public SysEnumGroupRel getSysEnumGroupRel(Long relId);
    
    /**
     * get the sysEnumGroupRel list by enumId and groupId
      * @Description: (用中文描述一下这个方法)
      * @Description: (English description)
      * @modifyReason: 
      * @author zhengzy
      * @param enumId
      * @param groupId
      * @return
     */
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumIdAndGroupId(Long enumId,Long groupId);
    
    public List<SysEnumGroupRel> getSysEnumGroupRelListByGroupIds(List<Long> groupIds);
    
    public List<SysEnumGroupRel> getSysEnumGroupRelListByEnumId(Long enumId);
} 
