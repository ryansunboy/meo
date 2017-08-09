package com.asiainfo.meo.activity.business.model.entity;

// Generated 2015-5-15 10:28:04 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmOrderActivity generated by hbm2java
 */
public class CmOrderActivity implements java.io.Serializable {

	private long orderId;
	private Long activityId;
	private long entityId;
	private int entityType;
	private long productId;
	private Timestamp doneTime;
	private Long resourceId;

	public CmOrderActivity() {
	}

	public CmOrderActivity(long orderId, long entityId, int entityType,
			long productId, Timestamp doneTime) {
		this.orderId = orderId;
		this.entityId = entityId;
		this.entityType = entityType;
		this.productId = productId;
		this.doneTime = doneTime;
	}

	public CmOrderActivity(long orderId, Long activityId, long entityId,
			int entityType, long productId, Timestamp doneTime, Long resourceId) {
		this.orderId = orderId;
		this.activityId = activityId;
		this.entityId = entityId;
		this.entityType = entityType;
		this.productId = productId;
		this.doneTime = doneTime;
		this.resourceId = resourceId;
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public int getEntityType() {
		return this.entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Timestamp getDoneTime() {
		return this.doneTime;
	}

	public void setDoneTime(Timestamp doneTime) {
		this.doneTime = doneTime;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}