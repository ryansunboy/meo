package com.asiainfo.meo.prm.profile.app.model.entity;

import java.sql.Timestamp;

/**
 * @Description: TODO(this is partner)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author zhaozx
 * @Date 2015-2-11
 */
public class Partner implements java.io.Serializable
{
    private static final long serialVersionUID    = 5614682055270995750L;
    
    /** sts constant */
    public static final int   STS_INACTIVE        = 0;
    /** sts constant */
    public static final int   STS_ACTIVE          = 1;
    /** sts constant */
    public static final int   STS_DEACTIVE        = 2;
    
    /** partner type constant */
    public static final int   PARTNER_TYPE_RETAIL = 0;
    /** partner type constant */
    public static final int   PARTNER_TYPE_MNO    = 1;
    
    /** partner init level */
    public static final int   INIT_PARTNER_LEVEL  = 1;
    
    private long              partnerId;
    
    /** partner register time */
    private Timestamp         regTime;
    
    /** partner level */
    private int               level;
    
    /** partner status */
    // @Enum(groups={Update.class,Insert.class},value={""+STS_CREATE,""+STS_ACTIVE,""+STS_SUSPEND,""+STS_EXPIRED},message=PartnerProfileErrorConstant.PARTNER_STS_IS_ERROR)
    private int               sts;
    
    /** partner modify date */
    private Timestamp         modifyDate;
    
    /** partner create date */
    private Timestamp         createDate;
    
    /** partner type */
    // @Enum(groups={Update.class,Insert.class},value={""+PARTNER_TYPE_RETAIL,""+PARTNER_TYPE_MNO},message=PartnerProfileErrorConstant.PARTNER_TYPE_IS_ERROR)
    private int               partnerType;
    
    public long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public Timestamp getRegTime()
    {
        return regTime;
    }
    
    public void setRegTime(Timestamp regTime)
    {
        this.regTime = regTime;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
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
    
    public int getPartnerType()
    {
        return partnerType;
    }
    
    public void setPartnerType(int partnerType)
    {
        this.partnerType = partnerType;
    }
    
    public boolean isRetail()
    {
        return PARTNER_TYPE_RETAIL== getPartnerType();
    }
    
    public boolean isMNO()
    {
        return PARTNER_TYPE_MNO== getPartnerType();
    }
    
    /**
     * @Description: TODO(is partner type)
     * @return
     */
    public boolean isPartner()
    {
        return isRetail()|| isMNO();
    }
    
    /**
     * @Description: 判断是否是有效的partner
     * @Description: judge valid partner
     * @author zhaozx
     * @return
     */
    public boolean isValidPartner()
    {
        return STS_ACTIVE== getSts();
    }
    
    public String getStsName()
    {
        if (getSts()== Partner.STS_INACTIVE)
        {
            return "inactive";
        }
        else if (getSts()== Partner.STS_ACTIVE)
        {
            return "active";
        }
        else if (getSts()== Partner.STS_DEACTIVE)
        {
            return "deactive";
        }
        else
        {
            return "error status";
        }
    }
}
