package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Sales implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8810390711371715288L;
	private Integer salesId;
	private String salesTitle;
	private String showImg;
	private Integer isDisplay;
	private Date createTime;
	private Integer sort;
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public String getSalesTitle() {
		return salesTitle;
	}
	public void setSalesTitle(String salesTitle) {
		this.salesTitle = salesTitle;
	}
	public String getShowImg() {
		return showImg;
	}
	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}
	public Integer getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
