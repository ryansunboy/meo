package com.asiainfo.meo.activity.manager.model.entity;

// Generated 2015-5-15 10:28:04 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmJoinActivity generated by hbm2java
 */
public class CmJoinActivity implements java.io.Serializable {

	public static final String SEQ_CM_JOIN_ACTIVITY = "SEQ_ACTIVITY_ID";
    private long activityId;
	private Long custId;
	private Timestamp doneTime;
	private Long actionId;

	public CmJoinActivity() {
	}

	public CmJoinActivity(long activityId) {
		this.activityId = activityId;
	}

	public CmJoinActivity(long activityId, Long custId, Timestamp doneTime,
			Long actionId) {
		this.activityId = activityId;
		this.custId = custId;
		this.doneTime = doneTime;
		this.actionId = actionId;
	}

	public long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public Long getCustId() {
		return this.custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Timestamp getDoneTime() {
		return this.doneTime;
	}

	public void setDoneTime(Timestamp doneTime) {
		this.doneTime = doneTime;
	}

	public Long getActionId() {
		return this.actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

}
