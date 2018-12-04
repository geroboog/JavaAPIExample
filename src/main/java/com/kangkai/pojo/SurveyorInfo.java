package com.kangkai.pojo;

import java.io.Serializable;

public class SurveyorInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7121702083407039195L;
	private Integer surveyorInfoId;
	private Integer userId;
	private Double balance;
	private String version;
	private Double evaluation;
	private Integer evaluationNum;
	private Integer serveNum;
	public Integer getSurveyorInfoId() {
		return surveyorInfoId;
	}
	public void setSurveyorInfoId(Integer surveyorInfoId) {
		this.surveyorInfoId = surveyorInfoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}
	public Integer getEvaluationNum() {
		return evaluationNum;
	}
	public void setEvaluationNum(Integer evaluationNum) {
		this.evaluationNum = evaluationNum;
	}
	public Integer getServeNum() {
		return serveNum;
	}
	public void setServeNum(Integer serveNum) {
		this.serveNum = serveNum;
	}
	
	
}
