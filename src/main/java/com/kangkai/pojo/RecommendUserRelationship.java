package com.kangkai.pojo;

import java.io.Serializable;
import java.util.Date;

public class RecommendUserRelationship implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4959047491569269838L;
	private Integer recommendUserRelationshipId;
	private Integer userId;
	private Integer recommendUserId;
	private Date createTime;
	public Integer getRecommendUserRelationshipId() {
		return recommendUserRelationshipId;
	}
	public void setRecommendUserRelationshipId(Integer recommendUserRelationshipId) {
		this.recommendUserRelationshipId = recommendUserRelationshipId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRecommendUserId() {
		return recommendUserId;
	}
	public void setRecommendUserId(Integer recommendUserId) {
		this.recommendUserId = recommendUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
