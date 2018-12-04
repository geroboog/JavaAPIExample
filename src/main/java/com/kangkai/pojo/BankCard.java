package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class BankCard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -639404116497640711L;
	private Integer bankCardId;
	private Integer userId;
	private String name;
	private String cardNum;
	private String identification;
	private Integer isDefault;
	private Date createTime;
	
	public Integer getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
