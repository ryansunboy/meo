package com.asiainfo.meo.customer.profile.app.model.entity;

import java.sql.Timestamp;



import com.asiainfo.meo.common.core.validate.annotation.Enum;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;


public class Customer implements java.io.Serializable
{
    
    private static final long   serialVersionUID = -3950870537814760668L;
    
    public static final Integer LEVEL_ID         = 1;
    
    public static final Integer MNO_CODE_AIS     = 1;
    
    public static final Integer MNO_CODE_TRUE    = 2;
    
    public static final int STS_INACTIVATE    = 0;
    
    public static final int STS_ACTIVATE      = 1;
    
    public static final Integer STS_DEACTIVATE   = 2;
    
    private long                custId;
    private Integer             levelId;
    private String              inviteCode;
    private Timestamp           regTime;
    private Timestamp           modifyDate;
    private Timestamp           createDate;
    //private String              imgUrl;
    @Enum(value={""+STS_INACTIVATE, ""+STS_ACTIVATE}, groups={Update.class,Insert.class},message=CustomerErrorConstant.CUST_STATUS_IS_ERROR)
    private Integer             sts;
    private Timestamp           levelExpiredDate;
    private Timestamp           levelValidDate;
    
    public Timestamp getLevelValidDate()
    {
        return levelValidDate;
    }
    
    public void setLevelValidDate(Timestamp levelValidDate)
    {
        this.levelValidDate = levelValidDate;
    }
    
    public long getCustId()
    {
        return this.custId;
    }
    
    public void setCustId(long custId)
    {
        this.custId = custId;
    }
    
    public Integer getLevelId()
    {
        return this.levelId;
    }
    
    public void setLevelId(Integer levelId)
    {
        this.levelId = levelId;
    }
    
    public String getInviteCode()
    {
        return this.inviteCode;
    }
    
    public void setInviteCode(String inviteCode)
    {
        this.inviteCode = inviteCode;
    }
    
    public Timestamp getRegTime()
    {
        return this.regTime;
    }
    
    public void setRegTime(Timestamp regTime)
    {
        this.regTime = regTime;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
//    public String getImgUrl()
//    {
//        return this.imgUrl;
//    }
//    
//    public void setImgUrl(String imgUrl)
//    {
//        this.imgUrl = imgUrl;
//    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getLevelExpiredDate()
    {
        return this.levelExpiredDate;
    }
    
    public void setLevelExpiredDate(Timestamp levelExpiredDate)
    {
        this.levelExpiredDate = levelExpiredDate;
    }
    
//    public boolean isValid()
//    {
//        return STS_ACTIVATE== getSts();
//    }
//    
//    public boolean isInvalid()
//    {
//        return STS_DEACTIVATE== getSts();
//    }
//    
//    public boolean isSts_Valid()
//    {
//        return isValid()|| isInvalid();
//    }
    
    // public boolean isAis()
    // {
    // return MNO_CODE_AIS== getMnoCode();
    // }
    //
    // public boolean isTrue()
    // {
    // return MNO_CODE_TRUE== getMnoCode();
    // }
    
    // public boolean isMno_Id()
    // {
    // return isAis()|| isTrue();
    // }
    
}
