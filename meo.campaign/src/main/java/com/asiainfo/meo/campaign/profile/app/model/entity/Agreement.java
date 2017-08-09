package com.asiainfo.meo.campaign.profile.app.model.entity;

// Generated 2015-4-9 13:11:57 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmAgreement generated by hbm2java
 */
public class Agreement implements java.io.Serializable {

	private long agreementId;
	private Integer sts;
	private Timestamp validDate;
	private Timestamp expiredDate;
	private Long contractId;
	private String campaignName;
	private String campaignUrl;
	private Integer campaignType;
	private String iconUrl;
	private Long inventory;
	private Long partnerId;

	public long getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(long agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getSts() {
		return this.sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public Timestamp getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Timestamp validDate) {
		this.validDate = validDate;
	}

	public Timestamp getExpiredDate() {
		return this.expiredDate;
	}

	public void setExpiredDate(Timestamp expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getCampaignName() {
		return this.campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignUrl() {
		return this.campaignUrl;
	}

	public void setCampaignUrl(String campaignUrl) {
		this.campaignUrl = campaignUrl;
	}

	public Integer getCampaignType() {
		return this.campaignType;
	}

	public void setCampaignType(Integer campaignType) {
		this.campaignType = campaignType;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Long getInventory() {
		return this.inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

    public Long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }

	
}
