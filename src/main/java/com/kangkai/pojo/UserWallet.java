package com.kangkai.pojo;

import java.io.Serializable;

public class UserWallet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8803956386169378632L;
	private Integer userWalletId;
	private Integer userId;
	private Double withDrawBalance;
	private Double balance;
	private Integer withDrawVersion;
	private Integer version;
	
	public Integer getUserWalletId() {
		return userWalletId;
	}
	public void setUserWalletId(Integer userWalletId) {
		this.userWalletId = userWalletId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getWithDrawBalance() {
		return withDrawBalance;
	}
	public void setWithDrawBalance(Double withDrawBalance) {
		this.withDrawBalance = withDrawBalance;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getWithDrawVersion() {
		return withDrawVersion;
	}
	public void setWithDrawVersion(Integer withDrawVersion) {
		this.withDrawVersion = withDrawVersion;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
