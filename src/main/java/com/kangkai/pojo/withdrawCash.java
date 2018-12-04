package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class withdrawCash implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 667294633048077414L;
	private Integer withdrawCashId;
	private Integer userId;
	private String bankNum;
	private String bankName;
	private Double money;
	private Integer state;
	private Date createTime;
	private Date completeTime;
	public Integer getWithdrawCashId() {
		return withdrawCashId;
	}
	public void setWithdrawCashId(Integer withdrawCashId) {
		this.withdrawCashId = withdrawCashId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
	
}
