package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProductCollect implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3624969362297347925L;
	private Integer productCollectId;
	private Integer userId;
	private Integer productId;
	private Date createTime;
	private Integer isDelete;
	
	public Integer getProductCollectId() {
		return productCollectId;
	}
	public void setProductCollectId(Integer productCollectId) {
		this.productCollectId = productCollectId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
