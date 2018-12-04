package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8693916824741130164L;
	private Integer logId;
	private String content;
	private Date createTime;
	private Integer userId;
	private Integer adminId;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	
}
