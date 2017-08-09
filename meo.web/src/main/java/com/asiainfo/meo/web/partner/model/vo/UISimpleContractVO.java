package com.asiainfo.meo.web.partner.model.vo;


public class UISimpleContractVO
{
    private Long contractId;
    
    private String  contractNo;
    
    private String  contractName;
    
    private Integer contractStatus;
    
    private Long    validDate;
    
    private Long    expiredDate;

    public Long getContractId()
    {
        return contractId;
    }

    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }

    public String getContractNo()
    {
        return contractNo;
    }

    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }

    public String getContractName()
    {
        return contractName;
    }

    public void setContractName(String contractName)
    {
        this.contractName = contractName;
    }

    public Integer getContractStatus()
    {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus)
    {
        this.contractStatus = contractStatus;
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
}
