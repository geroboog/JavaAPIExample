package com.kangkai.pojo;

import java.io.Serializable;

public class ProductAttr implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7915018727215413717L;
	/**
	 * 
	 */
	private Integer productAttrId;
	private String productAttrName;
	private String productAttrValue;
	private Integer productId;
	public Integer getProductAttrId() {
		return productAttrId;
	}
	public void setProductAttrId(Integer productAttrId) {
		this.productAttrId = productAttrId;
	}
	public String getProductAttrName() {
		return productAttrName;
	}
	public void setProductAttrName(String productAttrName) {
		this.productAttrName = productAttrName;
	}
	public String getProductAttrValue() {
		return productAttrValue;
	}
	public void setProductAttrValue(String productAttrValue) {
		this.productAttrValue = productAttrValue;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
	
}
