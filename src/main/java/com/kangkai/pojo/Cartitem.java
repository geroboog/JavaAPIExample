package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Cartitem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9115418311459716979L;
	private Integer cartitemId;
	private Integer productId;
	private String attrs;
	private Integer count;
	private Integer userId;
	private Integer type;
	private String customAttrs;
	private Date createTime;
	private Double customPrice;
	
	
	public Double getCustomPrice() {
		return customPrice;
	}
	public void setCustomPrice(Double customPrice) {
		this.customPrice = customPrice;
	}
	public Integer getCartitemId() {
		return cartitemId;
	}
	public void setCartitemId(Integer cartitemId) {
		this.cartitemId = cartitemId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getAttrs() {
		return attrs;
	}
	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCustomAttrs() {
		return customAttrs;
	}
	public void setCustomAttrs(String customAttrs) {
		this.customAttrs = customAttrs;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
