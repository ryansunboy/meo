package com.asiainfo.meo.prm.profile.app.model.vo;

/**
 * @Description: 提供给partner信息修改
 * @Description: partner info update
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-13 上午11:52:35
 * @version 1.0
 */
public class PartnerInfoVO
{
    /** partner id */
    private Long    partnerId;
    /** partner type */
    private Integer partnerType;
    /** partner level */
    private Integer level;
    /** partner company name */
    private String  companyName;
    /** partner company tax code */
    private String  taxCode;
    /** operation business code */
    private String  businessCode;
    /** partner email */
    private String  emailAddr;
    /** partner phone number */
    private String  msisdn;
    /** partner bill address */
    private String  billAddr;
    /** partner contact name */
    private String  contactName;
    /** partner contact phone number */
    private String  contactMsidn;
    /** partner contact email */
    private String  contactEmail;
    /** partner post code */
    private Long    postCode;
    /** partner country code */
    private Integer countryCode;
    /** partner city code */
    private Integer cityCode;
    /** partner province code */
    private Integer provinceCode;
    
    private String  avatarUrl;
    
    private String registrationForm;
    
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
    
    public Integer getLevel()
    {
        return level;
    }
    
    public void setLevel(Integer level)
    {
        this.level = level;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    public String getTaxCode()
    {
        return taxCode;
    }
    
    public void setTaxCode(String taxCode)
    {
        this.taxCode = taxCode;
    }
    
    public String getBusinessCode()
    {
        return businessCode;
    }
    
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
    }
    
    public String getEmailAddr()
    {
        return emailAddr;
    }
    
    public void setEmailAddr(String emailAddr)
    {
        this.emailAddr = emailAddr;
    }
    
    public String getMsisdn()
    {
        return msisdn;
    }
    
    public void setMsisdn(String msisdn)
    {
        this.msisdn = msisdn;
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
    
    public String getContactMsidn()
    {
        return contactMsidn;
    }
    
    public void setContactMsidn(String contactMsidn)
    {
        this.contactMsidn = contactMsidn;
    }
    
    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public Long getPostCode()
    {
        return postCode;
    }
    
    public void setPostCode(Long postCode)
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
    
    public Integer getCityCode()
    {
        return cityCode;
    }
    
    public void setCityCode(Integer cityCode)
    {
        this.cityCode = cityCode;
    }
    
    public Integer getProvinceCode()
    {
        return provinceCode;
    }
    
    public void setProvinceCode(Integer provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public String getRegistrationForm()
    {
        return registrationForm;
    }

    public void setRegistrationForm(String registrationForm)
    {
        this.registrationForm = registrationForm;
    }
    
}
