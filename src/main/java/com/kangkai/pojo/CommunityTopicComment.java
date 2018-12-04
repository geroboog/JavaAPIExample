package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class CommunityTopicComment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8981614224656833783L;
	private Integer communityTopicCommentId;
	private Integer communityTopicId;
	private Integer userId;
	private String content;
	private Date createTime;
	private Integer isDelete;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCommunityTopicCommentId() {
		return communityTopicCommentId;
	}
	public void setCommunityTopicCommentId(Integer communityTopicCommentId) {
		this.communityTopicCommentId = communityTopicCommentId;
	}
	public Integer getCommunityTopicId() {
		return communityTopicId;
	}
	public void setCommunityTopicId(Integer communityTopicId) {
		this.communityTopicId = communityTopicId;
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
