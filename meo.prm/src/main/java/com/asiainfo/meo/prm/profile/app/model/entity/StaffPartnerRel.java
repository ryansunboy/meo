package com.asiainfo.meo.prm.profile.app.model.entity;

// Generated 2015-3-25 11:00:06 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

public class StaffPartnerRel implements java.io.Serializable
{
    private static final long serialVersionUID = -4562984128648259234L;
    
    
    /** sts constant */
    public static final int   STS_VALID        = 1;
    /** sts constant */
    public static final int   STS_INVALID      = 2;
    
    private long      id;
    private long      partnerId;
    private Long      staffId;
    private int       sts;
    private Timestamp modifyDate;
    private Timestamp createDate;
    private Long creator;
    private Long owner;
    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    public long getPartnerId()
    {
        return partnerId;
    }
    public void setPartnerId(long partnerId)
    {
        this.partnerId = partnerId;
    }
    public Long getStaffId()
    {
        return staffId;
    }
    public void setStaffId(Long staffId)
    {
        this.staffId = staffId;
    }
    public int getSts()
    {
        return sts;
    }
    public void setSts(int sts)
    {
        this.sts = sts;
    }
    public Timestamp getModifyDate()
    {
        return modifyDate;
    }
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    public Timestamp getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    public Long getCreator()
    {
        return creator;
    }
    public void setCreator(Long creator)
    {
        this.creator = creator;
    }
    public Long getOwner()
    {
        return owner;
    }
    public void setOwner(Long owner)
    {
        this.owner = owner;
    }
}
