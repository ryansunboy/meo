package com.asiainfo.meo.web.partner.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.prm.profile.app.constant.PartnerProfileErrorConstant;

public class UIPartnerInfoVO
{
    @NotNull(groups = {Update.class })
    private Long    partnerId;
    
    @NotNull(groups = {Insert.class })
    private Integer partnerType;
    
    @NotEmpty(groups = {Insert.class })
    private String  companyName;
    
    @NotEmpty(groups = {Insert.class })
    private String  taxId;
    
    @NotEmpty(groups = Insert.class)
    private String  businessId;
    
    @NotEmpty(groups = Insert.class)
    @Email(groups = {Insert.class, Update.class }, message = PartnerProfileErrorConstant.PARTNER_EMAIL_IS_ERROR)
    private String  emailAddr;
    
    @NotEmpty(groups = Insert.class)
    private String  mobileNo;
    
    @NotEmpty(groups = Insert.class)
    private String  billAddr;
    
    @NotEmpty(groups = Insert.class)
    private String  contactName;
    
    @NotEmpty(groups = Insert.class)
    private String  contactMobileNo;
    
    @NotEmpty(groups = Insert.class)
    @Email(groups = {Insert.class, Update.class }, message = PartnerProfileErrorConstant.PARTNER_CONTACT_EMAIL_IS_ERROR)
    private String  contactEmail;
    
    private Integer postCode;
    
    private Integer countryCode;
    
    private Integer provCode;
    
    private Integer cityCode;
    
    private String  avatarUrl;
    
    private String  registerFileUrl;
    
    private Integer sts;
    
    private Long    createDate;
    
    private String  description;
    
    private String  registrationForm;
    
    private Long    contractId;
    
    private String  contractNo;
    
    private String  partnerTypeName;
    
    public String getRegisterFileUrl()
    {
        return registerFileUrl;
    }
    
    public void setRegisterFileUrl(String registerFileUrl)
    {
        this.registerFileUrl = registerFileUrl;
    }
    
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
    
    public Long getPartnerId()
    {
        return partnerId;
    }
    
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
    
    public Integer getPartnerType()
    {
        return partnerType;
    }
    
    public void setPartnerType(Integer partnerType)
    {
        this.partnerType = partnerType;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    public String getTaxId()
    {
        return taxId;
    }
    
    public void setTaxId(String taxId)
    {
        this.taxId = taxId;
    }
    
    public String getBusinessId()
    {
        return businessId;
    }
    
    public void setBusinessId(String businessId)
    {
        this.businessId = businessId;
    }
    
    public String getEmailAddr()
    {
        return emailAddr;
    }
    
    public void setEmailAddr(String emailAddr)
    {
        this.emailAddr = emailAddr;
    }
    
    public String getMobileNo()
    {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }
    
    public String getBillAddr()
    {
        return billAddr;
    }
    
    public void setBillAddr(String billAddr)
    {
        this.billAddr = billAddr;
    }
    
    public String getContactName()
    {
        return contactName;
    }
    
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }
    
    public String getContactMobileNo()
    {
        return contactMobileNo;
    }
    
    public void setContactMobileNo(String contactMobileNo)
    {
        this.contactMobileNo = contactMobileNo;
    }
    
    public Integer getPostCode()
    {
        return postCode;
    }
    
    public void setPostCode(Integer postCode)
    {
        this.postCode = postCode;
    }
    
    public Integer getCountryCode()
    {
        return countryCode;
    }
    
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public Integer getProvCode()
    {
        return provCode;
    }
    
    public void setProvCode(Integer provCode)
    {
        this.provCode = provCode;
    }
    
    public Integer getCityCode()
    {
        return cityCode;
    }
    
    public void setCityCode(Integer cityCode)
    {
        this.cityCode = cityCode;
    }
    
    public String getContactEmail()
    {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }
    
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Long getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Long createDate)
    {
        this.createDate = createDate;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getRegistrationForm()
    {
        return registrationForm;
    }
    
    public void setRegistrationForm(String registrationForm)
    {
        this.registrationForm = registrationForm;
    }
    
    public String getPartnerTypeName()
    {
        return partnerTypeName;
    }
    
    public void setPartnerTypeName(String partnerTypeName)
    {
        this.partnerTypeName = partnerTypeName;
    }
}
