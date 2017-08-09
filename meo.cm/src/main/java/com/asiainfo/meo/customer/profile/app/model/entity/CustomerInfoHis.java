package com.asiainfo.meo.customer.profile.app.model.entity;

// Generated 2015-3-3 16:45:40 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;

/**
 * CmCustomerInfoHis generated by hbm2java
 */
public class CustomerInfoHis implements java.io.Serializable
{
    
    private static final long serialVersionUID = -6103598011435920401L;
    
    private long              id;
    
    @NotNull(groups = {Update.class, Insert.class })
    private long              custId;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String            firstName;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String            middleName;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String            lastName;
    
    @Length(max = 256, message = CustomerErrorConstant.CUST_EMAILADDR_LENGTH_LE_256)
    private String            address;
    
    @NotNull(groups = {Update.class, Insert.class })
    private String            msisdn;
    
    private String            emailAddr;
    
    private Integer           countryCode;
    
    private Integer           gender;
    
    private Date              birthday;
    
    private Integer           languageCode;
    
    private Integer           cityCode;
    
    private Integer           provinceCode;
    
    @NotNull(groups = {Update.class, Insert.class })
    private Timestamp         expireDate;
    
    @NotNull(groups = {Update.class, Insert.class })
    private Timestamp         validDate;
    
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public long getId()
    {
        return this.id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public long getCustId()
    {
        return this.custId;
    }
    
    public void setCustId(long custId)
    {
        this.custId = custId;
    }
    
    public String getMsisdn()
    {
        return this.msisdn;
    }
    
    public void setMsisdn(String msisdn)
    {
        this.msisdn = msisdn;
    }
    
    public String getEmailAddr()
    {
        return this.emailAddr;
    }
    
    public void setEmailAddr(String emailAddr)
    {
        this.emailAddr = emailAddr;
    }
    
    public Integer getCountryCode()
    {
        return this.countryCode;
    }
    
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public Integer getGender()
    {
        return this.gender;
    }
    
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }
    
    public Date getBirthday()
    {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
    
    public Integer getLanguageCode()
    {
        return this.languageCode;
    }
    
    public void setLanguageCode(Integer languageCode)
    {
        this.languageCode = languageCode;
    }
    
    public Integer getCityCode()
    {
        return this.cityCode;
    }
    
    public void setCityCode(Integer cityCode)
    {
        this.cityCode = cityCode;
    }
    
    public Integer getProvinceCode()
    {
        return this.provinceCode;
    }
    
    public void setProvinceCode(Integer provinceCode)
    {
        this.provinceCode = provinceCode;
    }
    
    public Timestamp getExpireDate()
    {
        return this.expireDate;
    }
    
    public void setExpireDate(Timestamp expireDate)
    {
        this.expireDate = expireDate;
    }
    
    public Timestamp getValidDate()
    {
        return this.validDate;
    }
    
    public void setValidDate(Timestamp validDate)
    {
        this.validDate = validDate;
    }
    
}
