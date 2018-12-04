package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class CommunityTopic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 818428315017518445L;
	private Integer communityTopicId;
	private Integer communityId;
	private Integer userId;
	private String content;
	private Integer productId;
	private Integer productOrderId;
	private String productTitle;
	private String productShowImg;
	private Date createTime;
	private Integer isDelete;
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductShowImg() {
		return productShowImg;
	}
	public void setProductShowImg(String productShowImg) {
		this.productShowImg = productShowImg;
	}
	public Integer getCommunityTopicId() {
		return communityTopicId;
	}
	public void setCommunityTopicId(Integer communityTopicId) {
		this.communityTopicId = communityTopicId;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
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
