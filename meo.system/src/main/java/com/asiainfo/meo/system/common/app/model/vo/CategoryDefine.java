package com.asiainfo.meo.system.common.app.model.vo;

public class CategoryDefine {
	
    
    public static final Integer CAMPAGIN_TYPE = 1;
    public static final Integer PRODUCT_TYPE = 2;
	private Long categoryId;
	
	private Long parentCateId;
	
	private String categoryName;
	
	private String iconURL;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getParentCateId() {
		return parentCateId;
	}

	public void setParentCateId(Long parentCateId) {
		this.parentCateId = parentCateId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

    public String getIconURL()
    {
        return iconURL;
    }

    public void setIconURL(String iconURL)
    {
        this.iconURL = iconURL;
    }   	

}
