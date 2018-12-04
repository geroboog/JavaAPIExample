package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class RedPacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7390111181094531710L;
	private Integer redPacketId;
	private Integer productOrderId;
	private Integer userId;
	private Double money;
	private Integer isMyTake;
	private Integer takeNum;
	private Integer maxNum;
	private Date createTime;
	public Integer getRedPacketId() {
		return redPacketId;
	}
	public void setRedPacketId(Integer redPacketId) {
		this.redPacketId = redPacketId;
	}
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
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
	public Integer getIsMyTake() {
		return isMyTake;
	}
	public void setIsMyTake(Integer isMyTake) {
		this.isMyTake = isMyTake;
	}
	public Integer getTakeNum() {
		return takeNum;
	}
	public void setTakeNum(Integer takeNum) {
		this.takeNum = takeNum;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
