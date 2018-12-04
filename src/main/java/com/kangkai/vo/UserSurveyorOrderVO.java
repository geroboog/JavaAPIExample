package com.kangkai.vo;

import com.kangkai.pojo.SurveyorOrder;

public class UserSurveyorOrderVO extends SurveyorOrder{
	private String surveyorNickname="";
	private String surveyorUserIcon="";
	private int surveyorServeNum;
	private double surveyorEvaluationNum;
	public String getSurveyorNickname() {
		return surveyorNickname;
	}
	public void setSurveyorNickname(String surveyorNickname) {
			this.surveyorNickname = surveyorNickname;
	}
	public String getSurveyorUserIcon() {
		return surveyorUserIcon;
	}
	public void setSurveyorUserIcon(String surveyorUserIcon) {
		this.surveyorUserIcon = surveyorUserIcon;
	}
	public int getSurveyorServeNum() {
		return surveyorServeNum;
	}
	public void setSurveyorServeNum(int surveyorServeNum) {
		this.surveyorServeNum = surveyorServeNum;
	}
	public double getSurveyorEvaluationNum() {
		return surveyorEvaluationNum;
	}
	public void setSurveyorEvaluationNum(double surveyorEvaluationNum) {
		this.surveyorEvaluationNum = surveyorEvaluationNum;
	}
	
	
	
}
