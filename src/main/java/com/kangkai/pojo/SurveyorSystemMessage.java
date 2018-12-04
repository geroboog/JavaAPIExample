package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class SurveyorSystemMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2768311372958847672L;
	/**
	 * 
	 */
	private Integer surveyorSystemMessageId;
	private Integer surveyorId;
	private String content;
	private Date createTime;
	private Integer surveyorOrderId;
	private Integer type;
	private Integer isRead;
	
	
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSurveyorSystemMessageId() {
		return surveyorSystemMessageId;
	}
	public void setSurveyorSystemMessageId(Integer surveyorSystemMessageId) {
		this.surveyorSystemMessageId = surveyorSystemMessageId;
	}
	public Integer getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Integer surveyorId) {
		this.surveyorId = surveyorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSurveyorOrderId() {
		return surveyorOrderId;
	}
	public void setSurveyorOrderId(Integer surveyorOrderId) {
		this.surveyorOrderId = surveyorOrderId;
	}
	
	
	
}
