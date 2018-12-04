package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProductComment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1218472141357201183L;
	private Integer productCommentId;
	private Integer userId;
	private Integer productOrderId;
	private Integer productId;
	private String content;
	private Double evaluation;
	private Date createTime;
	private Integer isDelete;
	
	
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}
	public Integer getProductCommentId() {
		return productCommentId;
	}
	public void setProductCommentId(Integer productCommentId) {
		this.productCommentId = productCommentId;
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
