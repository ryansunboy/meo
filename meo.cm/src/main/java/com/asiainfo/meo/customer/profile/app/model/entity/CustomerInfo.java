package com.asiainfo.meo.customer.profile.app.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;

public class CustomerInfo
{
    
    private long                id;
    
    @NotNull(groups = {Update.class, Insert.class })
    private long                custId;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String              firstName;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String              middleName;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String              lastName;
    
    @Length(max = 256, message = CustomerErrorConstant.CUST_EMAILADDR_LENGTH_LE_256)
    private String              address;
    
    @Length(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String              displayName;
    
    @Length(max = 256, message = CustomerErrorConstant.CUST_EMAILADDR_LENGTH_LE_256)
    private String              emailAddr;
    
    private Integer             countryCode;
    
    private Integer             gender;
    
    private Date                birthday;
    
    private Integer             languageCode;
    
    private Integer             cityCode;
    
    private Integer             provinceCode;
    
    private Timestamp           createDate;
    
    private Timestamp           modifyDate;
    
    private int                 sts;
    
    private String              avatarUrl;
    
    public static final Integer GENDER_MALE   = 1;
    
    public static final Integer GENDER_FEMALE = 2;
    
    public boolean isMale()
    {
        return GENDER_MALE== getGender();
    }
    
    public boolean isFemale()
    {
        return GENDER_FEMALE== getGender();
    }
    
    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

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

    public boolean isGender_Male()
    {
        return isMale()|| isFemale();
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
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public int getSts()
    {
        return sts;
    }
    
    public void setSts(int sts)
    {
        this.sts = sts;
    }
    
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }
    
}
