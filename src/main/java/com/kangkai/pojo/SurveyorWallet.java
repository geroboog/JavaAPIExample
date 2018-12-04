package com.kangkai.pojo;

import java.io.Serializable;

public class SurveyorWallet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5254612954731772368L;
	private Integer surveyorWalletId;
	private Integer surveyorId;
	private Double withDrawBalance;
	private Double saleBalance;
	private Integer withDrawVersion;
	private Integer saleVersion;
	public Integer getSurveyorWalletId() {
		return surveyorWalletId;
	}
	public void setSurveyorWalletId(Integer surveyorWalletId) {
		this.surveyorWalletId = surveyorWalletId;
	}
	public Integer getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Integer surveyorId) {
		this.surveyorId = surveyorId;
	}
	public Double getWithDrawBalance() {
		return withDrawBalance;
	}
	public void setWithDrawBalance(Double withDrawBalance) {
		this.withDrawBalance = withDrawBalance;
	}
	public Double getSaleBalance() {
		return saleBalance;
	}
	public void setSaleBalance(Double saleBalance) {
		this.saleBalance = saleBalance;
	}
	public Integer getWithDrawVersion() {
		return withDrawVersion;
	}
	public void setWithDrawVersion(Integer withDrawVersion) {
		this.withDrawVersion = withDrawVersion;
	}
	public Integer getSaleVersion() {
		return saleVersion;
	}
	public void setSaleVersion(Integer saleVersion) {
		this.saleVersion = saleVersion;
	}
	
	
}
