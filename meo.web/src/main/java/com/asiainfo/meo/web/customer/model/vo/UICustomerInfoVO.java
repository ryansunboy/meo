package com.asiainfo.meo.web.customer.model.vo;

import javax.validation.constraints.Size;

import com.asiainfo.meo.customer.profile.app.constant.CustomerErrorConstant;

public class UICustomerInfoVO
{
    
    private Long    custId;
    
    @Size(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String  firstName;
    
    @Size(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String  middleName;
    
    @Size(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String  lastName;
    
    @Size(max = 256, message = CustomerErrorConstant.CUST_EMAILADDR_LENGTH_LE_256)
    private String  address;
    
    @Size(max = 128, message = CustomerErrorConstant.CUST_NAME_LENGTH_LE_128)
    private String  displayName;
    
    private String  mobileNo;
    
    private Integer mnoId;
    
    private String  mnoCode;
    
    @Size(max = 256, message = CustomerErrorConstant.CUST_EMAILADDR_LENGTH_LE_256)
    private String  emailAddr;
    
    private Integer countryCode;
    
    private Integer gender;
    
    private String  genderName;
    
    private String  birthday;
    
    private Integer languageId;
    
    private String  languageCode;
    
    private Integer provCode;
    
    private Integer cityCode;
    
    private String  avatarUrl;
    
    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public Long getCustId()
    {
        return custId;
    }
    
    public void setCustId(Long custId)
    {
        this.custId = custId;
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

    public String getMobileNo()
    {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }
    
    public String getEmailAddr()
    {
        return emailAddr;
    }
    
    public void setEmailAddr(String emailAddr)
    {
        this.emailAddr = emailAddr;
    }
    
    public Integer getCountryCode()
    {
        return countryCode;
    }
    
    public void setCountryCode(Integer countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public String getBirthday()
    {
        return birthday;
    }
    
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }
    
    public Integer getProvCode()
    {
        return provCode;
    }
    
    public void setProvCode(Integer provCode)
    {
        this.provCode = provCode;
    }
    
    public Integer getMnoId()
    {
        return mnoId;
    }
    
    public void setMnoId(Integer mnoId)
    {
        this.mnoId = mnoId;
    }
    
    public String getMnoCode()
    {
        return mnoCode;
    }
    
    public void setMnoCode(String mnoCode)
    {
        this.mnoCode = mnoCode;
    }
    
    public Integer getGender()
    {
        return gender;
    }
    
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }
    
    // public Integer getGenderId()
    // {
    // return genderId;
    // }
    // public void setGenderId(Integer genderId)
    // {
    // this.genderId = genderId;
    // }
    public String getGenderName()
    {
        return genderName;
    }
    
    public void setGenderName(String genderName)
    {
        this.genderName = genderName;
    }
    
    public Integer getLanguageId()
    {
        return languageId;
    }
    
    public void setLanguageId(Integer languageId)
    {
        this.languageId = languageId;
    }
    
    public String getLanguageCode()
    {
        return languageCode;
    }
    
    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }
    
    public Integer getCityCode()
    {
        return cityCode;
    }
    
    public void setCityCode(Integer cityCode)
    {
        this.cityCode = cityCode;
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
