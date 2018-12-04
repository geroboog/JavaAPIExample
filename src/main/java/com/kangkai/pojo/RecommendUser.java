package com.kangkai.pojo;

import java.util.Date;

public class RecommendUser {
	private Integer recommendUserId;
	private String phone;
	private Integer redPacketId;
	private Integer userId;
	private Double money;
	private Date createTime;
	
	public Integer getRecommendUserId() {
		return recommendUserId;
	}
	public void setRecommendUserId(Integer recommendUserId) {
		this.recommendUserId = recommendUserId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRedPacketId() {
		return redPacketId;
	}
	public void setRedPacketId(Integer redPacketId) {
		this.redPacketId = redPacketId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
