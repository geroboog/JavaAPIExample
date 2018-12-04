package com.kangkai.pojo;

import java.io.Serializable;

public class SurveyorOrderMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4170529042650508407L;
	/**
	 * 
	 */
	private Integer surveyorOrderMessageId;
	private Integer userId;
	private Integer num;
	private Integer state;
	public Integer getSurveyorOrderMessageId() {
		return surveyorOrderMessageId;
	}
	public void setSurveyorOrderMessageId(Integer surveyorOrderMessageId) {
		this.surveyorOrderMessageId = surveyorOrderMessageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
