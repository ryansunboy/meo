package com.asiainfo.meo.prm.profile.app.model.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.prm.define.PrmErrorCodeDefine;
import com.asiainfo.meo.prm.profile.app.constant.PartnerProfileErrorConstant;

/**
 * @Description: TODO(this is partner information)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author zhaozx
 * @Date 2015-2-11
 */
public class PartnerInfo implements java.io.Serializable
{
    
    private static final long serialVersionUID = -288359568236442533L;
    /** partner information id */
    private long              id;
    
    /** partner id from Partner class */
    private long              partnerId;
    
    /** partner company name */
    @NotEmpty(groups = {Update.class, Insert.class })
    private String            companyName;
    
    /** partner company tax code */
    @NotEmpty(groups = {Insert.class })
    private String            taxCode;
    
    /** operation business code */
    @NotEmpty(groups = {Insert.class })
    private String            businessCode;
    
    /** partner email */
    @NotEmpty(groups = {Update.class, Insert.class })
    @Email(groups = {Update.class, Insert.class }, message = PartnerProfileErrorConstant.PARTNER_EMAIL_IS_ERROR  )
    private String            emailAddr;
    
    /** partner phone number */
    @NotEmpty(groups = {Update.class, Insert.class })
    private String            msisdn;
    
    /** partner bill address */
    private String            billAddr;
    
    /** partner valid date */
    private Timestamp         validDate;
    
    /** partner expire date */
    private Timestamp         expiredDate;
    
    /** partner contact name */
    @NotEmpty(groups = {Insert.class,Update.class})
    private String            contactName;
    
    /** partner contact phone number */
    @NotEmpty(groups = {Insert.class,Update.class})
    private String            contactMsidn;
    
    /** partner confirm email */
    @NotEmpty(groups = {Insert.class,Update.class})
    @Email(groups={Insert.class,Update.class},message=PartnerProfileErrorConstant.PARTNER_CONTACT_EMAIL_IS_ERROR)
    private String            contactEmail;
    
    /** partner post code */
    private Long              postCode;
    
    /** partner country code */
    private Integer               countryCode;
    
    /** partner city code */
    private Integer               cityCode;
    
    /** partner province code */
    private Integer               provinceCode;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(long partnerId)
    {
        this.partnerId = partnerId;
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

    public Timestamp getValidDate()
    {
        return validDate;
    }

    public void setValidDate(Timestamp validDate)
    {
        this.validDate = validDate;
    }

    public Timestamp getExpiredDate()
    {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate)
    {
        this.expiredDate = expiredDate;
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
    
    
}
