package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class SurveyorComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8173678077662790051L;
	
	private Integer surveyorCommentId;
	private Integer userId;
	private Integer surveyorId;
	private String content;
	private Double evaluation;
	private Integer surveyorOrderId;
	private Date createTime;
	public Integer getSurveyorCommentId() {
		return surveyorCommentId;
	}
	public void setSurveyorCommentId(Integer surveyorCommentId) {
		this.surveyorCommentId = surveyorCommentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}
	public Integer getSurveyorOrderId() {
		return surveyorOrderId;
	}
	public void setSurveyorOrderId(Integer surveyorOrderId) {
		this.surveyorOrderId = surveyorOrderId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
