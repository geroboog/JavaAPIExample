package com.kangkai.pojo;

import java.util.Date;

public class ProductOrderBase {
	private Integer productOrderId;
	private Integer state;
	private Double freight;
	private Double totalPrice;
	private Double finalPrice;
	private Integer couponId;
	private String productOrderNum;
	private Integer isComment;
	private Integer isCompleteAfterSales;
	private Integer isApplyAfterSales;
	private Integer isStore;
	private Date createTime;
	
	
	public Integer getIsStore() {
		return isStore;
	}
	public void setIsStore(Integer isStore) {
		this.isStore = isStore;
	}
	public Integer getIsApplyAfterSales() {
		return isApplyAfterSales;
	}
	public void setIsApplyAfterSales(Integer isApplyAfterSales) {
		this.isApplyAfterSales = isApplyAfterSales;
	}
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public String getProductOrderNum() {
		return productOrderNum;
	}
	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
	}
	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	public Integer getIsCompleteAfterSales() {
		return isCompleteAfterSales;
	}
	public void setIsCompleteAfterSales(Integer isCompleteAfterSales) {
		this.isCompleteAfterSales = isCompleteAfterSales;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
