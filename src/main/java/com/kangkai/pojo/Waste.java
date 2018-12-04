package com.kangkai.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Waste implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1100536631764139069L;
	private Integer wasteId;
	private String describe;
	private Double money;
	private Integer type;
	private String payWay;
	private Integer userId;
	private String wasteNum;
	private Date createTime;
	private Integer isDelete;
	public Integer getWasteId() {
		return wasteId;
	}
	public void setWasteId(Integer wasteId) {
		this.wasteId = wasteId;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getWasteNum() {
		return wasteNum;
	}
	public void setWasteNum(String wasteNum) {
		this.wasteNum = wasteNum;
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
