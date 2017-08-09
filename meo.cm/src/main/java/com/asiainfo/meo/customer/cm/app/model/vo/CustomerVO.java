package com.asiainfo.meo.customer.cm.app.model.vo;

import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerBundleInfo;
import com.asiainfo.meo.customer.profile.app.model.entity.CustomerInfo;

public class CustomerVO
{
    
    public static final int    MEO_SOCIAL_TYPE       = 1;
    
    public static final int    FACEBOOK_SOCIAL_TYPE  = 2;
    
    public static final int    TWITTER_SOCIAL_TYPE   = 3;
    
    public static final int    PHONEBOOK_SOCIAL_TYPE = 4;
    
    public static final int    MNO_ENUM_TYPE         = 1;
   
    public static final int    GENDER_ENUM_TYPE      = 16;
  
    public static final int    LANGUAGE__ENUM_TYPE   = 14;
    
    private Customer           customer;
    
    private CustomerInfo       customerInfo;
    
    private CustomerBundleInfo customerBundleInfo;
    
    private LevelDef           levelDef;
    
    private Long               coins;
    
    private Long               experience;
    
    private Integer                socialType;
    
    private String             mnoCode;
    
    private String             genderName;
    
    private String             languageCode;
    
    public String getMnoCode()
    {
        return mnoCode;
    }
    
    public void setMnoCode(String mnoCode)
    {
        this.mnoCode = mnoCode;
    }
    
    public String getGenderName()
    {
        return genderName;
    }
    
    public void setGenderName(String genderName)
    {
        this.genderName = genderName;
    }
    
    public String getLanguageCode()
    {
        return languageCode;
    }
    
    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }
    
    public Integer getSocialType()
    {
        return socialType;
    }
    
    public void setSocialType(Integer socialType)
    {
        this.socialType = socialType;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public CustomerInfo getCustomerInfo()
    {
        return customerInfo;
    }
    
    public void setCustomerInfo(CustomerInfo customerInfo)
    {
        this.customerInfo = customerInfo;
    }
    
    public LevelDef getLevelDef()
    {
        return levelDef;
    }
    
    public void setLevelDef(LevelDef levelDef)
    {
        this.levelDef = levelDef;
    }
    
    public Long getCoins()
    {
        return coins;
    }
    
    public void setCoins(Long coins)
    {
        this.coins = coins;
    }
    
    public Long getExperience()
    {
        return experience;
    }
    
    public void setExperience(Long experience)
    {
        this.experience = experience;
    }
    
    public CustomerBundleInfo getCustomerBundleInfo()
    {
        return customerBundleInfo;
    }
    
    public void setCustomerBundleInfo(CustomerBundleInfo customerBundleInfo)
    {
        this.customerBundleInfo = customerBundleInfo;
    }
    
}
