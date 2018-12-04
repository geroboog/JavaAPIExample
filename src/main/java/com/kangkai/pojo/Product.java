package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6810221568083045060L;
	private Integer productId;
	private Integer adminId;
	private String productTitle;
	private Double productPrice;
	private String productShowImg;
	private String productDetail;
	private String afterSalesDetail;
	private Double VIPPrice;
	private Integer isDisPlay;
	private Date lastEditTime;
	private Date createTime;
	private String season;
	private Integer isDelete;
	private Integer isNotOnSale;
	private Integer is72;
	private String productNum;
	
	
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getAfterSalesDetail() {
		return afterSalesDetail;
	}
	public void setAfterSalesDetail(String afterSalesDetail) {
		this.afterSalesDetail = afterSalesDetail;
	}
	public Integer getIs72() {
		return is72;
	}
	public void setIs72(Integer is72) {
		this.is72 = is72;
	}
	public Integer getIsNotOnSale() {
		return isNotOnSale;
	}
	public void setIsNotOnSale(Integer isNotOnSale) {
		this.isNotOnSale = isNotOnSale;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public Double getVIPPrice() {
		return VIPPrice;
	}
	public void setVIPPrice(Double vIPPrice) {
		VIPPrice = vIPPrice;
	}
	public Integer getIsDisPlay() {
		return isDisPlay;
	}
	public void setIsDisPlay(Integer isDisPlay) {
		this.isDisPlay = isDisPlay;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
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
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	
	
}
