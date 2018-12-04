package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class SalesProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 950682391449099609L;
	private Integer salesProductId;
	private Integer salesId;
	private Integer productId;
	private Date createTime;
	public Integer getSalesProductId() {
		return salesProductId;
	}
	public void setSalesProductId(Integer salesProductId) {
		this.salesProductId = salesProductId;
	}
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
