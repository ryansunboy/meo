package com.asiainfo.meo.activity.business.model.entity;

// Generated 2015-5-15 11:02:02 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmCampaignActivitySummary generated by hbm2java
 */
public class CmCampaignActivitySummary implements java.io.Serializable {

	public static final Integer SUMMARY_TYPE_PARTICIPATE = 1;
    public static final Object STS_ACTIVATE = 1;
    public static final String ACTIVITY_SUMMARY_SEQ = "SEQ_SUMMARY_ACTIVITY_ID";
    private long id;
	private Long actionId;
	private Integer summaryType;
	private Long summaryValue;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private Integer sts;
	private String campaignNo;

	public CmCampaignActivitySummary() {
	}

	public CmCampaignActivitySummary(long id) {
		this.id = id;
	}

	public CmCampaignActivitySummary(long id, Long actionId,
			Integer summaryType, Long summaryValue, Timestamp createDate,
			Timestamp modifyDate, Integer sts, String campaignNo) {
		this.id = id;
		this.actionId = actionId;
		this.summaryType = summaryType;
		this.summaryValue = summaryValue;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.sts = sts;
		this.campaignNo = campaignNo;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getActionId() {
		return this.actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Integer getSummaryType() {
		return this.summaryType;
	}

	public void setSummaryType(Integer summaryType) {
		this.summaryType = summaryType;
	}

	public Long getSummaryValue() {
		return this.summaryValue;
	}

	public void setSummaryValue(Long summaryValue) {
		this.summaryValue = summaryValue;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getSts() {
		return this.sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public String getCampaignNo() {
		return this.campaignNo;
	}

	public void setCampaignNo(String campaignNo) {
		this.campaignNo = campaignNo;
	}

}
