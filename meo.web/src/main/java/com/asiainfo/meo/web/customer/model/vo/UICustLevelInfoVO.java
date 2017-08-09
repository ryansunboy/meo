package com.asiainfo.meo.web.customer.model.vo;

public class UICustLevelInfoVO {

	private Integer levelId;
	private String levelName;
	private String levelUrl;
	private long validDate;
	private long expiredDate;
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelUrl() {
		return levelUrl;
	}
	public void setLevelUrl(String levelUrl) {
		this.levelUrl = levelUrl;
	}
	public long getValidDate() {
		return validDate;
	}
	public void setValidDate(long validDate) {
		this.validDate = validDate;
	}
	public long getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(long expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
