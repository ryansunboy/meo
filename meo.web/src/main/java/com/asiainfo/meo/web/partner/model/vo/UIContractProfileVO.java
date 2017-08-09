package com.asiainfo.meo.web.partner.model.vo;

import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;


public class UIContractProfileVO
{
    @NotNull(groups = Insert.class)
    private Long   contractId;
    
    private String contractName;
    
    private Long   templeteId;
    
    private String contractNo;
    
    private Long validDate;
    
    private Long expiredDate;
    
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
