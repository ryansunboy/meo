package com.asiainfo.meo.web.customer.model.vo;

public class UISocialInfoReqVO {

	private String socialAccount;
	private Integer socialType;
	private String socialName;
	private String mobileNo;
	private String emailAddr;
	private Integer countryCode;
	private Integer gender;
	private String birthday;
	private Integer languageCode;
	private Integer provCode;
	private Integer cityCode;

	public String getSocialAccount() {
		return socialAccount;
	}

	public void setSocialAccount(String socialAccount) {
		this.socialAccount = socialAccount;
	}

	public Integer getSocialType() {
		return socialType;
	}

	public void setSocialType(Integer socialType) {
		this.socialType = socialType;
	}

	public String getSocialName() {
		return socialName;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(Integer languageCode) {
		this.languageCode = languageCode;
	}

	public Integer getProvCode() {
		return provCode;
	}

	public void setProvCode(Integer provCode) {
		this.provCode = provCode;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

}
