package com.asiainfo.meo.web.partner.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;

public class UIContractVO
{
    private Long   contractId;
    
    @NotNull(groups={Insert.class})
    private Long   partnerId;
    
    @NotEmpty(groups={Insert.class})
    private String contractName;
    
    private Long   templeteId;
    
    private String contractNo;
    
    private String contractUrl;
    
    private Integer status;
    
    @NotEmpty(groups={Insert.class})
    private String buyers;
    
    @NotEmpty(groups={Insert.class})
    private String sellers;
    
    @NotEmpty(groups={Insert.class})
    private String signedPlace;
    
    private Long signedDate;
    
    @NotNull(groups = Insert.class)
    private Long validDate;
    @NotNull(groups = Insert.class)
    private Long expiredDate;
    
    private Long   saleId;
    
    private Long   operatorId;
     
    public Long getContractId()
    {
        return contractId;
    }
    
    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public String getContractName()
    {
        return contractName;
    }
    
    public void setContractName(String contractName)
    {
        this.contractName = contractName;
    }
    
    public Long getTempleteId()
    {
        return templeteId;
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
        return buyers;
    }
    
    public void setBuyers(String buyers)
    {
        this.buyers = buyers;
    }
    
    public String getSellers()
    {
        return sellers;
    }
    
    public void setSellers(String sellers)
    {
        this.sellers = sellers;
    }
    
    public String getSignedPlace()
    {
        return signedPlace;
    }
    
    public void setSignedPlace(String signedPlace)
    {
        this.signedPlace = signedPlace;
    }
    
    public Long getSignedDate()
    {
        return signedDate;
    }
    
    public void setSignedDate(Long signedDate)
    {
        this.signedDate = signedDate;
    }
    
    public Long getValidDate()
    {
        return validDate;
    }
    
    public void setValidDate(Long validDate)
    {
        this.validDate = validDate;
    }
    
    public Long getExpiredDate()
    {
        return expiredDate;
    }
    
    public void setExpiredDate(Long expiredDate)
    {
        this.expiredDate = expiredDate;
    }
    
    public Long getSaleId()
    {
        return saleId;
    }
    
    public void setSaleId(Long saleId)
    {
        this.saleId = saleId;
    }

    public String getContractUrl()
    {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl)
    {
        this.contractUrl = contractUrl;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
}
