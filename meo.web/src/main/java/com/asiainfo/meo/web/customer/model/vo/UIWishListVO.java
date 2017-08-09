package com.asiainfo.meo.web.customer.model.vo;

public class UIWishListVO {

	private Long prodId;
	private String prodName;
	private Integer prodType;
	private Integer prodSts;
	private Integer curInventory;
	private Integer inventory;
	private Integer categoryId;
	private String imagUrl;
	private Long validDate;
	private Long expiredDate;
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getProdType() {
		return prodType;
	}
	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}
	public Integer getProdSts() {
		return prodSts;
	}
	public void setProdSts(Integer prodSts) {
		this.prodSts = prodSts;
	}
	public Integer getCurInventory() {
		return curInventory;
	}
	public void setCurInventory(Integer curInventory) {
		this.curInventory = curInventory;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getImagUrl() {
		return imagUrl;
	}
	public void setImagUrl(String imagUrl) {
		this.imagUrl = imagUrl;
	}
	public Long getValidDate() {
		return validDate;
	}
	public void setValidDate(Long validDate) {
		this.validDate = validDate;
	}
	public Long getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Long expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
