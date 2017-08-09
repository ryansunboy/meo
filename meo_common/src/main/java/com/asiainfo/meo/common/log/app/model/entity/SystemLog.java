package com.asiainfo.meo.common.log.app.model.entity;

import java.sql.Timestamp;

public class SystemLog 
{
	private String requestId;
	private Object requestParams;
	private Object response;
	private String errorCode;
	private String errorMessage;
	private String status;
	private Timestamp createTime;
	private int httpStatus;
}
