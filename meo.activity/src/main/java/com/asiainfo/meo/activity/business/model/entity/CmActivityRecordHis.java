package com.asiainfo.meo.activity.business.model.entity;

// Generated 2015-8-3 10:10:37 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmActivityRecordHis generated by hbm2java
 */
public class CmActivityRecordHis implements java.io.Serializable {

	public static final String ACTIVITY_RECORD_HIS_ID_SEQ = "SEQ_ACTIVITY_RECORD_HIS_ID";
    private long hisId;
	private Long activityId;
	private String content;
	private Timestamp doneTime;

	public CmActivityRecordHis() {
	}

	public CmActivityRecordHis(long hisId) {
		this.hisId = hisId;
	}

	public CmActivityRecordHis(long hisId, Long activityId, String content,
			Timestamp doneTime) {
		this.hisId = hisId;
		this.activityId = activityId;
		this.content = content;
		this.doneTime = doneTime;
	}

	public long getHisId() {
		return this.hisId;
	}

	public void setHisId(long hisId) {
		this.hisId = hisId;
	}

	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDoneTime() {
		return this.doneTime;
	}

	public void setDoneTime(Timestamp doneTime) {
		this.doneTime = doneTime;
	}

}
