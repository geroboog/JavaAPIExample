package com.kangkai.pojo;

import java.io.Serializable;

public class ProductOrderItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8822830332152607659L;
	private Integer ProductOrderItemId;
	private Integer productOrderId;
	private String attrs;
	private Integer count;
	private Integer productId;
	private String productTitle;
	private Double productPrice;
	private String productShowImg;
	private String customAttrs;
	private Integer type;
	private Integer isStore;
	private String productNum;
	
	
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public Integer getIsStore() {
		return isStore;
	}
	public void setIsStore(Integer isStore) {
		this.isStore = isStore;
	}
	public Integer getProductOrderItemId() {
		return ProductOrderItemId;
	}
	public void setProductOrderItemId(Integer productOrderItemId) {
		ProductOrderItemId = productOrderItemId;
	}
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
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
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getProductShowImg() {
		return productShowImg;
	}
	public void setProductShowImg(String productShowImg) {
		this.productShowImg = productShowImg;
	}
	public String getCustomAttrs() {
		return customAttrs;
	}
	public void setCustomAttrs(String customAttrs) {
		this.customAttrs = customAttrs;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
