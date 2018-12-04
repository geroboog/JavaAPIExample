package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Logs implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6669253306877915278L;
	private Integer  logsID;
	private String content;
	private Date createTime;
	private Integer userId;
	private Integer adminId;
	private String tableName;
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getLogsID() {
		return logsID;
	}
	public void setLogsID(Integer logsID) {
		this.logsID = logsID;
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
