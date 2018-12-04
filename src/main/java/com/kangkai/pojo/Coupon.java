package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1854852283461783997L;
	private Integer couponId;
	private Integer userId;
	private String couponName;
	private Double worth;
	private Integer type;
	private Double limitMoney;
	private Integer isUse;
	private Date expiryDate;
	private Date createTime;
	private Integer isDelete;
	
	
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Double getWorth() {
		return worth;
	}
	public void setWorth(Double worth) {
		this.worth = worth;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getLimitMoney() {
		return limitMoney;
	}
	public void setLimitMoney(Double limitMoney) {
		this.limitMoney = limitMoney;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
