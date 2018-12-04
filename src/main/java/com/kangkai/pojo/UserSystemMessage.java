package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserSystemMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3262321965679457786L;
	private Integer userSystemMessageId;
	private Integer userId;
	private String content;
	private Date createTime;
	private Integer productOrderId;
	private Integer surveyorOrderId;
	private Integer type;
	private Integer isRead;
	
	
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getUserSystemMessageId() {
		return userSystemMessageId;
	}
	public void setUserSystemMessageId(Integer userSystemMessageId) {
		this.userSystemMessageId = userSystemMessageId;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}
	public Integer getSurveyorOrderId() {
		return surveyorOrderId;
	}
	public void setSurveyorOrderId(Integer surveyorOrderId) {
		this.surveyorOrderId = surveyorOrderId;
	}
	
}
