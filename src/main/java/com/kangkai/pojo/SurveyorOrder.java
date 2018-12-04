package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class SurveyorOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6964750250957472938L;
	private Integer surveyorOrderId;
	private Integer userId;
	private Integer surveyorId;
	private Integer state;
	private Double money;
	private String surveyorOrderNum;
	private Date createTime;
	private Date lastEditTime;
	private String recipient;
	private String toProvince;
	private String toCity;
	private String toDistrict;
	private String phone;
	private String address;
	private Date bookTime;
	
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getToProvince() {
		return toProvince;
	}
	public void setToProvince(String toProvince) {
		this.toProvince = toProvince;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getToDistrict() {
		return toDistrict;
	}
	public void setToDistrict(String toDistrict) {
		this.toDistrict = toDistrict;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	public Integer getSurveyorOrderId() {
		return surveyorOrderId;
	}
	public void setSurveyorOrderId(Integer surveyorOrderId) {
		this.surveyorOrderId = surveyorOrderId;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getSurveyorOrderNum() {
		return surveyorOrderNum;
	}
	public void setSurveyorOrderNum(String surveyorOrderNum) {
		this.surveyorOrderNum = surveyorOrderNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
	
}
