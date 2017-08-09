package com.asiainfo.meo.web.partner.model.vo;

import java.util.List;

public class UIContractPriceInfoVO
{
    private Long                     partnerId;
    
    private Long                     contractId;
    
    private String                   contractName;
    
    private Long                     templeteId;
    
    private String                   contractNo;
    
    private String                   buyers;
    
    private String                   sellers;
    
    private String                   signedPlace;
    
    private Long                     signedDate;
    
    private String                   contractUrl;
    
    private Integer                  statusCode;
    
    private String                   statusName;
    
    private Long                     validate;
    
    private Long                     expiredDate;
    
    private Long                     saleId;
    
    private Long                     operatorId;
    
    private List<UIContractPricesVO> contractPrices;
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public Long getContractId()
    {
        return contractId;
    }
    
    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
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
    
    public String getContractUrl()
    {
        return contractUrl;
    }
    
    public void setContractUrl(String contractUrl)
    {
        this.contractUrl = contractUrl;
    }
    
    public Integer getStatusCode()
    {
        return statusCode;
    }
    
    public void setStatusCode(Integer statusCode)
    {
        this.statusCode = statusCode;
    }
    
    public String getStatusName()
    {
        return statusName;
    }
    
    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }
    
    public Long getValidate()
    {
        return validate;
    }
    
    public void setValidate(Long validate)
    {
        this.validate = validate;
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
    
    public Long getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    public List<UIContractPricesVO> getContractPrices()
    {
        return contractPrices;
    }
    
    public void setContractPrices(List<UIContractPricesVO> contractPrices)
    {
        this.contractPrices = contractPrices;
    }
    
}
