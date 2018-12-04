package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class CommunityFollow implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3204657054181318378L;
	private Integer communityFollowId;
	private Integer userId;
	private Integer communityId;
	private Date createTime;
	private Integer isDelete;
	
	
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getCommunityFollowId() {
		return communityFollowId;
	}
	public void setCommunityFollowId(Integer communityFollowId) {
		this.communityFollowId = communityFollowId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
