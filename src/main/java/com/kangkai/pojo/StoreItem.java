package com.kangkai.pojo;

import java.io.Serializable;

public class StoreItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4653370553707207046L;
	private Integer storeItemId;
	private Integer storeId;
	private Integer productOrderItemId;
	
	public Integer getStoreItemId() {
		return storeItemId;
	}
	public void setStoreItemId(Integer storeItemId) {
		this.storeItemId = storeItemId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProductOrderItemId() {
		return productOrderItemId;
	}
	public void setProductOrderItemId(Integer productOrderItemId) {
		this.productOrderItemId = productOrderItemId;
	}
	
	
}
