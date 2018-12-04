package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class BlackList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7591245029038687565L;
	private Integer blackListId;
	private Integer userId;
	private String description;
	private Date createTime;
	public Integer getBlackListId() {
		return blackListId;
	}
	public void setBlackListId(Integer blackListId) {
		this.blackListId = blackListId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
