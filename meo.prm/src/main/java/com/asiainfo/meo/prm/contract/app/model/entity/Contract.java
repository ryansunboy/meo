package com.asiainfo.meo.prm.contract.app.model.entity;

// Generated 2015-2-27 15:47:32 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

/**
 * CmContract generated by hbm2java
 */
public class Contract implements java.io.Serializable
{
    
    private static final long serialVersionUID  = -3383423328371819053L;
    
    public static final int   STS_INACTIVE      = 0;
    public static final int   STS_ACTIVE        = 1;
    public static final int   STS_DEACTIVE      = 2;
    public static final int   STS_EXPIREDACTIVE = 3;
    
    public static final int   STS_VALID               = 1;    
    public static final int   STS_INVALID             = 2;
    
    public static final String   CONTRACT_STS = "28";
    /** contract id */
    private long              contractId;
    
    /** partner id */
    // @NotNull(groups = {Update.class, Insert.class }, message = PartnerContractErrorConstant.PARTNER_ID_IS_EMPTY)
    private Long              partnerId;
    
    /** contract name */
    // @NotEmpty(groups = {Update.class, Insert.class }, message = PartnerContractErrorConstant.CONTRACT_NAME_IS_EMPTY)
    private String            contractName;
    
    /** templete id to printing contract */
    private Long              templeteId;
    
    /** contract number */
    // @NotNull(groups = {Update.class, Insert.class }, message = PartnerContractErrorConstant.CONTRACT_NO_IS_EMPTY)
    private String              contractNo;
    
    /** buyer */
    // @NotEmpty(groups = {Update.class, Insert.class }, message = PartnerContractErrorConstant.CONTRACT_BUYER_IS_EMPTY)
    private String            buyers;
    
    /** seller */
    // @NotEmpty(groups = {Update.class, Insert.class }, message = PartnerContractErrorConstant.CONTRACT_SELLER_IS_EMPTY)
    private String            sellers;
    
    /** signed place */
    // @NotEmpty(groups = {Update.class, Insert.class }, message = PartnerContractErrorConstant.CONTRACT_SIGNED_PLACE_IS_EMPTY)
    private String            signedPlace;
    
    /** signed date */
    private Timestamp         signedDate;
    
    /** contract url */
    private String            contractUrl;
    
    /** contract sts */
    // @Enum(groups={Update.class,Insert.class},value={""+STS_VALID,""+STS_INVALID},message=PartnerContractErrorConstant.CONTRACT_STATUS_IS_ERRROR)
    private Integer           sts;
    
    /** valid date */
    private Timestamp         validDate;
    
    /** expired date */
    private Timestamp         expiredDate;
    
    /** sale id */
    @NotNull(groups = {Update.class, Insert.class })
    private Long              operatorId;
    
    
    public Long getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    public long getContractId()
    {
        return this.contractId;
    }
    
    public void setContractId(long contractId)
    {
        this.contractId = contractId;
    }
    
    public Long getPartnerId()
    {
        return this.partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public String getContractName()
    {
        return this.contractName;
    }
    
    public void setContractName(String contractName)
    {
        this.contractName = contractName;
    }
    
    public Long getTempleteId()
    {
        return this.templeteId;
    }
    
    public void setTempleteId(Long templeteId)
    {
        this.templeteId = templeteId;
    }
    
   
    
    public String getContractNo()
    {
        return contractNo;
    }

    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }

    public String getBuyers()
    {
        return this.buyers;
    }
    
    public void setBuyers(String buyers)
    {
        this.buyers = buyers;
    }
    
    public String getSellers()
    {
        return this.sellers;
    }
    
    public void setSellers(String sellers)
    {
        this.sellers = sellers;
    }
    
    public String getSignedPlace()
    {
        return this.signedPlace;
    }
    
    public void setSignedPlace(String signedPlace)
    {
        this.signedPlace = signedPlace;
    }
    
    public Timestamp getSignedDate()
    {
        return this.signedDate;
    }
    
    public void setSignedDate(Timestamp signedDate)
    {
        this.signedDate = signedDate;
    }
    
    public String getContractUrl()
    {
        return this.contractUrl;
    }
    
    public void setContractUrl(String contractUrl)
    {
        this.contractUrl = contractUrl;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getValidDate()
    {
        return this.validDate;
    }
    
    public void setValidDate(Timestamp validDate)
    {
        this.validDate = validDate;
    }
    
    public Timestamp getExpiredDate()
    {
        return this.expiredDate;
    }
    
    public void setExpiredDate(Timestamp expiredDate)
    {
        this.expiredDate = expiredDate;
    }
  
}
