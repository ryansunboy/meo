package com.asiainfo.meo.customer.profile.app.model.vo;

import java.util.Date;

/**
 * @ClassName: CustomerProfileVO
 * @Description: (修改Customer信息使用)
 * @author lill
 * @date 2015-3-19 上午09:00:55
 */
public class CustomerProfileVO
{
    public static final String BIRTHDAY_ERROR = "4002001020";
    
    private long               custId;
    
    private String             firstName;
    
    private String             middleName;
    
    private String             lastName;
    
    private String             address;
    
    private String             mobileNo;
    
    private int                mnoCode;
    
    private String             emailAddr;
    
    private int                countryCode;
    
    private int                gender;
    
    private Date               birthday;
    
    private int                languageCode;
    
    private int                proCode;
    
    private int                cityCode;
    
    private int                socialType;
    
    private String             avatarUrl;
    
    public long getCustId()
    {
        return custId;
    }
    
    public void setCustId(long custId)
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
    
    public int getCountryCode()
    {
        return countryCode;
    }
    
    public void setCountryCode(int countryCode)
    {
        this.countryCode = countryCode;
    }
    
    public int getGender()
    {
        return gender;
    }
    
    public void setGender(int gender)
    {
        this.gender = gender;
    }
    
    public Date getBirthday()
    {
        return birthday;
    }
    
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
    
    public int getLanguageCode()
    {
        return languageCode;
    }
    
    public void setLanguageCode(int languageCode)
    {
        this.languageCode = languageCode;
    }
    
    public int getProCode()
    {
        return proCode;
    }
    
    public void setProCode(int proCode)
    {
        this.proCode = proCode;
    }
    
    public int getCityCode()
    {
        return cityCode;
    }
    
    public void setCityCode(int cityCode)
    {
        this.cityCode = cityCode;
    }
    
    public int getSocialType()
    {
        return socialType;
    }
    
    public void setSocialType(int socialType)
    {
        this.socialType = socialType;
    }
    
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }
    
    public int getMnoCode()
    {
        return mnoCode;
    }
    
    public void setMnoCode(int mnoCode)
    {
        this.mnoCode = mnoCode;
    }
    
}
