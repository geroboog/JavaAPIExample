package com.kangkai.pojo;

import java.io.Serializable;

public class PayWay implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8836406255765548114L;
	private Integer payWayId;
	private String payWayName;
	private String channel;
	private Integer isDelete;
	public Integer getPayWayId() {
		return payWayId;
	}
	public void setPayWayId(Integer payWayId) {
		this.payWayId = payWayId;
	}
	public String getPayWayName() {
		return payWayName;
	}
	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
