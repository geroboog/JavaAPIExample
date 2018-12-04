package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class Community implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3417952568281386553L;
	private Integer communityId;
	private String communityTitle;
	private String adminId;
	private String content;
	private String communityImg;
	private Date createTime;
	private Integer isDisplay;
	private Integer isDelete;
	
	
	public String getCommunityImg() {
		return communityImg;
	}
	public void setCommunityImg(String communityImg) {
		this.communityImg = communityImg;
	}
	public Integer getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getCommunityTitle() {
		return communityTitle;
	}
	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
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