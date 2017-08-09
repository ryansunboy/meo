package com.asiainfo.meo.web.partner.model.vo;


public class UIContractQueryVO
{
    private Long contractId;
    
    private Long validDate;
    
    private Long expiredDate;
    
    private String contractNo;
    
    private String contractName;
    
    private Integer pageSize;
    
    private Integer pageNo;
    
    private Long partnerId;

    public Long getContractId()
    {
        return contractId;
    }

    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
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

    public Integer getPageSize()
    {
        pageSize = pageSize == null || pageSize == 0 ?10:pageSize;
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getPageNo()
    {
        pageNo = pageNo == null || pageNo == 0 ?1:pageNo;
        return pageNo;
    }

    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }

    public Long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    } 
    
    
}
