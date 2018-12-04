package com.kangkai.pojo;

import java.io.Serializable;

public class ProductOrderMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7800617718297874152L;
	private Integer productOrderMessageId;
	private Integer userId;
	private Integer num;
	private Integer state;
	public Integer getProductOrderMessageId() {
		return productOrderMessageId;
	}
	public void setProductOrderMessageId(Integer productOrderMessageId) {
		this.productOrderMessageId = productOrderMessageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
