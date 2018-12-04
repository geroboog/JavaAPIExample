package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class WasteNum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7926780556243907411L;
	/**
	 * 
	 */
	private Integer wasteNumId;
	private String wasteNum;
	private String productOrderNum;
	private String surveyorOrderNum;
	private Integer userId;
	private Date createTime;
	private Integer isDelete;
	public Integer getWasteNumId() {
		return wasteNumId;
	}
	public void setWasteNumId(Integer wasteNumId) {
		this.wasteNumId = wasteNumId;
	}
	
	public String getWasteNum() {
		return wasteNum;
	}
	public void setWasteNum(String wasteNum) {
		this.wasteNum = wasteNum;
	}
	public String getProductOrderNum() {
		return productOrderNum;
	}
	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
	}
	public String getSurveyorOrderNum() {
		return surveyorOrderNum;
	}
	public void setSurveyorOrderNum(String surveyorOrderNum) {
		this.surveyorOrderNum = surveyorOrderNum;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
